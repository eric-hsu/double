package com.kingdee.ebank.utils;

public class Constants {
	
	//民生银行
	public static final String CHANNEL_CMBC="cmbc";
	
	//民生银行-代付
	public static final String TYPE_CMBC_GP="0000";
	
	//代付-账务交易结果查询请求
	public static final String TYPE_CMBC_GP_QUERY="0001";

	//民生银行-本行代扣
	public static final String TYPE_CMBC_BB="0002";
	
	//本行代扣-账务交易结果查询请求
	public static final String TYPE_CMBC_BB_QUERY="0003";
	
	//民生银行-跨行代扣
	public static final String TYPE_CMBC_CB="0004";

	//跨行代扣-实时跨行代扣结果查询
	public static final String TYPE_CMBC_CB_QUERY="0005";
	
	//白名单添加授权
	public static final String TYPE_CMBC_AUTH="0006";
	
	//白名单授权查询
	public static final String TYPE_CMBC_AUTH_QUERY="0007";
	
	//系统异常
	public static final String ERROR_SYS="系统异常，请联系金蝶支付技术人员处理";
	
	//请求参数不合法
	public static final String ERROR_NO_DATA="请求参数不合法";
	
	//用户不存在
	public static final String ERROR_NO_CUSTOMER="用户不存在";
	
	//用户未激活
	public static final String ERROR_ACTIVE_CUSTOMER="用户未激活";
	
	//用户证书异常
	public static final String ERROR_CERTIFYCATE_CUSTOMER="用户证书配置异常";
	
	//签名异常
	public static final String ERROR_VERIFY_CUSTOMER="签名异常";
	
	//编码
	public static final String input_charset="utf-8";
	
}
