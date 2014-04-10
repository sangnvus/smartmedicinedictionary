package com.med.dic.action;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.database.SQLExecuteUtils;
import com.med.dic.database.SQLStatementUtils;
import com.med.dic.model.City;
import com.med.dic.model.Degree;
import com.med.dic.model.District;
import com.med.dic.model.Hour;
import com.med.dic.model.Minute;
import com.med.dic.model.TypeOfBusiness;
import com.med.dic.validate.Validator;
import com.opensymphony.xwork2.ActionSupport;

public class SelectAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String pharmacyName;
	public String cityStr;
	public String districtStr;
	private List<City> cityList;
	private List<District> districtList;
	private List<TypeOfBusiness> typeOfBusinessList;
	private List<Hour> morningFromHourList;
	private List<Minute> morningFromMinuteList;
	private List<Hour> morningToHourList;
	private List<Minute> morningToMinuteList;
	private List<Hour> afterFromHourList;
	private List<Minute> afterFromMinuteList;
	private List<Hour> afterToHourList;
	private List<Minute> afterToMinuteList;
	private String district;
	private String city;
	private String morningFromHour;
	private String morningFromMinute;
	private String morningToHour;
	private String morningToMinute;
	private String afterFromHour;
	private String afterFromMinute;
	private String afterToHour;
	private String afterToMinute;
	public String morToHour;
	public String morToMinute;
	public String afToHour;
	public String afToMinute;

	private ResultSet resultSet = null;

	public String loadWorkingHour() {
		morningFromHourList = new ArrayList<>();
		morningToHourList = new ArrayList<>();
		morningFromMinuteList = new ArrayList<>();
		morningToMinuteList = new ArrayList<>();
		afterFromHourList = new ArrayList<>();
		afterToHourList = new ArrayList<>();
		afterFromMinuteList = new ArrayList<>();
		afterToMinuteList = new ArrayList<>();

		Hour hourEnd = new Hour();
		hourEnd.setHourId(24);
		hourEnd.setHourValue("00");

		Minute minuteEnd = new Minute();
		minuteEnd.setMinuteId(60);
		minuteEnd.setMinuteValue("00");

		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_HOUR);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					Hour hour = new Hour();
					int hourId = resultSet.getInt(DBNameCoreConstants.HOUR);
					String hourValue = "";
					if (hourId < 10) {
						hourValue = "0" + hourId;
					} else {
						hourValue = String.valueOf(hourId);
					}
					hour.setHourId(hourId);
					hour.setHourValue(hourValue);
					if (hourId < 13) {
						morningFromHourList.add(hour);
					} else {
						if (hourId < 24) {
							afterFromHourList.add(hour);
						}
					}
				}
				afterFromHourList.add(hourEnd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_MINUTE);
		if (resultSet != null) {
			morningFromMinuteList.add(minuteEnd);
			try {
				while (resultSet.next()) {
					Minute minute = new Minute();
					int minuteId = resultSet.getInt(DBNameCoreConstants.MINUTE);
					String minuteValue = "";
					if (minuteId < 10) {
						minuteValue = "0" + minuteId;
					} else {
						minuteValue = String.valueOf(minuteId);
					}
					minute.setMinuteId(minuteId);
					minute.setMinuteValue(minuteValue);
					if (minuteId < 60) {
						morningFromMinuteList.add(minute);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_MINUTE);
		if (resultSet != null) {
			afterFromMinuteList.add(minuteEnd);
			try {
				while (resultSet.next()) {
					Minute minute = new Minute();
					int minuteId = resultSet.getInt(DBNameCoreConstants.MINUTE);
					String minuteValue = "";
					if (minuteId < 10) {
						minuteValue = "0" + minuteId;
					} else {
						minuteValue = String.valueOf(minuteId);
					}
					minute.setMinuteId(minuteId);
					minute.setMinuteValue(minuteValue);
					if (minuteId < 60) {
						afterFromMinuteList.add(minute);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultSet = null;
		}
		if (!Validator.nullOrBlank(morningFromHour)) {
			if (Integer.parseInt(morningFromHour) < Integer
					.parseInt(morningToHour)) {
				// resultSet = SQLExecuteUtils.result(SQLStatementUtils.GET_HOUR
				// + " WHERE " + DBNameCoreConstants.HOUR + " >= ?",
				// Integer.parseInt(morningToHour));
				this.morToHour = this.morningToHour;
			}
			// else {
			resultSet = SQLExecuteUtils.result(SQLStatementUtils.GET_HOUR
					+ " WHERE " + DBNameCoreConstants.HOUR + " >= ?",
					Integer.parseInt(morningFromHour));
			// }
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Hour hour = new Hour();
						int hourId = resultSet.getInt(DBNameCoreConstants.HOUR);
						String hourValue = "";
						if (hourId < 10) {
							hourValue = "0" + hourId;
						} else {
							hourValue = String.valueOf(hourId);
						}
						hour.setHourId(hourId);
						hour.setHourValue(hourValue);
						if (hourId < 13) {
							morningToHourList.add(hour);
						} else {
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		} else {
			resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_HOUR);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Hour hour = new Hour();
						int hourId = resultSet.getInt(DBNameCoreConstants.HOUR);
						String hourValue = "";
						if (hourId < 10) {
							hourValue = "0" + hourId;
						} else {
							hourValue = String.valueOf(hourId);
						}
						hour.setHourId(hourId);
						hour.setHourValue(hourValue);
						if (hourId < 13) {
							morningToHourList.add(hour);
						} else {
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		}
		if (!Validator.nullOrBlank(afterFromHour)) {
			if (Integer.parseInt(afterFromHour) < Integer.parseInt(afterToHour)) {
				// resultSet = SQLExecuteUtils.result(SQLStatementUtils.GET_HOUR
				// + " WHERE " + DBNameCoreConstants.HOUR + " >= ?",
				// Integer.parseInt(afterToHour));
				afToHour = afterToHour;
			}
			// else {
			resultSet = SQLExecuteUtils.result(SQLStatementUtils.GET_HOUR
					+ " WHERE " + DBNameCoreConstants.HOUR + " >= ?",
					Integer.parseInt(afterFromHour));
			// }
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Hour hour = new Hour();
						int hourId = resultSet.getInt(DBNameCoreConstants.HOUR);
						String hourValue = "";
						if (hourId < 10) {
							hourValue = "0" + hourId;
						} else {
							hourValue = String.valueOf(hourId);
						}
						hour.setHourId(hourId);
						hour.setHourValue(hourValue);
						if (hourId > 12 && hourId < 24) {
							afterToHourList.add(hour);
						}
					}
					afterToHourList.add(hourEnd);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		} else {
			resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_HOUR);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Hour hour = new Hour();
						int hourId = resultSet.getInt(DBNameCoreConstants.HOUR);
						String hourValue = "";
						if (hourId < 10) {
							hourValue = "0" + hourId;
						} else {
							hourValue = String.valueOf(hourId);
						}
						hour.setHourId(hourId);
						hour.setHourValue(hourValue);
						if (hourId > 12 && hourId < 24) {
							afterToHourList.add(hour);
						}
					}
					afterToHourList.add(hourEnd);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		}
		if (!Validator.nullOrBlank(morningFromMinute)) {
			if (Integer.parseInt(morningFromMinute) < Integer
					.parseInt(morningToMinute)) {
				// resultSet =
				// SQLExecuteUtils.result(SQLStatementUtils.GET_MINUTE
				// + " WHERE " + DBNameCoreConstants.MINUTE + " >= ?",
				// Integer.parseInt(morningToMinute));
				morToMinute = morningToMinute;
			}
			// else {
			resultSet = SQLExecuteUtils.result(SQLStatementUtils.GET_MINUTE
					+ " WHERE " + DBNameCoreConstants.MINUTE + " >= ?",
					Integer.parseInt(morningFromMinute));
			// }
			// morningToMinuteList.add(minuteEnd);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Minute minute = new Minute();
						int minuteId = resultSet
								.getInt(DBNameCoreConstants.MINUTE);
						String minuteValue = "";
						if (minuteId < 10) {
							minuteValue = "0" + minuteId;
						} else {
							minuteValue = String.valueOf(minuteId);
						}
						minute.setMinuteId(minuteId);
						minute.setMinuteValue(minuteValue);
						if (minuteId < 60) {
							morningToMinuteList.add(minute);
						}
						if (Integer.parseInt(morningFromMinute) == 60) {
							morningToMinuteList.add(minuteEnd);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		} else {
			resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_MINUTE);
			morningToMinuteList.add(minuteEnd);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Minute minute = new Minute();
						int minuteId = resultSet
								.getInt(DBNameCoreConstants.MINUTE);
						String minuteValue = "";
						if (minuteId < 10) {
							minuteValue = "0" + minuteId;
						} else {
							minuteValue = String.valueOf(minuteId);
						}
						minute.setMinuteId(minuteId);
						minute.setMinuteValue(minuteValue);
						if (minuteId < 60) {
							morningToMinuteList.add(minute);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		}
		if (!Validator.nullOrBlank(afterFromMinute)) {
			if (Integer.parseInt(afterFromMinute) < Integer
					.parseInt(afterToMinute)) {
				// resultSet =
				// SQLExecuteUtils.result(SQLStatementUtils.GET_MINUTE
				// + " WHERE " + DBNameCoreConstants.MINUTE + " >= ?",
				// Integer.parseInt(afterToMinute));
				afToMinute = afterToMinute;
			}
			// else {
			resultSet = SQLExecuteUtils.result(SQLStatementUtils.GET_MINUTE
					+ " WHERE " + DBNameCoreConstants.MINUTE + " >= ?",
					Integer.parseInt(afterFromMinute));
			// }
			// afterToMinuteList.add(minuteEnd);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Minute minute = new Minute();
						int minuteId = resultSet
								.getInt(DBNameCoreConstants.MINUTE);
						String minuteValue = "";
						if (minuteId < 10) {
							minuteValue = "0" + minuteId;
						} else {
							minuteValue = String.valueOf(minuteId);
						}
						minute.setMinuteId(minuteId);
						minute.setMinuteValue(minuteValue);
						if (minuteId == 60) {
							afterToMinuteList.add(minute);
						}
						if (Integer.parseInt(afterFromMinute) == 60) {
							afterToMinuteList.add(minuteEnd);
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		} else {
			resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_MINUTE);
			afterToMinuteList.add(minuteEnd);
			if (resultSet != null) {
				try {
					while (resultSet.next()) {
						Minute minute = new Minute();
						int minuteId = resultSet
								.getInt(DBNameCoreConstants.MINUTE);
						String minuteValue = "";
						if (minuteId < 10) {
							minuteValue = "0" + minuteId;
						} else {
							minuteValue = String.valueOf(minuteId);
						}
						minute.setMinuteId(minuteId);
						minute.setMinuteValue(minuteValue);
						if (minuteId < 60) {
							afterToMinuteList.add(minute);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultSet = null;
			}
		}

		return SUCCESS;
	}

	public String loadCityDistrict() {
		if(cityStr == null) {
			cityStr = "";
		}
		if(Validator.nullOrBlank(city)) {
			this.city = cityStr;
		}
		cityList = new ArrayList<City>();
		districtList = new ArrayList<District>();
		String[] idLatLongName = city.split("~");
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.SELECT_CITY
				+ SQLStatementUtils.ORDER_BY + DBNameCoreConstants.CITY_NAME
				+ " collate utf8_czech_ci");
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					City city = new City();
					String cityName = resultSet
							.getString(DBNameCoreConstants.CITY_NAME);
					double lat = resultSet
							.getDouble(DBNameCoreConstants.LATITUDE);
					double lng = resultSet
							.getDouble(DBNameCoreConstants.LONGITUDE);
					int cityID = resultSet.getInt(DBNameCoreConstants.CITY_ID);
					String telAreaCode = resultSet
							.getString(DBNameCoreConstants.TEL_AREA_CODE);
					double northLat =  resultSet
							.getDouble(DBNameCoreConstants.NORTH_LAT);
					double northLong =  resultSet
							.getDouble(DBNameCoreConstants.NORTH_LONG);
					double sounthLat =  resultSet
							.getDouble(DBNameCoreConstants.SOUNTH_LAT);
					double sounthLong =  resultSet
							.getDouble(DBNameCoreConstants.SOUNTH_LONG);
					city.setCityId(cityID);
					city.setCityName(cityName);
					city.setLat(lat);
					city.setLon(lng);
					city.setIdLatLongName(cityID + "~" + lat + "~" + lng + "~"
							+ cityName + "~" + telAreaCode + "~" + northLat + "~" + northLong + "~" + sounthLat + "~" + sounthLong);
					cityList.add(city);
				}
				resultSet = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!Validator.nullOrBlank(city)) {
			if (!"-1".equals(idLatLongName[0])) {
				if(Validator.nullOrBlank(district)) {
					this.district = districtStr;
				}
				resultSet = SQLExecuteUtils.result(
						SQLStatementUtils.SELECT_DISTRICT
								+ SQLStatementUtils.ORDER_BY
								+ DBNameCoreConstants.DISTRICT_NAME
								+ " collate utf8_czech_ci",
						Integer.parseInt(idLatLongName[0]));
				if (resultSet != null) {
					try {
						while (resultSet.next()) {
							District district = new District();
							String districtName = resultSet
									.getString(DBNameCoreConstants.DISTRICT_NAME);
							double lat = resultSet
									.getDouble(DBNameCoreConstants.LATITUDE);
							double lng = resultSet
									.getDouble(DBNameCoreConstants.LONGITUDE);
							int districtId = resultSet
									.getInt(DBNameCoreConstants.DISTRICT_ID);
							double northLat =  resultSet
									.getDouble(DBNameCoreConstants.NORTH_LAT);
							double northLong =  resultSet
									.getDouble(DBNameCoreConstants.NORTH_LONG);
							double sounthLat =  resultSet
									.getDouble(DBNameCoreConstants.SOUNTH_LAT);
							double sounthLong =  resultSet
									.getDouble(DBNameCoreConstants.SOUNTH_LONG);
							district.setDistrictName(districtName);
							district.setLat(lat);
							district.setLon(lng);
							district.setIdLatlngName(districtId + "~" + lat
									+ "~" + lng + "~" + districtName + "~" + northLat + "~" + northLong + "~" + sounthLat + "~" + sounthLong);
							district.setDistrictId(districtId);
							districtList.add(district);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return SUCCESS;
	}

	public String loadTypeOfBusiness() {
		typeOfBusinessList = new ArrayList<>();
		resultSet = SQLExecuteUtils.resultSet(SQLStatementUtils.GET_TYPE_OF_BUSINES);
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					TypeOfBusiness typeOfBusiness = new TypeOfBusiness();
					String typeOfName = resultSet
							.getString(DBNameCoreConstants.TYPE_OF_BUSINESS);
					int typeOfId = resultSet.getInt(DBNameCoreConstants.ID);
					typeOfBusiness.setTypeBusinessId(typeOfId);
					typeOfBusiness.setTypeBusinessName(typeOfName);
					typeOfBusinessList.add(typeOfBusiness);
				}
				resultSet = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * @return the cityList
	 */
	public List<City> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList
	 *            the cityList to set
	 */
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	/**
	 * @return the districtList
	 */
	public List<District> getDistrictList() {
		return districtList;
	}

	/**
	 * @param districtList
	 *            the districtList to set
	 */
	public void setDistrictList(List<District> districtList) {
		this.districtList = districtList;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the morningFromHour
	 */
	public String getMorningFromHour() {
		return morningFromHour;
	}

	/**
	 * @param morningFromHour
	 *            the morningFromHour to set
	 */
	public void setMorningFromHour(String morningFromHour) {
		this.morningFromHour = morningFromHour;
	}

	/**
	 * @return the morningFromMinute
	 */
	public String getMorningFromMinute() {
		return morningFromMinute;
	}

	/**
	 * @param morningFromMinute
	 *            the morningFromMinute to set
	 */
	public void setMorningFromMinute(String morningFromMinute) {
		this.morningFromMinute = morningFromMinute;
	}

	/**
	 * @return the morningToHour
	 */
	public String getMorningToHour() {
		return morningToHour;
	}

	/**
	 * @param morningToHour
	 *            the morningToHour to set
	 */
	public void setMorningToHour(String morningToHour) {
		this.morningToHour = morningToHour;
	}

	/**
	 * @return the morningToMinute
	 */
	public String getMorningToMinute() {
		return morningToMinute;
	}

	/**
	 * @param morningToMinute
	 *            the morningToMinute to set
	 */
	public void setMorningToMinute(String morningToMinute) {
		this.morningToMinute = morningToMinute;
	}

	/**
	 * @return the afterFromHour
	 */
	public String getAfterFromHour() {
		return afterFromHour;
	}

	/**
	 * @param afterFromHour
	 *            the afterFromHour to set
	 */
	public void setAfterFromHour(String afterFromHour) {
		this.afterFromHour = afterFromHour;
	}

	/**
	 * @return the afterFromMinute
	 */
	public String getAfterFromMinute() {
		return afterFromMinute;
	}

	/**
	 * @param afterFromMinute
	 *            the afterFromMinute to set
	 */
	public void setAfterFromMinute(String afterFromMinute) {
		this.afterFromMinute = afterFromMinute;
	}

	/**
	 * @return the afterToHour
	 */
	public String getAfterToHour() {
		return afterToHour;
	}

	/**
	 * @param afterToHour
	 *            the afterToHour to set
	 */
	public void setAfterToHour(String afterToHour) {
		this.afterToHour = afterToHour;
	}

	/**
	 * @return the afterToMinute
	 */
	public String getAfterToMinute() {
		return afterToMinute;
	}

	/**
	 * @param afterToMinute
	 *            the afterToMinute to set
	 */
	public void setAfterToMinute(String afterToMinute) {
		this.afterToMinute = afterToMinute;
	}

	/**
	 * @return the morningFromHourList
	 */
	public List<Hour> getMorningFromHourList() {
		return morningFromHourList;
	}

	/**
	 * @param morningFromHourList
	 *            the morningFromHourList to set
	 */
	public void setMorningFromHourList(List<Hour> morningFromHourList) {
		this.morningFromHourList = morningFromHourList;
	}

	/**
	 * @return the morningFromMinuteList
	 */
	public List<Minute> getMorningFromMinuteList() {
		return morningFromMinuteList;
	}

	/**
	 * @param morningFromMinuteList
	 *            the morningFromMinuteList to set
	 */
	public void setMorningFromMinuteList(List<Minute> morningFromMinuteList) {
		this.morningFromMinuteList = morningFromMinuteList;
	}

	/**
	 * @return the morningToHourList
	 */
	public List<Hour> getMorningToHourList() {
		return morningToHourList;
	}

	/**
	 * @param morningToHourList
	 *            the morningToHourList to set
	 */
	public void setMorningToHourList(List<Hour> morningToHourList) {
		this.morningToHourList = morningToHourList;
	}

	/**
	 * @return the morningToMinuteList
	 */
	public List<Minute> getMorningToMinuteList() {
		return morningToMinuteList;
	}

	/**
	 * @param morningToMinuteList
	 *            the morningToMinuteList to set
	 */
	public void setMorningToMinuteList(List<Minute> morningToMinuteList) {
		this.morningToMinuteList = morningToMinuteList;
	}

	/**
	 * @return the afterFromHourList
	 */
	public List<Hour> getAfterFromHourList() {
		return afterFromHourList;
	}

	/**
	 * @param afterFromHourList
	 *            the afterFromHourList to set
	 */
	public void setAfterFromHourList(List<Hour> afterFromHourList) {
		this.afterFromHourList = afterFromHourList;
	}

	/**
	 * @return the afterFromMinuteList
	 */
	public List<Minute> getAfterFromMinuteList() {
		return afterFromMinuteList;
	}

	/**
	 * @param afterFromMinuteList
	 *            the afterFromMinuteList to set
	 */
	public void setAfterFromMinuteList(List<Minute> afterFromMinuteList) {
		this.afterFromMinuteList = afterFromMinuteList;
	}

	/**
	 * @return the afterToHourList
	 */
	public List<Hour> getAfterToHourList() {
		return afterToHourList;
	}

	/**
	 * @param afterToHourList
	 *            the afterToHourList to set
	 */
	public void setAfterToHourList(List<Hour> afterToHourList) {
		this.afterToHourList = afterToHourList;
	}

	/**
	 * @return the afterToMinuteList
	 */
	public List<Minute> getAfterToMinuteList() {
		return afterToMinuteList;
	}

	/**
	 * @param afterToMinuteList
	 *            the afterToMinuteList to set
	 */
	public void setAfterToMinuteList(List<Minute> afterToMinuteList) {
		this.afterToMinuteList = afterToMinuteList;
	}

	/**
	 * @return the morToHour
	 */
	public String getMorToHour() {
		return morToHour;
	}

	/**
	 * @param morToHour
	 *            the morToHour to set
	 */
	public void setMorToHour(String morToHour) {
		this.morToHour = morToHour;
	}

	/**
	 * @return the morToMinute
	 */
	public String getMorToMinute() {
		return morToMinute;
	}

	/**
	 * @param morToMinute
	 *            the morToMinute to set
	 */
	public void setMorToMinute(String morToMinute) {
		this.morToMinute = morToMinute;
	}

	/**
	 * @return the afToHour
	 */
	public String getAfToHour() {
		return afToHour;
	}

	/**
	 * @param afToHour
	 *            the afToHour to set
	 */
	public void setAfToHour(String afToHour) {
		this.afToHour = afToHour;
	}

	/**
	 * @return the afToMinute
	 */
	public String getAfToMinute() {
		return afToMinute;
	}

	/**
	 * @param afToMinute
	 *            the afToMinute to set
	 */
	public void setAfToMinute(String afToMinute) {
		this.afToMinute = afToMinute;
	}

	/**
	 * @return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}

	/**
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	/**
	 * @return the cityStr
	 */
	public String getCityStr() {
		return cityStr;
	}

	/**
	 * @param cityStr the cityStr to set
	 */
	public void setCityStr(String cityStr) {
		this.cityStr = cityStr;
	}

	/**
	 * @return the districtStr
	 */
	public String getDistrictStr() {
		return districtStr;
	}

	/**
	 * @param districtStr the districtStr to set
	 */
	public void setDistrictStr(String districtStr) {
		this.districtStr = districtStr;
	}

	/**
	 * @return the degreeList
	 */
	public List<TypeOfBusiness> getDegreeList() {
		return typeOfBusinessList;
	}

	/**
	 * @param degreeList the degreeList to set
	 */
	public void setDegreeList(List<TypeOfBusiness> degreeList) {
		this.typeOfBusinessList = degreeList;
	}
}
