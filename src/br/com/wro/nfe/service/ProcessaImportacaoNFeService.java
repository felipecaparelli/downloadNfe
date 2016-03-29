package br.com.wro.nfe.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.KeyStore;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import resNFe_v100.ResNFe;
import retDistDFeInt_v100.RetDistDFeInt;
import retDistDFeInt_v100.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.wro.nfe.dao.GenericDAO;
import br.com.wro.nfe.enums.IndicadorSN;
import br.com.wro.nfe.enums.IndicadorStatusProcNFe;
import br.com.wro.nfe.exception.ProcessoNfeException;
import br.com.wro.nfe.model.LogProcessoNfe;
import br.com.wro.nfe.mybatis.DBUtils;
import br.com.wro.nfe.mybatis.filter.LogProcessoNfeFilter;
import br.com.wro.nfe.mybatis.mapper.LogProcessoNfeMapper;
import br.com.wro.nfe.utils.ZipUtils;


/**
 * <code>
 * <h1>ProcessaImportacaoNFeService</h1>
 * 
 * Pacote: 		br.com.wro.nfe.service<br>
 * Descrição: 	Classe principal para execução do processo de importação da nota fiscal eletrônica. Realiza a integração de todo o processo.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	08/05/2015<br>
 * </code>
 */
public class ProcessaImportacaoNFeService extends BaseService {
	
	private static final Logger logger = Logger.getLogger(ProcessaImportacaoNFeService.class);
	
	private static int total_execucoes = 0;
	
	private static KeyStore keyStore;
	
	private static GenericDAO<LogProcessoNfe> logProcessoNfeDAO;
	
	private static String codNSU = "000000000016977"; //NSU inicial (1st execution)
	
	private static IndicadorStatusProcNFe indStaProc = IndicadorStatusProcNFe.FINALIZADO_SUCESSO;
	
	/**
	 * Método de entrada para a execução do processamento das NFes.
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			//inicia a conexão...
			keyStore = NFeConnector.connect();
			//inicia a execução...
			if(args != null && args.length > 0) {
				String statProc = args[0];
				if(statProc != null && "erro".equalsIgnoreCase(statProc)) {
					//para realizar o processo a partir dos lotes com erro.
					indStaProc = IndicadorStatusProcNFe.ERRO_PROCESSAMENTO;
				}
			}
			execute(obterUltimoNSUProcessado());
		} catch (Exception e) {
			logger.error(e);
		}
	}
	

	/**
	 * Obtem o ultimo log para receber o ultNSU registrado com sucesso! caso seja null utilizar o atual
	 * @return
	 */
	protected static String obterUltimoNSUProcessado() {
		
		
		SqlSession sqlSession = null;
		
		try {
			
			//abre uma sessao com o DB		
			sqlSession = getSqlSessionFactory().openSession();
			
			logProcessoNfeDAO = new GenericDAO<LogProcessoNfe>(sqlSession);
			
			LogProcessoNfeFilter filter = new LogProcessoNfeFilter.Builder()
																	.setIndUltFinalizado(IndicadorSN.SIM)
																	.build();
			
			List<LogProcessoNfe> logList = logProcessoNfeDAO.list(DBUtils.getNamedQuery(LogProcessoNfeMapper.class, "consultarLogProcessoNfe"), filter);
			
			if(logList == null || logList.isEmpty()) return codNSU;
			
			codNSU = logList.get(0).getCodNSU();
			
			//concluiu com sucesso o log do processo...
			logger.info("Log do processamento atualizado com sucesso!");	
			
			return codNSU;
			
		} catch (Exception e) {
			//rollback dos banco no DB
			if(sqlSession != null) sqlSession.rollback();
			//erro no processamento da NFe...
			logger.error(e);
			
			return null;
			
		} finally {
			//fechar sessao
			if(sqlSession != null) {				
				sqlSession.close();
			}
		}
	}
	
