package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	private static final String url = "jdbc:mysql://localhost:3306/crm_app";
	private static final String username = "root";
	private static final String password = "Thienanvip@321";
	
	public static Connection getConnection() {
		try {
			// Sá»­ dá»¥ng class com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Gá»�i hÃ m getConnection cá»§a DriverManager Ä‘á»ƒ thá»±c hiá»‡n káº¿t ná»‘i db
			// HÃ m nÃ y tráº£ vá»� Ä‘á»‘i tÆ°á»£ng Connection
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
