package com.kingdee.ebank.bank.pab.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class PabHttprequest {
	public static String send(String data){
		String res = null;
		try {
			PostMethod postMethod = new PostMethod(PabConstants.url);
			postMethod.setRequestEntity(new StringRequestEntity(data, "text/html", "gbk"));
			postMethod.setRequestHeader("Content-type","text/xml; charset=gbk");
			HttpClient httpClient = new HttpClient();
			httpClient.executeMethod(postMethod);
			res = new String(postMethod.getResponseBody(),"gbk");
			postMethod.releaseConnection();
		} catch (Exception e) {
			return e.getMessage();
		}
		return res;
	}
}
