package com.TAC.test.driver;

import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.TAC.DM.DMService;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;

public class DMServiceExecuteDriver {
	static String fileNameString = "C:\\Users\\wzy\\Desktop\\1.1结果用例集\\UT_002_FUN_002.csv";
	
	public static void main(String[] args) throws Exception {
		DMService service = new DMService(null);
		CsvReader reader = new CsvReader(fileNameString);
		reader.readHeaders();
		String[] headerStrings = reader.getHeaders();
		List<String[]> recordStrings = new ArrayList<String[]>();
		while (reader.readRecord()) {
			String[] record = new String[reader.getHeaderCount()];
			for(int i=0;i<reader.getHeaderCount();i++){
				record[i] = reader.get(i);
			}
			String result=service.execute(record[3]);
			record[5] = result;
			System.out.println(record[3]);
			System.out.println(record[5]);
			recordStrings.add(record);
		}// save old over
		reader.close();
		
		
		
		CsvWriter writer = new CsvWriter(fileNameString);
		writer.writeRecord(headerStrings);
		for (int i = 0; i < recordStrings.size(); i++) {
			writer.writeRecord(recordStrings.get(i));
			
		}
//		writer.flush();
		writer.close();
//		service.execute();
	}

}
