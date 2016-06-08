package com.kingdee.ebank.bank.pab.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Result")
public class EnterpriseBatchCapitalQueryResult {
	private String successCts; // 转账凭证号
	private String successAmt; // 银行流水号
	private String faildCts; // 客户自定义凭证号
	private String faildAmt; // 货币类型
	private String processCts; // 付款人账户名称
	private String processAmt; //付款人账户
	private String TotalCts; // 收款人开户行名称
	private String CTotalCts; // 收款人账户
	private String PTotalCts; // 收款人账户户名
	private String IsEnd; //交易金额
	private String batchSTT; // 行内跨行标志
	private String subBatchSTT;
	
	private List<EnterpriseBatchCapitalQueryItemResult>  list = new ArrayList<EnterpriseBatchCapitalQueryItemResult>();
	
	
	@XmlElement(name = "successCts")
	public String getSuccessCts() {
		return successCts;
	}
	public void setSuccessCts(String successCts) {
		this.successCts = successCts;
	}
	@XmlElement(name = "successAmt")
	public String getSuccessAmt() {
		return successAmt;
	}
	public void setSuccessAmt(String successAmt) {
		this.successAmt = successAmt;
	}
	@XmlElement(name = "faildCts")
	public String getFaildCts() {
		return faildCts;
	}
	public void setFaildCts(String faildCts) {
		this.faildCts = faildCts;
	}
	@XmlElement(name = "faildAmt")
	public String getFaildAmt() {
		return faildAmt;
	}
	public void setFaildAmt(String faildAmt) {
		this.faildAmt = faildAmt;
	}
	@XmlElement(name = "processCts")
	public String getProcessCts() {
		return processCts;
	}
	public void setProcessCts(String processCts) {
		this.processCts = processCts;
	}
	@XmlElement(name = "processAmt")
	public String getProcessAmt() {
		return processAmt;
	}
	public void setProcessAmt(String processAmt) {
		this.processAmt = processAmt;
	}
	@XmlElement(name = "TotalCts")
	public String getTotalCts() {
		return TotalCts;
	}
	public void setTotalCts(String totalCts) {
		TotalCts = totalCts;
	}
	@XmlElement(name = "CTotalCts")
	public String getCTotalCts() {
		return CTotalCts;
	}
	public void setCTotalCts(String cTotalCts) {
		CTotalCts = cTotalCts;
	}
	@XmlElement(name = "PTotalCts")
	public String getPTotalCts() {
		return PTotalCts;
	}
	public void setPTotalCts(String pTotalCts) {
		PTotalCts = pTotalCts;
	}
	@XmlElement(name = "IsEnd")
	public String getIsEnd() {
		return IsEnd;
	}
	public void setIsEnd(String isEnd) {
		IsEnd = isEnd;
	}
	@XmlElement(name = "batchSTT")
	public String getBatchSTT() {
		return batchSTT;
	}
	public void setBatchSTT(String batchSTT) {
		this.batchSTT = batchSTT;
	}
	@XmlElement(name = "subBatchSTT")
	public String getSubBatchSTT() {
		return subBatchSTT;
	}
	public void setSubBatchSTT(String subBatchSTT) {
		this.subBatchSTT = subBatchSTT;
	}
	public List<EnterpriseBatchCapitalQueryItemResult> getList() {
		return list;
	}
	public void setList(List<EnterpriseBatchCapitalQueryItemResult> list) {
		this.list = list;
	}
}
