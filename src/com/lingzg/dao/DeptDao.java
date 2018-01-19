package com.lingzg.dao;

import org.springframework.stereotype.Repository;

import com.lingzg.base.JdbcBaseDao;
import com.lingzg.entity.Dept;

@Repository
public class DeptDao extends JdbcBaseDao<Dept, Integer> {

	@Override
	protected Class<?> getEntityClass() {
		return Dept.class;
	}
	
	@Override
	protected String getTableName() {
		return Dept.getTableName();
	}
	
	@Override
	protected String[][] getCloumnFields() {
		return new String[][]{Dept.getCloumnNames(),Dept.getFieldNames()};
	}
	
}
