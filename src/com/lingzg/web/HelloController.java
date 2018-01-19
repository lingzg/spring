package com.lingzg.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lingzg.dao.CostDao;
import com.lingzg.entity.Cost;

@Controller
@RequestMapping("/demo")
public class HelloController {

	@Resource
	private CostDao dao;
	
	@RequestMapping("/hello.do")
	public String hello() {
//		Integer.valueOf("abc");
//		char c = "abc".charAt(9);
		Integer.parseInt("abc");
		System.out.println("HelloController.hello");
		return "hello";
	}
	
	@RequestMapping("/nihao.do")
	public String nihao(ModelMap model) {
		List<Cost> list = dao.findAll();
		model.addAttribute("costs", list);
		return "hello";
	}
	
	/**
	 * @ExceptionHandler的作用是告诉SpringMVC，
	 * 这个Controller当中任何一个方法报错，都要
	 * 调用当前异常处理的方法来处理错误。
	 * 
	 * 实际上SpringMVC会帮助我们调用Controller的方法，
	 * 它在调用时会try catch，一旦catch到异常就调用
	 * 当前这个方法，并且会将catch到的异常注入进来，
	 * 也会将request、response注入进来。
	 */
	@ExceptionHandler
	public String doException(Exception e, 
			HttpServletRequest request,
			HttpServletResponse response) {
		//判断异常的类型，不同的异常，处理方式可能不一样
		if(e instanceof IndexOutOfBoundsException) {
			return "index_error";
		} else {
			return "error";
		}
	}
	
}
