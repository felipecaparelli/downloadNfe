package br.com.wro.nfe.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import br.com.wro.nfe.enums.IndicadorStatusProcNFe;

@Alias("logProcessoNfe")
public class LogProcessoNfe implements Serializable {
	
	private static final long serialVersionUID = -144972085880065984L;
	
	private Long id;							
	private String codNSU;						
	private Date dtInicioProcessamento;			
	private Date dtFimProcessamento;			 
	private String chaveAcessoNFe;				
	private String descricaoErro;				
	private IndicadorStatusProcNFe indStatus; 	
	
	
	public LogProcessoNfe() {}
	
	public LogProcessoNfe(Long idProc, String codNSU, IndicadorStatusProcNFe indStaProc) {
		this.id = idProc;
		this.codNSU = codNSU;
		this.indStatus = indStaProc;
	}
	
	/**
	 * Objeto parametrizado.
	 * 
	 * @param codNSU
	 * @param chaveAcessoNFe
	 * @param descricaoErro
	 * @param indStatus
	 */
	public LogProcessoNfe(Long idProcesso, String codNSU, String chaveAcessoNFe, String descricaoErro, IndicadorStatusProcNFe indStatus) {
		this.id = idProcesso;
		this.codNSU = codNSU;
		this.chaveAcessoNFe = chaveAcessoNFe;
		this.descricaoErro = descricaoErro;
		this.indStatus = indStatus;
	}
	
	
	/* getters and setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodNSU() {
		return codNSU;
	}

	public void setCodNSU(String codNSU) {
		this.codNSU = codNSU;
	}

	public Date getDtInicioProcessamento() {
		return dtInicioProcessamento;
	}

	public void setDtInicioProcessamento(Date dtInicioProcessamento) {
		this.dtInicioProcessamento = dtInicioProcessamento;
	}

	public Date getDtFimProcessamento() {
		return dtFimProcessamento;
	}

	public void setDtFimProcessamento(Date dtFimProcessamento) {
		this.dtFimProcessamento = dtFimProcessamento;
	}

	public String getChaveAcessoNFe() {
		return chaveAcessoNFe;
	}

	public void setChaveAcessoNFe(String chaveAcessoNFe) {
		this.chaveAcessoNFe = chaveAcessoNFe;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	public IndicadorStatusProcNFe getIndStatus() {
		return indStatus;
	}

	public void setIndStatus(IndicadorStatusProcNFe indStatus) {
		this.indStatus = indStatus;
	}

}
