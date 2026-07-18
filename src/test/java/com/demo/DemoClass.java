package com.demo;

 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoClass {
	
	private static  Logger  logger = LogManager.getLogger(DemoClass.class);
			
		 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		logger.info("Inside the main method");
		
		int a=10;
	 
		logger.info("value of a {}", a);
		int b=0;
		if(b==0) {
			logger.warn("b value is {}",b);
		}
		
		try {
		int result=a/b;
		}
		catch (ArithmeticException e) {
			// TODO: handle exception
			logger.error("Operation cananot happen", e.getMessage());
		}
		
		 

	}

}
