package com.kingdee.ebank.vo;

import java.util.ArrayList;
import java.util.List;

public class TradeRecord extends BaseVo{
	
		/**
		 * 客户号，接入该系统客户账号
		 */
		private String customerNo;

		/**
		 * 交易金额，以分为单位
		 */
		private Integer customerTotalamount; // 商户交易金额,以分为单位
		
		/**
		 * 币种，RMB表示人民币
		 */
		private String customerCurrency;
		
		/**
		 * 订单号
		 */
		private String customerOrderno;
		/**
		 * 异步地址,当使用socket长连接的时候使用到，使用短连接无该字段
		 */
		private String customerAsynurl;

		/**
		 * 银企在线平台交易流水号
		 */
		private String outTradeNo;
		
		/**
		 * 状态 ：0 失败，1成功，2交易中
		 */
		private int status;
		
		/**
		 * 渠道：平安pab，民生cmbc
		 */
		private String channel;
		
		private List<TradeRecordItem> items = new ArrayList<TradeRecordItem>();

		public String getCustomerNo() {
			return customerNo;
		}

		public void setCustomerNo(String customerNo) {
			this.customerNo = customerNo;
		}

		public Integer getCustomerTotalamount() {
			return customerTotalamount;
		}

		public void setCustomerTotalamount(Integer customerTotalamount) {
			this.customerTotalamount = customerTotalamount;
		}

		public String getCustomerCurrency() {
			return customerCurrency;
		}

		public void setCustomerCurrency(String customerCurrency) {
			this.customerCurrency = customerCurrency;
		}

		public String getCustomerOrderno() {
			return customerOrderno;
		}

		public void setCustomerOrderno(String customerOrderno) {
			this.customerOrderno = customerOrderno;
		}

		public String getCustomerAsynurl() {
			return customerAsynurl;
		}

		public void setCustomerAsynurl(String customerAsynurl) {
			this.customerAsynurl = customerAsynurl;
		}

		public String getOutTradeNo() {
			return outTradeNo;
		}

		public void setOutTradeNo(String outTradeNo) {
			this.outTradeNo = outTradeNo;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}
		
		public String getChannel() {
			return channel;
		}

		public void setChannel(String channel) {
			this.channel = channel;
		}

		public List<TradeRecordItem> getItems() {
			return items;
		}

		public void setItems(List<TradeRecordItem> items) {
			this.items = items;
		}
		
		public void addItems(TradeRecordItem tradeRecordItem){
			items.add(tradeRecordItem);
		}
}