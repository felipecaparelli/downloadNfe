package br.com.wro.nfe.utils;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.security.KeyStore;  
import java.security.MessageDigest;  
import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
  

import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLException;  
import javax.net.ssl.SSLHandshakeException;  
import javax.net.ssl.SSLSocket;  
import javax.net.ssl.SSLSocketFactory;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.TrustManagerFactory;  
import javax.net.ssl.X509TrustManager;  
  
/**
 * <code>
 * <h1>NFeBuildAllCacerts</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Classe utilitária para gerar o arquivo cacerts para validar os
 * 				servidores que serão acessados via SSL.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	13/05/2015<br>
 * </code>
 */
public class NFeBuildAllCacerts {
	
    private static final String PASS_PHRASE = "changeit";
	private static final String JSSECACERTS = "NFeCacerts";  
	private static final String CACERTS_PATH = System.getProperty("user.dir") + "/certificados/nfe";  
	private static final char SEPARATOR = File.separatorChar;  
    private static final int TIMEOUT_WS = 30;
    
    /**
     * <code>
     * Descrição: 	[main].<br>
     * @since 		13/05/2015<br>
     * @author 		Felipe Caparelli<br>
     * @param 		args
     * </code>
     */
    public static void main(String[] args) { 
    	
        try {  
        	
            char[] passphrase = PASS_PHRASE.toCharArray();  
  
            File file = new File(CACERTS_PATH + SEPARATOR + JSSECACERTS); 
            
            if (file.isFile() == false) {  
                char SEP = File.separatorChar;  
                File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");  
                file = new File(dir, JSSECACERTS);  
                if (file.isFile() == false) {  
                    file = new File(dir, "cacerts");  
                }  
            }  
  
            info("| Carregando o KeyStore " + file + "...");  
            InputStream in = new FileInputStream(file);  
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());  
            ks.load(in, passphrase);  
            in.close();  
           
            /** 
             * AM - 2.00: homnfe.sefaz.am.gov.br 
             * BA - 2.00: hnfe.sefaz.ba.gov.br 
             * CE - 2.00: nfeh.sefaz.ce.gov.br 
             * GO - 2.00: homolog.sefaz.go.gov.br 
             * MG - 2.00: hnfe.fazenda.mg.gov.br 
             * MS - 2.00: homologacao.nfe.ms.gov.br 
             * MT - 2.00: homologacao.sefaz.mt.gov.br 
             * PE - 2.00: nfehomolog.sefaz.pe.gov.br 
             * PR - 2.00: homologacao.nfe2.fazenda.pr.gov.br 
             * RS - 2.00: homologacao.nfe.sefaz.rs.gov.br 
             * SP - 2.00: homologacao.nfe.fazenda.sp.gov.br 
             * SCAN - 2.00: hom.nfe.fazenda.gov.br 
             * SVAN - 2.00: hom.sefazvirtual.fazenda.gov.br 
             * SVRS - 2.00: homologacao.nfe.sefazvirtual.rs.gov.br 
             */  
          //HOMOLOGACAO  
            //AM - https://homnfe.sefaz.am.gov.br  
            get("homnfe.sefaz.am.gov.br", 443, ks);  
            //BA - https://hnfe.sefaz.ba.gov.br  
            get("hnfe.sefaz.ba.gov.br", 443, ks);  
            //CE - https://nfeh.sefaz.ce.gov.br  
            get("nfeh.sefaz.ce.gov.br", 443, ks);  
            //ES - https://app.sefaz.es.gov.br  
           /* get("app.sefaz.es.gov.br", 443, ks);  
            //GO - https://homolog.sefaz.go.gov.br  
            get("homolog.sefaz.go.gov.br", 443, ks);  
            //MG - https://hnfe.fazenda.mg.gov.br  
            get("hnfe.fazenda.mg.gov.br", 443, ks);  
            //MS - https://homologacao.nfe.ms.gov.br  
            get("homologacao.nfe.ms.gov.br", 443, ks);  
            //MT - https://homologacao.sefaz.mt.gov.br  
            get("homologacao.sefaz.mt.gov.br", 443, ks);  
            //PE - https://nfehomolog.sefaz.pe.gov.br  
            get("nfehomolog.sefaz.pe.gov.br", 443, ks);  
            //PR - https://homologacao.nfe2.fazenda.pr.gov.br  
            get("homologacao.nfe2.fazenda.pr.gov.br", 443, ks);  
            //RS - https://homologacao.nfe.sefaz.rs.gov.br  
            get("homologacao.nfe.sefaz.rs.gov.br", 443, ks);  
            //RS2 - https://sef.sefaz.rs.gov.br  
            get("sef.sefaz.rs.gov.br", 443, ks);  */
            //SP - https://homologacao.nfe.fazenda.sp.gov.br  
            get("homologacao.nfe.fazenda.sp.gov.br", 443, ks);  
            //SVAN - https://hom.sefazvirtual.fazenda.gov.br  
            get("hom.sefazvirtual.fazenda.gov.br", 443, ks);  
            //SVRS - https://homologacao.nfe.sefazvirtual.rs.gov.br  
            /*get("homologacao.nfe.sefazvirtual.rs.gov.br", 443, ks);  
            //SVRS NfeConsultaCadastro - https://webservice.set.rn.gov.br  
            get("webservice.set.rn.gov.br", 443, ks);  */
            //SCAN - https://hom.nfe.fazenda.gov.br  
            get("hom.nfe.fazenda.gov.br", 443, ks);  
            //SVC-AN - https://hom.svc.fazenda.gov.br  
            get("hom.svc.fazenda.gov.br", 443, ks);  
            //AN - https://hom.nfe.fazenda.gov.br  
            get("hom.nfe.fazenda.gov.br", 443, ks);  
  
            /*//PRODUCAO  
            //AM - https://nfe.sefaz.am.gov.br  
            get("nfe.sefaz.am.gov.br", 443, ks);  
            //BA - https://nfe.sefaz.ba.gov.br  
            get("nfe.sefaz.ba.gov.br", 443, ks);  
            //CE - https://nfe.sefaz.ce.gov.br  
            get("nfe.sefaz.ce.gov.br", 443, ks);  
            //ES - https://app.sefaz.es.gov.br  
            get("app.sefaz.es.gov.br", 443, ks);  
            //GO - https://nfe.sefaz.go.gov.br  
            get("nfe.sefaz.go.gov.br", 443, ks);  
            //MG - https://nfe.fazenda.mg.gov.br  
            get("nfe.fazenda.mg.gov.br", 443, ks);  
            //MS - https://nfe.fazenda.ms.gov.br  
            get("nfe.fazenda.ms.gov.br", 443, ks);  
            //MT - https://nfe.sefaz.mt.gov.br  
            get("nfe.sefaz.mt.gov.br", 443, ks);  
            //PE - https://nfe.sefaz.pe.gov.br  
            get("nfe.sefaz.pe.gov.br", 443, ks);  
            //PR - https://nfe2.fazenda.pr.gov.br  
            get("nfe2.fazenda.pr.gov.br", 443, ks);  
            //RS - https://nfe.sefaz.rs.gov.br  
            get("nfe.sefaz.rs.gov.br", 443, ks);  
            //RS NfeConsultaCadastro - https://sef.sefaz.rs.gov.br  
            get("sef.sefaz.rs.gov.br", 443, ks);  
            //SP - https://nfe.fazenda.sp.gov.br  
            get("nfe.fazenda.sp.gov.br", 443, ks);  
            //SVAN  - https://www.sefazvirtual.fazenda.gov.br  
            get("www.sefazvirtual.fazenda.gov.br", 443, ks);  
            //SVRS - https://nfe.sefazvirtual.rs.gov.br  
            get("nfe.sefazvirtual.rs.gov.br", 443, ks);  
            //SVRS - https://svp-ws.sefazvirtual.rs.gov.br  
            get("svp-ws.sefazvirtual.rs.gov.br", 443, ks);  
            //SCAN  - https://www.scan.fazenda.gov.br  
            get("www.scan.fazenda.gov.br", 443, ks);  
            //SVC-AN - https://www.svc.fazenda.gov.br  
            get("www.svc.fazenda.gov.br", 443, ks);  
            //AN - https://www.nfe.fazenda.gov.br  
            get("www.nfe.fazenda.gov.br", 443, ks);  */
  
            File cafile = new File(JSSECACERTS);  
            OutputStream out = new FileOutputStream(cafile);  
            ks.store(out, passphrase);  
            out.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /**
     * Método para acessar URL de acordo com a porta especificaca e o keystore passado utilizando SSL.
     * 
     * @param host
     * @param port
     * @param ks
     * @throws Exception
     */
    public static void get(String host, int port, KeyStore ks) throws Exception {  
        
    	SSLContext context = SSLContext.getInstance("TLS");  
        
    	TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
        
    	tmf.init(ks);  
        
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];  
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);  
        context.init(null, new TrustManager[] { tm }, null);  
        SSLSocketFactory factory = context.getSocketFactory();  
       
