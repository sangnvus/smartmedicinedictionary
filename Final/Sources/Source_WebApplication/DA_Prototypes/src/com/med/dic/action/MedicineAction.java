package com.med.dic.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.jca.cci.object.EisOperation;

import com.med.dic.base.action.BaseAction;
import com.med.dic.constant.MedicineDictionaryConstants;
import com.med.dic.dao.MedicineDAO;
import com.med.dic.dao.MedicineTypeDAO;
import com.med.dic.dao.PharmacyMedicineDAO;
import com.med.dic.dao.TypeOfMedicineDAO;
import com.med.dic.form.MedicineForm;
import com.med.dic.model.Medicine;
import com.med.dic.model.MedicineType;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.SMDUser;
import com.med.dic.model.TypeOfMedicine;
import com.med.dic.utility.AuthenticationUtility;
import com.med.dic.utility.MedicineBreakCharacter;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionContext;

public class MedicineAction extends MedicineForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception {
		String actionName = ActionContext.getContext().getName();
		SMDUser smdUser = new SMDUser();
		smdUser.setPreviousUser(this.previousUser);
		smdUser.setEmail(this.smdEmail);
		String user = AuthenticationUtility.checkSmdUser(this.session, smdUser);
		if("pharmacy_addNewMed".equals(actionName) || "pharmacy_copyNewMedicine".equals(actionName)) {
			if(MedicineDictionaryConstants.userGroup2.equals(user) && smdUser.getPreviousUser()) {
				if("pharmacy_addNewMed".equals(actionName)) {
					return pharmacy_addNewMed();
				} else {
					return admin_copyNewMedicine();
				}
			} else {
				return user;
			}
		} else {
			if(MedicineDictionaryConstants.userGroup1.equals(user)) {
				if("admin_copyNewMedicine".equals(actionName)) {
					return admin_copyNewMedicine();
				} else if("admin_addNewMed".equals(actionName)) {
					return admin_addNewMed();
				} else if("admin_changeMedInfo".equals(actionName)) {
					return admin_changeMedInfo();
				} else if("admin_updateMed".equals(actionName)) {
					return updateMedicine();
				} else if("admin_deleteMed".equals(actionName)) {
					return admin_deleteMed();
				} else if("admin_restoreMedicine".equals(actionName)) {
					return admin_restoreMedicine();
				} else if("admin_medicineManagementCancel".equals(actionName)) {
					this.searched = false;
					this.detail = false;
					this.copyNew = false;
					this.delete = false;
					this.restore = false;
					this.actionSucces = false;
					return SUCCESS;
				}
			} else {
				return user;
			}
			
		}
		return SUCCESS;
	}

	private void setValueIn() {
		SMDUser smdUser = (SMDUser)session.get("userGroup1");
		if(smdUser == null) {
			smdUser = (SMDUser)session.get("userGroup2");
		}
		medicine.setMedId(medId);
		medicine.setDeteleFlag("N");
		medicine.setModDate(new Date());
		medicine.setRegDate(regDate);
		medicine.setRegUser(smdUser.getEmail());
		medicine.setModUser(smdUser.getEmail());
		medicine.setMedName(medName);
		medicine.setManufacturer(manufacturer);
		medicine.setIndications(indications);
		medicine.setContraindications(contraindications);
		medicine.setDosingAndUse(dosingAndUse);
		medicine.setIngredients(ingredients);
		medicine.setTypeOfPackageId(this.typeOfMedId);
		medicine.setTypeOfPackageName(typeOfPackageName);
		medicine.setWarning(warning);
		medicine.setStorage(storage);
		medicine.setGenericMedicine(genericMedicine);
		medicine.setBrandName(brandName);
		medicine.setInteraction(interaction);
		medicine.setSimilarMedicine(similarMedicine);
	}

	private void resetProperty() {
		searched = false;
		detail = false;
		medicineDAO = null;
		medList = null;
		deleteFlagBoolean = false;
		medId = -1;
		deleteFlag = "";
		medName = "";
		manufacturer = "";
		indications = "";
		contraindications = "";
		dosingAndUse = "";
		ingredients = "";
		typeOfPackageName = "";
		typeOfPackageId = -1;
		warning = "";
		storage = "";
		imgPath = "";
		genericMedicine = "";
		brandName = "";
		medicine.setMedId(-1);
		medicine.deteleFlag = "";
		medicine.medName = "";
		medicine.manufacturer = "";
		medicine.indications = "";
		medicine.contraindications = "";
		medicine.dosingAndUse = "";
		medicine.ingredients = "";
		medicine.typeOfPackageName = "";
		medicine.typeOfPackageId = -1;
		medicine.warning = "";
		medicine.storage = "";
		medicine.imgPath = "";
		medicine.genericMedicine = "";
		medicine.brandName = "";
		keyWordMedTypeName = "0";
	}

	public String admin_medicineManagementCancel() {
		//resetProperty();
		return "success";
	}

	public String admin_deleteMed() {
		medicine = medicineDAO.searchByID(this.medId);
		medicine.setDeteleFlag("Y");
		medicineDAO.updateMedicine(medicine);
		//resetProperty();
		delete = true;
		this.actionSucces = true;
		this.message = "Bạn đã xóa thuốc thành công";
		return "success";
	}

	public String admin_restoreMedicine() {
		medicine = medicineDAO.searchByID(this.medId);
		medicine.setDeteleFlag("N");
		medicineDAO.updateMedicine(medicine);
		//resetProperty();
		restore = true;
		this.actionSucces = true;
		this.message = "Bạn đã khôi phục thuốc thành công";
		return "success";
	}

	public String admin_copyNewMedicine() {
		medicine = medicineDAO.searchByID(this.medId);
		copyNew = true;
		edit = false;
		this.actionSucces = false;
		//keyWordMedTypeName = "" + medicine.typeOfPackageId;
		medTypeNameList = "";
		medTypeIdList = "";
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		typeOfMedList = typeOfMedDAO.getByMedId(medId);
		for (TypeOfMedicine typeOfMedicine : typeOfMedList) {
			medTypeNameList += typeOfMedicine.getTypeOfPackageName() + ",";
			medTypeIdList += typeOfMedicine.getTypeOfPackageId() + ",";
		}
		if(medTypeNameList.length() > 0) {
			medTypeNameList = medTypeNameList.substring(0, medTypeNameList.length() - 1);
			medTypeIdList = medTypeIdList.substring(0, medTypeIdList.length() - 1);
		}
		this.addByPharSuccess = false;
		//this.keyWordMedTypeName = medTypeNameList;
		return "success";
	}

	public String admin_addNewMed() throws Exception {
		if(!Validator.nullOrBlank(medTypeIdList)) {
			String[] medTypeSplit = medTypeIdList.split(",");
			String[] medTypeNameSplit = medTypeNameList.split(",");
			this.typeOfMedId = Integer.parseInt(medTypeSplit[0]);
			setValueIn();
			if (keyWordMedImage == null) {
				medicine.imgPath = "no_img.jpg";
			} else {
				try {
					String filePath = servletRequest.getSession()
							.getServletContext().getRealPath("/");
					String filePathImage = filePath + "image/medicine/";
					File image = keyWordMedImage;
					keyWordMedImageFileName = timeStamp + keyWordMedImageFileName;
					File destFile = new File(filePathImage
							+ keyWordMedImageFileName);
					FileUtils.copyFile(image, destFile);
					medicine.imgPath = this.keyWordMedImageFileName;
				} catch (Exception e) {
					e.printStackTrace();
					medicine.imgPath = "no_img.jpg";
				}
			}
			medicine.setDeteleFlag("N");
			medicine.setRegDate(new Date());
			medicine.setModDate(new Date());
			medTypeObj = medTypeDAO
					.searchMedTypeById(medicine.getTypeOfPackageId());
			medicine.setTypeOfPackageName(medTypeObj.getMedTypeName());
			int addMedId = medicineDAO.addNewMed(medicine);
			for (int i = 0; i < medTypeSplit.length; i++) {
				TypeOfMedicine typeOfMed = new TypeOfMedicine();
				typeOfMed.setMedId(addMedId);
				typeOfMed.setTypeOfPackageId(Integer.parseInt(medTypeSplit[i]));
				typeOfMed.setTypeOfPackageName(medTypeNameSplit[i]);
				typeOfMedDAO.add(typeOfMed);
			}
			medicine = MedicineBreakCharacter.breakChar(medicine);
			//resetProperty();
			copyNew = false;
			this.actionSucces = true;
			this.message = "Bạn đã thêm thuốc mới thành công";
		}
		return "success";
	}

	public String admin_changeMedInfo() {
		medicine = medicineDAO.searchByID(medId);
		medTypeNameList = "";
		medTypeIdList = "";
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		typeOfMedList = typeOfMedDAO.getByMedId(medId);
		for (TypeOfMedicine typeOfMedicine : typeOfMedList) {
			medTypeNameList += typeOfMedicine.getTypeOfPackageName() + ",";
			medTypeIdList += typeOfMedicine.getTypeOfPackageId() + ",";
		}
		if(medTypeNameList.length() > 0) {
			medTypeNameList = medTypeNameList.substring(0, medTypeNameList.length() - 1);
			medTypeIdList = medTypeIdList.substring(0, medTypeIdList.length() - 1);
		}
		edit = true;
		this.actionSucces = false;
		//keyWordMedTypeName = "" + medicine.typeOfPackageId;
		return "success";
	}

	public String updateMedicine() throws Exception {
		if(!Validator.nullOrBlank(medTypeIdList)) {
			String[] medTypeSplit = medTypeIdList.split(",");
			String[] medTypeNameSplit = medTypeNameList.split(",");
			this.typeOfMedId = Integer.parseInt(medTypeSplit[0]);
			setValueIn();
			if (keyWordMedImage == null) {
				medicine.imgPath = this.imgPath;
			} else {
				try {
					String filePath = servletRequest.getSession()
							.getServletContext().getRealPath("/");
					String filePathImage = filePath + "image/medicine/";
					File image = keyWordMedImage;
					keyWordMedImageFileName = timeStamp + keyWordMedImageFileName;
					File destFile = new File(filePathImage
							+ keyWordMedImageFileName);
					FileUtils.copyFile(image, destFile);
					medicine.imgPath = this.keyWordMedImageFileName;
				} catch (Exception e) {
					e.printStackTrace();
					medicine.imgPath = "no_img.jpg";
				}
			}
			Medicine originalMed = medicineDAO.searchByID(this.medId);
			medTypeObj = medTypeDAO
					.searchMedTypeById(medicine.getTypeOfPackageId());
			medicine.setRegDate(originalMed.getRegDate());
			medicine.setSearchedTime(originalMed.getSearchedTime());
			medicine.setTypeOfPackageName(medTypeObj.getMedTypeName());
			medicineDAO.updateMedicine(medicine);
			typeOfMedDAO.delete(medId);
			for (int i = 0; i < medTypeSplit.length; i++) {
				TypeOfMedicine typeOfMed = new TypeOfMedicine();
				typeOfMed.setMedId(this.medId);
				typeOfMed.setTypeOfPackageId(Integer.parseInt(medTypeSplit[i]));
				typeOfMed.setTypeOfPackageName(medTypeNameSplit[i]);
				typeOfMedDAO.add(typeOfMed);
			}
			edit = false;
			this.message = "Bạn đã chỉnh sửa thuốc thành công";
			this.actionSucces = true;
			//resetProperty();
		}
		return "success";
	}

	public String pharmacy_addNewMed() {
		PharmacyMedicineAction pma = new PharmacyMedicineAction();
		if(!Validator.nullOrBlank(medTypeIdList)) {
			String[] medTypeSplit = medTypeIdList.split(",");
			String[] medTypeNameSplit = medTypeNameList.split(",");
			this.typeOfMedId = Integer.parseInt(medTypeSplit[0]);
			setValueIn();
			if (keyWordMedImage == null) {
				medicine.imgPath = "no_img.jpg";
			} else {
				try {
					String filePath = servletRequest.getSession()
							.getServletContext().getRealPath("/");
					String filePathImage = filePath + "image/medicine/";
					File image = keyWordMedImage;
					keyWordMedImageFileName = timeStamp
							+ keyWordMedImageFileName;
					this.imgPath = keyWordMedImageFileName;
					File destFile = new File(filePathImage
							+ keyWordMedImageFileName);
					FileUtils.copyFile(image, destFile);
					medicine.imgPath = this.keyWordMedImageFileName;
				} catch (Exception e) {
					e.printStackTrace();
					medicine.imgPath = "no_img.jpg";
				}
			}
			medicine.setDeteleFlag("N");
			medicine.setAccept(this.accept);
			medicine.setRegDate(new Date());
			medicine.setModDate(new Date());
			medicine.setAddedByPharId(this.pharmacyId);
			medTypeObj = medTypeDAO.searchMedTypeById(medicine
					.getTypeOfPackageId());
			medicine.setTypeOfPackageName(medTypeObj.getMedTypeName());
			int addMedId = medicineDAO.addNewMed(medicine);
			for (int i = 0; i < medTypeSplit.length; i++) {
				TypeOfMedicine typeOfMed = new TypeOfMedicine();
				typeOfMed.setMedId(addMedId);
				typeOfMed.setTypeOfPackageId(Integer.parseInt(medTypeSplit[i]));
				typeOfMed.setTypeOfPackageName(medTypeNameSplit[i]);
				typeOfMedDAO.add(typeOfMed);
			}
			medicine.setMedId(addMedId);
			this.medId = addMedId;
			pma.addNewPharmacyMedicine(medicine, pharmacyId, pharMedDAO,
					session, servletRequest, visitTimeDAO, pharmacyDAO);
			medicine = MedicineBreakCharacter.breakChar(medicine);
			copyNew = true;
			medChoosen = false;
		}
		addByPharSuccess = true;
		return "success";
	}
}
