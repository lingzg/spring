package com.lingzg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FirstInterceptor 
	implements HandlerInterceptor {

	//请求结束时调用
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("First.afterCompletion");
	}

	//在Controller之后调用
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("First.postHandle");
	}

	//在Controller之前调用 
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("First.preHandle");
		//若返回true，SpringMVC会继续调用Controller；
		//若返回false，SpringMVC则不会调用Controller；
		//该方法在Controller前调用，可以真正实现拦截功能。
		return true;
	}

}
