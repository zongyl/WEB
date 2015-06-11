package com.qianbao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.longlong.tools.HibernateDao;
import com.longlong.utils.HttpUtils;
import com.qianbao.model.Qbtask;

public class Test {

	static HibernateDao dao = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		HibernateDao.init();
		dao = new HibernateDao();
		
		/*for(int i = 1; i < 17; i++){
			System.out.println("————————————"+i+"——————————————");
			String html = HttpUtils.getString("http://www.qianbao666.com/ntask/home.html?co=0&po=0&ti=0&re=0&mr=0&mrb=&mre=&ty=11&p="+i);
			DB(html);
		}*/
		
		for(int i = 1; i < 9; i++){
			String html = HttpUtils.getString("http://www.qianbao666.com/ntask/home.html?p="+i);
			DB(html);
		}
		
		}
	
	private static void DB(String html){
		Document detail = Jsoup.parse(html);
		Elements els = detail.getElementsByAttributeValue("class", "h_hotTaskList h_taskW338 clearfix");
		for(Element e : Jsoup.parse(els.toString()).getElementsByTag("li")){
			System.out.println("----------------------------------");
//			System.out.println(e.getElementsByAttributeValueStarting("class", "h_hotTaskTitle ").get(0).getElementsByTag("h2").text());
//			System.out.println(e.getElementsByAttributeValueStarting("class", "h_hotTaskFont ").get(0).getElementsByTag("em").text());
//			System.out.println(e.getElementsByAttributeValueStarting("class", "h_hotTaskFont ").get(0).getElementsByTag("h3").text());
//			System.out.println(e.getElementsByAttributeValueStarting("class", "h_hotTaskBot ").get(0).getElementsByTag("em").text());
//			System.out.println(e.getElementsByAttributeValueStarting("class", "h_hotTaskBot ").get(0).getElementsByAttributeValue("class", "fr").text());
	
			Qbtask task = new Qbtask();
			task.setName(e.getElementsByAttributeValueStarting("class", "h_hotTaskTitle ").get(0).getElementsByTag("h2").text());
			task.setSj(e.getElementsByAttributeValueStarting("class", "h_hotTaskFont ").get(0).getElementsByTag("em").text());
			task.setDays(e.getElementsByAttributeValueStarting("class", "h_hotTaskFont ").get(0).getElementsByTag("h3").text());
			task.setBzj(e.getElementsByAttributeValueStarting("class", "h_hotTaskBot ").get(0).getElementsByTag("em").text());
			String str = e.getElementsByAttributeValueStarting("class", "h_hotTaskBot ").get(0).getElementsByAttributeValue("class", "fr").text();
			task.setCount(getCount(str));
			dao.save(task);
		}
	}
	
	/**
	 * 截取字符串
	 * @param str
	 * @return
	 */
	private static String getCount(String str){
		return str.substring(str.indexOf(':'), str.length()-1);
	}
	

}
