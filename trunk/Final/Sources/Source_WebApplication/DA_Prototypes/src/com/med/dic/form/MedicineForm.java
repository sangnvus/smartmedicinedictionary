package com.med.dic.form;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.MedicineDAO;
import com.med.dic.dao.MedicineTypeDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.PharmacyMedicineDAO;
import com.med.dic.dao.TypeOfMedicineDAO;
import com.med.dic.model.Medicine;
import com.med.dic.model.MedicineType;

public class MedicineForm extends BaseAction{
	public Medicine medicine = new Medicine();
	public boolean medChoosen = false;
	public MedicineDAO medicineDAO;
	public MedicineTypeDAO medTypeDAO;
	public int medId;
	public String deleteFlag;
	public String medName;
	public String indications;
	public String manufacturer;
	public String ingredients;
	public int typeOfPackageId;
	public String typeOfPackageName;
	public String contraindications;
	public String dosingAndUse;
	public String keyWordMedTypeName;
	public String warning;
	public String storage;
	public String imgPath;
	public String brandName;
	public String genericMedicine;
	public Date regDate;
	public File keyWordMedImage;
	public String keyWordMedImageFileName;
	public String interaction;
	public String similarMedicine;
	public String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
			.format(new Date());
	public boolean copyNew = false;
	public boolean searched = false;
	public boolean detail = false;
	public boolean deleteFlagBoolean = false;
	public boolean edit = false;
	public boolean delete = false;
	public boolean restore = false;
	public boolean addByPharSuccess = false;
	public List<Medicine> medList = new ArrayList<>();
	public MedicineType medTypeObj = new MedicineType();
	public int medTypeId;
	public String medTypeName;
	public int pharmacyId;
	public PharmacyMedicineDAO pharMedDAO;
	public String medTypeIdList;
	public String medTypeNameList;
	public TypeOfMedicineDAO typeOfMedDAO;
	public String pharmacyName;
	public boolean actionSucces;
	public String message;
	public String page;
	public int typeOfMedId;
	
	// admin search module
	public String keyWordMedName;
	public String keyWordMedManufac;
	public String keyWordMedIndication;
	public String keyWordMedContraindication;
	public String keyWordMedIngredients;
	public String accept;
	public String keyWordMedTypeName1;
	
