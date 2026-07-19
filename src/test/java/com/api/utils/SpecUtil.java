package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.filter.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	//GET and DELETE
	@Step("Setting up the BaseURI, Content Type and attaching the SensitiveDataFilter")
	public static RequestSpecification requestSpec() {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();
		
				
				return requestSpecification;
		
	}
	
	
	//POST PUT PATCH
	@Step("Setting up the BaseURI, Content Type,Payload and attaching the SensitiveDataFilter")
    public static RequestSpecification requestSpec(Object payload) {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();
		
				
				return requestSpecification;
		
	}
    
    //Request spec with AUTH
	@Step("Setting up the BaseURI, Content Type and attaching the SensitiveDataFilter for role")
 public static RequestSpecification requestSpecWithAuth(Role role) {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.gettoken(role))
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();
		
				
				return requestSpecification;
		
	}
    
	@Step("Setting up the BaseURI, Content Type,Payload and attaching the SensitiveDataFilter for role")
 public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
		
		
		RequestSpecification requestSpecification = new  RequestSpecBuilder()
				.setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.gettoken(role))
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())
				.build();
		
				
				return requestSpecification;
		
	}
    
	@Step("Expecting the  response to have Content Type as Application/json, statuscode as 200  and  Response time as less than 1000ms")
     public static ResponseSpecification responseSpec_OK() {
    	 
    	 ResponseSpecification responseSpecification  = new ResponseSpecBuilder()
    	 .expectContentType(ContentType.JSON)
    	 .expectStatusCode(200)
    	 .expectResponseTime(Matchers.lessThan(1000L))
    	 .build();
    	 
    	 return responseSpecification;
    	 
    	 
     }
     
	@Step("Expecting the  response to have Content Type as Application/json, statuscode  as customized  and  Response time as less than 1000ms")
  public static ResponseSpecification responseSpec_JSON(int statusCode) {
    	 
    	 ResponseSpecification responseSpecification  = new ResponseSpecBuilder()
    	 .expectContentType(ContentType.JSON)
    	 .expectStatusCode(statusCode)
    	 .expectResponseTime(Matchers.lessThan(1000L))
    	 .build();
    	 
    	 return responseSpecification;
    	 
    	 
     }
  
    
  //content type is not checked here
	@Step("Expecting the  response to have  Content Type as Text, statuscode as customized  and  Response time as less than 1000ms")
  public static ResponseSpecification responseSpec_TEXT(int statusCode) {
 	 
 	 ResponseSpecification responseSpecification  = new ResponseSpecBuilder()
 	 .expectStatusCode(statusCode)
 	 .expectResponseTime(Matchers.lessThan(1000L))
 	 .build();
 
 	 return responseSpecification;
 	 
 	 
  }
	
}
