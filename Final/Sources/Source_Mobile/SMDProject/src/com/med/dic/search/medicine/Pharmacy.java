package com.med.dic.search.medicine;

import java.io.Serializable;

public class Pharmacy implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int pharmacyId;
	private String pharmacyName;
	private String homePhone;
	private String businessLicense;
	private String pharmaceutical;
	private String gppNo;
	private String imagePath;
	private String address;
	private String notes;
	private double latitude;
	private double longitude;

	public Pharmacy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pharmacy(int pharmacyId, String pharmacyName, String homePhone,
			String businessLicense, String pharmaceutical, String gppNo,
			String imagePath, String address, String notes, double latitude, double longitude) {
		super();
		this.pharmacyId = pharmacyId;
		this.pharmacyName = pharmacyName;
		this.homePhone = homePhone;
		this.businessLicense = businessLicense;
		this.pharmaceutical = pharmaceutical;
		this.gppNo = gppNo;
		this.imagePath = imagePath;
		this.address = address;
		this.notes = notes;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public int getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getPharmaceutical() {
		return pharmaceutical;
	}
	public void setPharmaceutical(String pharmaceutical) {
		this.pharmaceutical = pharmaceutical;
	}
	public String getGppNo() {
		return gppNo;
	}
	public void setGppNo(String gppNo) {
		this.gppNo = gppNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
