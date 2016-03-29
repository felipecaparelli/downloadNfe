package br.com.wro.nfe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("nfeVO")
public class NfeVO implements Serializable {

	private static final long serialVersionUID = -7686176543181194561L;
	
	private Integer idNFInte;
	/* Dados da Parte Emitente */
	private String cpfCnpjEmitenteNF;
	private String razalSocialEmitenteNF;
	private String nomeFantasiaEmitenteNF;
	private String inscricaoMunicipalEmitenteNF;
	private String inscricaoEstadualEmitenteNF;
	private Integer codRegimeTrubitarioEmitenteNF;
	private String logradouroEmitenteNF;
	private String numeroLogradouroEmitenteNF;
	private String complEnderecoEmitenteNF;
	private String bairroEmitenteNF;
	private String municipioEmitenteNF;
	private String sgUfEmitenteNF;
	private String cepEmitenteNF;
	private String paisEmitenteNF;
	private String telefoneEmitenteNF;
	private String emailEmitenteNF;
	private Integer tipoInscricaoEmitenteNF;
	/* Dados da Parte Destinatário */
	private String cpfCnpjDestinatarioNF;
	private String razaoSocialDestinatarioNF;
	private String nomeFantasiaDestinatarioNF;
	private String inscricaoMunicipalDestinatarioNF;
	private String inscricaoEstadualDestinatarioNF;
	private Integer codRegimeTributarioDestinatarioNF;
	private String logradouroDestinatarioNF;
	private String numeroLogradouroDestinatarioNF;
	private String complEnderecoDestinatarioNF;
	private String bairroDestinatarioNF;
	private String municipioDestinatarioNF;
	private String sgUfDestinatarioNF;
	private String cepDestinatarioNF;
	private String paisDestinatarioNF;
	private String telefoneDestinatarioNF;
	private String emailDestinatarioNF;
	private Long tipoInscricaoDestinatarioNF;
	/* Dado do Transportador */
	private String cpfCnpjTransportadora;
	private String razaoSocialTransportadora;
	private String inscricaoEstadualTransportadora;
	private String enderecoTransportadora;
	private String municipioTransportadora;
	private String sgUfTransportadora;
	/* Importação da Nota Fiscal Eletrônica Associada a Produto e Serviço */
	private List<NfeAssocProdServ> tbImpoNfeAssocProdServ;
	/* Nota Fiscal Eletrônica Associada ao Documento Referenciado */
	private List<NfeAssocDocRef> tbImpoNfeAssocDocRef;
	/* Placa de Reboque do Transportador da Nota Fiscal */
	private List<NfeAssocReboque> tbImpoNfeAssocReboque;
	/* Importação de Nota Fiscal Eletrônica Associado a Volume */
	private List<NfeAssocVolume> tbImpoNfeAssocVolume;
	/* Importação de Nota Fiscal Eletrônica Associado a Duplicata */
	private List<NfeAssocDuplicata> tbImpoNfeAssocDuplicata;
	/* Importação de Nota Fiscal Eletrônica Assciado a Informação do Contribuinte */
	private List<NfeAssocInfoContrib> tbImpoNfeAssocInfoContrib;
	/* Cadastro de Nota Fiscal Eletrônica */
	private String codNFe;
	private Integer cdUfOrigIBGE;
	private String codChaveAcesso;
	private Integer codFormaPgto;
	private Integer codModeloDocFiscal;
	private Integer numSeriDocFiscal;
	private Integer numDocFiscal;
	private Date dtEmissaoDocFiscal;
	private Date dtEntradaSaida;
	private Integer codTipoOperacao;
	private Integer codTipoLocalDestinatarioOper;
	private Integer codFormImpressao;
	private Integer codFormEmissao;
	private Integer codFinalEmissao;
	private Integer digitoVerificadorChaveAcesso;
	private Integer tipoAmbienteEmissao; // Hom|Prod
	private Integer codMunicipioOcorFatoGeracao;
	private Integer codTipoOperConsFina;
	private Integer codTipoPresCOnsFina;
	private Integer codTipoProcEmissao;
	private String versaoProcEmissao;
	private Double valorBaseCalcICMS;
	private Double valorTotalICMS;
	private Double valorTotalICMSDes;
	private Double valorBaseCalcICMSSt;
	private Double valorTotalICMSSt;
	private Double valorTotalProdServ;
	private Double valorTotalFrete;
	private Double valorTotalSeguro;
	private Double valorTotalDesc;
	private Double valorTotalII;
	private Double valorTotalIPI;
	private Double valorTotalPIS;
	private Double valorCofins;
	private Double valorTotalOutrasDespAces;
	private Double valorTotalNFe;
	private Double valorTotalAproTrib;
	private Integer codModalidadeFrete;
	private String siglaPlacaTransportadora;
	private String numPlacaTransportadora;
	private String codigoFaturamento;
	private Double valorOrigFaturamento;
	private Double valorDescFaturamento;
	private Double valorLiqFaturamento;
	private String infoComplementar;
	/* Status da nota fiscal eletrônica */
	private Integer codStatNF;
	private Long idLogProcessamento;
	
