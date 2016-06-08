package com.kingdee.ebank.bank.pab.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseBuckleCapitalTradeItemResult {
	private String SThirdVoucher; // 转账凭证号
	private String OppAccNo; // 银行流水号
	private String OppAccName; // 客户自定义凭证号
	private String Amount; // 货币类型
	private String PostScript; // 付款人账户名称
	private String Fee; //付款人账户
	private String stt; //付款人账户
	
	@XmlElement(name = "SThirdVoucher")
	public String getSThirdVoucher() {
		return SThirdVoucher;
	}
	public void setSThirdVoucher(String sThirdVoucher) {
		SThirdVoucher = sThirdVoucher;
	}
	@XmlElement(name = "OppAccNo")
	public String getOppAccNo() {
		return OppAccNo;
	}
	public void setOppAccNo(String oppAccNo) {
		OppAccNo = oppAccNo;
	}
	@XmlElement(name = "OppAccName")
	public String getOppAccName() {
		return OppAccName;
	}
	public void setOppAccName(String oppAccName) {
		OppAccName = oppAccName;
	}
	@XmlElement(name = "Amount")
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	@XmlElement(name = "PostScript")
	public String getPostScript() {
		return PostScript;
	}
	public void setPostScript(String postScript) {
		PostScript = postScript;
	}
	@XmlElement(name = "Fee")
	public String getFee() {
		return Fee;
	}
	public void setFee(String fee) {
		Fee = fee;
	}
	@XmlElement(name = "stt")
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
}
