package br.com.wro.nfe.enums;

import org.apache.ibatis.type.Alias;

@Alias("indicadorSN")
public enum IndicadorSN {
	
	//---              PROPRIEDADES                  ---//
	SIM(1, "Sim"), 
	NAO(2, "N\u00E3o");
	
	private int valor; 
	private String texto;
	
	/**
	 * <code>
	 * Autor: 		<br>
	 * Descrição: 	Construtor padrão da classe.<br>
	 * @since   	07/03/2007<br>
	 * @param valor
	 * </code>
	 */
	IndicadorSN(int valor, String texto) {
		this.valor = valor;
		this.texto = texto;
	}
	
	/**
	 * Retorna o valor do enum
	 * 1 = Verdadeiro
	 * 2 = Falso
	 * @return valor do enum
	 */
	public int getValor() {
		return this.valor;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public static IndicadorSN get(int valor) {
		for (IndicadorSN sn : IndicadorSN.values()) {
			if(valor == sn.valor) return sn;
		}
		return null;
	}
}