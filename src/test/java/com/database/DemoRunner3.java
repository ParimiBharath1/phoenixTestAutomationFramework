package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner3 {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		   DatabaseManager.initalizepool();
          Connection conn =DatabaseManager.getconnection();
          
          System.out.println(conn);
	}

}
