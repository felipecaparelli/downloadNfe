package br.com.wro.nfe.model;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>NfeAssocDocRef</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Descrição: 	Nota Fiscal Eletrônica Associada ao Documento Referenciado.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocDocRef")
public class NfeAssocDocRef extends TabelaGenerica {

	private static final long serialVersionUID = -564893571885342190L;
	
	private Long seqDocumento;  		//Sequência do Documento (Obrigatório)
	private String codChaveAcesso;     	//Código da Chave de Acesso do Documento Referenciado (Obrigatório)
	private Long codUnidFederativa;    	//Código da Unidade Federal do Documento Refenciado (Obrigatório)
	private String descAnoMesEmissao; 	//Descrição do Ano e Mês de Emissão do Documento Referenciado (Obrigatório)
	private String cnpjEmitente;      	//Código do CNPJ do Emitente do Documento Referenciado (Não Obrigatório)
	private Long numSerie;           	//Número da Série do Documento Referenciado (Obrigatório)
	
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
