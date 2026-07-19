package com.api.service;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	
	private static final String SEARCH_ENDPOINT = "/job/search";
	
	private static  Logger  LOGGER = LogManager.getLogger(JobService.class);
	
	@Step("Creating Inwarrant job with Create job Api")
	public Response createJob(Role role, Object createJobPayload) {
		
		 LOGGER.info("Making request to the {} for the role {} and the payload {}", CREATE_JOB_ENDPOINT, role,createJobPayload);
		Response response =given()
        .spec(requestSpecWithAuth(Role.FD, createJobPayload))
        .when().post(CREATE_JOB_ENDPOINT);
		return response;
	}
	
	@Step("Making search Api request")
   public Response searchJob(Role role, Object payloadObject) {
		
	   LOGGER.info("Seacrhing job  request to the {} with No Auth Token", SEARCH_ENDPOINT);
	   
		Response response =given()
        .spec(requestSpecWithAuth(Role.FD, payloadObject))
        .when().post(SEARCH_ENDPOINT);
		return response;
	}
}

