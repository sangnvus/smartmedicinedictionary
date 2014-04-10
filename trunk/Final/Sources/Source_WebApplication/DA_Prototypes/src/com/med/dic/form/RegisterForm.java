package com.med.dic.form;

import java.io.File;
import java.math.BigDecimal;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.CityDAO;
import com.med.dic.dao.DistrictDAO;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterForm extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String email;
	public String password;
	public String retypePassword;
	public String representativeName;
	public String mobilePhone;
	public int degree;
	public String licensureNo;
	public String cardNumber;
	public String city;
	public String district;
	public boolean stepOne = false;
	public boolean completeAll = false;
	public BigDecimal longtitude;
	public BigDecimal latitude;
	public String pharmacyName;
	public String businesslicenseNo;
	public String pharCompany;
	public String homePhone;
	public String gppNo;
	public int typeOfBusiness;
	public String street;
	public String houseNo;
	public File avatarImage;
	public String avatarImageFileName;
	public String avatarImageContentType;
	public File video;
	public String videoFileName;
	public String videoContentType;
	public String path;
	public String cityIdName;
	public String districtIdName;
	public String notes;
	public String districtId;
	public String cityId;
	public BigDecimal latitudebase;
	public BigDecimal longtitudebase;
	public String cityStr;
	public String districtStr;
	public CityDAO cityDAO;
	public DistrictDAO districtDAO;
	public String streetStr;
	public String houseNoStr;
	public String homePhoneStr;
	public String pharCompanyStr;
	public String pharmacyNameStr;
	public String businesslicenseNoStr;
	public String gppNoStr;
	public String notesStr;
	public int typeOfBusinessStr;
	public boolean failed = false;

	public boolean validateStepOne() {
		if (Validator.nullOrBlank(email)) {
			return false;
		} else if (Validator.nullOrBlank(password)) {
			return false;
		} else if (Validator.nullOrBlank(retypePassword)) {
			return false;
		} else if (Validator.nullOrBlank(representativeName)) {
			return false;
		} else if (Validator.nullOrBlank(mobilePhone)) {
			return false;
		} else if (Validator.nullOrBlank(licensureNo)) {
			return false;
		}
		return true;
	}

	public boolean validation() {
		boolean check = true;
		if (Validator.nullOrBlank(email)) {
			addFieldError("email", "Bạn không được bỏ trống trường này!");
			check = false;
		}
		if (Validator.nullOrBlank(password)) {
			addFieldError("password",
					getText("Bạn không được bỏ trống trường này!"));
			check = false;
		}
		if (Validator.nullOrBlank(retypePassword)) {
			addFieldError("retypePassword",
					getText("Bạn không được bỏ trống trường này!"));
			check = false;
		}
		if (Validator.nullOrBlank(representativeName)) {
			addFieldError("representativeName",
					getText("Bạn không được bỏ trống trường này!"));
			check = false;
		}
		if (Validator.nullOrBlank(mobilePhone)) {
			addFieldError("mobilePhone",
					getText("Bạn không được bỏ trống trường này!"));
			check = false;
		}
		if (Validator.nullOrBlank(licensureNo)) {
			addFieldError("licensureNo",
					getText("Bạn không được bỏ trống trường này!"));
			check = false;
		}
		return check;
	}

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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the retypePassword
	 */
	public String getRetypePassword() {
		return retypePassword;
	}

	/**
	 * @param retypePassword
	 *            the retypePassword to set
	 */
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	/**
	 * @return the representativeName
	 */
	public String getRepresentativeName() {
		return representativeName;
	}

	/**
	 * @param representativeName
	 *            the representativeName to set
	 */
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the degree
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(int degree) {
		this.degree = degree;
	}

	/**
	 * @return the licensureNo
	 */
	public String getLicensureNo() {
		return licensureNo;
	}

	/**
	 * @param licensureNo
	 *            the licensureNo to set
	 */
	public void setLicensureNo(String licensureNo) {
		this.licensureNo = licensureNo;
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
	 * @return the stepOne
	 */
	public boolean getStepOne() {
		return stepOne;
	}

	/**
	 * @param stepOne
	 *            the stepOne to set
	 */
	public void setStepOne(boolean stepOne) {
		this.stepOne = stepOne;
	}

	/**
	 * @return the longtitude
	 */
	public BigDecimal getLongtitude() {
		return longtitude;
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
	 * @return the businesslicenseNo
	 */
	public String getBusinesslicenseNo() {
		return businesslicenseNo;
	}

	/**
	 * @param businesslicenseNo
	 *            the businesslicenseNo to set
	 */
	public void setBusinesslicenseNo(String businesslicenseNo) {
		this.businesslicenseNo = businesslicenseNo;
	}

	/**
	 * @return the pharCompany
	 */
	public String getPharCompany() {
		return pharCompany;
	}

	/**
	 * @param pharCompany
	 *            the pharCompany to set
	 */
	public void setPharCompany(String pharCompany) {
		this.pharCompany = pharCompany;
	}

	/**
	 * @return the homePhone
	 */
	public String getHomePhone() {
		return homePhone;
	}

	/**
	 * @param homePhone
	 *            the homePhone to set
	 */
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * @return the gppNo
	 */
	public String getGppNo() {
		return gppNo;
	}

	/**
	 * @param gppNo
	 *            the gppNo to set
	 */
	public void setGppNo(String gppNo) {
		this.gppNo = gppNo;
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
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the houseNo
	 */
	public String getHouseNo() {
		return houseNo;
	}

	/**
	 * @param houseNo
	 *            the houseNo to set
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	/**
	 * @param longtitude
	 *            the longtitude to set
	 */
	public void setLongtitude(BigDecimal longtitude) {
		this.longtitude = longtitude;
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
	 * @return the avatarImage
	 */
	public File getAvatarImage() {
		return avatarImage;
	}

	/**
	 * @param avatarImage
	 *            the avatarImage to set
	 */
	public void setAvatarImage(File avatarImage) {
		this.avatarImage = avatarImage;
	}

	/**
	 * @return the avatarImageFileName
	 */
	public String getAvatarImageFileName() {
		return avatarImageFileName;
	}

	/**
	 * @param avatarImageFileName
	 *            the avatarImageFileName to set
	 */
	public void setAvatarImageFileName(String avatarImageFileName) {
		this.avatarImageFileName = avatarImageFileName;
	}

	/**
	 * @return the avatarImageContentType
	 */
	public String getAvatarImageContentType() {
		return avatarImageContentType;
	}

	/**
	 * @param avatarImageContentType
	 *            the avatarImageContentType to set
	 */
	public void setAvatarImageContentType(String avatarImageContentType) {
		this.avatarImageContentType = avatarImageContentType;
	}

	/**
	 * @return the video
	 */
	public File getVideo() {
		return video;
	}

	/**
	 * @param video
	 *            the video to set
	 */
	public void setVideo(File video) {
		this.video = video;
	}

	/**
	 * @return the videoFileName
	 */
	public String getVideoFileName() {
		return videoFileName;
	}

	/**
	 * @param videoFileName
	 *            the videoFileName to set
	 */
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}

	/**
	 * @return the videoContentType
	 */
	public String getVideoContentType() {
		return videoContentType;
	}

	/**
	 * @param videoContentType
	 *            the videoContentType to set
	 */
	public void setVideoContentType(String videoContentType) {
		this.videoContentType = videoContentType;
	}

	/**
	 * @return the completeAll
	 */
	public boolean getCompleteAll() {
		return completeAll;
	}

	/**
	 * @param completeAll
	 *            the completeAll to set
	 */
	public void setCompleteAll(boolean completeAll) {
		this.completeAll = completeAll;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the cityIdName
	 */
	public String getCityIdName() {
		return cityIdName;
	}

	/**
	 * @param cityIdName
	 *            the cityIdName to set
	 */
	public void setCityIdName(String cityIdName) {
		this.cityIdName = cityIdName;
	}

	/**
	 * @return the districtIdName
	 */
	public String getDistrictIdName() {
		return districtIdName;
	}

	/**
	 * @param districtIdName
	 *            the districtIdName to set
	 */
	public void setDistrictIdName(String districtIdName) {
		this.districtIdName = districtIdName;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId
	 *            the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the latitudebase
	 */
	public BigDecimal getLatitudebase() {
		return latitudebase;
	}

	/**
	 * @param latitudebase
	 *            the latitudebase to set
	 */
	public void setLatitudebase(BigDecimal latitudebase) {
		this.latitudebase = latitudebase;
	}

	/**
	 * @return the longtitudebase
	 */
	public BigDecimal getLongtitudebase() {
		return longtitudebase;
	}

	/**
	 * @param longtitudebase
	 *            the longtitudebase to set
	 */
	public void setLongtitudebase(BigDecimal longtitudebase) {
		this.longtitudebase = longtitudebase;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
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
	 * @return the districtDAO
	 */
	public DistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	/**
	 * @param districtDAO the districtDAO to set
	 */
	public void setDistrictDAO(DistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	/**
	 * @return the streetStr
	 */
	public String getStreetStr() {
		return streetStr;
	}

	/**
	 * @param streetStr the streetStr to set
	 */
	public void setStreetStr(String streetStr) {
		this.streetStr = streetStr;
	}

	/**
	 * @return the houseNoStr
	 */
	public String getHouseNoStr() {
		return houseNoStr;
	}

	/**
	 * @param houseNoStr the houseNoStr to set
	 */
	public void setHouseNoStr(String houseNoStr) {
		this.houseNoStr = houseNoStr;
	}

	/**
	 * @return the homePhoneStr
	 */
	public String getHomePhoneStr() {
		return homePhoneStr;
	}

	/**
	 * @param homePhoneStr the homePhoneStr to set
	 */
	public void setHomePhoneStr(String homePhoneStr) {
		this.homePhoneStr = homePhoneStr;
	}

	/**
	 * @return the pharCompanyStr
	 */
	public String getPharCompanyStr() {
		return pharCompanyStr;
	}

	/**
	 * @param pharCompanyStr the pharCompanyStr to set
	 */
	public void setPharCompanyStr(String pharCompanyStr) {
		this.pharCompanyStr = pharCompanyStr;
	}

	/**
	 * @return the pharmacyNameStr
	 */
	public String getPharmacyNameStr() {
		return pharmacyNameStr;
	}

	/**
	 * @param pharmacyNameStr the pharmacyNameStr to set
	 */
	public void setPharmacyNameStr(String pharmacyNameStr) {
		this.pharmacyNameStr = pharmacyNameStr;
	}

	/**
	 * @return the businesslicenseNoStr
	 */
	public String getBusinesslicenseNoStr() {
		return businesslicenseNoStr;
	}

	/**
	 * @param businesslicenseNoStr the businesslicenseNoStr to set
	 */
	public void setBusinesslicenseNoStr(String businesslicenseNoStr) {
		this.businesslicenseNoStr = businesslicenseNoStr;
	}

	/**
	 * @return the gppNoStr
	 */
	public String getGppNoStr() {
		return gppNoStr;
	}

	/**
	 * @param gppNoStr the gppNoStr to set
	 */
	public void setGppNoStr(String gppNoStr) {
		this.gppNoStr = gppNoStr;
	}

	/**
	 * @return the notesStr
	 */
	public String getNotesStr() {
		return notesStr;
	}

	/**
	 * @param notesStr the notesStr to set
	 */
	public void setNotesStr(String notesStr) {
		this.notesStr = notesStr;
	}

	/**
	 * @return the typeOfBusinessStr
	 */
	public int getTypeOfBusinessStr() {
		return typeOfBusinessStr;
	}

	/**
	 * @param typeOfBusinessStr the typeOfBusinessStr to set
	 */
	public void setTypeOfBusinessStr(int typeOfBusinessStr) {
		this.typeOfBusinessStr = typeOfBusinessStr;
	}

	/**
	 * @return the failed
	 */
	public boolean getFailed() {
		return failed;
	}

	/**
	 * @param failed the failed to set
	 */
	public void setFailed(boolean failed) {
		this.failed = failed;
	}

}
