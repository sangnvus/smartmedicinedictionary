package com.med.dic.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.med.dic.dao.VisitTimeDAO;
import com.med.dic.model.VisitTime;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckVisitorTimeSession extends ActionSupport implements
		Interceptor, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7515706087682073196L;
	private HttpServletRequest servletRequest;
	private VisitTimeDAO visitTimeDAO;
	public String ipAddress;

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
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();
		VisitTime sessionVisitor = (VisitTime) session.get("visitor");
		String date = new SimpleDateFormat("yyyyMMdd")
		.format(new Date());
		/*VisitTime visitor = null;*/
		if (sessionVisitor == null) {
			servletRequest = ServletActionContext.getRequest();
			ipAddress = servletRequest.getHeader("x-forwarded-for");
			if (ipAddress == null) {
				ipAddress = servletRequest.getRemoteAddr();
			}
			sessionVisitor = visitTimeDAO.getVisitTime(this.ipAddress, date);
			if(sessionVisitor == null) {
				sessionVisitor = new VisitTime();
				sessionVisitor.setRegDate(new Date());
				sessionVisitor.setModDate(new Date());
				sessionVisitor.setIpAddress(this.ipAddress);
				sessionVisitor.setVisitTime(1);
				visitTimeDAO.addVisitTime(sessionVisitor);
			} else {
				String newDate = new SimpleDateFormat("yyyyMMdd")
						.format(new Date());
				String lastAccessDate = new SimpleDateFormat("yyyyMMdd")
						.format(sessionVisitor.getModDate());
				if (newDate.equals(lastAccessDate)) {
					int count = sessionVisitor.getVisitTime() + 1;
					sessionVisitor.setModDate(new Date());
					sessionVisitor.setVisitTime(count);
					visitTimeDAO.updateVisitTime(sessionVisitor);
				} else {
					sessionVisitor.setRegDate(new Date());
					sessionVisitor.setModDate(new Date());
					sessionVisitor.setIpAddress(this.ipAddress);
					sessionVisitor.setVisitTime(1);
					visitTimeDAO.addVisitTime(sessionVisitor);
				}
			}
			session.put("visitor", sessionVisitor);
		} 
		return SUCCESS;
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
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
