package com.mep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

import com.longlong.model.HBUser;
import com.longlong.utils.HttpUtils;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	
	static Parser parser = null;

	static HBUser user = null;
	static NodeFilter trsFilter = new HasAttributeFilter("name", "name");
	static NodeFilter sexFilter = new HasAttributeFilter("name", "gender");
	static NodeFilter dutyFilter = new HasAttributeFilter("name", "duty");
	static NodeFilter dwFilter = new HasAttributeFilter("name", "jobaddress");
	static NodeFilter sfFilter = new HasAttributeFilter("name", "province");
	static NodeFilter addressFilter = new HasAttributeFilter("name", "contactaddress");
	static NodeFilter mzFilter = new HasAttributeFilter("name", "national");
	static NodeFilter postFilter = new HasAttributeFilter("name", "postcode");
	static NodeFilter phoneFilter = new HasAttributeFilter("name", "phone");
	static NodeFilter mobileFilter = new HasAttributeFilter("name", "mobile");
	static NodeFilter faxFilter = new HasAttributeFilter("name", "fax");
	static NodeFilter emailFilter = new HasAttributeFilter("name", "email");
	static NodeFilter startFilter = new HasAttributeFilter("name", "reachetime");
	static NodeFilter endFilter = new HasAttributeFilter("name", "returntime");
	
	static NodeFilter inputFilter = new TagNameFilter("input");
	
    static NodeList list = null;
    static NodeList trs = null;
    static NodeList ui = null;
	
    static String htmlString = null;
    static Map<String, Object> map = null;
    
	public static void main(String[] args) throws Exception{
		
		com.longlong.tools.HibernateDao.init();
		for(int i=1624;i < 2092; i++){
			parser(HttpUtils.getString("http://datacenter.mep.gov.cn/apps/meeting/index.jsp?rpno=" + i));
		}
	}
	
	public static String openFile(File file) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
		String context = "";
		String temp = "";
		while((temp = br.readLine())!=null){
			context += temp + "\n";
		}
		br.close();
		return context;
	}
	
	public static void parser(String htmlString) throws Exception{
		
		//parser = new Parser(openFile(new File("C:/Users/long/Downloads/user2.htm")));
		parser = new Parser(htmlString);
		user = new HBUser();
		
		trs = parser.parse(inputFilter);
		
		map = new HashMap<String, Object>();
	
		for(SimpleNodeIterator iter = trs.elements(); iter.hasMoreNodes();){
			InputTag node = (InputTag)iter.nextNode();
			
			if("gender".equals(node.getAttribute("name"))){
				if(node.getText().contains("checked")){
					map.put("sex", node.getAttribute("value"));
				}
			}
			
			map.put(node.getAttribute("name"), node.getAttribute("value"));
		}
//		
//		for(String k : map.keySet()){
//			log.info("k:" + k);
//			//log.info("k:"+k+"=========v:"+map.get(k).toString());
//		}
		
		log.info("size:" + map.size());
		if(map.size()>5){
			user.setName(map.get("name").toString());
			user.setGzdw(map.get("jobaddress").toString());
			user.setAddress(map.get("contactaddress").toString());
			user.setMz(map.get("national").toString());
			user.setPhone(map.get("phone").toString());
			user.setMobile(map.get("mobile").toString());
			user.setSex(map.get("sex").toString());
			user.setFax(map.get("fax").toString());
			user.setEmail(map.get("email").toString());
			user.setZc(map.get("duty").toString());
			user.setPost(map.get("postcode").toString());
			user.setUpdno(Long.valueOf(map.get("updno").toString()));
			user.setZsstartdate(map.get("reachtime").toString());
			user.setZsenddate(map.get("returntime").toString());
			com.longlong.tools.HibernateDao dao = new com.longlong.tools.HibernateDao();
			dao.save(user);
		}
	}
	
//	public static boolean isEmpty(String htmlString) throws Exception{
//		parser = new Parser(htmlString);
//		list = parser.parse(titleFilter);
//		return list.elementAt(0).toString().contains("����ʵ�ҳ�治����");
//	} 

	/**
	 * 
	 * @param list
	 * @param index
	 * @return
	 */
	public static String getHtml(NodeList list, int index){
		return list.elementAt(index).toHtml();
	}

	/**
	 * 
	 * @param list
	 * @param index
	 * @return
	 */
	public static String getValue(NodeList list, int index){
		return list.elementAt(index).getText();
	}
	
	/**
	 * 
	 * @param list
	 * @param index
	 * @return
	 */
	public static NodeList getsubList(NodeList list, int index){
		return list.elementAt(index).getChildren();
	}
	
	
	
	
	
	
}
