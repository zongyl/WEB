package com.longlong.utils;

import java.lang.reflect.Method;
/**
 * 测试注解
 * @author long
 *
 */
public class MyAnnTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
 
		Method method = MyAnnTest.class.getMethod("doSomeString", null);
		
		if(method.isAnnotationPresent(myAnn.class)){
			System.out.println("............");
			System.out.println(method.getAnnotation(myAnn.class));
			
			myAnn ma = method.getAnnotation(myAnn.class);
			
			
		}
		
	}
	
	@myAnn(hello = "HELLO", world = "wd", style = String.class, array = {9, 8, 7})
	public void doSomeString(){
		System.out.println("hello world!!");
	}

}
