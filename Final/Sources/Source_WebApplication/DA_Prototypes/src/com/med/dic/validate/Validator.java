package com.med.dic.validate;

public class Validator {

	public static boolean nullOrBlank(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
}
