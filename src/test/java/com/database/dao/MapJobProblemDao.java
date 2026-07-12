package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DatabaseManager;
import com.database.model.MapJobProblemDBModel;

public class MapJobProblemDao {
	
	
	private static final String CUSTOMER_PROBLEM_QUERY = """
	select * from map_job_problem where tr_job_head_id = ?
			""";

	private MapJobProblemDao() {
		
	}
	
	
	 public static  MapJobProblemDBModel  getProblemsInfo(int tr_job_head_id) {
		 MapJobProblemDBModel mapJobProblemDBModel = null;
			try {

				Connection connection = DatabaseManager.getconnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_PROBLEM_QUERY);
				preparedStatement.setInt(1, tr_job_head_id);
				ResultSet resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {

					mapJobProblemDBModel = new  MapJobProblemDBModel(resultSet.getInt("id"),resultSet.getInt("tr_job_head_id"),
							resultSet.getInt("mst_problem_id"),resultSet.getString("remark"));
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			return mapJobProblemDBModel;
	}
	
}
