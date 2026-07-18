package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtility {
	
	private static  Logger  LOGGER = LogManager.getLogger(CsvReaderUtility.class);
	
	private CsvReaderUtility() {
		
	}
	
public static <T>  Iterator<T>  loadCsv(String pathOfCsvFile, Class<T> bean) {
		
         
	    LOGGER.info("Loading the csv file from the path {}", pathOfCsvFile);
		
		InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
		InputStreamReader is = new InputStreamReader(iStream);
		
		CSVReader csvReader = new CSVReader(is);
		
		  LOGGER.info("Converting csv to the bean class {}",  bean);
		
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				                     .withType(bean)
				                     .withIgnoreEmptyLine(true)
				                      .build();
		
		List<T> List = csvToBean.parse();
		 return List.iterator();
		   
	}

}
