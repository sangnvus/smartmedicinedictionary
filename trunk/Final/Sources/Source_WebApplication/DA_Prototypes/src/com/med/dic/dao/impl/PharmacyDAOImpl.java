
package com.med.dic.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.constant.SQLNameCoreConstants;
import com.med.dic.dao.PharmacyDAO;
import com.med.dic.model.Degree;
import com.med.dic.model.Pharmacy;
import com.med.dic.model.TypeOfBusiness;
import com.med.dic.pagination.Pagination;
import com.med.dic.validate.Validator;

public class PharmacyDAOImpl extends BaseDAO implements PharmacyDAO{

	private final static String SQL_SEARCH_NEAREST_PHARMACY1 = "SELECT "
															+ DBNameCoreConstants.PHARMACY_ID + ", "
															+ DBNameCoreConstants.PHARMACY_NAME + ", "
															+ DBNameCoreConstants.CITY_NAME + ", "
															+ DBNameCoreConstants.DISTRICT_NAME + ", "
															+ DBNameCoreConstants.STREET + ", "
															+ DBNameCoreConstants.HOUSE_NO + ", "
															+ DBNameCoreConstants.IMAGE_PATH + ", "
															+ DBNameCoreConstants.VIDEO_PATH + ", "
															+ DBNameCoreConstants.LATITUDE + "m, "
															+ DBNameCoreConstants.LONGITUDE + ", "
															+ " ( 6371 * acos( cos( radians(:lat) ) * cos( radians(" + DBNameCoreConstants.LATITUDE + " ) ) * cos( radians( "+ DBNameCoreConstants.LONGITUDE + " ) - radians(:lon) ) + sin( radians(:lat) ) * sin( radians(" + DBNameCoreConstants.LATITUDE + " ) ) ) ) AS distance"
															+ " FROM " + DBNameCoreConstants.PHARMACY
															+ " HAVING distance <= :radius ORDER BY distance";
	private final static String SQL_SEARCH_PHAR_JOIN_REP = " SELECT "
															+ SQLNameCoreConstants.PHARMACY_PHARMACY_ID + ", "
															+ SQLNameCoreConstants.PHARMACY_PHARMACY_NAME + ", "
															+ SQLNameCoreConstants.PHARMACY_CITY_ID + ", "
															+ SQLNameCoreConstants.PHARMACY_DISTRICT_NAME + ", "
															+ SQLNameCoreConstants.PHARMACY_DISTRICT_ID + ", "
															+ SQLNameCoreConstants.PHARMACY_DISTRICT_NAME + ", "
															+ SQLNameCoreConstants.PHARMACY_EMAIL + ", "
															+ SQLNameCoreConstants.PHARMACY_STREET + ", "
															+ SQLNameCoreConstants.PHARMACY_HOUSE_NO + ", "
															+ SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + ", "
															+ " CONCAT (" + SQLNameCoreConstants.PHARMACY_HOUSE_NO + ", " + SQLNameCoreConstants.PHARMACY_STREET + " ) AS ADDRESS"
															+ " FROM " + DBNameCoreConstants.PHARMACY
															+ " INNER JOIN " + DBNameCoreConstants.REPRESENTATIVE
															+ " ON " + SQLNameCoreConstants.PHARMACY_REPRESENTATIVE_ID
															+ " = " + SQLNameCoreConstants.REPRESENTATIVE_REPRESENTATIVE_ID
															+ " WHERE " + SQLNameCoreConstants.PHARMACY_PHARMACY_NAME + " like :pharmacyName"
															+ " AND " + SQLNameCoreConstants.REPRESENTATIVE_DEGREE + " = :degree"
															+ " AND " + SQLNameCoreConstants.PHARMACY_DELETE_FLAG + " = :deleteFlag";
	private final static String SQL_SEARCH_BY_NAME = " SELECT "
													+ DBNameCoreConstants.PHARMACY_ID + ", "
													+ DBNameCoreConstants.PHARMACY_NAME + ", "
													+ DBNameCoreConstants.CITY_NAME + ", "
													+ DBNameCoreConstants.DISTRICT_NAME + ", "
													+ DBNameCoreConstants.STREET + ", "
													+ DBNameCoreConstants.HOUSE_NO + ", "
													+ " CONCAT(" + DBNameCoreConstants.HOUSE_NO + ", ',' , " + DBNameCoreConstants.STREET + ") AS ADDRESS" + ", "
													+ DBNameCoreConstants.ACCEPTED_FLAG  + ", "
													+ DBNameCoreConstants.REG_USER
													+ " FROM " + DBNameCoreConstants.PHARMACY
													+ " WHERE " + DBNameCoreConstants.PHARMACY_NAME + " like :pharmacyName"
													+ " AND " + DBNameCoreConstants.DELETE_FLAG + " = :deleteFlag";
	// SEARCH BY NAME WITH RELATED MEDICINE
	private final static String SEARCH_BY_NAME = " SELECT "
												+ SQLNameCoreConstants.PHARMACY_PHARMACY_ID + ", "
												+ SQLNameCoreConstants.PHARMACY_PHARMACY_NAME + ", "
												+ SQLNameCoreConstants.PHARMACY_CITY_NAME + ", "
												+ SQLNameCoreConstants.PHARMACY_DISTRICT_NAME + ", "
												+ SQLNameCoreConstants.PHARMACY_STREET + ", "
												+ SQLNameCoreConstants.PHARMACY_HOUSE_NO + ", "
												+ " CONCAT(" + SQLNameCoreConstants.PHARMACY_HOUSE_NO + ", ',' , " + SQLNameCoreConstants.PHARMACY_STREET + ") AS ADDRESS"
												+ " FROM " + DBNameCoreConstants.PHARMACY
												+ " INNER JOIN " + DBNameCoreConstants.REPRESENTATIVE
												+ " ON " + SQLNameCoreConstants.PHARMACY_REPRESENTATIVE_ID
												+ " = " + SQLNameCoreConstants.REPRESENTATIVE_REPRESENTATIVE_ID;
												
