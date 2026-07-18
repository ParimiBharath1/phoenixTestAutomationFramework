package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	private CustomerDao() {
		
	}
	
	private static  Logger  LOGGER = LogManager.getLogger(CustomerDao.class);

	private static final String CUSTOMER_DETAIL_QUERY_STRING = """

			 select * from tr_customer where id=?
			""";

	public static CustomerDBModel getCustomerInfo(int customerId) {

		CustomerDBModel customerDBModel = null;
		try {
			LOGGER.info("Getting the connection from database manager");
			Connection connection = DatabaseManager.getconnection();
			LOGGER.info("Executing sql query{}",CUSTOMER_DETAIL_QUERY_STRING);
			PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_DETAIL_QUERY_STRING);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				customerDBModel = new CustomerDBModel(resultSet.getInt("id"),resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("mobile_number"),
						resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"),
						resultSet.getString("email_id_alt"),resultSet.getInt("tr_customer_address_id"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Cannot convert the resultset to CustomerDBModel bean",e);
		}

		return customerDBModel;
	}
}
