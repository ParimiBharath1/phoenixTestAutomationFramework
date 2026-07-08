package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.dataprovider.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtility {
	
	private CsvReaderUtility() {
		
	}
	
public static void  loadCsv(String pathOfCsvFile) {
		
 
		
		InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
		InputStreamReader is = new InputStreamReader(iStream);
		
		CSVReader csvReader = new CSVReader(is);
		
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				                     .withType(UserBean.class)
				                     .withIgnoreEmptyLine(true)
				                      .build();
		
		List<UserBean> datList =csvToBean.parse();
		System.out.println(datList);
		System.out.println(datList.get(1).getUsername());
		System.out.println(datList.get(0).getPassword());
		   
	}

}
