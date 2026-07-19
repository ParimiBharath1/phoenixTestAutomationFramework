package com.api.service;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserService {
	
	private static final String USER_ENDPOINT = "/userdetails";
	
	private static  Logger  LOGGER = LogManager.getLogger(UserService.class);
	
	@Step("Making user Details Api request")
	public Response userDetails(Role role) {
		
		 LOGGER.info("Making request to the {} for the role {}", USER_ENDPOINT, role);
		
	Response response =given()
		 .spec(requestSpecWithAuth(role))
        .when()
        .get(USER_ENDPOINT);
	
	  return response;
	}

}
