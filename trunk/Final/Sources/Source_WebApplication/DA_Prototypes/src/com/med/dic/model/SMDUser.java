package com.med.dic.model;

import java.io.Serializable;

import com.med.dic.base.model.BaseModel;
import com.med.dic.sercurity.EncryptPassword;

public class SMDUser extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int userId;
	public String email;
	public String password;
	public int userGroup;
	public String name;
	public boolean previousUser = false;

	public SMDUser() {

	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	public SMDUser(String email) {
		super();
		this.email = email;
	}

	/**
	 * @param email
	 * @param password
	 * @param userGroup
	 */
	public SMDUser(String email, String password) {
		super();
		this.email = email;
		this.password = EncryptPassword.md5(password);
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the userGroup
	 */
	public int getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(int userGroup) {
		this.userGroup = userGroup;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
