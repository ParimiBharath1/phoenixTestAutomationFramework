package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	
	
	private static  Properties properties = new Properties();
	
	private ConfigManagerOLD() {
		//Private constructor to restrict object creation in other classes
	}
	
	static {
		
          File configFile = new File(System.getProperty("user.dir")+ File.separator +"src"+ File.separator+"test"+ File.separator+"resources"+ File.separator+"config"+ File.separator+"config.properties");
		
		FileReader fileReader;
		  try {
			fileReader = new FileReader(configFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static String  getProperty(String key) throws IOException {
		
		return properties.getProperty(key);
		
	}

}