	private final static String INNER_JOIN_PHAR_MED = " INNER JOIN " + DBNameCoreConstants.PHARMACY_MEDICINE
			+ " ON " + SQLNameCoreConstants.PHARMACY_MEDICINE_PHARMACY_ID
			+ " = " + SQLNameCoreConstants.PHARMACY_PHARMACY_ID;
	private final static String WHERE_SEARCH_BY_NAME = " WHERE " + SQLNameCoreConstants.PHARMACY_DELETE_FLAG + " = 'N' "
			+ " AND " + SQLNameCoreConstants.REPRESENTATIVE_DELETE_FLAG + " = 'N' "
			+ " AND " + SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + " = 'Y' "
			+ " AND " + SQLNameCoreConstants.PHARMACY_PHARMACY_NAME + " LIKE :pharmacyName";
												
	private final static String SQL_SEARCH_NEAREST_PHARMACY = "SELECT "
															+ DBNameCoreConstants.PHARMACY_ID + ", "
															+ DBNameCoreConstants.PHARMACY_NAME + ", "
															+ DBNameCoreConstants.CITY_NAME + ", "
															+ DBNameCoreConstants.DISTRICT_NAME + ", "
															+ DBNameCoreConstants.STREET + ", "
															+ DBNameCoreConstants.HOUSE_NO + ", "
															+ DBNameCoreConstants.IMAGE_PATH + ", "
															+ DBNameCoreConstants.VIDEO_PATH + ", "
															+ DBNameCoreConstants.LATITUDE + ", "
															+ DBNameCoreConstants.LONGITUDE + ", "
															+ " ( 6371 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(:lat - ABS(" + DBNameCoreConstants.LATITUDE + "))), 2) + COS(RADIANS(:lat)) * COS(RADIANS(ABS(" + DBNameCoreConstants.LATITUDE + "))) * POWER(SIN(RADIANS(:lon - " + DBNameCoreConstants.LONGITUDE + ")), 2)))) AS distance"
															+ " FROM " + DBNameCoreConstants.PHARMACY
															+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
															+ " AND " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
															+ " HAVING distance <= :radius " + " ORDER BY distance LIMIT 100";
	private final static String SQL_NEAREST_PHARMACY = "SELECT "
			+ DBNameCoreConstants.PHARMACY_ID + ", "
			+ DBNameCoreConstants.PHARMACY_NAME + ", "
			+ DBNameCoreConstants.CITY_NAME + ", "
			+ DBNameCoreConstants.DISTRICT_NAME + ", "
			+ DBNameCoreConstants.STREET + ", "
			+ DBNameCoreConstants.HOUSE_NO + ", "
			+ DBNameCoreConstants.IMAGE_PATH + ", "
			+ DBNameCoreConstants.VIDEO_PATH + ", "
			+ DBNameCoreConstants.LATITUDE + ", "
			+ DBNameCoreConstants.LONGITUDE + ", "
			+ "(((acos(sin((:lat*pi()/180)) * sin((" + DBNameCoreConstants.LATITUDE + "*pi()/180))+cos((:lat*pi()/180))* cos((" + DBNameCoreConstants.LATITUDE + "*pi()/180)) * cos(((:lon- " + DBNameCoreConstants.LONGITUDE + ")*pi()/180))))*180/pi())*60*1.1515*1.609344) as distance"
			+ " FROM " + DBNameCoreConstants.PHARMACY
			+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
			+ " AND " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
			+ " HAVING distance <= :radius " + " ORDER BY distance LIMIT 100";
	private final static String SQL_SEARCH_LIKE_ADDRESS = " SELECT CONCAT ("
														 + DBNameCoreConstants.HOUSE_NO + ","
														 + DBNameCoreConstants.STREET + ","
														 + DBNameCoreConstants.DISTRICT_NAME + ","
														 + DBNameCoreConstants.CITY_NAME + ") AS "
														 + DBNameCoreConstants.ADDRESS
														 + " FROM " + DBNameCoreConstants.PHARMACY
														 + " WHERE " + DBNameCoreConstants.ADDRESS + " = :address";

