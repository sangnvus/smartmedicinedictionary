package com.med.dic.form;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.SMDUserDAO;

public class ChangePasswordForm extends BaseAction {

	public SMDUserDAO smdUserDAO;
	public String retypePassword;
	public String newPassword;
	public String password;
	public boolean changeFail = false;
	public boolean success = false;
	
	public ChangePasswordForm() {
		super();
	}

	/**
	 * @return the retypePassword
	 */
	public String getRetypePassword() {
		return retypePassword;
	}

	/**
	 * @param retypePassword
	 *            the retypePassword to set
	 */
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the changeFail
	 */
	public boolean getChangeFail() {
		return changeFail;
	}

	/**
	 * @param changeFail
	 *            the changeFail to set
	 */
	public void setChangeFail(boolean changeFail) {
		this.changeFail = changeFail;
	}

	/**
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the smdUserDAO
	 */
	public SMDUserDAO getSmdUserDAO() {
		return smdUserDAO;
	}

	/**
	 * @param smdUserDAO
	 *            the smdUserDAO to set
	 */
	public void setSmdUserDAO(SMDUserDAO smdUserDAO) {
		this.smdUserDAO = smdUserDAO;
	}
}
