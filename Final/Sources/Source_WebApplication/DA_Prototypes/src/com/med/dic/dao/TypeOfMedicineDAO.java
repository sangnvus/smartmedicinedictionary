package com.med.dic.dao;

import java.util.List;

import com.med.dic.model.TypeOfMedicine;

public interface TypeOfMedicineDAO {

	public TypeOfMedicine getById(int medId, int typeOfPackageId);
	public List<TypeOfMedicine> getByMedId(int medId);
	public void add(TypeOfMedicine typeOfMed);
	public void delete(TypeOfMedicine typeOfMed);
	public void delete(int medId);

	// get package name for the medicine
	public String getTypeOfPackage(int medId);
}
