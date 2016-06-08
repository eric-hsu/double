package com;

import java.io.IOException;

import com.kingdee.ebank.util.ReadFTPFile;

public class FtpTest {
	private static String ip = "127.0.0.1";
	private static int port = 21;
	private static String username = "admin";
	private static String password = "admin";
	/**
	 * @param args
	 * @throws FTPException 
	 * @throws IOException 
	 */
	public static void main(String[] args){
	    try {
	    	String ftppath = "/check/20150819/";
	    	ReadFTPFile read = new ReadFTPFile();  
            String result = read.readConfigFileForFTP(username, password, ftppath, ip, port, "1415141_DZWJ_20150819.txt");  
            System.out.println("***************************************");  
            System.out.println("" + result.trim()); 
            System.out.println("***************************************");  
	    } catch (Exception e) {
			e.printStackTrace();
		}
	        
	}

}
