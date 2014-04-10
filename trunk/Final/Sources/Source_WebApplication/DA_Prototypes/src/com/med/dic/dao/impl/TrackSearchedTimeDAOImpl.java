package com.med.dic.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.dao.TrackSearchedTimeDAO;
import com.med.dic.model.TrackSearchedTime;

public class TrackSearchedTimeDAOImpl extends BaseDAO implements TrackSearchedTimeDAO{

	@Override
	public List<TrackSearchedTime> listTrackTime(
			TrackSearchedTime trackSearchedTime) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(TrackSearchedTime.class);
		criteria.add(Restrictions.eq("type", trackSearchedTime.getType()));
		criteria.add(Restrictions.eq("monthYear", trackSearchedTime.getMonthYear()));
		criteria.add(Restrictions.eq("typeId", trackSearchedTime.getTypeId()));
		
		return null;
	}

}
