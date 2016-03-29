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

import br.com.wro.nfe.enums.IndicadorSN;

@Alias("indicadorSNTypeHandler")
@MappedTypes(IndicadorSN.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class IndicadorSNTypeHandler implements TypeHandler<Object> {
	
	public IndicadorSNTypeHandler() {}

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			ps.setNull(i, Types.INTEGER);
		} else {
			IndicadorSN indicador = (IndicadorSN) parameter;
			ps.setInt(i, indicador.getValor());
		}
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		return IndicadorSN.get(rs.getInt(columnName));
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		return IndicadorSN.get(rs.getInt(columnIndex));
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return IndicadorSN.get(cs.getInt(columnIndex));
	}
}