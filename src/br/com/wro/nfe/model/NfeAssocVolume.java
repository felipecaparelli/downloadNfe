package br.com.wro.nfe.model;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>NfeAssocVolume</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocVolume")
public class NfeAssocVolume extends TabelaGenerica {
	
	private static final long serialVersionUID = 4639142556874655968L;
	
	private Long seqVolume;            	//Sequencia de Volume (Obrigatório)
	private Double quantidade;          //Quantidade de Volume Transportado (Obrigatório)
	private String descEspecie;       	//Descrição da Espécie do Volume Transportado (Não Obrigatório)
	private String descMarca;       	//Descrição da Marca dos Volumes Transportados (Não Obrigatório)
	private String codigo;            	//Código do Volume Transportado (Não Obrigatório)
	private Double valorPesoBruto; 		//Valor do Peso Bruto do Volume Transportado (em Kg) (Obrigatório)
	private Double valorPesoLiquido; 	//Valor do Peso Liquido do Volume Transportado (em Kg) (Obrigatório)

	public NfeAssocVolume() {}
	
	public Long getSeqVolume() {
		return seqVolume;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public String getDescEspecie() {
		return descEspecie;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getValorPesoBruto() {
		return valorPesoBruto;
	}

	public Double getValorPesoLiquido() {
		return valorPesoLiquido;
	}

	public void setSeqVolume(Long seqVolume) {
		this.seqVolume = seqVolume;
		//parametriza os dados da tabela generica
		this.setID_1(seqVolume);
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
		//parametriza os dados da tabela generica
		this.setVR_1(quantidade);
	}

	public void setDescEspecie(String descEspecie) {
		this.descEspecie = descEspecie;
		//parametriza os dados da tabela generica
		this.setDS_1(descEspecie);
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
		//parametriza os dados da tabela generica
		this.setDS_2(descMarca);
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		//parametriza os dados da tabela generica
		this.setID_STRI_1(codigo);
	}

	public void setValorPesoBruto(Double valorPesoBruto) {
		this.valorPesoBruto = valorPesoBruto;
		//parametriza os dados da tabela generica
		this.setVR_2(valorPesoBruto);
	}

	public void setValorPesoLiquido(Double valorPesoLiquido) {
		this.valorPesoLiquido = valorPesoLiquido;
		//parametriza os dados da tabela generica
		this.setVR_3(valorPesoLiquido);
	}

}
