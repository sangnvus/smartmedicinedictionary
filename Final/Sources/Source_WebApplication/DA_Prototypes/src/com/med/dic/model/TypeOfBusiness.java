package com.med.dic.model;

public class TypeOfBusiness {

	public int typeBusinessId;
	public String typeBusinessName;

	public TypeOfBusiness() {

	}

	/**
	 * @param typeBusinessId
	 * @param typeBusinessName
	 */
	public TypeOfBusiness(int typeBusinessId, String typeBusinessName) {
		super();
		this.typeBusinessId = typeBusinessId;
		this.typeBusinessName = typeBusinessName;
	}

	/**
	 * @return the typeBusinessId
	 */
	public int getTypeBusinessId() {
		return typeBusinessId;
	}

	/**
	 * @param typeBusinessId
	 *            the typeBusinessId to set
	 */
	public void setTypeBusinessId(int typeBusinessId) {
		this.typeBusinessId = typeBusinessId;
	}

	/**
	 * @return the typeBusinessName
	 */
	public String getTypeBusinessName() {
		return typeBusinessName;
	}

	/**
	 * @param typeBusinessName
	 *            the typeBusinessName to set
	 */
	public void setTypeBusinessName(String typeBusinessName) {
		this.typeBusinessName = typeBusinessName;
	}
}
