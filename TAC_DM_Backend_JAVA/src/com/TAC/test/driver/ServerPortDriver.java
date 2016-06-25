package com.TAC.test.driver;

import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

import com.TAC.DM.DMMainServer;
import com.TAC.DM.DMService;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class ServerPortDriver {
	static String fileNameString = "C:\\Users\\wzy\\Desktop\\1.1结果用例集\\UT_001_FUN_001.csv";
	
	public static void main(String[] args) throws Exception {
		DMMainServer server = new DMMainServer();
		CsvReader reader = new CsvReader(fileNameString);
		reader.readHeaders();
		String[] headerStrings = reader.getHeaders();
		List<String[]> recordStrings = new ArrayList<String[]>();
		while (reader.readRecord()) {
			String[] record = new String[reader.getHeaderCount()];
			for(int i=0;i<reader.getHeaderCount();i++){
				record[i] = reader.get(i);
			}
			recordStrings.add(record);
			boolean resBool = false;
			try {
				resBool = server.registPort(Integer.parseInt(record[3]));
			} catch (Exception e) {
				// TODO: handle exception
//				record[5] = ""+resBool;
				continue;
			}
			record[5] = ""+resBool;
		}// save old over
		reader.close();
		
		CsvWriter writer = new CsvWriter(fileNameString);
		writer.writeRecord(headerStrings);
		for (int i = 0; i < recordStrings.size(); i++) {
			writer.writeRecord(recordStrings.get(i));
		}
		writer.close();
		
	}

}
