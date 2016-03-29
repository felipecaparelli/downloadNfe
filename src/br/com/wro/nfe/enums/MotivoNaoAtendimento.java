package br.com.wro.nfe.enums;

/**
 * Motivos do não atendimento das solicitações do web service NFeDistribuicaoDFe.
 * Vide 3. Tabela de códigos de erros e descrições de mensagens de erros (página 13 - Nota Técnica 2014/002)
 *
 */
public enum MotivoNaoAtendimento {
	
	MOT_214(214, "Tamanho da mensagem excedeu o limite estabelecido"),
	MOT_215(215, "Falha no schema XML"),
	MOT_238(238, "Cabeçalho - Versão do arquivo XML superior a Versão vigente"),
	MOT_239(239, "Cabeçalho - Versão do arquivo XML não suportada"),
	MOT_242(242, "Cabeçalho - Falha no Schema XML"),
	MOT_252(252, "Ambiente informado diverge do Ambiente de recebimento"),
	MOT_280(280, "Certificado Transmissor inválido"),
	MOT_281(281, "Certificado Transmissor Data Validade"),
	MOT_283(283, "Certificado Transmissor - erro Cadeia de Certificação"),
	MOT_284(284, "Certificado Transmissor revogado"),
	MOT_285(285, "Certificado Transmissor difere ICP-Brasil"),
	MOT_286(286, "Certificado Transmissor erro no acesso a LCR"),
	MOT_402(402, "XML da área de dados com codificação diferente de UTF-8"),
	MOT_404(404, "Uso de prefixo de namespace não permitido"),
	MOT_409(409, "Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header"),
	MOT_410(410, "UF informada no campo cUF não é atendida pelo Web Service"),
	MOT_411(411, "Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header"),
	MOT_472(472, "CPF consultado difere do CPF do Certificado Digital"),
	MOT_473(473, "Certificado Transmissor sem CNPJ ou CPF"),
	MOT_489(489, "CNPJ informado inválido (DV ou zeros)"),
	MOT_490(490, "CPF informado inválido (DV ou zeros)"),
	MOT_589(589, "Número do NSU informado superior ao maior NSU da base de dados do Ambiente Nacional"),
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
