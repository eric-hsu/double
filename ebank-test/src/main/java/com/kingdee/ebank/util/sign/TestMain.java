package com.kingdee.ebank.util.sign;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingdee.ebank.util.HttpClientUtil;
import com.kingdee.ebank.util.sign.RSA;
import com.kingdee.ebank.util.sign.SignCore;


public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	
	    	Map<String, Object> tradeMap = new HashMap<String, Object>();
	    	
	    	tradeMap.put("customerNo", "121121212");
	    	tradeMap.put("channel", "pab");
	    	tradeMap.put("tradecode", "0002");//
	    	tradeMap.put("bankMerCode", "00203030000000037000");//银行客户号
	    
	    	
	    	Gson gson = new Gson();
	    	String jsonstr= gson.toJson(tradeMap);
	    	System.out.println(jsonstr);
	    	System.out.println("发送订单号："+tradeMap.get("customerOrderno"));
	    	

	    	String privatekey = getServerPrivateKey();
	    	String sign = RSA.sign(jsonstr, privatekey, "utf-8");
	    	
	    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put("sign", sign);
	    	params.put("data", jsonstr);
	    	
//	    	Map<String, Object> extra = gson.fromJson(jsonstr,new TypeToken<Map<String, Object>>(){}.getType());
//	
//			String bodyStr = HttpClientUtil.sendPostRequest(url,params,"UTF-8" , "UTF-8");

			System.out.println(params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String getServerPrivateKey() throws IOException{

		String serverPrivateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMA0sHIC5sJd4Lmm"+
"QHz3y5hKzI/rYYINSA4RqhRmprrizyKnslIwaV96uG8U3CZFuo80/DOcAy/voHZS"+
"3m50S8+cXmGeJGMouDMHhF78iFczvdwYRwbxYIEU9mMO2V2IaaXaBVmoYtaK+0ni"+
"gMCu6p0vPuw4Fe8nGeObPdU5BXGdAgMBAAECgYAnprb/zjlsv5Esu29yWGxX7pZk"+
"2lozSiojPOGpc8PRqhixPYc8DWCvodGKmPMRsjDaekPNIdJGgVjJEC9y+erpfUEy"+
"ZR4hYw2xxlAZQYJANb3Z9G0U7y91pYKAYFouG2SxSuc4iNnN0h5X/5YtAjZvctPh"+
"dCt9S3arZPIPNStcAQJBAPw73qInQ9vv4EELrOma6wZmutDaEk5QqHlgdiAARytJ"+
"0nqH0uoytQkzNt2FH+Z1OZcqGIbMa2y5dW6bSWE9QM0CQQDDE17QDMDBItEIeyCf"+
"ldXCITWIuz6hKTQB1NRW3akxcf197Y5QxpMxkfTDRoX2vvn9NNwF3thsvoVIQJyr"+
"9LQRAkBdgDwJVBDdqNAyjIdumVTiLJa38P60NUYeqFlhh3jaXSU+8raGxoFBhdCi"+
"0USAA5hzptEstv5jcWRMuhe7ih9JAkAfDrxvnzgpB6QEF6ZQAgjwSV0+kaEdA3RW"+
"Pk44Lj47swxKukGINrVElRpE5Lt7V1hxqbLF9H68gXCy2iaXcfQhAkBxgheRboQn"+
"ZImljsqx0DcXxX0DGGnzQ2FZPefon8jz2UH2HfDPyZH2AfhC28NQ4xJs6kW0CWbe"+
"B9CHgsaKe8Pj";
		
		return serverPrivateKey;
	}
	
	public static String getServerPublicKey() throws IOException{

		String serverPublickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDANLByAubCXeC5pkB898uYSsyP"+
"62GCDUgOEaoUZqa64s8ip7JSMGlferhvFNwmRbqPNPwznAMv76B2Ut5udEvPnF5h"+
"niRjKLgzB4Re/IhXM73cGEcG8WCBFPZjDtldiGml2gVZqGLWivtJ4oDAruqdLz7s"+
"OBXvJxnjmz3VOQVxnQIDAQAB";
		
		return serverPublickey;
	}
	
	
	public static String getClientPrivateKey() throws IOException{

		String clientPrivateKey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBANKL3goUIDUj6S7F"+
"+h9gKIfOVdQTyxadmP/uZ7onH4LS5/MkOm92hDNR+7XbArq65r7q0LEg2l9hHmbL"+
"zAb0bILPajrlGEvNkRAk3vVKQuE+8Yk/0LNqruNDzbBO/Sby0BusXmVqRbyQN1eS"+
"+x8aoXmVzsgymceFzbmOHv+DTg/bAgMBAAECgYEAmvEtTNN0xEaSIsa1BAP6r75r"+
"sS+DxDfkUWu7z+VcriTRZ0Ag9MgOD8pkZ3ct8gb7KkKZo9Beg9Rv0AxoMDIN63xG"+
"Hu0FaNssf0kUs6FGPSIgKnQ2TqAON7eRQUCxbZK6UMsUWPVMmTqmPLHLoiVMoraB"+
"7BJK5ANGhZU/KanYiTECQQDrPe1s/QnoBm33J5OhpxwGtiHkGQhOo4+GL7llFLof"+
"SzbeG0vTOX9n2Do8t5fA3/DbzRXSZUH9HoRNBJbTDY3zAkEA5SASZ/v4/6eynqZN"+
"RUvbK/FCJal1sBSFlCgFgBmIjJVd+DSLkh/T9PoXOi7Dm4ShQmtKj4QB568wAt+X"+
"Y2goeQJBALoxKds57L1ZWXBmQtOQEP6+6dRAe/LCQrdSzsrSvP5H6gWiVUG6IL1w"+
"x6eHKPXgSiOvD7aXrsNJhC3xk+84q0ECQQCFBwPov5CnzhOUrPU8AHi1oJNJDoQ3"+
"wRJ1HG9lGm3/QVGXo9pM8pG7L+CAdVo4Vt7Rkz1s6bW5cCst3+cjXfvxAkEAoSr7"+
"I1u5kAmQK3MGAiKGR9iy2ange80hZw/qY35LM4tvKINVVFf6X7f48mQ1I7SORNwW"+
"7FAOuAbdk2/d/vJycw==";
		
		return clientPrivateKey;
	}

	
	public static String getClientPublicKey() throws IOException{

		String clientPublickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDSi94KFCA1I+kuxfofYCiHzlXU"+
"E8sWnZj/7me6Jx+C0ufzJDpvdoQzUfu12wK6uua+6tCxINpfYR5my8wG9GyCz2o6"+
"5RhLzZEQJN71SkLhPvGJP9Czaq7jQ82wTv0m8tAbrF5lakW8kDdXkvsfGqF5lc7I"+
"MpnHhc25jh7/g04P2wIDAQAB";
		
		return clientPublickey;
	}
}
