package com.api.service;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;

import io.restassured.response.Response;

public class MasterService {

private static final String MASTER_ENDPOINT = "/master";
	
	
	public Response master(Role role) {
		
	Response response =given()
			   .spec(requestSpecWithAuth(role))
		       .when()
		       .post(MASTER_ENDPOINT);
	
	  return response;
	}
	
	public Response masterWithNoAuth() {
		
		Response response =given()
				   .spec(requestSpec())
			       .when()
			       .post(MASTER_ENDPOINT);
		
		  return response;
		}
}
