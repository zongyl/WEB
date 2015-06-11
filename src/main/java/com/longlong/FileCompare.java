package com.longlong;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileCompare {

	static int index = 0;
	
	static List<String> array1, array;
	
	public static void main(String[] args){
		File file = new File("D:\\adt-bundle-windows-x86-20140702\\sdk");
		array = loop(file, array);
		array1 = loop(new File("D:\\adt-bundle-windows-x86-20140702qqq\\sdk"), array1);
		
		System.out.println(array.size() +"|"+ array1.size());
		
		List<String> delArray, delArray1;
		delArray = new ArrayList<String>();
		delArray1 = new ArrayList<String>();
		
		for(String str : array1){
			for(String str1 : array){
				if(str.equals(str1)){
//					array1.remove(str);
//					array.remove(str1);
					delArray.add(str1);
					delArray1.add(str);
				}
			}
		}

		array.removeAll(delArray);
		array1.removeAll(delArray1);
		
		System.out.println(array.size() +"|"+ array1.size());

		//each(array);
		//System.out.println("================================");
		//each(array1);
		
	}
	
	public static void each(List<String> array){
		if(array!=null){
			for(String str : array){
				System.out.println("each:"+str);
			}
		}
	}
	
	//递归 遍历文件
	public static List<String> loop(File file, List<String> lists){
		if(lists == null){
			lists = new ArrayList<String>();
		} 
		
		File[] files = file.listFiles();
		for(File f : files){
			if(f.isDirectory()){
				loop(f, lists);
				String path = f.getAbsolutePath();
				lists.add(path.substring(path.indexOf("\\sdk\\"))+" | "+getCountByDir(f));
				System.out.println(index+"."+path.substring(path.indexOf("\\sdk\\"))+" | "+getCountByDir(f));
				//index++;
			}else{
				//遍历文件
				//System.out.println(index+"."+f.getAbsolutePath());
				//index++;
			}
		}
		return lists;
	}
	
	//返回当前目录下 文件总数
	public static int getCountByDir(File file){
		return file.listFiles().length;
	}
	
}
