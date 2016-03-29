package br.com.wro.nfe.enums;

/**
 * Tipos de eventos que podem ser utilizados na chamada do serviço de recepção de NFe.
 */
public enum TipoEvento {

	CONFIRMACAO(210200, "Confirmacao da Operacao"),
	CIENCIA(210210, "Ciencia da Operacao"),
	DESCONHECIMENTO(210220, "Desconhecimento da Operacao"),
	NAO_REALIZADA(210240, "Operacao nao Realizada");
	
	private int codigo;
	private String descricao;
	
	TipoEvento(int cod, String msg) {
		this.codigo = cod;
		this.descricao = msg;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
