 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DatabaseMetaData;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFileMapToPojo {
	
	public static void main(String[] args) throws IOException, CsvException {
		
 
		
		InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader is = new InputStreamReader(iStream);
		
		CSVReader csvReader = new CSVReader(is);
		
		
		CsvToBean<UserPojo> csvToBean = new CsvToBeanBuilder(csvReader)
				                     .withType(UserPojo.class)
				                     .withIgnoreEmptyLine(true)
				                      .build();
		
		List<UserPojo> datList =csvToBean.parse();
		System.out.println(datList);
		System.out.println(datList.get(1).getUsername());
		System.out.println(datList.get(0).getPassword());
		   
	}


}
