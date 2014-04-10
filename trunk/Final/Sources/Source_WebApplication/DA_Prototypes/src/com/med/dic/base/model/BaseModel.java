package com.med.dic.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BaseModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public String deteleFlag;
	public Date regDate;
	public Date modDate;
	public String regUser;
	public String modUser;

	/**
	 * @return the deteleFlag
	 */
	public String getDeteleFlag() {
		return deteleFlag;
	}

	/**
	 * @param deteleFlag
	 *            the deteleFlag to set
	 */
	public void setDeteleFlag(String deteleFlag) {
		this.deteleFlag = deteleFlag;
	}

	/**
	 * @return the regDate
	 */
	public Date getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the modDate
	 */
	public Date getModDate() {
		return modDate;
	}

	/**
	 * @param modDate
	 *            the modDate to set
	 */
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	/**
	 * @return the regUser
	 */
	public String getRegUser() {
		return regUser;
	}

	/**
	 * @param regUser
	 *            the regUser to set
	 */
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	/**
	 * @return the modUser
	 */
	public String getModUser() {
		return modUser;
	}

	/**
	 * @param modUser
	 *            the modUser to set
	 */
	public void setModUser(String modUser) {
		this.modUser = modUser;
	}
}
