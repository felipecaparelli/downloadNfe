package br.com.wro.nfe.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import br.com.wro.nfe.service.NFeConnector;

public class LerWSDLSeFaz {
	
	/**
	 * <code>
	 * Descrição: 	Método util para ler o WSDL se seu browser naum colabora!.<br>
	 * @since 		09/06/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param args 		void<br>
	 * </code>
	 */
	public static void main(String[] args) {
		
		try {
			
			URL url = new URL("https://www.nfe.fazenda.gov.br/RecepcaoEvento/RecepcaoEvento.asmx?WSDL");
			
			NFeConnector.connect();
			
			URLConnection yc = url.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null)
	            System.out.println(inputLine);
	        in.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
