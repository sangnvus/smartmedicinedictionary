package com.med.dic.action;

import java.util.ArrayList;
import java.util.List;

import com.med.dic.base.action.BaseAction;
import com.med.dic.dao.AdvertiseDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.form.PharmacyInformationForm;
import com.med.dic.model.Advertise;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.PharmacyMedicine;
import com.med.dic.model.Representative;
import com.med.dic.pagination.Pagination;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.utility.CountSearchedPharmacy;
import com.opensymphony.xwork2.ActionContext;

public class SearchPharmacyAction extends PharmacyInformationForm {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4696120088388773822L;

	public String inputPharName;
	public int pharmacyId;
	public PharmacyDAO pharmacyDAO;
	public RepresentativeDAO repDAO;
	public boolean searched = false;
	public boolean detailed = false;
	public boolean noResult = false;
	public boolean refresh;
	public List<Pharmacy> pharList;
	public Pharmacy detailedPharmacy = new Pharmacy();
	public Representative rep;
	public Pharmacy pharmacy = new Pharmacy();
	public int medTypeStr;
	public int count;
	private Pagination pagination = new Pagination(10, 1);

	public int selectBoxAjax1;
	public int selectBoxAjax2;
	public String email;
	public String pharmacyName;
	public int typeOfBusiness;
	public boolean gpp;
	public int typeOfBusinessStr;
	public String medName;
	
	// advertise
	public AdvertiseDAO advertiseDAO;
	public List<Advertise> adLineOne = new ArrayList<>();
	public List<Advertise> adLineTwo = new ArrayList<>();
	
	// search for advanced or not
	public boolean searchAdvanced = false;
	
