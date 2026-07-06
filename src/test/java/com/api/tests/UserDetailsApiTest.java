package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailsApiTest {
		
	@Test
	public void userDetailsApiTest() throws IOException {
		
		 given()
		 .spec(SpecUtil.requestSpecWithAuth(FD))
         .when()
         .get("userdetails")
         .then()
         .spec(SpecUtil.responseSpec_OK())
         .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
         .body("message", equalTo("Success"));
        	}

}
