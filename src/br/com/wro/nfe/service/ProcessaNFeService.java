package br.com.wro.nfe.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import br.com.wro.nfe.dao.GenericDAO;
import br.com.wro.nfe.model.NfeVO;
import br.com.wro.nfe.mybatis.DBUtils;
import br.com.wro.nfe.mybatis.mapper.NfeMapper;

/**
 * Classe responsável por inserir a NFe no banco de dados.
 * 
 * @author 82004836
 *
 */
public class ProcessaNFeService extends BaseService {
	
	private static final Logger logger = Logger.getLogger(ProcessaNFeService.class);
	
	private static GenericDAO<NfeVO> nfeDAO;
	
	
	/**
	 * Persiste (insere/atualiza) a NFe no banco de dados.
	 * 
	 * @param nfe
	 * 
	 * @return true se inserida com sucesso
	 */
	protected static boolean registraNFe(NfeVO nfe) {
		
		SqlSession sqlSession = null;
		
		try {
			
			//abre uma sessao com o DB...		
			sqlSession = getSqlSessionFactory().openSession();
			
			nfeDAO = new GenericDAO<NfeVO>(sqlSession);
			
			//persiste a nfe no banco...
			nfeDAO.update(DBUtils.getNamedQuery(NfeMapper.class, "manterNfe"), nfe);
			
			//commit dos dados no DB
			sqlSession.commit();
			
			//concluiu com sucesso a inclusao da NFe...
			logger.info("NF-e processada com sucesso");	
			
			return true;
			
		} catch (Exception e) {
			//rollback dos banco no DB
			if(sqlSession != null) sqlSession.rollback();
			//erro no processamento da NFe...
			logger.error(e);
			
			return false;
		} finally {
			//fechar sessao
			if(sqlSession != null) {				
				sqlSession.close();
			}
		}
	}

}
