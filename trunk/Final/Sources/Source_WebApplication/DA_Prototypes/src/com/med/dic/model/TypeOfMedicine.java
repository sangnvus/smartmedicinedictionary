package com.med.dic.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="TypeOfMedicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeOfMedicine implements Serializable{

	public int medId;
	public int typeOfPackageId;
	public String typeOfPackageName;

	/**
	 * @return the medId
	 */
	public int getMedId() {
		return medId;
	}
	/**
	 * @param medId the medId to set
	 */
	public void setMedId(int medId) {
		this.medId = medId;
	}
	/**
	 * @return the typeOfPackageId
	 */
	public int getTypeOfPackageId() {
		return typeOfPackageId;
	}
	/**
	 * @param typeOfPackageId the typeOfPackageId to set
	 */
	public void setTypeOfPackageId(int typeOfPackageId) {
		this.typeOfPackageId = typeOfPackageId;
	}
	/**
	 * @return the typeOfPackageName
	 */
	public String getTypeOfPackageName() {
		return typeOfPackageName;
	}
	/**
	 * @param typeOfPackageName the typeOfPackageName to set
	 */
	public void setTypeOfPackageName(String typeOfPackageName) {
		this.typeOfPackageName = typeOfPackageName;
	}

}
