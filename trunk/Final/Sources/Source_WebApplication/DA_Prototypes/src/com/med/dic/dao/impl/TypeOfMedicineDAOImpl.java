package com.med.dic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.dao.TypeOfMedicineDAO;
import com.med.dic.model.TypeOfMedicine;

public class TypeOfMedicineDAOImpl extends BaseDAO implements TypeOfMedicineDAO{

	@Override
	public TypeOfMedicine getById(int medId, int typeOfPackageId) {
		session = sessionFactory.openSession();
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		Criteria criteria = session.createCriteria(TypeOfMedicine.class);
		criteria.add(Restrictions.eq("medId", medId));
		criteria.add(Restrictions.eq("typeOfPackageId", typeOfPackageId));
		typeOfMedList = criteria.list();
		TypeOfMedicine typeOfMed = null;
		if(typeOfMedList.size() > 0) {
			typeOfMed = typeOfMedList.get(0);
		}
		return typeOfMed;
	}

	@Override
	public List<TypeOfMedicine> getByMedId(int medId) {
		session = sessionFactory.openSession();
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		Criteria criteria = session.createCriteria(TypeOfMedicine.class);
		criteria.add(Restrictions.eq("medId", medId));
		typeOfMedList = criteria.list();
		return typeOfMedList;
	}

	@Override
	public void add(TypeOfMedicine typeOfMed) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.save(typeOfMed);
		tx.commit();

	}

	@Override
	public void delete(TypeOfMedicine typeOfMed) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.delete(typeOfMed);
		tx.commit();

	}

	@Override
	public void delete(int medId) {
		session = sessionFactory.openSession();
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		Criteria criteria = session.createCriteria(TypeOfMedicine.class);
		criteria.add(Restrictions.eq("medId", medId));
		typeOfMedList = criteria.list();
		Transaction tx = null;
		tx = session.beginTransaction();
		for (TypeOfMedicine typeOfMedicine : typeOfMedList) {
			session.delete(typeOfMedicine);
		}
		tx.commit();

	}

	@Override
	public String getTypeOfPackage(int medId) {
		List<TypeOfMedicine> typeOfMedList = new ArrayList<>();
		typeOfMedList = getByMedId(medId);
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
