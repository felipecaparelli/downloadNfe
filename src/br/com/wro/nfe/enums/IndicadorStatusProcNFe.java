package br.com.wro.nfe.enums;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

@Alias("indicadorStatusProcNFe")
public enum IndicadorStatusProcNFe implements Serializable, Cloneable {

	INICIADO_ABERTO(1, "Iniciado (Em Aberto)"), 
	FINALIZADO_SUCESSO(2, "Finalizado"),
	ERRO_PROCESSAMENTO(3, "Com erro");

	private int value;
	private String text;

	IndicadorStatusProcNFe(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public int getValue() {
		return this.value;
	}

	public String getText() {
		return text;
	}

	public static IndicadorStatusProcNFe get(int valor) {
		for (IndicadorStatusProcNFe sn : IndicadorStatusProcNFe.values()) {
			if (valor == sn.value)
				return sn;
		}
		return null;
	}

}
