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

@Alias("booleanTypeHandler")
@MappedTypes(Boolean.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class BooleanTypeHandler implements TypeHandler<Object>
{
	public BooleanTypeHandler()
	{
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
	{
		if (parameter == null)
		{
			ps.setNull(i, Types.INTEGER);
		}
		else
		{
			Boolean value = (Boolean) parameter;
			if (value)
			{
				ps.setInt(i, 1);
			}
			else if (!value)
			{
				ps.setInt(i, 2);
			}
		}
	}

	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException
	{
		Integer value = rs.getInt(columnName);
		if (value != null)
		{
			if (value.equals(1))
			{
				return new Boolean(true);
			}
			else if (value.equals(2))
			{
				return new Boolean(false);
			}
		}
		return null;
	}

	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException
	{
		Integer value = rs.getInt(columnIndex);
		if (value != null)
		{
			if (value.equals(1))
			{
				return new Boolean(true);
			}
			else if (value.equals(2))
			{
				return new Boolean(false);
			}
		}
		return null;
	}

	@Override
	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException
	{
		Integer value = cs.getInt(columnIndex);
		if (value != null)
		{
			if (value.equals(1))
			{
				return new Boolean(true);
			}
			else if (value.equals(2))
			{
				return new Boolean(false);
			}
		}
		return null;
	}
}
