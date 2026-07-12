package com.api.tests;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
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
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.MapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadModel;
import com.database.model.MapJobProblemDBModel;

import io.restassured.response.Response;

public class CreateJobApiWithDBValidationTest {

	private CreateJobPayload createJobPayload;
	Customer customer;
	CustomerAddress customerAddress;
	CustomerProduct customerProduct;

	@BeforeMethod(description = "Creating Create job api request Payload")
	public void setup() {
		customer = new Customer("Raju", "Kumar", "8900988907", "", "RajuKumar@gmail.com", "");
		customerAddress = new CustomerAddress("2-983", "Raju Enclave", "Raju Road", "Amogha", "Kondapur", "515003",
				"India", "Telangana");
		customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "201908023748287", "201908023748287",
				"201908023748287", getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(), Model.NeEXUS_2_BLUE.getCode());
		Problems problems = new Problems(Problem.POOR_BATTERY_LIFE.getCode(), "Charging Issue");

		List<Problems> problemlist = new ArrayList<Problems>();
		problemlist.add(problems);

		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONT_DESK.getCode(), Warannty_Status.IN_WARRANT.getCode(), Oem.GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemlist);
	}

	@Test(description = "Verifying if the Create Job is Able to create In-Warranty jobs", groups = { "api",
			"regression", "smoke" })
	public void createJobApiTest() {

		Response response = given().spec(requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create")
				.then().spec(responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.mst_service_location_id", equalTo(1))
				.body("data.job_number", startsWith("JOB_")).extract().response();

		int CustomerId = response.body().jsonPath().getInt("data.tr_customer_id");

		CustomerDBModel customerDatafromdb = CustomerDao.getCustomerInfo(CustomerId);

		Assert.assertEquals(customer.first_name(), customerDatafromdb.getFirst_name());
		Assert.assertEquals(customer.last_name(), customerDatafromdb.getLast_name());
		Assert.assertEquals(customer.mobile_number(), customerDatafromdb.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(), customerDatafromdb.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(), customerDatafromdb.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(), customerDatafromdb.getEmail_id_alt());

		CustomerAddressDBModel customerAddressDBModel = CustomerAddressDao
				.getCustomerAddress(customerDatafromdb.getTr_customer_address_id());

		Assert.assertEquals(customerAddress.flat_number(), customerAddressDBModel.getFlat_number());
		Assert.assertEquals(customerAddress.apartment_name(), customerAddressDBModel.getApartment_name());
		Assert.assertEquals(customerAddress.street_name(), customerAddressDBModel.getStreet_name());
		Assert.assertEquals(customerAddress.landmark(), customerAddressDBModel.getLandmark());
		Assert.assertEquals(customerAddress.area(), customerAddressDBModel.getArea());
		Assert.assertEquals(customerAddress.pincode(), customerAddressDBModel.getPincode());
		Assert.assertEquals(customerAddress.country(), customerAddressDBModel.getCountry());
		Assert.assertEquals(customerAddress.state(), customerAddressDBModel.getState());
 	
		
		int tr_job_head_id = response.body().jsonPath().getInt("data.id");
		
		MapJobProblemDBModel mapJobProblemDBModel = MapJobProblemDao.getProblemsInfo(tr_job_head_id);
		
		
		Assert.assertEquals(mapJobProblemDBModel.getMst_problem_id(), createJobPayload.problems().get(0).id());
		Assert.assertEquals(mapJobProblemDBModel.getRemark(), createJobPayload.problems().get(0).remark());
	 
		
		JobHeadModel jobHeadModelfromDB = JobHeadDao.getDataFromJobHead(CustomerId);
		
		Assert.assertEquals(createJobPayload.mst_service_location_id(), jobHeadModelfromDB.getMst_service_location_id());
		Assert.assertEquals(createJobPayload.mst_oem_id(), jobHeadModelfromDB.getMst_oem_id());
		Assert.assertEquals(createJobPayload.mst_platform_id(), jobHeadModelfromDB.getMst_platform_id());
		Assert.assertEquals(createJobPayload.mst_warrenty_status_id(), jobHeadModelfromDB.getMst_warrenty_status_id());
 	
		int productId = response.body().jsonPath().getInt("data.tr_customer_product_id");

		CustomerProductDBModel customerProductDBModel = CustomerProductDao.getCustomerProduct(productId);

		Assert.assertEquals(customerProduct.imei1(), customerProductDBModel.getImei1());
		Assert.assertEquals(customerProduct.imei2(), customerProductDBModel.getImei2());
		Assert.assertEquals(customerProduct.serial_number(), customerProductDBModel.getSerial_number());
		Assert.assertEquals(customerProduct.mst_model_id(), customerProductDBModel.getMst_model_id());
		Assert.assertEquals(customerProduct.popurl(), customerProductDBModel.getPopurl());
		System.out.println("Assertion done");
		//dop will fail bcoz of datemismatch
		Assert.assertEquals(customerProduct.dop(), customerProductDBModel.getDop());
		
		
		
		
		
		

	}

}