	private final static String SQL_SEARCH_PHARMACY_BY_NAME_AND_ADDRESS =
			"SELECT "
				+ DBNameCoreConstants.PHARMACY_ID + ", "
				+ DBNameCoreConstants.PHARMACY_NAME + ", "
				+ DBNameCoreConstants.HOME_PHONE + ", "
				+ DBNameCoreConstants.BUSINESS_LICENSE_NO + ","
				+ DBNameCoreConstants.PHARMACEUTICAL_COMPANY + ","
				+ DBNameCoreConstants.GPP_NO + ","
				+ DBNameCoreConstants.NOTES + ","
				+ DBNameCoreConstants.IMAGE_PATH + ","
				+ DBNameCoreConstants.LATITUDE + ","
				+ DBNameCoreConstants.LONGITUDE + ","
				+ " CONCAT ("
				+ DBNameCoreConstants.HOUSE_NO + "," + "', '" + ","
				+ DBNameCoreConstants.STREET + "," + "', '" + ","
				+ DBNameCoreConstants.DISTRICT_NAME + "," + "', '" + ","
				+ DBNameCoreConstants.CITY_NAME + ") AS "
				+ DBNameCoreConstants.ADDRESS
				+ " FROM " + DBNameCoreConstants.PHARMACY
				+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N'"
				+ " AND " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y'"
				+ " AND " + DBNameCoreConstants.PHARMACY_NAME + " = :pharmacyName"
				+ " HAVING " + DBNameCoreConstants.ADDRESS + " = :address";

