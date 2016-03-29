package br.com.wro.nfe.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import br.com.wro.nfe.enums.IndicadorStatus;

@Alias("indicadorStatusTypeHandler")
@MappedTypes(IndicadorStatus.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class IndicadorStatusTypeHandler implements TypeHandler<Object> {
	
	public IndicadorStatusTypeHandler() {}
	
	
	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setNull(i, Types.INTEGER);
		} else {
			IndicadorStatus indicador = (IndicadorStatus) parameter;
			ps.setInt(i, indicador.getValue());
		}
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		return IndicadorStatus.get(rs.getInt(columnName));
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		return IndicadorStatus.get(rs.getInt(columnIndex));
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return IndicadorStatus.get(cs.getInt(columnIndex));
	}

}
