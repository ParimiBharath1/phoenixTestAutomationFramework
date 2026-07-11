package com.api.utils;

import java.util.Iterator;

import com.dataprovider.api.bean.CreateJobBean;


public class ExcelReaderUtil4 {
	
	 public static void main(String[] args) {
		
		Iterator<CreateJobBean> iterator =ExcelReaderUtil3.loadTestData("testData/PhoenixTestData.xlsx","createjobdata", CreateJobBean.class);
		
		while(iterator.hasNext())
		      System.out.println(iterator.next());
	}

}
