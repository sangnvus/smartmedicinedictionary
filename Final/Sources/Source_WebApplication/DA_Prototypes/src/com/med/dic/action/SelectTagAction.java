package com.med.dic.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.med.dic.model.Degree;
import com.med.dic.model.MedicineType;
import com.med.dic.model.TypeOfBusiness;
import com.opensymphony.xwork2.ActionSupport;

public class SelectTagAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<MedicineType> medTypes;
	public String keyWordMedTypeName;
	public List<Degree> degreeList;
	public List<TypeOfBusiness> typeOfBusinessList;
	public List<String> medTypeSelectBox = new ArrayList<>();
	public String degree = "3";

	public static ResultSet resultSet(String query) {
		ResultSet resultSet = null;
		try {
			String driver = "";
			driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/medicine_dictionary";
			String userName = "root";
			String password = "123456789";
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, userName,
					password);
			PreparedStatement statement = (PreparedStatement) connection
					.prepareStatement(query);
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			return null;
		}
		return resultSet;
	}

	public String selectMedTypes() throws SQLException {
		medTypes = new ArrayList<MedicineType>();
		ResultSet medTypesList = resultSet("SELECT * FROM medicine_dictionary.medicine_type");
		try {
			while (medTypesList.next()) {
				MedicineType med = new MedicineType();
				String medTypeName = medTypesList.getString("Name");
				int medTypeId = medTypesList.getInt("Id");
				med.setMedTypeName(medTypeName);
				med.setMedTypeId(medTypeId);
				medTypeSelectBox.add(medTypeName + "~" + medTypeId);
				medTypes.add(med);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return SUCCESS;
	}

	public String degree() throws SQLException {
		degreeList = new ArrayList<>();
		ResultSet degreesLst = resultSet("SELECT * FROM degrees");
		try {
			while (degreesLst.next()) {
				Degree degree = new Degree();
				degree.setDegreeId(degreesLst.getInt("DEGREE_ID"));
				degree.setDegreeName(degreesLst.getString("DEGREE_NAME"));
				degreeList.add(degree);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return SUCCESS;
	}

	public String typeOfBusiness() throws SQLException {
		typeOfBusinessList = new ArrayList<>();
		ResultSet typeOfBusLst = resultSet(" SELECT * FROM TYPE_OF_BUSINESS");
		try {
			while (typeOfBusLst.next()) {
				TypeOfBusiness typeOfBusiness = new TypeOfBusiness();
				typeOfBusiness.setTypeBusinessId(typeOfBusLst.getInt("ID"));
				typeOfBusiness.setTypeBusinessName(typeOfBusLst
						.getString("TYPE_OF_BUSINESS"));
				typeOfBusinessList.add(typeOfBusiness);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return SUCCESS;
	}

	/**
	 * @return the medTypes
	 */
	public List<MedicineType> getMedTypes() {
		return medTypes;
	}

	/**
	 * @param medTypes
	 *            the medTypes to set
	 */
	public void setMedTypes(List<MedicineType> medTypes) {
		this.medTypes = medTypes;
	}

	public String getKeyWordMedTypeName() {
		return keyWordMedTypeName;
	}

	public void setKeyWordMedTypeName(String keyWordMedTypeName) {
		this.keyWordMedTypeName = keyWordMedTypeName;
	}

	/**
	 * @return the degreeList
	 */
	public List<Degree> getDegreeList() {
		return degreeList;
	}

	/**
	 * @param degreeList
	 *            the degreeList to set
	 */
	public void setDegreeList(List<Degree> degreeList) {
		this.degreeList = degreeList;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the typeOfBusinessList
	 */
	public List<TypeOfBusiness> getTypeOfBusinessList() {
		return typeOfBusinessList;
	}

	/**
	 * @param typeOfBusinessList
	 *            the typeOfBusinessList to set
	 */
	public void setTypeOfBusinessList(List<TypeOfBusiness> typeOfBusinessList) {
		this.typeOfBusinessList = typeOfBusinessList;
	}

	/**
	 * @return the medTypeSelectBox
	 */
	public List<String> getMedTypeSelectBox() {
		return medTypeSelectBox;
	}

	/**
	 * @param medTypeSelectBox
	 *            the medTypeSelectBox to set
	 */
	public void setMedTypeSelectBox(List<String> medTypeSelectBox) {
		this.medTypeSelectBox = medTypeSelectBox;
	}

}
