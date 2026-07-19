package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Search;
import com.api.service.JobService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.ApiTestListener.class)
@Epic("Job Management")
@Feature("Search Api")
public class SearchApiTest {

	private JobService  jobService;
	private  String jobNumber = "JOB_358209";
	private Search searchpayload;
	
	@BeforeMethod(description = "Created job service instance and search payload also")
	public void setup() {
		jobService = new JobService();
		searchpayload = new Search(jobNumber);
	}
	
	@Story("Search Api should bring the required job data")
	@Description("Job search Api working verification via Api")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Job search Api working verification")
	public void searchApiTest() {
		
		  jobService.searchJob(Role.FD, searchpayload)
		 .then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body("message", equalTo("Success"));
		
	}
	
}
