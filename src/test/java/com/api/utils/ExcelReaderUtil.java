package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

import io.qameta.allure.Step;


public class ExcelReaderUtil {
	
	private static  Logger  LOGGER = LogManager.getLogger(ExcelReaderUtil.class);
	
	private ExcelReaderUtil() {
		
	}

	 @Step("Loading the testdata from excel file")
	public static <T> Iterator<T> loadTestData(String xlsxfile,String sheetname, Class<T> clazz) {
		// TODO Auto-generated method stub
		
		  LOGGER.info("Reading the testdata from xlsx file {} and  from sheet {}", xlsxfile,sheetname);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xlsxfile);
				
		XSSFWorkbook myWorkbook=null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Cannot read the excel file{}",xlsxfile,e);
			e.printStackTrace();
		}
		
		XSSFSheet mySheet = myWorkbook.getSheet(sheetname);
		  LOGGER.info("Converting the XSSF sheet {} to pojo class{}",sheetname,clazz);
		
	    List<T>  datalist =Poiji.fromExcel(mySheet, clazz);
	    
	    return datalist.iterator();
	}

}
