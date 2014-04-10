package com.med.dic.diary.using;

import java.io.Serializable;


public class DiaryTitle implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int titleId;
	private String titleName;
	private String startDate;
	private String endDate;
	private String mode;

	/* Constructor */
	public DiaryTitle(int titleId, String titleName, String startDate, String endDate) {
		super();
		this.titleId = titleId;
		this.titleName = titleName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/* Constructor */
	public DiaryTitle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTitleId() {
		return titleId;
	}
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
