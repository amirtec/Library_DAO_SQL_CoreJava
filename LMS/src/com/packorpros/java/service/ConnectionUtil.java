package com.packorpros.java.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static String  driver= "com.mysql.cj.jdbc.Driver";
	public static String  url= "jdbc:mysql://localhost/library?useSSL=false";
	public static String  userName= "root";
	public static String  password= "root";
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		
		Class.forName(driver);
		Connection conn= DriverManager.getConnection(url, userName, password);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
		
	}

	

}
