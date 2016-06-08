package com.kingdee.ebank.bank.pab.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseSingleCapitalQueryResult {
	private String OrigThirdVoucher; // 转账凭证号
	private String FrontLogNo; // 银行流水号
	private String CstInnerFlowNo; // 客户自定义凭证号
	private String CcyCode; // 货币类型
	private String OutAcctBankName; // 付款人账户名称
	private String OutAcctNo; //付款人账户
	private String InAcctBankName; // 收款人开户行名称
	private String InAcctNo; // 收款人账户
	private String InAcctName; // 收款人账户户名
	private String TranAmount; //交易金额
	private String UnionFlag; // 行内跨行标志
	private String Stt; // 手续费
	private String IsBack; // 邮电费
	private String BackRem; // 银行返回传票号
	private String Yhcljg; // 银行返回流水号
	private String SysFlag; // 转账短信通知手机号码
	
	private String Fee; // 转账短信通知手机号码
	private String TransBsn; // 转账短信通知手机号码
	private String submitTime; // 转账短信通知手机号码
	private String AccountDate; // 转账短信通知手机号码
	private String hostFlowNo; // 转账短信通知手机号码
	private String hostErrorCode; // 转账短信通知手机号码
	private String ProxyPayName; // 转账短信通知手机号码
	private String ProxyPayAcc; // 转账短信通知手机号码
	private String ProxyPayBankName; // 转账短信通知手机号码
	
	
	@XmlElement(name = "OrigThirdVoucher")
	public String getOrigThirdVoucher() {
		return OrigThirdVoucher;
	}


	public void setOrigThirdVoucher(String origThirdVoucher) {
		OrigThirdVoucher = origThirdVoucher;
	}

	@XmlElement(name = "FrontLogNo")
	public String getFrontLogNo() {
		return FrontLogNo;
	}


	public void setFrontLogNo(String frontLogNo) {
		FrontLogNo = frontLogNo;
	}

	@XmlElement(name = "CstInnerFlowNo")
	public String getCstInnerFlowNo() {
		return CstInnerFlowNo;
	}


	public void setCstInnerFlowNo(String cstInnerFlowNo) {
		CstInnerFlowNo = cstInnerFlowNo;
	}

	@XmlElement(name = "CcyCode")
	public String getCcyCode() {
		return CcyCode;
	}


	public void setCcyCode(String ccyCode) {
		CcyCode = ccyCode;
	}

	@XmlElement(name = "OutAcctBankName")
	public String getOutAcctBankName() {
		return OutAcctBankName;
	}


	public void setOutAcctBankName(String outAcctBankName) {
		OutAcctBankName = outAcctBankName;
	}

	@XmlElement(name = "OutAcctNo")
	public String getOutAcctNo() {
		return OutAcctNo;
	}


	public void setOutAcctNo(String outAcctNo) {
		OutAcctNo = outAcctNo;
	}

	@XmlElement(name = "InAcctBankName")
	public String getInAcctBankName() {
		return InAcctBankName;
	}


	public void setInAcctBankName(String inAcctBankName) {
		InAcctBankName = inAcctBankName;
	}

	@XmlElement(name = "InAcctNo")
	public String getInAcctNo() {
		return InAcctNo;
	}


	public void setInAcctNo(String inAcctNo) {
		InAcctNo = inAcctNo;
	}

	@XmlElement(name = "InAcctName")
	public String getInAcctName() {
		return InAcctName;
	}


	public void setInAcctName(String inAcctName) {
		InAcctName = inAcctName;
	}

	@XmlElement(name = "TranAmount")
	public String getTranAmount() {
		return TranAmount;
	}


	public void setTranAmount(String tranAmount) {
		TranAmount = tranAmount;
	}

	@XmlElement(name = "UnionFlag")
	public String getUnionFlag() {
		return UnionFlag;
	}


	public void setUnionFlag(String unionFlag) {
		UnionFlag = unionFlag;
	}

	@XmlElement(name = "Stt")
	public String getStt() {
		return Stt;
	}


	public void setStt(String stt) {
		Stt = stt;
	}

	@XmlElement(name = "IsBack")
	public String getIsBack() {
		return IsBack;
	}


	public void setIsBack(String isBack) {
		IsBack = isBack;
	}

	@XmlElement(name = "BackRem")
	public String getBackRem() {
		return BackRem;
	}


	public void setBackRem(String backRem) {
		BackRem = backRem;
	}

	@XmlElement(name = "Yhcljg")
	public String getYhcljg() {
		return Yhcljg;
	}


	public void setYhcljg(String yhcljg) {
		Yhcljg = yhcljg;
	}

	@XmlElement(name = "SysFlag")
	public String getSysFlag() {
		return SysFlag;
	}


	public void setSysFlag(String sysFlag) {
		SysFlag = sysFlag;
	}

	@XmlElement(name = "Fee")
	public String getFee() {
		return Fee;
	}


	public void setFee(String fee) {
		Fee = fee;
	}

	@XmlElement(name = "TransBsn")
	public String getTransBsn() {
		return TransBsn;
	}


	public void setTransBsn(String transBsn) {
		TransBsn = transBsn;
	}

	@XmlElement(name = "submitTime")
	public String getSubmitTime() {
		return submitTime;
	}


	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	@XmlElement(name = "AccountDate")
	public String getAccountDate() {
		return AccountDate;
	}


	public void setAccountDate(String accountDate) {
		AccountDate = accountDate;
	}

	@XmlElement(name = "hostFlowNo")
	public String getHostFlowNo() {
		return hostFlowNo;
	}


	public void setHostFlowNo(String hostFlowNo) {
		this.hostFlowNo = hostFlowNo;
	}

	@XmlElement(name = "hostErrorCode")
	public String getHostErrorCode() {
		return hostErrorCode;
	}


	public void setHostErrorCode(String hostErrorCode) {
		this.hostErrorCode = hostErrorCode;
	}

	@XmlElement(name = "ProxyPayName")
	public String getProxyPayName() {
		return ProxyPayName;
	}


	public void setProxyPayName(String proxyPayName) {
		ProxyPayName = proxyPayName;
	}

	@XmlElement(name = "ProxyPayAcc")
	public String getProxyPayAcc() {
		return ProxyPayAcc;
	}


	public void setProxyPayAcc(String proxyPayAcc) {
		ProxyPayAcc = proxyPayAcc;
	}

	@XmlElement(name = "ProxyPayBankName")
	public String getProxyPayBankName() {
		return ProxyPayBankName;
	}


	public void setProxyPayBankName(String proxyPayBankName) {
		ProxyPayBankName = proxyPayBankName;
	}
	
}
