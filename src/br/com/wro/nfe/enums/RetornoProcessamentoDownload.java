package br.com.wro.nfe.enums;

public enum RetornoProcessamentoDownload {
	
	//SUCESSO
	COD_128(128, "Lote de Evento Processado"),
	COD_135(135, "Evento registrado e vinculado a NF-e"),
	COD_136(136, "Evento registrado, mas não vinculado a NF-e"),
	COD_137(137, "Nenhum documento localizado para o Destinatário"),
	COD_138(138, "Documento localizado para o Destinatário"),
	COD_139(139, "Pedido de Download processado"),
	COD_140(140, "Download disponibilizado"),
	//ERRO
	COD_489(489, "Rejeição: CNPJ informado inválido (DV ou zeros)"),
	COD_490(490, "Rejeição: CPF informado inválido (DV ou zeros)"),
	COD_491(491, "Rejeição: O tpEvento informado inválido"),
	COD_492(492, "Rejeição: O verEvento informado inválido"),
	COD_493(493, "Rejeição: Evento não atende o Schema XML específico"),
	COD_494(494, "Rejeição: Chave de Acesso inexistente"),
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
	COD_589(589, "Rejeição: Número do NSU informado superior ao maior NSU da base de dados da SEFAZ"),
	COD_593(593, "Rejeição: CNPJ-Base consultado difere do CNPJ-Base do Certificado Digital"),
	COD_594(594, "Rejeição: O número de sequencia do evento informado é maior que o permitido"),
	COD_595(595, "Rejeição: Obrigatória a informação da justificativa do evento."),
	COD_596(596, "Rejeição: Evento apresentado fora do prazo: [prazo vigente]"),
	COD_614(614, "Rejeição: Chave de Acesso inválida (Código UF inválido)"),
	COD_615(615, "Rejeição: Chave de Acesso inválida (Ano menor que 06 ou Ano maior que Ano corrente)"),
	COD_616(616, "Rejeição: Chave de Acesso inválida (Mês menor que 1 ou Mês maior que 12)"),
	COD_617(617, "Rejeição: Chave de Acesso inválida (CNPJ zerado ou dígito inválido)"),
	COD_618(618, "Rejeição: Chave de Acesso inválida (modelo diferente de 55)"),
	COD_619(619, "Rejeição: Chave de Acesso inválida (número NF = 0)"),
	COD_631(631, "Rejeição: CNPJ-Base do Destinatário difere do CNPJ-Base do Certificado Digital"),
	COD_632(632, "Rejeição: Solicitação fora de prazo, a NF-e não está mais disponível para download"),
	COD_633(633, "Rejeição: NF-e indisponível para download devido a ausência de Manifestação do Destinatário"),
	COD_634(634, "Rejeição: Destinatário da NF-e não tem o mesmo CNPJ raiz do solicitante do download"),
	COD_650(650, "Rejeição: Evento de 'Ciência da Operação' para NF-e Cancelada ou Denegada"),
	COD_651(651, "Rejeição: Evento de 'Desconhecimento da Operação' para NF-e Cancelada ou Denegada"),
	COD_653(653, "Rejeição: NF-e Cancelada, arquivo indisponível para download"),
	COD_654(654, "Rejeição: NF-e Denegada, arquivo indisponível para download"),
	COD_655(655, "Rejeição: Evento de Ciência da Operação informado após a manifestação final do destinatário"),
	COD_656(656, "Rejeição: Consumo Indevido"),
	COD_657(657, "Rejeição: Código do Órgão diverge do órgão autorizador"),
	COD_658(658, "Rejeição: UF do destinatário da Chave de Acesso diverge da UF autorizadora");
	

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
