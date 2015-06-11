package com.yhjj;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.longlong.tools.HibernateDao;
import com.yhjj.model.DeviceUser;
import com.yhjj.model.YhUsers;

/**
 * 余杭交警
 * @author long
 *
 */
public class Test {

	private static final Logger log = Logger.getLogger(Test.class);
	
	private static String HOST = "http://zk.yhjj.gov.cn:8080";
	
	private static String sessionId = null;
	
	static HibernateDao dao = null;

	static void eachMap(Map<String, List<String>> map){
		Set<String> keys = map.keySet();
		for(String key : keys){
			log.info("————————key:"+key);
			List<String> strs = map.get(key); //new ArrayList<String>();
			
			for(String str : strs){
				log.info("————————————————string:"+str);
			}
		}
	}
	
	static String getSessionId() throws Exception{
		String session_value = null;
		URL url = new URL(HOST+"/admin/login.aspx?ReturnUrl=%2fadmin%2fdefault.aspx&username=netover&password=vlzz808033&x=41&y=7");
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
		session_value = urlConnection.getHeaderField("Set-Cookie");
		
		Map<String, List<String>> maps = urlConnection.getHeaderFields();
		
		//Map<String, List<String>> maps1 = urlConnection.getRequestProperties();

		eachMap(maps);
		//eachMap(maps1);
		
	//	log.info("session_value:"+session_value);
		
		String[] sessionId = session_value.split(";");
		
		//urlConnection.setRequestProperty("Cookie", sessionId[0]);
		log.info("sessionid:"+sessionId[0]);
		return sessionId[0];
	}
	
	static DeviceUser getConn(String sessionId, String uri) throws Exception{
		System.out.println(":"+sessionId);
		URLConnection conn = null;
		URL url = new URL(uri);
		conn = url.openConnection();
		conn.setRequestProperty("Cookie", sessionId);
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
		conn.connect();
		
		InputStream is = conn.getInputStream();  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        String response = "";  
        String readLine = null;  
        while((readLine =br.readLine()) != null){  
            response = response + readLine+"\n";  
        }  
        is.close();  
        br.close();  
		log.info("getConn-response:"+response);
		return parseUser(response);
	}
	
	static DeviceUser parseUser(String html){
		Document dom = Jsoup.parse(html);
		log.info("LoginID:"+getValue(dom, "LoginID"));
		log.info("LoginPassword:"+getValue(dom, "LoginPassword"));
		log.info("UserName:"+getValue(dom, "UserPhone"));
		
		DeviceUser u = new DeviceUser();
		u.setLoginname(getValue(dom, "LoginID"));
		u.setLoginPwd(getValue(dom, "LoginPassword"));
		u.setMobile(getValue(dom, "UserPhone"));
		return u;
	}
	
	/**
	 * 余杭交警 设备维护派单考核系统
	 * @param html
	 * @param str
	 */
	static void parse(String html, String str){
		Document dom = Jsoup.parse(html);
		Elements els = dom.getElementsByClass("listTableListTR");
		for(Element el : els){
			DeviceUser du = new DeviceUser();
			du.setUserId(Long.parseLong(el.child(1).text()));
			du.setLoginname(el.child(2).text());
			du.setName(el.child(3).text());
			du.setRole(el.child(4).text());
			dao.save(du);
		}
	}
	
	/**
	 * 余杭交警 暂扣系统
	 * @param html
	 */
	static void parse(String html){
		Document dom = Jsoup.parse(html);
		log.info("user:"+getValue(dom, "username"));
		log.info("pass:"+getValue(dom, "password"));
		log.info("realName:"+getValue(dom, "name"));
		log.info("birthday:"+getValue(dom, "birthday"));
		log.info("birthday:"+getValueBySel(dom, "department"));
		log.info("birthday:"+getValueBySel(dom, "roles"));
		log.info("pass:"+getValue(dom, "tel"));
		log.info("mobile:"+getValue(dom, "mobile"));
		log.info("qq:"+getValue(dom, "qq"));
		log.info("msn:"+getValue(dom, "msn"));
		
		YhUsers user = new YhUsers();
		user.setLoginName(getValue(dom, "username"));
		user.setLoginPwd(getValue(dom, "password"));
		user.setRealName(getValue(dom, "name"));
		user.setBirthday(getValue(dom, "birthday"));
		user.setDep(getValueBySel(dom, "department"));
		user.setAuth(getValueBySel(dom, "roles"));
		user.setTelephone(getValue(dom, "tel"));
		user.setMobile(getValue(dom, "mobile"));
		user.setQQ(getValue(dom, "qq"));
		user.setMsn(getValue(dom, "msn"));
		dao.save(user);
		
	}
	
	static String getValue(Document dom, String input){
		return dom.getElementsByAttributeValue("name", input).get(0).attr("value");
	}
	
	static String getValueBySel(Document dom, String name){
		String ret = null;
		Elements els = dom.getElementsByAttributeValue("name", name).get(0).children();
		for(Element el : els){
			if(el.hasAttr("selected")){
				ret = el.text();
				break;
			}
		}
		return ret;
	}
	
	public static void main(String[] rags) throws Exception {
		
//		String html = HttpUtils.getString("http://zk.yhjj.gov.cn:8080/admin/system/users/user_info.aspx?action=edit&id=324");
//		log.info("html:"+html);
		
		//sessionId = getSessionId();
		
		HibernateDao.init();
		dao = new HibernateDao();
		
		List list = dao.list(" from DeviceUser", 80, 0);
		
		log.info("size:"+list.size());
		
		for(Object obj : list){
			DeviceUser user = (DeviceUser)obj;
			log.info(user.getUserId());
			log.info(user.getName());
			DeviceUser uu = getConn("ASP.NET_SessionId=wgkjeh55gworbknun23psrrb; xiTong=identity=0&userID=9&UserName=%e7%bd%91%e5%b0%bd%e7%a7%91%e6%8a%80&validateInfo=e938dc68-4ef9-4b1a-958a-2976ca09e79f&loginTime=2015-4-25 22:40:27; loginID=netover", 
					"http://115.236.87.134//xitong/user/info.aspx?action=edit&id="+user.getUserId());
			
			user.setLoginPwd(uu.getLoginPwd());
			user.setMobile(uu.getMobile());
			dao.update(user);
			
		}
		
		//for(int i = 1 ; i < 9 ; i++){
//			getConn("ASP.NET_SessionId=wgkjeh55gworbknun23psrrb; xiTong=identity=0&userID=9&UserName=%e7%bd%91%e5%b0%bd%e7%a7%91%e6%8a%80&validateInfo=e938dc68-4ef9-4b1a-958a-2976ca09e79f&loginTime=2015-4-25 22:40:27; loginID=netover", 
//					"http://115.236.87.134//xitong/user/info.aspx?action=edit&id=633");
		//}
	}
	
}
