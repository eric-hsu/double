package com.kingdee.ebank.bank.cmbc.service;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.bank.cmbc.AbstractCmbcService;
import com.kingdee.ebank.util.ReturnInfo;

import java.util.Map;
/**
 * 白名单授权查询
 * @author pc
 *
 */
@Service
public class AuthorizedQueryService extends AbstractCmbcService {
	private static Logger logger = Logger.getLogger(AuthorizedQueryService.class);

	/**
     * 白名单授权查询
     * 
     * @param params
     * @return String
     */
	public String perform(Map<String, Object> params) {
		logger.info("**************************白名单授权查询***************************");
		logger.info("提交参数："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		
		
		//1 数据验证
		
		//2 订单生产
		
		//3 网关调用
		
		//4 返回数据封装
		
		//5 格式转换
				
		return JSON.toJSONString(returnInfo);
    }
}
