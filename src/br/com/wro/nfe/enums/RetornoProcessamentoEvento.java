package br.com.wro.nfe.enums;

/**
 * 
 *
 */
public enum RetornoProcessamentoEvento {
	
	//SUCESSO
	COD_108(108, "Servi�o Paralisado Momentaneamente (curto prazo)"),
	COD_109(109, "Servi�o Paralisado sem Previs�o"),
	COD_128(128, "Lote de Evento Processado"),
	COD_135(135, "Evento registrado e vinculado a NF-e"),
	COD_136(136, "Evento registrado, mas n�o vinculado a NF-e"),
	//ERRO
	COD_203(203, "Rejei��o: Emissor n�o habilitado para emiss�o da NF-e"),
	COD_213(213, "Rejei��o: CNPJ-Base do Emitente difere do CNPJ-Base do Certificado Digital"),
	COD_214(214, "Rejei��o: Tamanho da mensagem excedeu o limite estabelecido"),
	COD_215(215, "Rejei��o: Falha Schema XML"),
	COD_222(222, "Rejei��o: Protocolo de Autoriza��o de Uso difere do cadastrado"),
	COD_225(225, "Rejei��o: Falha no Schema XML do lote de NFe"),
	COD_236(236, "Rejei��o: Chave de Acesso com d�gito verificador inv�lido"),
	COD_238(238, "Rejei��o: Cabe�alho - Vers�o do arquivo XML superior a Vers�o vigente"),
	COD_239(239, "Rejei��o: Cabe�alho - Vers�o do arquivo XML n�o suportada"),
	COD_240(240, "Rejei��o: Cancelamento/Inutiliza��o - Irregularidade Fiscal do Emitente"),
	COD_242(242, "Rejei��o: Cabe�alho - Falha no Schema XML"),
	COD_249(249, "Rejei��o: UF da Chave de Acesso diverge da UF autorizadora"),
	COD_250(250, "Rejei��o: UF diverge da UF autorizadora"),
	COD_252(252, "Rejei��o: Ambiente informado diverge do Ambiente de recebimento"),
	COD_280(280, "Rejei��o: Certificado Transmissor inv�lido"),
	COD_281(281, "Rejei��o: Certificado Transmissor Data Validade"),
	COD_282(282, "Rejei��o: Certificado Transmissor sem CNPJ"),
	COD_283(283, "Rejei��o: Certificado Transmissor - erro Cadeia de Certifica��o"),
	COD_284(284, "Rejei��o: Certificado Transmissor revogado"),
	COD_285(285, "Rejei��o: Certificado Transmissor difere ICP-Brasil"),
	COD_286(286, "Rejei��o: Certificado Transmissor erro no acesso a LCR"),
	COD_290(290, "Rejei��o: Certificado Assinatura inv�lido"),
	COD_291(291, "Rejei��o: Certificado Assinatura Data Validade"),
	COD_292(292, "Rejei��o: Certificado Assinatura sem CNPJ"),
	COD_293(293, "Rejei��o: Certificado Assinatura - erro Cadeia de Certifica��o"),
	COD_294(294, "Rejei��o: Certificado Assinatura revogado"),
	COD_295(295, "Rejei��o: Certificado Assinatura difere ICP-Brasil"),
	COD_296(296, "Rejei��o: Certificado Assinatura erro no acesso a LCR"),
	COD_297(297, "Rejei��o: Assinatura difere do calculado"),
	COD_298(298, "Rejei��o: Assinatura difere do padr�o do Sistema"),
	COD_402(402, "Rejei��o: XML da �rea de dados com codifica��o diferente de UTF-8"),
	COD_404(404, "Rejei��o: Uso de prefixo de namespace n�o permitido"),
	COD_409(409, "Rejei��o: Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_410(410, "Rejei��o: UF informada no campo cUF n�o � atendida pelo Web Service"),
	COD_411(411, "Rejei��o: Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_489(489, "Rejei��o: CNPJ informado inv�lido (DV ou zeros)"),
	COD_490(490, "Rejei��o: CPF informado inv�lido (DV ou zeros)"),
	COD_491(491, "Rejei��o: O tpEvento informado inv�lido"),
	COD_492(492, "Rejei��o: O verEvento informado inv�lido"),
	COD_493(493, "Rejei��o: Evento n�o atende o Schema XML espec�fico"),
	COD_494(494, "Rejei��o: Chave de Acesso inexistente"),
	COD_516(516, "Rejei��o: Falha no schema XML � inexiste a tag raiz esperada para a mensagem"),
	COD_517(517, "Rejei��o: Falha no schema XML � inexiste atributo versao na tag raiz da mensagem"),
	COD_545(545, "Rejei��o: Falha no schema XML � vers�o informada na versaoDados do SOAPHeader diverge da vers�o da mensagem"),
	COD_572(572, "Rejei��o: Erro Atributo ID do evento n�o corresponde a concatena��o dos campos ('ID' + tpEvento + chNFe + nSeqEvento)"),
	COD_573(573, "Rejei��o: Duplicidade de Evento"),
	COD_574(574, "Rejei��o: O autor do evento diverge do emissor da NF-e"),
	COD_575(575, "Rejei��o: O autor do evento diverge do destinat�rio da NF-e"),
	COD_576(576, "Rejei��o: O autor do evento n�o � um �rg�o autorizado a gerar o evento"),
	COD_577(577, "Rejei��o: A data do evento n�o pode ser menor que a data de emiss�o da NF-e"),
	COD_578(578, "Rejei��o: A data do evento n�o pode ser maior que a data do processamento"),
	COD_579(579, "Rejei��o: A data do evento n�o pode ser menor que a data de autoriza��o para NF-e n�o emitida em conting�ncia"),
	COD_580(580, "Rejei��o: O evento exige uma NF-e autorizada"),
	COD_587(587, "Rejei��o: Usar somente o namespace padr�o da NF-e"),
	COD_588(588, "Rejei��o: N�o � permitida a presen�a de caracteres de edi��o no in�cio/fim da mensagem ou entre as tags da mensagem"),
	COD_594(594, "Rejei��o: O n�mero de sequ�ncia do evento informado � maior que o permitido"),
	COD_614(614, "Rejei��o: Chave de Acesso inv�lida (C�digo UF inv�lido)"),
	COD_615(615, "Rejei��o: Chave de Acesso inv�lida (Ano < 05 ou Ano maior que Ano corrente)"), 
	COD_616(616, "Rejei��o: Chave de Acesso inv�lida (M�s < 1 ou M�s > 12)"),
	COD_617(617, "Rejei��o: Chave de Acesso inv�lida (CNPJ zerado ou d�gito inv�lido)"),
	COD_618(618, "Rejei��o: Chave de Acesso inv�lida (modelo diferente de 55)"),
	COD_619(619, "Rejei��o: Chave de Acesso inv�lida (n�mero NF = 0)"),
	COD_636(636, "Rejei��o: O tipo do evento de cancelamento n�o corresponde ao tipo do evento a ser cancelado"),
	COD_637(637, "Rejei��o: O Pedido de Prorroga��o est� cancelado"),
	COD_638(638, "Rejei��o: O n�mero Pedidos de Prorroga��o tipo '1� pedido' excede o valor limite de 20 Pedidos de Prorroga��o autorizados ou sem resposta."),
	COD_639(639, "Rejei��o: O n�mero Pedidos de Prorroga��o tipo '2� pedido' excede o valor limite de 20 Pedidos de Prorroga��o autorizados ou sem resposta."),
	COD_640(640, "Rejei��o: O Pedido de Prorroga��o n�o � v�lido"),
	COD_641(641, "Rejei��o: A data do evento n�o pode ser menor que a data de autoriza��o para o evento");
	
	private int codigo;
	private String mensagem;
	
	RetornoProcessamentoEvento(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public static RetornoProcessamentoEvento get(int codigo) {
		for (RetornoProcessamentoEvento res : RetornoProcessamentoEvento.values()) {
			if(res.codigo == codigo) return res;
		}
		throw new IllegalArgumentException(String.format("N\u00e3o existe o c\u00f3digo %s no mapeamento.", codigo));
	}

}