	public NfeVO() {}

	public Integer getIdNFInte() {
		return idNFInte;
	}

	public String getCpfCnpjEmitenteNF() {
		return cpfCnpjEmitenteNF;
	}

	public String getRazalSocialEmitenteNF() {
		return razalSocialEmitenteNF;
	}

	public String getNomeFantasiaEmitenteNF() {
		return nomeFantasiaEmitenteNF;
	}

	public String getInscricaoMunicipalEmitenteNF() {
		return inscricaoMunicipalEmitenteNF;
	}

	public String getInscricaoEstadualEmitenteNF() {
		return inscricaoEstadualEmitenteNF;
	}

	public Integer getCodRegimeTrubitarioEmitenteNF() {
		return codRegimeTrubitarioEmitenteNF;
	}

	public String getLogradouroEmitenteNF() {
		return logradouroEmitenteNF;
	}

	public String getNumeroLogradouroEmitenteNF() {
		return numeroLogradouroEmitenteNF;
	}

	public String getComplEnderecoEmitenteNF() {
		return complEnderecoEmitenteNF;
	}

	public String getBairroEmitenteNF() {
		return bairroEmitenteNF;
	}

	public String getMunicipioEmitenteNF() {
		return municipioEmitenteNF;
	}

	public String getSgUfEmitenteNF() {
		return sgUfEmitenteNF;
	}

	public String getCepEmitenteNF() {
		return cepEmitenteNF;
	}

	public String getPaisEmitenteNF() {
		return paisEmitenteNF;
	}

	public String getTelefoneEmitenteNF() {
		return telefoneEmitenteNF;
	}

	public String getEmailEmitenteNF() {
		return emailEmitenteNF;
	}

	public Integer getTipoInscricaoEmitenteNF() {
		return tipoInscricaoEmitenteNF;
	}

	public String getCpfCnpjDestinatarioNF() {
		return cpfCnpjDestinatarioNF;
	}

	public String getRazaoSocialDestinatarioNF() {
		return razaoSocialDestinatarioNF;
	}

	public String getNomeFantasiaDestinatarioNF() {
		return nomeFantasiaDestinatarioNF;
	}

	public String getInscricaoMunicipalDestinatarioNF() {
		return inscricaoMunicipalDestinatarioNF;
	}

	public String getInscricaoEstadualDestinatarioNF() {
		return inscricaoEstadualDestinatarioNF;
	}

	public Integer getCodRegimeTributarioDestinatarioNF() {
		return codRegimeTributarioDestinatarioNF;
	}

	public String getLogradouroDestinatarioNF() {
		return logradouroDestinatarioNF;
	}

	public String getNumeroLogradouroDestinatarioNF() {
		return numeroLogradouroDestinatarioNF;
	}

	public String getComplEnderecoDestinatarioNF() {
		return complEnderecoDestinatarioNF;
	}

	public String getBairroDestinatarioNF() {
		return bairroDestinatarioNF;
	}

	public String getMunicipioDestinatarioNF() {
		return municipioDestinatarioNF;
	}

	public String getSgUfDestinatarioNF() {
		return sgUfDestinatarioNF;
	}

	public String getCepDestinatarioNF() {
		return cepDestinatarioNF;
	}

	public String getPaisDestinatarioNF() {
		return paisDestinatarioNF;
	}

