package com.lingzg.common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;

import com.lingzg.base.BaseEntity;
import com.lingzg.util.BeanUtil;

public class EntityRowMapper implements RowMapper<BaseEntity> {

	private Class<?> clazz;

	public EntityRowMapper(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Override
	public BaseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		BaseEntity bean = null;
		try {
			bean = (BaseEntity) clazz.newInstance();
			for (int i = 1; i <= columnCount; i++) {
				String key = getColumnKey(rsmd.getColumnName(i));
				Object obj = getColumnValue(rs, i);
				BeanUtil.setfieldValue(bean, key, obj);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return bean;
	}

	protected String getColumnKey(String columnName) {
		return BeanUtil.column2field(clazz, columnName);
	}

	protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index);
	}

}
