package com.med.dic.model;

public class MedicineType {
	int medTypeId;
	String medTypeName;
	String medTypeConcat;
	
	public MedicineType(){
		
	}


	public String getMedTypeName() {
		return medTypeName;
	}

	public void setMedTypeName(String medTypeName) {
		this.medTypeName = medTypeName;
	}


	public int getMedTypeId() {
		return medTypeId;
	}


	public void setMedTypeId(int medTypeId) {
		this.medTypeId = medTypeId;
	}


	/**
	 * @return the medTypeConcat
	 */
	public String getMedTypeConcat() {
		return medTypeConcat;
	}


	/**
	 * @param medTypeConcat the medTypeConcat to set
	 */
	public void setMedTypeConcat(String medTypeConcat) {
		this.medTypeConcat = medTypeConcat;
	}

}