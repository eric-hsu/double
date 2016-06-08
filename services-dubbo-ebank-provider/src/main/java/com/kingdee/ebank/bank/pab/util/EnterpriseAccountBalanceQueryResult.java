package com.kingdee.ebank.bank.pab.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseAccountBalanceQueryResult {
	
	private String Account; // 账号
	private String CcyCode; // 货币类型
	private String CcyType; // 钞汇标志
	private String AccountName; // 账号
	private String Balance; //可用余额
	private String TotalAmount; // 账面余额
	
	@XmlElement(name = "Account")
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	@XmlElement(name = "CcyCode")
	public String getCcyCode() {
		return CcyCode;
	}
	public void setCcyCode(String ccyCode) {
		CcyCode = ccyCode;
	}
	@XmlElement(name = "CcyType")
	public String getCcyType() {
		return CcyType;
	}
	public void setCcyType(String ccyType) {
		CcyType = ccyType;
	}
	@XmlElement(name = "AccountName")
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	@XmlElement(name = "Balance")
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	@XmlElement(name = "TotalAmount")
	public String getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}
}
