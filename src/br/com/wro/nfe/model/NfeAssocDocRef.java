package br.com.wro.nfe.model;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>NfeAssocDocRef</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Descri��o: 	Nota Fiscal Eletr�nica Associada ao Documento Referenciado.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocDocRef")
public class NfeAssocDocRef extends TabelaGenerica {

	private static final long serialVersionUID = -564893571885342190L;
	
	private Long seqDocumento;  		//Sequ�ncia do Documento (Obrigat�rio)
	private String codChaveAcesso;     	//C�digo da Chave de Acesso do Documento Referenciado (Obrigat�rio)
	private Long codUnidFederativa;    	//C�digo da Unidade Federal do Documento Refenciado (Obrigat�rio)
	private String descAnoMesEmissao; 	//Descri��o do Ano e M�s de Emiss�o do Documento Referenciado (Obrigat�rio)
	private String cnpjEmitente;      	//C�digo do CNPJ do Emitente do Documento Referenciado (N�o Obrigat�rio)
	private Long numSerie;           	//N�mero da S�rie do Documento Referenciado (Obrigat�rio)
	
	public NfeAssocDocRef() {}
	
	/* getters and setters */

	public Long getSeqDocumento() {
		return seqDocumento;
	}

	public String getCodChaveAcesso() {
		return codChaveAcesso;
	}

	public Long getCodUnidFederativa() {
		return codUnidFederativa;
	}

	public String getDescAnoMesEmissao() {
		return descAnoMesEmissao;
	}

	public String getCnpjEmitente() {
		return cnpjEmitente;
	}

	public Long getNumSerie() {
		return numSerie;
	}

	public void setSeqDocumento(Long seqDocumento) {
		this.seqDocumento = seqDocumento;
		//parametriza os dados da tabela generica
		this.setID_1(seqDocumento);
	}

	public void setCodChaveAcesso(String codChaveAcesso) {
		this.codChaveAcesso = codChaveAcesso;
		//parametriza os dados da tabela generica
		this.setID_STRI_1(codChaveAcesso);
	}

	public void setCodUnidFederativa(Long codUnidFederativa) {
		this.codUnidFederativa = codUnidFederativa;
		//parametriza os dados da tabela generica
		this.setID_2(codUnidFederativa);
	}

	public void setDescAnoMesEmissao(String descAnoMesEmissao) {
		this.descAnoMesEmissao = descAnoMesEmissao;
		//parametriza os dados da tabela generica
		this.setDS_1(descAnoMesEmissao);
	}

	public void setCnpjEmitente(String cnpjEmitente) {
		this.cnpjEmitente = cnpjEmitente;
		//parametriza os dados da tabela generica
		this.setID_STRI_2(cnpjEmitente);
	}

	public void setNumSerie(Long numSerie) {
		this.numSerie = numSerie;
		//parametriza os dados da tabela generica
		this.setID_3(numSerie);
	}

}
