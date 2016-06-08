package com.kingdee.ebank.bank.cmbc.util.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KeepAliveWatchDog implements Runnable{
	
	private long checkDelay = 10;  
	private long keepAliveDelay = 30000;  
	private long lastSendTime = System.currentTimeMillis();
	private boolean running=true;
	private String keepAlivestr="";
	private Socket socket;
	
    public KeepAliveWatchDog(Socket socket,long checkDelay, long keepAliveDelay,String keepAlivestr) {
		this.checkDelay = checkDelay;
		this.keepAliveDelay = keepAliveDelay;
		this.keepAlivestr = keepAlivestr;
		this.socket = socket;
	}
    
    public void run() {  
        while(running){  
            if(System.currentTimeMillis()-lastSendTime>keepAliveDelay){  
                try {  
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
                    pw.write(keepAlivestr);
                    System.out.println("发送：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t心跳包内容["+keepAlivestr+"]"); 
                    pw.flush();
                } catch (IOException e) {  
                    e.printStackTrace();  
                    if(running)running=false;  
                }
                lastSendTime = System.currentTimeMillis();  
            }else{
                try {  
                    Thread.sleep(checkDelay);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();
                    if(running)running=false;
                }  
            }  
        }  
    }  
}