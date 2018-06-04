package com.packofpros.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddAuthors {
	
	public static String  driver= "com.mysql.cj.jdbc.Driver";
	public static String  url= "jdbc:mysql://localhost/library?useSSL=false";
	public static String  userName= "root";
	public static String  password= "root";

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter Authors Name to add");
		String authorName=scan.nextLine();
		
		try {
			//1> Register Drviers
			Class.forName(driver);
			
			//2> Create Connection
			Connection conn = DriverManager.getConnection(url, userName, password);
			
			//3> Create Statement Object
			PreparedStatement pstmt = conn.prepareStatement("insert into tbl_author (authorName) values(?)");
			pstmt.setString(1, authorName);
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
