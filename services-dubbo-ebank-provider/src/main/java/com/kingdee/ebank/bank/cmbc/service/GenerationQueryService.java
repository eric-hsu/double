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
import com.kingdee.ebank.bank.cmbc.util.CmbcConstants;
import com.kingdee.ebank.bank.cmbc.util.CmbcHandle;
import com.kingdee.ebank.bank.cmbc.util.socket.SocketUtil;
import com.kingdee.ebank.common.OperateResult;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.service.TradeRecordService;
import com.kingdee.ebank.util.DateStyle;
import com.kingdee.ebank.util.DateUtil;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.vo.TradeRecord;
/**
 * 代付-账务交易结果查询请求
 * @author pc
 *
 */
@Service
public class GenerationQueryService extends AbstractCmbcService {
	private static Logger logger = Logger.getLogger(GenerationQueryService.class);
	@Autowired
	private TradeRecordService tradeRecordService;
	
	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	/**
     * 代付-账务交易结果查询请求
     * 
     * @param params
     * @return String
     */
	public String perform(Map<String, Object> params) {
		logger.info("**************************代付-账务交易结果查询请求***************************");
		logger.info("提交参数："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		try {
			
		//1 数据验证 ？查询请求需要的数据待确定
		OperateResult operateResult = CheckCmbcRequestParam.checkcPquery(params);
		if(!operateResult.isSuccess()){
			returnInfo.setErrormsg(operateResult.getMessage());
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}
		//2 查询订单
		TradeRecord tradeRecord = tradeRecordService.findByCustomerOrderno((String)params.get("ORDERNO"));
		if(tradeRecord==null){
			returnInfo.setErrormsg("ORDERNO NOT EXIST");
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}
		
		//3 成功和失败订单直接返回
		if(tradeRecord.getStatus()!=2){
			returnInfo.setSuccess(true);
		    returnInfo.setErrormsg("");
		    returnInfo.setData(CmbcHandle.generationQueryReturnStr(tradeRecord));
		    return JSON.toJSONString(returnInfo);
		}
		
		//3 网关调用
		//3 组装发送字符串
		String sendstr = CmbcHandle.createGquerySendstr(params);
		logger.info("代付查询到账数据发送：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t发送内容["+sendstr+"]"); 

		//4 网关发送使用socket发送数据到民生银行
	    String cmbcbackstr = SocketUtil.sendGpstr(sendstr);
	    
	    String xml = cmbcbackstr.substring(21, cmbcbackstr.length()-32);
		Map<String, Object> map = CmbcHandle.xmlToMap(xml);
		String outTradeNo = (String) map.get("ORI_BANK_TRAN_ID");
		String RESP_TYPE = (String) map.get("ORI_RESP_TYPE");
		String RESP_CODE = (String) map.get("ORI_RESP_CODE");
		String RESP_MSG = (String) map.get("ORI_RESP_MSG");
		String ORI_BANK_TRAN_ID = (String) map.get("ORI_BANK_TRAN_ID");
		String ORI_BANK_TRAN_DATE = (String) map.get("ORI_BANK_TRAN_DATE");
		
		TradeRecord tradeVo = tradeRecordService.findByOutTradeNo(outTradeNo);
//		tradeVo.setBankTradeNo(ORI_BANK_TRAN_ID);
//		tradeVo.setBankStatus(RESP_TYPE);
//		tradeVo.setBankAmount(tradeVo.getAmount());
//		tradeVo.setBankDescription("RESP_CODE["+RESP_CODE+"],"+"RESP_MSG["+RESP_MSG+"]");
//		tradeVo.setBankTradeDate(DateUtil.StringToDate(ORI_BANK_TRAN_DATE,DateStyle.YYYYMMDDEN));
//		
//		if(CmbcConstants.GP_CMBC_STATUS_SUCCESS.equals(RESP_TYPE)){
//			tradeVo.setStatus(1);
//		}else if(CmbcConstants.GP_CMBC_STATUS_FAIL.equals(RESP_TYPE)){
//			tradeVo.setStatus(0);
//		}
		tradeRecordService.updateTradeRecordByid(tradeVo);
	    
		//5 同步返回操作结果
	    returnInfo.setSuccess(true);
	    returnInfo.setErrormsg("");
	    returnInfo.setData(CmbcHandle.generationQueryReturnStr(tradeVo));
	    
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg("系统异常,请联系技术人员处理！");
		}
		return JSON.toJSONString(returnInfo);
    }
}
