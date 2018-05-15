package com.lingzg.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lingzg.common.PageInfo;
import com.lingzg.dao.CostDao;
import com.lingzg.dao.EmpDao;
import com.lingzg.entity.Cost;
import com.lingzg.entity.Emp;

public class TestCase {

//	@Test
	public void test1(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CostDao dao = ctx.getBean("costDao", CostDao.class);
		List<Cost> list = dao.findAll();
		for(Cost c : list) {
			System.out.println(c.getCostId() + " " + c.getName());
		}
		System.out.println("--------------");
//		Cost cost = dao.get(10);
//		System.out.println(cost.getCostId() + " " + cost.getName());
//		cost.setName("80元套餐");
//		dao.update(cost);
//		System.out.println(cost.getCostId() + " " + cost.getName());
		
//		dao.delete(10);
		
//		Cost c = new Cost();
//		c.setName("Tarena套餐");
//		c.setBaseDuration(80);
//		c.setBaseCost(80.0);
//		c.setUnitCost(0.8);
//		c.setStatus("0");
//		c.setDescr("Tarena");
//		c.setCreatime(new Timestamp(System.currentTimeMillis()));
//		c.setStartime(new Timestamp(System.currentTimeMillis()));
//		c.setCostType("2");
//		dao.save(c);
	}
	
//	@Test
	public void test2(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		EmpDao dao = ctx.getBean("empDao", EmpDao.class);
		List<Emp> list = dao.findAll();
		for(Emp e : list) {
			System.out.println(e.getEmpno() + " " + e.getEname());
		}
		System.out.println("--------------");
		Emp e = dao.get(1);
		System.out.println(e.getEmpno() + " " + e.getEname());
	}
	
	@Test
	public void test3(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CostDao dao = ctx.getBean("costDao", CostDao.class);
		PageInfo page = new PageInfo();
		dao.findPageBySql(page,"select * from cost ",Cost.class);
		System.out.println(page.getTotal());
		System.out.println(page.getPageTotal());
		System.out.println(page.getRows());
		
	}
}
