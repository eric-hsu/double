package com.kingdee.ebank.bank.cmbc.util.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kingdee.ebank.bank.cmbc.GpasynReceiveService;

/** 
 *  C/S架构的客户端对象，持有该对象，可以随时向服务端发送消息。 
 */  
public class SocketUtil {  

	//本行代付socket相关
    private static int gpcount=0;
    private static Socket  gpsocket=null;
    private static String gpserverIp = "127.0.0.1";//待定
    private static int gpport = 9008;
    private static int gpsport = 9007;
    private static String gpkeepAlivestr = "000000";
    
    //本行代扣socket相关
    private static String cbserverIp = "127.0.0.1";//待定
    private static int cbport = 123456;//待定
    
    //跨行代扣socket相关
    private static int cccount=0;
    private static Socket  ccsocket=null;
    private static String ccserverIp = "127.0.0.1";//待定
    private static int ccport = 9006;
    private static String cckeepAlivestr = "00000000";

    private static long checkDelay = 10;
    private static long keepAliveDelay = 15000;
       
    /** 
     *  获取本行代付相关socket长连接 
     */ 
    public static Socket getGpSocket() throws UnknownHostException, IOException {  
        if(gpsocket==null){
	    	gpsocket = new Socket(gpserverIp,gpport);
	        //开启心跳及接收结果线程
	        if(gpcount==0){
	        	new Thread(new KeepAliveWatchDog(gpsocket,checkDelay,keepAliveDelay,gpkeepAlivestr)).start(); 
	        	new Thread(new GpSocketReceive(gpsocket,checkDelay)).start();
	        	gpcount++;
	        	System.out.println("gpcount={"+gpcount+"}");
	        }
        }
        return gpsocket;
    }
    
    /** 
     *  获取本行代 扣相关，短连接同步获取数据！
     */ 
    public static String sendGpstr(String sendStr) throws UnknownHostException, IOException {
    	Socket gpsocket = new Socket(gpserverIp,gpsport);
    	String receive= "";
    	try {
			OutputStream os = gpsocket.getOutputStream();
		    PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
		    pw.write(sendStr);
		    System.out.println("发送：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t发送内容["+sendStr+"]"); 
		    pw.flush();
			while(true){
				InputStream in = gpsocket.getInputStream();  
			    if(in.available()>0){
			     	
			     	byte[] b = new byte[in.available()];
			     	in.read(b);
			        receive = new String(b,0,b.length);
			        System.out.println("接收：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t返回内容["+receive+"]");
			        break;
			    }
			}
    	} catch (Exception e) {
    		e.printStackTrace();
			throw new RuntimeException("交易失败，请联系金蝶相关技术人员处理！");
		}finally{
			gpsocket.close();
		}
    	return receive;
    }

    /** 
     *  获取本行代 扣相关，短连接同步获取数据！
     */ 
    public static String sendCbstr(String sendStr) throws UnknownHostException, IOException {
    	Socket cbsocket = new Socket(cbserverIp,cbport);
    	String receive= "";
    	try {
			OutputStream os = cbsocket.getOutputStream();
		    PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
		    pw.write(sendStr);
		    System.out.println("发送：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t发送内容["+sendStr+"]"); 
		    pw.flush();
			while(true){
				InputStream in = cbsocket.getInputStream();  
			    if(in.available()>0){
			     	
			     	byte[] b = new byte[in.available()];
			     	in.read(b);
			        receive = new String(b,0,b.length);
			        System.out.println("接收：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t返回内容["+receive+"]");
			        break;
			    }else{
			    	 
			     }
			}
    	} catch (Exception e) {
    		e.printStackTrace();
			throw new RuntimeException("交易失败，请联系金蝶相关技术人员处理！");
		}finally{
			cbsocket.close();
		}
    	return receive;
    }
    
    
    /** 
     *  获取跨行代扣相关socket长连接 
     */ 
    public static Socket getcCSocket() throws UnknownHostException, IOException {  
        if(ccsocket==null){
	    	ccsocket = new Socket(ccserverIp,ccport);  
	        //开启心跳及接收结果线程
	        if(cccount==0){
	        	new Thread(new KeepAliveWatchDog(ccsocket,checkDelay,keepAliveDelay,cckeepAlivestr)).start(); 
	        	new Thread(new CcSocketReceive(ccsocket,checkDelay)).start();
	        	cccount++;
	        	System.out.println("cccount={"+cccount+"}");
	        }
        }
        return ccsocket;
    }
}  