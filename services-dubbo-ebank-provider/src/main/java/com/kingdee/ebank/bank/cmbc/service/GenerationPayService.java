package com.kingdee.ebank.bank.cmbc.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.bank.cmbc.AbstractCmbcService;
import com.kingdee.ebank.bank.cmbc.util.CheckCmbcRequestParam;
import com.kingdee.ebank.bank.cmbc.util.CmbcHandle;
import com.kingdee.ebank.bank.cmbc.util.socket.SocketSend;
import com.kingdee.ebank.bank.cmbc.util.socket.SocketUtil;
import com.kingdee.ebank.common.OperateResult;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.service.TradeRecordService;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.vo.TradeRecord;
/**
 * 代付
 * @author pc
 *
 */
@Service
public class GenerationPayService extends AbstractCmbcService {
	private static Logger logger = Logger.getLogger(CrossBuckleTradeService.class);
	@Autowired
	private TradeRecordService tradeRecordService;
	
	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	/**
     * 代付
     * 
     * @param params
     * @return String
     */
	public String perform(Map<String, Object> params) {
		logger.info("**************************代付处理***************************");
		logger.info("提交参数："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		
		try {
			
		//1 数据验证
		OperateResult operateResult = CheckCmbcRequestParam.checkcPp(params);
		if(!operateResult.isSuccess()){
			returnInfo.setErrormsg(operateResult.getMessage());
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}

		//2 生成交易任务单,一个订单只能操作一次
		TradeRecord tradeRecord = tradeRecordService.findByCustomerOrderno((String)params.get("ORDERNO"));
		if(tradeRecord!=null){
			returnInfo.setErrormsg("CustomerOrderno is exist");
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}
		TradeRecord tradeRecordNew = CmbcHandle.createGpTradeVo(params);
		int id = tradeRecordService.add(tradeRecordNew);
		logger.info("代付交易信息保存，记录id["+id+"]");
		
		
		//3 组装发送字符串
		String sendstr = CmbcHandle.createGpSendstr(params);
		logger.info("代付交易数据发送：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t发送内容["+sendstr+"]"); 

		//4 网关发送使用socket发送数据到民生银行
		SocketSend socketSend = new SocketSend(SocketUtil.getGpSocket());
	    socketSend.send(sendstr);
	    
	    returnInfo.setSuccess(true);
	    returnInfo.setErrormsg("");
	    returnInfo.setData(CmbcHandle.generationPayReturnStr(tradeRecordNew));
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg("系统异常,请联系技术人员处理！");
		}
		return JSON.toJSONString(returnInfo);
    }
}
