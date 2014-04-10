package com.med.dic.database;

import com.med.dic.constant.DBNameCoreConstants;

public class SQLStatementUtils {

	public static final String GET_MED_NAME = "SELECT DISTINCT " 
											+ DBNameCoreConstants.MEDICINE_NAME
											+  " FROM " + DBNameCoreConstants.MEDICINES 
											+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
											+ " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
											+ " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) "
											+ " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";
	public static final String GET_MED_NAME_FOR_PHAR = " SELECT DISTINCT " + DBNameCoreConstants.MEDICINE_NAME + ", " + DBNameCoreConstants.MEDICINE_ID + ", "
													+ DBNameCoreConstants.MANUFACTURER + ", "
													+ DBNameCoreConstants.TYPE_OF_PACKAGE_NAME + ", "
													+ DBNameCoreConstants.INGREDIENTS + ", "
													+ DBNameCoreConstants.INDICATIONS + ", "
													+ DBNameCoreConstants.CONTRAINDICATIONS
													+ " FROM " + DBNameCoreConstants.MEDICINES
													+ " WHERE " + DBNameCoreConstants.MEDICINE_ID + " NOT IN ( "
													+ " SELECT " + DBNameCoreConstants.MEDICINE_ID
													+ " FROM PHARMACY_MEDICINE "
													+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
													+ " AND " + DBNameCoreConstants.PHARMACY_ID + " = ? ) "
													+ " AND " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
													+ " AND ( ACCEPTED_FLAG = 'Y'  OR ACCEPTED_FLAG IS NULL ) "
													+ " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";
	public static final String SELECT_CITY = " SELECT " 
											+ DBNameCoreConstants.CITY_ID + ", "
											+ DBNameCoreConstants.CITY_NAME + ", "
											+ DBNameCoreConstants.LATITUDE + ", "
											+ DBNameCoreConstants.LONGITUDE + ", "
											+ DBNameCoreConstants.TEL_AREA_CODE + ", "
											+ DBNameCoreConstants.NORTH_LAT + ", "
											+ DBNameCoreConstants.NORTH_LONG + ", "
											+ DBNameCoreConstants.SOUNTH_LAT + ", "
											+ DBNameCoreConstants.SOUNTH_LONG
											+ " FROM " + DBNameCoreConstants.CITY;
	public static final String SELECT_DISTRICT = " SELECT " 
												+ DBNameCoreConstants.DISTRICT_ID + ", "
												+ DBNameCoreConstants.DISTRICT_NAME + ", "
												+ DBNameCoreConstants.LATITUDE + ", "
												+ DBNameCoreConstants.LONGITUDE + ", "
												+ DBNameCoreConstants.NORTH_LAT + ", "
												+ DBNameCoreConstants.NORTH_LONG + ", "
												+ DBNameCoreConstants.SOUNTH_LAT + ", "
												+ DBNameCoreConstants.SOUNTH_LONG
												+ " FROM "
												+ DBNameCoreConstants.DISTRICT
												+ " WHERE " + DBNameCoreConstants.CITY_ID + " = ? ";
	public static final String GET_SYMPTOMS = " SELECT DISTINCT "
												+ DBNameCoreConstants.SYMPTOM_NAME
												+ " FROM " + DBNameCoreConstants.SYMPTOMS;
	public static final String GET_CONTRAINDICATIONS = " SELECT DISTINCT "
												+ DBNameCoreConstants.CONTRAINDICATION_NAME
												+ " FROM " + DBNameCoreConstants.CONTRAINDICATIONS;
	public static final String ORDER_BY = " ORDER BY ";
	
	public static final String GET_EMAIL = " SELECT " + DBNameCoreConstants.EMAIL
											+ " FROM " + DBNameCoreConstants.SMD_USER;
	
	public static final String GET_PHARMACY_ADDRESS = " SELECT " + DBNameCoreConstants.CITY_NAME + ", "
													+ DBNameCoreConstants.DISTRICT_NAME + ", "
													+ DBNameCoreConstants.STREET + ", "
													+ DBNameCoreConstants.HOUSE_NO
													+ " FROM " + DBNameCoreConstants.PHARMACY;
	public static final String GET_PHARMACY_NAME = " SELECT DISTINCT " + DBNameCoreConstants.PHARMACY_NAME
												+ " FROM " + DBNameCoreConstants.PHARMACY
												+ " WHERE " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
												+ " AND " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
												+ " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";
	public static final String GET_HOUR = " SELECT " + DBNameCoreConstants.HOUR
										+ 	" FROM " + DBNameCoreConstants.HOURS;
	
	public static final String GET_MINUTE = " SELECT " + DBNameCoreConstants.MINUTE
											+ " FROM " + DBNameCoreConstants.MINUTES;
	
	public static final String GET_TYPE_OF_BUSINESS = " SELECT " + DBNameCoreConstants.TYPE_OF_BUSINESS + ", "
													+ DBNameCoreConstants.ID
													+" FROM " + DBNameCoreConstants.TYPE_OF_BUSINESS;
	public static final String GET_DEGREE = " SELECT " + DBNameCoreConstants.DEGREE_ID + ", "
											+ DBNameCoreConstants.DEGREE_NAME
											+ " FROM " + DBNameCoreConstants.DEGREES;
	
	public static final String GET_TYPE_OF_BUSINES = " SELECT " + DBNameCoreConstants.TYPE_OF_BUSINESS + ", "
													+ DBNameCoreConstants.ID
													+ " FROM " + DBNameCoreConstants.TYPE_OF_BUSINESS
													+ " ORDER BY " + DBNameCoreConstants.TYPE_OF_BUSINESS + " collate utf8_czech_ci";
	public static final String GET_NEWS = " SELECT " + DBNameCoreConstants.ID+ ", "
										+ DBNameCoreConstants.TITLE + " , "
										+ DBNameCoreConstants.CONTENT + ", "
										+ DBNameCoreConstants.IMG_PATH
										+ " FROM " + DBNameCoreConstants.NEWS
										+ " ORDER BY " + DBNameCoreConstants.TITLE;
													
}
