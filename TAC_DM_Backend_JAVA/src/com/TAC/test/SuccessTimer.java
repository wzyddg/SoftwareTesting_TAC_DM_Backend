package com.TAC.test;

import com.TAC.DM.DMMainServer;

public class SuccessTimer implements Runnable{
	@Override
	public void run() {
		boolean a= true;
		
		try {
		while(a){
			System.out.println("A:"+DMMainServer.totalAccept+" S:"+DMMainServer.totalSuccess+" Rate:"+((double)DMMainServer.totalSuccess/(double)DMMainServer.totalAccept));
			Thread.sleep(1000);
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
