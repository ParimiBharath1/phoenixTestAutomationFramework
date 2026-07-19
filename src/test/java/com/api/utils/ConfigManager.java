package com.api.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
	
	
	private static  Properties properties = new Properties();
	
	private static String path = "config/config.properties";
	
	public static String env;
	
	private static  Logger  LOGGER = LogManager.getLogger(ConfigManager.class);
	
	private ConfigManager() {
		//Private constructor to restrict object creation in other classes
	}
	
	static {
		LOGGER.info("Reading env value from the terminal");
		if(System.getProperty("env") == null) {
			LOGGER.warn("env value is not set.. reading qa as default value"); 
		}
		 env = System.getProperty("env","qa");
		 LOGGER.info("Running the test in environment {}",env);
		 env = env.toLowerCase().trim();
		 switch(env) {
		 case "dev" ->  path = "config/config.dev.properties";
 
		 case "qa"  ->  path = "config/config.qa.properties";
			  
		 case "uat" ->  path = "config/config.uat.properties";
		 
		 default  ->  path = "config/config.qa.properties";
		 
		 }
		 
		 LOGGER.info("Using the properties file from the path {}",path);
		
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		 
		if(input == null) {
			LOGGER.error("Cannot find the path  {}",path);
			throw new RuntimeException("Path of file is not availale"+path);
		}
		
          
		  try {
		 
			properties.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Cannot find the path  {}",path);
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			 LOGGER.error("something went wrong please check the file");
			e.printStackTrace();
		}
	}
	
	public  static String  getProperty(String key){
		
		return properties.getProperty(key);
		
	}

}
