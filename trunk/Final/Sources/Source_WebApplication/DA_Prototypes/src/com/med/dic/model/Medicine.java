package com.med.dic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.med.dic.base.model.BaseModel;

@XmlRootElement(name="Medicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicine extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public int medId;
	public int pharId;
	public String medName;
	public String manufacturer;
	public String indications;
	public String contraindications;
	public String dosingAndUse;
	public String ingredients;
	public String typeOfPackageName;
	public int typeOfPackageId;
	public String warning;
	public String storage;
	public String imgPath;
	public String genericMedicine;
	public String brandName;
	public String interaction;
	public String similarMedicine;
	public List<Pharmacy> pharmacyList = new ArrayList<>();
	public String accept;
	public int addedByPharId;
	public int searchedTime;
	public boolean acceptFlag = false;
	public List<String> indicationList = new ArrayList<>();
	public List<String> contraindicationList = new ArrayList<>();
	public List<String> dosingAndUseList = new ArrayList<>();
	public List<String> warningList = new ArrayList<>();
	public List<String> ingredientsList = new ArrayList<>();
	public List<String> storageList = new ArrayList<>();
	public List<String> interactionList = new ArrayList<>();
	public List<String> similarMedicineList = new ArrayList<>();
	public List<String> pharIdList = new ArrayList<>();
	public List<String> pharNameList = new ArrayList<>();
	public String pharmacyName;

	public int getMedId() {
		return medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
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

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getTypeOfPackageName() {
		return typeOfPackageName;
	}

	public void setTypeOfPackageName(String typeOfPackageName) {
		this.typeOfPackageName = typeOfPackageName;
	}

	public int getTypeOfPackageId() {
		return typeOfPackageId;
	}

	public void setTypeOfPackageId(int typeOfPackageId) {
		this.typeOfPackageId = typeOfPackageId;
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

	public String getGenericMedicine() {
		return genericMedicine;
	}

	public void setGenericMedicine(String genericMedicine) {
		this.genericMedicine = genericMedicine;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public String getSimilarMedicine() {
		return similarMedicine;
	}

	public void setSimilarMedicine(String similarMedicine) {
		this.similarMedicine = similarMedicine;
	}

	/**
	 * @return the pharId
	 */
	public int getPharId() {
		return pharId;
	}

	/**
	 * @param pharId
	 *            the pharId to set
	 */
	public void setPharId(int pharId) {
		this.pharId = pharId;
	}

	/**
	 * @return the pharmacyList
	 */
	public List<Pharmacy> getPharmacyList() {
		return pharmacyList;
	}

	/**
	 * @param pharmacyList
	 *            the pharmacyList to set
	 */
	public void setPharmacyList(List<Pharmacy> pharmacyList) {
		this.pharmacyList = pharmacyList;
	}

	/**
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * @param accept
	 *            the accept to set
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	/**
	 * @return the addedByPharId
	 */
	public int getAddedByPharId() {
		return addedByPharId;
	}

	/**
	 * @param addedByPharId
	 *            the addedByPharId to set
	 */
	public void setAddedByPharId(int addedByPharId) {
		this.addedByPharId = addedByPharId;
	}

	/**
	 * @return the acceptFlag
	 */
	public boolean getAcceptFlag() {
		return acceptFlag;
	}

	/**
	 * @param acceptFlag
	 *            the acceptFlag to set
	 */
	public void setAcceptFlag(boolean acceptFlag) {
		this.acceptFlag = acceptFlag;
	}

	/**
	 * @return the searchedTime
	 */
	public int getSearchedTime() {
		return searchedTime;
	}

	/**
	 * @param searchedTime
	 *            the searchedTime to set
	 */
	public void setSearchedTime(int searchedTime) {
		this.searchedTime = searchedTime;
	}

	/**
	 * @return the indicationList
	 */
	public List<String> getIndicationList() {
		return indicationList;
	}

	/**
	 * @param indicationList the indicationList to set
	 */
	public void setIndicationList(List<String> indicationList) {
		this.indicationList = indicationList;
	}

	/**
	 * @return the contraindicationList
	 */
	public List<String> getContraindicationList() {
		return contraindicationList;
	}

	/**
	 * @param contraindicationList the contraindicationList to set
	 */
	public void setContraindicationList(List<String> contraindicationList) {
		this.contraindicationList = contraindicationList;
	}

	/**
	 * @return the dosingAndUseList
	 */
	public List<String> getDosingAndUseList() {
		return dosingAndUseList;
	}

	/**
	 * @param dosingAndUseList the dosingAndUseList to set
	 */
	public void setDosingAndUseList(List<String> dosingAndUseList) {
		this.dosingAndUseList = dosingAndUseList;
	}

	/**
	 * @return the warningList
	 */
	public List<String> getWarningList() {
		return warningList;
	}

	/**
	 * @param warningList the warningList to set
	 */
	public void setWarningList(List<String> warningList) {
		this.warningList = warningList;
	}

	/**
	 * @return the ingredientsList
	 */
	public List<String> getIngredientsList() {
		return ingredientsList;
	}

	/**
	 * @param ingredientsList the ingredientsList to set
	 */
	public void setIngredientsList(List<String> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}

	/**
	 * @return the storageList
	 */
	public List<String> getStorageList() {
		return storageList;
	}

	/**
	 * @param storageList the storageList to set
	 */
	public void setStorageList(List<String> storageList) {
		this.storageList = storageList;
	}

	/**
	 * @return the interactionList
	 */
	public List<String> getInteractionList() {
		return interactionList;
	}

	/**
	 * @param interactionList the interactionList to set
	 */
	public void setInteractionList(List<String> interactionList) {
		this.interactionList = interactionList;
	}

	/**
	 * @return the similarMedicineList
	 */
	public List<String> getSimilarMedicineList() {
		return similarMedicineList;
	}

	/**
	 * @param similarMedicineList the similarMedicineList to set
	 */
	public void setSimilarMedicineList(List<String> similarMedicineList) {
		this.similarMedicineList = similarMedicineList;
	}

	/**
	 * @return the pharIdList
	 */
	public List<String> getPharIdList() {
		return pharIdList;
	}

	/**
	 * @param pharIdList the pharIdList to set
	 */
	public void setPharIdList(List<String> pharIdList) {
		this.pharIdList = pharIdList;
	}

	/**
	 * @return the pharNameList
	 */
	public List<String> getPharNameList() {
		return pharNameList;
	}

	/**
	 * @param pharNameList the pharNameList to set
	 */
	public void setPharNameList(List<String> pharNameList) {
		this.pharNameList = pharNameList;
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

}
