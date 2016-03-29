package br.com.wro.certificado.utils;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

public class CertificadoUtils {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	public static void main(String[] args) {
		printDados();
	}
	
	/**
	 * Retorna os dados do certificado digital utilizado no servidor Windows.
	 */
	public static void printDados() {
		
	      
		try {  
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            keyStore.load(null, null);  
              
            Enumeration <String> al = keyStore.aliases();  
            while (al.hasMoreElements()) {  
                String alias = al.nextElement();  
                info("--------------------------------------------------------");  
                if (keyStore.containsAlias(alias)) {  
                    info("Emitido para........: " + alias);  
  
                    X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);  
                    info("SubjectDN...........: " + cert.getSubjectDN().toString());  
                    info("Version.............: " + cert.getVersion());  
                    info("SerialNumber........: " + cert.getSerialNumber());  
                    info("SigAlgName..........: " + cert.getSigAlgName());  
                    info("Válido a partir de..: " + dateFormat.format(cert.getNotBefore()));  
                    info("Válido até..........: " + dateFormat.format(cert.getNotAfter()));    
                } else {  
                    info("Nao existe o Alias : " + alias);  
                }  
            }  
        } catch (Exception e) {  
            error(e.toString());  
        }  
	}
	
	/** 
     * Info. 
     * @param log 
     */  
    private static void info(String log) {  
        System.out.println("INFO: " + log);  
    }  
  
    /** 
     * Error. 
     * @param log 
     */  
    private static void error(String log) {  
        System.out.println("ERROR: " + log);  
    }  

}
