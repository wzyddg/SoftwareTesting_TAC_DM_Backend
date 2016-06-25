package com.TAC.DM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.TAC.test.SuccessTimer;
//import com.TAC.test.DMService;

public class DMMainServer {
	
	public static int totalAccept = 1;
	public static int totalSuccess = 1;
	ServerSocket server;
	
	public void registPort(int port) {
		try {
			// Create socket for TCP
			
			//fixed bug
			if(port<0||port>65535)
				port = 7527;
			// TODO: found bug: port number out of range
			server = new ServerSocket(port);	//8222 
			System.out.println("TAC-DM Server Start, waitng on Port "
					+port+ "...");
	
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	public void start() throws IOException {
		while (true) {
			//Second socket for data 
			Socket client = server.accept();
			DMMainServer.totalAccept++;
			DMService service=new DMService(client);
			Thread serviceThread = new Thread(service);
			serviceThread.start();
		}
	}
	
	public static void main(String[] args) throws IOException {
//		SuccessTimer timer = new SuccessTimer();
//		Thread timerThread = new Thread(new SuccessTimer());
//		timerThread.start();
		DMMainServer server = new DMMainServer();
		server.registPort(7527);
		server.start();
		
		//7527 for this 
	}

}
