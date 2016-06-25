package com.TAC.test.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.TAC.Model.DBQuerier;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class GetDeviceDriver {
	static String fileNameString = "C:\\Users\\wzy\\Desktop\\1.2结果用例集\\UT_003_FUN_007.csv";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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
			
			String result = "";
			try {
				result = DBQuerier.getDevice(record[3]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				record[5] = "false:"+e.toString();
				continue;
			}
			
			record[5] = result;
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
