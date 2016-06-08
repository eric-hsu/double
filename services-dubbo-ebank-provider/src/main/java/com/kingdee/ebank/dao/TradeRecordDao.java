package com.kingdee.ebank.dao;

import com.kingdee.ebank.vo.TradeRecord;

public interface TradeRecordDao {

	/**
	 * 插入请求对象
	 * @param message
	 * @return
	 */
	public int add(TradeRecord tradeRecord);
	
	/**
	 * 查询请求对象
	 * @param message
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
	 * 更新订单状态及交易金额
	 * @param id
	 * @return
	 */
	public void updateTradeRecordByid(TradeRecord tradeRecord);
	
}
