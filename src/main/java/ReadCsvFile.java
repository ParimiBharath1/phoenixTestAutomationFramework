import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile {
	
	public static void main(String[] args) throws IOException, CsvException {
		
		
//		
//		File csvFile = new File("C:\\Users\\parim\\eclipse-workspace\\sdepreparation2026\\PhoenixTestAutomationFramework\\src\\main\\resources\\testData\\LoginCreds.csv");
//		
//		FileReader fileReader = new FileReader(csvFile);
		
		InputStream iStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/LoginCreds.csv");
		InputStreamReader is = new InputStreamReader(iStream);
		
		CSVReader csReader = new CSVReader(is);
		
		   List<String[]> dataList =csReader.readAll();
		   
		      for(String[] datarray: dataList) {
		    	  for(String data: datarray) {
		    		  System.out.print(data+" ");
		    	  }
		    	  System.out.println("");
		      }
	}


}
