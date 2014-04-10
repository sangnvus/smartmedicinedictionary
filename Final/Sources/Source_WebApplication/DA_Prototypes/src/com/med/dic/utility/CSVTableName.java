package com.med.dic.utility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.med.dic.base.action.BaseAction;

public class CSVTableName extends BaseAction {

	public List<String> csvFileName;

	public String listCSV() {
		csvFileName = new ArrayList<>();
		try {
			Properties prop = new Properties();
			InputStream in = CSVTableName.class
					.getResourceAsStream("csv.properties");
			prop.load(in);
			String fileNameList = prop.getProperty("CSV_NAME");
			String[] fileNameSplit = fileNameList.split(",");
			for (String fileName : fileNameSplit) {
				csvFileName.add(fileName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * @return the csvFileName
	 */
	public List<String> getCsvFileName() {
		return csvFileName;
	}

	/**
	 * @param csvFileName
	 *            the csvFileName to set
	 */
	public void setCsvFileName(List<String> csvFileName) {
		this.csvFileName = csvFileName;
	}
}
