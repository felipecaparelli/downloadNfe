package br.com.wro.nfe.service;

import java.io.File;
import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import br.com.wro.nfe.enums.TipoCertificado;
import br.com.wro.nfe.utils.SecurityConfig;
import br.com.wro.nfe.utils.SecurityUtils;

/**
 * <code>
 * <h1>NFeConnector</h1>
 * 
 * Pacote: 		br.com.wro.nfe.service<br>
 * Descrição: 	Classe que realiza a conexão utilizando o certificado SSL (A1 ou A3).<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	08/05/2015<br>
 * </code>
 */
public class NFeConnector {
	
	protected static final String PATH_CACERTS_NFE = System.getProperty("user.dir") + File.separator + "certificados" + File.separator + "NFeCacerts";
	private static List<String> authServers = new ArrayList<String>();
	
	/**
	 * <code>
	 * Descrição: 	Realiza o processo de conexão com os servidores (com autenticação).<br>
	 * @since 		08/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @return 		KeyStore<br>
	 * </code>
	 */
	public static KeyStore connect() {
		
		//XXX TROCAR TIPO DE CERTIFICADO AQUI (A1/A3)!
		TipoCertificado tpCertificado = TipoCertificado.A3_PKCS11;
		
		/** Informações do Certificado Digital. */
		SecurityConfig config = SecurityUtils.loadSecurityConfig(tpCertificado);
		
		File caCerts = new File(PATH_CACERTS_NFE);
		
		if (!caCerts.exists() || !caCerts.isFile()){			
			//gerar o arquivo cacerts para estes servidores...
			SecurityUtils.gerarCaCerts(PATH_CACERTS_NFE, loadAuthServers(authServers));
		}
		
		loadSSL(tpCertificado, config, PATH_CACERTS_NFE);
		
		return config.getKeyStore();
		
	}

