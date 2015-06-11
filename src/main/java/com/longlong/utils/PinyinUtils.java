package com.longlong.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;

public class PinyinUtils {

	public static void main(String[] args){
		String[] strs = PinyinHelper.toHanyuPinyinStringArray('龙');
		System.out.println("pinyin:"+strs[0]);
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		/*try {
			String[] pins = PinyinHelper.toHanyuPinyinStringArray('彦', format);
			System.out.println("pinyin2:"+pins[0]);
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println(getPinyin("宗彦龙"));
	}
	
	
	public static String getPinyin(String fromString){
		String[] strs = {};
		String ret = "";
		for(int i=0;i<fromString.length();i++){
			 strs = PinyinHelper.toHanyuPinyinStringArray(fromString.charAt(i));
			 ret += strs[0];
		}
		return ret;
	}
	
}
