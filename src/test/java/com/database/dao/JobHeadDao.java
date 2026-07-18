package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.JobHeadModel;

public class JobHeadDao {

	private static  Logger  LOGGER = LogManager.getLogger(JobHeadDao.class);
	private static final String JOB_HEAD_QUERY = """

			select * from tr_job_head  where tr_customer_id = ?
				""";

	private JobHeadDao() {

	}

	public static JobHeadModel getDataFromJobHead(int tr_customer_id) {
		JobHeadModel jobHeadModel = null;
		try {
			LOGGER.info("Getting the connection from database manager");
			Connection connection = DatabaseManager.getconnection();
			LOGGER.info("Executing sql query{}",JOB_HEAD_QUERY);
			PreparedStatement preparedStatement = connection.prepareStatement(JOB_HEAD_QUERY);
			preparedStatement.setInt(1, tr_customer_id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				jobHeadModel = new JobHeadModel(resultSet.getInt("id"), resultSet.getString("job_number"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("tr_customer_product_id"),
						resultSet.getInt("mst_service_location_id"), resultSet.getInt("mst_platform_id"),
						resultSet.getInt("mst_warrenty_status_id"), resultSet.getInt("mst_oem_id"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Cannot convert the resultset to JobHeadModel bean");
		}

		return jobHeadModel;

	}

}
