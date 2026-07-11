package com.api.tests.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;

	 


public class CreateJobApiDBDataDriven {
	
	
 
	@Test(description = "Verifying if the Create Job is Able to create In-Warranty jobs", groups = { "api", "regression","smoke","csv" },
			dataProviderClass = com.dataprovider.DataProviderUtils.class,
			dataProvider = "CreateJobAPIDBDataProvider" )
		   
	public void createJobApiTest(CreateJobPayload createJobPayload) {

	
		given().spec(requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}

}
