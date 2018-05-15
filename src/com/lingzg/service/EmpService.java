package com.lingzg.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lingzg.base.BaseService;
import com.lingzg.base.IBaseDao;
import com.lingzg.dao.EmpDao;
import com.lingzg.entity.Emp;

@Service
public class EmpService extends BaseService<Emp, Integer>{

	@Resource
	private EmpDao dao;
	
	@Override
	protected IBaseDao<Emp, Integer> getEntityDao() {
		return dao;
	}
	
}
