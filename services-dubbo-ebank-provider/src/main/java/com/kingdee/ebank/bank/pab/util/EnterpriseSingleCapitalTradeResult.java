package com.kingdee.ebank.bank.pab.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseSingleCapitalTradeResult {
	private String ThirdVoucher; // 转账凭证号
	private String FrontLogNo; // 银行流水号
	private String CstInnerFlowNo; // 客户自定义凭证号
	private String CcyCode; // 货币类型
	private String OutAcctName; // 付款人账户名称
	private String OutAcctNo; //付款人账户
	private String InAcctBankName; // 收款人开户行名称
	private String InAcctNo; // 收款人账户
	private String InAcctName; // 收款人账户户名
	private String TranAmount; //交易金额
	private String UnionFlag; // 行内跨行标志
	private String Fee1; // 手续费
	private String Fee2; // 邮电费
	private String SOA_VOUCHER; // 银行返回传票号
	private String hostFlowNo; // 银行返回流水号
	private String Mobile; // 转账短信通知手机号码
	
	@XmlElement(name = "ThirdVoucher")
	public String getThirdVoucher() {
		return ThirdVoucher;
	}
	public void setThirdVoucher(String thirdVoucher) {
		ThirdVoucher = thirdVoucher;
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
	@XmlElement(name = "OutAcctName")
	public String getOutAcctName() {
		return OutAcctName;
	}
	public void setOutAcctName(String outAcctName) {
		OutAcctName = outAcctName;
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
	@XmlElement(name = "Fee1")
	public String getFee1() {
		return Fee1;
	}
	public void setFee1(String fee1) {
		Fee1 = fee1;
	}
	@XmlElement(name = "Fee2")
	public String getFee2() {
		return Fee2;
	}
	public void setFee2(String fee2) {
		Fee2 = fee2;
	}
	@XmlElement(name = "SOA_VOUCHER")
	public String getSOA_VOUCHER() {
		return SOA_VOUCHER;
	}
	public void setSOA_VOUCHER(String sOA_VOUCHER) {
		SOA_VOUCHER = sOA_VOUCHER;
	}
	@XmlElement(name = "hostFlowNo")
	public String getHostFlowNo() {
		return hostFlowNo;
	}
	public void setHostFlowNo(String hostFlowNo) {
		this.hostFlowNo = hostFlowNo;
	}
	@XmlElement(name = "Mobile")
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
}
