package com.med.dic.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;

public class CSVManagement {

	private static char seprator;
	public CSVManagement() {
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
		String[] nextLine;
		if (null == headerRow) {
			throw new FileNotFoundException(
					"No columns defined in given CSV file." +
					"Please check the CSV file format.");
		}
		if("CITIES".equals(tableName)) {
			
		}
	}
}
