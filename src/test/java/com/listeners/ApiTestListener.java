package com.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ApiTestListener implements  ITestListener{
	
	private static  Logger  LOGGER = LogManager.getLogger(ApiTestListener.class);
 
	public  void onTestStart(ITestResult result) {
	  
		LOGGER.info("******************************************************************************");
		LOGGER.info("===============Starting the Test {}====================",result.getName());
		LOGGER.info("TestClass {} ", result.getMethod().getTestClass());
		LOGGER.info("Description {} ", result.getMethod().getDescription());
		LOGGER.info("Groups {}", Arrays.toString(result.getMethod().getGroups()));
		LOGGER.info("******************************************************************************");
	  }
	
	 public void onTestSuccess(ITestResult result) {
         
		 long startTime = result.getStartMillis();
		 long endTime = result.getEndMillis();
		 
		 LOGGER.info("Total Duration:{}",(endTime-startTime));
		 LOGGER.info("{} Test Passed!!!",result.getName());
		
		  }
	 
	 public void onTestFailure(ITestResult result) {
		 LOGGER.error("{} Test Failed!!!",result.getName());
		 LOGGER.error("Error Message", result.getThrowable().getMessage());
		 LOGGER.error(result.getThrowable());
		  }
	 
	 public void onTestSkipped(ITestResult result) {
		 LOGGER.warn("{} Test Skipped",result.getName());
		 LOGGER.error(result.getThrowable());
		  }
	 
	  public void onStart(ITestContext context) {
		  LOGGER.warn("*********************STARTING THE PHOENIX FRAMEWORK*******************************");
		  }
 
		  public void onFinish(ITestContext context) {
			  LOGGER.warn("*********************FINISHED******************************");
		  }


}
