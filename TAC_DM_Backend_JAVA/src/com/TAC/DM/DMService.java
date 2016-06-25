package com.TAC.DM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.TAC.Model.DBQuerier;

public class DMService implements Runnable {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;

	public DMService(Socket serviceSocket) {
		this.clientSocket = serviceSocket;
	}
	
	public static void main(String[] args) {
		DMService service = new DMService(null);
		service.execute("[A]");
	}

	public String execute(String command) {
		String request = "";
		String result;
		try {
			request = command.substring(1, command.length() - 1);
			result = "";
			String[] splits = request.split("\\|");
			request = "";
			for(int i=1;i<splits.length;i++){
				request = request+"|"+splits[i];
			}
			if (request.startsWith("|")) {
				request = request.substring(1);
			}
			
			System.out.println("split number:"+splits.length);
			System.out.println(request);
			
			if("1".equals(splits[0])){
				result = DBQuerier.getDeviceList(request);
			}else if("2".equals(splits[0])){
				result = DBQuerier.getRecordList();
			}else if("3".equals(splits[0])){
				result = DBQuerier.getDevice(request);
			}else if("4".equals(splits[0])){
				result = DBQuerier.getRecord(request);
			}else if("5".equals(splits[0])){
				result = DBQuerier.borrowItem(request);
			}else if("6".equals(splits[0])){
				result = DBQuerier.returnItem(request);
			}else if("7".equals(splits[0])){
				result = DBQuerier.adminLogin(request);
			}else if("8".equals(splits[0])){
				result = DBQuerier.getDeviceListAsAdmin(request);
			}else if("9".equals(splits[0])){
				result = DBQuerier.editLeftNumber(request);
			}else if("A".equals(splits[0])){
				result = DBQuerier.getTypeList();
			}else if("B".equals(splits[0])){
				result = DBQuerier.addItem(request);
			}else if("C".equals(splits[0])){
				result = DBQuerier.editTotalNumber(request);
			}else {
				result = DBQuerier.wrongCode(request);
			}
//			switch (request.split("|")[0]) {
//			case "1":
//				System.out.println(request.split("|")[0]);
//				result = DBQuerier.getDeviceList(request.substring(2));
//				break;
//			case "2":
//				result = DBQuerier.getRecordList();
//				break;
//			case "3":
//				result = DBQuerier.getDevice(request.substring(2));
//				break;
//			case "4":
//				result = DBQuerier.getRecord(request.substring(2));
//				break;
//			case "5":
//				result = DBQuerier.borrowItem(request.substring(2));
//				break;
//			case "6":
//				result = DBQuerier.returnItem(request.substring(2));
//				break;
//			case "7":
//				result = DBQuerier.adminLogin(request.substring(2));
//				break;
//			case "8":
//				result = DBQuerier.getDeviceListAsAdmin(request.substring(2));
//				break;
//			case "9":
//				result = DBQuerier.editLeftNumber(request.substring(2));
//				break;
//				//TODO here is a bug, string switch
//			case "A":
//				result = DBQuerier.getTypeList();
//				break;
//			case "B":
//				result = DBQuerier.addItem(request.substring(2));
//				break;
//			case "C":
//				result = DBQuerier.editTotalNumber(request.substring(2));
//				break;
//			default:
//				result = DBQuerier.wrongCode(request.substring(2));
//				break;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
		System.out.println("Result:"+result);
		DMMainServer.totalSuccess++;
		
		return result;
	}

	@Override
	public void run() {
		
		try {
			
			System.out.println("---start Service----");
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream())), true);
			String request = "";
			while (true) {
				String str = in.readLine();
				request = request + str;
//				System.out.println(request);
				if (request.contains("]")) {
					System.out.println("Request:" + str);
					// out.println("Request:" + str);
					String resultString = execute(request);
					out.println(resultString);
					out.flush();
					break;
				}
				// else { // closeconnection
				// System.out.println("read nulg and out");
				// break;
				// }
			}
			out.close();
			in.close();
			clientSocket.close();

			// Close the connection, but not the server socket
			System.out.println("--end service--");
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}
}
