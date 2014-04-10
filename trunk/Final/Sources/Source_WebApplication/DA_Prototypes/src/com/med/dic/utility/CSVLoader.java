package com.med.dic.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.med.dic.dao.CityDAO;

import au.com.bytecode.opencsv.CSVReader;


public class CSVLoader {

	private CityDAO cityDAO;
	private static char seprator;
	public CSVLoader() {
		seprator = ',';
	}
	public static void loadCSV(File csvFile, String tableName) throws Exception {
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader(csvFile), seprator);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occured while executing file. "
					+ e.getMessage());

		}
		String[] headerRow = csvReader.readNext();
		if (null == headerRow) {
			throw new FileNotFoundException(
					"No columns defined in given CSV file." +
					"Please check the CSV file format.");
		}
		if("CITIES".equals(tableName)) {
			uploadCsvCities(csvReader, tableName);
		}
	}
	
	public static void uploadCsvCities(CSVReader csvReader, String tableName) throws Exception{
		String[] nextLine;
		try {
			while ((nextLine = csvReader.readNext()) != null) {

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"Error occured while loading data from file to database."
							+ e.getMessage());
		} finally {
			csvReader.close();
		}
	}
	/**
	 * @return the cityDAO
	 */
	public CityDAO getCityDAO() {
		return cityDAO;
	}
	/**
	 * @param cityDAO the cityDAO to set
	 */
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
}
