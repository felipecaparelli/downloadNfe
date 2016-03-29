package br.com.wro.nfe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import br.com.wro.nfe.mybatis.filter.BaseFilter;

/**
 * <code>
 * <h1>Classe</h1>
 * <p>
 * 
 * Classe que realiza a comunica��o com o Banco de dados atrav�s do MyBatis.<br>
 * @param <T> A classe a ser manipulada nas opera��es.
 * 
 * @project Siscorp-Services
 * @author  Everton da Silva Rodrigues
 * @version 1.0
 * @since   08/07/2014
 * </code>
 */
public class GenericDAO<T>
{
	// --- PROPRIEDADES
	
	// --- SqlSession que ser� utilizado por todos os m�todos
	private SqlSession session; 
	
	// --- CONSTRUTORES
	
	/**
	 * <code>
	 * <h1>Construtor</h1>
	 * <p>
	 * 
	 * Construtor padr�o da classe.
	 * 
	 * @author  Everton da Silva Rodrigues
	 * @version 1.0
	 * @since   08/07/2014
	 * @param 	_session
	 * </code>
	 */
	public GenericDAO(SqlSession _session)
	{
		this.session = _session;
	}
	
	// --- M�TODOS
	
    /**
     * Descri��o: M�todo que retorna uma cole��o com o tipo Informado 
     * @param elementName nome do <select> a ser executado
     * @return a lista populada com o resultado da query 
     */
    public List<T> select(String elementName) throws Exception {
        /* Cria a lista antes da execu��o para que o M�todo n�o retorne NULL */ 
    	return this.session.selectList(elementName);
    }
    
    /**
     * Descri��o: M�todo que retorna uma cole��o com o tipo Informado 
     * @param elementName nome do <select> a ser executado
     * @return a lista populada com o resultado da query 
     */
    public List<T> select(String elementName, Object filter) throws Exception {
        return this.session.selectList(elementName,filter);
    }
    
    /**
     * Descri��o: Recupera um cliente pelo seu e-mail
     * @param elementName o id do Select a ser executado
     * @return o cliente com os dados populados
     */
    @SuppressWarnings("unchecked")
	public T selectOne(String elementName, Object filter){
    	/* Executa a query e popula o objeto */
    	return (T) session.selectOne(elementName,filter);
    }
    
    /**
     * Descri��o: Faz a inclus�o de um item no banco de dados 
     * @param elementName Id do Elemento UPDATE do XML a ser executado
     * @param bean O filtro para a pesquisa
     */
    public void insert(String elementName,Object bean) throws Exception{
   		this.session.insert(elementName, bean);
    }
    
    
    /**
     * Descri��o: Faz a atualiza��o dos dados de um item na sess�o
     * @param elementName Id do Elemento UPDATE do XML a ser executado
     * @param bean O filtro para a pesquisa
     */
    public void update(String elementName, Object bean)throws Exception{
    	/* Executa a query e popula o objeto */
    	this.session.update(elementName, bean);
    }
    
    /**
     * Descri��o: Executa uma Function e retorna os seus resultados em uma lista 
     * @param elementName Id do Elemento do XML a ser executado
     * @param filtro	  O filtro para pesquisa
     * @return			  A lista com os dados populados
     * @throws Exception 
     */
    @SuppressWarnings("unchecked")
    public List<T> list(String elementName, BaseFilter filtro) throws Exception{
    	List<T> list = null;
    	/* Executa o elemento */
    	this.session.selectList(elementName, filtro);
    	/* Recupera a propriedade Resultado que cont�m os dados do cursor e a transforma em lista */
    	list = (List<T>) filtro.getResultado();
		/* Retorna os dados para o usu�rio  */
		return (list);
	}
}
