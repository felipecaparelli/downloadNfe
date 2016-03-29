package br.com.wro.nfe.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * <code>
 * <h1>ConfigService</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Configurações das chaves de acesso.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	24/03/2015<br>
 * </code>
 */
public class ConfigUtils {  
	
	/**
	 * <code>
	 * Descrição: 	Carrega a configuração default (utilizando a biblioteca correspondente de cada tipo/dispositivo).<br>
	 * @since 		24/03/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		name<br>
	 * @param 		library<br>
	 * @return 		InputStream<br>
	 * @throws 		UnsupportedEncodingException
	 * </code>
	 */
    private static InputStream defaultConfig(String name, String library) throws UnsupportedEncodingException {  
        StringBuilder conf = new StringBuilder()  
				        	.append("name = ").append(name)  
				            .append("\n\r")  
				            .append("library = ").append(library)  
				            .append("\n\r")  
				            .append("showInfo = true");  
        return new ByteArrayInputStream(conf.toString().getBytes("UTF-8"));   
    }  
  
    /** 
     * <code>
     * Descrição: 	Compatível com: Cartão Certisign - Leitor GemPC Twin; Cartão Serasa - Leitor Perto.<br>
     * @since 		24/03/2015<br>
     * @author 		Felipe Caparelli<br>
     * @return
     * @throws UnsupportedEncodingException 		InputStream<br>
     * </code>
     */
    public static InputStream smartCard() throws UnsupportedEncodingException {  
        return defaultConfig("SmartCard", "C:/Windows/System32/aetpkss1.dll");  
    }  
  
    /**  
     * <code>
     * Descrição: 	Compatível com: Cartão SafeWeb - Serasa Experian Leitor SCR 3310.<br>
     * @since 		24/03/2015<br>
     * @author 		Felipe Caparelli<br>
     * 
     * @return 		InputStream<br>
     * 
     * @throws UnsupportedEncodingException
     * </code>
     */
    public static InputStream safeWeb() throws UnsupportedEncodingException {  
        return defaultConfig("SafeWeb", "c:/windows/system32/cmp11.dll");  
    }  
  
    /**
     * <code>
     * Descrição: 	Compatível com: eToken Pro - Certisign Token Laranja e Azul.<br>
     * @since 		24/03/2015<br>
     * @author 		Felipe Caparelli<br>
     * 
     * @return 		InputStream<br>
     * 
     * @throws UnsupportedEncodingException
     * </code>
     */
    public static InputStream tokenAladdin() throws UnsupportedEncodingException {  
        return defaultConfig("eToken", "c:/windows/system32/eTpkcs11.dll");  
    }  
  
}  

