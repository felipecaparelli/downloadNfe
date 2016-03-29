package br.com.wro.nfe.enums;

/**
 * <code>
 * <h1>StatusNfe</h1>
 * 
 * Pacote: 		br.com.wro.nfe.enums<br>
 * Descrição: 	Status possíveis para a NFe.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
public enum StatusNfe {
	
	AUTORIZADO(100, "Autorizado o uso"),
	CANCELAMENTO(101, "Cancelamento de NF-e homologada"),
	RENEGADO(110, "Uso renegado");
	
	private int codigo;
	private String mensagem;
	
	StatusNfe(int cod, String msg) {
		this.codigo = cod;
		this.mensagem = msg;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	

}
