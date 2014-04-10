
package com.med.dic.action;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.form.RegisterForm;
import com.med.dic.googleMap.ConvertAddressToLatLong;
import com.med.dic.model.Address;
import com.med.dic.model.City;
import com.med.dic.model.District;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.Representative;
import com.med.dic.model.SMDUser;
import com.med.dic.sercurity.EncryptPassword;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.CheckVisitTime;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionContext;

public class RegisterAction extends RegisterForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean submitStepOne = false;
	public boolean logged = false;
	public boolean ignoreStepOne = false;
	public PharmacyDAO pharmacyDAO;
	public SMDUserDAO smdUserDAO;
	public RepresentativeDAO repDAO;

	public Pharmacy pharmacy;
	public SMDUser smdUser;
	public Representative representative;

	public String execute() {
		String actionName = ActionContext.getContext().getName();
		CheckVisitTime.checkVisitor(session, servletRequest, visitTimeDAO);
		boolean checkCondition = true;
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if(MedicineDictionaryConstants.userGroup2.equals(user) && !smdUser.getPreviousUser()) {
			setValueAfterLogout();
			checkCondition = false;
			this.logged = true;
		} else if(MedicineDictionaryConstants.userGroup1.equals(user)) {
			setValueAfterLogout();
			return user;
		} else if(LOGIN.equals(user)) {
			if(!Validator.nullOrBlank(this.smdEmail)) {
				setValueAfterLogout();
				return LOGIN;
			}
		}
		if(checkCondition) {
			if("completeStepOne".equals(actionName)) {
				return submitStepOne();
			} else if("backToStepOneAction".equals(actionName)) {
				return backToStepOne();
			} else if("completeRegisterAction".equals(actionName)) {
				return completeRegister();
			} else if("ignoreStepOneAction".equals(actionName)) {
				return ignoreStepOne();
			} else if("register".equals(actionName)) {
				return logged();
			}
			return SUCCESS;
		} else {
			return user;
		}
	}
	public void setValue() {
		
	}
	
	public String submitStepOne() {
		if (!validation()) {
			stepOne = true;
			return INPUT;
		}
		stepTwoForm();
		submitStepOne = true;
		return SUCCESS;
	}

	public void stepTwoForm() {
		this.city = this.cityStr;
		this.district = this.districtStr;
		this.street = this.streetStr;
		this.houseNo = this.houseNoStr;
		this.homePhone = this.homePhoneStr;
		this.pharCompany = this.pharCompanyStr;
		this.pharmacyName = this.pharmacyNameStr;
		this.businesslicenseNo = this.businesslicenseNoStr;
		this.gppNo = this.gppNoStr;
		this.notes = this.notesStr;
		this.typeOfBusiness = this.typeOfBusinessStr;
	}
	public String backToStepOne() {
		submitStepOne = false;
		this.cityStr = this.city;
		this.districtStr = this.district;
		this.cityId = this.cityStr.split("~")[0];
		this.districtId = this.districtStr.split("~")[0];
		this.streetStr = this.street;
		this.houseNoStr = this.houseNo;
		this.homePhoneStr = this.homePhone;
		this.pharCompanyStr = this.pharCompany;
		this.pharmacyNameStr = this.pharmacyName;
		this.businesslicenseNoStr = this.businesslicenseNo;
		this.gppNoStr = this.gppNo;
		this.notesStr = this.notes;
		this.typeOfBusinessStr = this.typeOfBusiness;
		return SUCCESS;
	}

	public String completeRegister() {
		SMDUser checkExist = new SMDUser();
		checkExist = smdUserDAO.getSMDUser(this.email);
		SMDUser sessionUser = (SMDUser) session.get("userGroup2");
		int repId = 0;
		int userId = 0;
		if (checkExist == null) {
			// Add SMD User information
			if (sessionUser == null || !ignoreStepOne) {
				smdUser = new SMDUser();
				smdUser.setDeteleFlag("N");
				smdUser.setRegDate(new Date());
				smdUser.setModDate(new Date());
				smdUser.setRegUser(this.email);
				smdUser.setModUser(this.email);
				smdUser.setEmail(this.email);
				smdUser.setPassword(EncryptPassword.md5(this.password));
				smdUser.setUserGroup(2);
				smdUser.setName(this.representativeName);
				userId = smdUserDAO.addSMDUser(smdUser);

				// Add Representative information
				representative = new Representative();
				representative.setDeteleFlag("N");
				representative.setRegDate(new Date());
				representative.setModDate(new Date());
				representative.setRegUser(this.email);
				representative.setModUser(this.email);
				representative.setEmail(this.email);
				representative.setName(this.representativeName);
				representative.setPhone(this.mobilePhone);
				representative.setDegree(this.degree);
				representative.setLicensureNo(this.licensureNo);
				representative.setCardNumber(this.cardNumber);
				representative.setUserId(userId);
				repId = repDAO.addRep(representative);
			} else {
				representative = (Representative) session.get("rep");
				repId = representative.getRepId();
				this.email = representative.getEmail();
			}

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
				avatarImageFileName = "no_img.jpg";
			}
			if (video != null) {
				try {
					String filePathVideo = path + "/Pharmacy_Medias/";
					File media = video;
					videoFileName = timestamp + videoFileName;
					File destFile = new File(filePathVideo, videoFileName);
					FileUtils.copyFile(media, destFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				videoFileName = "no_videos.png";
			}

			// Add Pharmacy information
			pharmacy = new Pharmacy();
			pharmacy.setDeteleFlag("N");
			pharmacy.setRegDate(new Date());
			pharmacy.setModDate(new Date());
			pharmacy.setRegUser(this.email);
			pharmacy.setModUser(this.email);
			pharmacy.setRepId(repId);
			pharmacy.setPharmacyName(this.pharmacyName);
			pharmacy.setHomePhone(this.homePhone);
			pharmacy.setBusinessLicenseNo(this.businesslicenseNo);
			pharmacy.setPharCompany(this.pharCompany);
			pharmacy.setGppNo(this.gppNo);
			pharmacy.setImgPath(avatarImageFileName);
			pharmacy.setNotes(this.notes);
			pharmacy.setVideoPath(this.videoFileName);
			pharmacy.setCityId(Integer.parseInt(this.cityId));
			pharmacy.setCityName(this.city.split("~")[3]);
			pharmacy.setDistrictId(Integer.parseInt(this.districtId));
			pharmacy.setDistrictName(this.district.split("~")[3]);
			pharmacy.setStreet(this.street);
			pharmacy.setHouseNo(this.houseNo);
			pharmacy.setAddress(this.houseNo + ", " + this.street + "," + pharmacy.getDistrictName() + ", " + pharmacy.getCityName());
			try {
				if (this.latitudebase == new BigDecimal(0.0) || latitudebase == null) {
					Address addObj = new Address();
					addObj = ConvertAddressToLatLong.returnLatLng(pharmacy.getAddress() + "," + "Việt Nam", addObj);
					this.latitude = new BigDecimal(addObj.getLat());
					this.longtitude = new BigDecimal(addObj.getLng());
					pharmacy.setLat(this.latitude);
					pharmacy.setLon(this.longtitude);
					/*pharmacy.setLat(this.latitude);
					pharmacy.setLon(this.longtitude);*/
				} else {
					pharmacy.setLat(this.latitudebase);
					pharmacy.setLon(this.longtitudebase);
				}
			} catch (Exception e) {
				addFieldError("latLngError", "Xin lỗi, chúng tôi không thể xác định vị trí của bạn");
				this.failed = true;
				return INPUT;
			}
			pharmacy.setAccepted("N");
			pharmacy.setTypeOfBusiness(this.typeOfBusiness);
			int pharId = pharmacyDAO.addPharmacy(pharmacy);
			pharmacy.setPharmacyId(pharId);
			List<Pharmacy> pharList = (List<Pharmacy>) session.get("pharmacyList");
			if(pharList != null) {
				pharList.add(pharmacy);
				session.put("pharmacyList", pharList);
			}
			completeAll = true;
		} else {
			submitStepOne = false;
			completeAll = false;
			if(logged) {
				addFieldError("emailError", "Email đã được sử dụng, vui lòng sử dụng email khác, hoặc bỏ qua bước này.");
			} else {
				addFieldError("emailError", "Email đã được sử dụng, vui lòng sử dụng email khác, hoặc đăng nhập để bỏ qua bước này.");
			}
		}
		return SUCCESS;
	}

	public String ignoreStepOne() {
		//cityAndDistrict(this.cityId, this.districtId);
//		this.district = this.districtId;
//		this.city = this.cityId;
		stepTwoForm();
		/*SMDUser sessionUser = (SMDUser) session.get("userGroup2");
		Representative rep = new Representative();
		rep = repDAO.getRepByEmail(sessionUser.getEmail());
		this.email = rep.getEmail();
		this.representativeName = rep.getName();
		this.degree = rep.getDegree();
		this.mobilePhone = rep.getPhone();
		this.licensureNo = rep.getLicensureNo();*/
//		servletRequest = ServletActionContext.getRequest();
//		servletRequest.getHeader("VIA");
//		String ipAddress = servletRequest.getHeader("HTTP-X_FORWARDED_FOR");
//		if(ipAddress == null) {
//			ipAddress = servletRequest.getRemoteAddr();
//		}
//		System.out.println(ipAddress);
//		System.out.println(servletRequest.getRemoteHost());
//		System.out.println(servletRequest.getHeader("Proxy-Client-IP"));
//		System.out.println(servletRequest.getHeader("WL-Proxy-Client-IP"));
//		System.out.println(servletRequest.getLocalAddr());
//		System.out.println(servletRequest.getLocalPort());
//		System.out.println(servletRequest.getHeader("X-Real-IP"));
//		System.out.println(servletRequest.getHeader("x-forwarded-for"));
//		System.out.println(servletRequest.getHeader("X-Client-IP"));
		submitStepOne = true;
		ignoreStepOne = true;
		return SUCCESS;
	}

	public String logged() {
		SMDUser sessionUser = (SMDUser) session.get("userGroup2");
		if(sessionUser != null) {
			logged = true;
		}
		this.submitStepOne = false;
		this.ignoreStepOne = false;
		this.completeAll =false;
		return SUCCESS;
	}
	
	public void cityAndDistrict(String cityId, String districtId) {
		if(!("-1".equals(cityId)) && !Validator.nullOrBlank(cityId)) {
			City cityObj = cityDAO.getById(Integer.parseInt(cityId.split("~")[0]));
			cityStr = cityObj.getCityId() + "~" + cityObj.getLat() + "~"
					+ cityObj.getLon() + "~" + cityObj.getCityName() + "~"
					+ cityObj.getTelAreaCodde();
		}
		if(!("-1".equals(districtId)) && !Validator.nullOrBlank(districtId)) {
			District districtObj = districtDAO.getById(Integer.parseInt(districtId.split("~")[0]));
			districtStr = districtObj.getDistrictId() + "~"
					+ districtObj.getLat() + "~" + districtObj.getLon() + "~"
					+ districtObj.getDistrictName();
		}
	}
	
	public void setValueAfterLogout() {
		this.submitStepOne = false;
		this.stepOne = false;
		this.completeAll = false;
		this.ignoreStepOne = false;
		this.logged = false;
	}

	/**
	 * @return the submitStepOne
	 */
	public boolean getSubmitStepOne() {
		return submitStepOne;
	}

	/**
	 * @param submitStepOne
	 *            the submitStepOne to set
	 */
	public void setSubmitStepOne(boolean submitStepOne) {
		this.submitStepOne = submitStepOne;
	}

	/**
	 * @param servletRequest
	 *            the servletRequest to set
	 */
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
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
	 * @return the smdUserDAO
	 */
	public SMDUserDAO getSmdUserDAO() {
		return smdUserDAO;
	}

	/**
	 * @param smdUserDAO
	 *            the smdUserDAO to set
	 */
	public void setSmdUserDAO(SMDUserDAO smdUserDAO) {
		this.smdUserDAO = smdUserDAO;
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
	 * @return the logged
	 */
	public boolean getLogged() {
		return logged;
	}

	/**
	 * @param logged
	 *            the logged to set
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * @return the ignoreStepOne
	 */
	public boolean getIgnoreStepOne() {
		return ignoreStepOne;
	}

	/**
	 * @param ignoreStepOne
	 *            the ignoreStepOne to set
	 */
	public void setIgnoreStepOne(boolean ignoreStepOne) {
		this.ignoreStepOne = ignoreStepOne;
	}
	
}
