package com.med.dic.action;

import com.med.dic.form.ChangePasswordForm;
import com.med.dic.model.SMDUser;
import com.med.dic.sercurity.EncryptPassword;
import com.med.dic.utility.CheckVisitTime;

public class ChangePasswordAction extends ChangePasswordForm{

	public String execute() {
		SMDUser smdUser1 = (SMDUser)session.get("userGroup1");
		SMDUser smdUser2 = (SMDUser)session.get("userGroup2");
		SMDUser smdUser = new SMDUser();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		if(smdUser1 == null && smdUser2 == null) {
			
		} else {
			if(smdUser1 == null) {
				smdUser = smdUser2;
			} else {
				smdUser = smdUser1;
			}
			if(smdUser.getPassword().equals(EncryptPassword.md5(this.password))) {
				smdUser.setPassword(EncryptPassword.md5(this.newPassword));
				smdUserDAO.updateSMDUser(smdUser);
				if(smdUser1 == null) {
					session.remove("userGroup2");
					session.put("userGroup2", smdUser2);
				} else {
					session.remove("userGroup1");
					session.put("userGroup1", smdUser1);
				}
				success = true;
			} else {
				addFieldError("wrongPassword",
						"Mật khẩu cũ của bạn không đúng");
			}
		}
		return SUCCESS;
	}
}
