package com.med.dic.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.AdvertiseDAO;
import com.med.dic.dao.CityDAO;
import com.med.dic.dao.MedicineDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.PharmacyMedicineDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.TypeOfMedicineDAO;
import com.med.dic.model.Advertise;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;

public class SearchMedicineForm extends BaseAction {

	public List<String> autoString = new ArrayList<>();
	public boolean noResult = false;
	public boolean search = false;
	public boolean detail = false;
	public boolean detailed = false;
	public boolean refresh;
	public boolean deleteFlagBoolean = false;
	public String keyWordMedName;
	public String advancedKeyWordMedName;
	public String keyWordMedManufac;
	public String keyWordMedContraindication;
	public String keyWordMedIndication;
	public String keyWordMedIngredients;
	public String keyWordMedTypeName;
	public int medId;
	public String deteleFlag;
	public List<Medicine> medList = new ArrayList<>();
	public Medicine medicine = new Medicine();
	public MedicineDAO medicineDAO;
	public Medicine medDetail = new Medicine();
	public PharmacyMedicineDAO pharMedDAO;
	public PharmacyDAO pharmacyDAO;
	public String page;
	public boolean hasvideo = true;

	// refer to pharmacy
	public Pharmacy pharmacy;
	public String pharmacyName;
	public int pharmacyId;
	public BigDecimal latitude;
	public BigDecimal longtitude;
	public boolean referPharmacy = false;
	public boolean medChoosen = false;
	
	// refer med list
	public List<Medicine> medicineList;
	public int typeOfPackage;
	public int medTypeStr;
	public RepresentativeDAO repDAO;
	public Representative rep;
	public int medTypeStr1;
	public TypeOfMedicineDAO typeOfMedDAO;
	public String degreeStr;
	public String cityStr;
	public String districtStr;
	public String typeOfBusinessStr;
	public String email;
	public int count;
	public boolean failedAction;
	public boolean successedAction;
	public String message;
	
	// City DAO
	public CityDAO cityDAO;
	
	// advertising dao
	public AdvertiseDAO advertiseDAO;
	public List<Advertise> adLineOne = new ArrayList<>();
	public List<Advertise> adLineTwo = new ArrayList<>();
	
	// search for advanced or not
	public boolean searchAdvanced = false;
	
	/**
	 * @return the autoString
	 */
	public List<String> getAutoString() {
		return autoString;
	}

	/**
	 * @param autoString
	 *            the autoString to set
	 */
	public void setAutoString(List<String> autoString) {
		this.autoString = autoString;
	}

	/**
	 * @return the noResult
	 */
	public boolean getNoResult() {
		return noResult;
	}

	/**
	 * @param noResult
	 *            the noResult to set
	 */
	public void setNoResult(boolean noResult) {
		this.noResult = noResult;
	}

	/**
	 * @return the search
	 */
	public boolean getSearch() {
		return search;
	}

	/**
	 * @param search
	 *            the search to set
	 */
	public void setSearch(boolean search) {
		this.search = search;
	}

	/**
	 * @return the detail
	 */
	public boolean getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(boolean detail) {
		this.detail = detail;
	}

	/**
	 * @return the deleteFlagBoolean
	 */
	public boolean getDeleteFlagBoolean() {
		return deleteFlagBoolean;
	}

	/**
	 * @param deleteFlagBoolean
	 *            the deleteFlagBoolean to set
	 */
	public void setDeleteFlagBoolean(boolean deleteFlagBoolean) {
		this.deleteFlagBoolean = deleteFlagBoolean;
	}

	/**
	 * @return the keyWordMedName
	 */
	public String getKeyWordMedName() {
		return keyWordMedName;
	}

	/**
	 * @param keyWordMedName
	 *            the keyWordMedName to set
	 */
	public void setKeyWordMedName(String keyWordMedName) {
		this.keyWordMedName = keyWordMedName;
	}

	/**
	 * @return the keyWordMedManufac
	 */
	public String getKeyWordMedManufac() {
		return keyWordMedManufac;
	}

	/**
	 * @param keyWordMedManufac
	 *            the keyWordMedManufac to set
	 */
	public void setKeyWordMedManufac(String keyWordMedManufac) {
		this.keyWordMedManufac = keyWordMedManufac;
	}

	/**
	 * @return the keyWordMedContraindication
	 */
	public String getKeyWordMedContraindication() {
		return keyWordMedContraindication;
	}

	/**
	 * @param keyWordMedContraindication
	 *            the keyWordMedContraindication to set
	 */
	public void setKeyWordMedContraindication(String keyWordMedContraindication) {
		this.keyWordMedContraindication = keyWordMedContraindication;
	}

	/**
	 * @return the keyWordMedIndication
	 */
	public String getKeyWordMedIndication() {
		return keyWordMedIndication;
	}

	/**
	 * @param keyWordMedIndication the keyWordMedIndication to set
	 */
	public void setKeyWordMedIndication(String keyWordMedIndication) {
		this.keyWordMedIndication = keyWordMedIndication;
	}

