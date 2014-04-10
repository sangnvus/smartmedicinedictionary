package com.med.dic.action;

import com.opensymphony.xwork2.ActionSupport;

public class CheckboxAction extends ActionSupport{

	private static final long serialVersionUID = 3448122882466136915L;
	public String[] checkBox;
	
	public String execute() {
		return SUCCESS;
	}

	/**
	 * @param checkBox the checkBox to set
	 */
	public void setCheckBox(String[] checkBox) {
		this.checkBox = checkBox;
	}
}
