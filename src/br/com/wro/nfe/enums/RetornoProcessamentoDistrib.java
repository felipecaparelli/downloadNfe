package br.com.wro.nfe.enums;

/**
 * Indica os resultados dos processamentos das solicita��es do web service NFeDistribuicaoDFe.
 * Vide 3. Tabela de c�digos de erros e descri��es de mensagens de erros (p�gina 13 - Nota T�cnica 2014/002)
 *
 */
public enum RetornoProcessamentoDistrib {
	
	//SUCESSO
	COD_108(108, "Servi�o Paralisado Momentaneamente (curto prazo)"),
	COD_109(109, "Servi�o Paralisado sem Previs�o"),
	COD_137(137, "Nenhum documento localizado"),
	COD_138(138, "Documento localizado"),
	//ERRO
	COD_214(214, "Rejei��o: Tamanho da mensagem excedeu o limite estabelecido"),
	COD_215(215, "Rejei��o: Falha no schema XML"),
	COD_238(238, "Rejei��o: Cabe�alho - Vers�o do arquivo XML superior a Vers�o vigente"),
	COD_239(239, "Rejei��o: Cabe�alho - Vers�o do arquivo XML n�o suportada"),
	COD_242(242, "Rejei��o: Cabe�alho - Falha no Schema XML"),
	COD_252(252, "Rejei��o: Ambiente informado diverge do Ambiente de recebimento"),
	COD_280(280, "Rejei��o: Certificado Transmissor inv�lido"),
	COD_281(281, "Rejei��o: Certificado Transmissor Data Validade"),
	COD_283(283, "Rejei��o: Certificado Transmissor - erro Cadeia de Certifica��o"),
	COD_284(284, "Rejei��o: Certificado Transmissor revogado"),
	COD_285(285, "Rejei��o: Certificado Transmissor difere ICP-Brasil"),
	COD_286(286, "Rejei��o: Certificado Transmissor erro no acesso a LCR"),
	COD_402(402, "Rejei��o: XML da �rea de dados com codifica��o diferente de UTF-8"),
	COD_404(404, "Rejei��o: Uso de prefixo de namespace n�o permitido"),
	COD_409(409, "Rejei��o: Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_410(410, "Rejei��o: UF informada no campo cUF n�o � atendida pelo Web Service"),
	COD_411(411, "Rejei��o: Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_472(472, "Rejei��o: CPF consultado difere do CPF do Certificado Digital"),
	COD_473(473, "Rejei��o: Certificado Transmissor sem CNPJ ou CPF"),
	COD_489(489, "Rejei��o: CNPJ informado inv�lido (DV ou zeros)"),
	COD_490(490, "Rejei��o: CPF informado inv�lido (DV ou zeros)"),
	COD_589(589, "Rejei��o: N�mero do NSU informado superior ao maior NSU da base de dados do Ambiente Nacional"),
	COD_593(593, "Rejei��o: CNPJ-Base consultado difere do CNPJ-Base do Certificado Digital"),
	COD_656(656, "Rejei��o: Consumo Indevido");
	
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
