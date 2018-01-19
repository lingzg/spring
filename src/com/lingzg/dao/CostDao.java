package com.lingzg.dao;

import org.springframework.stereotype.Repository;

import com.lingzg.base.JdbcBaseDao;
import com.lingzg.entity.Cost;

@Repository
public class CostDao extends JdbcBaseDao<Cost, Integer> {

	@Override
	protected Class<?> getEntityClass() {
		return Cost.class;
	}
	
	@Override
	protected String getTableName() {
		return Cost.getTableName();
	}
	
	@Override
	protected String[][] getCloumnFields() {
		return new String[][]{Cost.getCloumnNames(),Cost.getFieldNames()};
	}
	
}
