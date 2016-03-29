package br.com.wro.nfe.mybatis.filter;

import java.io.Serializable;
import java.util.List;

/**
 * <code>
 * Descrição: 	Filtro Base para pesquisa no Banco de dados.<br>
 * Projeto:		Siscorp<br>
 * @author 		Rudilei Douglas Mendes<br>
 * @version 	1.0<br>
 * @since 		10/07/2014<br>
 * </code>
 */
public class BaseFilter implements Serializable
{
	//--------------------------------------------------//
    //---              PROPRIEDADES                  ---//
    //--------------------------------------------------//
	
	private static final long serialVersionUID = -3421784871093395110L;
	private List<?> resultado;
	private Object retornoUnico;
	
	//--------------------------------------------------//
    //---              MÉTODOS GET/SET               ---//
    //--------------------------------------------------//
	
	public List<?> getResultado() {
		return resultado;
	}
	
	public void setResultado(List<?> resultado) {
		this.resultado = resultado;
	}
	
	public Object getRetornoUnico() {
		return retornoUnico;
	}
	
	public void setRetornoUnico(Object retornoUnico) {
		this.retornoUnico = retornoUnico;
	}
}
