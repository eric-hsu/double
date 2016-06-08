package com.kingdee.ebank.bank.pab.util;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseBatchCapitalTradeResult {
	private String ThirdVoucher; // 转账凭证号

	@XmlElement(name = "ThirdVoucher")
	public String getThirdVoucher() {
		return ThirdVoucher;
	}
	public void setThirdVoucher(String thirdVoucher) {
		ThirdVoucher = thirdVoucher;
	}
}
