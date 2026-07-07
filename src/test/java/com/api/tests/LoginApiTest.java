package com.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginApiTest {
	
	private UserCredentials userCredentials;
	@BeforeMethod(description = "Create the Payload for the loginApi")
	public void setup() {
		 userCredentials = new UserCredentials("iamfd", "password");
	}
	
	
	

	@Test(description = "Verifying if login Api is working for FD user", groups = {"api","regression","smoke"})
	public void loginApiTest() throws IOException {

	

		given()
		  .spec(requestSpec(userCredentials))
		  .when()
		  .post("login")
		  .then()
		  .spec(responseSpec_OK())
		  .body("message", equalTo("Success"))
		  .body( matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
				 

	}

}
