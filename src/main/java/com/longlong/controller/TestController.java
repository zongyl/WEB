package com.longlong.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.core.type.AnnotationMetadata;

@Controller
@RequestMapping
public class TestController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = "/login.do")
	public ModelAndView toLogin(HttpServletRequest request){
		request.getSession().setAttribute("currUser", "ZONGYANLONG");
		Map map = new HashMap();
		map.put("loginname", request.getSession().getAttribute("currUser"));
		return new ModelAndView("/index.jsp", map);
	}

	@RequestMapping(value = "/index.do")
	public @ResponseBody Object toIndex(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "zongyl");
		map.put("email", "379122181@qq.com");
		map.put("session[name]", request.getSession().getAttribute("currUser"));
		return map;
	}

	@RequestMapping(value = "/logout.do")
	public void toLogout(HttpServletRequest request){
	}
	
	public static void main(String rags[]){
		//ContentNegotiationManagerFactoryBean dd;
		//ConfigurableEnvironment config;
		
		String str = "1234567890";

//		System.out.println(""+str.substring(0,4));
//		TestController cc = new TestController();
//		System.out.println("cc:"+cc.getString(str));
		
		String str1 = "==QZ5Vkc19WWm9UZsBHcBVGaUNXQl1EclV2SlNXYlxGUlxGculGV";
	      String newStr = new StringBuffer(str1).reverse().toString();

	      System.out.println(newStr);
		
	}
	
	public String getString(String str){
		String ret = str.substring(0,4) + str.substring(str.length()-2) + str.substring(4, str.length()-2);
		return ret;
	}
	
}
