package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountApiTest {
	
	@Test(description = "Verifying if the Count Api response is shown correct", groups = {"api","regression","smoke"})
	public void verifyCountApiResponse() {
		
		   given()
		       .spec(requestSpecWithAuth(FD))
		       .when()
		       .get("/dashboard/count")
		       .then()
		       .log().ifValidationFails()
		       .spec(responseSpec_OK())
		       .body("message", equalTo("Success"))       
		       .body("data", notNullValue())
		       .body("data.size()", equalTo(3))
		       .body("data.count", everyItem(greaterThanOrEqualTo(0)))
		       .body("data.label", not(blankOrNullString()))
		       .body(matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema-FD.json"))
		       .body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"));
		       
		
	}
	
	@Test(description = "Verifying if the CountApi is giving correct status code for invalid token", groups = {"api","neagative","regression","smoke"})
	public void countApi_MissingAuthToken() {
		
		 given()
	       .spec(requestSpec())
	       .and()
	       .when()
	       .get("/dashboard/count")
	       .then()
	       .spec(responseSpec_TEXT(401));
		
	}
	

}