	public String getTelefoneDestinatarioNF() {
		return telefoneDestinatarioNF;
	}

	public String getEmailDestinatarioNF() {
		return emailDestinatarioNF;
	}

	public Long getTipoInscricaoDestinatarioNF() {
		return tipoInscricaoDestinatarioNF;
	}

	public String getCpfCnpjTransportadora() {
		return cpfCnpjTransportadora;
	}

	public String getRazaoSocialTransportadora() {
		return razaoSocialTransportadora;
	}

	public String getInscricaoEstadualTransportadora() {
		return inscricaoEstadualTransportadora;
	}

	public String getEnderecoTransportadora() {
		return enderecoTransportadora;
	}

	public String getMunicipioTransportadora() {
		return municipioTransportadora;
	}

	public String getSgUfTransportadora() {
		return sgUfTransportadora;
	}

	public List<NfeAssocProdServ> getTbImpoNfeAssocProdServ() {
		return tbImpoNfeAssocProdServ;
	}

	public List<NfeAssocDocRef> getTbImpoNfeAssocDocRef() {
		return tbImpoNfeAssocDocRef;
	}

	public List<NfeAssocReboque> getTbImpoNfeAssocReboque() {
		return tbImpoNfeAssocReboque;
	}

	public List<NfeAssocVolume> getTbImpoNfeAssocVolume() {
		return tbImpoNfeAssocVolume;
	}

	public List<NfeAssocDuplicata> getTbImpoNfeAssocDuplicata() {
		return tbImpoNfeAssocDuplicata;
	}

	public List<NfeAssocInfoContrib> getTbImpoNfeAssocInfoContrib() {
		return tbImpoNfeAssocInfoContrib;
	}

	public String getCodNFe() {
		return codNFe;
	}

	public Integer getCdUfOrigIBGE() {
		return cdUfOrigIBGE;
	}

	public String getCodChaveAcesso() {
		return codChaveAcesso;
	}

	public Integer getCodFormaPgto() {
		return codFormaPgto;
	}

	public Integer getCodModeloDocFiscal() {
		return codModeloDocFiscal;
	}

	public Integer getNumSeriDocFiscal() {
		return numSeriDocFiscal;
	}

	public Integer getNumDocFiscal() {
		return numDocFiscal;
	}

	public Date getDtEmissaoDocFiscal() {
		return dtEmissaoDocFiscal;
	}

	public Date getDtEntradaSaida() {
		return dtEntradaSaida;
	}

	public Integer getCodTipoOperacao() {
		return codTipoOperacao;
	}

	public Integer getCodTipoLocalDestinatarioOper() {
		return codTipoLocalDestinatarioOper;
	}

	public Integer getCodFormImpressao() {
		return codFormImpressao;
	}

	public Integer getCodFormEmissao() {
		return codFormEmissao;
	}

	public Integer getCodFinalEmissao() {
		return codFinalEmissao;
	}

	public Integer getDigitoVerificadorChaveAcesso() {
		return digitoVerificadorChaveAcesso;
	}

	public Integer getTipoAmbienteEmissao() {
		return tipoAmbienteEmissao;
	}

	public Integer getCodMunicipioOcorFatoGeracao() {
		return codMunicipioOcorFatoGeracao;
	}

	public Integer getCodTipoOperConsFina() {
		return codTipoOperConsFina;
	}

	public Integer getCodTipoPresCOnsFina() {
		return codTipoPresCOnsFina;
	}

	public Integer getCodTipoProcEmissao() {
		return codTipoProcEmissao;
	}

	public String getVersaoProcEmissao() {
		return versaoProcEmissao;
	}

	public Double getValorBaseCalcICMS() {
		return valorBaseCalcICMS;
	}

	public Double getValorTotalICMS() {
		return valorTotalICMS;
	}

	public Double getValorTotalICMSDes() {
		return valorTotalICMSDes;
	}

	public Double getValorBaseCalcICMSSt() {
		return valorBaseCalcICMSSt;
	}

	public Double getValorTotalICMSSt() {
		return valorTotalICMSSt;
	}

	public Double getValorTotalProdServ() {
		return valorTotalProdServ;
	}

	public Double getValorTotalFrete() {
		return valorTotalFrete;
	}

