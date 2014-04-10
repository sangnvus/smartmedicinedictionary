package com.med.dic.dao;

import java.util.List;

import com.med.dic.model.District;

public interface DistrictDAO {
	public List<District> listAll();
	public District getById(int districtId);
	public List<District> getByCityId(int cityId);
	
}
