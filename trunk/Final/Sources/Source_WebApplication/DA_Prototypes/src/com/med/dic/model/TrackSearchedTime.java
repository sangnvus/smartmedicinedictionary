package com.med.dic.model;

import java.io.Serializable;

public class TrackSearchedTime implements Serializable {

	public int type;
	public String monthYear;
	public int searchedTime;
	public int typeId;
	public String typeName;
	
	public TrackSearchedTime() {
		
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the monthYear
	 */
	public String getMonthYear() {
		return monthYear;
	}

	/**
	 * @param monthYear the monthYear to set
	 */
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	/**
	 * @return the searchedTime
	 */
	public int getSearchedTime() {
		return searchedTime;
	}

	/**
	 * @param searchedTime the searchedTime to set
	 */
	public void setSearchedTime(int searchedTime) {
		this.searchedTime = searchedTime;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
