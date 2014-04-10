package com.med.dic.email.config;

import java.io.InputStream;
import java.util.Properties;

import com.med.dic.database.SQLExecuteUtils;
import com.med.dic.form.EmailForm;

public class LoadEmailConfiguration {

	public static EmailForm getProperties() {
		EmailForm email = new EmailForm();
		try {
			Properties prop = new Properties();
			InputStream in = LoadEmailConfiguration.class.getResourceAsStream("EmailConfiguration.properties");
			prop.load(in);
			email.setHost(prop.getProperty("host"));
			email.setPort(prop.getProperty("port"));
			email.setUserName(prop.getProperty("userName"));
			email.setPassword(prop.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
}
