package com.med.dic.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.med.dic.base.model.BaseModel;

@XmlRootElement(name="Pharmacy")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pharmacy extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public int pharmacyId;
	public String pharmacyName;
	public String homePhone;
	public String businessLicenseNo;
	public String pharCompany;
	public String gppNo;
	public String imgPath;
	public String videoPath;
	public String notes;
	public int repId;
	public int cityId;
	public int districtId;
	public String cityName;
	public String districtName;
	public String houseNo;
	public String street;
	public String accepted;
	public BigDecimal lon;
	public BigDecimal lat;
	public double distance;
	public String address;
	public int typeOfBusiness;
	public String typeOfBusinessName;
	public int searchedTime;
	public boolean acceptFlag;
	public int degree;
	public String telAreaCode;
	public String medName;

	public Pharmacy() {

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
	 * @return the businessLicenseNo
	 */
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	/**
	 * @param businessLicenseNo
	 *            the businessLicenseNo to set
	 */
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
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
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath
	 *            the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the videoPath
	 */
	public String getVideoPath() {
		return videoPath;
	}

	/**
	 * @param videoPath
	 *            the videoPath to set
	 */
	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
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
	 * @return the repId
	 */
	public int getRepId() {
		return repId;
	}

	/**
	 * @param repId
	 *            the repId to set
	 */
	public void setRepId(int repId) {
		this.repId = repId;
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
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	 * @return the accepted
	 */
	public String getAccepted() {
		return accepted;
	}

	/**
	 * @param accepted
	 *            the accepted to set
	 */
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	/**
	 * @return the lon
	 */
	public BigDecimal getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public BigDecimal getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the typeOfBusinessName
	 */
	public String getTypeOfBusinessName() {
		return typeOfBusinessName;
	}

	/**
	 * @param typeOfBusinessName
	 *            the typeOfBusinessName to set
	 */
	public void setTypeOfBusinessName(String typeOfBusinessName) {
		this.typeOfBusinessName = typeOfBusinessName;
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
	 * @return the telAreaCode
	 */
	public String getTelAreaCode() {
		return telAreaCode;
	}

	/**
	 * @param telAreaCode the telAreaCode to set
	 */
	public void setTelAreaCode(String telAreaCode) {
		this.telAreaCode = telAreaCode;
	}

	/**
	 * @return the medName
	 */
	public String getMedName() {
		return medName;
	}

	/**
	 * @param medName the medName to set
	 */
	public void setMedName(String medName) {
		this.medName = medName;
	}

}
