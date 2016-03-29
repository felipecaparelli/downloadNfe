package br.com.wro.nfe.enums;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * Descrição: Enumeration representando um IndicadorStatus.<br>
 */
@Alias("indicadorStatus")
public enum IndicadorStatus implements Serializable, Cloneable
{
    ATIVO(1, "Ativo"), INATIVO(2, "Inativo");
    
    private int value;
    private String text;
    
    IndicadorStatus(int value, String text)
    {
        this.value = value;
        this.text = text;
    }
    
    public int getValue()
    {
        return this.value;
    }

	public String getText() {
		return text;
	}
	
	public static IndicadorStatus get(int valor) {
		for (IndicadorStatus sn : IndicadorStatus.values()) {
			if(valor == sn.value) return sn;
		}
		return null;
	}
}