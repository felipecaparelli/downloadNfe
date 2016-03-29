package br.com.wro.nfe.enums;

/**
 * Indica os resultados dos processamentos das solicitações do web service NFeDistribuicaoDFe.
 * Vide 3. Tabela de códigos de erros e descrições de mensagens de erros (página 13 - Nota Técnica 2014/002)
 *
 */
public enum RetornoProcessamentoDistrib {
	
	//SUCESSO
	COD_108(108, "Serviço Paralisado Momentaneamente (curto prazo)"),
	COD_109(109, "Serviço Paralisado sem Previsão"),
	COD_137(137, "Nenhum documento localizado"),
	COD_138(138, "Documento localizado"),
	//ERRO
	COD_214(214, "Rejeição: Tamanho da mensagem excedeu o limite estabelecido"),
	COD_215(215, "Rejeição: Falha no schema XML"),
	COD_238(238, "Rejeição: Cabeçalho - Versão do arquivo XML superior a Versão vigente"),
	COD_239(239, "Rejeição: Cabeçalho - Versão do arquivo XML não suportada"),
	COD_242(242, "Rejeição: Cabeçalho - Falha no Schema XML"),
	COD_252(252, "Rejeição: Ambiente informado diverge do Ambiente de recebimento"),
	COD_280(280, "Rejeição: Certificado Transmissor inválido"),
	COD_281(281, "Rejeição: Certificado Transmissor Data Validade"),
	COD_283(283, "Rejeição: Certificado Transmissor - erro Cadeia de Certificação"),
	COD_284(284, "Rejeição: Certificado Transmissor revogado"),
	COD_285(285, "Rejeição: Certificado Transmissor difere ICP-Brasil"),
	COD_286(286, "Rejeição: Certificado Transmissor erro no acesso a LCR"),
	COD_402(402, "Rejeição: XML da área de dados com codificação diferente de UTF-8"),
	COD_404(404, "Rejeição: Uso de prefixo de namespace não permitido"),
	COD_409(409, "Rejeição: Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_410(410, "Rejeição: UF informada no campo cUF não é atendida pelo Web Service"),
	COD_411(411, "Rejeição: Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_472(472, "Rejeição: CPF consultado difere do CPF do Certificado Digital"),
	COD_473(473, "Rejeição: Certificado Transmissor sem CNPJ ou CPF"),
	COD_489(489, "Rejeição: CNPJ informado inválido (DV ou zeros)"),
	COD_490(490, "Rejeição: CPF informado inválido (DV ou zeros)"),
	COD_589(589, "Rejeição: Número do NSU informado superior ao maior NSU da base de dados do Ambiente Nacional"),
	COD_593(593, "Rejeição: CNPJ-Base consultado difere do CNPJ-Base do Certificado Digital"),
	COD_656(656, "Rejeição: Consumo Indevido");
	
	private int codigo;
	private String mensagem;
	
	RetornoProcessamentoDistrib(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public static RetornoProcessamentoDistrib get(int codigo) {
		for (RetornoProcessamentoDistrib res : RetornoProcessamentoDistrib.values()) {
			if(res.codigo == codigo) return res;
		}
		throw new IllegalArgumentException(String.format("N\u00e3o existe o c\u00f3digo %s no mapeamento.", codigo));
	}

}
