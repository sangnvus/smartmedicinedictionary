package com.med.dic.form;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.CityDAO;
import com.med.dic.dao.DistrictDAO;
import com.med.dic.dao.MedicineDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.PharmacyMedicineDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;
import com.med.dic.model.SMDUser;
import com.opensymphony.xwork2.ActionSupport;

public class PharmacyInformationForm extends BaseAction {

	public String userGroup;
	public PharmacyDAO pharmacyDAO;
	public PharmacyMedicineDAO pharMedDAO;
	public MedicineDAO medicineDAO;
	public SMDUserDAO smdUserDAO;
	public RepresentativeDAO repDAO;
	public Pharmacy pharmacy = new Pharmacy();
	public Representative rep = new Representative();
	public SMDUser user = new SMDUser();
	public int keyWordMedTypeName;
	public int pharmacyId;
	public boolean hasvideo = true;
	public boolean changeInfo = false;
	public boolean search = false;
	public boolean detail = false;
	public String city;
	public String district;
	public int typeOfBusiness;
	public CityDAO cityDAO;
	public DistrictDAO districtDAO;
	public String cityStr;
	public String districtStr;
	public BigDecimal latitude;
	public BigDecimal longtitude;
	public BigDecimal mLat;
	public BigDecimal mLon;
	public BigDecimal latitudebase;
	public BigDecimal longtitudebase;
	public int medIdDetail;
	public int typeOfPackage;
	public String page;

	// fields for pharmacy
	public String pharmacyName;
	public String nameOfPharmacy;
	public String businesslicenseNo;
	public String pharCompany;
	public String homePhone;
	public String gppNo;
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
	public int districtId;
	public int cityId;
	public List<Medicine> medicineList;
	public boolean medChoosen = false;
	public boolean failed = false;
	
	/**
	 * @return the userGroup
	 */
	public String getUserGroup() {
		return userGroup;
	}

	/**
	 * @param userGroup
	 *            the userGroup to set
	 */
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
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
	 * @return the smdUserDAO
	 */
	public SMDUserDAO getSmdUserDAO() {
		return smdUserDAO;
	}

	/**
	 * @param smdUserDAO
	 *            the smdUserDAO to set
	 */
	public void setSmdUserDAO(SMDUserDAO smdUserDAO) {
		this.smdUserDAO = smdUserDAO;
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
	 * @return the rep
	 */
	public Representative getRep() {
		return rep;
	}

	/**
	 * @param rep
	 *            the rep to set
	 */
	public void setRep(Representative rep) {
		this.rep = rep;
	}

	/**
	 * @return the user
	 */
	public SMDUser getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(SMDUser user) {
		this.user = user;
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
	 * @return the hasvideo
	 */
	public boolean isHasvideo() {
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
	 * @return the changeInfo
	 */
	public boolean isChangeInfo() {
		return changeInfo;
	}

	/**
	 * @param changeInfo
	 *            the changeInfo to set
	 */
	public void setChangeInfo(boolean changeInfo) {
		this.changeInfo = changeInfo;
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
	 * @return the cityDAO
	 */
	public CityDAO getCityDAO() {
		return cityDAO;
	}

	/**
	 * @param cityDAO
	 *            the cityDAO to set
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
	 * @param districtDAO
	 *            the districtDAO to set
	 */
	public void setDistrictDAO(DistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	/**
	 * @return the cityStr
	 */
	public String getCityStr() {
		return cityStr;
	}

	/**
	 * @param cityStr
	 *            the cityStr to set
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
	 * @param districtStr
	 *            the districtStr to set
	 */
	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
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
	 * @return the mLat
	 */
	public BigDecimal getmLat() {
		return mLat;
	}

	/**
	 * @param mLat
	 *            the mLat to set
	 */
	public void setmLat(BigDecimal mLat) {
		this.mLat = mLat;
	}

	/**
	 * @return the mLon
	 */
	public BigDecimal getmLon() {
		return mLon;
	}

	/**
	 * @param mLon
	 *            the mLon to set
	 */
	public void setmLon(BigDecimal mLon) {
		this.mLon = mLon;
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
	public int getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId
	 *            the districtId to set
	 */
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * @param cityId
	 *            the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
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
	 * @return the medicineList
	 */
	public List<Medicine> getMedicineList() {
		return medicineList;
	}

	/**
	 * @param medicineList
	 *            the medicineList to set
	 */
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
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
	 * @return the keyWordMedTypeName
	 */
	public int getKeyWordMedTypeName() {
		return keyWordMedTypeName;
	}

	/**
	 * @param keyWordMedTypeName
	 *            the keyWordMedTypeName to set
	 */
	public void setKeyWordMedTypeName(int keyWordMedTypeName) {
		this.keyWordMedTypeName = keyWordMedTypeName;
	}

	/**
	 * @return the medChoosen
	 */
	public boolean getMedChoosen() {
		return medChoosen;
	}

	/**
	 * @param medChoosen
	 *            the medChoosen to set
	 */
	public void setMedChoosen(boolean medChoosen) {
		this.medChoosen = medChoosen;
	}

	/**
	 * @return the nameOfPharmacy
	 */
	public String getNameOfPharmacy() {
		return nameOfPharmacy;
	}

	/**
	 * @param nameOfPharmacy
	 *            the nameOfPharmacy to set
	 */
	public void setNameOfPharmacy(String nameOfPharmacy) {
		this.nameOfPharmacy = nameOfPharmacy;
	}

	/**
	 * @return the medIdDetail
	 */
	public int getMedIdDetail() {
		return medIdDetail;
	}

	/**
	 * @param medIdDetail
	 *            the medIdDetail to set
	 */
	public void setMedIdDetail(int medIdDetail) {
		this.medIdDetail = medIdDetail;
	}

	/**
	 * @return the typeOfPackage
	 */
	public int getTypeOfPackage() {
		return typeOfPackage;
	}

	/**
	 * @param typeOfPackage
	 *            the typeOfPackage to set
	 */
	public void setTypeOfPackage(int typeOfPackage) {
		this.typeOfPackage = typeOfPackage;
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
