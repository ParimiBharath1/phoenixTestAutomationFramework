package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {

		Customer customer = new Customer("Raju", "Kumar", "8900988907", "", "RajuKumar@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("2-983", "Raju Enclave", "Raju Road", "Amogha",
				"Kondapur", "515003", "India", "Telangana");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "102046023748292",
				"102046023748292", "102046023748292", getTimeWithDaysAgo(10), 1, 1);
		Problems problems = new Problems(2, "Charging Issue");

		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);

		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 2, 1, customer, customerAddress, customerProduct,
				problemlist);

		given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}

}