	/**
	 * @return the keyWordMedIngredients
	 */
	public String getKeyWordMedIngredients() {
		return keyWordMedIngredients;
	}

	/**
	 * @param keyWordMedIngredients
	 *            the keyWordMedIngredients to set
	 */
	public void setKeyWordMedIngredients(String keyWordMedIngredients) {
		this.keyWordMedIngredients = keyWordMedIngredients;
	}

	/**
	 * @return the keyWordMedTypeName
	 */
	public String getKeyWordMedTypeName() {
		return keyWordMedTypeName;
	}

	/**
	 * @param keyWordMedTypeName
	 *            the keyWordMedTypeName to set
	 */
	public void setKeyWordMedTypeName(String keyWordMedTypeName) {
		this.keyWordMedTypeName = keyWordMedTypeName;
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
	 * @return the deteleFlag
	 */
	public String getDeteleFlag() {
		return deteleFlag;
	}

	/**
	 * @param deteleFlag
	 *            the deteleFlag to set
	 */
	public void setDeteleFlag(String deteleFlag) {
		this.deteleFlag = deteleFlag;
	}

	/**
	 * @return the medList
	 */
	public List<Medicine> getMedList() {
		return medList;
	}

	/**
	 * @param medList
	 *            the medList to set
	 */
	public void setMedList(List<Medicine> medList) {
		this.medList = medList;
	}

	/**
	 * @return the medicine
	 */
	public Medicine getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine
	 *            the medicine to set
	 */
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	/**
	 * @return the medicineDAO
	 */
	public MedicineDAO getMedicineDAO() {
		return medicineDAO;
	}

	/**
	 * @param medicineDAO
	 *            the medicineDAO to set
	 */
	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
	}

	/**
	 * @return the medDetail
	 */
	public Medicine getMedDetail() {
		return medDetail;
	}

	/**
	 * @param medDetail
	 *            the medDetail to set
	 */
	public void setMedDetail(Medicine medDetail) {
		this.medDetail = medDetail;
	}

	/**
	 * @return the refresh
	 */
	public boolean getRefresh() {
		return refresh;
	}

	/**
	 * @param refresh
	 *            the refresh to set
	 */
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the pharMedDAO
	 */
	public PharmacyMedicineDAO getPharMedDAO() {
		return pharMedDAO;
	}

	/**
	 * @param pharMedDAO
	 *            the pharMedDAO to set
	 */
	public void setPharMedDAO(PharmacyMedicineDAO pharMedDAO) {
		this.pharMedDAO = pharMedDAO;
	}

	/**
	 * @return the pharmacyDAO
	 */
	public PharmacyDAO getPharmacyDAO() {
		return pharmacyDAO;
	}

	/**
	 * @param pharmacyDAO
	 *            the pharmacyDAO to set
	 */
	public void setPharmacyDAO(PharmacyDAO pharmacyDAO) {
		this.pharmacyDAO = pharmacyDAO;
	}

	/**
	 * @return the hasvideo
	 */
	public boolean getHasvideo() {
		return hasvideo;
	}

	/**
	 * @param hasvideo
	 *            the hasvideo to set
	 */
	public void setHasvideo(boolean hasvideo) {
		this.hasvideo = hasvideo;
	}

	/**
	 * @return the pharmacy
	 */
	public Pharmacy getPharmacy() {
		return pharmacy;
	}

	/**
	 * @param pharmacy
	 *            the pharmacy to set
	 */
	public void setPharmacy(Pharmacy pharmacy) {
		this.pharmacy = pharmacy;
	}

	/**
	 * @return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}

	/**
	 * @param pharmacyName
	 *            the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
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
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longtitude
	 */
	public BigDecimal getLongtitude() {
		return longtitude;
	}

	/**
	 * @param longtitude
	 *            the longtitude to set
	 */
	public void setLongtitude(BigDecimal longtitude) {
		this.longtitude = longtitude;
	}

	/**
	 * @return the referPharmacy
	 */
	public boolean getReferPharmacy() {
		return referPharmacy;
	}

	/**
	 * @param referPharmacy
	 *            the referPharmacy to set
	 */
	public void setReferPharmacy(boolean referPharmacy) {
		this.referPharmacy = referPharmacy;
	}

	/**
	 * @return the medChoosen
	 */
	public boolean getMedChoosen() {
		return medChoosen;
	}

	/**
	 * @param medChoosen the medChoosen to set
	 */
	public void setMedChoosen(boolean medChoosen) {
		this.medChoosen = medChoosen;
	}

	/**
	 * @return the medicineList
	 */
	public List<Medicine> getMedicineList() {
		return medicineList;
	}

