package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManager {
	
	
	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private volatile static Connection connection;
	
	private DatabaseManager() {
		
	}

    public   static void  CreateConnection() throws SQLException {
    	 	
    	if (connection == null ) {		
    		synchronized(DatabaseManager.class) {
    			if (connection == null ) {
    		connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
    			}
    		}
		}
    	  
    	
    	System.out.println(connection);
    	
    	
    }
	
}
