package com.med.dic.form;

import java.math.BigDecimal;
import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.CityDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;
import com.opensymphony.xwork2.ActionSupport;

public class PharmacyAdminForm extends BaseAction {

	public String email = "";
	public String pharmacyName = "";
	public String city = "";
	public int cityStr;
	public int districtStr;
	public int typeOfBusinessStr;
	public String district = "";
	public int pharIdDetail;
	public Pharmacy pharmacy = new Pharmacy();
	public Pharmacy pharDetailInfor = new Pharmacy();
	public Representative repDetailInfor = new Representative();
	public List<Pharmacy> pharList;
	public PharmacyDAO pharmacyDAO;
	public RepresentativeDAO repDAO;
	public boolean searched = false;
	public boolean noResult = false;
	public boolean detailed = false;
	public boolean acceptSuccess = false;
	public boolean checkaccept;
	public boolean checkAll;
	public int count;
	public int typeOfBusiness;
	public String[] checkBox;
	public String pharIdList;
	public int selectBoxAjax2;
	public int selectBoxAjax1;
	public int degree;
	public BigDecimal latitude;
	public BigDecimal longtitude;
	public boolean hasvideo = true;
	public boolean deleteFlag = false;
	public boolean deleteBoolean = false;
	public boolean success = false;
	public String message;
	public String acceptedFlag;
	public boolean failedAction;
	public boolean successedAction;
	public CityDAO cityDAO;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the cityStr
	 */
	public int getCityStr() {
		return cityStr;
	}

	/**
	 * @param cityStr
	 *            the cityStr to set
	 */
	public void setCityStr(int cityStr) {
		this.cityStr = cityStr;
	}

	/**
	 * @return the districtStr
	 */
	public int getDistrictStr() {
		return districtStr;
	}

	/**
	 * @param districtStr
	 *            the districtStr to set
	 */
	public void setDistrictStr(int districtStr) {
		this.districtStr = districtStr;
	}

	/**
	 * @return the typeOfBusinessStr
	 */
	public int getTypeOfBusinessStr() {
		return typeOfBusinessStr;
	}

