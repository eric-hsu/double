package com.kingdee.ebank.services.impl;

import org.springframework.stereotype.Component;

import com.kingdee.ebank.services.CommonService;
import com.kingdee.ebank.utils.SpringContextUtil;

@Component
public class CommonServiceImpl implements CommonService {
	
	/**
     * 获取代理
     * 
     * @param channel
     * @return
     */
	public Object findGatewayService(String channel) {
		return  SpringContextUtil.getBean(channel);
	}
}
