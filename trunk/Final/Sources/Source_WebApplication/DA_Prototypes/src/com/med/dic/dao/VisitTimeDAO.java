package com.med.dic.dao;

import com.med.dic.model.VisitTime;

public interface VisitTimeDAO {

	public VisitTime getVisitTime(String ipAddress, String date);
	public void addVisitTime(VisitTime visitTime);
	public void updateVisitTime(VisitTime visitTime);
}