	/**
	 * @param typeOfBusinessStr
	 *            the typeOfBusinessStr to set
	 */
	public void setTypeOfBusinessStr(int typeOfBusinessStr) {
		this.typeOfBusinessStr = typeOfBusinessStr;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the pharIdDetail
	 */
	public int getPharIdDetail() {
		return pharIdDetail;
	}

	/**
	 * @param pharIdDetail
	 *            the pharIdDetail to set
	 */
	public void setPharIdDetail(int pharIdDetail) {
		this.pharIdDetail = pharIdDetail;
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
	 * @return the pharDetailInfor
	 */
	public Pharmacy getPharDetailInfor() {
		return pharDetailInfor;
	}

	/**
	 * @param pharDetailInfor
	 *            the pharDetailInfor to set
	 */
	public void setPharDetailInfor(Pharmacy pharDetailInfor) {
		this.pharDetailInfor = pharDetailInfor;
	}

	/**
	 * @return the repDetailInfor
	 */
	public Representative getRepDetailInfor() {
		return repDetailInfor;
	}

	/**
	 * @param repDetailInfor
	 *            the repDetailInfor to set
	 */
	public void setRepDetailInfor(Representative repDetailInfor) {
		this.repDetailInfor = repDetailInfor;
	}

	/**
	 * @return the pharList
	 */
	public List<Pharmacy> getPharList() {
		return pharList;
	}

	/**
	 * @param pharList
	 *            the pharList to set
	 */
	public void setPharList(List<Pharmacy> pharList) {
		this.pharList = pharList;
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
	 * @return the repDAO
	 */
	public RepresentativeDAO getRepDAO() {
		return repDAO;
	}

	/**
	 * @param repDAO
	 *            the repDAO to set
	 */
	public void setRepDAO(RepresentativeDAO repDAO) {
		this.repDAO = repDAO;
	}

	/**
	 * @return the searched
	 */
	public boolean getSearched() {
		return searched;
	}

	/**
	 * @param searched
	 *            the searched to set
	 */
	public void setSearched(boolean searched) {
		this.searched = searched;
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
	 * @return the detailed
	 */
	public boolean getDetailed() {
		return detailed;
	}

	/**
	 * @param detailed
	 *            the detailed to set
	 */
	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	/**
	 * @return the checkaccept
	 */
	public boolean getCheckaccept() {
		return checkaccept;
	}

	/**
	 * @param checkaccept
	 *            the checkaccept to set
	 */
	public void setCheckaccept(boolean checkaccept) {
		this.checkaccept = checkaccept;
	}

	/**
	 * @return the checkAll
	 */
	public boolean getCheckAll() {
		return checkAll;
	}

	/**
	 * @param checkAll
	 *            the checkAll to set
	 */
	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the typeOfBusiness
	 */
	public int getTypeOfBusiness() {
		return typeOfBusiness;
	}

	/**
	 * @param typeOfBusiness
	 *            the typeOfBusiness to set
	 */
	public void setTypeOfBusiness(int typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
	}

	/**
	 * @return the checkBox
	 */
	public String[] getCheckBox() {
		return checkBox;
	}

	/**
	 * @param checkBox
	 *            the checkBox to set
	 */
	public void setCheckBox(String[] checkBox) {
		this.checkBox = checkBox;
	}

	/**
	 * @return the pharIdList
	 */
	public String getPharIdList() {
		return pharIdList;
	}

	/**
	 * @param pharIdList
	 *            the pharIdList to set
	 */
	public void setPharIdList(String pharIdList) {
		this.pharIdList = pharIdList;
	}

	/**
	 * @return the selectBoxAjax2
	 */
	public int getSelectBoxAjax2() {
		return selectBoxAjax2;
	}

	/**
	 * @param selectBoxAjax2
	 *            the selectBoxAjax2 to set
	 */
	public void setSelectBoxAjax2(int selectBoxAjax2) {
		this.selectBoxAjax2 = selectBoxAjax2;
	}

	/**
	 * @return the selectBoxAjax1
	 */
	public int getSelectBoxAjax1() {
		return selectBoxAjax1;
	}

	/**
	 * @param selectBoxAjax1
	 *            the selectBoxAjax1 to set
	 */
	public void setSelectBoxAjax1(int selectBoxAjax1) {
		this.selectBoxAjax1 = selectBoxAjax1;
	}

	/**
	 * @return the acceptSuccess
	 */
	public boolean getAcceptSuccess() {
		return acceptSuccess;
	}

	/**
	 * @param acceptSuccess the acceptSuccess to set
	 */
	public void setAcceptSuccess(boolean acceptSuccess) {
		this.acceptSuccess = acceptSuccess;
	}

	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}

	/**
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
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
	 * @param longtitude the longtitude to set
	 */
	public void setLongtitude(BigDecimal longtitude) {
		this.longtitude = longtitude;
	}

	/**
	 * @return the hasvideo
	 */
	public boolean getHasvideo() {
		return hasvideo;
	}

	/**
	 * @param hasvideo the hasvideo to set
	 */
	public void setHasvideo(boolean hasvideo) {
		this.hasvideo = hasvideo;
	}

	/**
	 * @return the deleteFlag
	 */
	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * @return the deleteBoolean
	 */
	public boolean getDeleteBoolean() {
		return deleteBoolean;
	}

	/**
	 * @param deleteBoolean the deleteBoolean to set
	 */
	public void setDeleteBoolean(boolean deleteBoolean) {
		this.deleteBoolean = deleteBoolean;
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
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the acceptedFlag
	 */
	public String getAcceptedFlag() {
		return acceptedFlag;
	}

	/**
	 * @param acceptedFlag the acceptedFlag to set
	 */
	public void setAcceptedFlag(String acceptedFlag) {
		this.acceptedFlag = acceptedFlag;
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
}
