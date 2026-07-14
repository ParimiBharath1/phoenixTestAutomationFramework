package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {

	public static final String CUSTOMER_ADDRESS_QUERY = """
						select id,flat_number,
			apartment_name,
			street_name,
			landmark,
			area,
			pincode,
			country,
			state
			from tr_customer_address where id = ?
						""";

	private CustomerAddressDao() {

	}

	public static CustomerAddressDBModel getCustomerAddress(int custAddressId) {

		CustomerAddressDBModel customerAddressDBModel=null;
		try {

			Connection connection = DatabaseManager.getconnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			preparedStatement.setInt(1, custAddressId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
                      System.out.println(resultSet.getString("flat_number"));
				customerAddressDBModel = new CustomerAddressDBModel
						
						(resultSet.getInt("id"),
						resultSet.getString("flat_number"), resultSet.getString("apartment_name"),
						resultSet.getString("street_name"), resultSet.getString("landmark"),
						resultSet.getString("area"), resultSet.getString("pincode"), resultSet.getString("country"),
						resultSet.getString("state"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return customerAddressDBModel;

	}

}
