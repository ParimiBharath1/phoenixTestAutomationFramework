package com.api.tests;

import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.service.JobService;
import com.api.utils.FakerDataGenerator;

	 


public class CreateJobApiTestwithfakeData {
	
	private CreateJobPayload createJobPayload;
	private JobService jobService;
 
	
	@BeforeMethod(description = "Creating Create job api request Payload")
	 public void setup() {
	 
		createJobPayload = FakerDataGenerator.createFakeCreateJobdata();
		jobService = new JobService();
	 }
	 

	@Test(description = "Verifying if the Create Job is Able to create In-Warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {

	
		         jobService.createJob(Role.FD, createJobPayload).then()
				.spec(responseSpec_OK()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}

}