	public Double getValorTotalSeguro() {
		return valorTotalSeguro;
	}

	public Double getValorTotalDesc() {
		return valorTotalDesc;
	}

	public Double getValorTotalII() {
		return valorTotalII;
	}

	public Double getValorTotalIPI() {
		return valorTotalIPI;
	}

	public Double getValorTotalPIS() {
		return valorTotalPIS;
	}

	public Double getValorCofins() {
		return valorCofins;
	}

	public Double getValorTotalOutrasDespAces() {
		return valorTotalOutrasDespAces;
	}

	public Double getValorTotalNFe() {
		return valorTotalNFe;
	}

	public Double getValorTotalAproTrib() {
		return valorTotalAproTrib;
	}

	public Integer getCodModalidadeFrete() {
		return codModalidadeFrete;
	}

	public String getSiglaPlacaTransportadora() {
		return siglaPlacaTransportadora;
	}

	public String getNumPlacaTransportadora() {
		return numPlacaTransportadora;
	}

	public String getCodigoFaturamento() {
		return codigoFaturamento;
	}

	public Double getValorOrigFaturamento() {
		return valorOrigFaturamento;
	}

	public Double getValorDescFaturamento() {
		return valorDescFaturamento;
	}

	public Double getValorLiqFaturamento() {
		return valorLiqFaturamento;
	}

	public String getInfoComplementar() {
		return infoComplementar;
	}

	public Integer getCodStatNF() {
		return codStatNF;
	}

	public void setIdNFInte(Integer idNFInte) {
		this.idNFInte = idNFInte;
	}

	public void setCpfCnpjEmitenteNF(String cpfCnpjEmitenteNF) {
		this.cpfCnpjEmitenteNF = cpfCnpjEmitenteNF;
	}

	public void setRazalSocialEmitenteNF(String razalSocialEmitenteNF) {
		this.razalSocialEmitenteNF = razalSocialEmitenteNF;
	}

	public void setNomeFantasiaEmitenteNF(String nomeFantasiaEmitenteNF) {
		this.nomeFantasiaEmitenteNF = nomeFantasiaEmitenteNF;
	}

	public void setInscricaoMunicipalEmitenteNF(
			String inscricaoMunicipalEmitenteNF) {
		this.inscricaoMunicipalEmitenteNF = inscricaoMunicipalEmitenteNF;
	}

	public void setInscricaoEstadualEmitenteNF(
			String inscricaoEstadualEmitenteNF) {
		this.inscricaoEstadualEmitenteNF = inscricaoEstadualEmitenteNF;
	}

	public void setCodRegimeTrubitarioEmitenteNF(
			Integer codRegimeTrubitarioEmitenteNF) {
		this.codRegimeTrubitarioEmitenteNF = codRegimeTrubitarioEmitenteNF;
	}

	public void setLogradouroEmitenteNF(String logradouroEmitenteNF) {
		this.logradouroEmitenteNF = logradouroEmitenteNF;
	}

	public void setNumeroLogradouroEmitenteNF(String numeroLogradouroEmitenteNF) {
		this.numeroLogradouroEmitenteNF = numeroLogradouroEmitenteNF;
	}

	public void setComplEnderecoEmitenteNF(String complEnderecoEmitenteNF) {
		this.complEnderecoEmitenteNF = complEnderecoEmitenteNF;
	}

	public void setBairroEmitenteNF(String bairroEmitenteNF) {
		this.bairroEmitenteNF = bairroEmitenteNF;
	}

	public void setMunicipioEmitenteNF(String municipioEmitenteNF) {
		this.municipioEmitenteNF = municipioEmitenteNF;
	}

	public void setSgUfEmitenteNF(String sgUfEmitenteNF) {
		this.sgUfEmitenteNF = sgUfEmitenteNF;
	}

	public void setCepEmitenteNF(String cepEmitenteNF) {
		this.cepEmitenteNF = cepEmitenteNF;
	}

	public void setPaisEmitenteNF(String paisEmitenteNF) {
		this.paisEmitenteNF = paisEmitenteNF;
	}

	public void setTelefoneEmitenteNF(String telefoneEmitenteNF) {
		this.telefoneEmitenteNF = telefoneEmitenteNF;
	}

