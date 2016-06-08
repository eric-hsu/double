package com.kingdee.ebank.bank.cmbc.util.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketSend{
	
	private Socket socket;
	
	public SocketSend(Socket socket) {
		this.socket = socket;
	}
	
	public String send(String sendStr) throws IOException{
		  OutputStream os = socket.getOutputStream();
          PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
          pw.write(sendStr);
          pw.flush();
          return null;
	}
}