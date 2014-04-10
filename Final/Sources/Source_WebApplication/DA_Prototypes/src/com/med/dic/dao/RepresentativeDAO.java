package com.med.dic.dao;

import java.util.List;

import com.med.dic.model.Representative;

public interface RepresentativeDAO {

	public Representative getRep(int repId);
	public int addRep(Representative repObj);
	public void updateRep(Representative repObj);
	public String getRepName(String email);
	public Representative getRepByEmail(String email);
	public List<Representative> repInfo(String email);
	public String getDegreeName(int degreeId);
}
