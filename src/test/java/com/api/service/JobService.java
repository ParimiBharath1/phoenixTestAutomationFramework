package com.api.service;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_JOB_ENDPOINT = "/job/create";
	
	
	public Response createJob(Role role, Object createJobPayload) {
		
		Response response =given()
        .spec(requestSpecWithAuth(Role.FD, createJobPayload))
        .when().post(CREATE_JOB_ENDPOINT);
		return response;
	}
}
