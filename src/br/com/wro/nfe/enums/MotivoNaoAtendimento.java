package br.com.wro.nfe.enums;

/**
 * Motivos do n�o atendimento das solicita��es do web service NFeDistribuicaoDFe.
 * Vide 3. Tabela de c�digos de erros e descri��es de mensagens de erros (p�gina 13 - Nota T�cnica 2014/002)
 *
 */
public enum MotivoNaoAtendimento {
	
	MOT_214(214, "Tamanho da mensagem excedeu o limite estabelecido"),
	MOT_215(215, "Falha no schema XML"),
	MOT_238(238, "Cabe�alho - Vers�o do arquivo XML superior a Vers�o vigente"),
	MOT_239(239, "Cabe�alho - Vers�o do arquivo XML n�o suportada"),
	MOT_242(242, "Cabe�alho - Falha no Schema XML"),
	MOT_252(252, "Ambiente informado diverge do Ambiente de recebimento"),
	MOT_280(280, "Certificado Transmissor inv�lido"),
	MOT_281(281, "Certificado Transmissor Data Validade"),
	MOT_283(283, "Certificado Transmissor - erro Cadeia de Certifica��o"),
	MOT_284(284, "Certificado Transmissor revogado"),
	MOT_285(285, "Certificado Transmissor difere ICP-Brasil"),
	MOT_286(286, "Certificado Transmissor erro no acesso a LCR"),
	MOT_402(402, "XML da �rea de dados com codifica��o diferente de UTF-8"),
	MOT_404(404, "Uso de prefixo de namespace n�o permitido"),
	MOT_409(409, "Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header"),
	MOT_410(410, "UF informada no campo cUF n�o � atendida pelo Web Service"),
	MOT_411(411, "Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header"),
	MOT_472(472, "CPF consultado difere do CPF do Certificado Digital"),
	MOT_473(473, "Certificado Transmissor sem CNPJ ou CPF"),
	MOT_489(489, "CNPJ informado inv�lido (DV ou zeros)"),
	MOT_490(490, "CPF informado inv�lido (DV ou zeros)"),
	MOT_589(589, "N�mero do NSU informado superior ao maior NSU da base de dados do Ambiente Nacional"),
	MOT_593(593, "CNPJ-Base consultado difere do CNPJ-Base do Certificado Digital"),
	MOT_656(656, "Consumo Indevido");
	
	private int codigo;
	private String mensagem;
	
	MotivoNaoAtendimento(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	/**
	 * Retornar o motivo de acordo com o codigo recebido.
	 * @param codigo
	 * @return
	 */
	public static MotivoNaoAtendimento get(int codigo) {
		for (MotivoNaoAtendimento motivo : MotivoNaoAtendimento.values()) {
			if(codigo == motivo.codigo) return motivo;
		}
		throw new IllegalArgumentException(String.format("N\u00e3o existe o c\u00f3digo %s no mapeamento.", codigo));
	}
}
