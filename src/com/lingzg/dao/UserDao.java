package com.lingzg.dao;

import org.springframework.stereotype.Repository;

import com.lingzg.base.JdbcBaseDao;
import com.lingzg.entity.User;

@Repository
public class UserDao extends JdbcBaseDao<User, Integer> {

	@Override
	protected Class<?> getEntityClass() {
		return User.class;
	}
	
	@Override
	protected String getTableName() {
		return User.getTableName();
	}
	
	@Override
	protected String[][] getCloumnFields() {
		return new String[][]{User.getCloumnNames(),User.getFieldNames()};
	}

}
