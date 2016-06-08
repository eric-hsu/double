package com.kingdee.ebank;

import java.io.InputStream;
import org.junit.Test;
import com.kingdee.ebank.utils.sign.RSA;

public class SignTest {
	@Test
	public void sign(){
		String content = "123456";
		
		try {

		//签名
		String privatekey = getPrivateKey("certificate/test_customer_private_pkcs8_key.pem");
		String sign = RSA.sign(content, privatekey.trim(), "utf-8");
		System.out.println("签名结果："+sign);
		
		//验证
		
		String publickey = getPublicKey("certificate/test_customer_public_key.pem");
		boolean flag = RSA.verify(content, sign, publickey, "utf-8");
		System.out.println("验证结果："+flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPrivateKey(String path){
		try {

			InputStream ips =RSA.class.getClassLoader().getResourceAsStream(path);
			
			int i=0; 
			byte[] b = new byte[1024];
			StringBuffer buffer = new StringBuffer();
			while((i=ips.read(b))!=-1){
				String str = new String(b);
				buffer.append(str);
			}
			String privatekey = buffer.toString();
			privatekey = privatekey.replaceAll("-----BEGIN PRIVATE KEY-----", "");
			privatekey = privatekey.replaceAll("-----END PRIVATE KEY-----", "");
		
			System.out.println(privatekey.trim());
			
			return privatekey.trim();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String getPublicKey(String path){
		try {

			InputStream ips =RSA.class.getClassLoader().getResourceAsStream(path);
			
			int i=0; 
			byte[] b = new byte[1024];
			StringBuffer buffer = new StringBuffer();
			while((i=ips.read(b))!=-1){
				String str = new String(b);
				buffer.append(str);
			}
			String publickey = buffer.toString();
			publickey = publickey.replaceAll("-----BEGIN PUBLIC KEY-----", "");
			publickey = publickey.replaceAll("-----END PUBLIC KEY-----", "");
		
			System.out.println(publickey.trim());
			
			return publickey.trim();
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
