package com.kingdee.ebank.bank.pab.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseBuckleCapitalTradeResult {
	private String ThirdVoucher; // 转账凭证号
	private String PayType; // 银行流水号
	private String SrcAccNo; // 客户自定义凭证号
	private String TotalNum; // 货币类型
	private String TotalAmount; // 付款人账户名称
	private String OutAcctNo; //付款人账户
	private List<EnterpriseBuckleCapitalTradeItemResult>  list;
	
	@XmlElement(name = "ThirdVoucher")
	public String getThirdVoucher() {
		return ThirdVoucher;
	}
	public void setThirdVoucher(String thirdVoucher) {
		ThirdVoucher = thirdVoucher;
	}
	@XmlElement(name = "PayType")
	public String getPayType() {
		return PayType;
	}
	public void setPayType(String payType) {
		PayType = payType;
	}
	@XmlElement(name = "SrcAccNo")
	public String getSrcAccNo() {
		return SrcAccNo;
	}
	public void setSrcAccNo(String srcAccNo) {
		SrcAccNo = srcAccNo;
	}
	@XmlElement(name = "TotalNum")
	public String getTotalNum() {
		return TotalNum;
	}
	public void setTotalNum(String totalNum) {
		TotalNum = totalNum;
	}
	@XmlElement(name = "TotalAmount")
	public String getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}
	@XmlElement(name = "OutAcctNo")
	public String getOutAcctNo() {
		return OutAcctNo;
	}
	public void setOutAcctNo(String outAcctNo) {
		OutAcctNo = outAcctNo;
	}
	@XmlElement(name = "list")
	public List<EnterpriseBuckleCapitalTradeItemResult> getList() {
		return list;
	}
	public void setList(List<EnterpriseBuckleCapitalTradeItemResult> list) {
		this.list = list;
	}
}
