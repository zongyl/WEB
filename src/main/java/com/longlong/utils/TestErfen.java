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
		client.getHttpConnectionManager().getParams().setConnectionTimeout(12000);
		client.getHttpConnectionManager().getParams().setSoTimeout(12000);
		//System.out.println(".........................................");
		erfen(1163859, 1170000, "http://www.4000979797.com/online/api/mc/order/detail.json?orderId=");
	}
	
	/**
	 * suan fa 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int erfen(int start, int end, String url){
	      	int i = start + (end - start)/2;
	      	log.info(start+"====="+end+"====="+i);
	      	if((end - start) > 1){
	      		if(istrue(url + i)){
	      			log.info("true!");
	      			erfen(i, end, url);
		      	}else{
		      		erfen(start, i, url);
		      	}
	      	}
		return 0;
	}
	
	/**
	 * 
	 * @param index
	 * @return ture or false
	 */
	public static boolean istrue(String url){
		log.info(url);
		get = new GetMethod(url);
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
