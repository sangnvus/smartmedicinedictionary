package com.med.dic.action;

import com.med.dic.form.MedTypePopupForm;
import com.med.dic.model.MedicineType;

public class MedTypePopupAction extends MedTypePopupForm{

	public String execute() {
		medTypeList = medTypeDAO.listAll();
		medTypeListStr = "";
		for (int i = 0; i < medTypeList.size(); i++) {
			MedicineType medicineType =  medTypeList.get(i);
			medicineType.setMedTypeConcat(medicineType.getMedTypeId() + "," + medicineType.getMedTypeName());
			if(i+1 == medTypeList.size()) {
				medTypeListStr += String.valueOf(medicineType.getMedTypeId());
			} else {
				medTypeListStr += String.valueOf(medicineType.getMedTypeId()) + "~";
			}
		}
		size = medTypeList.size();
		return SUCCESS;
	}
}
