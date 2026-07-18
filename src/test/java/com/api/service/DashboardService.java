package com.api.service;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.restassured.response.Response;

public class DashboardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAIL_ENDPOINT = "/dashboard/details";
	
	private static  Logger  LOGGER = LogManager.getLogger(DashboardService.class);

	public Response count(Role role) {
		
		 LOGGER.info("Making request to the {} for the role {}", COUNT_ENDPOINT, role);
		Response response = given().spec(requestSpecWithAuth(role)).when().get(COUNT_ENDPOINT);

		return response;
	}

	public Response countWithNoAuth() {
		
		 LOGGER.info("Making request to the {} with No Auth Token", COUNT_ENDPOINT);


		Response response = given().spec(requestSpec()).when().get(COUNT_ENDPOINT);

		return response;
	}

	// Details Endpoint method
	public Response details(Role role,Object detailsObject) {
		
		 LOGGER.info("Making request to the {} for the role {} and the payload {}", DETAIL_ENDPOINT, role,detailsObject);

		Response response = given().spec(requestSpecWithAuth(role)).body(detailsObject).when().post(DETAIL_ENDPOINT);

		return response;
	}

}
