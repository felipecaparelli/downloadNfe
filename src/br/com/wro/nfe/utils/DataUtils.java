package br.com.wro.nfe.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <code>
 * <h1>DataUtils</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Classe utilitária para formatar datas (retornadas nas NFe's).<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	08/05/2015<br>
 * </code>
 */
public class DataUtils {
	
	private static final String dtFormat_AAAA_MM_DD = "YYYY-MM-dd";	
	private static final String dtFormat_AAMM = "YYMM";
	private static final String dtFormat_AAAA_MM_DDThh_mm_ss = "YYYY-MM-dd'T'HH:mm:ss";
	private static final String dtFormat_AAAA_MM_DDThh_mm_ss_TZ = "YYYY-MM-dd'T'HH:mm:ss.SSSZ";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(dtFormat_AAAA_MM_DD);
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat(dtFormat_AAMM);
	private static final SimpleDateFormat sdfUTC = new SimpleDateFormat(dtFormat_AAAA_MM_DDThh_mm_ss);
	private static final SimpleDateFormat sdfUTC_TZ = new SimpleDateFormat(dtFormat_AAAA_MM_DDThh_mm_ss_TZ);
	
	/**
	 * <code>
	 * Descrição: 	Retorna a data no formato AAAA-MM-DD.<br>
	 * @since 		07/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param dateS
	 * @return 		Date<br>
	 * </code>
	 */
	public static Date getDateAAAA_MM_DD(String dateS) {
		if(dateS == null || dateS.trim().isEmpty()) return null;
		try {
			return sdf.parse(dateS);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * <code>
	 * Descrição: 	Retorna a data no formato AAAAMM.<br>
	 * @since 		08/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		dateS
	 * @return 		Date<br>
	 * </code>
	 */
	public static Date getDateAAMM(String dateS) {
		if(dateS == null || dateS.trim().isEmpty()) return null;
		try {
			return sdf2.parse(dateS);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * <code>
	 * Descrição: 	Retorna a data no formato AAAA-MM-DDThh:mm:ss.<br>
	 * @since 		07/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		dateS
	 * @return 		Date<br>
	 * </code>
	 */
	public static Date getDateAAAA_MM_DDThh_mm_ss(String dateS){
		if(dateS == null || dateS.trim().isEmpty()) return null;
		try {
			return sdfUTC.parse(dateS);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * <code>
	 * Descrição: 	Retorna a data no formato AAAA-MM-DD'T'HH:mm:ss.Timezone.<br>
	 * @since 		08/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		dateS
	 * @return 		Date<br>
	 * </code>
	 */
	public static Date getDateAAAA_MM_DDThh_mm_ss_TZ(String dateS){
		if(dateS == null || dateS.trim().isEmpty()) return null;
		try {
			return sdfUTC_TZ.parse(dateS);
		} catch (ParseException e) {
			return null;
		}
	}

}
