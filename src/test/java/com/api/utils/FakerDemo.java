package com.api.utils;

import java.io.Console;
import java.util.Locale;

import com.github.javafaker.Faker;

public class FakerDemo {

	public static void main(String[] args) {
		
		
		Faker fakerDemo = new Faker(new Locale("en-IND"));
		
		String firstName = fakerDemo.name().firstName();
		String lastName = fakerDemo.name().lastName();
		String address = fakerDemo.address().buildingNumber();
		String Streetadress = fakerDemo.address().streetAddress();
		
		System.out.println(firstName + " " + lastName);
		System.out.println(address);
		System.out.println(Streetadress);
		
		System.out.println(fakerDemo.numerify("456#######"));
		System.out.println(fakerDemo.numerify("###678####"));
		System.out.println(fakerDemo.numerify("#######897"));
		System.out.println(fakerDemo.number().digit());
		System.out.println(fakerDemo.number().digits(250));
		
	    System.out.println(fakerDemo.internet().emailAddress());
		
	}
}
