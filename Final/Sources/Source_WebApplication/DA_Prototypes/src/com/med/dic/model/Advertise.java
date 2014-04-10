package com.med.dic.model;

import com.med.dic.base.model.BaseModel;

public class Advertise extends BaseModel{

	public int advertiseId;
	public String title;
	public String imgPath;
	public String content;
	public String link;
	
	public Advertise() {
		
	}
	
	/**
	 * @return the advertiseId
	 */
	public int getAdvertiseId() {
		return advertiseId;
	}
	/**
	 * @param advertiseId the advertiseId to set
	 */
	public void setAdvertiseId(int advertiseId) {
		this.advertiseId = advertiseId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
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
	 * @param imgPath the imgPath to set
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
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
}
