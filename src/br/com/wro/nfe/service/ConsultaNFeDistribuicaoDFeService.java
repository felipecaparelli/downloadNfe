package br.com.wro.nfe.service;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.log4j.Logger;

import br.inf.portalfiscal.www.nfe.wsdl.nfedistribuicaodfe.NFeDistribuicaoDFeStub;
import br.inf.portalfiscal.www.nfe.wsdl.nfedistribuicaodfe.NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse;


/**
 * Classe responsável por conectar no serviço de Ditribuição DF-e para consultar as NFes a partir do CNPJ do destinatário.
 * (vide: Nota Técnica 2014/002 - fonte: http://www.nfe.fazenda.gov.br/portal/exibirArquivo.aspx?conteudo=2Z%20kcHOdpHs=)
 * 
 */
public class ConsultaNFeDistribuicaoDFeService extends BaseService {
	
	private static final Logger LOG = Logger.getLogger(ConsultaNFeDistribuicaoDFeService.class);
	
	private static final String URL_SERVICO = "https://www1.nfe.fazenda.gov.br/NFeDistribuicaoDFe/NFeDistribuicaoDFe.asmx";

	/**
	 * Monta o XML (schema distDFeInt_v9.99.xsd), os stubs e faz a chamada ao web service NFeDistribuicaoDFe.
	 * 
	 * @param nSU - numero sequencial único
	 * 
	 * @return o xml retornado da consulta como string
	 * 
	 * @throws RemoteException
	 * @throws XMLStreamException
	 */
	public static String execute(String nSU) throws RemoteException, XMLStreamException {
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
		+ "<distDFeInt xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"1.00\">"
		+ "<tpAmb>" + TP_AMB + "</tpAmb>"
		+ "<cUFAutor>" + COD_UF_IBGE + "</cUFAutor>"
		+ "<CNPJ>" + CNPJ_DESTINATARIO + "</CNPJ>"
		+ "<distNSU>"
		+ "<ultNSU>" + nSU + "</ultNSU>"
		+ "</distNSU>"
		+ "</distDFeInt>";
		
		LOG.info("XML da Consulta: " + xml);
		
		//le o string e o transforma...
		OMElement ome = AXIOMUtil.stringToOM(xml);
		
		//popula os dados no objeto		
		
		NFeDistribuicaoDFeStub.NfeDadosMsg_type0 dadosMsg = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();
		dadosMsg.setExtraElement(ome);
		
		//popula o stub com os dados do xml
		NFeDistribuicaoDFeStub.NfeDistDFeInteresse distDFe = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();
		distDFe.setNfeDadosMsg(dadosMsg);
		
		//prepara o stub para a chamada...
		NFeDistribuicaoDFeStub stub = new NFeDistribuicaoDFeStub(URL_SERVICO);			
		
		//executando o metodo do web service para obter o response...
		NfeDistDFeInteresseResponse response = stub.nfeDistDFeInteresse(distDFe);
		
		String xmlResult = response.getNfeDistDFeInteresseResult().getExtraElement().toString();
		
		LOG.info("XML de retorno: " + xmlResult);
		
		return xmlResult;
	}

}