package com.med.dic.utility;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.model.Pharmacy;

public class CountSearchedPharmacy extends BaseAction{

	public static void countSearchedPhar(Pharmacy pharmacy, PharmacyDAO pharmacyDAO) {
		int searchedTime = pharmacy.getSearchedTime();
		searchedTime++;
		pharmacy.setSearchedTime(searchedTime);
		pharmacyDAO.updatePharmacy(pharmacy);
	}
}
