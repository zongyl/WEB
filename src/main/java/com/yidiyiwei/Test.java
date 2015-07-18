package com.yidiyiwei;

import org.apache.log4j.Logger;

import com.longlong.utils.HttpUtils;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	
	private static String sessionId = "Cookie: PHPSESSID=3gn8vho69kv8kng5rhnpf3c5l7";
	
	private static String html = "";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("........");
		try {
			html = HttpUtils.getString(sessionId, "http://api.yidiyiwei.com/index.php/Interface_v4/Order/MineAndroid?userid=2901");
			log.info("html:"+html);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
