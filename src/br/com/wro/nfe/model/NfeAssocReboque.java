package br.com.wro.nfe.model;

import org.apache.ibatis.type.Alias;

import br.com.wro.nfe.utils.PlacaUtils;

/**
 * <code>
 * <h1>NfeAssocReboque</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Descrição: 	Placa de Reboque do Transportador da Nota Fiscal.<br>
 * Projeto: 	consultaNFeDist<br> 
 * @author 		Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since 		04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocReboque")
public class NfeAssocReboque extends TabelaGenerica {

	private static final long serialVersionUID = -1351151197797834520L;

	private Long seqReboqueTrans; 		// Sequencia de Reboque do Transportador (Obrigatório)
	private String siglaPlaca; 			// Sigla da Placa do Reboque do Transportador (Obrigatório)
	private Long numeroPlaca; 			// Número do Placa de Reboque do Transportador (Obrigatório)
	private String siglaUnidFederativa; // Sigla da Unidade Federal do Reboque do Transportador (Obrigatório) */

	public Long getSeqReboqueTrans() {
		return seqReboqueTrans;
	}

	public String getSiglaPlaca() {
		return siglaPlaca;
	}

	public Long getNumeroPlaca() {
		return numeroPlaca;
	}

	public String getSiglaUnidFederativa() {
		return siglaUnidFederativa;
	}

	public void setSeqReboqueTrans(Long seqReboqueTrans) {
		this.seqReboqueTrans = seqReboqueTrans;
		//parametriza os dados da tabela generica
		this.setID_1(seqReboqueTrans);
	}

	public void setSiglaPlaca(String siglaPlaca) {
		this.siglaPlaca = siglaPlaca;
		//parametriza os dados da tabela generica
		this.setID_STRI_1(siglaPlaca);
	}

	public void setNumeroPlaca(Long numeroPlaca) {
		this.numeroPlaca = numeroPlaca;
		//parametriza os dados da tabela generica
		this.setID_2(numeroPlaca);
	}
	
	public void setPlaca(String placa) {
		String siglaPlaca = PlacaUtils.getSiglaPlaca(placa);
		this.setSiglaPlaca(siglaPlaca);
		Long numPlaca = PlacaUtils.getNumPlaca(placa);
		this.setNumeroPlaca(numPlaca);
	}

	public void setSiglaUnidFederativa(String siglaUnidFederativa) {
		this.siglaUnidFederativa = siglaUnidFederativa;
		//parametriza os dados da tabela generica
		this.setID_STRI_2(siglaUnidFederativa);
	}

}
