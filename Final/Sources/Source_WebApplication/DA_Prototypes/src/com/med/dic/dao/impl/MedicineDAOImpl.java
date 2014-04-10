
package com.med.dic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.constant.SQLNameCoreConstants;
import com.med.dic.model.Medicine;
import com.med.dic.model.TypeOfMedicine;
import com.med.dic.pagination.Pagination;
import com.med.dic.utility.MedicineBreakCharacter;
import com.med.dic.validate.Validator;

public class MedicineDAOImpl implements com.med.dic.dao.MedicineDAO {

	public static final String medName = "medName";
	
	private static final String SQL_SEARCH_MED_ADVANCE = " SELECT " 
														+ SQLNameCoreConstants.MEDICINES_MEDICINE_ID + ", "
														+ SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + ", "
														+ SQLNameCoreConstants.MEDICINES_MANUFACTURER + ", "
														+ SQLNameCoreConstants.MEDICINES_INDICATIONS + ", "
														+ SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + ", "
														+ SQLNameCoreConstants.MEDICINES_INGREDIENTS + ", "
														+ SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + ", "
														+ SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_NAME
														+ " FROM "
														+ DBNameCoreConstants.MEDICINES + " INNER JOIN "
														+ DBNameCoreConstants.TYPE_OF_MEDICINE
														+ " ON " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
														+ " = " + SQLNameCoreConstants.TYPE_OF_MEDICINE_MEDICINE_ID
														+ " WHERE " + SQLNameCoreConstants.MEDICINE_DELETE_FLAG+ " = :deleteFlag"
														+ " AND " + SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + " like :medName "
														+ " AND " + SQLNameCoreConstants.MEDICINES_MANUFACTURER + " like :manufacturer "
														+" AND " + SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + " like :contraindications "
														+ " AND " + SQLNameCoreConstants.MEDICINES_INDICATIONS + " like :indications "
														+ " AND " + SQLNameCoreConstants.MEDICINES_INGREDIENTS + " like :ingredients "
														+ " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :packageId"
														+ " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
														+ " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) "
														+ " ORDER BY " + SQLNameCoreConstants.MEDICINE_SEARCH_TIME + " DESC ";
	private static final String USER_ADVANCED_SEARCH = " SELECT " 
			+ SQLNameCoreConstants.MEDICINES_MEDICINE_ID + ", "
			+ SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + ", "
			+ SQLNameCoreConstants.MEDICINES_MANUFACTURER + ", "
			+ SQLNameCoreConstants.MEDICINES_INDICATIONS + ", "
			+ SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + ", "
			+ SQLNameCoreConstants.MEDICINES_INGREDIENTS + ", "
			+ SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + ", "
			+ SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_NAME
			+ " FROM "
			+ DBNameCoreConstants.MEDICINES + " INNER JOIN "
			+ DBNameCoreConstants.TYPE_OF_MEDICINE
			+ " ON " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
			+ " = " + SQLNameCoreConstants.TYPE_OF_MEDICINE_MEDICINE_ID
			+ " WHERE " + SQLNameCoreConstants.MEDICINE_DELETE_FLAG+ " = 'N'";
	private final static String USER_NORMAL_ADVANCED_SEARCH = "SELECT"
			+ " MEDICINE_ID, MEDICINE_NAME, MANUFACTURER, INDICATIONS , CONTRAINDICATIONS, INGREDIENTS"
			+ " FROM MEDICINES "
			+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N'";
	
