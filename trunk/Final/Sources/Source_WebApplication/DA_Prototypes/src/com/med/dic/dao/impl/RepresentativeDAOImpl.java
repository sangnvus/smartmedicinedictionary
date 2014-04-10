package com.med.dic.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.med.dic.base.dao.BaseDAO;
import com.med.dic.dao.RepresentativeDAO;
import com.med.dic.model.Degree;
import com.med.dic.model.Representative;

public class RepresentativeDAOImpl extends BaseDAO implements RepresentativeDAO{

	@Override
	public Representative getRep(int repId) {
		session = sessionFactory.openSession();
		Representative rep = new Representative();
		Criteria criteria = session.createCriteria(Representative.class).add(Restrictions.eq("repId", repId));
		List<Representative> repList = criteria.list();
		if (repList.size() > 0) {
			rep = repList.get(0);
		}
		rep.setDegreeName(getDegreeName(rep.getDegree()));
		return rep;
		//return (Representative)session.get(Representative.class, repId);
	}

	@Override
	public int addRep(Representative repObj) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		Integer repId = (Integer) session.save(repObj);
		tx.commit();
		return repId;
	}

	@Override
	public String getRepName(String email) {
		String name = null;
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Representative.class);
		criteria.add(Restrictions.eq("email", email));
		name = ((Representative)criteria.list().get(0)).getName();
		return name;
	}

	@Override
	public Representative getRepByEmail(String email) {
		Representative rep = new Representative();
//		String degreeName = "";
		Degree degree = new Degree();
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Representative.class);
		criteria.add(Restrictions.eq("email", email));
		rep = (Representative)criteria.list().get(0);
		
		/*if(rep.getDegree() == 1) {
			degreeName = "Dược sĩ trung cấp";
		} else if (rep.getDegree() == 2) {
			degreeName = "Dược sĩ cao đẳng";
		} else if (rep.getDegree() == 3) {
			degreeName = "Dược sĩ đại học";
		} else {
			degreeName = "Khác";
		}*/
			
		rep.setDegreeName(getDegreeName(rep.getDegree()));
		return rep;
	}

	@Override
	public List<Representative> repInfo(String email) {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Representative.class);
		criteria.add(Restrictions.like("email", email + "%"));
		return criteria.list();
	}
	
	public String getDegreeName(int degreeId) {
		Degree degree = new Degree();
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Degree.class);
		criteria.add(Restrictions.eq("degreeId", degreeId));
		degree = (Degree)criteria.list().get(0);
		return degree.getDegreeName();
	}

	@Override
	public void updateRep(Representative repObj) {
		session = sessionFactory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		session.update(repObj);
		tx.commit();
	}

}
