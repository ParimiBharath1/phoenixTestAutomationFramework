package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtility {
	
	private CsvReaderUtility() {
		
	}
	
public static <T>  Iterator<T>  loadCsv(String pathOfCsvFile, Class<T> bean) {
		
 
		
		InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
		InputStreamReader is = new InputStreamReader(iStream);
		
		CSVReader csvReader = new CSVReader(is);
		
		
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
				                     .withType(bean)
				                     .withIgnoreEmptyLine(true)
				                      .build();
		
		List<T> List = csvToBean.parse();
		 return List.iterator();
		   
	}

}
