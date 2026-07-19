package com.api.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	
	private static  Logger  LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		// TODO Auto-generated method stub
		 System.out.println("-----------------Hello from filter----------------------");
		 readactPayload(requestSpec);

		 Response response = ctx.next(requestSpec, responseSpec);
		 
		 readactResponsebody(response);
		 System.out.println("-----------------BEFORE Response filter----------------------");
		return response;
	}
	
	public void readactPayload(FilterableRequestSpecification requestSpec) {
		
		String requestPayload =  requestSpec.getBody().toString();
		
		   requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"",  "\"password\":\"[REDACTED]\"");
		   
		   LOGGER.info("Request Body {}",requestPayload);
	}
	
	public void readactResponsebody(Response response) {
		String responseBody =  response.asPrettyString();
		
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"",  "\"token\":\"[REDACTED]\"");
		   
		  LOGGER.info("Response Body {}",responseBody);
	 
		
		
	}

}