	// detail medicine
	public boolean searchMed = false;
	public Medicine medicine;
	public int medId;
	public int degree;
	public String degreeStr;
	
	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		if("tim-kiem-nha-thuoc-theo-ten".equals(actionName)) {
			if(!searchAdvanced) {
				return searchPharByName();
			} else {
				return advancedSearchPhar();
			}
		} else if("tim-kiem-nha-thuoc-theo-ten-action".equals(actionName)) {
			resetPaging();
			return searchPharByName();
		} else if("thong-tin-hieu-thuoc".equals(actionName)) {
			return detailedPharInfo();
		} else if("danh-muc-thuoc-dang-ban-action".equals(actionName)) {
			return resetPagingForMed();
		} else if("danh-muc-thuoc-dang-ban".equals(actionName)) {
			return medicineList();
		} else if("tim-kiem-nha-thuoc-nang-cao-action".equals(actionName)) {
			resetPaging();
			return advancedSearchPhar();
		} else if("search_pharmacyInfo".equals(actionName)) {
			return advertising();
		}
		return SUCCESS;
	}
	
	public void resetPaging() {
		pagination = new Pagination(10, 1);
	}
	
	/**
	 * Search pharmacy information by name
	 * 
	 * @return success
	 */
	public String searchPharByName() {
		String pharName = (String)session.get("pharName");
		searched = true;
		detailed = false;
		hasvideo = true;
		this.searchAdvanced = false;
		try {
			if (inputPharName == null) {
				if(pharName == null && !refresh) {
					inputPharName = "";
				} else {
					inputPharName = pharName;
				}
			}
			session.put("pharName", inputPharName.trim());
			pharmacy.setPharmacyName(this.inputPharName.trim());
			pharmacy.setCityId(-1);
			pharmacy.setDistrictId(-1);
			pharmacy.setTypeOfBusiness(0);
			pharmacy.setRegUser("");
			pharmacy.setDeteleFlag("N");
			pharmacy.setAccepted("Y");
			if (count > 0) {
				pagination.setPreperties(count);
			} else {
				pagination.setPreperties(pharmacyDAO.count(pharmacy));
			}
			pharList = pharmacyDAO.listPharmacy(pharmacy, pagination, false);
			pagination.setPage_records(pharList.size());
			if (pharList.size() == 0) {
				noResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String detailedPharInfo() {
		String pharId = (String)session.get("pharId");
		if(pharmacyId == 0) {
			if(pharId != null && !refresh) {
				pharmacyId = Integer.parseInt(pharId);
				inputPharName = (String)session.get("pharName");
			}
		}
		session.put("pharId", String.valueOf(pharmacyId));
		detailed = true;
		searched = false;
		noResult = false;
		detailedPharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		detailedPharmacy.setAddress(detailedPharmacy.getHouseNo() + ", " + detailedPharmacy.getStreet()
				+ ", " + detailedPharmacy.getDistrictName() + ", "
				+ detailedPharmacy.getCityName());
		this.latitude = detailedPharmacy.getLat();
		this.longtitude = detailedPharmacy.getLon();
		if (detailedPharmacy.getVideoPath().equals("no_videos.png")) {
			hasvideo = false;
		}
		String telAreaCode = cityDAO.getById(detailedPharmacy.getCityId()).getTelAreaCodde();
		detailedPharmacy.setTelAreaCode(telAreaCode);
		CountSearchedPharmacy.countSearchedPhar(detailedPharmacy, pharmacyDAO);
		pharmacyDAO.updatePharmacy(detailedPharmacy);
		rep = repDAO.getRep(detailedPharmacy.getRepId());
		return SUCCESS;
	}
	
	public String resetPagingForMed() {
		pagination = new Pagination(10, 1);
		if(this.typeOfPackage == -1) {
			this.keyWordMedTypeName = this.typeOfPackage;
		}
		return medicineList();
	}
	public String medicineList() {
		this.medChoosen = true;
		search = true;
		detail = false;
		this.detailed = true;
		this.searchMed = false;
		this.medTypeStr = this.keyWordMedTypeName;
		pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		medicineList = new ArrayList<>();
		List<PharmacyMedicine> pharMedList = pharMedDAO.listPharById(this.pharmacyId, this.keyWordMedTypeName, "N"); 
		if(pagination.getTotal_records() == 0) {
			pagination.setPreperties(pharMedList.size());
		}
		pharMedList = pharMedDAO.pharMedList(this.pharmacyId, pagination, this.keyWordMedTypeName);
		if(pharMedList.size() > 0) {
			for (PharmacyMedicine pharmacyMedicine : pharMedList) {
				Medicine medicine = null;
					medicine = medicineDAO.searchById(pharmacyMedicine.getMedId());
				if(medicine != null) {
					medicine.setPharId(this.pharmacyId);
					medicineList.add(medicine);
				}
			}
		} else {
			pagination = new Pagination(0, 0);
		}
		pagination.setPage_records(medicineList.size());
		return SUCCESS;
	}
	
	public String medDetail() {
		this.medChoosen = true;
		this.search = false;
		this.detail = false;
		this.detailed = true;
		this.searchMed = true;
		medicine = medicineDAO.searchById(this.medId);
		return SUCCESS;
	}
	

	public String advancedSearchPhar() {
		searched = true;
		detailed = false;
		this.searchAdvanced = true;
		pharmacyId = 0;
		pharList = new ArrayList<>();
		cityStr = String.valueOf(this.selectBoxAjax1);
		districtStr = String.valueOf(this.selectBoxAjax2);
		degreeStr = String.valueOf(this.degree);
		typeOfBusinessStr = this.typeOfBusiness;
		try {
			pharmacy.setPharmacyName(this.pharmacyName.trim());
			if (this.selectBoxAjax1 != -1) {
				pharmacy.setCityId(this.selectBoxAjax1);
			} else {
				pharmacy.setCityId(-1);
			}
			if (this.selectBoxAjax2 != -1) {
				pharmacy.setDistrictId(this.selectBoxAjax2);
			} else {
				pharmacy.setDistrictId(-1);
			}
			pharmacy.setMedName(this.medName);
			pharmacy.setDegree(this.degree);
			if (typeOfBusiness != 0) {
				pharmacy.setTypeOfBusiness(this.typeOfBusiness);
			}
			pharmacy.setAccepted("Y");
			pharmacy.setDeteleFlag("N");
//			if(this.degree > 0) {
				pagination.setPreperties(pharmacyDAO.countAdvancedSearch(pharmacy));
//			} else {
//				pagination.setPreperties(pharmacyDAO.count(pharmacy));
//			}
			/*if(this.degree > 0) {
				pharList = pharmacyDAO.listPharmacyAdmin(pharmacy, pagination);
			} else {*/
				pharList = pharmacyDAO.listAdvancedSearch(pharmacy, pagination);
//			}
			pagination.setPage_records(pharList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String advertising() {
		List<Advertise> adList = new ArrayList<>();
		adList = advertiseDAO.advertiseList();
		count = 0;
		for (Advertise advertise : adList) {
			if(count < 8) {
				if(count < 4) {
					adLineOne.add(advertise);
				} else {
					adLineTwo.add(advertise);
				}
			}
			count++;
		}
		return SUCCESS;
	}
	
	/**
	 * @return the inputPharName
	 */
	public String getInputPharName() {
		return inputPharName;
	}

	/**
	 * @param inputPharName
	 *            the inputPharName to set
	 */
	public void setInputPharName(String inputPharName) {
		this.inputPharName = inputPharName;
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the detailed
	 */
	public boolean getDetailed() {
		return detailed;
	}

	/**
	 * @param detailed the detailed to set
	 */
	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	/**
	 * @return the detailedPharmacy
	 */
	public Pharmacy getDetailedPharmacy() {
		return detailedPharmacy;
	}

	/**
	 * @param detailedPharmacy the detailedPharmacy to set
	 */
	public void setDetailedPharmacy(Pharmacy detailedPharmacy) {
		this.detailedPharmacy = detailedPharmacy;
	}

	/**
	 * @return the pharmacyId
	 */
	public int getPharmacyId() {
		return pharmacyId;
	}

	/**
	 * @param pharmacyId the pharmacyId to set
	 */
	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
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
	 * @return the medTypeStr
	 */
	public int getMedTypeStr() {
		return medTypeStr;
	}

	/**
	 * @param medTypeStr the medTypeStr to set
	 */
	public void setMedTypeStr(int medTypeStr) {
		this.medTypeStr = medTypeStr;
	}

	/**
	 * @return the gpp
	 */
	public boolean getGpp() {
		return gpp;
	}

	/**
	 * @param gpp the gpp to set
	 */
	public void setGpp(boolean gpp) {
		this.gpp = gpp;
	}

	/**
	 * @return the selectBoxAjax1
	 */
	public int getSelectBoxAjax1() {
		return selectBoxAjax1;
	}

	/**
	 * @param selectBoxAjax1 the selectBoxAjax1 to set
	 */
	public void setSelectBoxAjax1(int selectBoxAjax1) {
		this.selectBoxAjax1 = selectBoxAjax1;
	}

	/**
	 * @return the selectBoxAjax2
	 */
	public int getSelectBoxAjax2() {
		return selectBoxAjax2;
	}

	/**
	 * @param selectBoxAjax2 the selectBoxAjax2 to set
	 */
	public void setSelectBoxAjax2(int selectBoxAjax2) {
		this.selectBoxAjax2 = selectBoxAjax2;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
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
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	/**
	 * @return the typeOfBusiness
	 */
	public int getTypeOfBusiness() {
		return typeOfBusiness;
	}

	/**
	 * @param typeOfBusiness the typeOfBusiness to set
	 */
	public void setTypeOfBusiness(int typeOfBusiness) {
		this.typeOfBusiness = typeOfBusiness;
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

	/**
	 * @return the searchAdvanced
	 */
	public boolean getSearchAdvanced() {
		return searchAdvanced;
	}

	/**
	 * @param searchAdvanced the searchAdvanced to set
	 */
	public void setSearchAdvanced(boolean searchAdvanced) {
		this.searchAdvanced = searchAdvanced;
	}

	/**
	 * @return the searchMed
	 */
	public boolean getSearchMed() {
		return searchMed;
	}

	/**
	 * @param searchMed the searchMed to set
	 */
	public void setSearchMed(boolean searchMed) {
		this.searchMed = searchMed;
	}

	/**
	 * @return the medicine
	 */
	public Medicine getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	/**
	 * @return the medId
	 */
	public int getMedId() {
		return medId;
	}

	/**
	 * @param medId the medId to set
	 */
	public void setMedId(int medId) {
		this.medId = medId;
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
	 * @return the degreeStr
	 */
	public String getDegreeStr() {
		return degreeStr;
	}

	/**
	 * @param degreeStr the degreeStr to set
	 */
	public void setDegreeStr(String degreeStr) {
		this.degreeStr = degreeStr;
	}

	/**
	 * @return the advertiseDAO
	 */
	public AdvertiseDAO getAdvertiseDAO() {
		return advertiseDAO;
	}

	/**
	 * @param advertiseDAO the advertiseDAO to set
	 */
	public void setAdvertiseDAO(AdvertiseDAO advertiseDAO) {
		this.advertiseDAO = advertiseDAO;
	}

	/**
	 * @return the adLineOne
	 */
	public List<Advertise> getAdLineOne() {
		return adLineOne;
	}

	/**
	 * @param adLineOne the adLineOne to set
	 */
	public void setAdLineOne(List<Advertise> adLineOne) {
		this.adLineOne = adLineOne;
	}

	/**
	 * @return the adLineTwo
	 */
	public List<Advertise> getAdLineTwo() {
		return adLineTwo;
	}

	/**
	 * @param adLineTwo the adLineTwo to set
	 */
	public void setAdLineTwo(List<Advertise> adLineTwo) {
		this.adLineTwo = adLineTwo;
	}
}
