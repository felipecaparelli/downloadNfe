package br.com.wro.nfe.mybatis;


/**
 * <code>
 * <h1>DBUtils</h1>
 * Descrição: 	Classe utilitaria relativa as configurações do mybatis.<br>
 * Projeto: 	Siscorp-Services<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	05/01/2015<br>
 * </code>
 */
public class DBUtils {
	
	private static final String PCK = DBUtils.class.getPackage().getName();
	private static final String SEPARATOR = ".";
	
	/**
	 * <code>
	 * Descrição: 	Retorna a named query que referencia o serviço mapeado no MyBatis (mapper).<br>
	 * @param 		mapperClass
	 * @param 		queryName
	 * @since 		05/01/2015<br>
	 * @return 		String<br>
	 * </code>
	 */
	public static String getNamedQuery(Class<?> mapperClass, String queryName) {
		
		String mapper = mapperClass.getName() + SEPARATOR + queryName;
		
		if(mapper == null || !mapper.contains(PCK)) throw new IllegalArgumentException("Classe inválida ou não mapeada: " + mapper);
		
		return mapper;
	}

}
