package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.ConfigManager;

public class AllureEnvironmentWriterUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);

	public static void  createEnvironmentPropertiesFile() {
	 
		
		
		
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdirs();		
		
		Properties properties = new Properties();
		
		properties.setProperty("Project Name", "Phoenix Test Automation Framework");
		properties.setProperty("Env", ConfigManager.env);
		properties.setProperty("BASE_URI",  ConfigManager.getProperty("BASE_URI"));
		properties.setProperty("Operating System", System.getProperty("os.name"));
		properties.setProperty("Operating System Version", System.getProperty("os.version"));
		properties.setProperty("Java version", System.getProperty("java.version"));
		
		FileWriter fWriter;
		
		try {
			 fWriter = new FileWriter(folderPath+"/environment.properties");
			properties.store(fWriter, "My properties file");
			LOGGER.info("Created the environment file at {}",folderPath);
		} catch (IOException e) {
			LOGGER.error("Unable to create the environment.properties file",e);
			e.printStackTrace();
		}
		

	}

}
