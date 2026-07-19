package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Search;
import com.api.service.JobService;
import com.api.utils.SpecUtil;

@Listeners(com.listeners.ApiTestListener.class)
public class SearchApiTest {

	private JobService  jobService;
	private  String jobNumber = "JOB_358209";
	private Search searchpayload;
	
	@BeforeMethod(description = "Created job service instance and search payload also")
	public void setup() {
		jobService = new JobService();
		searchpayload = new Search(jobNumber);
	}
	
	@Test(description = "Job service Api working verification")
	public void searchApiTest() {
		
		  jobService.searchJob(Role.FD, searchpayload)
		 .then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body("message", equalTo("Success"));
		
	}
	
}
