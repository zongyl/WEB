package com.yonghe;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longlong.tools.HibernateDao;
import com.longlong.utils.HttpUtils;
import com.yonghe.model.Order;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	
	static Order order ;
	
	static Map<String, String> maps = new HashMap<String, String>();
	
	static String html = "";
	
	static HibernateDao dao = null;
	
	public static void main(String[] args){
		
		HibernateDao.init();
		
		dao = new HibernateDao();
		//1 - 43865 is null   575136
 		for(int i = 500000; i < 1000000; i++){
			execute(i);
		}
		//execute(500000);
	}
	
	public static void execute(int id){
		try {
			html = HttpUtils.getString("http://www.4000979797.com/online/api/mc/order/detail.json?orderId="+id);
			if("".equals(html)){
				log.info(id+" is null");
			}else{
				JSONObject obj = JSON.parseObject(html);
				maps.clear();
				parseJsonObj(obj);
				save(maps);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param obj
	 */
	
	public static void parseJsonObj(JSONObject obj){
		for(String key : obj.keySet()){
			log.info("key:"+key);
			if(obj.get(key) instanceof JSONObject){
				parseJsonObj((JSONObject)obj.get(key));
			}else{
				maps.put(key, obj.getString(key));
			}
		}
	}
	
	static void save(Map<String, String> obj){
		order = new Order();
		order.setContactPerson(obj.get("contactPerson").toString());
		order.setTotalAmount(obj.get("totalAmount").toString());
		order.setDeliveryAddress(obj.get("deliveryAddress").toString());
		order.setDeliveryAddressComments(obj.get("deliveryAddressComments").toString());
		order.setEmail(obj.get("email")==null?"null":obj.get("email").toString());
		order.setContactPhone(obj.get("contactPhone").toString());
		order.setEstimatedDeliveryTime(obj.get("estimatedDeliveryTime").toString());
		order.setOrderCodeOnline(obj.get("orderCodeOnline").toString());
		order.setOrderId(obj.get("orderId").toString());
		order.setUserComments(obj.get("userComments").toString());
		order.setStoreName(obj.get("storeName").toString());
		order.setOrderItemList(obj.get("orderItemList").toString());
		order.setCommits(obj.get("commits")==null?"null":obj.get("commits").toString());
		dao.save(order);
	}
}
