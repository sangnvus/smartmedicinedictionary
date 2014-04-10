package com.med.dic;

public class AlarmInfo {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String title;
	private String medicine;
	private String time;
	private String amount;
	private int titleId;
	private int medicineId;

	public AlarmInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlarmInfo(int year, int month, int day, int hour, int minute,
			String title, String medicine, String time, String amount, int titleId, int medicineId) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.title = title;
		this.medicine = medicine;
		this.time = time;
		this.amount = amount;
		this.titleId = titleId;
		this.medicineId = medicineId;
	}


	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}
	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	/**
	 * @return the minute
	 */
	public int getMinute() {
		return minute;
	}
	/**
	 * @param minute the minute to set
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the medicine
	 */
	public String getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the titleId
	 */
	public int getTitleId() {
		return titleId;
	}

	/**
	 * @param titleId the titleId to set
	 */
	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	/**
	 * @return the medicineId
	 */
	public int getMedicineId() {
		return medicineId;
	}

	/**
	 * @param medicineId the medicineId to set
	 */
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
}
