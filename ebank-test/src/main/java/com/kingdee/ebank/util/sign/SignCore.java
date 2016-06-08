package com.kingdee.ebank.util.sign;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SignCore {

    /** 
     * 把数组所有元素排序，并按照�?参数=参数值�?的模式用�?”字符拼接成字符�?
     * @param params �?��排序并参与字符拼接的参数�?
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            if("sign".equals(key)){
            	continue;
            }
            if("signType".equals(key)){
            	continue;
            }
            String value = (String)params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一�?字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /** 
     * 获得Certificate 
     *  
     * @param certificatePath 
     * @return 
     * @throws Exception 
     */  
    public static String getCertificate(String certificatePath) throws Exception {  
    	BufferedReader br = new BufferedReader(new FileReader(certificatePath)); 
    	String s = br.readLine(); 
    	String str = ""; 
    	s = br.readLine(); 
    	while (s.charAt(0) != '-'){ 
    	str += s + "\r"; 
    	s = br.readLine(); 
    	} 
        return str;  
    }
}
