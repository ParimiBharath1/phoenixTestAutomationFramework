package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	
	private static  Logger  LOGGER = LogManager.getLogger(JsonReaderUtil.class);

	public static  <T> Iterator<T> loadJson(String filename, Class<T[]> clazz) {
		// TODO Auto-generated method stub
		
		LOGGER.info("Reading the json from the file{}", filename);
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
 
		ObjectMapper objectMapper = new ObjectMapper();
		  T[] classArray;
		List<T>	list = null;
		try {
			LOGGER.info("Converting the json data into the bean class{}",clazz);
			classArray =  objectMapper.readValue(is, clazz);
		    list = Arrays.asList(classArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Cnnot read the json from the file{}",filename,e);
			e.printStackTrace();
		}
		
	      return list.iterator();
 

		

	}

}
