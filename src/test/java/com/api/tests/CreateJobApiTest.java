package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.Oem;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.Warannty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

	 


public class CreateJobApiTest {
	
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "Creating Create job api request Payload")
	 public void setup() {
		Customer customer = new Customer("Raju", "Kumar", "8900988907", "", "RajuKumar@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("2-983", "Raju Enclave", "Raju Road", "Amogha",
				"Kondapur", "515003", "India", "Telangana");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "189646023748292",
				"189646023748292", "189646023748292", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
				Model.NeEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.POOR_BATTERY_LIFE.getCode(), "Charging Issue");

		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);
		
		 createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONT_DESK.getCode(), Warannty_Status.IN_WARRANT.getCode(), Oem.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemlist);
	 }
	 

	@Test(description = "Verifying if the Create Job is Able to create In-Warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {

	
		given().spec(requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}

}
