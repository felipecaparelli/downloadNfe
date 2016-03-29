package br.com.wro.nfe.model;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>NfeAssocProdServ</h1>
 * 
 * Pacote: 		br.com.wro.nfe.model<br>
 * Descri��o: 	Importa��o da Nota Fiscal Eletr�nica Associada a Produto e Servi�o.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	04/05/2015<br>
 * </code>
 */
@Alias("nfeAssocProdServ")
public class NfeAssocProdServ extends TabelaGenerica3 {
	
	private static final long serialVersionUID = -922762097479718448L;
	private Long seqProdServ;               	//Sequencia de Produto e Servi�o (Obrigat�rio)
	private String codProdServNfe; 				//C�digo do Produto ou Servi�o da Nota Fiscal (Obrigat�rio)
	private String descProdServNfe; 			//Descri��o do Produto ou Servi�o da Nota Fiscal (Obrigat�rio)
	private String codNCM;                    	//C�digo NCM (Obrigat�rio)
	private String codCFOPProdServ;      		//C�digo CFOP do Produto ou Servi�o (N�o Obrigat�rio) 
	private String descUnidComercial;           //Descri��o da Unidade Comercial (Obrigat�rio)
	private Double qtdProdServNfe; 				//(Obrigat�rio)                           
	private Double valorUnitComercializacao;    //Valor Unit�rio de Comercializa��o (Obrigat�rio)
	private Double valorTotalBrutoProdServ; 	//Valor Total Bruto Produto ou Servi�o (Obrigat�rio)
	private String descUnidComercTributavel;    //Descri��o da Unidade Comercial Tribut�vel (N�o Obrigat�rio) 
	private Double qtdTributavel;               //Quantidade Tribut�vel (N�o Obrigat�rio) 
	private Double valorUnitTributavel;         //Valor Unit�rio Tribut�vel (N�o Obrigat�rio) 
	private Double valorTotalFrete;             //Valor Total do Frete (Obrigat�rio)
	private Double valorTotalSeguro;            //Valor Total do Seguro (Obrigat�rio)
	private Double valorDesconto;               //Valor do Desconto (Obrigat�rio)
	private Double valorOutrasDespAcess;      	//Valor de Outras Despesas Acess�rias (Obrigat�rio)
	private Long tipoValorItem; 				//C�digo do Tipo de Valor do Item (Obrigat�rio)
	private Long tipoItem;     					//C�digo do Tipo do Item da Nota Fiscal (Obrigat�rio)
	private String codPedidoCompra;             //C�digo do Pedido de Compra (N�o Obrigat�rio) 
	private String codItemPedidoCompra;      	//C�digo do Item do Pedido de Compra (N�o Obrigat�rio) 
	private String descInfoAdicionalProd;      	//Descri��o da Informa��o Adicional do Produto (N�o Obrigat�rio) */

	public NfeAssocProdServ() {}
	
	/* getters and setters */
	
	public Long getSeqProdServ() {
		return seqProdServ;
	}

	public String getCodProdServNfe() {
		return codProdServNfe;
	}

	public String getDescProdServNfe() {
		return descProdServNfe;
	}

	public String getCodNCM() {
		return codNCM;
	}

	public String getCodCFOPProdServ() {
		return codCFOPProdServ;
	}

	public String getDescUnidComercial() {
		return descUnidComercial;
	}

	public Double getQtdProdServNfe() {
		return qtdProdServNfe;
	}

	public Double getValorUnitComercializacao() {
		return valorUnitComercializacao;
	}

	public Double getValorTotalBrutoProdServ() {
		return valorTotalBrutoProdServ;
	}

	public String getDescUnidComercTributavel() {
		return descUnidComercTributavel;
	}

	public Double getQtdTributavel() {
		return qtdTributavel;
	}

	public Double getValorUnitTributavel() {
		return valorUnitTributavel;
	}

	public Double getValorTotalFrete() {
		return valorTotalFrete;
	}

