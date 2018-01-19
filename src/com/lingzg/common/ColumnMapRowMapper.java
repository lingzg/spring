package com.lingzg.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

public class ColumnMapRowMapper implements RowMapper<Map<String, Object>>{

	@Override
	  public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException
	    {
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();
	        Map<String, Object> mapOfColValues = createColumnMap(columnCount);
	        for (int i = 1; i <= columnCount; i++)
	        {
	            String key = getColumnKey(rsmd.getColumnName(i));
	            Object obj = getColumnValue(rs, i);
	            mapOfColValues.put(key.toLowerCase(), obj);
	        }
	        return mapOfColValues;
	    }
	
	   protected Map<String, Object> createColumnMap(int columnCount)
	    {
	        return new LinkedHashMap<String, Object>(columnCount);
	    }
	   
	   protected String getColumnKey(String columnName)
	    {
	        return columnName;
	    }
	   protected Object getColumnValue(ResultSet rs, int index)
	            throws SQLException
	    {
	        return JdbcUtils.getResultSetValue(rs, index);
	    }

}
