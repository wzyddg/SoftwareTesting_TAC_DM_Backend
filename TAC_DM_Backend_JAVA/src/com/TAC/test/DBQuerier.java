package com.TAC.test;

public class DBQuerier {

	public static String getDeviceList(String type) {
		return "[1,iPad#1,TAC iPad,apple,1,1|230,Cocos2d-x高级开发教程,TP317.67 ZH771,book,2,2]";
	}
	
	public static String getTypeList() {
		return "[book|apple|umbrella]";
	}

	public static String getRecordList() {
		return "[75,王倩,18108093385,32,Keyboard No 3,logitech keyboard,1445432433000,0,1|76,赵一达,18221006616,17,mouse No 15,Mouse -15,1445432473000,0,1|77,高光宇,15821855080,22,iPad No 5,iPad Air 32G White,1445432556000,0,1]";
	}

	public static String getDevice(String itemId) {
		return "[24,iPad No 7,iPad 1 Black,apple_ipad,1,0]";
	}

	public static String getRecord(String recordId) {
		return "[107,安哲宏,17717090116,275,The Swift Programming Language 中,TAC-E039,1447729425000,0,1]";
	}

	public static String borrowItem(String command) {
		return "[1]";
	}

	public static String returnItem(String recordId) {
		return "[1]";
	}
	
	public static String addItem(String command) {
		return "[0]";
	}

	public static String adminLogin(String password) {
		return "[1]";
	}

	public static String getDeviceListAsAdmin(String type) {
		return "[2,TAC Rainbow Umbrella,Colourful Umbrella,umbrella,11,4|3,mouse No 1,Mouse -01,apple_mouse,1,1|43,Keyboard No 14,ordinary keyboard,apple_keyboard,1,1|136,计算机系统结构学习指导与题解,TAC-C020,book,1,1|137,计算机组成与结构,TAC-C017,book,7,6|139,计算机组成原理与系统结构实验教程,TAC-C021,book,1,1|140,剑桥国际商务英语,TAC-C003,book,3,3|141,剑桥BEC真题集,TAC-L008,book,1,1|142,精通正则表达式,TP301.2 ZF455A,book,1,1|143,精通iOS开发（第六版）,TN929.53 ZN122,book,2,2|144,看懂世界格局的第一本书,TAC-E031,book,1,1|145,跨文化交流入门,TAC-C006,book,2,2|146,跨文化商务交际,TAC-C004,book,1,1]";
	}

	public static String editLeftNumber(String command) {
		return "[1]";
	}
	
	public static String editTotalNumber(String command) {
		return "[1]";
	}

	public static String wrongCode(String command) {
		return "[]";
	}

	public static void main(String[] args) {
	}
}

