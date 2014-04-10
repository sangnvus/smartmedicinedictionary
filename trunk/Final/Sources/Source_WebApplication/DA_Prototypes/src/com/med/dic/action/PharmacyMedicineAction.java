package com.med.dic.action;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.PharmacyMedicineDAO;
import com.med.dic.dao.VisitTimeDAO;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.PharmacyMedicine;
import com.med.dic.utility.CheckVisitTime;
import com.opensymphony.xwork2.ActionSupport;

public class PharmacyMedicineAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PharmacyMedicine pharmacyMedicine = new PharmacyMedicine();
	
	public PharmacyMedicineAction() {
		
	}
	
	public void addNewPharmacyMedicine(Medicine medicine, int pharmacyId, PharmacyMedicineDAO pharMedDAO, Map<String, Object> session, HttpServletRequest servletRequest, VisitTimeDAO visitTimeDAO, PharmacyDAO pharmacyDAO){
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		try{
		Pharmacy pharmacy = pharmacyDAO.getPharmacy(pharmacyId);
		pharmacyMedicine.pharmacyId = pharmacyId;
		pharmacyMedicine.deteleFlag = medicine.deteleFlag;
		pharmacyMedicine.medId = medicine.medId;
		pharmacyMedicine.medName = medicine.getMedName();
		pharmacyMedicine.pharName = pharmacy.getPharmacyName();
		pharmacyMedicine.modDate = medicine.modDate;
		pharmacyMedicine.regDate = medicine.regDate;
		pharmacyMedicine.modUser = medicine.modUser;
		pharmacyMedicine.regUser = medicine.regUser;
		pharMedDAO.addPharMed(pharmacyMedicine);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @return the pharmacyMedicine
	 */
	public PharmacyMedicine getPharmacyMedicine() {
		return pharmacyMedicine;
	}

	/**
	 * @param pharmacyMedicine the pharmacyMedicine to set
	 */
	public void setPharmacyMedicine(PharmacyMedicine pharmacyMedicine) {
		this.pharmacyMedicine = pharmacyMedicine;
	}

}
