package br.com.wro.nfe.utils;

import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import distDFeInt_v100.DistDFeInt;

/**
 * <code>
 * <h1>ExportXML</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Trabalha com objetos que possuem anotações de XML (JAXB) 
 * 				para exportar os mesmos para as chamadas dos Web Services.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	13/05/2015<br>
 * </code>
 */
public class ExportXML {

	/**
	 * Obtem o marshaller para fazer a leitura dos objetos (JAXB).
	 * 
	 * @return Marshaller
	 * 
	 * @throws JAXBException
	 * @throws PropertyException
	 */
	private static Marshaller getDefaultMarshaller() throws JAXBException, PropertyException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DistDFeInt.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		return jaxbMarshaller;
	}
	
	/**
	 * Escreve o XML do objeto no OutputStream.
	 * 
	 * @param objXML - objeto a ser transformado
	 * @param out - outStream onde o XMl será escrito
	 * @throws JAXBException
	 */
	public static void xmlToStream(Object objXML, OutputStream out) throws JAXBException {
		getDefaultMarshaller().marshal(objXML, out);		
	}
	
	/**
	 * Retorna o XML do objeto como string.
	 * 
	 * @param objXML - objeto a ser transformado
	 * @return o XML transformado como String
	 * @throws JAXBException
	 */
	public static String xmlToString(Object objXML) throws JAXBException {
		
		StringWriter sw = new StringWriter();
 
		getDefaultMarshaller().marshal(objXML, sw);
		
		return sw.toString();
	}

}
