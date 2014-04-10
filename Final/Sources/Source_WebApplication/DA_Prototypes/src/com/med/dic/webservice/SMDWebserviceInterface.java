package com.med.dic.webservice;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;

@WebService(name="SMDWebserviceInterface")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public interface SMDWebserviceInterface {
	/*
	 * get list medicine by name input
	 * @param medName String
	 */
	@WebMethod(operationName="getListMedicineByName")
	ArrayList<Medicine> getListMedicineByName(@WebParam(name="medName")String medName);

	/*
	 * get package name
	 */
	@WebMethod(operationName="getPackageName")
	ArrayList<String> getPackageName();

	/*
	 * get type of package for the medicine
	 * @param medId int
	 */
	@WebMethod(operationName="getTypeOfPackage")
	String getTypeOfPackage(@WebParam(name="medId")int medId);

	/*
	 * get medicines is selling on pharmacy by pharmacy Id
	 */
	@WebMethod(operationName="getMedicinesByPharmacyId")
	ArrayList<Medicine> getMedicinesByPharmacyId(@WebParam(name="pharId")int pharId);

	/*
	 * get medicines is selling on pharmacy by pharmacy Id
	 */
	@WebMethod(operationName="getListPharmacyLocation")
	ArrayList<Pharmacy> getListPharmacyLocation(@WebParam(name="lat")double lat, @WebParam(name="lon")double lon);

	/*
	 * get pharmacy data by name and address
	 */
	@WebMethod(operationName="getPharmacyByNameAndAddress")
	ArrayList<Pharmacy> getPharmacyByNameAndAddress(@WebParam(name="pharmacyName")String name, @WebParam(name="address")String address);

	/*
	 * get pharmacy data by medicine Id
	 */
	@WebMethod(operationName="getPharmacyByMedicineId")
	ArrayList<Pharmacy> getPharmacyByMedicineId(@WebParam(name="medicineId")int medId);
}
