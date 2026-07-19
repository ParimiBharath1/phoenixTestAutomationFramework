package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.service.UserService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.ApiTestListener.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsApiTest {
	
	private UserService userService;
	
	@BeforeMethod
	public void setup() {
		userService = new UserService();
	}
    
	@Story("User details should be shown")
	@Description("Verifying if the UserDetails Api response is shown correctly via Api")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying if the UserDetails Api response is shown correct", groups = {"api","regression","smoke"})
	public void userDetailsApiTest() throws IOException {
		
		  userService.userDetails(FD)
         .then()
         .spec(responseSpec_OK())
         .body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"))
         .body("message", equalTo("Success"));
        	}

}