	/**
	 * Inicia o processamento das Notas Fiscais emitidas contra a empresa (destinatario);
	 * 
	 * 
	 * @param ultNSU - identificador do ultimo lote processado (código NSU)
	 * 
	 * @throws RemoteException
	 * @throws XMLStreamException
	 * @throws InterruptedException
	 * @throws JAXBException
	 */
	protected static void execute(String ultNSU) throws RemoteException, XMLStreamException, InterruptedException, JAXBException {
		
		++total_execucoes;
		
		if(total_execucoes > 3){
			//apos 5 chamadas eu executo uma parada de 30 segundos.
			Thread.sleep(30000);
			total_execucoes=0;
			
		} else if (total_execucoes > 5) {
			//estou parando em 5 para evitar sobrecarregar o serviço!
			System.exit(0);
			
		} else {
			
			//1 - Executa a chamada ao web service NFeDistribuicaoDFe (obtem os documentos zipados - nfe | resumo de nfe)  
			String xmlRetDistDFeInt = ConsultaNFeDistribuicaoDFeService.execute(ultNSU);
			
			//1.1 - Faz o parse do retorno...
			RetDistDFeInt retorno = unmarshallXml(RetDistDFeInt.class, xmlRetDistDFeInt);
			
			//2 - processa a leitura desses arquivos zipados (lote)
			processaLoteNFe(retorno);
			
			//3 - Reexecuta com o proximo lote, caso o codNSU seja diferente do MAX_NSU desse lote
			if(retorno != null && !retorno.getMaxNSU().equals("") && !retorno.getMaxNSU().equals(ultNSU)){
				//Inicia nova execução, a partir do maior NSU retornado (max)
				execute(retorno.getMaxNSU());
			}
		}
	}
	
	/**
	 * Processa o objeto retornado da consulta (RetDistDFeInt).
	 * @param retDistDFeInt
	 */
	private static void processaLoteNFe(RetDistDFeInt retDistDFeInt) {
		
		boolean sucesso = true;
	    
	    if (retDistDFeInt != null) { 
	    	
	        logger.info("\nRetorno Distribuicao...: " + retDistDFeInt.getVersao()
	        			+ "\nV Aplic...............: " + retDistDFeInt.getVerAplic()
	        			+ "\nCStat.................: " + retDistDFeInt.getCStat() 
				        + "\nXMotivo...............: " + retDistDFeInt.getXMotivo() 
				        + "\nUltNSU................: " + retDistDFeInt.getUltNSU() 
				        + "\nMaxNSU................: " + retDistDFeInt.getMaxNSU());  
	        
	        //Verifica se o lote existe...
	        if (retDistDFeInt.getLoteDistDFeInt() != null) {
	        	
	        	LogProcessoNfe log = null;
	        	Long idLogProc = null;
	        	String codProNSU = null;
	        	boolean mudouNSU = false;
	        		        	
	        	//Inicia leitura dos arquivos com o resumo das NFes...
	            for (DocZip arquivoZipado : retDistDFeInt.getLoteDistDFeInt().getDocZip()) {
	            	
	            	
	            	logger.info("\nDoc Zip -------------------------------------------"
			        			+ "\nNSU..................: " + codProNSU
						        + "\nSchema...............: " + arquivoZipado.getSchema());
	                
	            	if(codProNSU == null || !arquivoZipado.getNSU().equals(codProNSU)) {	            		
	            		log = new LogProcessoNfe(idLogProc, arquivoZipado.getNSU(), indStaProc);
	            		codProNSU = arquivoZipado.getNSU();
	            		mudouNSU = true;
	            	} else {
	            		log.setId(idLogProc);
	            		mudouNSU = false;
	            	}
	    			
	    			//Registra o log do processamento...
	    			sucesso = sucesso && registraLogProcessoNfe(log);
	    			
	    			idLogProc = log.getId();
	                
	                if(arquivoZipado.getValue() != null) {
	                	
	                	//1 - Descompacta o arquivo com o lote de NF-e (resumo | nfe)
	                	ByteArrayInputStream xmlUnZipped = new ByteArrayInputStream(ZipUtils.unzipBestEffort(arquivoZipado.getValue()));	 
	                	
						try {
							
							String xmlNFe = IOUtils.toString(xmlUnZipped, "UTF-8");
							
							//Resumo da NFe...
							if(xmlNFe != null && xmlNFe.startsWith("<resNFe")) {
								
								ResNFe resNFe = unmarshallXml(ResNFe.class, xmlNFe);
								
								if(resNFe != null) {
									
									logger.info("XML Resumo NFe ------------------------------ "
									+ "\n____________ NFe: CNPJ...........: " + resNFe.getCNPJ()
									+ "\n____________ Chave..........: " + resNFe.getChNFe()
									+ "\n____________ Valor..........: " + resNFe.getVNF()
									+ "\n____________ Dh. Emis.......: " + resNFe.getDhEmi()
									+ "\n____________ Situacao.......: " + resNFe.getCSitNFe()
									+ "\n____________ Dt. Recb.......: " + resNFe.getDhRecbto()
									+ "\n____________ IE.............: " + resNFe.getIE()
									+ "\n____________ Num Protoc.....: " + resNFe.getNProt()
									+ "\n____________ Tipo...........: " + resNFe.getTpNF()
									+ "\n____________ Versao.........: " + resNFe.getVersao()
									+ "\n____________ VNF............: " + resNFe.getVNF());
									
									//2 - Registra a ciencia da operacao, caso contrario nao conseguira baixar a NFe
									String xmlRetornoEvento = RecepcaoEventoNFeService.execute(keyStore, CNPJ_DESTINATARIO, resNFe.getChNFe());
									
									//2.1 - retorno do evento...
									logger.info(xmlRetornoEvento);
									
									/**
									 * TODO Seria interessante quebrar esse processo em 2 para ficar mais inteligente. 
									 * Fechariamos aqui no envio do evento para dar ciencia da operacao, e em outro 
									 * processo independente fariamos o download com base nas notas que tiveram 
									 * sucesso no retorno do evento anterior!
									 */
									sucesso = sucesso && ProcessaDownloadNFeService.execute(resNFe.getChNFe(), idLogProc);
									
								} else {
									throw new ProcessoNfeException("Resumo da NFe não encontrado.");
								}
								
							} else if(xmlNFe != null && xmlNFe.startsWith("<nfeProc")) {
								
								//NFe completa...								
								if(xmlNFe.startsWith("<nfeProc versao=\"3.10")) {
									//persistir os dados da NFe v 3.10
									sucesso = sucesso && ProcessaNFe_v310Service.processaNfeProc(xmlNFe, idLogProc);
								} else {
									//persistir os dados da NFe v 1.10
									sucesso = sucesso && ProcessaNFe_v110Service.processaNfeProc(xmlNFe, idLogProc);
								}
								
							} else {
								throw new ProcessoNfeException("Nenhum dos documentos esperados foi retornado- " + xmlNFe);
							}
							
							logger.info("+ Fim da NF ---------------------------------------------------------"); 
							
						} catch (IOException e) {
							sucesso = logError(log, e.getMessage());
						} catch (XMLStreamException e) {
							sucesso = logError(log, e.getMessage());
						} catch (JAXBException e) {
							sucesso = logError(log, e.getMessage());
						} catch (TransformerFactoryConfigurationError e) {
							sucesso = logError(log, e.getMessage());
						} catch (ProcessoNfeException e) {
							sucesso = logError(log, e.getMessage());
						} catch (Exception e) {
							sucesso = logError(log, e.getMessage());
						}
	                }
	                
	                if(sucesso) {
						log.setIndStatus(IndicadorStatusProcNFe.FINALIZADO_SUCESSO);
						log.setDescricaoErro(null);
					} else {
						log.setIndStatus(IndicadorStatusProcNFe.ERRO_PROCESSAMENTO);
						if(log.getDescricaoErro() == null) log.setDescricaoErro("Erro não identificado.");
					}
					
	                if(!sucesso || mudouNSU) {	                	
	                	registraLogProcessoNfe(log);
	                }
	                
	                logger.info("+-------------------------------------------------------------------");  
	            }  
	        }  
	    }  
	}
	
