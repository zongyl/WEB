/**
 * 
 */
package com.longlong.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;




/**
 * @author zongyl
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bean1 bean = new Bean1();
		bean.setName("name");
		bean.setAge("age");
		//bean.setSex("sex");
		
		Bean bean1 = new Bean();
		Bean2 bean2 = new Bean2();
		try {
			BeanUtils.copyProperties(bean1, bean);
			BeanUtils.copyProperties(bean2, bean);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("bean.name:"+bean.getName());
		System.out.println("bean.age:"+bean.getAge());
		//System.out.println("bean.sex:"+bean.getSex());
		
		System.out.println("bean1.name:"+bean1.getName());
		System.out.println("bean1.age:"+bean1.getAge());
		System.out.println("bean.sex:"+bean1.getSex());
		
		System.out.println("bean2.name:"+bean2.getName());
		System.out.println("bean2.sex:"+bean2.getSex());

	}

}
