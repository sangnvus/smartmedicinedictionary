package com.med.dic.model;

import java.io.Serializable;

import com.med.dic.base.model.BaseModel;

public class Representative extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int repId;
	public String email;
	public String name;
	public String phone;
	public int degree;
	public String degreeName;
	public String licensureNo;
	public String cardNumber;
	public int userId;

	public Representative() {

	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the degreeName
	 */
	public String getDegreeName() {
		return degreeName;
	}

	/**
	 * @param degreeName the degreeName to set
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
