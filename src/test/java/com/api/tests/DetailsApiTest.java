package com.api.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Detail;
import com.api.service.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.ApiTestListener.class)
@Epic("Job Management")
@Feature("Job Details")
public class DetailsApiTest {

	private DashboardService dashboardService;
	private Detail detailPayloaDetail;
	
	@BeforeMethod(description = "Created dashboard service instance and details payload also")
	public void setup() {
		dashboardService = new DashboardService();
		detailPayloaDetail = new Detail("created_today");
	}
	
	@Story("Job details should be shown correctly")
	@Description("Verifying if the jobDetails Api response is shown correctly via Api")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if details Api work properly",groups = {"smoke","regression","sanity"})
	public void detailApiTest() {
		
		 dashboardService.details(Role.FD, detailPayloaDetail)
		 .then()
		 .spec(SpecUtil.responseSpec_OK())
		 .body("message", equalTo("Success"));
		
	}
	
}
