package com.med.dic.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.med.dic.dao.CityDAO;
import com.med.dic.model.City;

public class CityDAOImpl implements CityDAO {

	public SessionFactory sessionFactory;

	@Override
	public List<City> listAll() {
		Session session = sessionFactory.openSession();
		return session.createCriteria(City.class).list();
	}

	@Override
	public City getById(int cityId) {
		Session session = sessionFactory.openSession();
		return (City)session.get(City.class, cityId);
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
