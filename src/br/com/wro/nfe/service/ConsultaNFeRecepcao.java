package br.com.wro.nfe.service;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.inf.portalfiscal.www.nfe.wsdl.retrecepcao.NfeRetRecepcao2Stub;

public class ConsultaNFeRecepcao {

	private static final String URL_SERVICO = "https://nfe.fazenda.sp.gov.br/nfeweb/services/nferecepcao2.asmx";

	public void consultar(String chaveNFe) {

		try {

			StringBuilder xml = new StringBuilder();
			xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
					.append("<consReciNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">")
					.append("<tpAmb>1</tpAmb>")
					.append("<nRec>")
					.append(chaveNFe)
					.append("</nRec>")
					.append("</consReciNFe>");
			
			OMElement ome = AXIOMUtil.stringToOM(xml.toString());
			NfeRetRecepcao2Stub.NfeDadosMsg dadosMsg = new NfeRetRecepcao2Stub.NfeDadosMsg();
			dadosMsg.setExtraElement(ome);
			NfeRetRecepcao2Stub.NfeCabecMsg nfeCabecMsg = new NfeRetRecepcao2Stub.NfeCabecMsg();
			/**
			 * Código do Estado.
			 */
			nfeCabecMsg.setCUF("35");
			/**
			 * Versão do XML
			 */
			nfeCabecMsg.setVersaoDados("3.10");
			NfeRetRecepcao2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeRetRecepcao2Stub.NfeCabecMsgE();
			nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);
			NfeRetRecepcao2Stub stub = new NfeRetRecepcao2Stub(URL_SERVICO);
			NfeRetRecepcao2Stub.NfeRetRecepcao2Result result = stub
					.nfeRetRecepcao2(dadosMsg, nfeCabecMsgE);
			System.out.println(result.getExtraElement().toString());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
