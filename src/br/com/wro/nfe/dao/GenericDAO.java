package br.com.wro.nfe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import br.com.wro.nfe.mybatis.filter.BaseFilter;

/**
 * <code>
 * <h1>Classe</h1>
 * <p>
 * 
 * Classe que realiza a comunicação com o Banco de dados através do MyBatis.<br>
 * @param <T> A classe a ser manipulada nas operações.
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
	
	// --- SqlSession que será utilizado por todos os métodos
	private SqlSession session; 
	
	// --- CONSTRUTORES
	
	/**
	 * <code>
	 * <h1>Construtor</h1>
	 * <p>
	 * 
	 * Construtor padrão da classe.
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
	
	// --- MÉTODOS
	
    /**
     * Descrição: Método que retorna uma coleção com o tipo Informado 
     * @param elementName nome do <select> a ser executado
     * @return a lista populada com o resultado da query 
     */
    public List<T> select(String elementName) throws Exception {
        /* Cria a lista antes da execução para que o Método não retorne NULL */ 
    	return this.session.selectList(elementName);
    }
    
    /**
     * Descrição: Método que retorna uma coleção com o tipo Informado 
     * @param elementName nome do <select> a ser executado
     * @return a lista populada com o resultado da query 
     */
    public List<T> select(String elementName, Object filter) throws Exception {
        return this.session.selectList(elementName,filter);
    }
    
    /**
     * Descrição: Recupera um cliente pelo seu e-mail
     * @param elementName o id do Select a ser executado
     * @return o cliente com os dados populados
     */
    @SuppressWarnings("unchecked")
	public T selectOne(String elementName, Object filter){
    	/* Executa a query e popula o objeto */
    	return (T) session.selectOne(elementName,filter);
    }
    
    /**
     * Descrição: Faz a inclusão de um item no banco de dados 
     * @param elementName Id do Elemento UPDATE do XML a ser executado
     * @param bean O filtro para a pesquisa
     */
    public void insert(String elementName,Object bean) throws Exception{
   		this.session.insert(elementName, bean);
    }
    
    
    /**
     * Descrição: Faz a atualização dos dados de um item na sessão
     * @param elementName Id do Elemento UPDATE do XML a ser executado
     * @param bean O filtro para a pesquisa
     */
    public void update(String elementName, Object bean)throws Exception{
    	/* Executa a query e popula o objeto */
    	this.session.update(elementName, bean);
    }
    
    /**
     * Descrição: Executa uma Function e retorna os seus resultados em uma lista 
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
    	/* Recupera a propriedade Resultado que contém os dados do cursor e a transforma em lista */
    	list = (List<T>) filtro.getResultado();
		/* Retorna os dados para o usuário  */
		return (list);
	}
}
