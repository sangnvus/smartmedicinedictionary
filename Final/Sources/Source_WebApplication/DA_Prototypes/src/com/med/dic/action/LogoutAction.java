package com.med.dic.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 3309096601476699765L;
	private Map<String, Object> session;

	public String execute() {
		session.remove("userGroup2");
		session.remove("userGroup1");
		session.remove("rep");
		session.remove("pharmacyList");
		session.remove("smdEmail");
		session.remove("userName");
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
