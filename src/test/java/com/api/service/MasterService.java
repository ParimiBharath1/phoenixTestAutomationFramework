package com.api.service;

import static com.api.utils.SpecUtil.requestSpec;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class MasterService {

private static final String MASTER_ENDPOINT = "/master";

private static  Logger  LOGGER = LogManager.getLogger(MasterService.class);
	
    @Step("Making Master Api request")
	public Response master(Role role) {
		
		 LOGGER.info("Making request to the {} for the role {}", MASTER_ENDPOINT, role);
		
	Response response =given()
			   .spec(requestSpecWithAuth(role))
		       .when()
		       .post(MASTER_ENDPOINT);
	
	  return response;
	}
	
    @Step("Making master Api request with No Auth token")
	public Response masterWithNoAuth() {
		 LOGGER.info("Making request to the {} with NO AUTH", MASTER_ENDPOINT);
		Response response =given()
				   .spec(requestSpec())
			       .when()
			       .post(MASTER_ENDPOINT);
		
		  return response;
		}
}
