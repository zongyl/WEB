/**
 * 
 */
package com.longlong.cxf;

import java.net.URL;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;

/**
 * @author zongyl
 *
 */
public class Test {

	public static void main(String[] args){
		
		Client client = connectToService();
		long id = 201209280;
		String appKey = "bf1ab3cdf7b4c08f-10000000";
		String phoneNums = "18606531369";
		String content = "短信正文！~";
		Long time = System.currentTimeMillis();
		Integer result = 8;
		try {
			result = Integer.parseInt(client.invoke("sendSms", id, appKey, phoneNums, content, time)[0].toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(""+result);
	}
	
	/**
	 * 连接服务器
	 * 
	 * @return
	 */
	private static Client connectToService() {
		Client client = null;
		try {

			String url = "http://192.168.101.121:8080/APL-SMSService/SMSService?wsdl";
			// -- 通过动态代理类创建客户端
			URL wsdlURL = new URL(url);
			DynamicClientFactory dcf = DynamicClientFactory.newInstance(); // 创建动态代理类
			client = dcf.createClient(wsdlURL);
			if (client == null) {
				//log.error("Cannot create webService client.");
			}

		} catch (Exception e) {
			//log.error("Create webService client failed! ", e);
		}
		return client;
	}
	
}
