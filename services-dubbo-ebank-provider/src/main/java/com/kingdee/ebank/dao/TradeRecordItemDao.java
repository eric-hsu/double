package com.kingdee.ebank.dao;

import java.util.List;

import com.kingdee.ebank.vo.TradeRecordItem;

public interface TradeRecordItemDao {

	/**
	 * 插入请求对象
	 * @param message
	 * @return
	 */
	public int add(TradeRecordItem tradeRecordItem);
	
	/**
	 * 插入请求对象
	 * @param message
	 * @return
	 */
	public void addBatch(List<TradeRecordItem> list);
	
	/**
	 * 查询请求对象
	 * @param message
	 * @return
	 */
	public TradeRecordItem findByItemOrderno(String itemOrderno);
	
	/**
	 * 查询请求对象
	 * @param id
	 * @return
	 */
	public TradeRecordItem findByItemOutTradeNo(String itemOutTradeNo);

	/**
	 * 更新订单状态及交易金额
	 * @param id
	 * @return
	 */
	public void updateTradeRecordItemByid(TradeRecordItem tradeRecordItem);
}
