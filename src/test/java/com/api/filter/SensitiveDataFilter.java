package com.api.filter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	
	private static  Logger  LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		// TODO Auto-generated method stub
		 LOGGER.info("******************************REQUEST DETAILS********************************");
		 LOGGER.info("BASE URI: {}",requestSpec.getURI());
		 LOGGER.info("HTTP METHOD: {}",requestSpec.getMethod());
//		 LOGGER.info("REQUEST HEADERS: \n {}",requestSpec.getHeaders());
		 readactHeader(requestSpec);
		 readactPayload(requestSpec);
		 Response response = ctx.next(requestSpec, responseSpec);	 
		 LOGGER.info("******************************RESPONSE DETAILS********************************");
		 LOGGER.info("Get Status Line : {}",response.getStatusLine());
		 LOGGER.info("RESPONSE TIME ms: {}",response.timeIn(TimeUnit.MILLISECONDS));
		 LOGGER.info("RESPONSE HEADERS: \n {}",response.getHeaders());
		 readactResponsebody(response);
		
		return response;
	}
	
	private void readactHeader(FilterableRequestSpecification requestSpec) {
		// TODO Auto-generated method stub
		
		    List<Header> headerList = requestSpec.getHeaders().asList();
		    
		    for(Header h:headerList) {
		    	if(h.getName().equalsIgnoreCase("Authorization")) {
		    		LOGGER.info("HEADER {} : {}",h.getName(),"\"[REDACTED]\"");
		    	}
		    	else {
		    		LOGGER.info("HEADER {} : {}",h.getName(),h.getValue());
		    	}
		    }
		
		
		
	}

	private void readactPayload(FilterableRequestSpecification requestSpec) {
		
		if(requestSpec.getBody()!=null) {
			//POST PUT AND DELETE WHERE ONLY BODY IS AVAILABLE 
		String requestPayload =  requestSpec.getBody().toString();
		
		   requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"",  "\"password\":\"[REDACTED]\"");
		   
		   LOGGER.info("Request Body: \n {}",requestPayload);
		}
	}
	
	private void readactResponsebody(Response response) {
		String responseBody =  response.asPrettyString();
		
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"",  "\"token\":\"[REDACTED]\"");
		   
		  LOGGER.info("Response Body: \n {}",responseBody);
	 
		
		
	}

}
