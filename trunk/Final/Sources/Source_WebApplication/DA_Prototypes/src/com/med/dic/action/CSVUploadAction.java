package com.med.dic.action;

import java.io.File;

import com.med.dic.base.action.BaseAction;

public class CSVUploadAction extends BaseAction {

	public File csvFile;
	public String csvFileFileName;
	public String csvFileContentType;

	public String execute() {
		return SUCCESS;
	}

	/**
	 * @return the csvFile
	 */
	public File getCsvFile() {
		return csvFile;
	}

	/**
	 * @param csvFile the csvFile to set
	 */
	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

	/**
	 * @return the csvFileFileName
	 */
	public String getCsvFileFileName() {
		return csvFileFileName;
	}

	/**
	 * @param csvFileFileName the csvFileFileName to set
	 */
	public void setCsvFileFileName(String csvFileFileName) {
		this.csvFileFileName = csvFileFileName;
	}

	/**
	 * @return the csvFileContentType
	 */
	public String getCsvFileContentType() {
		return csvFileContentType;
	}

	/**
	 * @param csvFileContentType the csvFileContentType to set
	 */
	public void setCsvFileContentType(String csvFileContentType) {
		this.csvFileContentType = csvFileContentType;
	}

}
