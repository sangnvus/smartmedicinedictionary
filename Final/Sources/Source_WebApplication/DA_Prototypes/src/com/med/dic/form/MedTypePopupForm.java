package com.med.dic.form;

import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.MedicineTypeDAO;
import com.med.dic.model.MedicineType;

public class MedTypePopupForm extends BaseAction{

	public MedicineTypeDAO medTypeDAO;
	public List<MedicineType> medTypeList;
	public String medTypeListStr;
	public String medTypeLst;
	
	public int size;

	/**
	 * @return the medTypeList
	 */
	public List<MedicineType> getMedTypeList() {
		return medTypeList;
	}

	/**
	 * @param medTypeList the medTypeList to set
	 */
	public void setMedTypeList(List<MedicineType> medTypeList) {
		this.medTypeList = medTypeList;
	}

	/**
	 * @return the medTypeDAO
	 */
	public MedicineTypeDAO getMedTypeDAO() {
		return medTypeDAO;
	}

	/**
	 * @param medTypeDAO the medTypeDAO to set
	 */
	public void setMedTypeDAO(MedicineTypeDAO medTypeDAO) {
		this.medTypeDAO = medTypeDAO;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the medTypeListStr
	 */
	public String getMedTypeListStr() {
		return medTypeListStr;
	}

	/**
	 * @param medTypeListStr the medTypeListStr to set
	 */
	public void setMedTypeListStr(String medTypeListStr) {
		this.medTypeListStr = medTypeListStr;
	}

	/**
	 * @return the medTypeLst
	 */
	public String getMedTypeLst() {
		return medTypeLst;
	}

	/**
	 * @param medTypeLst the medTypeLst to set
	 */
	public void setMedTypeLst(String medTypeLst) {
		this.medTypeLst = medTypeLst;
	}
}
