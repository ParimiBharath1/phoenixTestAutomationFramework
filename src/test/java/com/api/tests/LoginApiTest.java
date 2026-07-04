package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;
import static com.api.utils.ConfigManager.*;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {

	@Test
	public void loginApiTest() throws IOException {

		UserCredentials userCredentials = new UserCredentials("iamfd", "password");

		given().baseUri(getProperty("BASE_URI")).and().contentType(ContentType.JSON).and().accept(ContentType.JSON)
				.and().body(userCredentials).log().uri().log().method().log().headers().log().body().when()
				.post("login").then().statusCode(200).and().body("message", equalTo("Success")).and()
				.time(lessThan(1500L)).and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
				.log().all();

	}

}
