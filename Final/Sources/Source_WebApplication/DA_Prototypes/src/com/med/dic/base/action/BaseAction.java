package com.med.dic.base.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.dao.VisitTimeDAO;
import com.med.dic.model.VisitTime;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware{

	public Map<String, Object> session;
	public HttpServletRequest servletRequest;
	public VisitTimeDAO visitTimeDAO;
	public String group1 = "userGroup1";
	public String group2 = "userGroup2";
	public String login = "login";
	public String smdEmail;
	public boolean previousUser = false;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * @return the visitTimeDAO
	 */
	public VisitTimeDAO getVisitTimeDAO() {
		return visitTimeDAO;
	}

	/**
	 * @param visitTimeDAO
	 *            the visitTimeDAO to set
	 */
	public void setVisitTimeDAO(VisitTimeDAO visitTimeDAO) {
		this.visitTimeDAO = visitTimeDAO;
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;

	}

	/**
	 * @return the smdEmail
	 */
	public String getSmdEmail() {
		return smdEmail;
	}

	/**
	 * @param smdEmail the smdEmail to set
	 */
	public void setSmdEmail(String smdEmail) {
		this.smdEmail = smdEmail;
	}

	/**
	 * @return the previousUser
	 */
	public boolean getPreviousUser() {
		return previousUser;
	}

	/**
	 * @param previousUser the previousUser to set
	 */
	public void setPreviousUser(boolean previousUser) {
		this.previousUser = previousUser;
	}
}