	public Double getValorTotalSeguro() {
		return valorTotalSeguro;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public Double getValorOutrasDespAcess() {
		return valorOutrasDespAcess;
	}

	public Long getTipoValorItem() {
		return tipoValorItem;
	}

	public Long getTipoItem() {
		return tipoItem;
	}

	public String getCodPedidoCompra() {
		return codPedidoCompra;
	}

	public String getCodItemPedidoCompra() {
		return codItemPedidoCompra;
	}

	public String getDescInfoAdicionalProd() {
		return descInfoAdicionalProd;
	}

	public void setSeqProdServ(Long seqProdServ) {
		this.seqProdServ = seqProdServ;
		//parametriza os dados da tabela generica
		this.setID_1(seqProdServ);
	}

	public void setCodProdServNfe(String codProdServNfe) {
		this.codProdServNfe = codProdServNfe;
		//parametriza os dados da tabela generica
		this.setID_STRI_1(codProdServNfe);
	}

	public void setDescProdServNfe(String descProdServNfe) {
		this.descProdServNfe = descProdServNfe;
		//parametriza os dados da tabela generica
		this.setDS_1(descProdServNfe);
	}
	
	public void setCodNCM(String codNCM) {
		this.codNCM = codNCM;
		//parametriza os dados da tabela generica
		this.setID_STRI_2(codNCM);
	}

	public void setCodCFOPProdServ(String codCFOPProdServ) {
		this.codCFOPProdServ = codCFOPProdServ;
		//parametriza os dados da tabela generica
		this.setID_STRI_3(codCFOPProdServ);
	}

	public void setDescUnidComercial(String descUnidComercial) {
		this.descUnidComercial = descUnidComercial;
		//parametriza os dados da tabela generica
		this.setDS_2(descUnidComercial);
	}

	public void setQtdProdServNfe(Double qtdProdServNfe) {
		this.qtdProdServNfe = qtdProdServNfe;
		//parametriza os dados da tabela generica
		this.setVR_1(qtdProdServNfe);
	}

	public void setValorUnitComercializacao(Double valorUnitComercializacao) {
		this.valorUnitComercializacao = valorUnitComercializacao;
		//parametriza os dados da tabela generica
		this.setVR_2(valorUnitComercializacao);
	}

	public void setValorTotalBrutoProdServ(Double valorTotalBrutoProdServ) {
		this.valorTotalBrutoProdServ = valorTotalBrutoProdServ;
		//parametriza os dados da tabela generica
		this.setVR_3(valorTotalBrutoProdServ);
	}

	public void setDescUnidComercTributavel(String descUnidComercTributavel) {
		this.descUnidComercTributavel = descUnidComercTributavel;
		//parametriza os dados da tabela generica
		this.setDS_3(descUnidComercTributavel);
	}

	public void setQtdTributavel(Double qtdTributavel) {
		this.qtdTributavel = qtdTributavel;
		//parametriza os dados da tabela generica
		this.setVR_4(qtdTributavel);
	}

	public void setValorUnitTributavel(Double valorUnitTributavel) {
		this.valorUnitTributavel = valorUnitTributavel;
		//parametriza os dados da tabela generica
		this.setVR_5(valorUnitTributavel);
	}

	public void setValorTotalFrete(Double valorTotalFrete) {
		this.valorTotalFrete = valorTotalFrete;
		//parametriza os dados da tabela generica
		this.setVR_6(valorTotalFrete);
	}

	public void setValorTotalSeguro(Double valorTotalSeguro) {
		this.valorTotalSeguro = valorTotalSeguro;
		//parametriza os dados da tabela generica
		this.setVR_7(valorTotalSeguro);
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
		//parametriza os dados da tabela generica
		this.setVR_8(valorDesconto);
	}

	public void setValorOutrasDespAcess(Double valorOutrasDespAcess) {
		this.valorOutrasDespAcess = valorOutrasDespAcess;
		//parametriza os dados da tabela generica
		this.setVR_9(valorOutrasDespAcess);
	}

	public void setTipoValorItem(Long tipoValorItem) {
		this.tipoValorItem = tipoValorItem;
		//parametriza os dados da tabela generica
		this.setID_2(tipoValorItem);
	}

	public void setTipoItem(Long tipoItem) {
		this.tipoItem = tipoItem;
		//parametriza os dados da tabela generica
		this.setID_3(tipoItem);
	}

	public void setCodPedidoCompra(String codPedidoCompra) {
		this.codPedidoCompra = codPedidoCompra;
		//parametriza os dados da tabela generica
		this.setID_STRI_4(codPedidoCompra);
	}

	public void setCodItemPedidoCompra(String codItemPedidoCompra) {
		this.codItemPedidoCompra = codItemPedidoCompra;
		//parametriza os dados da tabela generica
		this.setID_STRI_5(codItemPedidoCompra);
	}

	public void setDescInfoAdicionalProd(String descInfoAdicionalProd) {
		this.descInfoAdicionalProd = descInfoAdicionalProd;
		//parametriza os dados da tabela generica
		this.setDS_3(descInfoAdicionalProd);
	}

}
