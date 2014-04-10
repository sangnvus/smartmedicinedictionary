package com.med.dic.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.googleMap.ConvertAddressToLatLong;
import com.med.dic.model.Address;
import com.med.dic.model.Pharmacy;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.utility.CountSearchedPharmacy;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionSupport;

public class NearestPharmacyAction extends BaseAction implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private static final int radius = 5;
	public static final String NAME = "name";
	public static final String ADDRESS = "address";
	public static final String LAT = "lat";
	public static final String LON = "lon";
	public static final String PHARMACY_ID = "id";
	public static final String IMAGE = "image";

	public String id;
	public int countPharmacyList;
	public BigDecimal mLat = new BigDecimal(0.0);
	public BigDecimal mLon = new BigDecimal(0.0);
	public BigDecimal currentLat = new BigDecimal(0.0);
	public BigDecimal currentLon = new BigDecimal(0.0);
	public String keyword;
	PharmacyDAO pharmacyDAO;
	Pharmacy pharmacy = new Pharmacy();
	Pharmacy pharmacyPopup = new Pharmacy();
	public Pharmacy pharmacyTop = new Pharmacy();
	List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();
	public boolean duplicate = false;
	public boolean searched = false;
	public boolean refresh;
	public boolean hasNextLocation = false;
	public boolean noResult = false;
	public String message;
	public String nearestName;

	// ArrayList parse to String
	public String pharamcyNameList;
	public String addressList;
	public String latList;
	public String lonList;
	public String pharmacyIdList;
	public String imgPathList;

	// ArrayList stores value of locations
	List<String> listName = new ArrayList<>();
	List<String> listAddress = new ArrayList<>();
	List<String> listLat = new ArrayList<>();
	List<String> listLng = new ArrayList<>();
	List<String> listId = new ArrayList<>();
	List<String> listImg = new ArrayList<>();

	public String nearestLocation() throws IOException{
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		Pharmacy pharSession = (Pharmacy)session.get("pharmacy");
		if(Validator.nullOrBlank(keyword)) {
			if(pharSession != null && !refresh) {
				this.keyword = pharSession.getAddress();
				this.mLat = pharSession.getLat();
				this.mLon = pharSession.getLon();
			}
		}
		duplicate = false;
		if (!Validator.nullOrBlank(keyword)) {
			try {
				
			pharmacy.setAddress(keyword);
//			pharmacy.setLat(mLat);
//			pharmacy.setLon(mLon);
			Address addObj = new Address();
			addObj = ConvertAddressToLatLong.returnLatLng(this.keyword, addObj);
			this.mLat = new BigDecimal(addObj.getLat());
			this.mLon = new BigDecimal(addObj.getLng());
			pharmacy.setLat(this.mLat);
			pharmacy.setLon(this.mLon);
			} catch (Exception e) {
				hasNextLocation = false;
				noResult = true;
				this.message = "Xin lỗi chúng tôi không thể xác định được vị trí bạn đang đứng";
				setNullAfterFind();
				return INPUT;
			}
			pharmacyList = pharmacyDAO.listLocations(pharmacy, radius);
			if (pharmacyList.size() > 0) {
				searched = true;
				noResult = false;
				this.currentLat = this.mLat;
				this.currentLon = this.mLon;
				setNullAfterFind();
				countPharmacyList = pharmacyList.size();
				/*if (pharmacyTop.getLat() == mLat) {
					duplicate = true;
				} else {*/
					pharmacyTop = pharmacyList.get(0);
					this.nearestName = pharmacyTop.getPharmacyName();
					pharmacyTop.setAddress(pharmacyTop.getHouseNo() + ", " + pharmacyTop.getStreet() + ", " + pharmacyTop.getDistrictName() + ", " + pharmacyTop.cityName);
					pharmacyList.remove(0);
				/*}*/
				if(pharmacyList.size() > 0) {
					hasNextLocation = true;
					pharamcyNameList = addListToString(pharmacyList, listName, NAME);
					addressList = addListToString(pharmacyList, listAddress,
							ADDRESS);
					latList = addListToString(pharmacyList, listLat, LAT);
					lonList = addListToString(pharmacyList, listLng, LON);
					pharmacyIdList = addListToString(pharmacyList, listId,
							PHARMACY_ID);
					imgPathList = addListToString(pharmacyList, listImg, IMAGE);
				} else {
					hasNextLocation = false;
				}
			} else {
				noResult = true;
				this.message = "Không có kết quả tương ứng với từ khóa bạn nhập";
				hasNextLocation = false;
				setNullAfterFind();
			}
		} else {
			noResult = true;
			this.message = "Không có kết quả tương ứng với từ khóa bạn nhập";
			hasNextLocation = false;
			setNullAfterFind();
		}
		session.put("pharmacy", pharmacy);
		return SUCCESS;
	}

	public String openPopup() {
		pharmacyPopup = pharmacyDAO.getPharmacy(Integer.parseInt(this.id));
		return SUCCESS;
	}

	public void setNullAfterFind() {
		this.pharamcyNameList = null;
		this.addressList = null;
		this.latList = null;
		this.lonList = null;
		this.pharmacyIdList = null;
		this.imgPathList = null;
		this.countPharmacyList = 0;

	}

	private String addListToString(List<Pharmacy> pharmacyList,
			List<String> listName, String name) {
		for (int i = 0; i < pharmacyList.size(); i++) {
			Pharmacy pharmacy = pharmacyList.get(i);
			if (NAME.equals(name)) {
				listName.add(pharmacy.getPharmacyName());
			} else if (ADDRESS.equals(name)) {
				listName.add(pharmacy.getHouseNo() + ", "
						+ pharmacy.getStreet() + ", "
						+ pharmacy.getDistrictName() + ", "
						+ pharmacy.getCityName());
			} else if (LAT.equals(name)) {
				listName.add(String.valueOf(pharmacy.getLat()));
			} else if (LON.equals(name)) {
				listName.add(String.valueOf(pharmacy.getLon()));
			} else if (PHARMACY_ID.equals(name)) {
				listName.add(String.valueOf(pharmacy.getPharmacyId()));
			} else if (IMAGE.equals(name)) {
				listName.add(String.valueOf(pharmacy.getImgPath()));
			}
		}
		return arrayListToString(listName);
	}

	private String arrayListToString(List<String> arrayList) {
		int intCount;
		String strFinal = "";
		for (intCount = 0; intCount < arrayList.size(); intCount++) {
			if (intCount > 0) {
				strFinal += "~";
			}
			strFinal += arrayList.get(intCount).toString();
		}
		return strFinal;
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
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	 * @return the duplicate
	 */
	public boolean getDuplicate() {
		return duplicate;
	}

	/**
	 * @param duplicate
	 *            the duplicate to set
	 */
	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}

	/**
	 * @return the pharamcyNameList
	 */
	public String getPharamcyNameList() {
		return pharamcyNameList;
	}

	/**
	 * @param pharamcyNameList
	 *            the pharamcyNameList to set
	 */
	public void setPharamcyNameList(String pharamcyNameList) {
		this.pharamcyNameList = pharamcyNameList;
	}

	/**
	 * @return the addressList
	 */
	public String getAddressList() {
		return addressList;
	}

	/**
	 * @param addressList
	 *            the addressList to set
	 */
	public void setAddressList(String addressList) {
		this.addressList = addressList;
	}

	/**
	 * @return the latList
	 */
	public String getLatList() {
		return latList;
	}

	/**
	 * @param latList
	 *            the latList to set
	 */
	public void setLatList(String latList) {
		this.latList = latList;
	}

	/**
	 * @return the lonList
	 */
	public String getLonList() {
		return lonList;
	}

	/**
	 * @param lonList
	 *            the lonList to set
	 */
	public void setLonList(String lonList) {
		this.lonList = lonList;
	}

	/**
	 * @return the pharmacyIdList
	 */
	public String getPharmacyIdList() {
		return pharmacyIdList;
	}

	/**
	 * @param pharmacyIdList
	 *            the pharmacyIdList to set
	 */
	public void setPharmacyIdList(String pharmacyIdList) {
		this.pharmacyIdList = pharmacyIdList;
	}

	/**
	 * @return the imgPathList
	 */
	public String getImgPathList() {
		return imgPathList;
	}

	/**
	 * @param imgPathList
	 *            the imgPathList to set
	 */
	public void setImgPathList(String imgPathList) {
		this.imgPathList = imgPathList;
	}

	/**
	 * @return the currentLat
	 */
	public BigDecimal getCurrentLat() {
		return currentLat;
	}

	/**
	 * @param currentLat
	 *            the currentLat to set
	 */
	public void setCurrentLat(BigDecimal currentLat) {
		this.currentLat = currentLat;
	}

	/**
	 * @return the currentLon
	 */
	public BigDecimal getCurrentLon() {
		return currentLon;
	}

	/**
	 * @param currentLon
	 *            the currentLon to set
	 */
	public void setCurrentLon(BigDecimal currentLon) {
		this.currentLon = currentLon;
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
	 * @return the countPharmacyList
	 */
	public int getCountPharmacyList() {
		return countPharmacyList;
	}

	/**
	 * @param countPharmacyList
	 *            the countPharmacyList to set
	 */
	public void setCountPharmacyList(int countPharmacyList) {
		this.countPharmacyList = countPharmacyList;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	/**
	 * @return the refresh
	 */
	public boolean getRefresh() {
		return refresh;
	}

	/**
	 * @param refresh the refresh to set
	 */
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	/**
	 * @return the pharmacyTop
	 */
	public Pharmacy getPharmacyTop() {
		return pharmacyTop;
	}

	/**
	 * @param pharmacyTop the pharmacyTop to set
	 */
	public void setPharmacyTop(Pharmacy pharmacyTop) {
		this.pharmacyTop = pharmacyTop;
	}

	/**
	 * @return the hasNextLocation
	 */
	public boolean getHasNextLocation() {
		return hasNextLocation;
	}

	/**
	 * @param hasNextLocation the hasNextLocation to set
	 */
	public void setHasNextLocation(boolean hasNextLocation) {
		this.hasNextLocation = hasNextLocation;
	}

	/**
	 * @return the nearestName
	 */
	public String getNearestName() {
		return nearestName;
	}

	/**
	 * @param nearestName the nearestName to set
	 */
	public void setNearestName(String nearestName) {
		this.nearestName = nearestName;
	}

	/**
	 * @return the noResult
	 */
	public boolean getNoResult() {
		return noResult;
	}

	/**
	 * @param noResult the noResult to set
	 */
	public void setNoResult(boolean noResult) {
		this.noResult = noResult;
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
}
