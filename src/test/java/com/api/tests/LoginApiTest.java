package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	
	@Test
	public void loginApiTest() {
		
		  UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		
	
	      given()
	         .baseUri("http://64.227.160.186:9000/v1")
	         .and()
	         .contentType(ContentType.JSON)
	         .and()
	         .accept(ContentType.JSON)
	         .and()
	         .body(userCredentials)
	         .log().uri()
	         .log().method()
	         .log().headers()
	         .log().body()
	         .when()
	         .post("login")
	         .then()
	          .statusCode(200)
	          .and()
	          .body("message",  equalTo("Success"))
	          .and()
	          .time(lessThan(1500L))
	          .and()
	          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
	          .log().all();
	         
		
		
		
		
	}

}
