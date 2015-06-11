package com.longlong.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.longlong.model.UZlecuList;
import com.longlong.tools.HibernateDao;

/**
 * HTML parse 
 * @author long
 *
 */
public class JsoupUtil {

	static Logger log = Logger.getLogger(JsoupUtil.class);
	
	/**
	 * 
	 * @param e
	 * @param type "uvnum" or "likenum"
	 * @return
	 */
	static String getnum(Element e, String type){
		String numString = e.getElementsByClass(type).get(0).text();
		return numString.substring(0, numString.indexOf("人"));
	}

	static void parseHTML(Document doc){
		for(ListIterator<Element> i =  doc.getElementsByClass("list-c").listIterator(); i.hasNext();){
			Element e = i.next();
			log.info("____________________________________________________________________");
			Elements a = e.getElementsByTag("h3").first().getElementsByTag("a");
//			log.info(a.attr("href"));
			log.info(a.html().substring(0, a.html().indexOf("<span")));
//			log.info("____________________________________________________________________");
//			log.info(e.getElementsByClass("intro").get(0).getElementsByTag("span").html());
//			log.info("____________________________________________________________________");
//
//			log.info(getnum(e, "uvnum"));
//			log.info("____________________________________________________________________");
//			log.info(getnum(e, "likenum"));
//			log.info("____________________________________________________________________");
//			log.info(e.getElementsByClass("uptime").get(0).text());
//			log.info("==============================================================================");
			
			HibernateDao dao = new HibernateDao();
			UZlecuList uz = new UZlecuList();
			uz.setName(a.html().substring(0, a.html().indexOf("<span")));
			uz.setUrl(a.attr("href"));
			uz.setRemarker(e.getElementsByClass("intro").get(0).getElementsByTag("span").html());
			uz.setUvnum(Long.parseLong(getnum(e, "uvnum")));
			uz.setLikenum(Long.parseLong(getnum(e, "likenum")));
			uz.setLastUpdate(e.getElementsByClass("uptime").get(0).text());
			dao.save(uz);
		}
	}
	

	static void parseHTML2(Document doc) throws Exception{
		for(ListIterator<Element> i =  doc.getElementsByClass("list-c").listIterator(); i.hasNext();){
			Element e = i.next();
			log.info("____________________________________________________________________");
			Elements a = e.getElementsByTag("h3").first().getElementsByTag("a");
			log.info(a.html().substring(0, a.html().indexOf("<span")));
			
			HibernateDao dao = new HibernateDao();
			UZlecuList uz = new UZlecuList();
			uz.setName(a.html().substring(0, a.html().indexOf("<span")));
			
			if("".equals(uz.getName())){
				log.info(".....................");
				continue;
			}
			
			uz.setUrl(a.attr("href"));
			uz.setRemarker(e.getElementsByClass("intro").get(0).getElementsByTag("span").html());
			uz.setUvnum(Long.parseLong(getnum(e, "uvnum")));
			uz.setLikenum(Long.parseLong(getnum(e, "likenum")));
			uz.setLastUpdate(e.getElementsByClass("uptime").get(0).text());
			
			//Document detail = Jsoup.connect("http://uz.lecu8.com"+uz.getUrl()).get();
			Document detail = Jsoup.parse(HttpUtils.getString("http://uz.lecu8.com"+uz.getUrl()));
			String url = detail.getElementsByClass("params").first().child(0).child(0).child(2).child(0).child(0).child(0).html();
			String tag = detail.getElementsByClass("params").first().child(0).child(0).child(1).child(1).child(0).html();
			uz.setLink(url);
			uz.setTag(tag);
			uz.setType(2);
			dao.save(uz);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		
		HibernateDao.init();
		
		//18页  没抓完  折优购  抓完了
		//252   3味书屋 没抓
//		for(int i = 360; i < 572; i++){    
//			Document doc = Jsoup.parse(HttpUtils.getString("http://uz.lecu8.com/index-uzlist-type-2-order-uv%20desc-page-"+i+".html"));
//			parseHTML2(doc);
//		}
		
		
		// update 
//		HibernateDao dao = new HibernateDao();
//		for(int i=809;i < 1014; i++){
//			log.info("——————————————————————————————————————————："+i);
//			UZlecuList uz = (UZlecuList)dao.getById(Long.valueOf(i));
//			Document doc = Jsoup.connect("http://uz.lecu8.com"+uz.getUrl()).get();
//			String url = doc.getElementsByClass("params").first().child(0).child(0).child(2).child(0).child(0).child(0).html();
//			String tag = doc.getElementsByClass("params").first().child(0).child(0).child(1).child(1).child(0).html();
//			log.info(url);
//			log.info(tag);
//			uz.setLink(url);
//			uz.setTag(tag);
//			dao.update(uz);
//		}
		
		List<String> uzs = new ArrayList<String>();
		uzs.add("http://jiukuaiyoucom.uz.taobao.com/");
		uzs.add("http://youhuihui.uz.taobao.com/");
		uzs.add("http://jiaxiangwei.uz.taobao.com/");
		
//		Document doc = Jsoup.parse(HttpUtils.getString("http://bangpai.taobao.com/group/15485956.htm"));
//		log.info(doc.toString());
		
		JsoupUtil ju = new JsoupUtil();
		ju.getFileByUrl("http://bangpai.taobao.com/group/15485956.htm", "d:\\uz\\11111111111111.html");
		
	//	log.info(doc.getElementsByClass("focus sh-btn focus-num J_FocusNum").size());
		
		
		//Map<String, String> urls = new HashMap<String, String>();
		//urls.put("http://uz.taobao.com/", "uz-index");
		//urls.put("http://www.taoniupin.com/", "uz-taoniupin");  yes
		//urls.put("http://xiaoxin.uz.taobao.com/", "uz-xiaoxin");  yes
		//urls.put("http://99.uz.taobao.com/theme/tejia/view/list_goods.php?pindaoId=2", "uz-99");
		//urls.put("http://shi.taobao.com/", "uz-shi-taobao");
		//urls.put("http://shi.taobao.com/?pagenum=3&pagesize=15/", "uz-shi-taobao-3");
//		urls.put("http://bbs.taobao.com/catalog/thread/154503-264478019.htm", "bbs.taobao.com.catalog.thread.154503-264478019");
//		urls.put("http://bbs.taobao.com/catalog/thread/154503-264478019-2.htm", "bbs.taobao.com.catalog.thread.154503-264478019-2");
//		urls.put("http://bbs.taobao.com/catalog/thread/154503-264478019-3.htm", "bbs.taobao.com.catalog.thread.154503-264478019-3");
		//urls.put("http://youqu.uz.taobao.com/", "uz-youqu");
		
//		urls.put("http://uz.lecu8.com/", "uz.lecu8.com");
//		Set<String> urlsSet = urls.keySet();
//		JsoupUtil ju = new JsoupUtil();
//		for(String u : urlsSet){
//			ju.getFileByUrl(u, urls.get(u));
//		}
		
	}
	
	/**
	 * 
	 * @param url 
	 * @param fileName
	 * @throws Exception
	 */
	public void getFileByUrl(String url, String fileName) throws Exception{
		HttpUtils.toFile(url, fileName);
	}
	
	
	
}
