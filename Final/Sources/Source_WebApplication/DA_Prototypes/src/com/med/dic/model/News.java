package com.med.dic.model;

import java.util.ArrayList;
import java.util.List;

import com.med.dic.base.model.BaseModel;

public class News extends BaseModel {

	public int id;
	public String title;
	public String imgPath;
	public String content;
	public String searchRedate;
	public String author;
	public String modDateStr;
	public String regDateStr;
	public String subContent;
	public String imgDesc;
	public String dayName;
	public List<String> arrayContent = new ArrayList<>();
	public int searchedTime;
	
	/**
	 * @return the modDateStr
	 */
	public String getModDateStr() {
		return modDateStr;
	}

	/**
	 * @param modDateStr the modDateStr to set
	 */
	public void setModDateStr(String modDateStr) {
		this.modDateStr = modDateStr;
	}

	/**
	 * @return the regDateStr
	 */
	public String getRegDateStr() {
		return regDateStr;
	}

	/**
	 * @param regDateStr the regDateStr to set
	 */
	public void setRegDateStr(String regDateStr) {
		this.regDateStr = regDateStr;
	}

	public News() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath
	 *            the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the searchRedate
	 */
	public String getSearchRedate() {
		return searchRedate;
	}

	/**
	 * @param searchRedate the searchRedate to set
	 */
	public void setSearchRedate(String searchRedate) {
		this.searchRedate = searchRedate;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the subContent
	 */
	public String getSubContent() {
		return subContent;
	}

	/**
	 * @param subContent the subContent to set
	 */
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	/**
	 * @return the arrayContent
	 */
	public List<String> getArrayContent() {
		return arrayContent;
	}

	/**
	 * @param arrayContent the arrayContent to set
	 */
	public void setArrayContent(List<String> arrayContent) {
		this.arrayContent = arrayContent;
	}

	/**
	 * @return the imgDesc
	 */
	public String getImgDesc() {
		return imgDesc;
	}

	/**
	 * @param imgDesc the imgDesc to set
	 */
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}

	/**
	 * @return the dayName
	 */
	public String getDayName() {
		return dayName;
	}

	/**
	 * @param dayName the dayName to set
	 */
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	/**
	 * @return the searchedTime
	 */
	public int getSearchedTime() {
		return searchedTime;
	}

	/**
	 * @param searchedTime the searchedTime to set
	 */
	public void setSearchedTime(int searchedTime) {
		this.searchedTime = searchedTime;
	}
}
