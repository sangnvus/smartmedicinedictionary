package com.med.dic.dao;

import com.med.dic.model.SMDUser;

public interface SMDUserDAO {

	public SMDUser getSMDUser(String email);
	public int addSMDUser(SMDUser smdUser);
	public void updateSMDUser(SMDUser smdUser);
}
