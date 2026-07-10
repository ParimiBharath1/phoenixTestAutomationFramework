package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.dataprovider.api.bean.UserBean;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginApiDataDriven {
	
	 
	@Test(description = "Verifying if login Api is working for FD user", 
		groups = {"api","regression","smoke"},
		dataProviderClass = com.dataprovider.DataProviderUtils.class,
		dataProvider = "LoginApiDataProvider")
	public void loginApiTest(UserBean userbean) {

	

		given()
		  .spec(requestSpec(userbean))
		  .when()
		  .post("login")
		  .then()
		  .spec(responseSpec_OK())
		  .body("message", equalTo("Success"))
		  .body( matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
				 

	}

}
