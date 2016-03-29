package br.com.wro.nfe.mybatis.filter;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import br.com.wro.nfe.enums.IndicadorSN;

@Alias("logProcessoNfeFilter")
public class LogProcessoNfeFilter extends BaseFilter {

	private static final long serialVersionUID = 2481635183914942548L;
	
	/* Parâmetros */
	private Long idLogProcessoNfe; 
	private Date dtInicioProcessamento;
	private Date dtFimProcessamento;
	private String codNSU;
	private String chaveAcessoNFe;
	private Long codNfeInterno; 
	private String codNfe; 
	private Integer codTipoAcao;
	private IndicadorSN indUltIniciado; //Indicador de ultimo processo iniciado (Não Obrigatório)(Quando especificado deve ser 1 - Sim ou 2 - Não)
	private IndicadorSN indUltFinalizado; //Indicador de ultimo processo finalizado sem erro (Não Obrigatório)(Quando especificado deve ser 1 - Sim ou 2 - Não) 
	private IndicadorSN indUltErro; //Indicador de ultimo processo finalizado com erro (Não Obrigatório)(Quando especificado deve ser 1 - Sim ou 2 - Não)

	/* getters and setters */

	public Long getIdLogProcessoNfe() {
		return idLogProcessoNfe;
	}

	public Date getDtInicioProcessamento() {
		return dtInicioProcessamento;
	}

	public Date getDtFimProcessamento() {
		return dtFimProcessamento;
	}

	public String getCodNSU() {
		return codNSU;
	}

	public String getChaveAcessoNFe() {
		return chaveAcessoNFe;
	}

	public Long getCodNfeInterno() {
		return codNfeInterno;
	}

	public String getCodNfe() {
		return codNfe;
	}

	public Integer getCodTipoAcao() {
		return codTipoAcao;
	}

	public IndicadorSN getIndUltIniciado() {
		return indUltIniciado;
	}

	public IndicadorSN getIndUltFinalizado() {
		return indUltFinalizado;
	}

	public IndicadorSN getIndUltErro() {
		return indUltErro;
	}

	public void setIdLogProcessoNfe(Long idLogProcessoNfe) {
		this.idLogProcessoNfe = idLogProcessoNfe;
	}

	public void setDtInicioProcessamento(Date dtInicioProcessamento) {
		this.dtInicioProcessamento = dtInicioProcessamento;
	}

	public void setDtFimProcessamento(Date dtFimProcessamento) {
		this.dtFimProcessamento = dtFimProcessamento;
	}

	public void setCodNSU(String codNSU) {
		this.codNSU = codNSU;
	}

	public void setChaveAcessoNFe(String chaveAcessoNFe) {
		this.chaveAcessoNFe = chaveAcessoNFe;
	}

	public void setCodNfeInterno(Long codNfeInterno) {
		this.codNfeInterno = codNfeInterno;
	}

	public void setCodNfe(String codNfe) {
		this.codNfe = codNfe;
	}

	public void setCodTipoAcao(Integer codTipoAcao) {
		this.codTipoAcao = codTipoAcao;
	}

	public void setIndUltIniciado(IndicadorSN indUltIniciado) {
		this.indUltIniciado = indUltIniciado;
	}

	public void setIndUltFinalizado(IndicadorSN indUltFinalizado) {
		this.indUltFinalizado = indUltFinalizado;
	}

	public void setIndUltErro(IndicadorSN indUltErro) {
		this.indUltErro = indUltErro;
	}

	public static class Builder {
		
		private final LogProcessoNfeFilter logProcessoNfeFilter;
		
		public Builder() {
			this.logProcessoNfeFilter = new LogProcessoNfeFilter();
		}
		
		public Builder setIdLogProcessoNfe(Long idLogProcessoNfe) {
			this.logProcessoNfeFilter.idLogProcessoNfe = idLogProcessoNfe;
			return this;
		}

		public Builder setDtInicioProcessamento(Date dtInicioProcessamento) {
			this.logProcessoNfeFilter.dtInicioProcessamento = dtInicioProcessamento;
			return this;
		}

		public Builder setDtFimProcessamento(Date dtFimProcessamento) {
			this.logProcessoNfeFilter.dtFimProcessamento = dtFimProcessamento;
			return this;
		}

		public Builder setCodNSU(String codNSU) {
			this.logProcessoNfeFilter.codNSU = codNSU;
			return this;
		}

		public Builder setChaveAcessoNFe(String chaveAcessoNFe) {
			this.logProcessoNfeFilter.chaveAcessoNFe = chaveAcessoNFe;
			return this;
		}

		public Builder setCodNfeInterno(Long codNfeInterno) {
			this.logProcessoNfeFilter.codNfeInterno = codNfeInterno;
			return this;
		}

		public Builder setCodNfe(String codNfe) {
			this.logProcessoNfeFilter.codNfe = codNfe;
			return this;
		}

		public Builder setCodTipoAcao(Integer codTipoAcao) {
			this.logProcessoNfeFilter.codTipoAcao = codTipoAcao;
			return this;
		}

		public Builder setIndUltIniciado(IndicadorSN indUltIniciado) {
			this.logProcessoNfeFilter.indUltIniciado = indUltIniciado;
			return this;
		}

		public Builder setIndUltFinalizado(IndicadorSN indUltFinalizado) {
			this.logProcessoNfeFilter.indUltFinalizado = indUltFinalizado;
			return this;
		}

		public Builder setIndUltErro(IndicadorSN indUltErro) {
			this.logProcessoNfeFilter.indUltErro = indUltErro;
			return this;
		}
		
		public LogProcessoNfeFilter build() {
			return this.logProcessoNfeFilter;
		}
	}

}
