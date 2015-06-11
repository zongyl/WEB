/**
 * 
 */
package com.longlong.cxf;

import javax.jws.WebService;

/**
 * @author zongyl
 *
 */
@WebService(serviceName = "hello", endpointInterface="com.longlong.cxf.CxfDemo")
public class CxfDemoImpl implements CxfDemo {

	/* (non-Javadoc)
	 * @see com.longlong.cxf.CxfDemo#getExist(java.lang.String)
	 */
	@Override
	public Integer getExist(String name) {
		return "longlong".equals(name)?1:0;
	}

}
