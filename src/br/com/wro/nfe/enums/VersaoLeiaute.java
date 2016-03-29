package br.com.wro.nfe.enums;

/**
 * Tipo de versão dos documentos XML para uso nos Web Services da SeFaz.
 */
public enum VersaoLeiaute {
	V_2("2.00"),
	V_3_1("2.00");
	
	private String codVersao;
	
	VersaoLeiaute(String codVersao) {
		this.codVersao = codVersao;
	}

	public String getCodVersao() {
		return codVersao;
	}

}
