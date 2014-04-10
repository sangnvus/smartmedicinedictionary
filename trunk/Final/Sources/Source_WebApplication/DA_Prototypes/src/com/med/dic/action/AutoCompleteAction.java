package com.med.dic.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.database.SQLExecuteUtils;
import com.med.dic.database.SQLStatementUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AutoCompleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int selectBoxAjax1;
	public List<String> autoString = new ArrayList<>();
	public List<String> medForPhar = new ArrayList<>();
	public List<String> locationString = new ArrayList<>();
	public List<String> selectString = new ArrayList<>();
	public List<String> selectDistrictStr = new ArrayList<>();
	public List<String> selectTypeStr = new ArrayList<>();
	public List<String> degreeString = new ArrayList<>();
	public List<String> titleOfNews = new ArrayList<>();
	public List<String> symptoms = new ArrayList<>();
	public List<String> contraindications = new ArrayList<>();
	private ResultSet resultSet = null;
	public int pharmacyId;

	public String autoCompleteMed() {
		String query = SQLStatementUtils.GET_MED_NAME;
		resultSet = SQLExecuteUtils.resultSet(query);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String medName = resultSet.getString("MEDICINE_NAME");
					autoString.add(medName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String autoCompleteSymptom() {
		String query = SQLStatementUtils.GET_SYMPTOMS;
		resultSet = SQLExecuteUtils.resultSet(query);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String symptomName = resultSet.getString(DBNameCoreConstants.SYMPTOM_NAME);
					symptoms.add(symptomName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String autoCompleteContraindications() {
		String query = SQLStatementUtils.GET_CONTRAINDICATIONS;
		resultSet = SQLExecuteUtils.resultSet(query);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String contraName = resultSet.getString(DBNameCoreConstants.CONTRAINDICATION_NAME);
					contraindications.add(contraName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String autoCompleteNews() {
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_NEWS);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String title = resultSet.getString(DBNameCoreConstants.TITLE);
					titleOfNews.add(title);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String medForPhar() {
		String query = SQLStatementUtils.GET_MED_NAME_FOR_PHAR;
		resultSet = 
				//SQLExecuteUtils.resultSet(query);
		SQLExecuteUtils.result(query, this.pharmacyId);
		if(resultSet != null) {
			try {
				while (resultSet.next()) {
					String medName = resultSet.getString("MEDICINE_NAME");
					medForPhar.add(medName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String autoCompletePhar() {
		String query = SQLStatementUtils.GET_PHARMACY_NAME; 
		resultSet = SQLExecuteUtils.resultSet(query);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String pharmacyName = resultSet.getString(DBNameCoreConstants.PHARMACY_NAME);
					locationString.add(pharmacyName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String Degree() {
		String query = SQLStatementUtils.GET_DEGREE; 
		resultSet = SQLExecuteUtils.resultSet(query);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String degreeId = resultSet.getString(DBNameCoreConstants.DEGREE_ID);
					String degreeName = resultSet.getString(DBNameCoreConstants.DEGREE_NAME);
					degreeString.add(degreeName + "~" + degreeId);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String selectBox() {
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.SELECT_CITY
				+ SQLStatementUtils.ORDER_BY + DBNameCoreConstants.CITY_NAME
				+ " collate utf8_czech_ci");
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String cityName = resultSet.getString(DBNameCoreConstants.CITY_NAME);
					String cityId = resultSet.getString(DBNameCoreConstants.CITY_ID);
					selectString.add(cityName + "~" + cityId);
				}
				resultSet = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(selectBoxAjax1 != -1) {
			resultSet = SQLExecuteUtils.result(
					SQLStatementUtils.SELECT_DISTRICT
							+ SQLStatementUtils.ORDER_BY
							+ DBNameCoreConstants.DISTRICT_NAME
							+ " collate utf8_czech_ci",
					this.selectBoxAjax1);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						String districtName = resultSet.getString(DBNameCoreConstants.DISTRICT_NAME);
						String districtId = resultSet.getString(DBNameCoreConstants.DISTRICT_ID);
						selectDistrictStr.add(districtName + "~" + districtId);
					}
					resultSet = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			selectDistrictStr = new ArrayList<>();
		}
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_TYPE_OF_BUSINESS
				+ SQLStatementUtils.ORDER_BY + DBNameCoreConstants.TYPE_OF_BUSINESS
				+ " collate utf8_czech_ci");
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					String typeName = resultSet.getString(DBNameCoreConstants.TYPE_OF_BUSINESS);
					String typeId = resultSet.getString(DBNameCoreConstants.ID);
					selectTypeStr.add(typeName + "~" + typeId);
				}
				resultSet = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @return the autoString
	 */
	public List<String> getAutoString() {
		return autoString;
	}

	/**
	 * @param autoString
	 *            the autoString to set
	 */
	public void setAutoString(List<String> autoString) {
		this.autoString = autoString;
	}

	/**
	 * @return the locationString
	 */
	public List<String> getLocationString() {
		return locationString;
	}

	/**
	 * @param locationString
	 *            the locationString to set
	 */
	public void setLocationString(List<String> locationString) {
		this.locationString = locationString;
	}

	/**
	 * @return the selectString
	 */
	public List<String> getSelectString() {
		return selectString;
	}

	/**
	 * @param selectString the selectString to set
	 */
	public void setSelectString(List<String> selectString) {
		this.selectString = selectString;
	}

	/**
	 * @return the selectBoxAjax1
	 */
	public int getSelectBoxAjax1() {
		return selectBoxAjax1;
	}

	/**
	 * @param selectBoxAjax1 the selectBoxAjax1 to set
	 */
	public void setSelectBoxAjax1(int selectBoxAjax1) {
		this.selectBoxAjax1 = selectBoxAjax1;
	}

	/**
	 * @return the selectDistrictStr
	 */
	public List<String> getSelectDistrictStr() {
		return selectDistrictStr;
	}

	/**
	 * @param selectDistrictStr the selectDistrictStr to set
	 */
	public void setSelectDistrictStr(List<String> selectDistrictStr) {
		this.selectDistrictStr = selectDistrictStr;
	}

	/**
	 * @return the selectTypeStr
	 */
	public List<String> getSelectTypeStr() {
		return selectTypeStr;
	}

	/**
	 * @param selectTypeStr the selectTypeStr to set
	 */
	public void setSelectTypeStr(List<String> selectTypeStr) {
		this.selectTypeStr = selectTypeStr;
	}


	/**
	 * @return the medForPhar
	 */
	public List<String> getMedForPhar() {
		return medForPhar;
	}


	/**
	 * @param medForPhar the medForPhar to set
	 */
	public void setMedForPhar(List<String> medForPhar) {
		this.medForPhar = medForPhar;
	}

	/**
	 * @return the pharmacyId
	 */
	public int getPharmacyId() {
		return pharmacyId;
	}

	/**
	 * @param pharmacyId the pharmacyId to set
	 */
	public void setPharmacyId(int pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	/**
	 * @return the degreeString
	 */
	public List<String> getDegreeString() {
		return degreeString;
	}

	/**
	 * @param degreeString the degreeString to set
	 */
	public void setDegreeString(List<String> degreeString) {
		this.degreeString = degreeString;
	}

	/**
	 * @return the titleOfNews
	 */
	public List<String> getTitleOfNews() {
		return titleOfNews;
	}

	/**
	 * @param titleOfNews the titleOfNews to set
	 */
	public void setTitleOfNews(List<String> titleOfNews) {
		this.titleOfNews = titleOfNews;
	}

	/**
	 * @return the symptoms
	 */
	public List<String> getSymptoms() {
		return symptoms;
	}

	/**
	 * @param symptoms the symptoms to set
	 */
	public void setSymptoms(List<String> symptoms) {
		this.symptoms = symptoms;
	}

	/**
	 * @return the contraindications
	 */
	public List<String> getContraindications() {
		return contraindications;
	}

	/**
	 * @param contraindications the contraindications to set
	 */
	public void setContraindications(List<String> contraindications) {
		this.contraindications = contraindications;
	}

}
