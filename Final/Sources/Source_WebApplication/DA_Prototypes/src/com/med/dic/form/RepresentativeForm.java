package com.med.dic.form;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.model.Representative;
import com.med.dic.model.SMDUser;

public class RepresentativeForm extends BaseAction {

	public int repId;
	public boolean edit = false;
	public int degreeInput;
	public int degree;
	public String representativeName;
	public String mobilePhone;
	public String licensureNo;
	public Representative rep = new Representative();
	public RepresentativeDAO repDAO;
	public boolean editSuccess = false;

	public RepresentativeForm() {
		super();
	}
	
	public String validateUser() {
		SMDUser sessionUser1 = (SMDUser) session.get("userGroup2");
		SMDUser sessionUser2 = (SMDUser) session.get("userGroup1");
		if (sessionUser1 == null && sessionUser2 == null) {
			return this.login;
		} else {
			if (sessionUser1 == null) {
				return this.group2;
			} else {
				return this.group1;
			}
		}

	}

	/**
	 * @return the repId
	 */
	public int getRepId() {
		return repId;
	}

	/**
	 * @param repId
	 *            the repId to set
	 */
	public void setRepId(int repId) {
		this.repId = repId;
	}

	/**
	 * @return the edit
	 */
	public boolean getEdit() {
		return edit;
	}

	/**
	 * @param edit
	 *            the edit to set
	 */
	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	/**
	 * @return the degreeInput
	 */
	public int getDegreeInput() {
		return degreeInput;
	}

	/**
	 * @param degreeInput
	 *            the degreeInput to set
	 */
	public void setDegreeInput(int degreeInput) {
		this.degreeInput = degreeInput;
	}

	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}

	/**
	 * @return the representativeName
	 */
	public String getRepresentativeName() {
		return representativeName;
	}

	/**
	 * @param representativeName
	 *            the representativeName to set
	 */
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the licensureNo
	 */
	public String getLicensureNo() {
		return licensureNo;
	}

	/**
	 * @param licensureNo
	 *            the licensureNo to set
	 */
	public void setLicensureNo(String licensureNo) {
		this.licensureNo = licensureNo;
	}

	/**
	 * @return the repDAO
	 */
	public RepresentativeDAO getRepDAO() {
		return repDAO;
	}

	/**
	 * @param repDAO
	 *            the repDAO to set
	 */
	public void setRepDAO(RepresentativeDAO repDAO) {
		this.repDAO = repDAO;
	}

	/**
	 * @return the rep
	 */
	public Representative getRep() {
		return rep;
	}

	/**
	 * @param rep
	 *            the rep to set
	 */
	public void setRep(Representative rep) {
		this.rep = rep;
	}

	/**
	 * @return the editSuccess
	 */
	public boolean getEditSuccess() {
		return editSuccess;
	}

	/**
	 * @param editSuccess
	 *            the editSuccess to set
	 */
	public void setEditSuccess(boolean editSuccess) {
		this.editSuccess = editSuccess;
	}
}
