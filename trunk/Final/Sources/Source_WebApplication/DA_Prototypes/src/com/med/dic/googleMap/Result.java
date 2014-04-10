package com.med.dic.googleMap;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Result {

	private String formatted_address;
	private boolean partial_match;
	private Geometry geometry;
	@JsonIgnore
	private Object address_components;

	@JsonIgnore
	private Object types;

	/**
	 * @return the formatted_address
	 */
	public String getFormatted_address() {
		return formatted_address;
	}

	/**
	 * @param formatted_address
	 *            the formatted_address to set
	 */
	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	/**
	 * @return the partial_match
	 */
	public boolean isPartial_match() {
		return partial_match;
	}

	/**
	 * @param partial_match
	 *            the partial_match to set
	 */
	public void setPartial_match(boolean partial_match) {
		this.partial_match = partial_match;
	}

	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @param geometry
	 *            the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * @return the address_components
	 */
	public Object getAddress_components() {
		return address_components;
	}

	/**
	 * @param address_components
	 *            the address_components to set
	 */
	public void setAddress_components(Object address_components) {
		this.address_components = address_components;
	}

	/**
	 * @return the types
	 */
	public Object getTypes() {
		return types;
	}

	/**
	 * @param types
	 *            the types to set
	 */
	public void setTypes(Object types) {
		this.types = types;
	}
}
