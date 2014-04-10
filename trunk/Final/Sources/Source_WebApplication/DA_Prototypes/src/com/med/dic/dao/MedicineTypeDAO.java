package com.med.dic.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.model.MedicineType;

public interface MedicineTypeDAO extends Serializable{

	public MedicineType searchMedTypeById(int medTypeId);
	public List<MedicineType> listAll();

	// list all package name
	public ArrayList<String> listPackageName();
}
