package com.weituitui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Course {

	private Parser parser = null;   
	
	/**
	 * 绗竴娆＄櫥褰曡幏鍙杝ession
	 * @return
	 * @throws IOException
	 */
	public String getSessionId() throws Exception{
		String session_value = null;
		//
		URL url = new URL("http://www.linuxcast.net/Users/attemp_login?user[mail]=alongmail_01@163.com&user[password]=along1988");
		URLConnection urlConnection = url.openConnection();
		urlConnection.connect();
		//鑾峰緱session淇℃伅
		session_value = urlConnection.getHeaderField("Set-Cookie");
		String[] sessionId = session_value.split(";");
		
		//淇濆瓨session淇℃伅
		//urlConnection.setRequestProperty("Cookie", sessionId[0]);
		System.out.println("sessionid:"+sessionId[0]);
		
//		parser = new Parser("http://www.linuxcast.net/Users/attemp_login?user[mail]=alongmail_01@163.com&user[password]=along1988");
//		//top_navi_user_info
//		NodeFilter userFilter = new AndFilter(new TagNameFilter("a"), new HasAttributeFilter("class", "small white radius button"));
//		
//		 NodeList l = parser.parse(userFilter);
		
		return sessionId[0];
		
	}
	
	/**
	 * 绗簩娆￠摼鎺ョ綉椤� 甯︿笂session
	 * @param sessionId
	 * @return
	 * @throws Exception
	 */
	public URLConnection getConn(String sessionId) throws Exception{
		System.out.println("绗簩娆￠摼鎺�sessionid:"+sessionId);
		URLConnection conn = null;
		URL url = new URL("http://www.linuxcast.net/course/11");
		conn = url.openConnection();
		conn.setRequestProperty("Cookie", sessionId);
		conn.setRequestProperty("User-Agent", "new");
		conn.connect();
		
		InputStream is = conn.getInputStream();  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        String response = "";  
        String readLine = null;  
        while((readLine =br.readLine()) != null){  
            //response = br.readLine();  
            response = response + readLine+"\n";  
        }  
        is.close();  
        br.close();  
		System.out.println(response);
		
		return conn;
	}
	
	/**
	 * 瑙ｆ瀽缃戦〉
	 * @param url
	 * @return
	 * @throws ParserException
	 */
	public String parser(String url) throws Exception{
		String ret = "";
		//parser = new Parser(url);
		parser = new Parser(getConn(getSessionId())); 
		//鑾峰彇鐢ㄦ埛淇℃伅
		NodeFilter userFilter = new AndFilter(new TagNameFilter("a"), new HasAttributeFilter("class", "top_navi_user_info"));
		//鑾峰彇璇剧▼鏍囬
		NodeFilter filter = new AndFilter(new TagNameFilter("h2"), new HasAttributeFilter("itemprop", "name"));
		//鑾峰彇璇剧▼瑙嗛
	    NodeFilter videoFilter = new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("id", "wrapper"));
		
//		NodeList list = parser.parse(filter);
//		for(int i=0;i<list.size();i++){
//			HeadingTag title = (HeadingTag)list.elementAt(i);
//			ret = title.getStringText();
//		}
//		 System.out.println(ret.trim());
//		 
//		 parser.reset();
	   System.out.println( parser.getVersion());
	      
		 NodeList l = parser.parse(videoFilter);
		 for(int i=0;i<l.size();i++){
			 Node node = l.elementAt(i);
			 node.getText();
		 }
		return ret;
	}
	
	/**
	 * 娴嬭瘯httpclient鍖�
	 * 
	 * httpclient 寰堢粰鍔涳紝鏈�粓鍦ㄨ娴嬭瘯鏂规硶涓幏鍙栧埌浜嗘墍鏈塴inuxcast.net璇剧▼鐨勭浉鍏宠棰戝湴鍧�
	 * 璇ュ姛鑳界殑鐡堕鍦ㄤ簬锛氱洿鎺ヨ闂浉鍏宠绋嬬殑URL鏃讹紝鍥犺瘑鍒笉浜唖ession淇℃伅鑰岄〉闈㈡樉绀虹櫥褰昫iv锛屽苟娌℃湁鏄剧ず瑙嗛鍦板潃
	 * @throws Exception
	 */
	public void test() throws Exception{
		HttpClient client = new HttpClient();
		//HttpMethod method = new PostMethod("http://www.linuxcast.net/Users/attemp_login?user[mail]=alongmail_01@163.com&user[password]=along1988");
	
		PostMethod post = new PostMethod("http://www.linuxcast.net/Users/attemp_login");
		NameValuePair user = new NameValuePair("user[mail]", "alongmail_01@163.com");
		NameValuePair passwd = new NameValuePair("user[password]", "along1988");
		
		post.setRequestBody(new NameValuePair[] {user, passwd});
		
		
		client.executeMethod(post);
		System.out.println("status:"+post.getStatusCode());
		System.out.println(post.getResponseBodyAsString());
		post.releaseConnection();
		
		  
        CookieSpec cookiespec = CookiePolicy.getDefaultSpec();  
  
        Cookie[] cookies = cookiespec.match("http://www.linuxcast.net", 80, "/", false, client.getState().getCookies());  
  
       if (cookies.length == 0) {  
  
           System.out.println("None");     
  
       } else {  
  
           for (int i = 0; i < cookies.length; i++) {  
  
               System.out.println(cookies[i].toString());     
  
           }  
  
       }  
  
  
       GetMethod get = null;
       /** 11-80   82-128  */
       for(int k =11; k < 80; k++){
       
        get = new GetMethod("http://www.linuxcast.net/course/"+k);  
  
        client.executeMethod(get);  
  
        //System.out.println(get.getResponseBodyAsString());  
        get.getResponseBodyAsString();
  
        get.releaseConnection();  
        
        parser = new Parser(get.getResponseBodyAsString());
        
        //鑾峰彇璇剧▼鏍囬
      	NodeFilter titleFilter = new AndFilter(new TagNameFilter("h2"), new HasAttributeFilter("itemprop", "name"));
        //鑾峰彇璇剧▼瑙嗛
      	NodeFilter videoFiler = new AndFilter(new TagNameFilter("source"), new HasAttributeFilter("type"));
        
      	NodeList list = parser.parse(titleFilter);
		for(int i=0;i<list.size();i++){
			HeadingTag title = (HeadingTag)list.elementAt(i);
			System.out.println(title.getStringText().trim());
		}
      	parser.reset();
        NodeList l = parser.parse(videoFiler);
        for(int i = 0; i < l.size(); i++){
        	Node node = l.elementAt(i);
        	System.out.println(node.getText());
        }
        
       }
	}
	
	public static void main(String[] args) throws Exception{
		Course course = new Course();
		//<h2 itemprop="name"> GNOME鍥惧舰鐣岄潰鍩烘湰鎿嶄綔 </h2>
//		for(int i=11;i<80;i++){
//        	course.parser("http://www.linuxcast.net/course/"+i);
//		}
		//course.parser("http://www.linuxcast.net/course/11");
		
//		String sessionId = "";
//		sessionId = course.getSessionId();
//		course.getConn(sessionId);
		
	//	course.test();
		
		File file = new File("e:\\new.txt");
		InputStreamReader read = new InputStreamReader(new FileInputStream(file), "gbk");
		BufferedReader br = new BufferedReader(read);
		String line;
		while((line = br.readLine())!=null){
//			if(!(line.startsWith("2013")||line.startsWith("璀﹀憡")||(line.indexOf("ogg")!=-1))){
//				System.out.println(line);
//			}
			
			if(line.startsWith("source")){
				System.out.println(line.substring(line.lastIndexOf("/")+1, line.length()-2));
				
				//line.substring(line.indexOf("http"));
			
			}else{
				System.out.println(line);
			}
			
		}
		
	}
	
}
