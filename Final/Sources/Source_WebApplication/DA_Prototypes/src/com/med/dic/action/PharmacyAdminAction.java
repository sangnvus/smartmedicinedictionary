package com.med.dic.action;

import java.util.ArrayList;

import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.form.PharmacyAdminForm;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.SMDUser;
import com.med.dic.pagination.Pagination;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.CheckVisitTime;
import com.opensymphony.xwork2.ActionContext;

public class PharmacyAdminAction extends PharmacyAdminForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6450476627056287657L;
	private Pagination pagination = new Pagination(10, 1);

	public String execute() {
		this.deleteBoolean = this.deleteFlag;
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if(MedicineDictionaryConstants.userGroup1.equals(user) && smdUser.getPreviousUser()) {
			if("tra-cuu-nha-thuoc".equals(actionName)) {
				return searchPharmacy();
			} else if("adSearchPharAdvancedAction".equals(actionName)) {
				return searchPharmacyAdvanced();
			} else if("kiem-duyet-nha-thuoc".equals(actionName)) {
				this.acceptedFlag = "Y";
				this.message = "Bạn đã kiểm duyệt nhà thuốc thành công";
				return acceptPharmacy();
			} else if("quan-ly-thong-tin-nha-thuoc-chi-tiet".equals(actionName)) {
				return detailInfo();
			} else if("xoa-nha-thuoc".equals(actionName)) {
				return deletePhar();
			} else if("khoi-phuc-nha-thuoc".equals(actionName)) {
				return restorePhar();
			} else if("huy-kiem-duyet-nha-thuoc".equals(actionName)) {
				this.acceptedFlag = "N";
				this.message = "Bạn đã hủy kiểm duyệt nhà thuốc thành công";
				return acceptPharmacy();
			}
			return SUCCESS;
		} else {
			return user;
		}
	}
	
	public String searchPharmacyAdvanced() {
		pagination = new Pagination(10, 1);
		return searchPharmacy();
	}
	public String searchPharmacy() {
		searched = true;
		checkAll = false;
		detailed = false;
		pharIdDetail = 0;
		pharList = new ArrayList<>();
		pharIdList = "";
		cityStr = this.selectBoxAjax1;
		districtStr = this.selectBoxAjax2;
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
			pharmacy.setRegUser(this.email.trim());
			if (typeOfBusiness != 0) {
				pharmacy.setTypeOfBusiness(this.typeOfBusiness);
			}
			if(checkaccept) {
				pharmacy.setAccepted("Y");
			} else {
				pharmacy.setAccepted("N");
			}
			if(deleteFlag) {
				pharmacy.setDeteleFlag("Y");
			} else {
				pharmacy.setDeteleFlag("N");
			}
			pharmacy.setDegree(this.degree);
			if(this.degree > 0) {
				pagination.setPreperties(pharmacyDAO.countForAdmin(pharmacy));
			} else {
				pagination.setPreperties(pharmacyDAO.count(pharmacy));
			}
			if(this.degree > 0) {
				pharList = pharmacyDAO.listPharmacyAdmin(pharmacy, pagination);
			} else {
				pharList = pharmacyDAO.listPharmacy(pharmacy, pagination, true);
			}
			for (int i = 0; i < pharList.size(); i++) {
				if(i+1 == pharList.size()) {
					pharIdList += pharList.get(i).getPharmacyId();
				} else {
					pharIdList += pharList.get(i).getPharmacyId() + "~";
				}
			}
			if(pharList.size() > 0) {
				this.successedAction = false;
				this.failedAction = false;
			} else {
				this.message = "Xin lỗi, không tìm thấy kết quả tìm kiếm với từ khoá bạn nhập!";
				this.successedAction = false;
				this.failedAction = true;
			}
			pagination.setPage_records(pharList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String searchFailedAccept() {
		searched = true;
		checkAll = false;
		detailed = false;
		pharIdDetail = 0;
		pharList = new ArrayList<>();
		pharIdList = "";
		cityStr = this.selectBoxAjax1;
		districtStr = this.selectBoxAjax2;
		typeOfBusinessStr = this.typeOfBusiness;
		try {
			pharmacy.setPharmacyName(this.pharmacyName);
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
			pharmacy.setRegUser(this.email);
			if (typeOfBusiness != 0) {
				pharmacy.setTypeOfBusiness(this.typeOfBusiness);
			}
			if(checkaccept) {
				pharmacy.setAccepted("Y");
			} else {
				pharmacy.setAccepted("N");
			}
			if(deleteFlag) {
				pharmacy.setDeteleFlag("Y");
			} else {
				pharmacy.setDeteleFlag("N");
			}
			pharmacy.setDegree(this.degree);
			if(this.degree > 0) {
				pagination.setPreperties(pharmacyDAO.countForAdmin(pharmacy));
			} else {
				pagination.setPreperties(pharmacyDAO.count(pharmacy));
			}
			if(this.degree > 0) {
				pharList = pharmacyDAO.listPharmacyAdmin(pharmacy, pagination);
			} else {
				pharList = pharmacyDAO.listPharmacy(pharmacy, pagination, true);
			}
			for (int i = 0; i < pharList.size(); i++) {
				if(i+1 == pharList.size()) {
					pharIdList += pharList.get(i).getPharmacyId();
				} else {
					pharIdList += pharList.get(i).getPharmacyId() + "~";
				}
			}
			pagination.setPage_records(pharList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String acceptPharmacy() {
		searched = false;
		detailed = false;
		pagination = new Pagination(10, 1);
		if (pharIdDetail != 0) {
			Pharmacy phar = pharmacyDAO.getPharmacy(this.pharIdDetail);
			phar.setAccepted(this.acceptedFlag);
			pharmacyDAO.updatePharmacy(phar);
			this.failedAction = false;
			this.successedAction = true;
		} 
		else {
			if(checkBox != null && !"false".equals(checkBox[0])) {
				for (String check : checkBox) {
					Pharmacy phar = pharmacyDAO.getPharmacy(Integer.parseInt(check));
					phar.setAccepted(this.acceptedFlag);
					pharmacyDAO.updatePharmacy(phar);
					this.failedAction = false;
					this.successedAction = true;
				}
			} else {
				this.failedAction = true;
				this.successedAction = false;
				this.message = "Bạn phải chọn một trong các nhà thuốc dưới đây!";
				return searchFailedAccept();
			}
		}
		acceptSuccess = true;
		success = true;
		return SUCCESS;
	}
	
	public String detailInfo() {
		cityStr = this.selectBoxAjax1;
		districtStr = this.selectBoxAjax2;
		success = false;
		typeOfBusinessStr = this.typeOfBusiness;
		acceptSuccess = false;
		detailed = true;
		this.failedAction = false;
		this.successedAction = false;
		pharDetailInfor = pharmacyDAO.getPharmacy(this.pharIdDetail);
		pharDetailInfor.setAddress(pharDetailInfor.getHouseNo() + ", " + pharDetailInfor.getStreet()
				+ ", " + pharDetailInfor.getDistrictName() + ", "
				+ pharDetailInfor.getCityName());
		this.latitude = pharDetailInfor.getLat();
		this.longtitude = pharDetailInfor.getLon();
		if (pharDetailInfor.getVideoPath().equals("no_videos.png")) {
			hasvideo = false;
		}
		String telAreaCode = cityDAO.getById(pharDetailInfor.getCityId()).getTelAreaCodde();
		pharDetailInfor.setTelAreaCode(telAreaCode);
		repDetailInfor = repDAO.getRep(pharDetailInfor.getRepId());
		return SUCCESS;
	}
	
	public String deletePhar() {
		this.detailed = false;
		this.searched = false;
		Pharmacy phar = pharmacyDAO.getPharmacy(this.pharIdDetail);
		phar.setDeteleFlag("Y");
		pharmacyDAO.updatePharmacy(phar);
		this.failedAction = false;
		this.successedAction = true;
		this.message = "Bạn đã xóa nhà thuốc thành công";
		return SUCCESS;
	}
	
	public String restorePhar() {
		this.detailed = false;
		this.searched = false;
		Pharmacy phar = pharmacyDAO.getPharmacy(this.pharIdDetail);
		phar.setDeteleFlag("N");
		pharmacyDAO.updatePharmacy(phar);
		this.failedAction = false;
		this.successedAction = true;
		this.message = "Bạn đã khôi phục nhà thuốc thành công";
		return SUCCESS;
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
}
