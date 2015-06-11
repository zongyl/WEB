package com.imooc;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.longlong.model.MKauthor;
import com.longlong.model.MKcourse;
import com.longlong.tools.HibernateDao;
import com.longlong.utils.HttpUtils;

public class Test {

	static Logger log = Logger.getLogger(Test.class);
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//getLess();
		//ss();
		
		HibernateDao.init();
		
		HibernateDao dao = new HibernateDao();
//		List list = dao.list("from MKcourse", 30, 30);
//		int i = 1;
//		for(Iterator it = list.iterator(); it.hasNext();){
//			Object obj = it.next();
//			
//			MKcourse course = (MKcourse)obj;
//			System.out.println(course.getOid()+"l,");
//			//dao.save(getLess(course.getOid()));
//			i++;
//		}
		  
		Long[] ids = new Long[]{ 199l,184l};
		
		for(Long id : ids){
			log.info("_______________________:" + id);
			dao.save(getLess(id));
		}
		
		/**
		 *
9    没有讲师
10   没有讲师
36   没有讲师
48   没有讲师
71   没有讲师
73   没有讲师
200  多个讲师
		 */
		
	}

	public static MKauthor getLess(Long oid) throws Exception{
		String html = HttpUtils.getString("http://www.imooc.com/view/"+oid);
		Document detail = Jsoup.parse(html);
		
		Element el = detail.getElementsByClass("course_teacher").get(0);
		String desc = detail.getElementsByClass("autowrap").html();
		
		MKauthor author = new MKauthor();
		author.setName(el.child(1).child(0).text());
		author.setTitle(el.child(2).html());
		author.setUrl(el.child(1).child(0).attr("href"));
		author.setDescription(desc);
		author.setCourseId(1l);
		return author;
	}
	
	public static void ss() throws Exception{

		HibernateDao.init();
		
		String html = HttpUtils.getString("http://www.imooc.com/course/ajaxlist?pos_id=0&lange_id=0&is_easy=0&sort=last&pagesize=30&page=6");
		
		JSONObject obj = JSON.parseObject(html);
		log.info(obj);
		
		Object o = obj.get("list");
		log.info(o);
		
		HibernateDao dao = new HibernateDao();
		
		JSONArray array = JSON.parseArray(o.toString());
		for(Iterator it = array.iterator(); it.hasNext();){
			Object oo = it.next();
			
			JSONObject jsonobj = JSON.parseObject(oo.toString());
			
			MKcourse course = new MKcourse();
			course.setIsstop(jsonobj.getString("istop"));
			course.setStartTime(Long.parseLong(jsonobj.getString("start_time")));
			course.setCreateTime(Long.parseLong(jsonobj.getString("create_time")));
			course.setUpdateTime(Long.parseLong(jsonobj.getString("update_time")));
			course.setUpdateTimeStr(jsonobj.getString("update_time_str"));
			course.setName(jsonobj.getString("name"));
			course.setOid(Long.parseLong(jsonobj.getString("id")));
			course.setDescription(jsonobj.getString("description"));
			course.setShortDescription(jsonobj.getString("short_description"));
			course.setChapterMedia(jsonobj.getString("chapter_media"));
			course.setMaxChpter(jsonobj.getString("max_chpter"));
			course.setMaxSection("max_section");
			course.setDuration(jsonobj.getString("duration"));
			course.setFinished(jsonobj.getString("finished"));
			course.setPic(jsonobj.getString("pic"));
			course.setNumbers(Long.parseLong(jsonobj.getString("numbers")));
			course.setSectionNum(jsonobj.getString("section_num"));
			course.setPurpose(jsonobj.getString("purpose"));
			course.setPrecondition(jsonobj.getString("precondition"));
			course.setStart(jsonobj.getString("start"));
			course.setIsapplied(jsonobj.getString("isapplied"));
			course.setGreen(jsonobj.getString("is_green"));
			
			dao.save(course); 
			
			log.info("ooooooooooo"+oo);
		}
		 
		
//		String str = "less\u5373\u5b66\u5373\u7528";
//		String str1 = "less即学即用";
//		log.info(str);
//		log.info(str1); 
	}
	
}
