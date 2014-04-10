package com.med.dic.utility;

import java.util.Map;

import com.med.dic.model.SMDUser;
import com.opensymphony.xwork2.ActionSupport;

public class AuthenticationUtility extends ActionSupport{

	public static String checkSmdUser(Map<String, Object> session,  SMDUser smdUser) {
		SMDUser user1 = (SMDUser) session.get("userGroup1");
		SMDUser user2 = (SMDUser) session.get("userGroup2");
		if (user2 == null && user1 == null) {
			return LOGIN;
		} else {
			if (user1 != null) {
				if(smdUser.getEmail().toLowerCase().equals(user1.getEmail().toLowerCase())) {
					smdUser.previousUser = true;
				}
				return "userGroup1";
			} else {
				if(smdUser.getEmail().toLowerCase().equals(user2.getEmail().toLowerCase())) {
					smdUser.previousUser = true;
				}
				return "userGroup2";
			}
		}
	}
}