	private final static String SQL_SEARCH_PHARMACY_BY_MED_ID =
			"SELECT "
				+ SQLNameCoreConstants.PHARMACY_PHARMACY_ID + ","
				+ SQLNameCoreConstants.PHARMACY_PHARMACY_NAME + ","
				+ SQLNameCoreConstants.PHARMACY_HOME_PHONE + ","
				+ SQLNameCoreConstants.PHARMACY_BUSINESS_LICENSE_NO + ","
				+ SQLNameCoreConstants.PHARMACY_PHARMACEUTICAL_COMPANY + ","
				+ SQLNameCoreConstants.PHARMACY_GPP_NO + ","
				+ SQLNameCoreConstants.PHARMACY_NOTES + ","
				+ SQLNameCoreConstants.PHARMACY_IMAGE_PATH + ","
				+ SQLNameCoreConstants.PHARMACY_LATITUDE + ","
				+ SQLNameCoreConstants.PHARMACY_LONGITUDE + ","
				+ " CONCAT ("
				+ SQLNameCoreConstants.PHARMACY_HOUSE_NO + "," + "', '" + ","
				+ SQLNameCoreConstants.PHARMACY_STREET + "," + "', '" + ","
				+ SQLNameCoreConstants.PHARMACY_DISTRICT_NAME + "," + "', '" + ","
				+ SQLNameCoreConstants.PHARMACY_CITY_NAME + ") AS "
				+ DBNameCoreConstants.ADDRESS
				+ " FROM " + DBNameCoreConstants.PHARMACY
				+ " INNER JOIN " + DBNameCoreConstants.PHARMACY_MEDICINE
				+ " ON " + SQLNameCoreConstants.PHARMACY_PHARMACY_ID + " = " + SQLNameCoreConstants.PHARMACY_MEDICINE_PHARMACY_ID
				+ " INNER JOIN " + DBNameCoreConstants.MEDICINES
				+ " ON " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_ID + " = " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
				+ " WHERE " + SQLNameCoreConstants.PHARMACY_DELETE_FLAG + " = 'N'"
				+ " AND " + SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + " = 'Y'"
				+ " AND " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID + " = :medId" + " LIMIT " + ":limitRow";

				;

	@Override
	public List<Pharmacy> listAll(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addPharmacy(Pharmacy pharmacy) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		Integer pharId = (Integer) session.save(pharmacy);
		//session.save(pharmacy);
		tx.commit();
		return pharId;
	}

