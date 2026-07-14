package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {

	public static final String CUSTOMER_PRODUCT_QUERY = """
						select * from tr_customer_product where id = ?
						""";
	private CustomerProductDao() {
	}
	
	public static CustomerProductDBModel getCustomerProduct(int tr_customer_product_id) {
		
		CustomerProductDBModel customerProductDBModel=null;
		try {

			Connection connection = DatabaseManager.getconnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_PRODUCT_QUERY);
			preparedStatement.setInt(1, tr_customer_product_id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
                      
                      customerProductDBModel = new CustomerProductDBModel(resultSet.getInt("id"), resultSet.getInt("tr_customer_id"),
                    		  resultSet.getInt("mst_model_id"), resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"), 
                    		  resultSet.getString("imei1"), resultSet.getString("serial_number"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return customerProductDBModel;
	}
	}

