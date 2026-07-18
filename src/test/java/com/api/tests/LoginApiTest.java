package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.service.AuthService;
import com.dataprovider.api.bean.UserBean;

public class LoginApiTest {

	private UserBean userCredentials;
	private AuthService authService;

	@BeforeMethod(description = "Create the Payload for the loginApi")
	public void setup() {
		userCredentials = new UserBean("iamfd", "password");
		authService = new AuthService();
	}

	@Test(description = "Verifying if login Api is working for FD user", groups = { "api", "regression", "smoke" })
	public void loginApiTest() throws IOException {

		authService.login(userCredentials).then().spec(responseSpec_OK()).body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}
