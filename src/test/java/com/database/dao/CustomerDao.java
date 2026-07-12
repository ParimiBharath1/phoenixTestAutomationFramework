package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	
	private static final String CUSTOMER_DETAIL_QUERY_STRING= """
			
			 select * from tr_customer where id=351365
			""";
	
	
	public static CustomerDBModel getCustomerInfo() throws SQLException {
		
		Connection connection =  DatabaseManager.getconnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(CUSTOMER_DETAIL_QUERY_STRING);
		CustomerDBModel customerDBModel=null;
		while (resultSet.next()) {
			
			 customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), resultSet.getString("last_name"), 
					resultSet.getString("mobile_number"), resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"), 
					resultSet.getString("email_id_alt"));
		}
		
		return  customerDBModel;
	}
}

