package com.kingdee.ebank.bank.cmbc.util.socket;

import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kingdee.ebank.bank.cmbc.CcasynReceiveService;
import com.kingdee.ebank.bank.cmbc.GpasynReceiveService;
import com.kingdee.ebank.bank.cmbc.service.CcasynReceiveServiceImpl;
import com.kingdee.ebank.util.SpringContextUtil;

public class CcSocketReceive implements Runnable{  
	
	private boolean running=true;
	private Socket socket;
	private long checkDelay = 10; 
    public CcSocketReceive(Socket socket,long checkDelay) {
		this.socket = socket;
		this.checkDelay = checkDelay;
	}

	public void run() {  
        while(running){
            try {
                InputStream in = socket.getInputStream();  
                if(in.available()>0){
                	
                	byte[] b = new byte[in.available()];
                	in.read(b);
                	String receive = new String(b,0,b.length);
                    System.out.println("接收：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t返回内容["+receive+"]");
                    CcasynReceiveService ccasynReceiveService = (CcasynReceiveService) SpringContextUtil.getBean("ccasynReceiveService");
                    ccasynReceiveService.doHandle(receive);
                    
                }else{
                    Thread.sleep(checkDelay);
                }
            } catch (Exception e) {  
                e.printStackTrace();  
                if(running)running=false;  
            }   
        }  
    }  
}  