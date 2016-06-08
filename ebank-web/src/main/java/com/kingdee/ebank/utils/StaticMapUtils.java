package com.kingdee.ebank.utils;

import java.util.HashMap;
import java.util.Map;

public class StaticMapUtils {
	
    // 渠道映射map
    public static Map<String, String> gatewayMap = new HashMap<String, String>();

	public static String getGateway(String channel){
		gatewayMap.put("cmbc", "cmbcService");
		String gateway = gatewayMap.get(channel);
		return gateway;
	}
}
