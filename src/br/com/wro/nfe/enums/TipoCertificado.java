package br.com.wro.nfe.enums;

import br.com.wro.nfe.utils.SecurityUtils;

/**
 * <code>
 * <h1>TipoCertificado</h1>
 * 
 * Pacote: 		br.com.wro.nfe.enums<br>
 * Descrição: 	Tipos de certificados que podem ser utilizados para a conexao com os servidores.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	08/05/2015<br>
 * </code>
 */
public enum TipoCertificado {

	A1_PKCS12(SecurityUtils.getCertPath(), "PKCS12", "JKS", "pkcs12"), 
	A3_PKCS11("NONE", "PKCS11", "JKS", "pkcs11");

	private String keyStore;
	private String keyStoreType;
	private String trustStoreType;
	private String instance;

	TipoCertificado(String keyStore, String keyStoreType, String trustStoreType, String instance) {
		this.keyStore = keyStore;
		this.keyStoreType = keyStoreType;
		this.trustStoreType = trustStoreType;
		this.instance = instance;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public String getKeyStoreType() {
		return keyStoreType;
	}

	public String getTrustStoreType() {
		return trustStoreType;
	}

	public String getInstance() {
		return instance;
	}

}
