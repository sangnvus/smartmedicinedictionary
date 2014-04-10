package com.med.dic.action;

import com.med.dic.form.RepresentativeForm;
import com.med.dic.model.SMDUser;
import com.med.dic.utility.AuthenticationUtility;
import com.opensymphony.xwork2.ActionContext;

public class RepresentativeAction extends RepresentativeForm {

	public String execute() {
		String actionName = ActionContext.getContext().getName();
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if("userGroup2".equals(user) && smdUser.getPreviousUser()) {
			if("chinh-sua-thong-tin-nguoi-dai-dien-action".equals(actionName)) {
				return repInfor();
			} else if("chinh-sua-thong-tin-nguoi-dai-dien".equals(actionName)) {
				return editRepInfor();
			} else if("huy-bo".equals(actionName)) {
				return editRepCancel();
			}
			return SUCCESS;
		} else {
			setValue();
			return user;
		}
	}

	public void setValue() {
		this.edit = false;
		this.editSuccess = false;
	}
	public String repInfor() {
		edit = true;
		this.editSuccess = false;
		degree = this.degreeInput;
		return SUCCESS;
	}

	public String editRepInfor() {
		this.edit = false;
		session.remove("rep");
		rep = repDAO.getRep(this.repId);
		rep.setName(this.representativeName);
		rep.setPhone(this.mobilePhone);
		rep.setDegree(this.degree);
		rep.setLicensureNo(this.licensureNo);
		rep.setDegreeName(repDAO.getDegreeName(this.degree));
		repDAO.updateRep(rep);
		session.put("userName", this.representativeName);
		session.put("rep", this.rep);
		this.editSuccess = true;
		return SUCCESS;
	}
	
	public String editRepCancel() {
		this.edit = false;
		this.editSuccess = false;
		return SUCCESS;
	}
}