	/**
	 * Persiste o log do processamento do lote.
	 * 
	 * @param log
	 * @return
	 */
	private static boolean registraLogProcessoNfe(LogProcessoNfe log) {
		
		SqlSession sqlSession = null;
		
		try {
			
			//abre uma sessao com o DB		
			sqlSession = getSqlSessionFactory().openSession();
			
			logProcessoNfeDAO = new GenericDAO<LogProcessoNfe>(sqlSession);
			
			//inicializa o log do processamento...
			logProcessoNfeDAO.update(DBUtils.getNamedQuery(LogProcessoNfeMapper.class, "manterLogProcessoNfe"), log);
			
			//commita os dados para o DB...
			sqlSession.commit();
			
			//concluiu com sucesso o log do processo...
			logger.info("Log do processamento atualizado com sucesso!");	
			
			return true;
			
		} catch (Exception e) {
			//rollback dos banco no DB
			if(sqlSession != null) sqlSession.rollback();
			//erro no processamento da NFe...
			logger.error(e);
			
			return false;
			
		} finally {
			//fechar sessao
			if(sqlSession != null) {				
				sqlSession.close();
			}
		}
	}

	
	
	/**
	 * Log o erro.
	 * 
	 * @param log
	 * @param exceptionMsg
	 * @return
	 */
	private static boolean logError(LogProcessoNfe log, String exceptionMsg) {
		boolean result;
		if(exceptionMsg == null) exceptionMsg = "Erro não identificado.";
		log.setDescricaoErro(exceptionMsg);
		logger.error(exceptionMsg);
		result = false;
		return result;
	}

}