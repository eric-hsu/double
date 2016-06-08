package com.kingdee.ebank.bank.cmbc.util;

public class CmbcConstants {

	//代付-交易服务吗
	public static final String GP_PAY_CODE="1002";
		
	//代付-查询服务吗
	public static final String GP_QUERY_CODE="3002";
	
	//交易类型-代付
	public static final String TRADE_TYPE_GP="0";
	
	//交易类型-本行代扣
	public static final String TRADE_TYPE_CB="1";
	
	//交易类型-跨行代扣
	public static final String TRADE_TYPE_CC="2";
	
	
	//代付-交易成功
	public static final String GP_SYS_STATUS_SUCCESS="1";
	
	//代付-交易失败
	public static final String GP_SYS_STATUS_FAIL="0";
	
	//代付-不确定（对于不确定交易，可以通过账务交易结果查询，一般3分钟后会有准确结果）
	public static final String GP_SYS_STATUS_WAIT="2";
	
	//代付-民生交易成功
	public static final String GP_CMBC_STATUS_SUCCESS="S";
	
	//代付-民生交易失败
	public static final String GP_CMBC_STATUS_FAIL="E";
	
	//代付-民生，不确定（对于不确定交易，可以通过账务交易结果查询，一般3分钟后会有准确结果）
	public static final String GP_CMBC_STATUS_WAIT="R";
	
	
	//本行代扣-民生交易成功
	public static final String CB_CMBC_STATUS_SUCCESS="S";
	
	//本行代扣-民生交易失败
	public static final String CB_CMBC_STATUS_FAIL="E";
	
	//本行代扣-民生，不确定（对于不确定交易，可以通过账务交易结果查询，一般3分钟后会有准确结果）
	public static final String CB_CMBC_STATUS_WAIT="R";
}
