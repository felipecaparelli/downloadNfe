package br.com.wro.nfe.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>NfeAssocDuplicata</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Descrição: 	Importação de Nota Fiscal Eletrônica Associado a Duplicata<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocDuplicata")
public class NfeAssocDuplicata extends TabelaGenerica {
	
	private static final long serialVersionUID = 3420210715150032552L;
	
	
	private Long seqDuplicata;      //Sequência de Duplicata (Obrigatório)
	private String codigo;      	//Código da Duplicata (Obrigatório)
	private Date dataVencimento; 	//Data de Vencimento da Duplicata (Obrigatório)
	private Double valor;      		//Valor da Duplicata (Obrigatório)
	
	public NfeAssocDuplicata() {}

	public Long getSeqDuplicata() {
		return seqDuplicata;
	}

	public String getCodigo() {
		return codigo;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setSeqDuplicata(Long seqDuplicata) {
		this.seqDuplicata = seqDuplicata;
		//parametriza os dados da tabela generica
		this.setID_1(seqDuplicata);
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
		//parametriza os dados da tabela generica
		this.setID_STRI_1(codigo);
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
		//parametriza os dados da tabela generica
		this.setDT_1(dataVencimento);
	}

	public void setValor(Double valor) {
		this.valor = valor;
		//parametriza os dados da tabela generica
		this.setVR_1(valor);
	}
	

}
