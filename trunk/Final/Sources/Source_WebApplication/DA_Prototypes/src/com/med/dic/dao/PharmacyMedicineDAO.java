package com.med.dic.dao;

import java.util.List;

import com.med.dic.model.PharmacyMedicine;
import com.med.dic.pagination.Pagination;

public interface PharmacyMedicineDAO {

	
	public List<PharmacyMedicine> listAll();
	public PharmacyMedicine getById(int medId, int pharId);
	public List<PharmacyMedicine> getByMedId(int medId);
	public List<PharmacyMedicine> getByPharId(int pharId);
	public List<PharmacyMedicine> listPharById(int pharId, int typeOfPackageId, String deleteFlag);
	public void addPharMed(PharmacyMedicine pharMed);
	public void deletePharMed(PharmacyMedicine pharMed);
	public void updatePharMed(PharmacyMedicine pharMed);
	public List<PharmacyMedicine> pharMedList(int pharId, Pagination pagination, int typeOfPackageId);
}
