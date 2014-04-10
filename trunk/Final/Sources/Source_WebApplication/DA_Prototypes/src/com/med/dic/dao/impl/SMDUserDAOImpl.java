package com.med.dic.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.dao.SMDUserDAO;
import com.med.dic.model.SMDUser;

public class SMDUserDAOImpl extends BaseDAO implements SMDUserDAO{

	private static final String SQL_JOIN_REP_SMDUSER = " SELECT "
														+ DBNameCoreConstants.SMD_USER + "." + DBNameCoreConstants.EMAIL
														+ DBNameCoreConstants.REPRESENTATIVE + "." + DBNameCoreConstants.NAME
														+ " FROM " + DBNameCoreConstants.SMD_USER + " JOIN "
														+ DBNameCoreConstants.REPRESENTATIVE + " ON "
														+ DBNameCoreConstants.SMD_USER + "." + DBNameCoreConstants.EMAIL + " = "
														+ DBNameCoreConstants.REPRESENTATIVE + "." + DBNameCoreConstants.EMAIL
														+ " WHERE " + DBNameCoreConstants.SMD_USER + "." + DBNameCoreConstants.EMAIL + " = :email";
	
	@Override
	public SMDUser getSMDUser(String email) {
		session = sessionFactory.openSession();
		SMDUser smdUser = null;
		Criteria criteria = session.createCriteria(SMDUser.class).add(Restrictions.eq("email", email));
		List<SMDUser> smdUserList = criteria.list();
		if (smdUserList.size() > 0) {
			smdUser = smdUserList.get(0);
		}
		return smdUser;
	}

	@Override
	public int addSMDUser(SMDUser smdUser) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		Integer userId = (Integer)session.save(smdUser);
		tx.commit();
		return userId;
	}

	@Override
	public void updateSMDUser(SMDUser smdUser) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(smdUser);
		tx.commit();
	}

}
