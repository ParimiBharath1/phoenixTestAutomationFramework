package com.api.utils;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/PhoenixTestData.xlsx");
				
		XSSFWorkbook  myWorkbook = new XSSFWorkbook(is);
		
		XSSFSheet mySheet = myWorkbook.getSheet("loginsheet");
		
		XSSFRow myRow;  
		XSSFCell myCell;  
		
		//System.out.println(myCell);
		
		
		int lastrowIndex = mySheet.getLastRowNum();
		System.out.println(lastrowIndex);
		
		XSSFRow rowheader = mySheet.getRow(0);
		
		int lastcolindex = rowheader.getLastCellNum()-1;
		
		System.out.println(lastcolindex);
		
		   for(int rowIndex=0;rowIndex<=lastrowIndex;rowIndex++) {
			   for(int  colIndex=0;colIndex<=lastcolindex;colIndex++) {
				       myRow = mySheet.getRow(rowIndex);
				       myCell = myRow.getCell(colIndex);
				       System.out.print(myCell+ " ");
			   }
			   System.out.println("");
		   }
		
		

	}

}
