package com.med.dic.model;

public class Hour {
	public int hourId;
	public String hourValue;

	public Hour() {
		
	}
	
	/**
	 * @return the hourId
	 */
	public int getHourId() {
		return hourId;
	}

	/**
	 * @param hourId
	 *            the hourId to set
	 */
	public void setHourId(int hourId) {
		this.hourId = hourId;
	}

	/**
	 * @return the hourValue
	 */
	public String getHourValue() {
		return hourValue;
	}

	/**
	 * @param hourValue
	 *            the hourValue to set
	 */
	public void setHourValue(String hourValue) {
		this.hourValue = hourValue;
	}
}
