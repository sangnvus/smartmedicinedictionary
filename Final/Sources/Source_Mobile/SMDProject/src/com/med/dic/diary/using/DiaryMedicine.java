package com.med.dic.diary.using;

import java.io.Serializable;

public class DiaryMedicine implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int diaryMedicineId;
	private int diaryTitleId;
	private String diaryMedicineTitle;
	private String time1;
	private String time2;
	private String time3;
	private String time4;
	private String time5;
	private String amount1;
	private String amount2;
	private String amount3;
	private String amount4;
	private String amount5;
	private String diaryTitle;
	private String diaryStart;
	private String diaryEnd;
	private String mode;


	public DiaryMedicine(int diaryMedicineId, int diaryTitleId, String diaryMedicineTitle, String time1,
			String time2, String time3, String time4, String time5,
			String amount1, String amount2, String amount3, String amount4,
			String amount5, String diaryTitle, String diaryStart, String diaryEnd) {
		super();
		this.diaryMedicineId = diaryMedicineId;
		this.diaryTitleId = diaryTitleId;
		this.diaryMedicineTitle = diaryMedicineTitle;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
		this.time4 = time4;
		this.time5 = time5;
		this.amount1 = amount1;
		this.amount2 = amount2;
		this.amount3 = amount3;
		this.amount4 = amount4;
		this.amount5 = amount5;
		this.diaryTitle = diaryTitle;
		this.diaryStart = diaryStart;
		this.diaryEnd = diaryEnd;
	}


	public DiaryMedicine() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getDiaryMedicineId() {
		return diaryMedicineId;
	}
	public void setDiaryMedicineId(int diaryMedicineId) {
		this.diaryMedicineId = diaryMedicineId;
	}
	public int getDiaryTitleId() {
		return diaryTitleId;
	}
	public void setDiaryTitleId(int diaryTitleId) {
		this.diaryTitleId = diaryTitleId;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getTime3() {
		return time3;
	}
	public void setTime3(String time3) {
		this.time3 = time3;
	}
	public String getTime4() {
		return time4;
	}
	public void setTime4(String time4) {
		this.time4 = time4;
	}
	public String getTime5() {
		return time5;
	}
	public void setTime5(String time5) {
		this.time5 = time5;
	}
	public String getAmount1() {
		return amount1;
	}
	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}
	public String getAmount2() {
		return amount2;
	}
	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}
	public String getAmount3() {
		return amount3;
	}
	public void setAmount3(String amount3) {
		this.amount3 = amount3;
	}
	public String getAmount4() {
		return amount4;
	}
	public void setAmount4(String amount4) {
		this.amount4 = amount4;
	}
	public String getAmount5() {
		return amount5;
	}
	public void setAmount5(String amount5) {
		this.amount5 = amount5;
	}


	public String getDiaryMedicineTitle() {
		return diaryMedicineTitle;
	}


	public void setDiaryMedicineTitle(String diaryMedicineTitle) {
		this.diaryMedicineTitle = diaryMedicineTitle;
	}


	/**
	 * @return the diaryTitle
	 */
	public String getDiaryTitle() {
		return diaryTitle;
	}


	/**
	 * @param diaryTitle the diaryTitle to set
	 */
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}


	/**
	 * @return the diaryStart
	 */
	public String getDiaryStart() {
		return diaryStart;
	}


	/**
	 * @param diaryStart the diaryStart to set
	 */
	public void setDiaryStart(String diaryStart) {
		this.diaryStart = diaryStart;
	}


	/**
	 * @return the diaryEnd
	 */
	public String getDiaryEnd() {
		return diaryEnd;
	}


	/**
	 * @param diaryEnd the diaryEnd to set
	 */
	public void setDiaryEnd(String diaryEnd) {
		this.diaryEnd = diaryEnd;
	}


	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}


	/**
	 * @param mode the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
}
