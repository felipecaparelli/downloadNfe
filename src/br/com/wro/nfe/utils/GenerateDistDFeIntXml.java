package br.com.wro.nfe.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import distDFeInt_v100.DistDFeInt;

/**
 * <code>
 * <h1>GenerateDistDFeIntXml</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Classe utilitária para gerar o XML exemplo das chamadas do serviço de distribuição.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	13/05/2015<br>
 * </code>
 */
public class GenerateDistDFeIntXml {
	
	protected static void printXML(Object o) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(DistDFeInt.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		jaxbMarshaller.marshal(o, System.out);
	}

}
