package com.longlong.utils;

import java.lang.reflect.Method;
/**
 * 反射 获取注解
 * @author long
 *
 */
public class MyReflection {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		MyAnnTest test = new MyAnnTest();
		Class<MyAnnTest> c = MyAnnTest.class;
		Method method = c.getMethod("doSomeString", new Class[] {});
		method.invoke(test, null);
		myAnn ma = method.getAnnotation(myAnn.class);
		System.out.println(ma.hello());
		System.out.println(ma.world());
	}

}