	private static final String SQL_SEARCH_BY_NAME = "SELECT "
													+ DBNameCoreConstants.MEDICINE_ID + ", "
													+ DBNameCoreConstants.MEDICINE_NAME + ", "
													+ DBNameCoreConstants.MANUFACTURER + ", "
													+ DBNameCoreConstants.TYPE_OF_PACKAGE_NAME + ", "
													+ DBNameCoreConstants.INGREDIENTS + ", "
													+ DBNameCoreConstants.INDICATIONS + ", "
													+ DBNameCoreConstants.CONTRAINDICATIONS + ", "
													+ DBNameCoreConstants.WARNING + ", "
													+ DBNameCoreConstants.IMAGE_PATH + ", "
													+ DBNameCoreConstants.DOSING_AND_USE + ", "
													+ DBNameCoreConstants.STORAGE + ", "
													+ DBNameCoreConstants.INTERACTION_MEDICINE
													+" FROM " + DBNameCoreConstants.MEDICINES
													+ " WHERE " + DBNameCoreConstants.MEDICINE_NAME + " like :medName"
													+ " AND " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
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
													+ " AND " + DBNameCoreConstants.PHARMACY_ID + " = :pharmacyId ) "
													+ " AND " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
													+ " AND " + DBNameCoreConstants.MEDICINE_NAME + " like :medName"
													+ " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
													+ " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) "
													+ " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";
	public static final String ACCEPT_MED_BY_ADMIN = " SELECT " 
													+ SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + ", "
													+ SQLNameCoreConstants.MEDICINES_MEDICINE_ID + ", "
													+ SQLNameCoreConstants.MEDICINES_MANUFACTURER + ", "
													+ SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_NAME + ", "
													+ SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + ", "
													+ SQLNameCoreConstants.MEDICINES_INGREDIENTS + ", "
													+ SQLNameCoreConstants.MEDICINES_INDICATIONS + ", "
													+ SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + ", "
													+ SQLNameCoreConstants.MEDICINE_ACCEPTED_FLAG
													+ " FROM "
													+ DBNameCoreConstants.MEDICINES + " INNER JOIN "
													+ DBNameCoreConstants.TYPE_OF_MEDICINE
													+ " ON " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
													+ " = " + SQLNameCoreConstants.TYPE_OF_MEDICINE_MEDICINE_ID
													+ " WHERE " + SQLNameCoreConstants.MEDICINE_DELETE_FLAG + " = 'N' "
													+ " AND " + SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + " like :medName "
													+ " AND " + SQLNameCoreConstants.MEDICINES_MANUFACTURER + " like :manufacturer "
													+" AND " + SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + " like :contraindications "
													+ " AND " + SQLNameCoreConstants.MEDICINES_INDICATIONS + " like :indications "
													+ " AND " + SQLNameCoreConstants.MEDICINES_INGREDIENTS + " like :ingredients "
													+ " AND " + DBNameCoreConstants.ACCEPTED_FLAG + " = :accept"
													+ " AND " + SQLNameCoreConstants.MEDICINE_PHARMACY_ID + " > 0 ";
		public static final String SQL_ACCEPT_MED =	" SELECT " + DBNameCoreConstants.MEDICINE_NAME + ", " + DBNameCoreConstants.MEDICINE_ID + ", "
												+ DBNameCoreConstants.MANUFACTURER + ", "
												+ DBNameCoreConstants.TYPE_OF_PACKAGE_NAME + ", "
												+ DBNameCoreConstants.TYPE_OF_PACKAGE_ID + ", "
												+ DBNameCoreConstants.INGREDIENTS + ", "
												+ DBNameCoreConstants.INDICATIONS + ", "
												+ DBNameCoreConstants.CONTRAINDICATIONS + ", "
												+ DBNameCoreConstants.ACCEPTED_FLAG
												+ " FROM " + DBNameCoreConstants.MEDICINES
												+ " WHERE " + DBNameCoreConstants.DELETE_FLAG + " = 'N' "
												+ " AND " + DBNameCoreConstants.MEDICINE_NAME + " like :medName "
												+ " AND " + DBNameCoreConstants.MANUFACTURER + " like :manufacturer "
												+ " AND " + DBNameCoreConstants.INDICATIONS + " like :indications "
												+ " AND " + DBNameCoreConstants.CONTRAINDICATIONS + " like :contraindications "
												+ " AND " + DBNameCoreConstants.INGREDIENTS + " like :ingredients "
												+ " AND " + DBNameCoreConstants.ACCEPTED_FLAG + " = :accept"
												+ " AND " + DBNameCoreConstants.PHARMACY_ID + " > 0 ";
												
	
	private final static String SQL_USER_ADVANCED_SEARCH = "SELECT"
			+ " MEDICINE_ID, MEDICINE_NAME, MANUFACTURER, INDICATIONS , INGREDIENTS"
			+ " FROM MEDICINES "
			+ " WHERE MEDICINE_NAME like  :name AND MANUFACTURER like :manu AND CONTRAINDICATIONS like :cont AND INGREDIENTS like :ingr AND TYPE_OF_PACKAGE_ID like :type AND DELETE_FLAG = :deleteFlag"
			+ " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME +  " DESC ";
	private final static String SQL_ADMIN_ADVANCED_SEARCH = "SELECT"
			+ " MEDICINE_ID, MEDICINE_NAME, MANUFACTURER, INDICATIONS, CONTRAINDICATIONS , INGREDIENTS"
			+ " FROM MEDICINES "
			+ " WHERE DELETE_FLAG like :deleteFlag AND MEDICINE_NAME like  :medName " +
			"AND MANUFACTURER like :manufacturer AND CONTRAINDICATIONS like :contraindications AND INGREDIENTS like :ingredients " +
			" AND " + DBNameCoreConstants.INDICATIONS + " like :indications"
			+ " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' "
			+ " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) "
			+ " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";

