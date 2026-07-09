package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.tests.CreateJobApiTest;
import com.github.javafaker.Faker;

public class FakeDemo2 {
	
	 private static final String COUNTRY="INDIA";
	
	public static void main(String[] args) {
		
		Locale locale = new Locale("en-IND");
		
		Faker fakerDemo = new Faker(locale);
		
		String fname = fakerDemo.name().firstName();
		String lname = fakerDemo.name().lastName();
		String mobilnumber = fakerDemo.numerify("765#######");
		String email = fakerDemo.internet().emailAddress();
		
		
		
		Customer customer = new Customer(fname, lname, mobilnumber, null, email, null);
		
		System.out.println(customer);
		
		String flat_number = fakerDemo.numerify(("1-40##"));
		String apartment_name = fakerDemo.address().streetName();
		String street_name = fakerDemo.address().streetName();
		String landmark = fakerDemo.address().streetName();
		String area = fakerDemo.address().streetName();
		String pincode = fakerDemo.numerify("#######") ;
		String state = fakerDemo.address().state();
		
		
		CustomerAddress customerAddress = new  CustomerAddress(flat_number, apartment_name, street_name,
				landmark, area, pincode, COUNTRY, state);
		System.out.println(customerAddress);
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		
		String imenum = fakerDemo.numerify("##############");
		
		String popurl = DateTimeUtil.getTimeWithDaysAgo(10);
			
		CustomerProduct customerProduct = new CustomerProduct(dop, imenum, imenum, imenum, popurl, 1, 1);
		
		System.out.println(customerProduct);
		
		
		//random generate 
		
		Random random = new Random();
				
	      int problem= random.nextInt(27)+1;
		
		String remarkString = fakerDemo.lorem().sentence(20);
		Problems problems = new Problems(problem, remarkString);
		
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problems);
		
		System.out.println(problemList);
		
		 CreateJobPayload payload = new CreateJobPayload(0, 2, 2, 1, customer, customerAddress, customerProduct, problemList);
		 
		 System.out.println(payload);
		
		
	}

}
