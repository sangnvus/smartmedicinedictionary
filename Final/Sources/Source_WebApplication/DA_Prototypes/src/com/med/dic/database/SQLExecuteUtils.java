package com.med.dic.database;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import java.sql.Connection;

import com.mysql.jdbc.PreparedStatement;

public class SQLExecuteUtils {
	
	public static Connection connection() {
		Connection connect = null;
		try {
			Properties prop = new Properties();
			InputStream in = SQLExecuteUtils.class.getResourceAsStream("database.properties");
			prop.load(in);
			String driver = prop.getProperty("jdbc.driverClassName");
			String url = prop.getProperty("jdbc.url");
			String userName = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");
			Class.forName(driver);
			connect = DriverManager.getConnection(url, userName,
					password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}

	public static ResultSet resultSet(String query) {
		ResultSet resultSet = null;
		try {
			Connection connection = connection();
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			resultSet = statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static ResultSet result(String query, int id) {
		ResultSet resultSet = null;
		try {
			Connection connection = connection();
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
