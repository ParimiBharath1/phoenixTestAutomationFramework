package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import com.api.service.JobService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

	 

@Listeners(com.listeners.ApiTestListener.class)
@Epic("Job Management")
@Feature("Job Creation")
public class CreateJobApiTest {
	
	private CreateJobPayload createJobPayload;
	private JobService jobService;
	
	@BeforeMethod(description = "Creating Create job api request Payload and instanciating jobservice")
	 public void setup() {
		Customer customer = new Customer("Raju", "Kumar", "8900988907", "", "RajuKumar@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("2-983", "Raju Enclave", "Raju Road", "Amogha",
				"Kondapur", "515003", "India", "Telangana");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "654646023748292",
				"654646023748292", "654646023748292", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
				Model.NeEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.POOR_BATTERY_LIFE.getCode(), "Charging Issue");

		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);
		
		 createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONT_DESK.getCode(), Warannty_Status.IN_WARRANT.getCode(), Oem.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemlist);
		 
		 jobService = new JobService();
	 }
	 

	@Story("FD should be able to craeate Job")
	@Description("Verifying if the FD is able to create inwarranty jobs via Api")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Verifying if the Create Job is Able to create In-Warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {

	
		         jobService.createJob(Role.FD, createJobPayload)
		        .then()
				.spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"));

	}

}
