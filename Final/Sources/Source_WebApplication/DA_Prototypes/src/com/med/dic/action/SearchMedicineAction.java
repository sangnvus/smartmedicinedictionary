package com.med.dic.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.form.SearchMedicineForm;
import com.med.dic.model.Advertise;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.PharmacyMedicine;
import com.med.dic.model.SMDUser;
import com.med.dic.model.TypeOfMedicine;
import com.med.dic.pagination.Pagination;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.utility.CountSearchedMedicine;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionContext;

public class SearchMedicineAction extends SearchMedicineForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private Map<String, Object> session;*/
	public String medIdList;

	// Initializing Pagination with page size 10, and current page 1
	private Pagination pagination = new Pagination(10, 1);
	
	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(this.session, this.servletRequest, this.visitTimeDAO);
		if("home".equals(actionName)) {
			return advertising();
		} else if("thong-tin-hieu-thuoc-chi-tiet".equals(actionName)) {
			return referPharmacy();
		} else if("tim-kiem-thuoc-theo-ten".equals(actionName)) {
			if(!searchAdvanced) {
			return searchMedByName();
			} else {
				return user_searchMedAdvanced();
			}
		} else if("user_searchMedAdvanced".equals(actionName)) {
			resetPaging();
			return user_searchMedAdvanced();
		} else if ("thong-tin-thuoc-chi-tiet".equals(actionName) || "user_showDetailMedInfo_advancedMed".equals(actionName)) {
			return user_searchMedId();
		} else if ("admin_searchMedAdvanced".equals(actionName)) {
			SMDUser smdUser = new SMDUser();
			smdUser.setPreviousUser(this.previousUser);
			smdUser.setEmail(this.smdEmail);
			String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
			if(MedicineDictionaryConstants.userGroup1.equals(user) && smdUser.getPreviousUser()) {
				return admin_searchMedAdvanced();
			} else {
				return user;
			}
		} else if("tim-kiem-thuoc-theo-ten-action".equals(actionName)) {
			resetPaging();
			return searchMedByName();
		} else if("trang-chu-danh-muc-thuoc-dang-ban-action".equals(actionName)) {
			resetPagingForMed();
			return medicineList();
		} else if("trang-chu-danh-muc-thuoc-dang-ban".equals(actionName)) {
			return medicineList();	
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
	public void resetPaging() {
		pagination = new Pagination(10, 1);
		this.count = 0;
	}

	public void resetPagingForMed() {
		pagination = new Pagination(10, 1);
		if(this.typeOfPackage == -1) {
			this.keyWordMedTypeName = String.valueOf(this.typeOfPackage);
		}
	}
	
	/**
	 * search medicine list by input medicine name
	 * 
	 * @return success
	 */
	public String searchMedByName() {
		this.referPharmacy = false;
		this.searchAdvanced = false;
		String medName = (String) session.get("medName");
		String pageSession = (String) session.get("page");
		medIdList = "";
		search = true;
		detail = false;
		try {
			if (this.page == null) {
				if (pageSession != null) {
					page = pageSession;
				}
			}
			if (keyWordMedName == null) {
				if (medName == null && !refresh) {
					keyWordMedName = "";
				} else {
					keyWordMedName = medName;
				}
			}
			this.keyWordMedName = this.keyWordMedName.trim();
			medicine.setMedName(this.keyWordMedName);
			
			if(count == 0) {
				count = medicineDAO.count(medicine.medName);
			} 
			pagination.setPreperties(count);
			medList = medicineDAO.listMedicine(medicine, pagination);
			pagination.setPage_records(medList.size());
			session.put("medName", this.keyWordMedName);
			session.put("page", page);
			if (medList.size() == 0) {
				noResult = true;
			} else {
				List<PharmacyMedicine> pharMedList = new ArrayList<>();
				for (Medicine med : medList) {
					int id = med.getMedId();
					medIdList += id + "~";
					pharMedList = pharMedDAO.getByMedId(id);
					int count = 0;
					for (PharmacyMedicine pharmacyMedicine : pharMedList) {
						if(count < 2) {
							int pharId = pharmacyMedicine.getPharmacyId();
							String pharName = pharmacyMedicine.getPharName();
							Pharmacy pharmacy = new Pharmacy();
							pharmacy.setPharmacyId(pharId);
							pharmacy.setPharmacyName(pharName);
							med.pharmacyList.add(pharmacy);
						} else {
							break;
						}
						count++;
					}
				}
				if(medIdList.length() > 0) {
					medIdList = medIdList.substring(0, medIdList.length() - 1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String user_searchMedId() {
		this.referPharmacy = false;
		detail = true;
		this.detailed = true;
		this.search = false;
		String medicineId = (String) session.get("medId");
		if (this.medId == 0) {
			if (medicineId != null && !refresh) {
				this.medId = Integer.parseInt(medicineId);
				this.keyWordMedName = (String) session.get("medName");
			}
		}
		session.put("medId", String.valueOf(this.medId));
		medDetail = medicineDAO.searchById(this.medId);
		List<TypeOfMedicine> typeOfMedicine = new ArrayList<>();
		typeOfMedicine = typeOfMedDAO.getByMedId(medDetail.getMedId());
		String typeOfPackage = "";
		for (TypeOfMedicine typeOfMedicine2 : typeOfMedicine) {
			typeOfPackage += typeOfMedicine2.getTypeOfPackageName() + ",";
		}
		typeOfPackage = typeOfPackage.substring(0, typeOfPackage.length() - 1);
		medDetail.setTypeOfPackageName(typeOfPackage);
		CountSearchedMedicine.countSearchedMed(medDetail, medicineDAO);
		if(Validator.nullOrBlank(medDetail.getGenericMedicine())) {
			medDetail.setGenericMedicine("Chưa có thông tin");
		}
		if(Validator.nullOrBlank(medDetail.getBrandName())) {
			medDetail.setBrandName("Chưa có thông tin");
		}
		List<PharmacyMedicine> pharMedList = pharMedDAO.getByMedId(medId);
		if(pharMedList != null) {
			if(pharMedList.size() <= 3) {
				for (PharmacyMedicine pharmacyMedicine : pharMedList) {
					Pharmacy pharmacy = pharmacyDAO.getPharmacy(pharmacyMedicine.getPharmacyId());
					if("Y".equals(pharmacy.getAccepted()) && "N".equals(pharmacy.getDeteleFlag())) {
						medDetail.pharmacyList.add(pharmacy);
					}
				}
			} else {
				for (int i = 0; i < pharMedList.size(); i++) {
					Pharmacy pharmacy = pharmacyDAO.getPharmacy(pharMedList.get(i).getPharmacyId());
					medDetail.pharmacyList.add(pharmacy);
				}
			}
		}
		if("search_pharmacyInfo.jsp".equals(page) || "AddNewMedicineByPharmacy.jsp".equals(page) || "pharmacy_registerNewMedicine.jsp".equals(page)) {
			this.medChoosen = true;
		}
		medList = null;
		return SUCCESS;
	}

	public String referPharmacy() {
		this.search = false;
		this.detail = false;
		this.referPharmacy = true;
		pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		this.pharmacyName = pharmacy.getPharmacyName();
		pharmacy.setAddress(pharmacy.getHouseNo() + ", " + pharmacy.getStreet()
				+ ", " + pharmacy.getDistrictName() + ", "
				+ pharmacy.getCityName());
		String telAreacode = cityDAO.getById(pharmacy.getCityId()).getTelAreaCodde();
		pharmacy.setTelAreaCode(telAreacode);
		this.latitude = pharmacy.getLat();
		this.longtitude = pharmacy.getLon();
		if (pharmacy.getVideoPath().equals("no_videos.png")) {
			hasvideo = false;
		}
		rep = repDAO.getRep(pharmacy.getRepId());
		return SUCCESS;
	}
	public String user_searchMedAdvanced() {
		this.searchAdvanced = true;
		this.detail = false;
		this.referPharmacy = false;
		try {
			this.medTypeStr1 = Integer.parseInt(this.keyWordMedTypeName);
			this.advancedKeyWordMedName = this.advancedKeyWordMedName.trim();
			this.keyWordMedManufac = this.keyWordMedManufac.trim();
			this.keyWordMedContraindication = this.keyWordMedContraindication.trim();
			this.keyWordMedIngredients = this.keyWordMedIngredients.trim();
			this.keyWordMedIndication = this.keyWordMedIndication.trim();
			
			medicine.medName = advancedKeyWordMedName;
			medicine.manufacturer = keyWordMedManufac;
			medicine.contraindications = keyWordMedContraindication;
			medicine.ingredients = keyWordMedIngredients;
			medicine.setIndications(this.keyWordMedIndication);
			medicine.deteleFlag = "N";
			try{
				medicine.typeOfPackageId = Integer.parseInt(keyWordMedTypeName);
				}catch(Exception e){
					e.printStackTrace();
				}
			//if(count == 0) {
			if(Integer.parseInt(this.keyWordMedTypeName) > 0) {
				count = medicineDAO.userSearchAdvancedWithType(medicine);
				pagination.setPreperties(count);
				medList = medicineDAO.userAdvancedSearchWithtype(medicine,
						pagination);
			} else {
				count = medicineDAO.userSearchAdvanced(medicine);
				pagination.setPreperties(count);
				medList = medicineDAO.userAdvancedSearch(medicine,
						pagination);
			}
			if(medList.size() > 0) {
				List<PharmacyMedicine> pharMedList = new ArrayList<>();
				for (Medicine med : medList) {
					int id = med.getMedId();
					medIdList += id + "~";
					pharMedList = pharMedDAO.getByMedId(id);
					int count = 0;
					for (PharmacyMedicine pharmacyMedicine : pharMedList) {
						if(count < 2) {
							int pharId = pharmacyMedicine.getPharmacyId();
							String pharName = pharmacyMedicine.getPharName();
							Pharmacy pharmacy = new Pharmacy();
							pharmacy.setPharmacyId(pharId);
							pharmacy.setPharmacyName(pharName);
							med.pharmacyList.add(pharmacy);
						} else {
							break;
						}
						count++;
					}
				}
				if(medIdList.length() > 0) {
					medIdList = medIdList.substring(0, medIdList.length() - 1);
				}
				pagination.setPage_records(medList.size());
			}
				search = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String admin_searchMedAdvanced() {
		try {
			if (this.deleteFlagBoolean)
				this.deteleFlag = "Y";
			else
				this.deteleFlag = "N";
			medicine.medName = keyWordMedName;
			medicine.manufacturer = keyWordMedManufac;
			medicine.contraindications = keyWordMedContraindication;
			medicine.ingredients = keyWordMedIngredients;
			if(Validator.nullOrBlank(keyWordMedIndication)) {
				this.keyWordMedIndication = "";
			}
			medicine.indications = keyWordMedIndication;
			try {
				medicine.typeOfPackageId = Integer.parseInt(keyWordMedTypeName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			medicine.deteleFlag = this.deteleFlag;
			
			// get total record ==> page_size, item, start, end
			pagination.setPreperties(medicineDAO.count_admin_searchMedAdvanced(
					medicine).size());
			
			// list
			medList = medicineDAO.admin_searchMedAdvanced(medicine, pagination);
			if(medList.size() > 0) {
				for (Medicine med1 : medList) {
					List<TypeOfMedicine> typeOfMedicine = new ArrayList<>();
					typeOfMedicine = typeOfMedDAO.getByMedId(med1.getMedId());
					String typeOfPackage = "";
					for (TypeOfMedicine typeOfMedicine2 : typeOfMedicine) {
						typeOfPackage += typeOfMedicine2.getTypeOfPackageName() + ",";
					}
					typeOfPackage = typeOfPackage.substring(0, typeOfPackage.length() - 1 );
					med1.setTypeOfPackageName(typeOfPackage);
				}
			}
			// set page record
			pagination.setPage_records(medList.size());
			search = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	public String medicineList() {
		this.medChoosen = true;
		this.search = false;
		this.detail = false;
		this.referPharmacy = true;
		this.medTypeStr = Integer.parseInt(this.keyWordMedTypeName);
		pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		medicineList = new ArrayList<>();
		List<PharmacyMedicine> pharMedList = pharMedDAO.listPharById(this.pharmacyId, Integer.parseInt(keyWordMedTypeName), "N"); 
		if(pagination.getTotal_records() == 0) {
			pagination.setPreperties(pharMedList.size());
		}
		pharMedList = pharMedDAO.pharMedList(this.pharmacyId, pagination, Integer.parseInt(keyWordMedTypeName));
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
	
	/*@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}*/

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination
	 *            the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the medIdList
	 */
	public String getMedIdList() {
		return medIdList;
	}

	/**
	 * @param medIdList
	 *            the medIdList to set
	 */
	public void setMedIdList(String medIdList) {
		this.medIdList = medIdList;
	}
}
