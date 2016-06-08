package com.kingdee.ebank.service;

import com.kingdee.ebank.vo.TradeRecord;

public interface TradeRecordService {
	/**
	 * 插入请求对象
	 * @param message
	 * @return
	 */
	public int add(TradeRecord tradeRecord);
	
	/**
	 * 查询请求对象
	 * @param id
	 * @return
	 */
	public TradeRecord findByCustomerOrderno(String customerOrderno);
	
	/**
	 * 查询请求对象
	 * @param id
	 * @return
	 */
	public TradeRecord findByOutTradeNo(String outTradeNo);
	
	/**
	 * 更新订单信息
	 * @param id
	 * @return
	 */
	public void updateTradeRecordByid(TradeRecord tradeRecord);
}
