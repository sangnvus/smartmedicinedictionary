/**
 * 
 */
package com.med.dic.interceptor;

import java.util.Map;

import com.med.dic.model.SMDUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author VAIO
 *
 */
public class AuthenticationInterceptorGroup1 extends ActionSupport implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7422614519372018249L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		SMDUser user = (SMDUser)session.get("userGroup1");
		if(user == null) {
			return LOGIN;
		}
		return actionInvocation.invoke();
	}

}
