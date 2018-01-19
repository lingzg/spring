package com.lingzg.dao;

import org.springframework.stereotype.Repository;

import com.lingzg.base.JdbcBaseDao;
import com.lingzg.entity.Emp;

@Repository
public class EmpDao extends JdbcBaseDao<Emp, Integer> {

	@Override
	protected Class<?> getEntityClass() {
		return Emp.class;
	}
	
	@Override
	protected String getTableName() {
		return Emp.getTableName();
	}
	
	@Override
	protected String[][] getCloumnFields() {
		return new String[][]{Emp.getCloumnNames(),Emp.getFieldNames()};
	}

}
