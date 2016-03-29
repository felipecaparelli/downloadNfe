package br.com.wro.nfe.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;

import br.com.wro.nfe.enums.RetornoProcessamentoDownload;
import br.com.wro.nfe.exception.ProcessoNfeException;
import downloadNFe_v100.TRetDownloadNFe;
import downloadNFe_v100.TRetDownloadNFe.RetNFe;

/**
 * Classe responsável por dividir o processamento das notas que já foram baixadas e o processo de distribuição.
 */
public class ProcessaDownloadNFeService extends BaseService {
	
	private static final Logger logger = Logger.getLogger(ProcessaDownloadNFeService.class);
	
	
	protected static boolean execute(String chaveNFe, Long idLogProcessamento) throws ProcessoNfeException, JAXBException, RemoteException, XMLStreamException {
		
		//3 - Executa o download da NF-e (necessário que o evento de ciencia da NF ja tenha sido enviado!
		String xmlDownloadNFe = DownloadNFeService.execute(CNPJ_DESTINATARIO, chaveNFe);
		
		boolean result = true;
		
		logger.info(xmlDownloadNFe);	
		
		TRetDownloadNFe nfeDown = unmarshallXml(TRetDownloadNFe.class, xmlDownloadNFe);
		
		List<RetNFe> resNFeList = nfeDown.getRetNFe();
		
		Integer codigo = nfeDown.getCStat() != null ? Integer.parseInt(nfeDown.getCStat()) : null;
		
		if(codigo != null && (RetornoProcessamentoDownload.COD_139.getCodigo() == codigo || RetornoProcessamentoDownload.COD_138.getCodigo() == codigo)){
			
			if(resNFeList != null && !resNFeList.isEmpty()){
				
				for (RetNFe retNFe : resNFeList) {
					
					Integer codStat = retNFe.getCStat() != null ? Integer.parseInt(retNFe.getCStat()) : null;
					
					if(codStat != null && codStat.equals(RetornoProcessamentoDownload.COD_140)) {
						
						logger.info("NF-e --------------------------------------------------------");
						
						//TNfeProc: (nfe + protocolo)
						if("3.10".equals(retNFe.getProcNFe().getSchema())) {							
							//persistir os dados da NFe v 3.10
							result = result && ProcessaNFe_v310Service.processaNFe(retNFe.getProcNFe().getAny(), idLogProcessamento);
						} else {
							//persistir os dados da NFe v 1.10
							result = result && ProcessaNFe_v110Service.processaNFe(retNFe.getProcNFe().getAny(), idLogProcessamento);
						}
						
						
					} else {
						//processamento não executado...
						throw new ProcessoNfeException("NF-e não processada: " + codStat + " - " + retNFe.getXMotivo());
					}
				}
			}
			
		} else {
			//Arquivo não processado...
			throw new ProcessoNfeException("Processamento não executado: " + nfeDown.getCStat() + " - " + nfeDown.getXMotivo());
		}
		
		return result;
	}

}
