package com.borderlands.connectionOnly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToSQL {

	public static Connection getConnection() {
		//Establishing Connection to the SQL database
		String url = "jdbc:postgresql://localhost:5432/postgres";
		try {
			return DriverManager.getConnection(url, 
								System.getenv("VENDOR_ROLE"), 
								System.getenv("VENDOR_PASS"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database cannot be found:  Access Denied!");
			return null;
		}
	}
	
}
