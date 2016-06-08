package com.kingdee.ebank.bank.pab.util;

import java.util.Map;

import org.dom4j.Element;

import com.kingdee.ebank.common.OperateResult;


public class CheckPabRequestParam {
	
	/**
     * 企业账户余额查询 [4001] 交易参数验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcEnterpriseAccountBalanceQueryParam(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object account = params.get("account");
		if(account==null){
			operateResult.setMessage("account ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	/**
     * 企业单笔资金划转[4004] 交易参数验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcEnterpriseSingleCapitalTradeParam(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object payAccount = params.get("payAccount");
		if(payAccount==null){
			operateResult.setMessage("payAccount ERROR");
			return operateResult;
		}
		
		Object receiveAccount = params.get("receiveAccount");
		if(receiveAccount==null){
			operateResult.setMessage("receiveAccount ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	/**
     * 单笔转账指令查询[4005] 交易参数验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcEnterpriseSingleCapitalQueryParam(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object customerOrderno = params.get("customerOrderno");
		if(customerOrderno==null){
			operateResult.setMessage("customerOrderno ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	
	/**
     * 企业大批量资金划转[4018] 交易参数验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcEnterpriseBatchCapitalTradeParam(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object customerOrderno = params.get("customerOrderno");
		if(customerOrderno==null){
			operateResult.setMessage("customerOrderno ERROR");
			return operateResult;
		}
		
		Object payAccount = params.get("payAccount");
		if(payAccount==null){
			operateResult.setMessage("payAccount ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	/**
     * 代发代扣申请接口[4047] 交易参数验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcEnterpriseBuckleCapitalTradeParam(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object customerOrderno = params.get("customerOrderno");
		if(customerOrderno==null){
			operateResult.setMessage("customerOrderno ERROR");
			return operateResult;
		}
		
		Object srcAccNo = params.get("srcAccNo");
		if(srcAccNo==null){
			operateResult.setMessage("srcAccNo ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	/**
     * 代发代扣结果查询接口[4048]
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcEnterpriseBuckleCapitalQueryParam(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object customerOrderno = params.get("customerOrderno");
		if(customerOrderno==null){
			operateResult.setMessage("customerOrderno ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
}
