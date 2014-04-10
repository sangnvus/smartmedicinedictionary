package com.med.dic.dao;

import java.util.ArrayList;
import java.util.List;

import com.med.dic.model.Medicine;
import com.med.dic.pagination.Pagination;

public interface MedicineDAO {

	public List<Medicine> listMedicine(Medicine medicine, Pagination pagination);
	public List<String> autoComplete();
	public List<Medicine> medList();
	public Medicine searchById(int medId);
	public int count(String medName);
	public int countForPhar(String medName, int pharmacyId);
	List<Medicine> count_user_searchMedAdvanced(Medicine medicine);
	List<Medicine> user_searchMedAdvanced(Medicine medicine,
			Pagination pagination);
	List<Medicine> count_admin_searchMedAdvanced(Medicine medicine);
	List<Medicine> admin_searchMedAdvanced(Medicine medicine,
			Pagination pagination);
	Medicine searchByID(int medId);
	int addNewMed(Medicine medicine);
	void updateMedicine(Medicine medicine);
	public List<Medicine> medListForPhar(String medName, Pagination pagination, int pharmacyId);
	public Medicine getMedByIdAndTypeOfPackage(int medId, int typeOfPackage);
	public int coutForAcceptMed(Medicine medicine);
	public List<Medicine> searchForAcceptMed(Medicine medicine, Pagination pagination);
	public int userSearchAdvancedWithType(Medicine medicine);
	public List<Medicine> userAdvancedSearchWithtype(Medicine medicine, Pagination pagination);
	public int userSearchAdvanced(Medicine medicine);
	public List<Medicine> userAdvancedSearch(Medicine medicine, Pagination pagination);
	public ArrayList<Medicine> searchByName(String medName);

	// get medicines is selling in pharmacy
	public ArrayList<Medicine> searchMedByPharId(int pharmacyId);
}
