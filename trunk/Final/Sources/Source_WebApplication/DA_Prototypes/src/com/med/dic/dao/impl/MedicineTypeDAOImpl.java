package com.med.dic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.dao.MedicineTypeDAO;
import com.med.dic.model.MedicineType;

public class MedicineTypeDAOImpl extends BaseDAO implements MedicineTypeDAO{

	private static final long serialVersionUID = 8927482900495234841L;
	public SessionFactory sessionFactory;

	@Override
	public MedicineType searchMedTypeById(int medTypeId) {
		Session session = sessionFactory.openSession();
		return (MedicineType) session.get(MedicineType.class, medTypeId);
	}
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<MedicineType> listAll() {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(MedicineType.class);
		List<MedicineType> medTypeList = new ArrayList<>();
		try {
			medTypeList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medTypeList;
	}

	/*
	 * Implement list package name method
	 * @see com.med.dic.dao.MedicineTypeDAO#listPackageName()
	 */
	@Override
	public ArrayList<String> listPackageName() {
		List<MedicineType> medTypeList = new ArrayList<>();
		medTypeList = listAll();
		ArrayList<String> listName = new ArrayList<>();
		for (int i = 0; i < medTypeList.size(); i++) {
			listName.add(medTypeList.get(i).getMedTypeName());
		}
		return listName;
	}
}
