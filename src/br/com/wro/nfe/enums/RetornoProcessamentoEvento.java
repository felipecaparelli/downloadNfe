package br.com.wro.nfe.enums;

/**
 * 
 *
 */
public enum RetornoProcessamentoEvento {
	
	//SUCESSO
	COD_108(108, "Serviço Paralisado Momentaneamente (curto prazo)"),
	COD_109(109, "Serviço Paralisado sem Previsão"),
	COD_128(128, "Lote de Evento Processado"),
	COD_135(135, "Evento registrado e vinculado a NF-e"),
	COD_136(136, "Evento registrado, mas não vinculado a NF-e"),
	//ERRO
	COD_203(203, "Rejeição: Emissor não habilitado para emissão da NF-e"),
	COD_213(213, "Rejeição: CNPJ-Base do Emitente difere do CNPJ-Base do Certificado Digital"),
	COD_214(214, "Rejeição: Tamanho da mensagem excedeu o limite estabelecido"),
	COD_215(215, "Rejeição: Falha Schema XML"),
	COD_222(222, "Rejeição: Protocolo de Autorização de Uso difere do cadastrado"),
	COD_225(225, "Rejeição: Falha no Schema XML do lote de NFe"),
	COD_236(236, "Rejeição: Chave de Acesso com dígito verificador inválido"),
	COD_238(238, "Rejeição: Cabeçalho - Versão do arquivo XML superior a Versão vigente"),
	COD_239(239, "Rejeição: Cabeçalho - Versão do arquivo XML não suportada"),
	COD_240(240, "Rejeição: Cancelamento/Inutilização - Irregularidade Fiscal do Emitente"),
	COD_242(242, "Rejeição: Cabeçalho - Falha no Schema XML"),
	COD_249(249, "Rejeição: UF da Chave de Acesso diverge da UF autorizadora"),
	COD_250(250, "Rejeição: UF diverge da UF autorizadora"),
	COD_252(252, "Rejeição: Ambiente informado diverge do Ambiente de recebimento"),
	COD_280(280, "Rejeição: Certificado Transmissor inválido"),
	COD_281(281, "Rejeição: Certificado Transmissor Data Validade"),
	COD_282(282, "Rejeição: Certificado Transmissor sem CNPJ"),
	COD_283(283, "Rejeição: Certificado Transmissor - erro Cadeia de Certificação"),
	COD_284(284, "Rejeição: Certificado Transmissor revogado"),
	COD_285(285, "Rejeição: Certificado Transmissor difere ICP-Brasil"),
	COD_286(286, "Rejeição: Certificado Transmissor erro no acesso a LCR"),
	COD_290(290, "Rejeição: Certificado Assinatura inválido"),
	COD_291(291, "Rejeição: Certificado Assinatura Data Validade"),
	COD_292(292, "Rejeição: Certificado Assinatura sem CNPJ"),
	COD_293(293, "Rejeição: Certificado Assinatura - erro Cadeia de Certificação"),
	COD_294(294, "Rejeição: Certificado Assinatura revogado"),
	COD_295(295, "Rejeição: Certificado Assinatura difere ICP-Brasil"),
	COD_296(296, "Rejeição: Certificado Assinatura erro no acesso a LCR"),
	COD_297(297, "Rejeição: Assinatura difere do calculado"),
	COD_298(298, "Rejeição: Assinatura difere do padrão do Sistema"),
	COD_402(402, "Rejeição: XML da área de dados com codificação diferente de UTF-8"),
	COD_404(404, "Rejeição: Uso de prefixo de namespace não permitido"),
	COD_409(409, "Rejeição: Campo cUF inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_410(410, "Rejeição: UF informada no campo cUF não é atendida pelo Web Service"),
	COD_411(411, "Rejeição: Campo versaoDados inexistente no elemento nfeCabecMsg do SOAP Header"),
	COD_489(489, "Rejeição: CNPJ informado inválido (DV ou zeros)"),
	COD_490(490, "Rejeição: CPF informado inválido (DV ou zeros)"),
	COD_491(491, "Rejeição: O tpEvento informado inválido"),
	COD_492(492, "Rejeição: O verEvento informado inválido"),
	COD_493(493, "Rejeição: Evento não atende o Schema XML específico"),
	COD_494(494, "Rejeição: Chave de Acesso inexistente"),
	COD_516(516, "Rejeição: Falha no schema XML – inexiste a tag raiz esperada para a mensagem"),
	COD_517(517, "Rejeição: Falha no schema XML – inexiste atributo versao na tag raiz da mensagem"),
	COD_545(545, "Rejeição: Falha no schema XML – versão informada na versaoDados do SOAPHeader diverge da versão da mensagem"),
	COD_572(572, "Rejeição: Erro Atributo ID do evento não corresponde a concatenação dos campos ('ID' + tpEvento + chNFe + nSeqEvento)"),
	COD_573(573, "Rejeição: Duplicidade de Evento"),
	COD_574(574, "Rejeição: O autor do evento diverge do emissor da NF-e"),
	COD_575(575, "Rejeição: O autor do evento diverge do destinatário da NF-e"),
	COD_576(576, "Rejeição: O autor do evento não é um órgão autorizado a gerar o evento"),
	COD_577(577, "Rejeição: A data do evento não pode ser menor que a data de emissão da NF-e"),
	COD_578(578, "Rejeição: A data do evento não pode ser maior que a data do processamento"),
	COD_579(579, "Rejeição: A data do evento não pode ser menor que a data de autorização para NF-e não emitida em contingência"),
	COD_580(580, "Rejeição: O evento exige uma NF-e autorizada"),
	COD_587(587, "Rejeição: Usar somente o namespace padrão da NF-e"),
	COD_588(588, "Rejeição: Não é permitida a presença de caracteres de edição no início/fim da mensagem ou entre as tags da mensagem"),
	COD_594(594, "Rejeição: O número de sequência do evento informado é maior que o permitido"),
	COD_614(614, "Rejeição: Chave de Acesso inválida (Código UF inválido)"),
	COD_615(615, "Rejeição: Chave de Acesso inválida (Ano < 05 ou Ano maior que Ano corrente)"), 
	COD_616(616, "Rejeição: Chave de Acesso inválida (Mês < 1 ou Mês > 12)"),
	COD_617(617, "Rejeição: Chave de Acesso inválida (CNPJ zerado ou dígito inválido)"),
	COD_618(618, "Rejeição: Chave de Acesso inválida (modelo diferente de 55)"),
	COD_619(619, "Rejeição: Chave de Acesso inválida (número NF = 0)"),
	COD_636(636, "Rejeição: O tipo do evento de cancelamento não corresponde ao tipo do evento a ser cancelado"),
	COD_637(637, "Rejeição: O Pedido de Prorrogação está cancelado"),
	COD_638(638, "Rejeição: O número Pedidos de Prorrogação tipo '1º pedido' excede o valor limite de 20 Pedidos de Prorrogação autorizados ou sem resposta."),
	COD_639(639, "Rejeição: O número Pedidos de Prorrogação tipo '2º pedido' excede o valor limite de 20 Pedidos de Prorrogação autorizados ou sem resposta."),
	COD_640(640, "Rejeição: O Pedido de Prorrogação não é válido"),
	COD_641(641, "Rejeição: A data do evento não pode ser menor que a data de autorização para o evento");
	
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
