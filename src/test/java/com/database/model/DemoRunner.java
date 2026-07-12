package com.database.model;


import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerProductDao;

public class DemoRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		CustomerDBModel customerDBModel = new CustomerDBModel("Raju", "Khan", "9089089087", "", "rajukhan@gmail.com", "");
//		
//		System.out.println(customerDBModel);

//		CustomerAddressDBModel customerAddressDBModel =  CustomerAddressDao.getCustomerAddress(351365);
//		
//		System.out.println(customerAddressDBModel);
		
		CustomerProductDBModel customerProductDBModel = CustomerProductDao.getCustomerProduct(351336);
		
		System.out.println(customerProductDBModel.getPopurl());
		
		
		 
	}

}
