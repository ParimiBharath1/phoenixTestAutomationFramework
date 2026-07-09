package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;

import static com.api.utils.DateTimeUtil.*;
import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

	 


public class CreateJobApiTest2 {
	
	private CreateJobPayload createJobPayload;
	 private static final String COUNTRY="INDIA";
	
	@BeforeMethod(description = "Creating Create job api request Payload")
	 public void setup() {
	Locale locale = new Locale("en-IND");
		
		Faker fakerDemo = new Faker(locale);
		
		String fname = fakerDemo.name().firstName();
		String lname = fakerDemo.name().lastName();
		String mobilnumber = fakerDemo.numerify("765#######");
		String email = fakerDemo.internet().emailAddress();
		
		
		
		Customer customer = new Customer(fname, lname, mobilnumber, "", email, "");
		
		System.out.println(customer);
		
		String flat_number = fakerDemo.numerify(("1-40##"));
		String apartment_name = fakerDemo.address().streetName();
		String street_name = fakerDemo.address().streetName();
		String landmark = fakerDemo.address().streetName();
		String area = fakerDemo.address().streetName();
		String pincode = fakerDemo.numerify("#######") ;
		String state = fakerDemo.address().state();
		
		
		CustomerAddress customerAddress = new  CustomerAddress(flat_number, apartment_name, street_name,
				landmark, area, pincode, COUNTRY, state);
		System.out.println(customerAddress);
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		
		String imenum = fakerDemo.numerify("##############");
		
		String popurl = DateTimeUtil.getTimeWithDaysAgo(10);
			
		CustomerProduct customerProduct = new CustomerProduct(dop, imenum, imenum, imenum, popurl, 1, 1);
		
		System.out.println(customerProduct);
		
		
		//random generate 
		
		Random random = new Random();
				
	      int problem= random.nextInt(27)+1;
		
		String remarkString = fakerDemo.lorem().sentence(5);
		Problems problems = new Problems(problem, remarkString);
		
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problems);
		
		System.out.println(problemList);
		
		createJobPayload = new CreateJobPayload(0, 2, 2, 1, customer, customerAddress, customerProduct, problemList);
		 
		 System.out.println(createJobPayload);
		
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
