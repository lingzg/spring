package com.lingzg.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lingzg.common.PageInfo;

public interface IBaseDao<E,PK extends Serializable> {
	
	List<E> findAll();
	
	List<E> findByProperty(String propertyName, Object value);
	
	List<E> findByProperties(String[] propertyNames, Object[] values);
	
	E get(PK id);

	void save(E entity);
	
	void update(E entity);
	
	void delete(PK id);
	
	void delete(E entity);
			
	List<?> findListBySql(String sql,Object[] params,Class<?> clazz);
		
	List<?> findListBySql(String sql,Object... params);
	
	List<Map<String, Object>> findMapListBySql(String sql,Object... params);
					
	void findPageBySql(PageInfo page, String sql, Object... params);
				
	boolean executeSql(String sql, Object... params);

}
