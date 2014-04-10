package com.med.dic.model;

import com.med.dic.base.model.BaseModel;

public class PharmacyMedicine extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799869994058806844L;
	public int medId;
	public int pharmacyId;
	public String medName;
	public String pharName;
	
	public PharmacyMedicine() {
		
	}

	/**
	 * @return the medId
	 */
	public int getMedId() {
		return medId;
	}

	/**
	 * @param medId
	 *            the medId to set
	 */
	public void setMedId(int medId) {
		this.medId = medId;
	}

	/**
	 * @return the pharmacyId
	 */
	public int getPharmacyId() {
		return pharmacyId;
	}

	/**
	 * @param pharmacyId
	 *            the pharmacyId to set
	 */
	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	/**
	 * @return the medName
	 */
	public String getMedName() {
		return medName;
	}

	/**
	 * @param medName the medName to set
	 */
	public void setMedName(String medName) {
		this.medName = medName;
	}

	/**
	 * @return the pharName
	 */
	public String getPharName() {
		return pharName;
	}

	/**
	 * @param pharName the pharName to set
	 */
	public void setPharName(String pharName) {
		this.pharName = pharName;
	}
}
