package com.api.service;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	
	private static final String SEARCH_ENDPOINT = "/job/search";
	
	public Response createJob(Role role, Object createJobPayload) {
		
		Response response =given()
        .spec(requestSpecWithAuth(Role.FD, createJobPayload))
        .when().post(CREATE_JOB_ENDPOINT);
		return response;
	}
	
   public Response searchJob(Role role, Object payloadObject) {
		
		Response response =given()
        .spec(requestSpecWithAuth(Role.FD, payloadObject))
        .when().post(SEARCH_ENDPOINT);
		return response;
	}
}