	/**
	 * @param medicineList the medicineList to set
	 */
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}

	/**
	 * @return the typeOfPackage
	 */
	public int getTypeOfPackage() {
		return typeOfPackage;
	}

	/**
	 * @param typeOfPackage the typeOfPackage to set
	 */
	public void setTypeOfPackage(int typeOfPackage) {
		this.typeOfPackage = typeOfPackage;
	}

	/**
	 * @return the medTypeStr
	 */
	public int getMedTypeStr() {
		return medTypeStr;
	}

	/**
	 * @param medTypeStr the medTypeStr to set
	 */
	public void setMedTypeStr(int medTypeStr) {
		this.medTypeStr = medTypeStr;
	}

	/**
	 * @return the repDAO
	 */
	public RepresentativeDAO getRepDAO() {
		return repDAO;
	}

	/**
	 * @param repDAO the repDAO to set
	 */
	public void setRepDAO(RepresentativeDAO repDAO) {
		this.repDAO = repDAO;
	}

	/**
	 * @return the rep
	 */
	public Representative getRep() {
		return rep;
	}

	/**
	 * @param rep the rep to set
	 */
	public void setRep(Representative rep) {
		this.rep = rep;
	}

	/**
	 * @return the searchAdvanced
	 */
	public boolean getSearchAdvanced() {
		return searchAdvanced;
	}

	/**
	 * @param searchAdvanced the searchAdvanced to set
	 */
	public void setSearchAdvanced(boolean searchAdvanced) {
		this.searchAdvanced = searchAdvanced;
	}

	/**
	 * @return the advancedKeyWordMedName
	 */
	public String getAdvancedKeyWordMedName() {
		return advancedKeyWordMedName;
	}

	/**
	 * @param advancedKeyWordMedName the advancedKeyWordMedName to set
	 */
	public void setAdvancedKeyWordMedName(String advancedKeyWordMedName) {
		this.advancedKeyWordMedName = advancedKeyWordMedName;
	}

	/**
	 * @return the medTypeStr1
	 */
	public int getMedTypeStr1() {
		return medTypeStr1;
	}

	/**
	 * @param medTypeStr1 the medTypeStr1 to set
	 */
	public void setMedTypeStr1(int medTypeStr1) {
		this.medTypeStr1 = medTypeStr1;
	}
	

	/**
	 * @return the detailed
	 */
	public boolean getDetailed() {
		return detailed;
	}

	/**
	 * @param detailed the detailed to set
	 */
	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	/**
	 * @return the typeOfMedDAO
	 */
	public TypeOfMedicineDAO getTypeOfMedDAO() {
		return typeOfMedDAO;
	}

	/**
	 * @param typeOfMedDAO the typeOfMedDAO to set
	 */
	public void setTypeOfMedDAO(TypeOfMedicineDAO typeOfMedDAO) {
		this.typeOfMedDAO = typeOfMedDAO;
	}

	/**
	 * @return the degreeStr
	 */
	public String getDegreeStr() {
		return degreeStr;
	}

	/**
	 * @param degreeStr the degreeStr to set
	 */
	public void setDegreeStr(String degreeStr) {
		this.degreeStr = degreeStr;
	}

	/**
	 * @return the cityStr
	 */
	public String getCityStr() {
		return cityStr;
	}

	/**
	 * @param cityStr the cityStr to set
	 */
	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}

	/**
	 * @return the districtStr
	 */
	public String getDistrictStr() {
		return districtStr;
	}

	/**
	 * @param districtStr the districtStr to set
	 */
	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
	}

	/**
	 * @return the typeOfBusinessStr
	 */
	public String getTypeOfBusinessStr() {
		return typeOfBusinessStr;
	}

	/**
	 * @param typeOfBusinessStr the typeOfBusinessStr to set
	 */
	public void setTypeOfBusinessStr(String typeOfBusinessStr) {
		this.typeOfBusinessStr = typeOfBusinessStr;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the failedAction
	 */
	public boolean getFailedAction() {
		return failedAction;
	}

	/**
	 * @param failedAction the failedAction to set
	 */
	public void setFailedAction(boolean failedAction) {
		this.failedAction = failedAction;
	}

	/**
	 * @return the successedAction
	 */
	public boolean getSuccessedAction() {
		return successedAction;
	}

	/**
	 * @param successedAction the successedAction to set
	 */
	public void setSuccessedAction(boolean successedAction) {
		this.successedAction = successedAction;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the cityDAO
	 */
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	/**
	 * @param cityDAO the cityDAO to set
	 */
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	/**
	 * @return the advertiseDAO
	 */
	public AdvertiseDAO getAdvertiseDAO() {
		return advertiseDAO;
	}

	/**
	 * @param advertiseDAO the advertiseDAO to set
	 */
	public void setAdvertiseDAO(AdvertiseDAO advertiseDAO) {
		this.advertiseDAO = advertiseDAO;
	}

	/**
	 * @return the adLineOne
	 */
	public List<Advertise> getAdLineOne() {
		return adLineOne;
	}

	/**
	 * @param adLineOne the adLineOne to set
	 */
	public void setAdLineOne(List<Advertise> adLineOne) {
		this.adLineOne = adLineOne;
	}

	/**
	 * @return the adLineTwo
	 */
	public List<Advertise> getAdLineTwo() {
		return adLineTwo;
	}

	/**
	 * @param adLineTwo the adLineTwo to set
	 */
	public void setAdLineTwo(List<Advertise> adLineTwo) {
		this.adLineTwo = adLineTwo;
	}
}