        info("| Abrindo conexao para " + host + ":" + port + "...");  
        
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);  
        socket.setSoTimeout(TIMEOUT_WS * 1000);  
        
        try {  
        	
            info("| Iniciando o SSL handshake...");  
            socket.startHandshake();  
            socket.close();  
            info("| Nenhum erro, certificado confiavel");  
            
        } catch (SSLHandshakeException e) {  
            /** 
             * PKIX path building failed:  
             * sun.security.provider.certpath.SunCertPathBuilderException:  
             * unable to find valid certification path to requested target 
             * Não tratado, pois sempre ocorre essa exceção quando o cacerts 
             * nao esta gerado. 
             */  
        } catch (SSLException e) {  
            error("| " + e.toString());  
        }  
          
        X509Certificate[] chain = tm.chain;  
        if (chain == null) {  
            info("| Nao foi possivel obter a cadeia de certificados");  
        }  
  
        info("| " + chain.length + " certificado(s) enviados pelo servidor:");  
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");  
        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        for (int i = 0; i < chain.length; i++) {  
            X509Certificate cert = chain[i];  
            sha1.update(cert.getEncoded());  
            md5.update(cert.getEncoded());  
              
            String alias = host + "-" + (i);  
            ks.setCertificateEntry(alias, cert);  
            info("| Certificado adicionado no keystore '" + JSSECACERTS + "' usando o alias '" + alias + "'");            
        }  
    }  
  
    /**
     * 
     * @author 82004836
     *
     */
    private static class SavingTrustManager implements X509TrustManager { 
    	
        private final X509TrustManager tm;  
        private X509Certificate[] chain;  
       
        SavingTrustManager(X509TrustManager tm) {  
            this.tm = tm;  
        }  
       
        public X509Certificate[] getAcceptedIssuers() { 
        	return new X509Certificate[0]; 
            //throw new UnsupportedOperationException();  
        }  
       
        public void checkClientTrusted(X509Certificate[] chain, String authType)  
            throws CertificateException {  
            throw new UnsupportedOperationException();  
        }  
       
        public void checkServerTrusted(X509Certificate[] chain, String authType)  
            throws CertificateException {  
            this.chain = chain;  
            tm.checkServerTrusted(chain, authType);  
        }  
    }
      
    /**
     * Imprime as informações do processo...
     * @param log
     */
    private static void info(String log) {
        System.out.println("INFO: " + log);  
    }  
  
    /**
     * Imprime os erros do processo...
     * @param log
     */
    private static void error(String log) {
        System.out.println("ERROR: " + log);  
    }  
  
}  
