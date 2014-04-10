package com.med.dic.model;

import com.med.dic.base.model.BaseModel;

public class City extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int cityId;
	public String cityName;
	public double lon;
	public double lat;
	public double northLat;
	public double northLong;
	public double southLat;
	public double sounthLong;
	public String idLatLongName;
	public String telAreaCodde;

	public City() {

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
	 * @return the idLatLongName
	 */
	public String getIdLatLongName() {
		return idLatLongName;
	}

	/**
	 * @param idLatLong
	 *            the idLatLongName to set
	 */
	public void setIdLatLongName(String idLatLongName) {
		this.idLatLongName = idLatLongName;
	}

	/**
	 * @return the telAreaCodde
	 */
	public String getTelAreaCodde() {
		return telAreaCodde;
	}

	/**
	 * @param telAreaCodde
	 *            the telAreaCodde to set
	 */
	public void setTelAreaCodde(String telAreaCodde) {
		this.telAreaCodde = telAreaCodde;
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
