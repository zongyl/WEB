package com.longlong.utils;

import org.apache.log4j.Logger;

public class StringUtil {

	static Logger log = Logger.getLogger(StringUtil.class);
	
	/**
	 * unicode to string
	 * @param unicode
	 * @return
	 */
	public static String unicode2String(String unicode){
		log.info(unicode);
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		log.info("length:"+hex.length);
		for(int i = 1; i < hex.length; i++){
			log.info("=========loop:"+hex[i]);
			int data = Integer.parseInt(hex[i], 16);
			string.append((char)data);
		}
		log.info(string.toString());
		return string.toString();
	}

	/**
	 * string to unicode
	 * @param string
	 * @return
	 */
	public static String string2Unicode(String string){
		StringBuffer unicode = new StringBuffer();
		for(int i = 0; i < string.length(); i++){
			char c = string.charAt(i);
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}
	
}
