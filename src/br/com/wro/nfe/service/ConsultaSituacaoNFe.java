package br.com.wro.nfe.service;

import java.rmi.RemoteException;

import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axis2.AxisFault;

import br.inf.portalfiscal.www.nfe.wsdl.consulta.NfeConsulta2Stub;

public class ConsultaSituacaoNFe {
	
	private static final String URL_SERVICO = "https://nfe.fazenda.sp.gov.br/ws/nfeconsulta2.asmx";	
	//private static final String URL_SERVICO = "https://www.svc.fazenda.gov.br/NfeConsulta2/NfeConsulta2.asmx"; //AMBIENTE NACIONAL
	
	public static void main(String[] args) {
		
		NFeConnector.connect();
		
		consultar("41150485074623000161550020001497961172898123");
	}
	
	public static void consultar(String chaveNFe) {
		
		/** 
         * Xml de Consulta. 
         */  
        StringBuilder xml = new StringBuilder();  
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")  
            .append("<consSitNFe versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\">")  
            .append("<tpAmb>1</tpAmb>")  
            .append("<xServ>CONSULTAR</xServ>")  
            .append("<chNFe>")  
            .append(chaveNFe)  
            .append("</chNFe>")  
            .append("</consSitNFe>"); 

		try {
			
			OMElement ome = AXIOMUtil.stringToOM(xml.toString());  

	        NfeConsulta2Stub.NfeDadosMsg dadosMsg = new NfeConsulta2Stub.NfeDadosMsg();  
	        dadosMsg.setExtraElement(ome);  

	        NfeConsulta2Stub.NfeCabecMsg nfeCabecMsg = new NfeConsulta2Stub.NfeCabecMsg();  
	        /** 
	         * Código do Estado. 
	         */  
	        nfeCabecMsg.setCUF("35");  

	        /** 
	         * Versao do XML 
	         */  
	        nfeCabecMsg.setVersaoDados("3.10");  
	        NfeConsulta2Stub.NfeCabecMsgE nfeCabecMsgE = new NfeConsulta2Stub.NfeCabecMsgE();  
	        nfeCabecMsgE.setNfeCabecMsg(nfeCabecMsg); 
	        
			NfeConsulta2Stub stub = new NfeConsulta2Stub(URL_SERVICO);
			NfeConsulta2Stub.NfeConsultaNF2Result result = stub.nfeConsultaNF2(dadosMsg, nfeCabecMsgE); 
			
			System.out.println(result.getExtraElement().toString());  
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}  

	}

}
