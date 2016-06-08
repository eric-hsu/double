package com.kingdee.ebank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kingdee.ebank.dao.TradeRecordItemDao;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.vo.TradeRecord;
import com.kingdee.ebank.vo.TradeRecordItem;

@Component("tradeRecordItemService")
public class TradeRecordItemServiceImpl implements TradeRecordItemService {

	@Autowired
	private TradeRecordItemDao tradeRecordItemDao;
	
	/**
	 * 插入请求对象
	 * @param message
	 * @return
	 */
	public int add(TradeRecordItem tradeRecordItem) {
		return tradeRecordItemDao.add(tradeRecordItem);
	}
	
	/**
	 * 批量插入请求对象
	 * @param message
	 * @return
	 */
	public void addBatch(List<TradeRecordItem> list){
		tradeRecordItemDao.addBatch(list);
	}
	
	/**
	 * 查询请求对象
	 * @param id
	 * @return
	 */
	public TradeRecordItem findByItemOrderno(String itemOrderno){
		return tradeRecordItemDao.findByItemOrderno(itemOrderno);
	}
	
	/**
	 * 查询请求对象
	 * @param id
	 * @return
	 */
	public TradeRecordItem findByItemOutTradeNo(String itemOutTradeNo){
		return tradeRecordItemDao.findByItemOutTradeNo(itemOutTradeNo);
	}
	
	/**
	 * 更新订单状态及交易金额
	 * @param id
	 * @return
	 */
	public void updateTradeRecordItemByid(TradeRecordItem tradeRecordItem){
		tradeRecordItemDao.updateTradeRecordItemByid(tradeRecordItem);
	}

}
