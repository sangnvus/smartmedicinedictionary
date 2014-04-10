package com.med.dic.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.med.dic.dao.VisitTimeDAO;
import com.med.dic.model.VisitTime;

public class CheckVisitTime {

	public static Map<String, Object> checkVisitor(Map<String, Object> session, HttpServletRequest servletRequest, VisitTimeDAO visitTimeDAO) {
		VisitTime sessionVisitor = (VisitTime) session.get("visitor");
		String ipAddress =  null;
		String date = new SimpleDateFormat("yyyyMMdd")
		.format(new Date());
		if (sessionVisitor == null) {
			servletRequest = ServletActionContext.getRequest();
			ipAddress = servletRequest.getHeader("x-forwarded-for");
			if (ipAddress == null) {
				ipAddress = servletRequest.getRemoteAddr();
			}
			sessionVisitor = visitTimeDAO.getVisitTime(ipAddress, date);
			if(sessionVisitor == null) {
				sessionVisitor = new VisitTime();
				sessionVisitor.setRegDate(new Date());
				sessionVisitor.setModDate(new Date());
				sessionVisitor.setIpAddress(ipAddress);
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
					sessionVisitor.setIpAddress(ipAddress);
					sessionVisitor.setVisitTime(1);
					visitTimeDAO.addVisitTime(sessionVisitor);
				}
			}
			session.put("visitor", sessionVisitor);
		} 
		return session;
	}
}
