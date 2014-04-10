package com.med.dic.model;

public class Degree {

	public int degreeId;
	public String degreeName;

	public Degree() {

	}

	/**
	 * @param degreeId
	 * @param degreeName
	 */
	public Degree(int degreeId, String degreeName) {
		super();
		this.degreeId = degreeId;
		this.degreeName = degreeName;
	}

	/**
	 * @return the degreeId
	 */
	public int getDegreeId() {
		return degreeId;
	}

	/**
	 * @param degreeId
	 *            the degreeId to set
	 */
	public void setDegreeId(int degreeId) {
		this.degreeId = degreeId;
	}

	/**
	 * @return the degreeName
	 */
	public String getDegreeName() {
		return degreeName;
	}

	/**
	 * @param degreeName
	 *            the degreeName to set
	 */
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
}
