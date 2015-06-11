/**
 * 
 */
package com.longlong.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author zongyl
 *@WebService( serviceName = "hello", targetNamespace = "http://ws.hello.com")
 */
@WebService
public interface CxfDemo {

	Integer getExist(@WebParam(name = "name") String name);
	
}
