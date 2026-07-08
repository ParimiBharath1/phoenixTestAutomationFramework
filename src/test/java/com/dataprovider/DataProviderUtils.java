package com.dataprovider;

import java.util.Iterator;

import org.apache.commons.collections.functors.TruePredicate;
import org.testng.annotations.DataProvider;

import com.api.utils.CsvReaderUtility;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name="LoginApiDataProvider",parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		  return CsvReaderUtility.loadCsv("testData/LoginCreds.csv");
	}
}
