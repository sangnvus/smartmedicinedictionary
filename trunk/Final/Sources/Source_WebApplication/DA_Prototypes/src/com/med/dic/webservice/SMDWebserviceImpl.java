package com.med.dic.webservice;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.med.dic.dao.MedicineDAO;
import com.med.dic.dao.MedicineTypeDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.TypeOfMedicineDAO;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;

@WebService(endpointInterface = "com.med.dic.webservice.SMDWebserviceInterface")
public class SMDWebserviceImpl implements SMDWebserviceInterface {

	private MedicineDAO medicineDAO;
	private TypeOfMedicineDAO typeOfMedDAO;
	private MedicineTypeDAO medTypeDAO;
	private PharmacyDAO pharmacyDAO;

	/**
	 * @param pharmacyDAO the pharmacyDAO to set
	 */
	public void setPharmacyDAO(PharmacyDAO pharmacyDAO) {
		this.pharmacyDAO = pharmacyDAO;
	}


	@WebMethod(exclude=true)
	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
	}


	/**
	 * @param medTypeDAO the medTypeDAO to set
	 */
	public void setMedTypeDAO(MedicineTypeDAO medTypeDAO) {
		this.medTypeDAO = medTypeDAO;
	}

	/**
	 * @param typeOfMedDAO the typeOfMedDAO to set
	 */
	public void setTypeOfMedDAO(TypeOfMedicineDAO typeOfMedDAO) {
		this.typeOfMedDAO = typeOfMedDAO;
	}

	@Override
	@WebMethod(operationName="getListMedicineByName")
	public ArrayList<Medicine> getListMedicineByName(String medName) {
		ArrayList<Medicine>  medicine = medicineDAO.searchByName(medName);
		return medicine;
	}

	@Override
	@WebMethod(operationName="getMedicinesByPharmacyId")
	public ArrayList<Medicine> getMedicinesByPharmacyId(int pharId) {
		ArrayList<Medicine>  medicine = medicineDAO.searchMedByPharId(pharId);
		return medicine;
	}

	@Override
	@WebMethod(operationName="getPackageName")
	public ArrayList<String> getPackageName() {
		ArrayList<String> packageName = medTypeDAO.listPackageName();
		return packageName;
	}

	@Override
	@WebMethod(operationName="getTypeOfPackage")
	public String getTypeOfPackage(int medId) {
		String packageType = typeOfMedDAO.getTypeOfPackage(medId);
		return packageType;
	}


	@Override
	@WebMethod(operationName="getListPharmacyLocation")
	public ArrayList<Pharmacy> getListPharmacyLocation(double lat, double lon) {
		ArrayList<Pharmacy> pharmacy = pharmacyDAO.listPharmacyLocation(lat, lon);
		return pharmacy;
	}


	@Override
	@WebMethod(operationName="getPharmacyByNameAndAddress")
	public ArrayList<Pharmacy> getPharmacyByNameAndAddress(String name, String address) {
		ArrayList<Pharmacy> pharmacy = pharmacyDAO.getPharmacyByNameAndAddr(name, address);
		return pharmacy;
	}


	@Override
	@WebMethod(operationName="getPharmacyByMedicineId")
	public ArrayList<Pharmacy> getPharmacyByMedicineId(int medId) {
		ArrayList<Pharmacy> pharmacy = pharmacyDAO.getPharmacyByMedId(medId);
		return pharmacy;
	}

}
