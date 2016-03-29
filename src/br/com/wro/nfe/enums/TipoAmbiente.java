package br.com.wro.nfe.enums;

/**
 * Tipos de ambiente aceitos para a chamada aos serviços da NFe.
 * 
 * @author 82004836
 *
 */
public enum TipoAmbiente {
	
	PRODUCAO(1), HOMOLOGACAO(2);
	
	private int codigo;
	
	TipoAmbiente(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}
}
