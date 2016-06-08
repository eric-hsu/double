package com.kingdee.ebank.vo;

import java.util.Date;


public class TradeRecordItem extends BaseVo{
	
	/**
	 * 订单记录t_trade_record表id号
	 */
	private int recordId;
	
	/**
	 * 交易类型，0 本行代付，1 跨行代付，2 本行代扣，3 跨行代扣',
	 */
	private String itemType;
	
	/**
	 * 状态,0失败、1成功、2处理中
	 */
	private int itemStatus;

	/**
	 * 客户订单号
	 */
	private String itemOrderno;

	/**
	 * 交易金额，以分为单位
	 */
	private Integer itemAmount; // 商户交易金额,以分为单位

	/**
	 * 币种，RMB表示人民币
	 */
	private String itemCurrency;
	
	/**
	 * 支付行
	 */
	private String itemOutTradeNo;
	
	/**
	 * 支付行
	 */
	private String payBankCode;
	
	/**
	 * 支付账号
	 */
	private String payAccount;
	
	/**
	 * 支付户名
	 */
	private String payAccountName;
	
	/**
	 * 收款行
	 */
	private String receiveBankCode;
	
	/**
	 * 收款账号
	 */
	private String receiveAccount;
	
	/**
	 * 收款户名
	 */
	private String receiveAccountName;

	/**
	 * 银行返回状态
	 */
	private String channelStatus;
	
	/**
	 * 银行交易金额
	 */
	private String channelAmount;
	
	/**
	 * 银行交易流水
	 */
	private String channelTradeNo;
	
	/**
	 * 银行交易币种
	 */
	private String channelCurrency;
	
	/**
	 * 银行交易时间
	 */
	private Date channelTradeDate;
	
	/**
	 * 银行相关交易描述
	 */
	private String channelDescription;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public int getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(int itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getItemOrderno() {
		return itemOrderno;
	}

	public void setItemOrderno(String itemOrderno) {
		this.itemOrderno = itemOrderno;
	}

	public Integer getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getItemCurrency() {
		return itemCurrency;
	}

	public void setItemCurrency(String itemCurrency) {
		this.itemCurrency = itemCurrency;
	}

	public String getItemOutTradeNo() {
		return itemOutTradeNo;
	}

	public void setItemOutTradeNo(String itemOutTradeNo) {
		this.itemOutTradeNo = itemOutTradeNo;
	}

	public String getPayBankCode() {
		return payBankCode;
	}

	public void setPayBankCode(String payBankCode) {
		this.payBankCode = payBankCode;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPayAccountName() {
		return payAccountName;
	}

	public void setPayAccountName(String payAccountName) {
		this.payAccountName = payAccountName;
	}

	public String getReceiveBankCode() {
		return receiveBankCode;
	}

	public void setReceiveBankCode(String receiveBankCode) {
		this.receiveBankCode = receiveBankCode;
	}

	public String getReceiveAccount() {
		return receiveAccount;
	}

	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}

	public String getReceiveAccountName() {
		return receiveAccountName;
	}

	public void setReceiveAccountName(String receiveAccountName) {
		this.receiveAccountName = receiveAccountName;
	}

	public String getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(String channelStatus) {
		this.channelStatus = channelStatus;
	}

	public String getChannelAmount() {
		return channelAmount;
	}

	public void setChannelAmount(String channelAmount) {
		this.channelAmount = channelAmount;
	}

	public String getChannelCurrency() {
		return channelCurrency;
	}

	public void setChannelCurrency(String channelCurrency) {
		this.channelCurrency = channelCurrency;
	}

	public String getChannelTradeNo() {
		return channelTradeNo;
	}

	public void setChannelTradeNo(String channelTradeNo) {
		this.channelTradeNo = channelTradeNo;
	}

	public Date getChannelTradeDate() {
		return channelTradeDate;
	}

	public void setChannelTradeDate(Date channelTradeDate) {
		this.channelTradeDate = channelTradeDate;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}
}