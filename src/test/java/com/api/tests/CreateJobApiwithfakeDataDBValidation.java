package com.api.tests;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.JobHeadDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.JobHeadModel;

	 


public class CreateJobApiwithfakeDataDBValidation {
	
	private CreateJobPayload createJobPayload;
 
	
	@BeforeMethod(description = "Creating Create job api request Payload")
	 public void setup() {
	 
		createJobPayload = FakerDataGenerator.createFakeCreateJobdata();
	 }
	 

	@Test(description = "Verifying if the Create Job is Able to create In-Warranty jobs", groups = { "api", "regression",
			"smoke" })
	public void createJobApiTest() {

	
		 Integer CustomerId =given().spec(requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(responseSpec_OK()).body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_"))
				.extract().body().jsonPath().getInt("data.tr_customer_id");
		
		     Customer expectedCustomer = createJobPayload.customer();
		     
		     CustomerDBModel customerActual = CustomerDao.getCustomerInfo(CustomerId);
		     
		        Assert.assertEquals(expectedCustomer.first_name(), customerActual.getFirst_name());
		        Assert.assertEquals(expectedCustomer.last_name(), customerActual.getLast_name());
		        Assert.assertEquals(expectedCustomer.mobile_number(), customerActual.getMobile_number());
		        Assert.assertEquals(expectedCustomer.mobile_number_alt(), customerActual.getMobile_number_alt());
		        Assert.assertEquals(expectedCustomer.email_id(), customerActual.getEmail_id());
		        Assert.assertEquals(expectedCustomer.email_id_alt(), customerActual.getEmail_id_alt());
		        
		        CustomerAddress expeCustomerAddress = createJobPayload.customer_address();
		        
		     CustomerAddressDBModel customerAddressActual = CustomerAddressDao.getCustomerAddress(customerActual.getTr_customer_address_id());
		     
		     
		     Assert.assertEquals(expeCustomerAddress.flat_number(), customerAddressActual.getFlat_number());
		     Assert.assertEquals(expeCustomerAddress.apartment_name(), customerAddressActual.getApartment_name());
		     Assert.assertEquals(expeCustomerAddress.street_name(), customerAddressActual.getStreet_name());
		     Assert.assertEquals(expeCustomerAddress.landmark(), customerAddressActual.getLandmark());	     
		     Assert.assertEquals(expeCustomerAddress.area(), customerAddressActual.getArea());
		     Assert.assertEquals(expeCustomerAddress.pincode(), customerAddressActual.getPincode());
		     Assert.assertEquals(expeCustomerAddress.country(), customerAddressActual.getCountry());
		     Assert.assertEquals(expeCustomerAddress.state(), customerAddressActual.getState());
		     
		 	JobHeadModel jobHeadModelfromDB = JobHeadDao.getDataFromJobHead(CustomerId);
			
			
			Assert.assertEquals(createJobPayload.mst_oem_id(), jobHeadModelfromDB.getMst_oem_id());
			Assert.assertEquals(createJobPayload.mst_platform_id(), jobHeadModelfromDB.getMst_platform_id());
			Assert.assertEquals(createJobPayload.mst_warrenty_status_id(), jobHeadModelfromDB.getMst_warrenty_status_id());
			   System.out.println("Assertion done");
			//service location issue with 0 and 1 random generation
			//Assert.assertEquals(createJobPayload.mst_service_location_id(), jobHeadModelfromDB.getMst_service_location_id());
	 	
		     
		  
		     
	}

}
