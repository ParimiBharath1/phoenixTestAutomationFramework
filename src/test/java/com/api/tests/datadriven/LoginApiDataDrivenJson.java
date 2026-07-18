package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.service.AuthService;
import com.dataprovider.api.bean.UserBean;

public class LoginApiDataDrivenJson {
	
   private AuthService authService;
	
	@BeforeMethod
	public void setup() {
		authService = new AuthService();
	}
	
	 
	@Test(description = "Verifying if login Api is working for FD user", 
		groups = {"api","regression","smoke"},
		dataProviderClass = com.dataprovider.DataProviderUtils.class,
		dataProvider = "UserCredentialsProvider")
	public void loginApiTest(UserBean userCredentials) {

	

		   authService.login(userCredentials)
		  .then()
		  .spec(responseSpec_OK())
		  .body("message", equalTo("Success"))
		  .body( matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
				 

	}

}
