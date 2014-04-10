package com.med.dic.model;

import java.io.Serializable;

public class Symptoms implements Serializable{

	public int symptomId;
	public String symptomName;
	
	public Symptoms() {
		
	}

	/**
	 * @return the symptomId
	 */
	public int getSymptomId() {
		return symptomId;
	}

	/**
	 * @param symptomId the symptomId to set
	 */
	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}

	/**
	 * @return the symptomName
	 */
	public String getSymptomName() {
		return symptomName;
	}

	/**
	 * @param symptomName the symptomName to set
	 */
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
}
