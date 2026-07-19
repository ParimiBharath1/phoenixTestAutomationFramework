package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

import io.qameta.allure.Step;

public class CustomerAddressDao {
	private static  Logger  LOGGER = LogManager.getLogger(CustomerAddressDao.class);

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

	 @Step("Retriving the Create address data from Database for specific customer id")
	public static CustomerAddressDBModel getCustomerAddress(int custAddressId) {

		CustomerAddressDBModel customerAddressDBModel=null;
		try {
			LOGGER.info("Getting the connection from database manager");
			Connection connection = DatabaseManager.getconnection();
			LOGGER.info("Executing sql query{}",CUSTOMER_ADDRESS_QUERY);
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
			LOGGER.error("Cannot convert the result set to CustomerAddressDBModel bean");
			e.printStackTrace();
		}

		return customerAddressDBModel;

	}

}