	@Override
	public Pharmacy getPharmacy(int pharmacyId) {
//		session = sessionFactory.openSession();
//		return (Pharmacy)session.get(Pharmacy.class, pharmacyId);
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Pharmacy.class).add(Restrictions.eq("pharmacyId", pharmacyId));
		List<Pharmacy> pharmacyList = criteria.list();
		Pharmacy pharmacy = new Pharmacy();
		if (pharmacyList.size() > 0 ) {
			pharmacy = pharmacyList.get(0);
			pharmacy.setTypeOfBusinessName(getTypeOfBusinessName(pharmacy.getTypeOfBusiness()));
		}
		return pharmacy;
	}

	@Override
	public List<Pharmacy> listLocations(Pharmacy pharmacy, int radius) {
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(SQL_NEAREST_PHARMACY);
		query.setParameter("lat", pharmacy.getLat());
		query.setParameter("lon", pharmacy.getLon());
		query.setParameter("radius", radius);
		List result = query.list();
		List<Pharmacy> pharmacyList = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			phar.setCityName((String)element[2]);
			phar.setDistrictName((String)element[3]);
			phar.setStreet((String)element[4]);
			phar.setHouseNo((String)element[5]);
			phar.setImgPath((String)element[6]);
			phar.setVideoPath((String)element[7]);
			phar.setLat((BigDecimal)element[8]);
			phar.setLon((BigDecimal)element[9]);
			pharmacyList.add(phar);
		}
		return pharmacyList;
	}

	@Override
	public Pharmacy getPharmacyLatLong(String address) {
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(SQL_SEARCH_LIKE_ADDRESS);
		query.setParameter("address", address);


		return null;
	}

	@Override
	public List<Pharmacy> repsPharmacy(int repId) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Pharmacy.class);
		criteria.add(Restrictions.eq("repId", repId));
		criteria.add(Restrictions.eq("accepted", "Y"));
		criteria.add(Restrictions.eq("deteleFlag", "N"));
		List<Pharmacy> pharList = criteria.list();
		for (Pharmacy pharmacy : pharList) {
			pharmacy.setTypeOfBusinessName(getTypeOfBusinessName(pharmacy.getTypeOfBusiness()));
		}
		return pharList;
	}

	@Override
	public int count(Pharmacy pharmacy) {
		int count = 0;
		List<Pharmacy> pharList = new ArrayList<>();
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Pharmacy.class);
		criteria.add(Restrictions.like("pharmacyName", "%" + pharmacy.getPharmacyName() + "%"));
		criteria.add(Restrictions.like("regUser", "%" + pharmacy.getRegUser() + "%"));
		if (pharmacy.getCityId() != -1) {
			criteria.add(Restrictions.eq("cityId", pharmacy.getCityId()));
		}
		if (pharmacy.getDistrictId() != -1) {
			criteria.add(Restrictions.eq("districtId", pharmacy.getDistrictId()));
		}
		if (pharmacy.getTypeOfBusiness() != 0) {
			criteria.add(Restrictions.eq("typeOfBusiness", pharmacy.getTypeOfBusiness()));
		}
		if(!Validator.nullOrBlank(pharmacy.getAccepted())) {
			criteria.add(Restrictions.eq("accepted", pharmacy.getAccepted()));
		}
		criteria.add(Restrictions.eq("deteleFlag", pharmacy.getDeteleFlag()));
		pharList = criteria.list();
		if(pharList.size() > 0) {
			count = pharList.size();
		}
		return count;
	}

	@Override
	public int countForAdmin(Pharmacy pharmacy) {
		int count = 0;
		List<Pharmacy> pharList = new ArrayList<>();
		session = sessionFactory.openSession();
		String queryStr = SQL_SEARCH_PHAR_JOIN_REP;
		if (!Validator.nullOrBlank(pharmacy.getRegUser())) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_EMAIL + " like :regUser";
		}
		if (pharmacy.getCityId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_CITY_ID + " = :cityId";
		}
		if (pharmacy.getDistrictId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_DISTRICT_ID + " = :districtId";
		}
		if (pharmacy.getTypeOfBusiness() != 0) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_TYPE_OF_BUSINESS + "  = :typeOfBusiness";
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + " = :accepted";
		}
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("pharmacyName", "%" + pharmacy.getPharmacyName() + "%");
		query.setParameter("degree", pharmacy.getDegree());
		query.setParameter("deleteFlag", pharmacy.getDeteleFlag());
		if (pharmacy.getTypeOfBusiness() != 0) {
			query.setParameter("typeOfBusiness", pharmacy.getTypeOfBusiness());
		}
		if (pharmacy.getCityId() != -1) {
			query.setParameter("cityId", pharmacy.getCityId());
		}
		if (pharmacy.getDistrictId() != -1) {
			query.setParameter("districtId", pharmacy.getDistrictId());
		}
		if (!Validator.nullOrBlank(pharmacy.getRegUser())) {
			query.setParameter("regUser", "%" + pharmacy.getRegUser() + "%");
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			query.setParameter("accepted", pharmacy.getAccepted());
		}
		pharList = query.list();
		count = pharList.size();
		return count;
	}

	@Override
	public int countAdvancedSearch(Pharmacy pharmacy) {
		session = sessionFactory.openSession();
		String queryStr = SEARCH_BY_NAME;
		if(!Validator.nullOrBlank(pharmacy.getMedName())) {
			queryStr += INNER_JOIN_PHAR_MED;
			queryStr += WHERE_SEARCH_BY_NAME;
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_NAME + " LIKE :medName" +  " AND " + SQLNameCoreConstants.PHARMACY_MEDICINE_DELETE_FLAG + " = 'N' ";
		} else {
			queryStr += WHERE_SEARCH_BY_NAME;
		}
		if (pharmacy.getCityId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_CITY_ID + " = :cityId ";
		}
		if (pharmacy.getDistrictId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_DISTRICT_ID + " = :districtId ";
		}
		if (pharmacy.getTypeOfBusiness() != 0) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_TYPE_OF_BUSINESS + " = :typeOfBusiness ";
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + " = :accepted ";
		}
		if (pharmacy.getDegree() > 0) {
			queryStr += " AND " + SQLNameCoreConstants.REPRESENTATIVE_DEGREE + " = :degree ";
		}
		queryStr += " GROUP BY " + SQLNameCoreConstants.PHARMACY_PHARMACY_ID
				+ " ORDER BY " + SQLNameCoreConstants.PHARMACY_SEARCHED_TIME;
		Query query = session.createSQLQuery(queryStr);
		if (pharmacy.getTypeOfBusiness() != 0) {
			query.setParameter("typeOfBusiness", pharmacy.getTypeOfBusiness());
		}
		if (pharmacy.getCityId() != -1) {
			query.setParameter("cityId", pharmacy.getCityId());
		}
		if (pharmacy.getDistrictId() != -1) {
			query.setParameter("districtId", pharmacy.getDistrictId());
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			query.setParameter("accepted", pharmacy.getAccepted());
		}
		if (pharmacy.getDegree() > 0) {
			query.setParameter("degree", pharmacy.getDegree());
		}
		query.setParameter("pharmacyName", "%" + pharmacy.getPharmacyName().trim() + "%");
		if(!Validator.nullOrBlank(pharmacy.getMedName())) {
			query.setParameter("medName", "%" + pharmacy.getMedName().trim() + "%");
		}
		List result = query.list();

		return result.size();
	}

	@Override
	public List<Pharmacy> listAdvancedSearch(Pharmacy pharmacy, Pagination pagination) {
		List<Pharmacy> pharList = new ArrayList<>();
		String queryStr = SEARCH_BY_NAME;
		if(!Validator.nullOrBlank(pharmacy.getMedName())) {
			queryStr += INNER_JOIN_PHAR_MED;
			queryStr += WHERE_SEARCH_BY_NAME;
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_NAME + " LIKE :medName" +  " AND " + SQLNameCoreConstants.PHARMACY_MEDICINE_DELETE_FLAG + " = 'N' ";
		} else {
			queryStr += WHERE_SEARCH_BY_NAME;
		}
		if (pharmacy.getCityId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_CITY_ID + " = :cityId ";
		}
		if (pharmacy.getDistrictId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_DISTRICT_ID + " = :districtId ";
		}
		if (pharmacy.getTypeOfBusiness() != 0) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_TYPE_OF_BUSINESS + " = :typeOfBusiness ";
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + " = :accepted ";
		}
		if (pharmacy.getDegree() > 0) {
			queryStr += " AND " + SQLNameCoreConstants.REPRESENTATIVE_DEGREE + " = :degree ";
		}
		queryStr += " GROUP BY " + SQLNameCoreConstants.PHARMACY_PHARMACY_ID
				+ " ORDER BY " + SQLNameCoreConstants.PHARMACY_SEARCHED_TIME + " DESC";
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		if (pharmacy.getTypeOfBusiness() != 0) {
			query.setParameter("typeOfBusiness", pharmacy.getTypeOfBusiness());
		}
		if (pharmacy.getCityId() != -1) {
			query.setParameter("cityId", pharmacy.getCityId());
		}
		if (pharmacy.getDistrictId() != -1) {
			query.setParameter("districtId", pharmacy.getDistrictId());
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			query.setParameter("accepted", pharmacy.getAccepted());
		}
		if (pharmacy.getDegree() > 0) {
			query.setParameter("degree", pharmacy.getDegree());
		}
		query.setParameter("pharmacyName", "%" + pharmacy.getPharmacyName().trim() + "%");
		if(!Validator.nullOrBlank(pharmacy.getMedName())) {
			query.setParameter("medName", "%" + pharmacy.getMedName().trim() + "%");
		}
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			phar.setCityName((String)element[2]);
			phar.setDistrictName((String)element[3]);
			phar.setStreet((String)element[4]);
			phar.setHouseNo((String)element[5]);
			phar.setAddress((String)element[6]);
			pharList.add(phar);
		}
		return pharList;
	}

	@Override
	public List<Pharmacy> listPharmacy(Pharmacy pharmacy, Pagination pagination, boolean isAdmin) {
		List<Pharmacy> pharList = new ArrayList<>();
		session = sessionFactory.openSession();
		String queryStr = SQL_SEARCH_BY_NAME;
		if (!Validator.nullOrBlank(pharmacy.getRegUser())) {
			queryStr += " AND " + DBNameCoreConstants.REG_USER + " LIKE :regUser ";
		}
		if (pharmacy.getCityId() != -1) {
			queryStr += " AND " + DBNameCoreConstants.CITY_ID + " = :cityId ";
		}
		if (pharmacy.getDistrictId() != -1) {
			queryStr += " AND " + DBNameCoreConstants.DISTRICT_ID + " = :districtId ";
		}
		if (pharmacy.getTypeOfBusiness() != 0) {
			queryStr += " AND " + DBNameCoreConstants.TYPE_OF_BUSINESS + " = :typeOfBusiness ";
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			queryStr += " AND " + DBNameCoreConstants.ACCEPTED_FLAG + " = :accepted ";
		}
		if(isAdmin) {
			queryStr += " ORDER BY " + DBNameCoreConstants.REG_DATE + " DESC ";
		} else {
			queryStr += " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";
		}
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		if (pharmacy.getTypeOfBusiness() != 0) {
			query.setParameter("typeOfBusiness", pharmacy.getTypeOfBusiness());
		}
		if (pharmacy.getCityId() != -1) {
			query.setParameter("cityId", pharmacy.getCityId());
		}
		if (pharmacy.getDistrictId() != -1) {
			query.setParameter("districtId", pharmacy.getDistrictId());
		}
		if (!Validator.nullOrBlank(pharmacy.getRegUser())) {
			query.setParameter("regUser", pharmacy.getRegUser() + "%");
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			query.setParameter("accepted", pharmacy.getAccepted());
		}
		query.setParameter("pharmacyName", "%" + pharmacy.getPharmacyName() + "%");
		query.setParameter("deleteFlag", pharmacy.getDeteleFlag());
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			phar.setCityName((String)element[2]);
			phar.setDistrictName((String)element[3]);
			phar.setStreet((String)element[4]);
			phar.setHouseNo((String)element[5]);
			phar.setAddress((String)element[6]);
			String accept = (String)element[7];
			phar.setRegUser((String)element[8]);
			if ("N".equals(accept)) {
				phar.setAcceptFlag(false);
			} else {
				phar.setAcceptFlag(true);
			}
			pharList.add(phar);
		}
		return pharList;
	}

	@Override
	public List<Pharmacy> listPharmacyAdmin(Pharmacy pharmacy, Pagination pagination) {
		List<Pharmacy> pharList = new ArrayList<>();
		session = sessionFactory.openSession();
		String queryStr = SQL_SEARCH_PHAR_JOIN_REP;
		if (!Validator.nullOrBlank(pharmacy.getRegUser())) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_EMAIL + " like :regUser";
		}
		if (pharmacy.getCityId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_CITY_ID + " = :cityId";
		}
		if (pharmacy.getDistrictId() != -1) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_DISTRICT_ID + " = :districtId";
		}
		if (pharmacy.getTypeOfBusiness() != 0) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_TYPE_OF_BUSINESS + "  = :typeOfBusiness";
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			queryStr += " AND " + SQLNameCoreConstants.PHARMACY_ACCEPTED_FLAG + " = :accepted";
		}
		queryStr += " ORDER BY " + SQLNameCoreConstants.PHARMACY_REG_DATE + " DESC ";
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		if (pharmacy.getTypeOfBusiness() != 0) {
			query.setParameter("typeOfBusiness", pharmacy.getTypeOfBusiness());
		}
		if (pharmacy.getCityId() != -1) {
			query.setParameter("cityId", pharmacy.getCityId());
		}
		if (pharmacy.getDistrictId() != -1) {
			query.setParameter("districtId", pharmacy.getDistrictId());
		}
		if (!Validator.nullOrBlank(pharmacy.getRegUser())) {
			query.setParameter("regUser", "%" + pharmacy.getRegUser() + "%");
		}
		if (!Validator.nullOrBlank(pharmacy.getAccepted())) {
			query.setParameter("accepted", pharmacy.getAccepted());
		}
		query.setParameter("pharmacyName", "%" + pharmacy.getPharmacyName() + "%");
		query.setParameter("degree", pharmacy.getDegree());
		query.setParameter("deleteFlag", pharmacy.getDeteleFlag());
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			phar.setCityId((int)element[2]);
			phar.setCityName((String)element[3]);
			phar.setDistrictId((int)element[4]);
			phar.setDistrictName((String)element[5]);
			phar.setRegUser((String)element[6]);
			phar.setStreet((String)element[7]);
			phar.setHouseNo((String)element[8]);
			String accept = (String)element[9];
			phar.setAddress((String)element[10]);
			if ("N".equals(accept)) {
				phar.setAcceptFlag(false);
			} else {
				phar.setAcceptFlag(true);
			}
			pharList.add(phar);
		}
		return pharList;
	}

	@Override
	public void updatePharmacy(Pharmacy pharmacy) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(pharmacy);
		tx.commit();
	}


	public String getTypeOfBusinessName(int typeOfBusinessId) {
		TypeOfBusiness typeOfBus = new TypeOfBusiness();
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TypeOfBusiness.class);
		criteria.add(Restrictions.eq("typeBusinessId", typeOfBusinessId));
		typeOfBus = (TypeOfBusiness)criteria.list().get(0);
		return typeOfBus.getTypeBusinessName();
	}

	/*
	 * Implement list pharmacy location method
	 * @see com.med.dic.dao.PharmacyDAO#listPharmacyLocation(double, double)
	 */
	@Override
	public ArrayList<Pharmacy> listPharmacyLocation(double lat, double lon) {
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(SQL_NEAREST_PHARMACY);
		int radius = 5;
		query.setParameter("lat", lat);
		query.setParameter("lon", lon);
		query.setParameter("radius", radius);
		List result = query.list();
		String address = "";
		ArrayList<Pharmacy> pharmacyList = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			String cityName = (String)element[2];
			String districtName = (String)element[3];
			String street = (String)element[4];
			String houseNo = (String)element[5];
			phar.setImgPath((String)element[6]);
			phar.setVideoPath((String)element[7]);
			phar.setLat((BigDecimal)element[8]);
			phar.setLon((BigDecimal)element[9]);
			address = houseNo + ", " + street + ", " + districtName + ", " + cityName;
			phar.setAddress(address);
			pharmacyList.add(phar);
		}
		return pharmacyList;
	}

	/*
	 * Implement get pharmacy by name and address method
	 * @see com.med.dic.dao.PharmacyDAO#getPharmacyByNameAndAddr(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<Pharmacy> getPharmacyByNameAndAddr(String pharmacyName, String address) {
		session = sessionFactory.openSession();
		Query query = session.createSQLQuery(SQL_SEARCH_PHARMACY_BY_NAME_AND_ADDRESS);
		query.setParameter("pharmacyName", pharmacyName);
		query.setParameter("address", address);
		List result = query.list();
		ArrayList<Pharmacy> pharmacyList = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			phar.setHomePhone((String)element[2]);
			phar.setBusinessLicenseNo((String)element[3]);
			phar.setPharCompany((String)element[4]);
			phar.setGppNo((String)element[5]);
			phar.setNotes((String)element[6]);
			phar.setImgPath((String)element[7]);
			phar.setLat((BigDecimal)element[8]);
			phar.setLon((BigDecimal)element[9]);
			phar.setAddress((String)element[10]);
			pharmacyList.add(phar);
		}
		return pharmacyList;
	}

	/*
	 * Implement get pharmacy by medicine Id method
	 * @see com.med.dic.dao.PharmacyDAO#getPharmacyByMedId(int)
	 */
	@Override
	public ArrayList<Pharmacy> getPharmacyByMedId(int medId) {
		session = sessionFactory.openSession();
		int limitRow = 3;
		Query query = session.createSQLQuery(SQL_SEARCH_PHARMACY_BY_MED_ID);
		query.setParameter("medId", medId);
		query.setParameter("limitRow", limitRow);
		List result = query.list();
		ArrayList<Pharmacy> pharmacyList = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Pharmacy phar = new Pharmacy();
			phar.setPharmacyId((int)element[0]);
			phar.setPharmacyName((String)element[1]);
			phar.setHomePhone((String)element[2]);
			phar.setBusinessLicenseNo((String)element[3]);
			phar.setPharCompany((String)element[4]);
			phar.setGppNo((String)element[5]);
			phar.setNotes((String)element[6]);
			phar.setImgPath((String)element[7]);
			phar.setLat((BigDecimal)element[8]);
			phar.setLon((BigDecimal)element[9]);
			phar.setAddress((String)element[10]);
			pharmacyList.add(phar);
		}
		return pharmacyList;
	}

}
