package br.com.wro.nfe.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.ProviderException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import br.com.wro.nfe.enums.TipoCertificado;

/**
 * <code>
 * <h1>SecurityUtils</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Utilitario para configurar o certificado digital.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	08/05/2015<br>
 * </code>
 */
public class SecurityUtils {
	
	private static final Logger logger = Logger.getLogger(SecurityUtils.class);
	
	private static final String PASS_PHRASE = "changeit";
	private static final String JSSECACERTS = "NFeCacerts";  
	private static final String SEPARATOR = File.separator;
	
    private static final int TIMEOUT_WS = 30;
    
    private static final String certPassword = "0987";  
    private static final String certPath = "C:/cert/certificado.pfx";
    
	
	/**
	 * <code>
	 * Descrição: 	Retorna o objeto SecurityConfig com os dados de configuração do certificado.<br>
	 * @param 		tipoCertificado - certificados aceitos: A1 e A3
	 * @since 		08/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @return 		SecurityConfig<br>
	 * </code>
	 */
	@SuppressWarnings("static-access")
	public static SecurityConfig loadSecurityConfig(TipoCertificado tipoCertificado) {
        
        try {
        	
            Provider provider = null;  
            
            switch (tipoCertificado) {
				case A1_PKCS12:
					//carrega o provider do certificado A1
					provider = new com.sun.net.ssl.internal.ssl.Provider();
					break;
				case A3_PKCS11:
					//carrega o provider do certificado a partir do smart card (A3)
					provider = new sun.security.pkcs11.SunPKCS11(ConfigUtils.smartCard());
					break;
				default:
					break;
			}
            
            if(provider == null) throw new IllegalArgumentException("Erro ao carregar o Provider - tipo de certificado invalido.");
            
            Security.addProvider(provider);
            
            //carrega o KeyStore
            KeyStore ks = KeyStore.getInstance(tipoCertificado.getInstance(), provider);
			ks.load(null, certPassword.toCharArray()); 
            
            Provider pp = ks.getProvider(); 
            String alias = null;
            
    		try {
                
    			logger.debug("--------------------------------------------------------");  
    			logger.debug("Provider   : " + pp.getName());  
    			logger.debug("Prov.Vers. : " + pp.getVersion());  
    			logger.debug("KS Type    : " + ks.getType());  
    			logger.debug("KS DefType : " + ks.getDefaultType());  
    			
    			Enumeration <String> al = ks.aliases();  
    			
    			while (al.hasMoreElements()) {  
    				
    				alias = al.nextElement();
    				
    				logger.debug("--------------------------------------------------------");
    				
    				if (ks.containsAlias(alias)) {
    					
    					logger.debug("Alias exists : '" + alias + "'");  
    					
    					X509Certificate cert = (X509Certificate) ks.getCertificate(alias);
    					
    					logger.debug("Certificate  : '" + cert.toString() + "'");  
    					logger.debug("Version      : '" + cert.getVersion() + "'");  
    					logger.debug("SerialNumber : '" + cert.getSerialNumber() + "'");  
    					logger.debug("SigAlgName   : '" + cert.getSigAlgName() + "'");  
    					logger.debug("NotBefore    : '" + cert.getNotBefore().toString() + "'");  
    					logger.debug("NotAfter     : '" + cert.getNotAfter().toString() + "'");  
    					logger.debug("TBS          : '" + cert.getTBSCertificate().toString() + "'");
    					//logger.debug("Public Key   : '" + cert.getPublicKey().toString() + "'");  
    					
    				} else {
    					logger.debug("Alias doesn't exists : '" + alias + "'");  
    				}  
    			}  
                
            } catch (ProviderException e){
            	logger.error(e); 
            } catch (Exception ex) {
            	logger.error(ex);  
            }	
            
            return new SecurityConfig(pp, ks);
			
    	} catch (ProviderException e){
    		logger.error(e); 
        } catch (IOException e) {
        	logger.error(e);
		} catch (KeyStoreException e) {
			logger.error(e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		} catch (CertificateException e) {
			logger.error(e);
		}
        
        return null;        
	}

    /**
     * <code>
     * Descrição: 	Gera os arquivos de certificado digital cacerts.<br>
     * @since 		08/05/2015<br>
     * @author 		Felipe Caparelli<br>
     * @param 		caCertsFilename nome absoluto do arquivo (diretorios + nome do arquivo)<br>
     * @param 		servidoresAutorizacao servidores utilizados na geração dos certificados<br>
     * </code>
     */
	public static void gerarCaCerts(String caCertsFilename, List<String> servidoresAutorizacao) {

		try {  
        	
			//senha do arquivo (default: changeit)
            char[] passphrase = PASS_PHRASE.toCharArray();            
            //obtem o nome do arquivo (sem o diretorio)
            String caCertName = caCertsFilename == null ? JSSECACERTS : caCertsFilename.substring(caCertsFilename.lastIndexOf(SEPARATOR)+1);
            //carrega o arquivo caCerts - "existente"
            File nfeCacerts = new File(caCertsFilename);             
            //verifica se o arquivo nao existe
            if (!nfeCacerts.isFile()) {
                //obtem o diretorio security do Java
                File dir = new File(System.getProperty("java.home") + File.separatorChar + "lib" + File.separatorChar + "security");  
                //obtem o cacerts raiz do diretorio security do Java (JDK)
                nfeCacerts = new File(dir, caCertName);     
                //verifica se o arquivo default não existe
                if (!nfeCacerts.isFile()) {
                	//se nao existe, cria um.
                	Path target = Paths.get(caCertsFilename);
                	//cria...
					try {

						final Path dirs = target.getParent();
						if (dirs != null) {
							// null will be returned if the path has no parent
							Files.createDirectories(dirs);
						}
						
						Files.deleteIfExists(target);
						target = Files.createFile(target);
						
						nfeCacerts = target.toFile();
						
					} catch (IOException ex) {
						System.out.println("Erro ao criar o arquivo destino: " + ex.getMessage());
					}
                	
					// Writing to file4
					try (BufferedWriter writer = Files.newBufferedWriter(target, Charset.defaultCharset())) {
						//arquivo origem
						File source = new File(dir, "cacerts");
						//copia os dados do arquivo origem para o destino
						FileUtils.copyFile(source, target.toFile());
						
					} catch (IOException exception) {
						System.out.println(exception.getMessage());
					}
                    
                }
            }
            
            logger.debug("| Carregando o KeyStore " + nfeCacerts.getAbsolutePath() + "...");  
            
            InputStream in = new FileInputStream(nfeCacerts);  
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());  
            ks.load(in, passphrase);  
            in.close();  
           
            for (String server : servidoresAutorizacao) {
            	//acessar o servidor
            	get(server, 443, ks, caCertsFilename);
			}
            
            //obtem o arquivo cacerts gerado...
            OutputStream out = new FileOutputStream(new File(caCertsFilename));  
            //autentica o arquivo
            ks.store(out, passphrase); 
            
            out.close();  
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * Realiza uma chamada GET (segura/autenticada) no servidor informado.
	 * 
	 * @param host - nome do servidor
	 * @param port - porta que sera utilizada (recomendado: 443)
	 * @param ks - KeyStore previamente carregado
	 * @param caCertFilename - nome absoluto do arquivo (diretorio + arquivo)
	 * 
	 * @throws Exception
	 */
	public static void get(String host, int port, KeyStore ks, String caCertFilename) throws Exception {
		
        SSLContext context = SSLContext.getInstance("TLS");  
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());  
        
        tmf.init(ks);  
        
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];  
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);  
        
