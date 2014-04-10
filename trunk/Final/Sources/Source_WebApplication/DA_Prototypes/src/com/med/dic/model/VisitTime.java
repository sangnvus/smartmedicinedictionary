package com.med.dic.model;

import com.med.dic.base.model.BaseModel;

public class VisitTime extends BaseModel {

	public String ipAddress;
	public int visitTime;

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the visitTime
	 */
	public int getVisitTime() {
		return visitTime;
	}

	/**
	 * @param visitTime
	 *            the visitTime to set
	 */
	public void setVisitTime(int visitTime) {
		this.visitTime = visitTime;
	}
}
