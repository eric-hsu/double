package com.kingdee.ebank.bank.cmbc.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kingdee.ebank.bank.cmbc.GpasynReceiveService;
import com.kingdee.ebank.bank.cmbc.util.CmbcConstants;
import com.kingdee.ebank.bank.cmbc.util.CmbcHandle;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.service.TradeRecordService;
import com.kingdee.ebank.util.DateStyle;
import com.kingdee.ebank.util.DateUtil;
import com.kingdee.ebank.vo.TradeRecord;

/** 
 * 处理服务端发回的对象，可实现该接口。 
 */
@Component("gpasynReceiveService")
public class GpasynReceiveServiceImpl implements GpasynReceiveService {
	
	private static Logger logger = Logger.getLogger(GpasynReceiveServiceImpl.class);

	@Autowired
	private TradeRecordService tradeRecordService;
	
	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	
	public void doHandle(String receive) {
		try { 
			System.out.println("***********************************************");

			logger.info("民生银行异步返回处理开始：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t返回数据["+receive+"]"); 

			String servicecode = receive.substring(6, 21).trim();
			//交易结果长连接处理
			if(CmbcConstants.GP_PAY_CODE.equals(servicecode)){
				String xml = receive.substring(21, receive.length()-32);
				Map<String, Object> map = CmbcHandle.xmlToMap(xml);
				String outTradeNo = (String) map.get("TRAN_ID");
				String RESP_TYPE = (String) map.get("RESP_TYPE");
				String RESP_CODE = (String) map.get("RESP_CODE");
				String RESP_MSG = (String) map.get("RESP_MSG");
				String BANK_TRAN_ID = (String) map.get("BANK_TRAN_ID");
				String BANK_TRAN_DATE = (String) map.get("BANK_TRAN_DATE");

				TradeRecord tradeVo = tradeRecordService.findByOutTradeNo(outTradeNo);
//				tradeVo.setBankTradeNo(BANK_TRAN_ID);
//				tradeVo.setBankStatus(RESP_TYPE);
//				tradeVo.setBankAmount(tradeVo.getAmount());
//				tradeVo.setBankDescription("RESP_CODE["+RESP_CODE+"],"+"RESP_MSG["+RESP_MSG+"]");
//				tradeVo.setBankTradeDate(DateUtil.StringToDate(BANK_TRAN_DATE,DateStyle.YYYYMMDDEN));
//				
				if(CmbcConstants.GP_CMBC_STATUS_SUCCESS.equals(RESP_TYPE)){
					tradeVo.setStatus(1);
				}else if(CmbcConstants.GP_CMBC_STATUS_FAIL.equals(RESP_TYPE)){
					tradeVo.setStatus(0);
				}
				tradeRecordService.updateTradeRecordByid(tradeVo);
				
				String returnback = CmbcHandle.generationPayReturnStr(tradeVo);
				logger.info("处理结束：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t异步返回["+returnback+"]");

				//CmbcHandle.callBack(returnback,tradeVo.getAsynurl());
			}
			
			//查询结果长连接处理
			else if(CmbcConstants.GP_QUERY_CODE.equals(servicecode)){
				String xml = receive.substring(21, receive.length()-32);
				Map<String, Object> map = CmbcHandle.xmlToMap(xml);
				String outTradeNo = (String) map.get("ORI_BANK_TRAN_ID");
				String RESP_TYPE = (String) map.get("ORI_RESP_TYPE");
				String RESP_CODE = (String) map.get("ORI_RESP_CODE");
				String RESP_MSG = (String) map.get("ORI_RESP_MSG");
				String ORI_BANK_TRAN_ID = (String) map.get("ORI_BANK_TRAN_ID");
				String ORI_BANK_TRAN_DATE = (String) map.get("ORI_BANK_TRAN_DATE");

				TradeRecord tradeVo = tradeRecordService.findByOutTradeNo(outTradeNo);
//				tradeVo.setBankTradeNo(ORI_BANK_TRAN_ID);
//				tradeVo.setBankStatus(RESP_TYPE);
//				tradeVo.setBankAmount(tradeVo.getAmount());
//				tradeVo.setBankDescription("RESP_CODE["+RESP_CODE+"],"+"RESP_MSG["+RESP_MSG+"]");
//				tradeVo.setBankTradeDate(DateUtil.StringToDate(ORI_BANK_TRAN_DATE,DateStyle.YYYYMMDDEN));
//				
//				if(CmbcConstants.GP_CMBC_STATUS_SUCCESS.equals(RESP_TYPE)){
//					tradeVo.setStatus(1);
//				}else if(CmbcConstants.GP_CMBC_STATUS_FAIL.equals(RESP_TYPE)){
//					tradeVo.setStatus(0);
//				}
				tradeRecordService.updateTradeRecordByid(tradeVo);
				
				String returnback = CmbcHandle.generationPayReturnStr(tradeVo);
				logger.info("处理结束：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t异步返回["+returnback+"]");

				//CmbcHandle.callBack(returnback,tradeVo.getAsynurl());
			}
			
			
		 } catch (Exception e) {
				logger.info("：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t异步返回异常，异常信息["+e.getMessage()+"]");
				e.printStackTrace();
		 }
	}
}