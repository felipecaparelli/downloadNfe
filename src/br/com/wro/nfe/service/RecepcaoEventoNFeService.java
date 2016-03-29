package br.com.wro.nfe.service;

import java.security.KeyStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.log4j.Logger;

import br.com.wro.nfe.enums.TipoEvento;
import br.inf.portalfiscal.www.nfe.wsdl.recepcaoevento.RecepcaoEventoStub;

/**
 * Classe responsável por gerar eventos de Manifestação do Destinatário. 
 * (vide documento: Nota Técnica 2012.002 - fonte: http://www.nfe.fazenda.gov.br/portal/listaConteudo.aspx?tipoConteudo=tW+YMyk/50s=).
 * 
 * Outra URL possivel: https://nfe.fazenda.sp.gov.br/ws/recepcaoevento.asmx
 *
 */
public class RecepcaoEventoNFeService extends BaseService {
	
	private static final Logger LOG = Logger.getLogger(RecepcaoEventoNFeService.class);
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
	
	private static final String URL_SERVICO = "https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx";
	
	private static int idLote = 0;
	
	/**
	 * Monta o XML (schema envConfRecebto_v9.99.xsd), os stubs e faz a chamada ao web service RecepcaoEvento. 
	 * 
	 * @param cnpj
	 * @param chaveNFe
	 */
	public static String execute(KeyStore keyStore, String cnpj, String chaveNFe) {
		
		try {
			
			TipoEvento tpEvento = TipoEvento.CIENCIA;
			
			String xmlEvento = "<evento versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"		
			+ "<infEvento Id=\"ID" + tpEvento.getCodigo() + chaveNFe + "01\">"
			+ "<cOrgao>91</cOrgao>"
			+ "<tpAmb>" + TP_AMB + "</tpAmb>"
			+ "<CNPJ>" + cnpj + "</CNPJ>"
			+ "<chNFe>" + chaveNFe + "</chNFe>"
			+ "<dhEvento>" + df.format(new Date()) + "</dhEvento>"
			+ "<tpEvento>" + tpEvento.getCodigo() + "</tpEvento>"
			+ "<nSeqEvento>1</nSeqEvento>"
			+ "<verEvento>1.00</verEvento>"
			+ "<detEvento versao=\"1.00\">"
			+ "<descEvento>" + tpEvento.getDescricao() + "</descEvento>"
			+ "</detEvento>"
			+ "</infEvento>"
			+ "</evento>";
			
			//Assina o XML do evento
			String xmlEventoAssinado = new AssinadorNFeXML(keyStore).assinaEvento(xmlEvento);
			
			//Remove o cabecalho do XML (invalido)
			xmlEventoAssinado = xmlEventoAssinado.substring(xmlEventoAssinado.indexOf("?>")+2);

			// Schema XML envConfRecebto_v9.99.xsd
			String xmlEnvioEvento = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ "<envEvento versao=\"1.00\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">"			
			+ "<idLote>" + (++idLote) + "</idLote>"
			+ xmlEventoAssinado
			+ "</envEvento>";
			
			LOG.info("XML Envio do Evento: " + xmlEnvioEvento);
			
			OMElement ome = AXIOMUtil.stringToOM(xmlEnvioEvento);
			
			//Popula os dados nos stubs de envio
			RecepcaoEventoStub.NfeDadosMsg dadosMsg = new RecepcaoEventoStub.NfeDadosMsg();    
            dadosMsg.setExtraElement(ome);
            
            RecepcaoEventoStub.NfeCabecMsg nfeCabecMsg = new RecepcaoEventoStub.NfeCabecMsg();               
            nfeCabecMsg.setCUF(COD_UF_IBGE);   
            nfeCabecMsg.setVersaoDados("1.00");    
    
            RecepcaoEventoStub.NfeCabecMsgE nfeCabecMsgE = new RecepcaoEventoStub.NfeCabecMsgE();    
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);    
    
            //Executa o web service
            RecepcaoEventoStub stub = new RecepcaoEventoStub(URL_SERVICO);
            RecepcaoEventoStub.NfeRecepcaoEventoResult result = stub.nfeRecepcaoEvento(dadosMsg, nfeCabecMsgE);
			
            //Retorna os dados
			return result.getExtraElement().toString();			

		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		
		return null;
	}

}
