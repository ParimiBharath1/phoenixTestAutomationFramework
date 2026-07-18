package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemDBModel;

public class MapJobProblemDao {
	
	private static  Logger  LOGGER = LogManager.getLogger(MapJobProblemDao.class);
	private static final String CUSTOMER_PROBLEM_QUERY = """
	select * from map_job_problem where tr_job_head_id = ?
			""";

	private MapJobProblemDao() {
		
	}
	
	
	 public static  MapJobProblemDBModel  getProblemsInfo(int tr_job_head_id) {
		 MapJobProblemDBModel mapJobProblemDBModel = null;
			try {
				LOGGER.info("Getting the connection from database manager");
				Connection connection = DatabaseManager.getconnection();
				LOGGER.info("Executing sql query{}",CUSTOMER_PROBLEM_QUERY);
				PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_PROBLEM_QUERY);
				preparedStatement.setInt(1, tr_job_head_id);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					mapJobProblemDBModel = new  MapJobProblemDBModel(resultSet.getInt("id"),resultSet.getInt("tr_job_head_id"),
							resultSet.getInt("mst_problem_id"),resultSet.getString("remark"));
				}

			} catch (Exception e) {
				// TODO: handle exception
				LOGGER.error("Cannot convert the resultset to MapJobProblemDBModel bean");
			}

			return mapJobProblemDBModel;
	}
	
}
