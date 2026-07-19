package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static com.api.utils.SpecUtil.responseSpec_TEXT;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import  org.testng.annotations.Test;

import com.api.service.MasterService;

@Listeners(com.listeners.ApiTestListener.class)
public class MasterApiTest {
	
	private MasterService masterService;
	
	@BeforeMethod(description = "Creating master service Insatnce")
	public void setup() {
		 masterService = new MasterService();
	}
	
	@Test(description = "Verifying if the MasterApi is giving correct response", groups = {"api","regression","smoke"})
	public void masterAPITest() {
		
		    masterService.master(FD)
	       .then()
	       .log().all()
	       .log().ifValidationFails()
	       .spec(responseSpec_OK())
	       .body("message", equalTo("Success"))
	       .body("data", notNullValue())
	       .body("data", hasKey("mst_oem"))
	       .body("data", hasKey("mst_model"))
	       .body("$", hasKey("message"))
	       .body("$", hasKey("data"))
	       .body("data.mst_oem.size()", equalTo(2))
	       .body("data.mst_model.size()", greaterThan(0))
	       .body("data.mst_oem.id", everyItem(notNullValue()))
	       .body(matchesJsonSchemaInClasspath("response-schema/MasterApiResponseSchema.json"));
		
		
	}
	
	@Test(description = "Verifying if the MasterApi is giving correct status code for invalid token", groups = {"api","negative","regression","smoke"})
	public void InvalidTokenMasterApi() {
		
		    masterService.masterWithNoAuth()
	       .then()
	       .spec(responseSpec_TEXT(401));
		
	       
		
	}

}
