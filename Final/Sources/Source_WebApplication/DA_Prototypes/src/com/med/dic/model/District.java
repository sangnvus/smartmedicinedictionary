package com.med.dic.model;

import com.med.dic.base.model.BaseModel;

public class District extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int districtId;
	public String districtName;
	public double lon;
	public double lat;
	public double northLat;
	public double northLong;
	public double southLat;
	public double sounthLong;
	public int cityId;
	public String idLatlngName;

	public District() {

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
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * @param lon
	 *            the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
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
	 * @return the idLatlngName
	 */
	public String getIdLatlngName() {
		return idLatlngName;
	}

	/**
	 * @param idLatlngName
	 *            the idLatlngName to set
	 */
	public void setIdLatlngName(String idLatlngName) {
		this.idLatlngName = idLatlngName;
	}

	/**
	 * @return the northLat
	 */
	public double getNorthLat() {
		return northLat;
	}

	/**
	 * @param northLat the northLat to set
	 */
	public void setNorthLat(double northLat) {
		this.northLat = northLat;
	}

	/**
	 * @return the northLong
	 */
	public double getNorthLong() {
		return northLong;
	}

	/**
	 * @param northLong the northLong to set
	 */
	public void setNorthLong(double northLong) {
		this.northLong = northLong;
	}

	/**
	 * @return the southLat
	 */
	public double getSouthLat() {
		return southLat;
	}

	/**
	 * @param southLat the southLat to set
	 */
	public void setSouthLat(double southLat) {
		this.southLat = southLat;
	}

	/**
	 * @return the sounthLong
	 */
	public double getSounthLong() {
		return sounthLong;
	}

	/**
	 * @param sounthLong the sounthLong to set
	 */
	public void setSounthLong(double sounthLong) {
		this.sounthLong = sounthLong;
	}
}
