package com.weituitui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.longlong.model.WttUser;
import com.longlong.model.WttWeibo;
import com.longlong.tools.HibernateDao;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	
	 Parser parser = null;
	 WttUser user = null;
	 WttWeibo weibo = null;
	/*  */
	static NodeFilter cssfilter =  new CssSelectorNodeFilter("div[class='userinfo mb10 mt20']");
	/* 获取 中标收入、微博列表 */
	static NodeFilter trsFilter = new HasAttributeFilter("class", "orange1");
	/* 获取个人信息   注册时间   发帖数等 */
	static NodeFilter userinfoFilter = new HasAttributeFilter("class", "user_intro gray2");
	
	static NodeFilter titleFilter = new TagNameFilter("title");
	
     NodeList list = null;
     NodeList trs = null;
     NodeList ui = null;
     
	public static void main(String[] args) throws Exception{
		
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(12000);
		client.getHttpConnectionManager().getParams().setSoTimeout(12000);
		GetMethod get = null;                                                                         
//		105  3309  5533 8565 11635 14690 17697 20731 23792 26851 29929 33033 36158 39213 40261 43371 46460 
//		  59038 59187(too many connections) 59336(too many connections) 60410(404) 64374(404) 65975 78423  
//       90967 103488 105394 118033 130677 143525 156297 168929 181567 194183 206874 220125 598919 599266
		
		HibernateDao.init();
		//484473 503754
		for(int i=600193;i < 600912; i++){
		get = new GetMethod("http://www.weituitui.com/users/" + i);
		log.info("userId:" + i);
		client.executeMethod(get);
		log.info("status Code :" + get.getStatusCode());
		
		if(get.getStatusCode()==404){
			continue;
		}
		String htlmString = get.getResponseBodyAsString();
		//log.info(htlmString);
		get.releaseConnection();
		Test tset = new Test();
		if(!tset.isEmpty(htlmString)){
			tset.parser(htlmString);
			//com.longlong.test.Test.close();
		}
		}
	}
	
	public static String openFile(File file) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		String context = "";
		String temp = "";
		while((temp = br.readLine())!=null){
			context += temp + "\n";
		}
		br.close();
		return context;
	}
	
	public void parser(String htmlString) throws Exception{
		
		//parser = new Parser(openFile(new File("C:/Users/long/Downloads/user2.htm")));
		parser = new Parser(htmlString);
		user = new WttUser();
		
		list = parser.parse(cssfilter);
		parser.reset();
		trs = parser.parse(trsFilter);
		parser.reset();
		ui = parser.parse(userinfoFilter);
		
		user.setName(getsubList(list, 0).elementAt(2).toHtml());
		user.setRenzheng(getsubList(list, 0).elementAt(7).getText());
		if(getsubList(list, 0, 12)!=null){
			user.setVip(getsubList(list, 0, 12, 0).elementAt(0).getText());
		}
		
		/**
		 * 获取个人信息
		 */
//		for(int i=1;i<12;i++){
//			log.info(getsubList(ui, 0, i).elementAt(0).toHtml().split("��")[1]);
//			i++;
//		}
		
		WttUser wttuser = userinfo(ui);
		user.setUserId(wttuser.getUserId());
		user.setZcsj(wttuser.getZcsj());
		user.setFbrw(wttuser.getFbrw());
		user.setTjgj(wttuser.getTjgj());
		user.setFbtz(wttuser.getFbtz());
		
		Map<String, Object> moneys = moneyMap(getsubList(trs, 0));
		user.setMoney1(Double.valueOf(moneys.get("money1").toString()));
		user.setMoney2(Double.valueOf(moneys.get("money2").toString()));
		user.setMoney3(Double.valueOf(moneys.get("money3").toString()));
		user.setMoney4(Double.valueOf(moneys.get("money4").toString()));
		user.setMoney5(Double.valueOf(moneys.get("money5").toString()));
		
		HibernateDao dao = new HibernateDao();
		/**
		 * 循环获取微博tr
		 */
		for(int i=1;i<trs.size();i++){
			weibo = wb(getsubList(trs, i));
			weibo.setUserId(user.getUserId());
			dao.save(weibo);
		}
		dao.save(user);
	}
	
	public static WttUser userinfo(NodeList nodes){
		WttUser user = new WttUser();
		user.setUserId(Long.parseLong(getsubList(nodes, 0, 1).elementAt(0).toHtml().split("：")[1]));
		user.setName(getsubList(nodes, 0, 3).elementAt(0).toHtml().split("：")[1]);
		user.setZcsj(getsubList(nodes, 0, 5).elementAt(0).toHtml().split("：")[1]);
		user.setFbrw(Long.parseLong(getsubList(nodes, 0, 7).elementAt(0).toHtml().split("：")[1]));
		user.setTjgj(Long.parseLong(getsubList(nodes, 0, 9).elementAt(0).toHtml().split("：")[1]));
		user.setFbtz(Long.parseLong(getsubList(nodes, 0, 11).elementAt(0).toHtml().split("：")[1]));
		return user;
	}
	
	public static WttWeibo wb(NodeList wbList){
		WttWeibo weibo = new WttWeibo();
		log.info("weibo:"+ getsubList(wbList, 3).elementAt(1).toHtml());
		
		log.info("weibo renzheng:"+ (getsubList(wbList, 5, 0)==null?"null":getsubList(wbList, 5, 0).elementAt(0).toHtml()));
		
		log.info(getsubList(wbList, 7, 1, 1, 1, 0).elementAt(0).toHtml());
		log.info(getsubList(wbList, 7, 1, 1, 3).elementAt(0).toHtml());
		log.info(getsubList(wbList, 7, 1, 3, 1, 0).elementAt(0).toHtml());
		log.info(getsubList(wbList, 7, 1, 3, 3).elementAt(0).toHtml());
		log.info(getsubList(wbList, 7, 1, 5, 1, 0).elementAt(0).toHtml());
		log.info(getsubList(wbList, 7, 1, 5, 3).elementAt(0).toHtml());
		weibo.setWeibo(getsubList(wbList, 3).elementAt(1).toHtml());
		weibo.setRzfs(Long.parseLong(getsubList(wbList, 5, 0)==null?"0":getsubList(wbList, 5, 0).elementAt(0).toHtml()));
		weibo.setGz(Long.parseLong(getsubList(wbList, 7, 1, 1, 1, 0).elementAt(0).toHtml()));
		weibo.setFs(Long.parseLong(getsubList(wbList, 7, 1, 3, 1, 0).elementAt(0).toHtml()));
		weibo.setWb(Long.parseLong(getsubList(wbList, 7, 1, 5, 1, 0).elementAt(0).toHtml()));
		return weibo;
	}

	public static Map<String, Object> moneyMap(NodeList moneyList){
		Map<String, Object> ret = new HashMap<String, Object>();
		for(int i=0;i<10;i++){
			i++;
			log.info(getValue(getsubList(moneyList, i), 1));
			switch (i) {
			case 1:
				ret.put("money1", getValue(getsubList(moneyList, i), 1));
				break;
			case 3:
				ret.put("money2", getValue(getsubList(moneyList, i), 1));
				break;
			case 5:
				ret.put("money3", getValue(getsubList(moneyList, i), 1));
				break;
			case 7:
				ret.put("money4", getValue(getsubList(moneyList, i), 1));
				break;
			case 9:
				ret.put("money5", getValue(getsubList(moneyList, i), 1));
				break;
			default:
				break;
			}
		}
		return ret;
	}
	
	public boolean isEmpty(String htmlString) throws Exception{
		parser = new Parser(htmlString);
		list = parser.parse(titleFilter);
		return list.elementAt(0).toString().contains("您访问的页面不存在");
	} 

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
	
	/**
	 * 
	 * @param list
	 * @param index
	 * @return
	 */
	public static NodeList getsubList(NodeList list, Object...index){
		NodeList nodes = null;
		for(int i=0;i<index.length;i++){
//			log.info(i);
//			log.info(index);
//			log.info(index[i]);
			if(i==0){
				nodes = list.elementAt((Integer)index[i]).getChildren();
			}else{
				nodes = nodes.elementAt((Integer)index[i]).getChildren();
			}
		}
		return nodes;
	}
	
	public static void eachList(NodeList list, int deep){
		if(list!=null){
			for(int i = 0; i < list.size(); i++){
				Node node = list.elementAt(i);
				log.info("deep:"+deep);
				log.info(node.getText());
				if(node.getChildren()!=null){
					if(deep>0){
						eachList(node.getChildren(), deep--);
					}
				}
			}
		}
	}
	
	public static void eachList(NodeList list){
		if(list!=null){
			for(int i = 0; i < list.size(); i++){
				Node node = list.elementAt(i);
				log.info(i);
				log.info(node.getText());
//				if(node.getChildren()!=null){
//					eachList(node.getChildren());
//				}
				
			}
		}
	}
	
}
