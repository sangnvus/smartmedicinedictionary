package com.med.dic.action;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.med.dic.dao.CityDAO;
import com.med.dic.dao.DistrictDAO;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.form.PharmacyInformationForm;
import com.med.dic.googleMap.ConvertAddressToLatLong;
import com.med.dic.model.Address;
import com.med.dic.model.City;
import com.med.dic.model.District;
import com.med.dic.model.Medicine;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.PharmacyMedicine;
import com.med.dic.model.Representative;
import com.med.dic.model.SMDUser;
import com.med.dic.pagination.Pagination;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PharmacyInformationAction extends PharmacyInformationForm {

	private static final long serialVersionUID = 4951817902377220744L;
	private Pagination pagination = new Pagination(10, 1);

	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if("userGroup2".equals(user) && smdUser.getPreviousUser()) {
			if("xoa-khoi-danh-muc".equals(actionName)) {
				return deleteFormPharMed();
			} else if("pharmacyDetail".equals(actionName)) {
				return pharmacyDetail();
			} else if("chinh-sua-thong-tin-hieu-thuoc-action".equals(actionName)) {
				return changeInfo();
			} else if("chinh-sua-thong-tin-hieu-thuoc".equals(actionName)) {
				return submitChangeInfo();
			} else if("huy-bo-chinh-sua-thong-tin-hieu-thuoc".equals(actionName)) {
				return cancelChange();
			} else if("danh-muc-thuoc".equals(actionName)) {
				return medicineList();
			} else if("danh-muc-thuoc-action".equals(actionName)) {
				return resetPaging();
			}
			return SUCCESS;
		} else {
			return user;
		}
	}
	public String pharmacyDetail() {
		this.medChoosen = false;
		this.changeInfo = false;
		pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		this.pharmacyName = pharmacy.getPharmacyName();
		pharmacy.setAddress(pharmacy.getHouseNo() + ", " + pharmacy.getStreet()
				+ ", " + pharmacy.getDistrictName() + ", "
				+ pharmacy.getCityName());
		this.latitude = pharmacy.getLat();
		this.longtitude = pharmacy.getLon();
		if (pharmacy.getVideoPath().equals("no_videos.png")) {
			hasvideo = false;
		}
		String telAreaCode = cityDAO.getById(pharmacy.getCityId()).getTelAreaCodde();
		pharmacy.setTelAreaCode(telAreaCode);
		rep = repDAO.getRep(pharmacy.getRepId());
		return SUCCESS;
	}

	public String changeInfo() {
		this.medChoosen = false;
		changeInfo = true;
		pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		if (pharmacy.getPharCompany().equals("Không")) {
			pharmacy.setPharCompany("");
		}
		if (pharmacy.getNotes().equals("Không")) {
			pharmacy.setNotes("");
		}
		this.latitude = pharmacy.getLat();
		this.longtitude = pharmacy.getLon();
		this.typeOfBusiness = pharmacy.getTypeOfBusiness();
		City cityObj = cityDAO.getById(pharmacy.getCityId());
		cityStr = cityObj.getCityId() + "~" + cityObj.getLat() + "~"
				+ cityObj.getLon() + "~" + cityObj.getCityName() + "~"
				+ cityObj.getTelAreaCodde() + "~" + cityObj.getNorthLat() + "~" + cityObj.getNorthLong() + "~" + cityObj.getSouthLat()
				+ "~" + cityObj.getSounthLong();
		this.city = cityStr;
		District districtObj = districtDAO.getById(pharmacy.getDistrictId());
		districtStr = districtObj.getDistrictId() + "~"
				+ districtObj.getLat() + "~" + districtObj.getLon() + "~"
				+ districtObj.getDistrictName() + "~" + districtObj.getNorthLat() + "~" + districtObj.getNorthLong() + "~" + districtObj.getSouthLat()
				+ "~" + districtObj.getSounthLong();
		String telAreaCode = cityDAO.getById(pharmacy.getCityId()).getTelAreaCodde();
		pharmacy.setTelAreaCode(telAreaCode);
		this.district = districtStr;
		this.cityId = pharmacy.getCityId();
		this.districtId = pharmacy.getDistrictId();
		return SUCCESS;
	}

	public String submitChangeInfo() {
		this.medChoosen = false;
		changeInfo = false;
		pharmacy = pharmacyDAO.getPharmacy(this.pharmacyId);
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss")
		.format(new Date());
		String path = servletRequest.getSession().getServletContext()
		.getRealPath("/");
		if (avatarImage != null) {
			try {
				String filePathImage = path + "/Pharmacy_Images/";
				File image = avatarImage;
				avatarImageFileName = timestamp + avatarImageFileName.replace(" ", "");
				File destFile = new File(filePathImage
						+ avatarImageFileName);
				FileUtils.copyFile(image, destFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			avatarImageFileName = pharmacy.getImgPath();
		}
		if (video != null) {
			try {
				String filePathVideo = path + "/Pharmacy_Medias/";
				File media = video;
				videoFileName = timestamp + videoFileName.replace("FLV", "mp4");
				File destFile = new File(filePathVideo, videoFileName);
				FileUtils.copyFile(media, destFile);
				hasvideo = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			videoFileName = pharmacy.getVideoPath();
		}
		// Change Pharmacy information
		pharmacy.setDeteleFlag("N");
		pharmacy.setModDate(new Date());
		pharmacy.setPharmacyName(this.nameOfPharmacy);
		pharmacy.setHomePhone(this.homePhone);
		pharmacy.setBusinessLicenseNo(this.businesslicenseNo);
		if (Validator.nullOrBlank(this.pharCompany)) {
			this.pharCompany = "Không";
		}
		pharmacy.setPharCompany(this.pharCompany);
		if (Validator.nullOrBlank(this.gppNo)) {
			this.gppNo = "Không";
		}
		pharmacy.setGppNo(this.gppNo);
		pharmacy.setImgPath(avatarImageFileName);
		if (Validator.nullOrBlank(this.notes)) {
			this.notes = "Không";
		}
		pharmacy.setNotes(this.notes);
		pharmacy.setVideoPath(this.videoFileName);
		pharmacy.setCityId(this.cityId);
		pharmacy.setCityName(this.city.split("~")[3]);
		pharmacy.setDistrictId(this.districtId);
		pharmacy.setDistrictName(this.district.split("~")[3]);
		pharmacy.setAddress(this.houseNo + ", " + this.street + "," + pharmacy.getDistrictName() + ", " + pharmacy.getCityName());
		if(!Validator.nullOrBlank(this.street)) {
			pharmacy.setStreet(this.street);
		}
		if(!Validator.nullOrBlank(this.houseNo)) {
			pharmacy.setHouseNo(this.houseNo);
		}
		//pharmacy.setAccepted("N");
		pharmacy.setTypeOfBusiness(this.typeOfBusiness);
		try {
			if (this.latitudebase == new BigDecimal(0.0) || latitudebase == null) {
				/*pharmacy.setLat(this.latitude);
				pharmacy.setLon(this.longtitude);*/
				Address addObj = new Address();
				addObj = ConvertAddressToLatLong.returnLatLng(pharmacy.getAddress(), addObj);
				this.latitude = new BigDecimal(addObj.getLat());
				this.longtitude = new BigDecimal(addObj.getLng());
				pharmacy.setLat(this.latitude);
				pharmacy.setLon(this.longtitude);
			} else {
				pharmacy.setLat(this.latitudebase);
				pharmacy.setLon(this.longtitudebase);
				this.latitude = this.latitudebase;
				this.longtitude = this.longtitudebase;
			}
		} catch (Exception e) {
			this.failed = true;
			addFieldError("latLngError", "Xin lỗi, chúng tôi không thể xác định vị trí của bạn");
			return INPUT;
		}
		pharmacyDAO.updatePharmacy(pharmacy);
		session.put("pharmacyList", pharmacyDAO.repsPharmacy(pharmacy.getRepId()));
		pharmacyDetail();
		return SUCCESS;
	}

	public String cancelChange() {
		this.medChoosen = false;
		this.changeInfo = false;
		return pharmacyDetail();
	}
	
	public String resetPaging() {
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
	
	public String deleteFormPharMed() {
		this.medChoosen = true;
		this.search = true;
		this.detail = false;
		PharmacyMedicine pharMed = new PharmacyMedicine();
		pharMed = pharMedDAO.getById(this.medIdDetail, this.pharmacyId);
		pharMed.setDeteleFlag("Y");
		pharMedDAO.deletePharMed(pharMed);
		return resetPaging();
	}
	
	public void representativeInfo(String email) {
		rep = repDAO.getRepByEmail(email);
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
