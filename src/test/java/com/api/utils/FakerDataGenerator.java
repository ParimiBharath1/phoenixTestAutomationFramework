package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;

public class FakerDataGenerator {
	
	private FakerDataGenerator() {
		//utilty design pattern 
	}
	
	private static   Faker faker = new Faker(new Locale("en-IND"));
	 private static final String COUNTRY="INDIA";
	 private static final Random RANDOM = new Random();
	 private static final int MST_SERVICE_LOCATION= 0;
	 private static final int MST_PLATFORM_ID= 2;
	 private static final int MST_WARRENTY_STATIS_ID= 2;
	 private static final int MST_OEM_ID= 1;
	 private static final int PRODUCT_ID= 1;
	 private static final int MST_MODEL_ID= 1;
	 
	 private static  final int VALIDPROMBLEMID[] = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,22,24,26,27,28,29};
	
	
	public static CreateJobPayload createFakeCreateJobdata() {
		
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generatFakeCustomerAddress();
		CustomerProduct customerProduct = getFakeCustomerProduct();
		List<Problems> problemList =getFakeProblemList();
		
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION, MST_PLATFORM_ID, MST_WARRENTY_STATIS_ID, MST_OEM_ID, customer,
				customerAddress, customerProduct, problemList);
		
		return payload;
			
	}
	
	public static Iterator<CreateJobPayload> createFakeCreateJobdata(int count) {
		
		 List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		  
		 
		for(int i=0;i<count;i++) {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generatFakeCustomerAddress();
		CustomerProduct customerProduct = getFakeCustomerProduct();
		List<Problems> problemList =getFakeProblemList();
		
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION, MST_PLATFORM_ID, MST_WARRENTY_STATIS_ID, MST_OEM_ID, customer,
				customerAddress, customerProduct, problemList);
		
		payloadList.add(payload);
		 
		}
		
		return payloadList.iterator();
			
	}


private static List<Problems> getFakeProblemList() {
		// TODO Auto-generated method stub
	
      int randomindex= RANDOM.nextInt(VALIDPROMBLEMID.length);
	
	String remarkString = faker.lorem().sentence(5);
	Problems problems = new Problems(randomindex, remarkString);
	
	List<Problems> problemList = new ArrayList<Problems>();
	
	problemList.add(problems);
		return problemList;
	}


	private static CustomerProduct getFakeCustomerProduct() {
		// TODO Auto-generated method stub
String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		
		String imenum = faker.numerify("##############");
		
		String popurl = DateTimeUtil.getTimeWithDaysAgo(10);
			
		CustomerProduct customerProduct = new CustomerProduct(dop, imenum, imenum, imenum, popurl, PRODUCT_ID,MST_MODEL_ID);
		
		return customerProduct;
	}


	private static CustomerAddress generatFakeCustomerAddress() {
		// TODO Auto-generated method stub
		String flat_number = faker.numerify(("1-40##"));
		String apartment_name = faker.address().streetName();
		String street_name = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("#######") ;
		String state = faker.address().state();
		
		
		CustomerAddress customerAddress = new  CustomerAddress(flat_number, apartment_name, street_name,
				landmark, area, pincode, COUNTRY, state);
		return customerAddress;
	}


	private static Customer generateFakeCustomerData() {
		// TODO Auto-generated method stub
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobilnumber = faker.numerify("765#######");
		String email = faker.internet().emailAddress();
			
		Customer customer = new Customer(fname, lname, mobilnumber, "", email, "");
		return customer;
	}

}
