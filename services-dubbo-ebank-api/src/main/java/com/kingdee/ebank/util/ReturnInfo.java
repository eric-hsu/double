package com.kingdee.ebank.util;

import java.rmi.registry.Registry;

public class ReturnInfo {
	/**
	 * 请求是否成功
	 */
	private boolean success;
	
	/**
	 * 当success 为false时，失败原因
	 */
	private String errormsg;
	
	/**
	 * 当success 为true时，返回数据
	 */
	private String data;

	public ReturnInfo(){}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	private ReturnInfo(Builder builder) {
		this.success = builder.success;
		this.errormsg = builder.errormsg;
		this.data = builder.data;
	}

	public static class Builder implements com.kingdee.ebank.util.Builder<ReturnInfo> {
		private boolean success;
		private String errormsg;
		private String data;

		public Builder(boolean success, String errormsg) {
			this.success = success;
			this.errormsg = errormsg;
		}

		public ReturnInfo build() {
			return new ReturnInfo(this);
		}

		public Builder data(String data) {
			this.data = data;
			return this;
		}
	}
}
