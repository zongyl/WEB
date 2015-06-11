package com.longlong.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception{
		if(request.getRequestURI().contains("login.do")){
			return true;
		}else{
			//执行拦截器
			System.out.println("执行拦截器!");
		}
		return true;
	}
}