	public void setEmailEmitenteNF(String emailEmitenteNF) {
		this.emailEmitenteNF = emailEmitenteNF;
	}

	public void setTipoInscricaoEmitenteNF(Integer tipoInscricaoEmitenteNF) {
		this.tipoInscricaoEmitenteNF = tipoInscricaoEmitenteNF;
	}

	public void setCpfCnpjDestinatarioNF(String cpfCnpjDestinatarioNF) {
		this.cpfCnpjDestinatarioNF = cpfCnpjDestinatarioNF;
	}

	public void setRazaoSocialDestinatarioNF(String razaoSocialDestinatarioNF) {
		this.razaoSocialDestinatarioNF = razaoSocialDestinatarioNF;
	}

	public void setNomeFantasiaDestinatarioNF(String razalSocialDestinatarioNF) {
		this.nomeFantasiaDestinatarioNF = razalSocialDestinatarioNF;
	}

	public void setInscricaoMunicipalDestinatarioNF(
			String inscricaoMunicipalDestinatarioNF) {
		this.inscricaoMunicipalDestinatarioNF = inscricaoMunicipalDestinatarioNF;
	}

	public void setInscricaoEstadualDestinatarioNF(
			String inscricaoEstadualDestinatarioNF) {
		this.inscricaoEstadualDestinatarioNF = inscricaoEstadualDestinatarioNF;
	}

	public void setCodRegimeTributarioDestinatarioNF(
			Integer codRegimeTributarioDestinatarioNF) {
		this.codRegimeTributarioDestinatarioNF = codRegimeTributarioDestinatarioNF;
	}

	public void setLogradouroDestinatarioNF(String logradouroDestinatarioNF) {
		this.logradouroDestinatarioNF = logradouroDestinatarioNF;
	}

	public void setNumeroLogradouroDestinatarioNF(
			String numeroLogradouroDestinatarioNF) {
		this.numeroLogradouroDestinatarioNF = numeroLogradouroDestinatarioNF;
	}

	public void setComplEnderecoDestinatarioNF(
			String complEnderecoDestinatarioNF) {
		this.complEnderecoDestinatarioNF = complEnderecoDestinatarioNF;
	}

	public void setBairroDestinatarioNF(String bairroDestinatarioNF) {
		this.bairroDestinatarioNF = bairroDestinatarioNF;
	}

	public void setMunicipioDestinatarioNF(String municipioDestinatarioNF) {
		this.municipioDestinatarioNF = municipioDestinatarioNF;
	}

	public void setSgUfDestinatarioNF(String sgUfDestinatarioNF) {
		this.sgUfDestinatarioNF = sgUfDestinatarioNF;
	}

	public void setCepDestinatarioNF(String cepDestinatarioNF) {
		this.cepDestinatarioNF = cepDestinatarioNF;
	}

	public void setPaisDestinatarioNF(String paisDestinatarioNF) {
		this.paisDestinatarioNF = paisDestinatarioNF;
	}

	public void setTelefoneDestinatarioNF(String telefoneDestinatarioNF) {
		this.telefoneDestinatarioNF = telefoneDestinatarioNF;
	}

	public void setEmailDestinatarioNF(String emailDestinatarioNF) {
		this.emailDestinatarioNF = emailDestinatarioNF;
	}

	public void setTipoInscricaoDestinatarioNF(
			Long tipoInscricaoDestinatarioNF) {
		this.tipoInscricaoDestinatarioNF = tipoInscricaoDestinatarioNF;
	}

	public void setCpfCnpjTransportadora(String cpfCnpjTransportadora) {
		this.cpfCnpjTransportadora = cpfCnpjTransportadora;
	}

	public void setRazaoSocialTransportadora(String razaoSocialTransportadora) {
		this.razaoSocialTransportadora = razaoSocialTransportadora;
	}

	public void setInscricaoEstadualTransportadora(
			String inscricaoEstadualTransportadora) {
		this.inscricaoEstadualTransportadora = inscricaoEstadualTransportadora;
	}

	public void setEnderecoTransportadora(String enderecoTransportadora) {
		this.enderecoTransportadora = enderecoTransportadora;
	}

	public void setMunicipioTransportadora(String municipioTransportadora) {
		this.municipioTransportadora = municipioTransportadora;
	}

