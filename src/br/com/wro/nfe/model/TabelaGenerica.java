package br.com.wro.nfe.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * <code>
 * <h1>TabelaGenerica</h1>
 * 
 * @author Felipe Caparelli<br>
 * @version 1.0<br>
 * @since 12/01/2015<br> </code>
 */
@Alias("tabelaGenerica")
public class TabelaGenerica implements Serializable {

	private static final long serialVersionUID = -1545604086241352113L;
	
	public Long ID_1;
	public Long ID_2;
	public Long ID_3;
	public Long ID_4;
	public String ID_STRI_1;
	public String ID_STRI_2;
	public Integer IN_1;
	public Integer IN_2;
	public Integer IN_3;
	public Integer IN_4;
	public Integer IN_5;
	public Integer IN_6;
	public Integer IN_7;
	public Integer IN_8;
	public String DS_1;
	public String DS_2;
	public String DS_3;
	public String DS_4;
	public Double VR_1;
	public Double VR_2;
	public Double VR_3;
	public Double VR_4;
	public Double VR_5;
	public Date DT_1;
	
	public TabelaGenerica() {}

	public Long getID_1() {
		return ID_1;
	}

	public void setID_1(Long iD_1) {
		ID_1 = iD_1;
	}

	public Long getID_2() {
		return ID_2;
	}

	public void setID_2(Long iD_2) {
		ID_2 = iD_2;
	}

	public Long getID_3() {
		return ID_3;
	}

	public void setID_3(Long iD_3) {
		ID_3 = iD_3;
	}

	public Long getID_4() {
		return ID_4;
	}

	public void setID_4(Long iD_4) {
		ID_4 = iD_4;
	}

	public String getID_STRI_1() {
		return ID_STRI_1;
	}

	public void setID_STRI_1(String iD_STRI_1) {
		ID_STRI_1 = iD_STRI_1;
	}

	public String getID_STRI_2() {
		return ID_STRI_2;
	}

	public void setID_STRI_2(String iD_STRI_2) {
		ID_STRI_2 = iD_STRI_2;
	}

	public Integer getIN_1() {
		return IN_1;
	}

	public void setIN_1(Integer iN_1) {
		IN_1 = iN_1;
	}

	public Integer getIN_2() {
		return IN_2;
	}

	public void setIN_2(Integer iN_2) {
		IN_2 = iN_2;
	}

	public Integer getIN_3() {
		return IN_3;
	}

	public void setIN_3(Integer iN_3) {
		IN_3 = iN_3;
	}

	public Integer getIN_4() {
		return IN_4;
	}

	public void setIN_4(Integer iN_4) {
		IN_4 = iN_4;
	}

	public Integer getIN_5() {
		return IN_5;
	}

	public void setIN_5(Integer iN_5) {
		IN_5 = iN_5;
	}

	public Integer getIN_6() {
		return IN_6;
	}

	public void setIN_6(Integer iN_6) {
		IN_6 = iN_6;
	}

	public Integer getIN_7() {
		return IN_7;
	}

	public void setIN_7(Integer iN_7) {
		IN_7 = iN_7;
	}

	public Integer getIN_8() {
		return IN_8;
	}

	public void setIN_8(Integer iN_8) {
		IN_8 = iN_8;
	}

	public String getDS_1() {
		return DS_1;
	}

	public void setDS_1(String dS_1) {
		DS_1 = dS_1;
	}

	public String getDS_2() {
		return DS_2;
	}

	public void setDS_2(String dS_2) {
		DS_2 = dS_2;
	}

	public String getDS_3() {
		return DS_3;
	}

	public void setDS_3(String dS_3) {
		DS_3 = dS_3;
	}

	public String getDS_4() {
		return DS_4;
	}

	public void setDS_4(String dS_4) {
		DS_4 = dS_4;
	}

	public Double getVR_1() {
		return VR_1;
	}

	public void setVR_1(Double vR_1) {
		VR_1 = vR_1;
	}

	public Double getVR_2() {
		return VR_2;
	}

	public void setVR_2(Double vR_2) {
		VR_2 = vR_2;
	}

	public Double getVR_3() {
		return VR_3;
	}

	public void setVR_3(Double vR_3) {
		VR_3 = vR_3;
	}

	public Double getVR_4() {
		return VR_4;
	}

	public void setVR_4(Double vR_4) {
		VR_4 = vR_4;
	}

	public Double getVR_5() {
		return VR_5;
	}

	public void setVR_5(Double vR_5) {
		VR_5 = vR_5;
	}

	public Date getDT_1() {
		return DT_1;
	}

	public java.sql.Timestamp getSqlDT_1(){
		if(getDT_1() != null){
			return new java.sql.Timestamp(getDT_1().getTime());
		}else{
			return null;
		}
	}
	
	public void setDT_1(Date dT_1) {
		DT_1 = dT_1;
	}
    

}
