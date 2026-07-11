package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCpDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
         HikariConfig hikariConfig = new HikariConfig();
         hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
         hikariConfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
         hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
         hikariConfig.setMaximumPoolSize(10);
         hikariConfig.setMinimumIdle(2);
         hikariConfig.setConnectionTimeout(10000);
         hikariConfig.setIdleTimeout(10000);
         hikariConfig.setMaxLifetime(180000);
         hikariConfig.setPoolName("Phoenix test hikari");
         
         HikariDataSource ds = new HikariDataSource(hikariConfig);
         Connection connection= ds.getConnection();
               System.out.println(connection);
               
               Statement statement =connection.createStatement();
      		 
     		  ResultSet resultSet =statement.executeQuery("Select first_name ,last_name ,mobile_number  from tr_customer;");
     		  
     		  
     		   while (resultSet.next()) {
     			String first_name = resultSet.getString("first_name");
     			String last_name = resultSet.getString("last_name");
     			String mobile_number = resultSet.getString("mobile_number");
     			
     			System.out.println(first_name +" "+last_name+" "+mobile_number);
     		}
         
	}

}
