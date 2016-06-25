package com.TAC.DM;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.TAC.test.SuccessTimer;

public class DMMainServer {
	
	public static int totalAccept = 1;
	public static int totalSuccess = 1;
	
	public void startServer(int port) {
		try {
			// Create socket for TCP
//			port = 65536;
			// TODO: found bug: port number out of range
			ServerSocket server = new ServerSocket(port);	//8222 
			System.out.println("TAC-DM Server Start, waitng on Port "
					+port+ "...");
			
			while (true) {
				//Second socket for data 
				Socket client = server.accept();
				DMMainServer.totalAccept++;
				DMService service=new DMService(client);
				Thread serviceThread = new Thread(service);
				serviceThread.start();
			}
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	public static void main(String[] args) {
//		SuccessTimer timer = new SuccessTimer();
//		Thread timerThread = new Thread(new SuccessTimer());
//		timerThread.start();
		DMMainServer server = new DMMainServer();
		server.startServer(7527);
		
		//7527 for this 
	}

}