        context.init(null, new TrustManager[] { tm }, null);  
        
        SSLSocketFactory factory = context.getSocketFactory();  
       
        logger.debug("| Abrindo conexao para " + host + ":" + port + "..."); 
        
        //inicia a comunicação entre os servidores via socket
        SSLSocket socket = (SSLSocket) factory.createSocket(host, port);  
        socket.setSoTimeout(TIMEOUT_WS * 1000);  
        
        try {
        	
            logger.debug("| Iniciando o SSL handshake...");  
            
            //garante a conversa...
            socket.startHandshake();  
            //fecha a comunicação... 
            socket.close(); 
            
            logger.debug("| Nenhum erro, certificado confiavel");  
            
        } catch (SSLHandshakeException e) {  
            /** 
             * PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException: 
             * 			Unable to find valid certification path to requested target.
             * ATENCAO: Não tratado, pois sempre ocorre essa exceção quando o cacerts nao esta gerado. 
             */  
        } catch (SSLException e) {  
            logger.error("| " + e.toString());  
        }  
          
        X509Certificate[] chain = tm.chain;
        
        if (chain == null) {  
            logger.debug("| Nao foi possivel obter a cadeia de certificados");  
        }  
  
        logger.debug("| " + chain.length + " certificado(s) enviados pelo servidor:");  
        
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");  
        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        
        for (int i = 0; i < chain.length; i++) {
        	
        	//le o certificado...
            X509Certificate cert = chain[i];
            //atualiza o certificado
            sha1.update(cert.getEncoded());  
            md5.update(cert.getEncoded());  
            //obtem o nome do servidor
            String alias = host + "-" + (i);  
            //adiciona o servidor no certificado
            ks.setCertificateEntry(alias, cert);
            
            logger.debug("| Certificado adicionado no keystore '" + caCertFilename + "' usando o alias '" + alias + "'");            
        }
    }
	
	/**
	 * <code>
	 * <h1>SavingTrustManager</h1>
	 * 
	 * Pacote: 		br.com.wro.nfe.utils<br>
	 * Descrição: 	Dados do padrão de certificação X509.<br>
	 * Projeto: 	consultaNFeDist<br>
	 * @author  	Felipe Caparelli<br>
	 * @version 	1.0<br>
	 * @since   	08/05/2015<br>
	 * </code>
	 */
    private static class SavingTrustManager implements X509TrustManager {
    	
        private final X509TrustManager tm;  
        private X509Certificate[] chain;  
       
        SavingTrustManager(X509TrustManager tm) {  
            this.tm = tm;  
        }  
       
        public X509Certificate[] getAcceptedIssuers() { 
        	return new X509Certificate[0]; //throw new UnsupportedOperationException();  atualizacao do JDK7
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
    
    
    /* Acesso aos dados do certificado A1 (password e caminho) */
    
    public static String getCertPassword() {
    	return certPassword;
    }
    
    public static String getCertPath() {
    	return certPath;
    }

}
