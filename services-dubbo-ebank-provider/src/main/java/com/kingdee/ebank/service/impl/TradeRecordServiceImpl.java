package com.kingdee.ebank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kingdee.ebank.dao.TradeRecordDao;
import com.kingdee.ebank.service.TradeRecordService;
import com.kingdee.ebank.vo.TradeRecord;

@Component("tradeRecordService")
public class TradeRecordServiceImpl implements TradeRecordService {

	@Autowired
	private TradeRecordDao tradeRecordDao;
	
	public int add(TradeRecord tradeRecord) {
		return tradeRecordDao.add(tradeRecord);
	}

	public TradeRecord findByCustomerOrderno(String customerOrderno){
		return tradeRecordDao.findByCustomerOrderno(customerOrderno);
	}
	
	/**
	 * 查询请求对象
	 * @param id
	 * @return
	 */
	public TradeRecord findByOutTradeNo(String outTradeNo){
		return tradeRecordDao.findByOutTradeNo(outTradeNo);
	}
	
	/**
	 * 更新订单状态及交易金额
	 * @param id
	 * @return
	 */
	public void updateTradeRecordByid(TradeRecord tradeRecord){
		tradeRecordDao.updateTradeRecordByid(tradeRecord);
	}

}
