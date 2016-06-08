package com.kingdee.ebank.services;

public interface CommonService {

	/**
     * 获取代理
     * 
     * @param channel
     * @return
     */
	public Object findGatewayService(String channel);

}
