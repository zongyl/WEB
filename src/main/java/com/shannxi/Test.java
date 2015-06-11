package com.shannxi;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.longlong.utils.HttpUtils;

public class Test {

	public static final Logger log = Logger.getLogger(Test.class);
	
	static String html = "", houseId = "";
	
	static String sessionId = "JSESSIONID=0000UiVnVG7QActXdGxthFWt4bi:-1";
	
	public static void main(String[] args){
		
		for(int i=1000;i<1099;i++){
			each(i);
		}
		
	}
	
	static void each(int page){
		try {
			html = HttpUtils.getString(sessionId, "http://124.115.170.79:9080/pis/searchHouse.html?ec_i=ec&eti=&eti_p=&ec_efn=&ec_ev=&ec_crd=12&ec_p="+page+"&ec_s__check=&ec_s_ACCOUNTTHIN=&ec_s_LIVINGTYPE=&ec_s_ACCOUNTTYPE=&ec_s_AREANAME=&ec_s_DOORPLATE=&ec_s_CONTACTPHONE=&ec_s_PISFIELD_REGTIME=&ec_s_PISFIELD_RECORDERNAME=&ec_s__operation=&house.AccountThin=&curareaName=%E9%99%95%E8%A5%BF%E7%9C%81&people.PISField001=&house.LivingType=&house.AreaName=&PISField_AeaNo=610000000000&houseId=&house.AccountType=&people.PISField011=&ec_totalpages=19365&ec_totalrows=232375&ec_pg=0&ec_rd=12");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Document dom = Jsoup.parse(html);
		Elements els = dom.getElementById("ec_table_body").children();
		
		Element el;
		for( Iterator<Element> it = els.iterator(); it.hasNext();){
			el = it.next();
			houseId = el.child(0).child(0).attr("id");
			detailsHouse(houseId);
			//log.info("QQ:"+houseId);
		}
	}
	
	static void detailsHouse(String houseId){
		
		log.info("======================================:"+houseId);
		
		try {
			html = HttpUtils.getString(sessionId, "http://124.115.170.79:9080/pis/houseDetailCard.html?houseId="+houseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//log.info("hukou:"+html);
		
		Document dom = Jsoup.parse(html);
		int s = dom.getElementById("houseDetailCard").children().size();
		Element tbody = dom.getElementById("houseDetailCard").child(20);//.toString();
		
		String info = tbody.child(1).child(1).text();
		String dcy = tbody.child(1).child(3).text();

		String d = tbody.child(3).child(1).text();//户别   家庭户
		String d1 = tbody.child(3).child(2).text();//公安户口薄号
		String d2 = tbody.child(3).child(3).text();//户口类型
		String d3 = tbody.child(3).child(4).text();//小区(组)名称
		String d4 = tbody.child(3).child(5).text();//门牌号
		String d5 = tbody.child(3).child(6).text();//建卡日期
		
	/*	String p = tbody.child(7).child(0).text();//编号
		String p1 = tbody.child(7).child(1).text();//姓名	
		String p2 = tbody.child(7).child(2).text();//性别
		String p3 = tbody.child(7).child(4).text();// 身份证号
		String p4 = tbody.child(7).child(5).text();//民族
		String p5 = tbody.child(7).child(6).text();//文化程度
		String p6 = tbody.child(7).child(7).text();//户口性质
		String p7 = tbody.child(7).child(8).text();//婚姻状况
		String p8 = tbody.child(7).child(9).text();//初婚日期
		String p9 = tbody.child(7).child(10).text();//与户主关系
		String p10 = tbody.child(7).child(11).text();//户籍所在地
		String p11 = tbody.child(7).child(12).text();//现居住地
		String p12 = tbody.child(7).child(13).text();//迁移或流动类型
		String p13 = tbody.child(7).child(14).text();//变动日期
*/		
		for(int i = 7;i < 15; i++){
			if(tbody.child(i).children().size()>3){
				getPeople(tbody.child(i));
			}else{
				log.info(i+"行跳出!");
				break;
			}
		}
		
		//log.info("tbody:"+dcy);
	}
	
	static String getPeople(Element el){
		String p = el.child(0).text();//编号
		String p1 = el.child(1).text();//姓名	
		String p2 = el.child(2).text();//性别
		String p3 = el.child(4).text();// 身份证号
		String p4 = el.child(5).text();//民族
		String p5 = el.child(6).text();//文化程度
		String p6 = el.child(7).text();//户口性质
		String p7 = el.child(8).text();//婚姻状况
		String p8 = el.child(9).text();//初婚日期
		String p9 = el.child(10).text();//与户主关系
		String p10 = el.child(11).text();//户籍所在地
		String p11 = el.child(12).text();//现居住地
		String p12 = el.child(13).text();//迁移或流动类型
		String p13 = el.child(14).text();//变动日期
		log.info(p1);
		
		return p1;
	}
	
}
