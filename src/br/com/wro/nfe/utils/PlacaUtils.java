package br.com.wro.nfe.utils;

/**
 * <code>
 * <h1>PlacaUtils</h1>
 * 
 * Pacote: 		br.com.wro.nfe.utils<br>
 * Descrição: 	Classe utilitária para tratar dados de placa de veículo.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	07/05/2015<br>
 * </code>
 */
public class PlacaUtils {
	
	/**
	 * <code>
	 * Descrição: 	Recupera a sigla da placa informada.<br>
	 * @since 		07/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		placa
	 * @return 		String<br>
	 * </code>
	 */
	public static String getSiglaPlaca(String placa) {
		return removeDigits(placa);
	}
	
	/**
	 * <code>
	 * Descrição: 	Recupera o número da placa informada.<br>
	 * @since 		07/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		placa
	 * @return 		Long<br>
	 * </code>
	 */
	public static Long getNumPlaca(String placa) {
		Long numPlaca = null;
		String numPlacaS = removeNonDigits(placa);
		if(numPlacaS != null && !numPlacaS.isEmpty())
			numPlaca = Long.parseLong(numPlacaS);
		return numPlaca;
	}
	
	private static String removeDigits(final String str) {
	   if (str == null || str.length() == 0) {
	       return null;
	   }
	   return str.replaceAll("[0-9]+", "");
	}
	
	private static String removeNonDigits(final String str) {
	   if (str == null || str.length() == 0) {
	       return null;
	   }
	   return str.replaceAll("[^0-9]+", "");
	}

}
