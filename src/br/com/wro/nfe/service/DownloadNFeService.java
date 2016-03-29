package br.com.wro.nfe.service;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.inf.portalfiscal.www.nfe.wsdl.nfedownloadnf.NfeDownloadNFStub;

/**
 * <code>
 * <h1>DownloadNFeService</h1>
 * 
 * Pacote: 		br.com.wro.nfe.service<br>
 * Descrição: 	Classe responsável pela execução do serviço de download da NF-e.
 * 				(vide: Nota Técnica 2012/002 - fonte: http://www.nfe.fazenda.gov.br/portal/exibirArquivo.aspx?conteudo=bUBJ/PmtKQo=).<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	13/05/2015<br>
 * </code>
 */
public class DownloadNFeService extends BaseService {
	
	private static final String URL_SERVICO = "https://www.nfe.fazenda.gov.br/NfeDownloadNF/NfeDownloadNF.asmx";
	
	/**
	 * Monta o XML (schema downloadNFe_v9.99.xsd), os stubs e faz a chamada ao web service NfeDownloadNF.
	 * 
	 * @param cnpj
	 * @param chaveNFe
	 * 
	 * @return String
	 * 
	 * @throws RemoteException
	 * @throws XMLStreamException
	 */
	public static String execute(String cnpj, String chaveNFe) throws RemoteException, XMLStreamException {
		
		NfeDownloadNFStub.NfeDownloadNFResult result = new NfeDownloadNFStub.NfeDownloadNFResult();

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"
		+ "<downloadNFe xmlns=\"http://www.portalfiscal.inf.br/nfe\" versao=\"1.00\">"
		+ "<tpAmb>" + TP_AMB + "</tpAmb>"
		+ "<xServ>DOWNLOAD NFE</xServ>"
		+ "<CNPJ>" + cnpj+ "</CNPJ>"
		+ "<chNFe>" + chaveNFe+ "</chNFe>"
		+ "</downloadNFe>";
		
		OMElement ome = AXIOMUtil.stringToOM(xml);
		
		NfeDownloadNFStub.NfeDadosMsg dadosMsg = new NfeDownloadNFStub.NfeDadosMsg();
		dadosMsg.setExtraElement(ome);
		
		NfeDownloadNFStub.NfeCabecMsg nfeCabecMsg = new NfeDownloadNFStub.NfeCabecMsg();
		
		nfeCabecMsg.setCUF(COD_UF_IBGE);
		nfeCabecMsg.setVersaoDados("1.00");
		
		NfeDownloadNFStub.NfeCabecMsgE nfeCabecMsgE = new NfeDownloadNFStub.NfeCabecMsgE();
		nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);
		
		NfeDownloadNFStub stub = new NfeDownloadNFStub(URL_SERVICO);		
		result = stub.nfeDownloadNF(dadosMsg, nfeCabecMsgE);
		
		return result.getExtraElement().toString(); 
	}

}