	public void setSgUfTransportadora(String sgUfTransportadora) {
		this.sgUfTransportadora = sgUfTransportadora;
	}

	public void setTbImpoNfeAssocProdServ(
			List<NfeAssocProdServ> tbImpoNfeAssocProdServ) {
		this.tbImpoNfeAssocProdServ = tbImpoNfeAssocProdServ;
	}

	public void setTbImpoNfeAssocDocRef(
			List<NfeAssocDocRef> tbImpoNfeAssocDocRef) {
		this.tbImpoNfeAssocDocRef = tbImpoNfeAssocDocRef;
	}

	public void setTbImpoNfeAssocReboque(
			List<NfeAssocReboque> tbImpoNfeAssocReboque) {
		this.tbImpoNfeAssocReboque = tbImpoNfeAssocReboque;
	}

	public void setTbImpoNfeAssocVolume(
			List<NfeAssocVolume> tbImpoNfeAssocVolume) {
		this.tbImpoNfeAssocVolume = tbImpoNfeAssocVolume;
	}

	public void setTbImpoNfeAssocDuplicata(
			List<NfeAssocDuplicata> tbImpoNfeAssocDuplicata) {
		this.tbImpoNfeAssocDuplicata = tbImpoNfeAssocDuplicata;
	}

	public void setTbImpoNfeAssocInfoContrib(
			List<NfeAssocInfoContrib> tbImpoNfeAssocInfoContrib) {
		this.tbImpoNfeAssocInfoContrib = tbImpoNfeAssocInfoContrib;
	}

	public void setCodNFe(String codNFe) {
		this.codNFe = codNFe;
	}

	public void setCdUfOrigIBGE(Integer cdUfOrigIBGE) {
		this.cdUfOrigIBGE = cdUfOrigIBGE;
	}

	public void setCodChaveAcesso(String codChaveAcesso) {
		this.codChaveAcesso = codChaveAcesso;
	}

	public void setCodFormaPgto(Integer codFormaPgto) {
		this.codFormaPgto = codFormaPgto;
	}

	public void setCodModeloDocFiscal(Integer codModeloDocFiscal) {
		this.codModeloDocFiscal = codModeloDocFiscal;
	}

	public void setNumSeriDocFiscal(Integer numSeriDocFiscal) {
		this.numSeriDocFiscal = numSeriDocFiscal;
	}

	public void setNumDocFiscal(Integer numDocFiscal) {
		this.numDocFiscal = numDocFiscal;
	}

	public void setDtEmissaoDocFiscal(Date dtEmissaoDocFiscal) {
		this.dtEmissaoDocFiscal = dtEmissaoDocFiscal;
	}

	public void setDtEntradaSaida(Date dtEntradaSaida) {
		this.dtEntradaSaida = dtEntradaSaida;
	}

	public void setCodTipoOperacao(Integer codTipoOperacao) {
		this.codTipoOperacao = codTipoOperacao;
	}

	public void setCodTipoLocalDestinatarioOper(
			Integer codTipoLocalDestinatarioOper) {
		this.codTipoLocalDestinatarioOper = codTipoLocalDestinatarioOper;
	}

	public void setCodFormImpressao(Integer codFormImpressao) {
		this.codFormImpressao = codFormImpressao;
	}

	public void setCodFormEmissao(Integer codFormEmissao) {
		this.codFormEmissao = codFormEmissao;
	}

	public void setCodFinalEmissao(Integer codFinalEmissao) {
		this.codFinalEmissao = codFinalEmissao;
	}

	public void setDigitoVerificadorChaveAcesso(
			Integer digitoVerificadorChaveAcesso) {
		this.digitoVerificadorChaveAcesso = digitoVerificadorChaveAcesso;
	}

	public void setTipoAmbienteEmissao(Integer tipoAmbienteEmissao) {
		this.tipoAmbienteEmissao = tipoAmbienteEmissao;
	}

	public void setCodMunicipioOcorFatoGeracao(
			Integer codMunicipioOcorFatoGeracao) {
		this.codMunicipioOcorFatoGeracao = codMunicipioOcorFatoGeracao;
	}

