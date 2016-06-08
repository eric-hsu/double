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
 * 本行代扣
 * @author pc
 *
 */
@Service
public class CmbcBuckleTradeService extends AbstractCmbcService {
	private static Logger logger = Logger.getLogger(CmbcBuckleTradeService.class);

	@Autowired
	private TradeRecordService tradeRecordService;
	
	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	/**
     * 本行代扣
     * 
     * @param params
     * @return String
     */
	public String perform(Map<String, Object> params) {
		logger.info("**************************本行代扣***************************");
		logger.info("提交参数："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		try {
			//1 数据验证
			OperateResult operateResult = CheckCmbcRequestParam.checkcCb(params);
			if(!operateResult.isSuccess()){
				returnInfo.setErrormsg(operateResult.getMessage());
				logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
				return  JSON.toJSONString(returnInfo);
			}

			//2 生成交易任务单,一个订单只能操作一次
			TradeRecord tradeRecord = tradeRecordService.findByCustomerOrderno((String)params.get("ORDERNO"));
			if(tradeRecord!=null){
				returnInfo.setErrormsg("ORDERNO IS EXIST");
				logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
				return  JSON.toJSONString(returnInfo);
			}
			TradeRecord tradeRecordNew = CmbcHandle.createCBTradeVo(params);
			int id = tradeRecordService.add(tradeRecordNew);
			logger.info("本行代扣交易信息保存，记录id["+id+"]");
			
			//3 组装发送字符串
			String sendstr = CmbcHandle.createCbSendstr(params);
			logger.info("本行代扣交易数据发送：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t发送内容["+sendstr+"]"); 

			
			//4 网关调用,短连接直接发送同步获取数据
			String returnXml = SocketUtil.sendCbstr(sendstr);
			//5 返回数据封装
			
			logger.info("本行代扣交易数据返回：\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\t同步返回内容["+returnXml+"]"); 
			String xml = returnXml.substring(21, returnXml.length()-32);
			Map<String, Object> map = CmbcHandle.xmlToMap(xml);
			
			Object TRAN_ID = map.get("TRAN_ID");
			Object RESP_TYPE = map.get("RESP_TYPE");
			Object RESP_CODE = map.get("RESP_CODE");
			Object RESP_MSG = map.get("RESP_MSG");
			Object BANK_TRAN_ID = map.get("BANK_TRAN_ID");
			Object BANK_TRAN_DATE = map.get("BANK_TRAN_DATE");
			
			//4 网关发送使用socket发送数据到民生银行
//			tradeVo.setBankTradeNo((String)BANK_TRAN_ID);
//			tradeVo.setBankAmount(tradeVo.getAmount());
//			tradeVo.setBankDescription("RESP_CODE["+RESP_CODE+"],"+"RESP_MSG["+RESP_MSG+"]");
//			tradeVo.setBankTradeDate(DateUtil.StringToDate((String)BANK_TRAN_DATE,DateStyle.YYYYMMDDEN));
//			
//			String bankstatus = (String)RESP_TYPE;
//			tradeVo.setBankStatus(bankstatus);
//			if(CmbcConstants.CB_CMBC_STATUS_SUCCESS.equals(bankstatus)){
//				tradeVo.setStatus(1);
//			}else if(CmbcConstants.CB_CMBC_STATUS_FAIL.equals(bankstatus)){
//				tradeVo.setStatus(0);
//			}
//			
			tradeRecordService.updateTradeRecordByid(tradeRecordNew);
			
		    returnInfo.setSuccess(true);
		    returnInfo.setErrormsg("");
		    returnInfo.setData(CmbcHandle.cmbcBuckleTradeReturnStr(tradeRecordNew));
			
			//6 格式转换
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JSON.toJSONString(returnInfo);
    }
}
