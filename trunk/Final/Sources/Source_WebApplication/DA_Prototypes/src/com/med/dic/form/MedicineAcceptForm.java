package com.med.dic.form;

import java.util.List;

import com.med.dic.model.Medicine;

public class MedicineAcceptForm extends SearchMedicineForm {

	public boolean deleteFlagBoolean = false;
	public boolean searched = false;
	public boolean noResult = false;
	public boolean detailed = false;
	public boolean checkaccept;
	public boolean checkAll;
	public String medIdList;
	public String keyWordMedIndication;
	public List<Medicine> medicineList;
	public Medicine medicine = new Medicine();
	public int medIdDetail;
	public String[] checkBox;
	public boolean acceptSuccess = false;
	public boolean failedAction;
	public boolean successedAction;
	public String message;

	/**
	 * @return the deleteFlagBoolean
	 */
	public boolean getsDeleteFlagBoolean() {
		return deleteFlagBoolean;
	}

	/**
	 * @param deleteFlagBoolean
	 *            the deleteFlagBoolean to set
	 */
	public void setDeleteFlagBoolean(boolean deleteFlagBoolean) {
		this.deleteFlagBoolean = deleteFlagBoolean;
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
	 * @return the detailed
	 */
	public boolean getDetailed() {
		return detailed;
	}

	/**
	 * @param detailed
	 *            the detailed to set
	 */
	public void setDetailed(boolean detailed) {
		this.detailed = detailed;
	}

	/**
	 * @return the checkaccept
	 */
	public boolean getCheckaccept() {
		return checkaccept;
	}

	/**
	 * @param checkaccept
	 *            the checkaccept to set
	 */
	public void setCheckaccept(boolean checkaccept) {
		this.checkaccept = checkaccept;
	}

	/**
	 * @return the checkAll
	 */
	public boolean getCheckAll() {
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

	/**
	 * @return the medicineList
	 */
	public List<Medicine> getMedicineList() {
		return medicineList;
	}

	/**
	 * @param medicineList
	 *            the medicineList to set
	 */
	public void setMedicineList(List<Medicine> medicineList) {
		this.medicineList = medicineList;
	}

	/**
	 * @return the deleteFlagBoolean
	 */
	public boolean getDeleteFlagBoolean() {
		return deleteFlagBoolean;
	}

	/**
	 * @return the medicine
	 */
	public Medicine getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine
	 *            the medicine to set
	 */
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	/**
	 * @return the keyWordMedIndication
	 */
	public String getKeyWordMedIndication() {
		return keyWordMedIndication;
	}

	/**
	 * @param keyWordMedIndication
	 *            the keyWordMedIndication to set
	 */
	public void setKeyWordMedIndication(String keyWordMedIndication) {
		this.keyWordMedIndication = keyWordMedIndication;
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
	 * @return the acceptSuccess
	 */
	public boolean getAcceptSuccess() {
		return acceptSuccess;
	}

	/**
	 * @param acceptSuccess
	 *            the acceptSuccess to set
	 */
	public void setAcceptSuccess(boolean acceptSuccess) {
		this.acceptSuccess = acceptSuccess;
	}

	/**
	 * @return the failedAction
	 */
	public boolean getFailedAction() {
		return failedAction;
	}

	/**
	 * @param failedAction the failedAction to set
	 */
	public void setFailedAction(boolean failedAction) {
		this.failedAction = failedAction;
	}

	/**
	 * @return the successedAction
	 */
	public boolean getSuccessedAction() {
		return successedAction;
	}

	/**
	 * @param successedAction the successedAction to set
	 */
	public void setSuccessedAction(boolean successedAction) {
		this.successedAction = successedAction;
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