	/**
	 * <code>
	 * Descrição: 	Adiciona os servidores que serão validados pelo certificado.<br>
	 * @since 		08/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * </code>
	 */
	private static List<String> loadAuthServers(List<String> servers) {
		//Especificando os servidores que deverão ser utilizados na geracao do cacert
		servers.add("homnfe.sefaz.am.gov.br"); 					//AM - https://homnfe.sefaz.am.gov.br
		servers.add("hnfe.sefaz.ba.gov.br"); 					//BA - https://hnfe.sefaz.ba.gov.br
		servers.add("nfeh.sefaz.ce.gov.br"); 					//CE - https://nfeh.sefaz.ce.gov.br
		servers.add("app.sefaz.es.gov.br"); 					//ES - https://app.sefaz.es.gov.br
		servers.add("homolog.sefaz.go.gov.br"); 				//GO - https://homolog.sefaz.go.gov.br 
		servers.add("hnfe.fazenda.mg.gov.br"); 					//MG - https://hnfe.fazenda.mg.gov.br
		servers.add("homologacao.nfe.ms.gov.br");				//MS - https://homologacao.nfe.ms.gov.br
		servers.add("homologacao.sefaz.mt.gov.br");				//MT - https://homologacao.sefaz.mt.gov.br
		servers.add("nfehomolog.sefaz.pe.gov.br");				//PE - https://nfehomolog.sefaz.pe.gov.br
		//authServers.add("homologacao.nfe2.fazenda.pr.gov.br");	//PR - https://homologacao.nfe2.fazenda.pr.gov.br
		servers.add("homologacao.nfe.sefaz.rs.gov.br"); 		//RS - https://homologacao.nfe.sefaz.rs.gov.br
		servers.add("sef.sefaz.rs.gov.br");						//RS2 - https://sef.sefaz.rs.gov.br
		servers.add("homologacao.nfe.fazenda.sp.gov.br");		//SP - https://homologacao.nfe.fazenda.sp.gov.br
		servers.add("hom.sefazvirtual.fazenda.gov.br");			//SVAN - https://hom.sefazvirtual.fazenda.gov.br
		servers.add("homologacao.nfe.sefazvirtual.rs.gov.br");	//SVRS - https://homologacao.nfe.sefazvirtual.rs.gov.br
		servers.add("webservice.set.rn.gov.br");				//SVRS NfeConsultaCadastro - https://webservice.set.rn.gov.br
		servers.add("hom.nfe.fazenda.gov.br");					//SCAN - https://hom.nfe.fazenda.gov.br
		servers.add("hom.svc.fazenda.gov.br");					//SVC-AN - https://hom.svc.fazenda.gov.br
		servers.add("hom.nfe.fazenda.gov.br");					//AN - https://hom.nfe.fazenda.gov.br
		//PRODUCAO
		servers.add("nfe.sefaz.am.gov.br");						//AM - https://nfe.sefaz.am.gov.br
		servers.add("nfe.sefaz.ba.gov.br");						//BA - https://nfe.sefaz.ba.gov.br
		servers.add("nfe.sefaz.ce.gov.br");						//CE - https://nfe.sefaz.ce.gov.br
		servers.add("app.sefaz.es.gov.br");						//ES - https://app.sefaz.es.gov.br
		servers.add("nfe.sefaz.go.gov.br");						//GO - https://nfe.sefaz.go.gov.br
		servers.add("nfe.fazenda.mg.gov.br");					//MG - https://nfe.fazenda.mg.gov.br
		servers.add("nfe.fazenda.ms.gov.br");					//MS - https://nfe.fazenda.ms.gov.br
		servers.add("nfe.sefaz.mt.gov.br");						//MT - https://nfe.sefaz.mt.gov.br
		servers.add("nfe.sefaz.pe.gov.br"); 					//PE - https://nfe.sefaz.pe.gov.br
		//authServers.add("nfe2.fazenda.pr.gov.br");				//PR - https://nfe2.fazenda.pr.gov.br
		servers.add("nfe.sefaz.rs.gov.br");						//RS - https://nfe.sefaz.rs.gov.br
		servers.add("sef.sefaz.rs.gov.br"); 					//RS NfeConsultaCadastro - https://sef.sefaz.rs.gov.br
		servers.add("nfe.fazenda.sp.gov.br");					//SP - https://nfe.fazenda.sp.gov.br
		servers.add("www.sefazvirtual.fazenda.gov.br");			//SVAN - https://www.sefazvirtual.fazenda.gov.br
		servers.add("nfe.sefazvirtual.rs.gov.br");				//SVRS - https://nfe.sefazvirtual.rs.gov.br
		servers.add("svp-ws.sefazvirtual.rs.gov.br");			//SVRS - https://svp-ws.sefazvirtual.rs.gov.br
		//authServers.add("www.scan.fazenda.gov.br");				//SCAN - https://www.scan.fazenda.gov.br
		servers.add("www.svc.fazenda.gov.br");					//SVC-AN - https://www.svc.fazenda.gov.br
		servers.add("www.nfe.fazenda.gov.br");					//AN - https://www.nfe.fazenda.gov.br
		
		return servers;
	}
	
	/**
	 * <code>
	 * Descrição: 	Prepara a autentificação para o acesso ao servidor via SSL.<br>
	 * @since 		08/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		tipoCertificado
	 * @param 		config
	 * @param 		caCertsFile
	 * </code>
	 */
	public static void loadSSL(TipoCertificado tipoCertificado, SecurityConfig config, String caCertsFile) {
		
		Provider provider = config.getProvider();
		//TODO Verificar se eh necessario checar a existencia do provider no mapa do security!
		Security.addProvider(provider);
		
		//inicializa o debug para a conexao SSL...
		System.setProperty("javax.net.debug", "SSL,handshake");
		System.setProperty("java.security.debug", "all");
		
		//limpa as configuracoes anteriores...
		System.clearProperty("javax.net.ssl.keyStore");  
        System.clearProperty("javax.net.ssl.keyStorePassword");  
        System.clearProperty("javax.net.ssl.trustStore");		
		System.setProperty("javax.net.ssl.keyStoreProvider", provider.getName());
		
		if(TipoCertificado.A1_PKCS12.equals(tipoCertificado)) {
			System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol"); 
            //informa a senha do Certificado Digital A1...
			System.setProperty("javax.net.ssl.keyStorePassword", SecurityUtils.getCertPassword()); 
		}
		
		//informa os dados do keystore...
		System.setProperty("javax.net.ssl.keyStore", tipoCertificado.getKeyStore());
		System.setProperty("javax.net.ssl.keyStoreType", tipoCertificado.getKeyStoreType());		
		System.setProperty("javax.net.ssl.trustStoreType", tipoCertificado.getTrustStoreType());		
		System.setProperty("javax.net.ssl.trustStore", caCertsFile);
	}

}
