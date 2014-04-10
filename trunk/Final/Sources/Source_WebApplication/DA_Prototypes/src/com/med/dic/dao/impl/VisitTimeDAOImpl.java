package com.med.dic.dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.constant.DBNameCoreConstants;
import com.med.dic.dao.VisitTimeDAO;
import com.med.dic.model.VisitTime;

public class VisitTimeDAOImpl extends BaseDAO implements VisitTimeDAO {

	private static final String SEARCH_BY_IP_ADDRESS = " SELECT " + DBNameCoreConstants.REG_DATE + ", "
														+ DBNameCoreConstants.MOD_DATE + ", "
														+ DBNameCoreConstants.IP_ADDRESS + ", "
														+ DBNameCoreConstants.VISIT_TIME
														+ " FROM " + DBNameCoreConstants.VISIT_TIME
														+ " WHERE " + DBNameCoreConstants.IP_ADDRESS + " = :ipAddress "
														+ " AND DATE_FORMAT (" + DBNameCoreConstants.REG_DATE
														+ " , '%Y%e%m' ) = :regDate";
	@Override
	public VisitTime getVisitTime(String ipAddress, String date) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(VisitTime.class);
		criteria.add(Restrictions.eq("ipAddress", ipAddress));
		/*criteria.add(Restrictions.eq("DATE_FORMAT(regDate, '%Y%e%m'", date));*/
		criteria.addOrder(Order.desc("modDate"));
		List<VisitTime> visitorList = new ArrayList<>();
		VisitTime visitor = null;
		visitorList = criteria.list();
		if(visitorList.size() > 0) {
			visitor = visitorList.get(0);
			String regDate = new SimpleDateFormat("yyyyMMdd")
			.format(visitor.getRegDate());
			if(!date.equals(regDate)) {
				visitor = null;
			}
		}
		return visitor;
	}

	@Override
	public void addVisitTime(VisitTime visitTime) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.save(visitTime);
		tx.commit();
	}

	@Override
	public void updateVisitTime(VisitTime visitTime) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(visitTime);
		tx.commit();
	}

}
