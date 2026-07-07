package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsApiTest {
		
	@Test(description = "Verifying if the UserDetails Api response is shown correct", groups = {"api","regression","smoke"})
	public void userDetailsApiTest() throws IOException {
		
		 given()
		 .spec(requestSpecWithAuth(FD))
         .when()
         .get("userdetails")
         .then()
         .spec(responseSpec_OK())
         .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
         .body("message", equalTo("Success"));
        	}

}
