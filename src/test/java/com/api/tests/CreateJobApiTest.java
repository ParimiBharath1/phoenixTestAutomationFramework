package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojos.CreateJobPayload;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problems;
import com.api.utils.SpecUtil;

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {
		
		Customer customer = new Customer("Raju", "Kumar", "8900988907", "", "RajuKumar@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("2-983", "Raju Enclave", "Raju Road", "Amogha", "Kondapur", "515003", "India", "Telangana");
		CustomerProduct customerProduct = new CustomerProduct("2024-10-15T18:30:00.000Z", "102046823647256", "102046823647256", "102046823647256", "2024-10-15T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(2,"Charging Issue");
		
		 Problems[] problemsArray = new Problems[1];
         problemsArray[0] = problems;		
		 
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 2, 1, customer, customerAddress, customerProduct, problemsArray);
		
	      given()
	      .spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
	     .when()
	     .post("/job/create")
	     .then()
	     .spec(SpecUtil.responseSpec_OK());
	     
		
	}

}
