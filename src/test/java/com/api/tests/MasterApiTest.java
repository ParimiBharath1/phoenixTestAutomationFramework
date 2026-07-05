package com.api.tests;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;
public class MasterApiTest {
	
	@Test
	public void masterAPITest() {
		
		given()
	       .baseUri(getProperty("BASE_URI"))
	       .and()
	       .header("Authorization", gettoken(FD))
	       .and()
	       .contentType("")
	       .log().uri()
	       .log().method()
	       .log().headers()
	       .when()
	       .post("master")
	       .then()
	       .log().all()
	       .log().ifValidationFails()
	       .statusCode(200)
	       .body("message", equalTo("Success"))
	       .time(lessThan(1000L))
	       .body("data", notNullValue())
	       .body("data", hasKey("mst_oem"))
	       .body("data", hasKey("mst_model"))
	       .body("$", hasKey("message"))
	       .body("$", hasKey("data"))
	       .body("data.mst_oem.size()", equalTo(2))
	       .body("data.mst_model.size()", greaterThan(0))
	       .body("data.mst_oem.id", everyItem(notNullValue()))
	       .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterApiResponseSchema.json"));
		
		
	}
	
	@Test
	public void InvalidTokenMasterApi() {
		
		given()
	       .baseUri(getProperty("BASE_URI"))
	       .and()
	       .header("Authorization", "")
	       .and()
	       .contentType("")
	       .log().uri()
	       .log().method()
	       .log().headers()
	       .when()
	       .post("master")
	       .then()
	       .log().all()
	       .statusCode(401);
		
		
	}

}
