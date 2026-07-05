package com.api.utils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import static com.api.utils.ConfigManager.*;

import static com.api.constants.Role.*;

import com.api.constants.Role;
//import static com.api.utils.ConfigManager.*;
import com.api.pojos.UserCredentials;

import io.restassured.http.ContentType;


public class AuthTokenProvider {

	
	 private AuthTokenProvider() {
		 //Private cosntructor
	 }
	
	public static String gettoken(Role role) {
		  
	 

			UserCredentials userCredentials = null;
			
			if(role == FD) {
				userCredentials = new UserCredentials("iamfd", "password");			
			}
			else if(role == SUP) {
				userCredentials = new UserCredentials("iamsup", "password");			
			}
			else if(role == ENG) {
				userCredentials = new UserCredentials("iameng", "password");			
			}
			else if(role == QC) {
				userCredentials = new UserCredentials("iamqc", "password");			
			}
					
		

			String token =  given()
					.baseUri(getProperty("BASE_URI"))
					.and().
					contentType(ContentType.JSON)
					.and()
					.accept(ContentType.JSON)
					.and()
					.body(userCredentials)
					.log().uri().log().method().log().headers().log().body()
					.when()
					.post("login")
					.then()
					.log().ifValidationFails().statusCode(200).and().body("message", equalTo("Success"))
					
					
					.extract().body().jsonPath().getString("data.token");

		
		
		return token;
	}
}
