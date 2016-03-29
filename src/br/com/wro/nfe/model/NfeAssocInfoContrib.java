package br.com.wro.nfe.model;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>NfeAssocInfoContrib</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Descri��o: 	Importa��o de Nota Fiscal Eletr�nica Assciado a Informa��o do Contribuinte<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocInfoContrib")
public class NfeAssocInfoContrib extends TabelaGenerica {
	
	private static final long serialVersionUID = 2401248151270998667L;
	
	private Long seqInfoCont; 			// Sequ�ncia de Informa��o do Contribuinte (Obrigat�rio somente na altera��o)
	private String nomeAtributoInfo; 	// Nome do Atributo da Informa��o (Obrigat�rio)
	private String descInfo;         	// Descri��o da Informa��o (Obrigat�rio)

	public NfeAssocInfoContrib() {}
	
	public Long getSeqInfoCont() {
		return seqInfoCont;
	}

	public String getNomeAtributoInfo() {
		return nomeAtributoInfo;
	}

	public String getDescInfo() {
		return descInfo;
	}

	public void setSeqInfoCont(Long seqInfoCont) {
		this.seqInfoCont = seqInfoCont;
		//parametriza os dados da tabela generica
		this.setID_1(seqInfoCont);
	}

	public void setNomeAtributoInfo(String nomeAtributoInfo) {
		this.nomeAtributoInfo = nomeAtributoInfo;
		//parametriza os dados da tabela generica
		this.setID_STRI_1(nomeAtributoInfo);
	}

	public void setDescInfo(String descInfo) {
		this.descInfo = descInfo;
		//parametriza os dados da tabela generica
		this.setDS_1(descInfo);
	}

}
