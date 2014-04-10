package com.med.dic.dao;

import java.util.List;

import com.med.dic.model.City;

public interface CityDAO {

	public List<City> listAll();
	public City getById(int cityId);
}
