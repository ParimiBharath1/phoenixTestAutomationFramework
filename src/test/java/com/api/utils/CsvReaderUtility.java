package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataprovider.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReaderUtility {
	
	private CsvReaderUtility() {
		
	}
	
public static Iterator<UserBean>  loadCsv(String pathOfCsvFile) {
		
 
		
		InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
		InputStreamReader is = new InputStreamReader(iStream);
		
		CSVReader csvReader = new CSVReader(is);
		
		
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvReader)
				                     .withType(UserBean.class)
				                     .withIgnoreEmptyLine(true)
				                      .build();
		
		List<UserBean> dataList =csvToBean.parse();
		 return dataList.iterator();
		   
	}

}
