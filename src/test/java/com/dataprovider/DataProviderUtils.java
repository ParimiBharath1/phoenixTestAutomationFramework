package com.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.CsvReaderUtility;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataprovider.api.bean.CreateJobBean;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name="LoginApiDataProvider",parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		  return CsvReaderUtility.loadCsv("testData/LoginCreds.csv", UserBean.class);
	}
	
	@DataProvider(name="CreateJobDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		Iterator<CreateJobBean> createjobbeanIterator  =CsvReaderUtility.loadCsv("testData/CreateJobData.csv", CreateJobBean.class);
		
		   List<CreateJobPayload> payloadLists = new ArrayList<CreateJobPayload>();
		   
		   CreateJobBean tempBean;
		   CreateJobPayload temPayload;
		   
		   while(createjobbeanIterator.hasNext()) {
			   tempBean= createjobbeanIterator.next();
			   temPayload = CreateJobBeanMapper.mapper(tempBean);
			   payloadLists.add(temPayload);
		   }
		   
		   return payloadLists.iterator();
	}
	
	@DataProvider(name="CreateJobAPIFakerDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIFakerTest() {
		
		       String fakerCount = System.getProperty("fakerCount", "5");
		       int fakerCountInt = Integer.parseInt(fakerCount);
		
		      Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.createFakeCreateJobdata(fakerCountInt);
		     
		         return payloadIterator;
		
	}
	
	
	@DataProvider(name="UserCredentialsProvider",parallel = true)
	public static Iterator<UserCredentials> UserCredentialsProvider() {
		  return JsonReaderUtil.loadJson("testData/LoginApiTestData.json", UserCredentials[].class);
	}
	
	@DataProvider(name="CreateJobAPIJsonDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
		  return JsonReaderUtil.loadJson("testData/CreateJobTestData.json",  CreateJobPayload[].class);
	}
}
