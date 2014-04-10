package com.med.dic.base.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDAO {
	public SessionFactory sessionFactory;
	public Session session;
	
	public BaseDAO() {
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

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = this.sessionFactory.openSession();
	}
}
