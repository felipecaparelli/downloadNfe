package br.com.wro.nfe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.wro.nfe.enums.TipoAmbiente;
import br.com.wro.nfe.mybatis.factory.MyBatisConnectionFactory;

import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Métodos e dados base para a execução de todos os outros serviços.
 * 
 * @author 
 *
 */
public class BaseService {
	
	public static final int TP_AMB = TipoAmbiente.PRODUCAO.getCodigo(); //Tipo Ambiente SeFaz
	public static final String CNPJ_DESTINATARIO = "10280028000250"; 	//CNPJ da sua empresa
	public static final String COD_UF_IBGE = "35"; 			  			//codigo UF de Sao Paulo
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	protected static File getFile(byte[] in) throws IOException {
    	
    	byte[] bFile = new byte[(int) in.length];
    	
    	//convert array of bytes into file
	    FileOutputStream fileOuputStream = null;
	    
		try {
			
			fileOuputStream = new FileOutputStream("xmlDoc");
			fileOuputStream.write(bFile);
			
			return new File("xmlDoc");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			fileOuputStream.close();
		}
    	
    	return null;
    	
    }
    
	protected static String readBytes(byte[] in) {
		
		String result = "";

		try {
			
			File f = getFile(in);
			
			@SuppressWarnings("resource")
			ZipInputStream zin = new ZipInputStream(new FileInputStream(f));
			ZipEntry ze = null; 
			
			while ((ze = zin.getNextEntry()) != null) { 
				
				byte[] bytes= new byte[(int) ze.getSize()];
	            zin.read(bytes, 0, bytes.length);
	            
	            result= new String( bytes, "UTF-8" );
	            
		        zin.closeEntry(); 
		        
		        return result;
			} 

			System.out.println("FIM!");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Transforma o Nó do XML em string.
	 * 
	 * @param doc - documento (nó) XML a ser trabalhado
	 * 
	 * @return String
	 * 
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public static String nodeToString(Node doc) throws TransformerFactoryConfigurationError, TransformerException {
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		
		transformer.transform(new DOMSource(doc), result);
		
		return result.getWriter().toString();
	}
	

	
	
	/* metodos utilitarios */
	
	/**
	 * Transforma o string do XML em um objeto específico.
	 * @param classe - classe do objeto
	 * @param xmlToUnmarshall
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unmarshallXml(Class<T> classe, String xmlToUnmarshall) throws JAXBException {  
	    JAXBContext context = JAXBContext.newInstance(classe);  
	    Unmarshaller unmarshaller = context.createUnmarshaller();  
	    return unmarshaller.unmarshal(new StreamSource(new StringReader(xmlToUnmarshall)), classe).getValue();  
	} 
	
	/**
	 * Transforma um elemento XML em um objeto específico.
	 * @param classe
	 * @param el
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T unmarshallXml(Class<T> classe, Element el) throws JAXBException {

		try {			
			
			JAXBContext jaxbContext = JAXBContext.newInstance(classe);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			
			return jaxbUnmarshaller.unmarshal(el, classe).getValue();
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
