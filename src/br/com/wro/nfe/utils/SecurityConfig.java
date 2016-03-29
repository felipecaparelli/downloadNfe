package br.com.wro.nfe.utils;

import java.security.KeyStore;
import java.security.Provider;

/**
 * Objeto que deve guardar as informacoes para obter o certificado digital.
 * @author 82004836
 *
 */
public class SecurityConfig {

	private Provider provider;
	private KeyStore keyStore;
	private String filepathCACerts;
	
	public SecurityConfig(Provider provider, KeyStore keyStore) {
		this.provider = provider;
		this.keyStore = keyStore;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public KeyStore getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(KeyStore keyStore) {
		this.keyStore = keyStore;
	}

	public String getFilepathCACerts() {
		return filepathCACerts;
	}

	public void setFilepathCACerts(String filepathCACerts) {
		this.filepathCACerts = filepathCACerts;
	}

}
