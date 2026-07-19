package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.service.AuthService;
import com.dataprovider.api.bean.UserBean;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.ApiTestListener.class)
@Epic("User Management")
@Feature("Authentication")
public class LoginApiTest {

	private UserBean userCredentials;
	private AuthService authService;

	@BeforeMethod(description = "Create the Payload for the loginApi")
	public void setup() {
		userCredentials = new UserBean("iamfd", "password");
		authService = new AuthService();
	}

	@Story("Valid user should be able to login to system")
	@Description("Verify the FD user is able to login via Api")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if login Api is working for FD user", groups = { "api", "regression", "smoke" })
	public void loginApiTest() throws IOException {

		authService.login(userCredentials).then().spec(responseSpec_OK()).body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}
