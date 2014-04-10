package com.med.dic.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.med.dic.dao.PharmacyMedicineDAO;
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

public class AddMedByPharmacyAction extends SearchMedicineAction implements
		SessionAware {

	public boolean checkAll;
	public boolean success;
	public String[] checkBox;
	public PharmacyMedicineDAO pharMedDAO;
	private Map<String, Object> session;
	public int pharmacyId;
	public int medIdDetail;
	public String pharmacyName;
	public boolean medChoosen = false;
	// Initializing Pagination with page size 10, and current page 1
	private Pagination pagination = new Pagination(10, 1);
	
	public AddMedByPharmacyAction() {
		super();
	}
	
	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(this.session, this.servletRequest, this.visitTimeDAO);
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if("userGroup2".equals(user) && smdUser.getPreviousUser()) {
			if("thuoc-trong-tu-dien".equals(actionName)) {
				this.failedAction = false;
				this.successedAction = false;
				return searchMedByName();
			} else if("thuoc-trong-tu-dien-action".equals(actionName)) {
				resetPaging();
				this.failedAction = false;
				this.successedAction = false;
				return searchMedByName();
			} else if("thong-tin-thuoc".equals(actionName)) {
				this.failedAction = false;
				this.successedAction = false;
				return user_searchMedId();
			} else if("them-vao-danh-muc-thuoc".equals(actionName)) {
				return addToPharMed();
			} else if("them-moi-thuoc-vao-danh-muc".equals(actionName)) {
				return addToPharMed();
			} else if("them-thuoc-vao-nha-thuoc".equals(actionName)) {
				return referAddNewMed();
			} else if("nha-thuoc-thong-tin-thuoc-chi-tiet".equals(actionName)) {
				
			}
			return SUCCESS;
		} else {
			return user;
		}
	}

	public String referAddNewMed() {
		this.medChoosen = false;
		this.search = false;
		this.detail = false;
		return SUCCESS;
	}

	public void resetPaging() {
		this.medChoosen = true;
		pagination = new Pagination(10, 1);
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

	public String searchMedByName() {
		this.medChoosen = true;
		this.medIdDetail = 0;
		this.medId = 0;
		String medName = (String) session.get("medNameForPhar");
		medIdList = "";
		search = true;
		detail = false;
		success = false;
		try {
			if (keyWordMedName == null) {
				if (medName == null && !refresh) {
					keyWordMedName = "";
				} else {
					keyWordMedName = medName;
				}
			}
			medicine.setMedName(this.keyWordMedName);
			if (pagination.getTotal_records() == 0) {
				pagination.setPreperties(medicineDAO
						.countForPhar(this.keyWordMedName, this.pharmacyId));
			}
			medList = medicineDAO.medListForPhar(this.keyWordMedName,
					pagination, this.pharmacyId);
			pagination.setPage_records(medList.size());
			session.put("medNameForPhar", this.keyWordMedName);
			if (medList.size() == 0) {
				noResult = true;
			} else {
				for (int i = 0; i < medList.size(); i++) {
					if (i + 1 == medList.size()) {
						medIdList += medList.get(i).getMedId();
					} else {
						medIdList += medList.get(i).getMedId() + "~";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String addToPharMed() {
		search = false;
		detail = false;
		this.medChoosen = true;
		PharmacyMedicine pharMed = new PharmacyMedicine();
		SMDUser sessionUser = (SMDUser) session.get("userGroup2");
		String medicineId = (String) session.get("medId");
		if (this.medId == 0) {
			if (medicineId != null && !refresh) {
				this.medId = Integer.parseInt(medicineId);
				this.keyWordMedName = (String) session.get("medNameForPhar");
			}
		}
		session.put("medId", String.valueOf(this.medId));
		if ((checkBox == null || "false".equals(checkBox[0])) && medId == 0) {
//			addFieldError("nullCheckBox",
//					"Bạn phải chọn một trong các thuốc dưới đây");
			this.failedAction = true;
			this.successedAction = false;
			this.message = 	"Bạn phải chọn một trong các thuốc dưới đây";
			searchMedByName();
		} else {
			pharMed.setDeteleFlag("N");
			pharMed.setRegDate(new Date());
			pharMed.setModDate(new Date());
			pharMed.setRegUser(sessionUser.getEmail());
			pharMed.setModUser(sessionUser.getEmail());
			if (checkBox != null) {
				for (String medicineID : checkBox) {
					PharmacyMedicine pharmacyMedicine = null; 
					pharmacyMedicine = pharMedDAO.getById(Integer.parseInt(medicineID), this.pharmacyId);
					Medicine medicine = medicineDAO.searchById(Integer.parseInt(medicineID));
					Pharmacy pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
					if(pharmacyMedicine == null) {
						pharMed.setPharmacyId(this.pharmacyId);
						pharMed.setMedId(Integer.parseInt(medicineID));
						pharMed.setMedName(medicine.getMedName());
						pharMed.setPharName(pharmacy.getPharmacyName());
						pharMedDAO.addPharMed(pharMed);
					} else {
						pharmacyMedicine.setDeteleFlag("N");
						pharmacyMedicine.setModDate(new Date());
						pharmacyMedicine.setModUser(sessionUser.getEmail());
						pharMedDAO.updatePharMed(pharmacyMedicine);
					}
				}
			} else {
				PharmacyMedicine pharmacyMedicine = null; 
				pharmacyMedicine = pharMedDAO.getById(this.medId, this.pharmacyId);
				Medicine medicine = medicineDAO.searchById(this.medId);
				Pharmacy pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
				if(pharmacyMedicine == null) {
					pharMed.setPharmacyId(this.pharmacyId);
					pharMed.setMedId(this.medId);
					pharMed.setMedName(medicine.getMedName());
					pharMed.setPharName(pharmacy.getPharmacyName());
					pharMedDAO.addPharMed(pharMed);
				} else {
					pharmacyMedicine.setDeteleFlag("N");
					pharmacyMedicine.setModDate(new Date());
					pharmacyMedicine.setModUser(sessionUser.getEmail());
					pharMedDAO.updatePharMed(pharmacyMedicine);
				}
			}
			success = true;
			this.failedAction = false;
			this.successedAction = true;
			this.message = 	"Bạn đã thêm vào danh mục thuốc thành công";
			this.medId = 0;
		}
		return SUCCESS;
	}

	/**
	 * @return the checkAll
	 */
	public boolean isCheckAll() {
		return checkAll;
	}

	/**
	 * @param checkAll
	 *            the checkAll to set
	 */
	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * @return the checkBox
	 */
	public String[] getCheckBox() {
		return checkBox;
	}

	/**
	 * @param checkBox
	 *            the checkBox to set
	 */
	public void setCheckBox(String[] checkBox) {
		this.checkBox = checkBox;
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

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

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
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
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
}