	private final static String SQL_SEARCH_MEDICINES_ON_PHARMACY =
			"SELECT "
			+ SQLNameCoreConstants.MEDICINES_MEDICINE_ID + ", "
			+ SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + ", "
			+ SQLNameCoreConstants.MEDICINES_MANUFACTURER + ", "
			+ SQLNameCoreConstants.MEDICINES_TYPE_OF_PACKAGE_NAME + ", "
			+ SQLNameCoreConstants.MEDICINES_INGREDIENTS + ", "
			+ SQLNameCoreConstants.MEDICINES_INDICATIONS + ", "
			+ SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + ", "
			+ SQLNameCoreConstants.MEDICINES_WARNING + ", "
			+ SQLNameCoreConstants.MEDICINES_IMAGE_PATH + ", "
			+ SQLNameCoreConstants.MEDICINES_DOSING_AND_USE + ", "
			+ SQLNameCoreConstants.MEDICINES_STORAGE + ", "
			+ SQLNameCoreConstants.MEDICINES_INTERACTION
			+ " FROM " + DBNameCoreConstants.MEDICINES
			+ " INNER JOIN " + DBNameCoreConstants.PHARMACY_MEDICINE
			+ " ON " + SQLNameCoreConstants.PHARMACY_MEDICINE_MEDICINE_ID + " = " + SQLNameCoreConstants.MEDICINES_MEDICINE_ID
			+ " INNER JOIN " + DBNameCoreConstants.PHARMACY
			+ " ON " + SQLNameCoreConstants.PHARMACY_PHARMACY_ID + " = " + SQLNameCoreConstants.PHARMACY_MEDICINE_PHARMACY_ID
			+ " WHERE " + SQLNameCoreConstants.MEDICINE_DELETE_FLAG + " = 'N'"
			+ " AND " + SQLNameCoreConstants.MEDICINE_ACCEPTED_FLAG + " = 'Y'"
			+ " AND " + SQLNameCoreConstants.PHARMACY_PHARMACY_ID + " = :pharmacyId" + " LIMIT " + " :limitRow"
			;

	public SessionFactory sessionFactory;

