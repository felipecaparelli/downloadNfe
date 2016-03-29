package br.com.wro.nfe.enums;

public enum RetornoProcessamentoDownload {
	
	//SUCESSO
	COD_128(128, "Lote de Evento Processado"),
	COD_135(135, "Evento registrado e vinculado a NF-e"),
	COD_136(136, "Evento registrado, mas n�o vinculado a NF-e"),
	COD_137(137, "Nenhum documento localizado para o Destinat�rio"),
	COD_138(138, "Documento localizado para o Destinat�rio"),
	COD_139(139, "Pedido de Download processado"),
	COD_140(140, "Download disponibilizado"),
	//ERRO
	COD_489(489, "Rejei��o: CNPJ informado inv�lido (DV ou zeros)"),
	COD_490(490, "Rejei��o: CPF informado inv�lido (DV ou zeros)"),
	COD_491(491, "Rejei��o: O tpEvento informado inv�lido"),
	COD_492(492, "Rejei��o: O verEvento informado inv�lido"),
	COD_493(493, "Rejei��o: Evento n�o atende o Schema XML espec�fico"),
	COD_494(494, "Rejei��o: Chave de Acesso inexistente"),
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
	COD_589(589, "Rejei��o: N�mero do NSU informado superior ao maior NSU da base de dados da SEFAZ"),
	COD_593(593, "Rejei��o: CNPJ-Base consultado difere do CNPJ-Base do Certificado Digital"),
	COD_594(594, "Rejei��o: O n�mero de sequencia do evento informado � maior que o permitido"),
	COD_595(595, "Rejei��o: Obrigat�ria a informa��o da justificativa do evento."),
	COD_596(596, "Rejei��o: Evento apresentado fora do prazo: [prazo vigente]"),
	COD_614(614, "Rejei��o: Chave de Acesso inv�lida (C�digo UF inv�lido)"),
	COD_615(615, "Rejei��o: Chave de Acesso inv�lida (Ano menor que 06 ou Ano maior que Ano corrente)"),
	COD_616(616, "Rejei��o: Chave de Acesso inv�lida (M�s menor que 1 ou M�s maior que 12)"),
	COD_617(617, "Rejei��o: Chave de Acesso inv�lida (CNPJ zerado ou d�gito inv�lido)"),
	COD_618(618, "Rejei��o: Chave de Acesso inv�lida (modelo diferente de 55)"),
	COD_619(619, "Rejei��o: Chave de Acesso inv�lida (n�mero NF = 0)"),
	COD_631(631, "Rejei��o: CNPJ-Base do Destinat�rio difere do CNPJ-Base do Certificado Digital"),
	COD_632(632, "Rejei��o: Solicita��o fora de prazo, a NF-e n�o est� mais dispon�vel para download"),
	COD_633(633, "Rejei��o: NF-e indispon�vel para download devido a aus�ncia de Manifesta��o do Destinat�rio"),
	COD_634(634, "Rejei��o: Destinat�rio da NF-e n�o tem o mesmo CNPJ raiz do solicitante do download"),
	COD_650(650, "Rejei��o: Evento de 'Ci�ncia da Opera��o' para NF-e Cancelada ou Denegada"),
	COD_651(651, "Rejei��o: Evento de 'Desconhecimento da Opera��o' para NF-e Cancelada ou Denegada"),
	COD_653(653, "Rejei��o: NF-e Cancelada, arquivo indispon�vel para download"),
	COD_654(654, "Rejei��o: NF-e Denegada, arquivo indispon�vel para download"),
	COD_655(655, "Rejei��o: Evento de Ci�ncia da Opera��o informado ap�s a manifesta��o final do destinat�rio"),
	COD_656(656, "Rejei��o: Consumo Indevido"),
	COD_657(657, "Rejei��o: C�digo do �rg�o diverge do �rg�o autorizador"),
	COD_658(658, "Rejei��o: UF do destinat�rio da Chave de Acesso diverge da UF autorizadora");
	

	private int codigo;
	private String mensagem;
	
	RetornoProcessamentoDownload(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	public static RetornoProcessamentoDownload get(int codigo) {
		for (RetornoProcessamentoDownload res : RetornoProcessamentoDownload.values()) {
			if(res.codigo == codigo) return res;
		}
		throw new IllegalArgumentException(String.format("N\u00e3o existe o c\u00f3digo %s no mapeamento.", codigo));
	}

}
