package com.lingzg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("test")
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/hello.do")
	public String hello() {
//		Integer.valueOf("abc");
//		char c = "abc".charAt(9);
		Integer.parseInt("abc");
		System.out.println("HelloController.hello");
		return "hello";
	}
	
}
