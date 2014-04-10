package com.med.dic.action;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.CityDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.utility.CountSearchedPharmacy;
import com.opensymphony.xwork2.ActionSupport;

public class PharmacyPopupAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;
	public String countryCode;
	public String pharmacyId;
	Pharmacy pharmacyPopup = new Pharmacy();
	Representative rep = new Representative();
	PharmacyDAO pharmacyDAO;
	RepresentativeDAO repDAO;
	public String currentLat = "0.0";
	public String currentLon = "0.0";
	public boolean popup = false;
	public boolean hasvideo = true;
	public CityDAO cityDAO;

	public String popUp() {
		pharmacyPopup = pharmacyDAO.getPharmacy(Integer
				.parseInt(this.pharmacyId));
		pharmacyPopup.setAddress(pharmacyPopup.getHouseNo() +", " + pharmacyPopup.getStreet() + ", " + pharmacyPopup.getDistrictName() + ", " + pharmacyPopup.cityName);
		if (pharmacyPopup.getVideoPath().equals("no_videos.png")) {
			hasvideo = false;
		}
		int repID = pharmacyPopup.getRepId();
		rep = repDAO.getRep(repID);
		popup = true;
		return SUCCESS;
	}

	public String openPopup() {
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		pharmacyPopup = pharmacyDAO.getPharmacy(Integer
				.parseInt(this.pharmacyId));
		CountSearchedPharmacy.countSearchedPhar(pharmacyPopup, pharmacyDAO);
		pharmacyPopup.setAddress(pharmacyPopup.getHouseNo() +", " + pharmacyPopup.getStreet() + ", " + pharmacyPopup.getDistrictName() + ", " + pharmacyPopup.cityName);
		if (pharmacyPopup.getVideoPath().equals("no_videos.png")) {
			hasvideo = false;
		}
		String telAreaCode = cityDAO.getById(pharmacyPopup.getCityId()).getTelAreaCodde();
		pharmacyPopup.setTelAreaCode(telAreaCode);
		int repID = pharmacyPopup.getRepId();
		rep = repDAO.getRep(repID);
		return SUCCESS;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the pharmacyId
	 */
	public String getPharmacyId() {
		return pharmacyId;
	}

	/**
	 * @param pharmacyId
	 *            the pharmacyId to set
	 */
	public void setPharmacyId(String pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	/**
	 * @return the pharmacyPopup
	 */
	public Pharmacy getPharmacyPopup() {
		return pharmacyPopup;
	}

	/**
	 * @param pharmacyPopup
	 *            the pharmacyPopup to set
	 */
	public void setPharmacyPopup(Pharmacy pharmacyPopup) {
		this.pharmacyPopup = pharmacyPopup;
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
	 * @return the currentLat
	 */
	public String getCurrentLat() {
		return currentLat;
	}

	/**
	 * @param currentLat
	 *            the currentLat to set
	 */
	public void setCurrentLat(String currentLat) {
		this.currentLat = currentLat;
	}

	/**
	 * @return the currentLon
	 */
	public String getCurrentLon() {
		return currentLon;
	}

	/**
	 * @param currentLon
	 *            the currentLon to set
	 */
	public void setCurrentLon(String currentLon) {
		this.currentLon = currentLon;
	}

	/**
	 * @return the popup
	 */
	public boolean getPopup() {
		return popup;
	}

	/**
	 * @param popup the popup to set
	 */
	public void setPopup(boolean popup) {
		this.popup = popup;
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
