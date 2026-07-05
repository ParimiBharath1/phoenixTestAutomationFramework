package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserDetailsApiTest {
	
	Header  autHeader = new Header("Authorization", gettoken(FD));
	
	@Test
	public void userDetailsApiTest() throws IOException {
		
		 given()
		 .baseUri(getProperty("BASE_URI"))
         .and()
         .header(autHeader)
         .and()
         .contentType(ContentType.JSON)
         .and()
         .accept(ContentType.JSON)
         .and()
         .log().uri()
         .log().method()
         .log().headers()
         .when()
         .get("userdetails")
         .then()
         .statusCode(200)
         .time(lessThan(1500L))
         .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
         .body("message", equalTo("Success"))
         .log().all();
		 
		
	}

}
