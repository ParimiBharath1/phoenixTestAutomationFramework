package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import  org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;


public class ExcelReaderUtil2 {
	
	private ExcelReaderUtil2() {
		
	}

	public static Iterator<UserCredentials> loadTestData() {
		// TODO Auto-generated method stub
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");
				
		XSSFWorkbook myWorkbook=null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet mySheet = myWorkbook.getSheet("loginsheet");
		
		XSSFRow myRow;  
		XSSFCell myCell;  
		
	    XSSFRow headerRows = mySheet.getRow(0);
	    
	    int userNameIndex=-1;
	    int passwordIndex= -1;
	    
	     for(Cell cell: headerRows ) {
	                       if(cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
	                    	    userNameIndex = cell.getColumnIndex();
	                       }
	                       if(cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
	                    	    passwordIndex = cell.getColumnIndex();
	                       }
	     }
	     
	     System.out.println(userNameIndex+ " "+ passwordIndex);
	     
	     
	     int lastrowIndex = mySheet.getLastRowNum();
	     
	     XSSFRow rowDataRow;
	     UserCredentials userCredentials=null;
	     ArrayList<UserCredentials> userlist = new ArrayList<UserCredentials>();
	       for(int rowIndex=1;rowIndex<=lastrowIndex;rowIndex++) {
	    	   rowDataRow= mySheet.getRow(rowIndex);
	    	    userCredentials = new UserCredentials(rowDataRow.getCell(userNameIndex).toString(), rowDataRow.getCell(passwordIndex).toString());
	    	    userlist.add(userCredentials);
	       }
		 
//	       System.out.println(userCredentials);
//	       System.out.println(userlist);
	       
	       
	       
		   return userlist.iterator();
	}

}
