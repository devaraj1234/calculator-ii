package com.calculator_ii;

import java.sql.*;

public class Connect2Sql {
	
	public static void main(String[] args) {
		try {
			// get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "Durga@1234");
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("select * from actor ");
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name")+" " +myRs.getString("first_name"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}