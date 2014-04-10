package com.med.dic.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.med.dic.dao.DistrictDAO;
import com.med.dic.model.District;

public class DistrictDAOImpl implements DistrictDAO {

	public SessionFactory sessionFactory;
	private static final String cityId = "cityId";
	@Override
	public List<District> listAll() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(District.class).list();
	}

	@Override
	public District getById(int districtId) {
		Session session = sessionFactory.openSession();
		return (District)session.get(District.class, districtId);
	}

	@Override
	public List<District> getByCityId(int cityId) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(District.class);
		criteria.add(Restrictions.eq("cityId", cityId));
		return criteria.list();
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

}
