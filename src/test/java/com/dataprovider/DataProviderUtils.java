package com.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.CsvReaderUtility;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataprovider.api.bean.CreateJobBean;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {
	
	private static  Logger  LOGGER = LogManager.getLogger(DataProviderUtils.class);

	@DataProvider(name = "LoginApiDataProvider", parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		LOGGER.info("Loading data from CSV file testData/LoginCreds.csv");
		return CsvReaderUtility.loadCsv("testData/LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		LOGGER.info("Loading data from CSV file testData/CreateJobData.csv");
		Iterator<CreateJobBean> createjobbeanIterator = CsvReaderUtility.loadCsv("testData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payloadLists = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;
		CreateJobPayload temPayload;

		while (createjobbeanIterator.hasNext()) {
			tempBean = createjobbeanIterator.next();
			temPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadLists.add(temPayload);
		}

		return payloadLists.iterator();
	}

	@DataProvider(name = "CreateJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIFakerTest() {
 
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		LOGGER.info("Generating the faker create job data  with faker count",fakerCountInt);
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.createFakeCreateJobdata(fakerCountInt);

		return payloadIterator;

	}

	@DataProvider(name = "UserCredentialsProvider", parallel = true)
	public static Iterator<UserBean> UserCredentialsProvider() {
		LOGGER.info("Loading data from JSON file testData/LoginApiTestData.json");
		return JsonReaderUtil.loadJson("testData/LoginApiTestData.json",  UserBean[].class);
	}

	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {
		LOGGER.info("Loading data from JSON file testData/CreateJobTestData.json");
		return JsonReaderUtil.loadJson("testData/CreateJobTestData.json", CreateJobPayload[].class);
	}

	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIExcelDataProvider() {
		LOGGER.info("Loading data from EXCEL file testData/PhoenixTestData.xlsx");
		return ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "loginsheet", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {

		LOGGER.info("Loading data from EXCEL file testData/PhoenixTestData.xlsx");
		Iterator<CreateJobBean> iterator = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx",
				"createjobdata", CreateJobBean.class);

		List<CreateJobPayload> payloadLists = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;
		CreateJobPayload temPayload;

		while (iterator.hasNext()) {
			tempBean = iterator.next();
			temPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadLists.add(temPayload);
		}

		return payloadLists.iterator();

	}
	
	@DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIDBDataProvider() {
		
		LOGGER.info("Loading data from Database for create job payload");
		
		 List<CreateJobBean> 	beanlist =CreateJobPayloadDataDao.getCreateJobPayloadData();
		   List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		   
		   for(CreateJobBean createJobBean: beanlist) {
			   CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
			   payloadList.add(payload);
		   }
		   
		   return payloadList.iterator();
	}
}
