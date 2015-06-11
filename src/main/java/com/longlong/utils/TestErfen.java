package com.longlong.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

public class TestErfen { 
	static Logger log = Logger.getLogger(TestErfen.class);
	static HttpClient client = new HttpClient();
	static GetMethod get = null; 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		client.getHttpConnectionManager().getParams().setConnectionTimeout(12000);
		client.getHttpConnectionManager().getParams().setSoTimeout(12000);
		//System.out.println(".........................................");
		erfen(550000, 605000);

	}
	
	/**
	 * suan fa 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int erfen(int start, int end){
	      	int i = start + (end - start)/2;
	      	if((end - start) > 1){
	      		if(istrue(i)){
		      		erfen(i, end);
		      	}else{
		      		erfen(start, i);
		      	}
	      	}
		return 0;
	}
	
	/**
	 * 
	 * @param index
	 * @return ture or false
	 */
	public static boolean istrue(int index){
		get = new GetMethod("http://www.weituitui.com/users/" + index);
		log.info("userId:" + index);
		try {
			client.executeMethod(get);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		log.info("status Code :" + get.getStatusCode());
		if(get.getStatusCode()==404){
			return false;
		}else{
			return true;
		}
	}

}
