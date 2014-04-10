package com.med.dic.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.dao.AdvertiseDAO;
import com.med.dic.model.Advertise;

public class AdvertiseDAOImpl extends BaseDAO implements AdvertiseDAO{

	@Override
	public List<Advertise> advertiseList() {
		List<Advertise> advertiseList = new ArrayList<>();
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Advertise.class);
		try {
			advertiseList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return advertiseList;
	}

}
