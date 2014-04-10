package com.med.dic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.constant.SQLNameCoreConstants;
import com.med.dic.dao.PharmacyMedicineDAO;
import com.med.dic.model.Medicine;
import com.med.dic.model.PharmacyMedicine;
import com.med.dic.pagination.Pagination;

public class PharmacyMedicineDAOImpl extends BaseDAO implements PharmacyMedicineDAO{

	private final static String SQL_SEARCH_PHAR_MED_BY_PHAR_ID = " SELECT " + DBNameCoreConstants.MEDICINE_ID + ", "
																+ DBNameCoreConstants.PHARMACY_ID
																+ " FROM " + DBNameCoreConstants.PHARMACY_MEDICINE
																+ " WHERE " + DBNameCoreConstants.PHARMACY_ID + " = :pharmacyId";
	private final static String SQL_SEARCH_PHAR_MED = " SELECT " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_ID + ", "
													+ SQLNameCoreConstants.PHARMACY_MEDICINE_PHARMACY_ID + ", "
													+ SQLNameCoreConstants.MEDICINES_INDICATIONS + ", "
													+ SQLNameCoreConstants.MEDICINES_INGREDIENTS + ", "
													+ SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + ", "
													+ SQLNameCoreConstants.MEDICINES_TYPE_OF_PACKAGE_NAME + ", "
													+ SQLNameCoreConstants.MEDICINES_MEDICINE_ID
													+ " FROM " + DBNameCoreConstants.PHARMACY_MEDICINE + " INNER JOIN "
													+ DBNameCoreConstants.MEDICINES
													+ " ON " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_ID
													+ " = " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
													+ " INNER JOIN " + DBNameCoreConstants.TYPE_OF_MEDICINE
													+ " ON " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
													+ " = " + SQLNameCoreConstants.TYPE_OF_MEDICINE_MEDICINE_ID
													+ " WHERE "
													+ SQLNameCoreConstants.MEDICINE_DELETE_FLAG + " = 'N'" + " AND "
													+ SQLNameCoreConstants.PHARMACY_MEDICINE_PHARMACY_ID + " = :pharmacyId"
													+ " AND " + DBNameCoreConstants.PHARMACY_MEDICINE + "." + DBNameCoreConstants.DELETE_FLAG + " = 'N' ";
													
	
	@Override
	public List<PharmacyMedicine> listAll() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PharmacyMedicine.class);
		List<PharmacyMedicine> medList = new ArrayList<>();
		try {
			medList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medList;
	}

	@Override
	public PharmacyMedicine getById(int medId, int pharId) {
		session = sessionFactory.openSession();
		List<PharmacyMedicine> pharMedList = new ArrayList<>();
		Criteria criteria = session.createCriteria(PharmacyMedicine.class);
		criteria.add(Restrictions.eq("medId", medId));
		criteria.add(Restrictions.eq("pharmacyId", pharId));
		pharMedList = criteria.list();
		PharmacyMedicine pharMed = null; 
				//new PharmacyMedicine();
		if(pharMedList.size() > 0) {
			pharMed = pharMedList.get(0);
		}
		return pharMed;
	}

	@Override
	public List<PharmacyMedicine> getByMedId(int medId) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PharmacyMedicine.class).add(
				Restrictions.eq("medId", medId));
		return criteria.list();
	}

	@Override
	public List<PharmacyMedicine> getByPharId(int pharId) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(PharmacyMedicine.class).add(
				Restrictions.eq("pharmacyId", pharId));
		return criteria.list();
	}
	
	@Override
	public void addPharMed(PharmacyMedicine pharMed) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.save(pharMed);
		tx.commit();
	}

	@Override
	public List<PharmacyMedicine> pharMedList(int pharId, Pagination pagination, int typeOfPackageId) {
		List<PharmacyMedicine> pharMedList = new ArrayList<>();
		session = sessionFactory.openSession();
		Session session = sessionFactory.openSession();
		String queryStr = SQL_SEARCH_PHAR_MED;
		if(typeOfPackageId != -1 && typeOfPackageId != 0) {
			queryStr += " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :typeOfPackageId";
		}
		queryStr += " GROUP BY " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_ID;
		queryStr = pagination.getSQLQuery(queryStr, false);
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("pharmacyId", pharId);
		if(typeOfPackageId != -1 && typeOfPackageId != 0) {
			query.setParameter("typeOfPackageId", typeOfPackageId);
		}
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			PharmacyMedicine pharMed = new PharmacyMedicine();
			pharMed.setMedId((int)element[0]);
			pharMed.setPharmacyId(pharId);
			pharMedList.add(pharMed);
		}
		return pharMedList;
	}

	@Override
	public List<PharmacyMedicine> listPharById(int pharId, int typeOfPackageId, String deleteFlag) {
		List<PharmacyMedicine> pharMedList = new ArrayList<>();
		session = sessionFactory.openSession();
		if(typeOfPackageId == -1 || typeOfPackageId == 0) {
			Criteria criteria = session.createCriteria(PharmacyMedicine.class);
			criteria.add(
					Restrictions.eq("pharmacyId", pharId));
			
			criteria.add(Restrictions.eq("deteleFlag", deleteFlag));
			pharMedList =  criteria.list();
		} else {
			Session session = sessionFactory.openSession();
			String queryStr = SQL_SEARCH_PHAR_MED + " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :typeOfPackageId";
			queryStr += " GROUP BY " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_ID;
			Query query = session.createSQLQuery(queryStr);
			query.setParameter("pharmacyId", pharId);
			query.setParameter("typeOfPackageId", typeOfPackageId);
			List result = query.list();
			for (Object obj : result) {
				Object[] element = (Object[])obj;
				PharmacyMedicine pharMed = new PharmacyMedicine();
				pharMed.setMedId((int)element[0]);
				pharMed.setPharmacyId(pharId);
				pharMedList.add(pharMed);
			}
		}
		return pharMedList;
	}

	@Override
	public void deletePharMed(PharmacyMedicine pharMed) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(pharMed);
		tx.commit();
		
	}

	@Override
	public void updatePharMed(PharmacyMedicine pharMed) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(pharMed);
		tx.commit();
	}

}
