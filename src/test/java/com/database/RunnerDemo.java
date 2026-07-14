package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class RunnerDemo {
	
	public static void main(String[] args) {
		
		 Dotenv dotenv = Dotenv.load();
		 String dotenvString = dotenv.get("DB_PASSWORD");
		 System.out.println(dotenvString);
		
	}

}
