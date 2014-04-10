package com.med.dic.utility;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.MedicineDAO;
import com.med.dic.model.Medicine;

public class CountSearchedMedicine extends BaseAction {

	public static void countSearchedMed(Medicine medicine, MedicineDAO medicineDAO) {
		int searchedTime = medicine.getSearchedTime();
		searchedTime++;
		medicine.setSearchedTime(searchedTime);
		medicineDAO.updateMedicine(medicine);
	}
}
