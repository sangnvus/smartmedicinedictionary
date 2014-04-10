package com.med.dic.action;

import java.util.ArrayList;
import java.util.List;

import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.form.MedicineAcceptForm;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.PharmacyMedicine;
import com.med.dic.model.SMDUser;
import com.med.dic.pagination.Pagination;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionContext;

public class MedicineAcceptAction extends MedicineAcceptForm {

	private Pagination pagination = new Pagination(10, 1);

	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if(MedicineDictionaryConstants.userGroup1.equals(user) && smdUser.getPreviousUser()) {
			if("tra-cuu-thuoc-kiem-duyet-action".equals(actionName)) {
				return resetPaging();
			} else if("thong-tin-chi-tiet-thuoc-kiem-duyet".equals(actionName)) {
				return detailInfor();
			} else if("kiem-duyet-thuoc".equals(actionName)) {
				this.message = "Bạn đã kiểm duyệt thuốc thành công";
				return acceptMedicine();
			} else if("tra-cuu-thuoc-kiem-duyet".equals(actionName)) {
				return searchMedicine();
			} else if("huy-kiem-duyet-thuoc".equals(actionName)) {
				this.message = "Bạn đã hủy kiểm duyệt thuốc thành công";
				return rejectMed();
			}
			return SUCCESS;
		} else {
			return user;
		}
	}
	public String resetPaging() {
		pagination = new Pagination(10, 1);
		return searchMedicine();
	}

	public String searchMedicine() {
		searched = true;
		checkAll = false;
		detailed = false;
		acceptSuccess = false;
		medIdList = "";
		medicineList = new ArrayList<>();
		medicine.setMedName(this.keyWordMedName);
		medicine.setManufacturer(this.keyWordMedManufac);
		medicine.setIndications(this.keyWordMedIndication);
		medicine.setContraindications(this.keyWordMedContraindication);
		medicine.setTypeOfPackageId(Integer.parseInt(this.keyWordMedTypeName));
		medicine.setIngredients(this.keyWordMedIngredients);
		try {
			if (checkaccept) {
				medicine.setAccept("Y");
			} else {
				medicine.setAccept("N");
			}
			int count = medicineDAO.coutForAcceptMed(medicine);
			pagination.setPreperties(count);
			if (count > 0) {
				medicineList = medicineDAO.searchForAcceptMed(medicine,
						pagination);
				for (int i = 0; i < medicineList.size(); i++) {
					if (i + 1 == medicineList.size()) {
						medIdList += medicineList.get(i).getMedId();
					} else {
						medIdList += medicineList.get(i).getMedId() + "~";
					}
				}
				this.successedAction = false;
				this.failedAction = false;
			} else {
				this.message = "Xin lỗi, không tìm thấy kết quả tìm kiếm với từ khoá bạn nhập!";
				this.successedAction = false;
				this.failedAction = true;
			}
			pagination.setPage_records(medicineList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String searchFailedAccept() {
		searched = true;
		checkAll = false;
		detailed = false;
		medIdList = "";
		medicineList = new ArrayList<>();
		medicine.setMedName(this.keyWordMedName);
		medicine.setManufacturer(this.keyWordMedManufac);
		medicine.setIndications(this.keyWordMedIndication);
		medicine.setContraindications(this.keyWordMedContraindication);
		medicine.setTypeOfPackageId(Integer.parseInt(this.keyWordMedTypeName));
		medicine.setIngredients(this.keyWordMedIngredients);
		try {
			if (checkaccept) {
				medicine.setAccept("Y");
			} else {
				medicine.setAccept("N");
			}
			int count = medicineDAO.coutForAcceptMed(medicine);
			pagination.setPreperties(count);
			if (count > 0) {
				medicineList = medicineDAO.searchForAcceptMed(medicine,
						pagination);
				for (int i = 0; i < medicineList.size(); i++) {
					if (i + 1 == medicineList.size()) {
						medIdList += medicineList.get(i).getMedId();
					} else {
						medIdList += medicineList.get(i).getMedId() + "~";
					}
				}
			}
			pagination.setPage_records(medicineList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String detailInfor() {
		this.searched = false;
		this.checkAll = false;
		this.detailed = true;
		acceptSuccess = false;
		medDetail = medicineDAO.searchById(this.medIdDetail);
		if(Validator.nullOrBlank(medDetail.getGenericMedicine())) {
			medDetail.setGenericMedicine("Chưa có thông tin");
		}
		if(Validator.nullOrBlank(medDetail.getBrandName())) {
			medDetail.setBrandName("Chưa có thông tin");
		}
//		List<Pharmacy> pharList = new ArrayList<>();
		Pharmacy pharmacy = pharmacyDAO.getPharmacy(medDetail.getAddedByPharId());
		//pharList.add(pharmacy);
		//medDetail.setPharmacyList(pharList);
		List<PharmacyMedicine> pharMedList = new ArrayList<>();
		pharMedList = pharMedDAO.getByMedId(this.medIdDetail);
		int count = 0;
		for (PharmacyMedicine pharmacyMedicine : pharMedList) {
			if(count < 2) {
				int pharId = pharmacyMedicine.getPharmacyId();
				String pharName = pharmacyMedicine.getPharName();
				Pharmacy pharmacy1 = new Pharmacy();
				pharmacy1.setPharmacyId(pharId);
				pharmacy1.setPharmacyName(pharName);
				medDetail.pharmacyList.add(pharmacy1);
			} else {
				break;
			}
			count++;
		}
		medDetail.setPharmacyName(pharmacy.getPharmacyName());
		medList = null;
		return SUCCESS;
	}

	public String acceptMedicine() {
		detailed = false;
		pagination = new Pagination(10, 1);
		if(medIdDetail != 0) {
			Medicine med = medicineDAO.searchByID(this.medIdDetail);
			med.setAccept("Y");
			medicineDAO.updateMedicine(med);
			this.failedAction = false;
			this.successedAction = true;
		} else {
			if(checkBox != null && !"false".equals(checkBox[0])) {
				for (String check : checkBox) {
					Medicine med = medicineDAO.searchByID(Integer.parseInt(check));
					med.setAccept("Y");
					medicineDAO.updateMedicine(med);
					this.failedAction = false;
					this.successedAction = true;
				} 
			} else {
				this.failedAction = true;
				this.successedAction = false;
				this.message = "Bạn phải chọn một trong các thuốc dưới đây!";
				//addFieldError("nullCheckBox", "Bạn phải chọn một trong các nhà thuốc dưới đây");
				return searchFailedAccept();
			}
		}
		medIdDetail = 0;
		acceptSuccess = true;
		return SUCCESS;
	}
	
	public String rejectMed() {
		detailed = false;
		pagination = new Pagination(10, 1);
		if (medIdDetail != 0) {
			Medicine med = medicineDAO.searchByID(this.medIdDetail);
			med.setAccept("N");
			medicineDAO.updateMedicine(med);
			this.failedAction = false;
			this.successedAction = true;
		} else {
			if (checkBox != null && !"false".equals(checkBox[0])) {
				for (String check : checkBox) {
					Medicine med = medicineDAO.searchByID(Integer
							.parseInt(check));
					med.setAccept("N");
					medicineDAO.updateMedicine(med);
					this.failedAction = false;
					this.successedAction = true;
				}
			} else {
				//addFieldError("nullCheckBox",
					//	"Bạn phải chọn một trong các nhà thuốc dưới đây");
				this.failedAction = true;
				this.successedAction = false;
				this.message = "Bạn phải chọn một trong các thuốc dưới đây!";
				return searchFailedAccept();
			}
		}
		medIdDetail = 0;
		acceptSuccess = true;
		return SUCCESS;
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
}
