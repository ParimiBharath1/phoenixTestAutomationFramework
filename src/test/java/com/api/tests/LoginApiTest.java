package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {

	@Test
	public void loginApiTest() throws IOException {

		UserCredentials userCredentials = new UserCredentials("iamfd", "password");

		given()
		  .spec(SpecUtil.requestSpec(userCredentials))
		  .when()
		  .post("login")
		  .then()
		  .spec(SpecUtil.responseSpec_OK())
		  .body("message", equalTo("Success"))
		  .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
				 

	}

}
