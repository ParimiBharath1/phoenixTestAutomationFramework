package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DatabaseManagerOLD {
	
	
	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private volatile static Connection connection;
	
	private DatabaseManagerOLD() {
		
	}

    public   static void  CreateConnection() throws SQLException {
    	 	
    	if (connection == null ) {		
    		synchronized(DatabaseManagerOLD.class) {
    			if (connection == null ) {
    		connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
    			}
    		}
		}
    	  
    	
    	System.out.println(connection);
    	
    	
    }
	
}
