package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.filter.SensitiveDataFilter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	//GET and DELETE
	public static RequestSpecification requestSpec() {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();
		
				
				return requestSpecification;
		
	}
	
	
	//POST PUT PATCH
    public static RequestSpecification requestSpec(Object payload) {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.build();
		
				
				return requestSpecification;
		
	}
    
    //Request spec with AUTH
 public static RequestSpecification requestSpecWithAuth(Role role) {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.gettoken(role))
				.addFilter(new SensitiveDataFilter())
				.build();
		
				
				return requestSpecification;
		
	}
    
 public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.gettoken(role))
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.build();
		
				
				return requestSpecification;
		
	}
    
     public static ResponseSpecification responseSpec_OK() {
    	 
    	 ResponseSpecification responseSpecification  = new ResponseSpecBuilder()
    	 .expectContentType(ContentType.JSON)
    	 .expectStatusCode(200)
    	 .expectResponseTime(Matchers.lessThan(1000L))
    	 .build();
    	 
    	 return responseSpecification;
    	 
    	 
     }
     
     
  public static ResponseSpecification responseSpec_JSON(int statusCode) {
    	 
    	 ResponseSpecification responseSpecification  = new ResponseSpecBuilder()
    	 .expectContentType(ContentType.JSON)
    	 .expectStatusCode(statusCode)
    	 .expectResponseTime(Matchers.lessThan(1000L))
    	 .build();
    	 
    	 return responseSpecification;
    	 
    	 
     }
  
    
  //content type is not checked here
  public static ResponseSpecification responseSpec_TEXT(int statusCode) {
 	 
 	 ResponseSpecification responseSpecification  = new ResponseSpecBuilder()
 	 .expectStatusCode(statusCode)
 	 .expectResponseTime(Matchers.lessThan(1000L))
 	 .build();
 
 	 return responseSpecification;
 	 
 	 
  }
	
}