	// pharmacy dao
	public PharmacyDAO pharmacyDAO;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public MedicineDAO getMedicineDAO() {
		return medicineDAO;
	}

	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
	}

	public MedicineTypeDAO getMedTypeDAO() {
		return medTypeDAO;
	}

	public void setMedTypeDAO(MedicineTypeDAO medTypeDAO) {
		this.medTypeDAO = medTypeDAO;
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public String getDeteleFlag() {
		return deleteFlag;
	}

	public void setDeteleFlag(String deteleFlag) {
		this.deleteFlag = deteleFlag;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public int getTypeOfPackageId() {
		return typeOfPackageId;
	}

	public void setTypeOfPackageId(int typeOfPackageId) {
		this.typeOfPackageId = typeOfPackageId;
	}

	public String getTypeOfPackageName() {
		return typeOfPackageName;
	}

	public void setTypeOfPackageName(String typeOfPackageName) {
		this.typeOfPackageName = typeOfPackageName;
	}

	public String getContraindications() {
		return contraindications;
	}

	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	public String getDosingAndUse() {
		return dosingAndUse;
	}

	public void setDosingAndUse(String dosingAndUse) {
		this.dosingAndUse = dosingAndUse;
	}

	public String getKeyWordMedTypeName() {
		return keyWordMedTypeName;
	}

	public void setKeyWordMedTypeName(String keyWordMedTypeName) {
		this.keyWordMedTypeName = keyWordMedTypeName;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGenericMedicine() {
		return genericMedicine;
	}

	public void setGenericMedicine(String genericMedicine) {
		this.genericMedicine = genericMedicine;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public File getKeyWordMedImage() {
		return keyWordMedImage;
	}

	public void setKeyWordMedImage(File keyWordMedImage) {
		this.keyWordMedImage = keyWordMedImage;
	}

	public String getKeyWordMedImageFileName() {
		return keyWordMedImageFileName;
	}

	public void setKeyWordMedImageFileName(String keyWordMedImageFileName) {
		this.keyWordMedImageFileName = keyWordMedImageFileName;
	}

	public boolean isCopyNew() {
		return copyNew;
	}

	public void setCopyNew(boolean copyNew) {
		this.copyNew = copyNew;
	}

	public boolean isSearched() {
		return searched;
	}

	public void setSearched(boolean searched) {
		this.searched = searched;
	}

	public boolean isDetail() {
		return detail;
	}

	public void setDetail(boolean detail) {
		this.detail = detail;
	}

	public boolean getDeleteFlagBoolean() {
		return deleteFlagBoolean;
	}

	public void setDeleteFlagBoolean(boolean deleteFlagBoolean) {
		this.deleteFlagBoolean = deleteFlagBoolean;
	}

	public List<Medicine> getMedList() {
		return medList;
	}

	public void setMedList(List<Medicine> medList) {
		this.medList = medList;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSimilarMedicine() {
		return similarMedicine;
	}

	public void setSimilarMedicine(String similarMedicine) {
		this.similarMedicine = similarMedicine;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public MedicineType getMedTypeObj() {
		return medTypeObj;
	}

	public void setMedTypeObj(MedicineType medTypeObj) {
		this.medTypeObj = medTypeObj;
	}

	public int getMedTypeId() {
		return medTypeId;
	}

	public void setMedTypeId(int medTypeId) {
		this.medTypeId = medTypeId;
	}

	public String getMedTypeName() {
		return medTypeName;
	}

	public void setMedTypeName(String medTypeName) {
		this.medTypeName = medTypeName;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public boolean isRestore() {
		return restore;
	}

	public void setRestore(boolean restore) {
		this.restore = restore;
	}

	/**
	 * @return the pharmacyId
	 */
	public int getPharmacyId() {
		return pharmacyId;
	}

	/**
	 * @param pharmacyId the pharmacyId to set
	 */
	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	/**
	 * @return the pharMedDAO
	 */
	public PharmacyMedicineDAO getPharMedDAO() {
		return pharMedDAO;
	}

	/**
	 * @param pharMedDAO the pharMedDAO to set
	 */
	public void setPharMedDAO(PharmacyMedicineDAO pharMedDAO) {
		this.pharMedDAO = pharMedDAO;
	}

	/**
	 * @return the medTypeIdList
	 */
	public String getMedTypeIdList() {
		return medTypeIdList;
	}

	/**
	 * @param medTypeIdList the medTypeIdList to set
	 */
	public void setMedTypeIdList(String medTypeIdList) {
		this.medTypeIdList = medTypeIdList;
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
	 * @return the medTypeNameList
	 */
	public String getMedTypeNameList() {
		return medTypeNameList;
	}

	/**
	 * @param medTypeNameList the medTypeNameList to set
	 */
	public void setMedTypeNameList(String medTypeNameList) {
		this.medTypeNameList = medTypeNameList;
	}

	/**
	 * @return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}

	/**
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	/**
	 * @return the addByPharSuccess
	 */
	public boolean getAddByPharSuccess() {
		return addByPharSuccess;
	}

	/**
	 * @param addByPharSuccess the addByPharSuccess to set
	 */
	public void setAddByPharSuccess(boolean addByPharSuccess) {
		this.addByPharSuccess = addByPharSuccess;
	}

	/**
	 * @return the actionSucces
	 */
	public boolean getActionSucces() {
		return actionSucces;
	}

	/**
	 * @param actionSucces the actionSucces to set
	 */
	public void setActionSucces(boolean actionSucces) {
		this.actionSucces = actionSucces;
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
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the keyWordMedName
	 */
	public String getKeyWordMedName() {
		return keyWordMedName;
	}

	/**
	 * @param keyWordMedName the keyWordMedName to set
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
	 * @param keyWordMedManufac the keyWordMedManufac to set
	 */
	public void setKeyWordMedManufac(String keyWordMedManufac) {
		this.keyWordMedManufac = keyWordMedManufac;
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
	 * @return the keyWordMedContraindication
	 */
	public String getKeyWordMedContraindication() {
		return keyWordMedContraindication;
	}

	/**
	 * @param keyWordMedContraindication the keyWordMedContraindication to set
	 */
	public void setKeyWordMedContraindication(String keyWordMedContraindication) {
		this.keyWordMedContraindication = keyWordMedContraindication;
	}

	/**
	 * @return the keyWordMedIngredients
	 */
	public String getKeyWordMedIngredients() {
		return keyWordMedIngredients;
	}

	/**
	 * @param keyWordMedIngredients the keyWordMedIngredients to set
	 */
	public void setKeyWordMedIngredients(String keyWordMedIngredients) {
		this.keyWordMedIngredients = keyWordMedIngredients;
	}

	/**
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * @param accept the accept to set
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * @return the keyWordMedTypeName1
	 */
	public String getKeyWordMedTypeName1() {
		return keyWordMedTypeName1;
	}

	/**
	 * @param keyWordMedTypeName1 the keyWordMedTypeName1 to set
	 */
	public void setKeyWordMedTypeName1(String keyWordMedTypeName1) {
		this.keyWordMedTypeName1 = keyWordMedTypeName1;
	}

	/**
	 * @return the pharmacyDAO
	 */
	public PharmacyDAO getPharmacyDAO() {
		return pharmacyDAO;
	}

	/**
	 * @param pharmacyDAO the pharmacyDAO to set
	 */
	public void setPharmacyDAO(PharmacyDAO pharmacyDAO) {
		this.pharmacyDAO = pharmacyDAO;
	}

	/**
	 * @return the typeOfMedId
	 */
	public int getTypeOfMedId() {
		return typeOfMedId;
	}

	/**
	 * @param typeOfMedId the typeOfMedId to set
	 */
	public void setTypeOfMedId(int typeOfMedId) {
		this.typeOfMedId = typeOfMedId;
	}

}
