package br.com.wro.nfe.service;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.inf.portalfiscal.www.nfe.wsdl.nfestatusservico2.NfeStatusServico2Stub;

public class ConsultaStatusServico {
	
	private static final String URL_SERVICO = "https://homologacao.nfe.fazenda.sp.gov.br/ws/nfestatusservico2.asmx";
	
	public static void main(String[] args) {
		
		try {
			
			NFeConnector.connect();
			
			String codigoDoEstado = "35";
			
			// Schema XML distDFeInt_v9.99.xsd
			StringBuilder xml = new StringBuilder()
					.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")  
		            .append("<consStatServ versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">")  
		            .append("<tpAmb>2</tpAmb>")  
		            .append("<cUF>")  
		            .append(codigoDoEstado)  
		            .append("</cUF>")  
		            .append("<xServ>STATUS</xServ>")  
		            .append("</consStatServ>");

			//le o string e o transforma...
			OMElement ome = AXIOMUtil.stringToOM(xml.toString());
			
			//popula os dados no objeto
			NfeStatusServico2Stub.NfeDadosMsg dadosMsg = new NfeStatusServico2Stub.NfeDadosMsg();  
            dadosMsg.setExtraElement(ome);  
  
            NfeStatusServico2Stub.NfeCabecMsg nfeCabecMsg = new NfeStatusServico2Stub.NfeCabecMsg();  
            /** 
             * Código do Estado. 
             */  
            nfeCabecMsg.setCUF(codigoDoEstado);  
  
            /** 
             * Versao do XML 
             */  
            nfeCabecMsg.setVersaoDados("3.10");  
            NfeStatusServico2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeStatusServico2Stub.NfeCabecMsgE();  
            nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg);  
  
            NfeStatusServico2Stub stub = new NfeStatusServico2Stub(URL_SERVICO);  
            NfeStatusServico2Stub.NfeStatusServicoNF2Result result = stub.nfeStatusServicoNF2(dadosMsg, nfeCabecMsgE); 
			
			//escrevendo o response na saida...
			System.out.println(result.getExtraElement().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
