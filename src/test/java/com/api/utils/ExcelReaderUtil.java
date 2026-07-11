package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dataprovider.api.bean.UserBean;
import com.poiji.bind.Poiji;


public class ExcelReaderUtil {
	
	private ExcelReaderUtil() {
		
	}

	public static <T> Iterator<T> loadTestData(String xlsxfile,String sheetname, Class<T> clazz) {
		// TODO Auto-generated method stub
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xlsxfile);
				
		XSSFWorkbook myWorkbook=null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet mySheet = myWorkbook.getSheet(sheetname);
		
	    List<T>  datalist =Poiji.fromExcel(mySheet, clazz);
	    
	    return datalist.iterator();
	}

}
