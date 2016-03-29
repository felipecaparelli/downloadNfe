package br.com.wro.nfe.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import br.com.wro.nfe.model.TabelaGenerica;

public class TabelaGenericaTypeHandler implements TypeHandler<Object> {
	
	private static final int TOTAL_CAMPOS = 24;
	private static final String REGISTRO = "TP_GENE";
    private static final String TABELA    = "TP_TABL_GENE";
    

	@Override
	public void setParameter(PreparedStatement stat, int columnIndex, Object param, JdbcType tipo) throws SQLException {
		
        try {
        	
            List<?> list = (List<?>) param;
            Connection conn = stat.getConnection().getMetaData().getConnection();

            StructDescriptor structDescriptor = StructDescriptor.createDescriptor(REGISTRO, conn);
            ArrayDescriptor arrayDescriptor = ArrayDescriptor.createDescriptor(TABELA, conn);

            STRUCT[] structArray;
            
            if (list == null) {
            	
                structArray = new STRUCT[0];
                
            } else {
            	
                structArray = new STRUCT[list.size()];

                for (int i = 0; i < structArray.length; i++) {
                	
                    Object[] propertiesArray = new Object[TOTAL_CAMPOS];
                    TabelaGenerica obj;
                    try {
                        obj = (TabelaGenerica) list.get(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }

                    propertiesArray[0] = obj.getID_1();
                    propertiesArray[1] = obj.getID_2();
                    propertiesArray[2] = obj.getID_3();
                    propertiesArray[3] = obj.getID_4();
                    propertiesArray[4] = obj.getID_STRI_1();
                    propertiesArray[5] = obj.getID_STRI_2();
                    propertiesArray[6] = obj.getIN_1();
                    propertiesArray[7] = obj.getIN_2();
                    propertiesArray[8] = obj.getIN_3();
                    propertiesArray[9] = obj.getIN_4();
                    propertiesArray[10] = obj.getIN_5();
                    propertiesArray[11] = obj.getIN_6();
                    propertiesArray[12] = obj.getIN_7();
                    propertiesArray[13] = obj.getIN_8();
                    propertiesArray[14] = obj.getDS_1();
                    propertiesArray[15] = obj.getDS_2();
                    propertiesArray[16] = obj.getDS_3();
                    propertiesArray[17] = obj.getDS_4();
                    propertiesArray[18] = obj.getVR_1();
                    propertiesArray[19] = obj.getVR_2();
                    propertiesArray[20] = obj.getVR_3();
                    propertiesArray[21] = obj.getVR_4();
                    propertiesArray[22] = obj.getVR_5();
                    propertiesArray[23] = obj.getSqlDT_1();

                    structArray[i] = new STRUCT(structDescriptor, conn, propertiesArray);
                }
            }
            
            stat.setArray(columnIndex, new ARRAY(arrayDescriptor, conn, structArray));
            
        } catch (SQLException sqle) {
            throw sqle;
        }
    }
	
	@Override
	public Object getResult(ResultSet rs, String columnName) throws SQLException {		
		return rs.getObject(columnName);
	}

	@Override
	public Object getResult(ResultSet rs, int idx) throws SQLException {
		return rs.getArray(idx).getArray();
	}

	@Override
	public Object getResult(CallableStatement stat, int idx) throws SQLException {
		return stat.getArray(idx).getArray();
	}
	
}
