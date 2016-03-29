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

import br.com.wro.nfe.model.TabelaGenerica3;

public class TabelaGenerica3TypeHandler implements TypeHandler<Object> {
	
	private static final int TOTAL_CAMPOS = 90;
	private static final String REGISTRO = "TP_GENE_3";
    private static final String TABELA    = "TP_TABL_GENE_3";


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
                    TabelaGenerica3 obj;
                    try {
                        obj = (TabelaGenerica3) list.get(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                    
                    int index = 0;

                    propertiesArray[index++] = obj.getID_1();
                    propertiesArray[index++] = obj.getID_2();
                    propertiesArray[index++] = obj.getID_3();
                    propertiesArray[index++] = obj.getID_4();
                    propertiesArray[index++] = obj.getID_5();
                    propertiesArray[index++] = obj.getID_6();
                    propertiesArray[index++] = obj.getID_7();
                    propertiesArray[index++] = obj.getID_8();
                    propertiesArray[index++] = obj.getID_9();
                    propertiesArray[index++] = obj.getID_10();
                    propertiesArray[index++] = obj.getID_11();
                    propertiesArray[index++] = obj.getID_12();
                    propertiesArray[index++] = obj.getID_13();
                    propertiesArray[index++] = obj.getID_14();
                    propertiesArray[index++] = obj.getID_15();
                    propertiesArray[index++] = obj.getID_STRI_1();
                    propertiesArray[index++] = obj.getID_STRI_2();
                    propertiesArray[index++] = obj.getID_STRI_3();
                    propertiesArray[index++] = obj.getID_STRI_4();
                    propertiesArray[index++] = obj.getID_STRI_5();
                    propertiesArray[index++] = obj.getID_STRI_6();
                    propertiesArray[index++] = obj.getID_STRI_7();
                    propertiesArray[index++] = obj.getID_STRI_8();
                    propertiesArray[index++] = obj.getID_STRI_9();
                    propertiesArray[index++] = obj.getID_STRI_10();
                    propertiesArray[index++] = obj.getID_STRI_11();
                    propertiesArray[index++] = obj.getID_STRI_12();
                    propertiesArray[index++] = obj.getID_STRI_13();
                    propertiesArray[index++] = obj.getID_STRI_14();
                    propertiesArray[index++] = obj.getID_STRI_15();
                    propertiesArray[index++] = obj.getIN_1();
                    propertiesArray[index++] = obj.getIN_2();
                    propertiesArray[index++] = obj.getIN_3();
                    propertiesArray[index++] = obj.getIN_4();
                    propertiesArray[index++] = obj.getIN_5();
                    propertiesArray[index++] = obj.getIN_6();
                    propertiesArray[index++] = obj.getIN_7();
                    propertiesArray[index++] = obj.getIN_8();
                    propertiesArray[index++] = obj.getIN_9();
                    propertiesArray[index++] = obj.getIN_10();
                    propertiesArray[index++] = obj.getIN_11();
                    propertiesArray[index++] = obj.getIN_12();
                    propertiesArray[index++] = obj.getIN_13();
                    propertiesArray[index++] = obj.getIN_14();
                    propertiesArray[index++] = obj.getIN_15();
                    propertiesArray[index++] = obj.getDS_1();
                    propertiesArray[index++] = obj.getDS_2();
                    propertiesArray[index++] = obj.getDS_3();
                    propertiesArray[index++] = obj.getDS_4();
                    propertiesArray[index++] = obj.getDS_5();
                    propertiesArray[index++] = obj.getDS_6();
                    propertiesArray[index++] = obj.getDS_7();
                    propertiesArray[index++] = obj.getDS_8();
                    propertiesArray[index++] = obj.getDS_9();
                    propertiesArray[index++] = obj.getDS_10();
                    propertiesArray[index++] = obj.getDS_11();
                    propertiesArray[index++] = obj.getDS_12();
                    propertiesArray[index++] = obj.getDS_13();
                    propertiesArray[index++] = obj.getDS_14();
                    propertiesArray[index++] = obj.getDS_15();
                    propertiesArray[index++] = obj.getVR_1();
                    propertiesArray[index++] = obj.getVR_2();
                    propertiesArray[index++] = obj.getVR_3();
                    propertiesArray[index++] = obj.getVR_4();
                    propertiesArray[index++] = obj.getVR_5();
                    propertiesArray[index++] = obj.getVR_6();
                    propertiesArray[index++] = obj.getVR_7();
                    propertiesArray[index++] = obj.getVR_8();
                    propertiesArray[index++] = obj.getVR_9();
                    propertiesArray[index++] = obj.getVR_10();
                    propertiesArray[index++] = obj.getVR_11();
                    propertiesArray[index++] = obj.getVR_12();
                    propertiesArray[index++] = obj.getVR_13();
                    propertiesArray[index++] = obj.getVR_14();
                    propertiesArray[index++] = obj.getVR_15();
                    propertiesArray[index++] = obj.getSqlDT_1();
                    propertiesArray[index++] = obj.getSqlDT_2();
                    propertiesArray[index++] = obj.getSqlDT_3();
                    propertiesArray[index++] = obj.getSqlDT_4();
                    propertiesArray[index++] = obj.getSqlDT_5();
                    propertiesArray[index++] = obj.getSqlDT_6();
                    propertiesArray[index++] = obj.getSqlDT_7();
                    propertiesArray[index++] = obj.getSqlDT_8();
                    propertiesArray[index++] = obj.getSqlDT_9();
                    propertiesArray[index++] = obj.getSqlDT_10();
                    propertiesArray[index++] = obj.getSqlDT_11();
                    propertiesArray[index++] = obj.getSqlDT_12();
                    propertiesArray[index++] = obj.getSqlDT_13();
                    propertiesArray[index++] = obj.getSqlDT_14();
                    propertiesArray[index++] = obj.getSqlDT_15();

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