	// private static final Session
	@Override
	public List<Medicine> listMedicine(Medicine medicine, Pagination pagination) {
		List<Medicine> medList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String queryStr = SQL_SEARCH_BY_NAME;
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.getMedName().trim() + "%");
		query.setCacheable(true);
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Medicine med = new Medicine();
			med.setMedId((int)element[0]);
			med.setMedName((String)element[1]);
			med.setManufacturer((String)element[2]);
			med.setTypeOfPackageName((String) element[3]);
			med.setIngredients((String)element[4]);
			med.setIndications((String)element[5]);
			med.setContraindications((String)element[6]);
			med.setWarning((String)element[7]);
			med.setImgPath((String) element[8]);
			medList.add(med);
		}
		return medList;
	}

	@Override
	public List<Medicine> medListForPhar(String medName, Pagination pagination, int pharmacyId) {
		List<Medicine> medList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String queryStr = GET_MED_NAME_FOR_PHAR;
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("pharmacyId", pharmacyId);
		query.setParameter("medName", "%" + medName.trim() + "%");
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Medicine med = new Medicine();
			med.setMedName((String)element[0]);
			med.setMedId((int)element[1]);
			med.setManufacturer((String)element[2]);
			med.setTypeOfPackageName((String) element[3]);
			med.setIngredients((String)element[4]);
			med.setIndications((String)element[5]);
			med.setContraindications((String)element[6]);
			medList.add(med);
		}
		return medList;
	}

	@Override
	public List<String> autoComplete() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Medicine.class);
		List<Medicine> medList = new ArrayList<>();
		try {
			medList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<String> strList = new ArrayList<>();
		for (Medicine medicine : medList) {
			strList.add(medicine.getMedName());
		}
		return strList;
	}

	@Override
	public List<Medicine> medList() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Medicine.class);
		List<Medicine> medList = new ArrayList<>();
		try {
			medList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medList;
	}

	@Override
	public Medicine searchById(int id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Medicine.class).add(
				Restrictions.eq("medId", id));
		List<Medicine> medicineList = new ArrayList<>();
		medicineList = criteria.list();
		Medicine medicine = new Medicine();
		if(medicineList.size() > 0) {
			medicine = medicineList.get(0);
			medicine = MedicineBreakCharacter.breakChar(medicine);
		}
		return medicine;

	}

	@Override
	public int count(String medName) {
		int count = 0;
		List<Medicine> medList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Medicine.class);
		Criterion acceptedNull = Restrictions.eq("accept", "Y");
		Criterion accepted = Restrictions.isNull("accept");
		LogicalExpression orExp = Restrictions.or(acceptedNull, accepted);
		criteria.add(Restrictions.like("medName", "%" + medName.trim() + "%"));
		criteria.add(Restrictions.eq("deteleFlag", "N"));
		criteria.add(orExp);
		medList = criteria.list();
		if (medList.size() > 0) {
			count = medList.size();
			/*for (Medicine medicine : medList) {
				int searchedCount = medicine.getSearchedTime();
				searchedCount++;
				medicine.setSearchedTime(searchedCount);
				updateMedicine(medicine);
			}*/
		}
		return count;
	}

	@Override
	public int countForPhar(String medName, int pharmacyId) {
		int count = 0;
		List<Medicine> medList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String queryStr = GET_MED_NAME_FOR_PHAR;
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("pharmacyId", pharmacyId);
		query.setParameter("medName", "%" + medName.trim() + "%");
		List result = query.list();
		if(result.size() > 0) {
			count = result.size();
		}
		return count;
	}

	@Override
	public int coutForAcceptMed(Medicine medicine) {
		int count = 0;
		List<Medicine> medList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String queryStr = "";
		if(medicine.getTypeOfPackageId() > 0) {
			queryStr = ACCEPT_MED_BY_ADMIN;
			queryStr += " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :packageId";
		} else {
			queryStr = SQL_ACCEPT_MED;
		}
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.getMedName().trim() + "%");
		query.setParameter("manufacturer", medicine.getManufacturer().trim() + "%");
		query.setParameter("indications", "%" + medicine.getIndications().trim() + "%");
		query.setParameter("contraindications", "%" + medicine.getContraindications().trim() + "%");
		query.setParameter("ingredients", "%" + medicine.getIngredients().trim() + "%");
		query.setParameter("accept", medicine.getAccept());
		if(medicine.getTypeOfPackageId() > 0) {
			query.setParameter("packageId", medicine.getTypeOfPackageId());
		}
		List result = query.list();
		if(result.size() > 0) {
			count = result.size();
		}
		return count;
	}

	@Override
	public List<Medicine> searchForAcceptMed(Medicine medicine, Pagination pagination) {
		List<Medicine> medList = new ArrayList<>();
		Session session = sessionFactory.openSession();
		String queryStr = "";
		if(medicine.getTypeOfPackageId() > 0) {
			queryStr = ACCEPT_MED_BY_ADMIN;
			queryStr += " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :packageId";
		} else {
			queryStr = SQL_ACCEPT_MED;
		}
		queryStr += " ORDER BY " + SQLNameCoreConstants.MEDICINES_REG_DATE + " DESC ";
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.getMedName().trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.getManufacturer().trim() + "%");
		query.setParameter("indications", "%" + medicine.getIndications().trim() + "%");
		query.setParameter("contraindications", "%" + medicine.getContraindications().trim() + "%");
		query.setParameter("ingredients", "%" + medicine.getIngredients().trim() + "%");
		query.setParameter("accept", medicine.getAccept());
		if(medicine.getTypeOfPackageId() > 0) {
			query.setParameter("packageId", medicine.getTypeOfPackageId());
		}
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Medicine med = new Medicine();
			med.setMedName((String)element[0]);
			med.setMedId((int)element[1]);
			med.setManufacturer((String)element[2]);
			med.setTypeOfPackageName((String) element[3]);
			med.setTypeOfPackageId((int)element[4]);
			med.setIngredients((String)element[5]);
			med.setIndications((String)element[6]);
			med.setContraindications((String)element[7]);
			String accept = (String)element[8];
			if ("N".equals(accept)) {
				med.setAcceptFlag(false);
			} else {
				med.setAcceptFlag(true);
			}
			medList.add(med);
		}
		return medList;
	}

	@Override
	public List<Medicine> user_searchMedAdvanced(Medicine medicine,
			Pagination pagination) {
		Session session = sessionFactory.openSession();
		String queryStr = SQL_USER_ADVANCED_SEARCH;
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("name", "%" + medicine.medName.trim() + "%");
		query.setParameter("manu", "%" + medicine.manufacturer.trim() + "%");
		query.setParameter("cont", "%" + medicine.contraindications.trim() + "%");
		query.setParameter("ingr", "%" + medicine.ingredients.trim() + "%");
		query.setParameter("deleteFlag", medicine.deteleFlag);
		if (medicine.typeOfPackageId > 0)
			query.setParameter("type", medicine.typeOfPackageId);
		else
			query.setParameter("type", "%");
		List result = query.list();
		List<Medicine> listMed = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[]) obj;
			Medicine med = new Medicine();
			med.setMedId((int) element[0]);
			med.setMedName((String) element[1]);
			med.setIngredients((String) element[4]);
			med.setManufacturer((String) element[2]);
			med.setIndications((String) element[3]);
			listMed.add(med);
		}
		return listMed;
	}

	@Override
	public List<Medicine> count_admin_searchMedAdvanced(Medicine medicine) {
		Session session = sessionFactory.openSession();
		List<Medicine> medList = new ArrayList<>();
		String queryStr = "";
		if(medicine.getTypeOfPackageId() > 0) {
			queryStr = SQL_SEARCH_MED_ADVANCE;
		} else {
			queryStr = SQL_ADMIN_ADVANCED_SEARCH;
		}
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("deleteFlag", medicine.getDeteleFlag());
		query.setParameter("medName", "%" + medicine.medName.trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.manufacturer.trim() + "%");
		query.setParameter("contraindications", "%" + medicine.contraindications.trim() + "%");
		query.setParameter("indications", "%" + medicine.indications.trim() + "%");
		query.setParameter("ingredients", "%" + medicine.ingredients.trim() + "%");
		if(medicine.getTypeOfPackageId() > 0) {
			query.setParameter("packageId", medicine.typeOfPackageId);
		}
		medList = query.list();
		return medList;
	}

	@Override
	public List<Medicine> count_user_searchMedAdvanced(Medicine medicine) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Medicine.class);
		criteria.add(Restrictions.like("medName", "%" + medicine.medName.trim() + "%"));
		criteria.add(Restrictions.like("manufacturer", "%" + medicine.manufacturer.trim()
				+ "%"));
		criteria.add(Restrictions.like("contraindications", "%" +
				medicine.contraindications.trim() + "%"));
		criteria.add(Restrictions.like("ingredients", "%" + medicine.ingredients.trim()
				+ "%"));
		criteria.add(Restrictions.eq("deteleFlag", medicine.deteleFlag));
		if (medicine.typeOfPackageId > 0)
			criteria.add(Restrictions.like("typeOfPackageId",
					medicine.typeOfPackageId));
		List<Medicine> medicineList = criteria.list();
		/*for (Medicine medicine2 : medicineList) {
			int searchedCount = medicine2.getSearchedTime();
			searchedCount++;
			medicine2.setSearchedTime(searchedCount);
			updateMedicine(medicine2);
		}*/
		return medicineList;
	}
	
	@Override
	public List<Medicine> admin_searchMedAdvanced(Medicine medicine,
			Pagination pagination) {
		Session session = sessionFactory.openSession();
		String queryStr = "";
		if(medicine.getTypeOfPackageId() > 0) {
			queryStr = SQL_SEARCH_MED_ADVANCE;
		} else {
			queryStr = SQL_ADMIN_ADVANCED_SEARCH;
		}
		queryStr = pagination.getSQLQuery(queryStr, true);
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("deleteFlag", medicine.deteleFlag);
		query.setParameter("medName", "%" + medicine.medName.trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.manufacturer.trim() + "%");
		query.setParameter("contraindications", "%" + medicine.contraindications.trim() + "%");
		query.setParameter("ingredients", "%" + medicine.ingredients.trim() + "%");
		query.setParameter("indications", "%" + medicine.indications.trim() + "%");
		if (medicine.typeOfPackageId > 0)
			query.setParameter("packageId", medicine.typeOfPackageId);
		List result = query.list();
		List<Medicine> listMed = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[]) obj;
			Medicine med = new Medicine();
			med.setMedId((int) element[0]);
			med.setMedName((String) element[1]);
			med.setManufacturer((String) element[2]);
			med.setIndications((String) element[3]);
			med.setContraindications((String) element[4]);
			med.setIngredients((String) element[5]);
			listMed.add(med);
		}
		return listMed;
	}
	
	
	@Override
	public Medicine searchByID(int medId) {
		Session session = sessionFactory.openSession();
		return (Medicine) session.get(Medicine.class, medId);
	}
	
	@Override
	public int addNewMed(Medicine medicine) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Integer medId = (Integer) session.save(medicine);
		tx.commit();
		return medId;

	}
	
	@Override
	public void updateMedicine(Medicine medicine) {
		Transaction tx = null;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.update(medicine);
		tx.commit();
	}
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Medicine getMedByIdAndTypeOfPackage(int medId, int typeOfPackage) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Medicine.class);
		criteria.add(Restrictions.eq("medId", medId));
		criteria.add(Restrictions.eq("typeOfPackageId", typeOfPackage));
		List<Medicine> medList = new ArrayList<Medicine>();
		medList = criteria.list();
		Medicine med = null;
		if(medList.size() > 0) {
			med = medList.get(0);
		}
		return med;
	}
	
	public Medicine breakChar(Medicine medicine) {
		String indications = medicine.getIndications().replace("\r", "");
		String contraindications = medicine.getContraindications().replace("\r", "");
		String dosingAndUse = medicine.getDosingAndUse().replace("\r", "");
		String ingredients = medicine.getIngredients().replace("\r", "");
		String warning = medicine.getWarning().replace("\r", "");
		String storage = medicine.getStorage().replace("\r", "");
		String interaction = medicine.getInteraction().replace("\r", "");
		String similarMedicine = medicine.getSimilarMedicine().replace("\r", "");
		String[] indicationSplit = indications.split("\n");
		String[] contraindicationSplit = contraindications.split("\n");
		String[] dosingAndUseSplit = dosingAndUse.split("\n");
		String[] ingredientSplit = ingredients.split("\n");
		String[] warningSplit = warning.split("\n");
		String[] storageSplit = storage.split("\n");
		String[] interactionSplit = interaction.split("\n");
		String[] similarMedicineSplit = similarMedicine.split("\n");
		for (String string : indicationSplit) {
			medicine.indicationList.add(string);
		}
		for (String string : contraindicationSplit) {
			medicine.contraindicationList.add(string);
		}
		for (String string : dosingAndUseSplit) {
			medicine.dosingAndUseList.add(string);
		}
		for (String string : ingredientSplit) {
			medicine.ingredientsList.add(string);
		}
		for (String string : warningSplit) {
			medicine.warningList.add(string);
		}
		for (String string : storageSplit) {
			medicine.storageList.add(string);
		}
		for (String string : interactionSplit) {
			medicine.interactionList.add(string);
		}
		for (String string : similarMedicineSplit) {
			medicine.similarMedicineList.add(string);
		}
		return medicine;
	}

	@Override
	public int userSearchAdvancedWithType(Medicine medicine) {
		Session session = sessionFactory.openSession();
		List<Medicine> medList = new ArrayList<>();
		String queryStr = "";
		queryStr = USER_ADVANCED_SEARCH;
		queryStr += " AND " + SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + " like  :medName  ";
		queryStr += " AND  " + SQLNameCoreConstants.MEDICINES_MANUFACTURER + " like :manufacturer   ";
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			queryStr += " AND MATCH(" + SQLNameCoreConstants.MEDICINES_INDICATIONS+ ")" + " AGAINST(:indications IN BOOLEAN MODE) ";
		}
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			queryStr += " AND MATCH(" + SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + ") AGAINST ( :contraindications IN BOOLEAN MODE)";
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			queryStr +=  " AND MATCH (" + SQLNameCoreConstants.MEDICINES_INGREDIENTS + " ) AGAINST ( :ingredients IN BOOLEAN MODE) ";
		}
		queryStr += " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :packageId";
		queryStr += " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' " + " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) ";
		queryStr += " ORDER BY " + SQLNameCoreConstants.MEDICINE_SEARCH_TIME + " DESC ";
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.medName.trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.manufacturer.trim() + "%");
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			query.setParameter("contraindications", medicine.contraindications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			query.setParameter("indications", medicine.indications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			query.setParameter("ingredients", medicine.ingredients.trim());
		}
		query.setParameter("packageId", medicine.typeOfPackageId);
		medList = query.list();
		return medList.size();
	}

	@Override
	public List<Medicine> userAdvancedSearchWithtype(Medicine medicine,
			Pagination pagination) {
		Session session = sessionFactory.openSession();
		boolean hasFulltext = false;
		String queryStr = "";
		queryStr = USER_ADVANCED_SEARCH;
		queryStr += " AND " + SQLNameCoreConstants.MEDICINES_MEDICINE_NAME + " like  :medName  ";
		queryStr += " AND  " + SQLNameCoreConstants.MEDICINES_MANUFACTURER + " like :manufacturer   ";
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			hasFulltext = true;
			queryStr += " AND MATCH(" + SQLNameCoreConstants.MEDICINES_INDICATIONS+ ")" + " AGAINST(:indications IN BOOLEAN MODE) ";
		}
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			hasFulltext = true;
			queryStr += " AND MATCH(" + SQLNameCoreConstants.MEDICINES_CONTRAINDICATIONS + ") AGAINST ( :contraindications IN BOOLEAN MODE)";
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			hasFulltext = true;
			queryStr +=  " AND MATCH (" + SQLNameCoreConstants.MEDICINES_INGREDIENTS + " ) AGAINST ( :ingredients IN BOOLEAN MODE) ";
		}
		queryStr += " AND " + SQLNameCoreConstants.TYPE_OF_MEDICINE_PACKAGE_ID + " = :packageId";
		queryStr += " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' " + " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) ";
		if(!hasFulltext) {
			queryStr += " ORDER BY " + SQLNameCoreConstants.MEDICINE_SEARCH_TIME + " DESC ";
		}
		if(!hasFulltext) {
			queryStr = pagination.getSQLQuery(queryStr, true);
		} else {
			queryStr = pagination.getSQLQuery(queryStr, false);
		}
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.medName.trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.manufacturer.trim() + "%");
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			query.setParameter("contraindications", medicine.contraindications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			query.setParameter("indications", medicine.indications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			query.setParameter("ingredients", medicine.ingredients.trim());
		}
		query.setParameter("packageId", medicine.typeOfPackageId);
		List result = query.list();
		List<Medicine> listMed = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[]) obj;
			Medicine med = new Medicine();
			med.setMedId((int) element[0]);
			med.setMedName((String) element[1]);
			med.setManufacturer((String) element[2]);
			med.setIndications((String) element[3]);
			med.setContraindications((String) element[4]);
			med.setIngredients((String) element[5]);
			listMed.add(med);
		}
		return listMed;
	}

	@Override
	public int userSearchAdvanced(Medicine medicine) {
		Session session = sessionFactory.openSession();
		List<Medicine> medList = new ArrayList<>();
		String queryStr = "";
		queryStr = USER_NORMAL_ADVANCED_SEARCH;
		queryStr += " AND " + DBNameCoreConstants.MEDICINE_NAME + " like  :medName  ";
		queryStr += " AND  " + DBNameCoreConstants.MANUFACTURER + " like :manufacturer   ";
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			queryStr += " AND MATCH(" + DBNameCoreConstants.INDICATIONS+ ")" + " AGAINST(:indications IN BOOLEAN MODE) ";
		}
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			queryStr += " AND MATCH(" + DBNameCoreConstants.CONTRAINDICATIONS + ") AGAINST ( :contraindications IN BOOLEAN MODE)";
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			queryStr +=  " AND MATCH (" + DBNameCoreConstants.INGREDIENTS + " ) AGAINST ( :ingredients IN BOOLEAN MODE) ";
		}
		queryStr += " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' " + " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) ";
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.medName.trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.manufacturer.trim() + "%");
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			query.setParameter("contraindications", medicine.contraindications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			query.setParameter("indications", medicine.indications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			query.setParameter("ingredients", medicine.ingredients.trim());
		}
		medList = query.list();
		return medList.size();
	}

	@Override
	public List<Medicine> userAdvancedSearch(Medicine medicine,
			Pagination pagination) {
		Session session = sessionFactory.openSession();
		boolean hasFulltext = false;
		String queryStr = "";
		queryStr = USER_NORMAL_ADVANCED_SEARCH;
		queryStr += " AND " + DBNameCoreConstants.MEDICINE_NAME + " like  :medName  ";
		queryStr += " AND  " + DBNameCoreConstants.MANUFACTURER + " like :manufacturer   ";
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			hasFulltext = true;
			queryStr += " AND MATCH(" + DBNameCoreConstants.INDICATIONS+ ")" + " AGAINST(:indications IN BOOLEAN MODE) ";
		}
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			hasFulltext = true;
			queryStr += " AND MATCH(" + DBNameCoreConstants.CONTRAINDICATIONS + ") AGAINST ( :contraindications IN BOOLEAN MODE)";
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			hasFulltext = true;
			queryStr +=  " AND MATCH (" + DBNameCoreConstants.INGREDIENTS + " ) AGAINST ( :ingredients IN BOOLEAN MODE) ";
		}
		queryStr += " AND ( " + DBNameCoreConstants.ACCEPTED_FLAG + " = 'Y' " + " OR " + DBNameCoreConstants.ACCEPTED_FLAG + " IS NULL ) ";
		if(!hasFulltext) {
			queryStr += " ORDER BY " + DBNameCoreConstants.SEARCHED_TIME + " DESC ";
		}
		if(!hasFulltext) {
			queryStr = pagination.getSQLQuery(queryStr, true);
		} else {
			queryStr = pagination.getSQLQuery(queryStr, false);
		}
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", "%" + medicine.medName.trim() + "%");
		query.setParameter("manufacturer", "%" + medicine.manufacturer.trim() + "%");
		if(!Validator.nullOrBlank(medicine.getContraindications())) {
			query.setParameter("contraindications", medicine.contraindications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIndications())) {
			query.setParameter("indications", medicine.indications.trim());
		}
		if(!Validator.nullOrBlank(medicine.getIngredients())) {
			query.setParameter("ingredients", medicine.ingredients.trim());
		}
		List result = query.list();
		List<Medicine> listMed = new ArrayList<>();
		for (Object obj : result) {
			Object[] element = (Object[]) obj;
			Medicine med = new Medicine();
			med.setMedId((int) element[0]);
			med.setMedName((String) element[1]);
			med.setManufacturer((String) element[2]);
			med.setIndications((String) element[3]);
			med.setContraindications((String) element[4]);
			med.setIngredients((String) element[5]);
			listMed.add(med);
		}
		return listMed;
	}

	/*
	 * Implement search by name method
	 * @see com.med.dic.dao.MedicineDAO#searchByName(java.lang.String)
	 */
	@Override
	public ArrayList<Medicine> searchByName(String medName) {
		ArrayList<Medicine> medList = new ArrayList<Medicine>();
		Session session = sessionFactory.openSession();
		String queryStr = SQL_SEARCH_BY_NAME;
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("medName", medName + "%");
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Medicine med = new Medicine();
			med.setMedId((int)element[0]);
			med.setMedName((String)element[1]);
			med.setManufacturer((String)element[2]);
			med.setTypeOfPackageName((String) element[3]);
			med.setIngredients((String)element[4]);
			med.setIndications((String)element[5]);
			med.setContraindications((String)element[6]);
			med.setWarning((String)element[7]);
			med.setImgPath((String) element[8]);
			med.setDosingAndUse((String) element[9]);
			med.setStorage((String) element[10]);
			med.setInteraction((String) element[11]);
			medList.add(med);
		}
		return medList;
	}

	@Override
	public ArrayList<Medicine> searchMedByPharId(int pharmacyId) {
		Session session = sessionFactory.openSession();
		ArrayList<Medicine> medList = new ArrayList<>();
		String queryStr = SQL_SEARCH_MEDICINES_ON_PHARMACY;
		int limitRow = 500;
		Query query = session.createSQLQuery(queryStr);
		query.setParameter("pharmacyId", pharmacyId);
		query.setParameter("limitRow", limitRow);
		List result = query.list();
		for (Object obj : result) {
			Object[] element = (Object[])obj;
			Medicine med = new Medicine();
			med.setMedId((int)element[0]);
			med.setMedName((String)element[1]);
			med.setManufacturer((String)element[2]);
			med.setTypeOfPackageName((String) element[3]);
			med.setIngredients((String)element[4]);
			med.setIndications((String)element[5]);
			med.setContraindications((String)element[6]);
			med.setWarning((String)element[7]);
			med.setImgPath((String) element[8]);
			med.setDosingAndUse((String) element[9]);
			med.setStorage((String) element[10]);
			med.setInteraction((String) element[11]);
			medList.add(med);
		}
		String typeOfPackage = "";
		for (int i = 0; i < medList.size(); i++) {
			typeOfPackage = getTypeOfPackage(medList.get(i).getMedId());
			medList.get(i).setTypeOfPackageName(typeOfPackage);
		}
		return medList;
	}

	public String getTypeOfPackage(int medId) {
		Session session = sessionFactory.openSession();
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		Criteria criteria = session.createCriteria(TypeOfMedicine.class);
		criteria.add(Restrictions.eq("medId", medId));
		typeOfMedList = criteria.list();
		String typeOfPackage = "";
		for (TypeOfMedicine typeOfMedicine2 : typeOfMedList) {
			typeOfPackage += typeOfMedicine2.getTypeOfPackageName() + ", ";
		}
		if (!typeOfPackage.equals("")) {
			typeOfPackage = typeOfPackage.substring(0, typeOfPackage.length() - 2);
		}
		return typeOfPackage;
	}
}
