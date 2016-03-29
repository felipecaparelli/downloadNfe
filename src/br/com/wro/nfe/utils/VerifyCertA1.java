package br.com.wro.nfe.utils;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import br.com.wro.nfe.enums.TipoCertificado;

/**
 * <code>
 * <h1>VerifyCertA1</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Checa a validade do certificado digital A1.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	08/05/2015<br>
 * </code>
 */
public class VerifyCertA1 {
	
	/**
     * <code>
     * Descrição: 	[main]<br>
     * @since 		08/05/2015<br>
     * @author 		Felipe Caparelli<br>
     * </code>
     */
    public static void main() {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	
    	 try {
             
             KeyStore keystore = KeyStore.getInstance(TipoCertificado.A1_PKCS12.getKeyStoreType());    
             keystore.load(new FileInputStream(SecurityUtils.getCertPath()), SecurityUtils.getCertPassword().toCharArray());    
             
             Enumeration<String> eAliases = keystore.aliases();    
                 
             while (eAliases.hasMoreElements()) {    
                 String alias = (String) eAliases.nextElement();    
                 Certificate certificado = (Certificate) keystore.getCertificate(alias);    
             
                 info("Aliais: " + alias);  
                 X509Certificate cert = (X509Certificate) certificado;    
                   
                 info(cert.getSubjectDN().getName());  
                 info("Válido a partir de..: " + sdf.format(cert.getNotBefore()));  
                 info("Válido até..........: " + sdf.format(cert.getNotAfter()));    
             }
             
         } catch (Exception e) {
             error(e.getMessage());  
         }  
    }
    
    private static void info(String text) {
    	System.out.println(text);
    }
    
    private static void error(String text) {
    	System.out.println("[ERROR]: " + text);
    }

}
