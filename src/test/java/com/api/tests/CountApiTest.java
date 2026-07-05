package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class CountApiTest {
	
	@Test
	public void verifyCountApiResponse() {
		
		   given()
		       .baseUri(getProperty("BASE_URI"))
		       .and()
		       .header("Authorization", gettoken(FD))
		       .log().uri()
		       .log().method()
		       .log().headers()
		       .when()
		       .get("/dashboard/count")
		       .then()
		       .log().ifValidationFails()
		       .statusCode(200)
		       .body("message", equalTo("Success"))
		       .time(lessThan(1500L))
		       .body("data", notNullValue())
		       .body("data.size()", equalTo(3))
		       .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		       .body("data.label", not(blankOrNullString()))
		       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema-FD.json"))
		       .body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		       
		
	}
	
	@Test
	public void countApi_MissingAuthToken() {
		
		 given()
	       .baseUri(getProperty("BASE_URI"))
	       .log().uri()
	       .log().method()
	       .and()
	       .when()
	       .get("/dashboard/count")
	       .then()
	       .log().all()
	       .statusCode(401)
	       .log().all();
		
	}
	

}
