package com.med.dic.dao;

import java.util.ArrayList;
import java.util.List;

import com.med.dic.model.Pharmacy;
import com.med.dic.pagination.Pagination;

public interface PharmacyDAO {

	List<Pharmacy> listAll(Pharmacy pharmacy);
	public int addPharmacy(Pharmacy pharmacy);
	public void updatePharmacy(Pharmacy pharmacy);
	public Pharmacy getPharmacy(int pharmacyId);
	public List<Pharmacy> listLocations(Pharmacy pharmacy, int radius);
	public Pharmacy getPharmacyLatLong(String address);
	public List<Pharmacy> repsPharmacy(int repId);
	public int count(Pharmacy pharmacy);
	public int countForAdmin(Pharmacy pharmacy);
	public List<Pharmacy> listPharmacy(Pharmacy pharmacy, Pagination pagination, boolean isAdmin);
	public List<Pharmacy> listPharmacyAdmin(Pharmacy pharmacy, Pagination pagination);
	public int countAdvancedSearch(Pharmacy pharmacy);
	public List<Pharmacy> listAdvancedSearch(Pharmacy pharmacy, Pagination pagination);

	// get list pharmacy location by latitude and longitude
	public ArrayList<Pharmacy> listPharmacyLocation(double lat, double lon);

	// get pharmacy data by address and name
	public ArrayList<Pharmacy> getPharmacyByNameAndAddr(String pharmacyName, String address);

	// get pharmacy data by medicine id
	public ArrayList<Pharmacy> getPharmacyByMedId(int medId);
}
