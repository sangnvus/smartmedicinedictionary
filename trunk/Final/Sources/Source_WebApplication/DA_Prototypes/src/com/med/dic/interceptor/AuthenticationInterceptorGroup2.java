package com.med.dic.interceptor;

import java.util.Map;

import com.med.dic.model.SMDUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptorGroup2 extends ActionSupport implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5529965130724380222L;

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
		SMDUser user1 = (SMDUser)session.get("userGroup1");
		SMDUser user2 = (SMDUser)session.get("userGroup2");
		if(user2 == null && user1 == null) {
			return LOGIN;
		} else {
			if(user1 != null) {
				return "userGroup1";
			} else {
				return "userGroup2";
			}
		}
		//return actionInvocation.invoke();
	}
}
