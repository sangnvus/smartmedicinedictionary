package com.med.dic.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.database.SQLExecuteUtils;
import com.med.dic.database.SQLStatementUtils;
import com.opensymphony.xwork2.ActionSupport;

public class CheckEmailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id;
	public String countryCode;
	public List<String> email = new ArrayList<>();
	private ResultSet resultSet = null;

	public String emailList() {
		String query = SQLStatementUtils.GET_EMAIL;
		resultSet = SQLExecuteUtils.resultSet(query);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String districtName = resultSet
							.getString(DBNameCoreConstants.EMAIL);
					email.add(districtName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String ok() {
		return SUCCESS;
	}

	/**
	 * @return the email
	 */
	public List<String> getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(List<String> email) {
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
