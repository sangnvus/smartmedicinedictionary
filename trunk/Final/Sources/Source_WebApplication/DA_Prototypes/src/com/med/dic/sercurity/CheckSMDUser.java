package com.med.dic.sercurity;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.med.dic.base.action.BaseAction;
import com.med.dic.model.SMDUser;

public class CheckSMDUser extends BaseAction{

	public static boolean checkSMDUser(Map<String, Object> session, HttpServletRequest servletRequest) {
		SMDUser user1 = (SMDUser) session.get("userGroup1");
		if (user1 == null) {
			return false;
		} else {
			session.put("regUser", user1.getRegUser());
			return true;
		}
	}
}