	public void setCodTipoOperConsFina(Integer codTipoOperConsFina) {
		this.codTipoOperConsFina = codTipoOperConsFina;
	}

	public void setCodTipoPresCOnsFina(Integer codTipoPresCOnsFina) {
		this.codTipoPresCOnsFina = codTipoPresCOnsFina;
	}

	public void setCodTipoProcEmissao(Integer codTipoProcEmissao) {
		this.codTipoProcEmissao = codTipoProcEmissao;
	}

	public void setVersaoProcEmissao(String versaoProcEmissao) {
		this.versaoProcEmissao = versaoProcEmissao;
	}

	public void setValorBaseCalcICMS(Double valorBaseCalcICMS) {
		this.valorBaseCalcICMS = valorBaseCalcICMS;
	}

	public void setValorTotalICMS(Double valorTotalICMS) {
		this.valorTotalICMS = valorTotalICMS;
	}

	public void setValorTotalICMSDes(Double valorTotalICMSDes) {
		this.valorTotalICMSDes = valorTotalICMSDes;
	}

	public void setValorBaseCalcICMSSt(Double valorBaseCalcICMSSt) {
		this.valorBaseCalcICMSSt = valorBaseCalcICMSSt;
	}

	public void setValorTotalICMSSt(Double valorTotalICMSSt) {
		this.valorTotalICMSSt = valorTotalICMSSt;
	}

	public void setValorTotalProdServ(Double valorTotalProdServ) {
		this.valorTotalProdServ = valorTotalProdServ;
	}

	public void setValorTotalFrete(Double valorTotalFrete) {
		this.valorTotalFrete = valorTotalFrete;
	}

	public void setValorTotalSeguro(Double valorTotalSeguro) {
		this.valorTotalSeguro = valorTotalSeguro;
	}

	public void setValorTotalDesc(Double valorTotalDesc) {
		this.valorTotalDesc = valorTotalDesc;
	}

	public void setValorTotalII(Double valorTotalII) {
		this.valorTotalII = valorTotalII;
	}

	public void setValorTotalIPI(Double valorTotalIPI) {
		this.valorTotalIPI = valorTotalIPI;
	}

	public void setValorTotalPIS(Double valorTotalPIS) {
		this.valorTotalPIS = valorTotalPIS;
	}

	public void setValorCofins(Double valorCofins) {
		this.valorCofins = valorCofins;
	}

	public void setValorTotalOutrasDespAces(Double valorTotalOutrasDespAces) {
		this.valorTotalOutrasDespAces = valorTotalOutrasDespAces;
	}

	public void setValorTotalNFe(Double valorTotalNFe) {
		this.valorTotalNFe = valorTotalNFe;
	}

	public void setValorTotalAproTrib(Double valorTotalAproTrib) {
		this.valorTotalAproTrib = valorTotalAproTrib;
	}

	public void setCodModalidadeFrete(Integer codModalidadeFrete) {
		this.codModalidadeFrete = codModalidadeFrete;
	}

	public void setSiglaPlacaTransportadora(String siglaPlacaTransportadora) {
		this.siglaPlacaTransportadora = siglaPlacaTransportadora;
	}

	public void setNumPlacaTransportadora(String numPlacaTransportadora) {
		this.numPlacaTransportadora = numPlacaTransportadora;
	}

	public void setCodigoFaturamento(String codigoFaturamento) {
		this.codigoFaturamento = codigoFaturamento;
	}

	public void setValorOrigFaturamento(Double valorOrigFaturamento) {
		this.valorOrigFaturamento = valorOrigFaturamento;
	}

	public void setValorDescFaturamento(Double valorDescFaturamento) {
		this.valorDescFaturamento = valorDescFaturamento;
	}

	public void setValorLiqFaturamento(Double valorLiqFaturamento) {
		this.valorLiqFaturamento = valorLiqFaturamento;
	}

	public void setInfoComplementar(String infoComplementar) {
		this.infoComplementar = infoComplementar;
	}

	public void setCodStatNF(Integer codStatNF) {
		this.codStatNF = codStatNF;
	}

	public Long getIdLogProcessamento() {
		return idLogProcessamento;
	}

	public void setIdLogProcessamento(Long idLogProcessamento) {
		this.idLogProcessamento = idLogProcessamento;
	}

}
