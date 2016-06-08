package com.kingdee.ebank.bank.pab.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.bank.pab.PabAbstractService;
import com.kingdee.ebank.bank.pab.util.CheckPabRequestParam;
import com.kingdee.ebank.bank.pab.util.EnterpriseSingleCapitalTradeResult;
import com.kingdee.ebank.bank.pab.util.PabConstants;
import com.kingdee.ebank.bank.pab.util.PabHandle;
import com.kingdee.ebank.bank.pab.util.PabHttprequest;
import com.kingdee.ebank.common.OperateResult;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.service.TradeRecordService;
import com.kingdee.ebank.util.Constants;
import com.kingdee.ebank.util.DateStyle;
import com.kingdee.ebank.util.DateUtil;
import com.kingdee.ebank.util.JaxbMapper;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.vo.TradeRecord;
import com.kingdee.ebank.vo.TradeRecordItem;

/**
 * 企业单笔资金划转[4004]
 * @author pc
 *
 */
@Service
public class PabSingleCapitalTradeService extends PabAbstractService {
	private static Logger logger = Logger.getLogger(PabAccountBalanceQueryService.class);
	@Autowired
	private TradeRecordService tradeRecordService;
	
	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	/**
	 * 企业单笔资金划转[4004]
	 * @author pc
	 *
	 */
	public String perform(Map<String, Object> params) {
    	
		logger.info("**************************平安企业单笔资金划转[4004]***************************");
		logger.info("单笔自己转账交易请求报文："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		
    	try {
    	
        /**
    	 * 1,请求数据合法性验证
    	 */
  		OperateResult operateResult = CheckPabRequestParam.checkcEnterpriseSingleCapitalTradeParam(params);
  		logger.info("请求参数校验结果："+JSON.toJSONString(operateResult));
  		if(!operateResult.isSuccess()){
  			returnInfo.setErrormsg(operateResult.getMessage());
  			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
  		}
  		
  		/**
    	 * 2,订单校验，查询数据库订单请求，同一个订单不能重复提交
    	 */
		TradeRecord tradeRecord = tradeRecordService.findByCustomerOrderno((String)params.get("customerOrderno"));
		logger.info("系统订单获取结果："+JSON.toJSONString(tradeRecord));
		if(tradeRecord!=null){
			returnInfo.setErrormsg("CustomerOrderno is exist");
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}
		
		/**
    	 * 3,产生平台交易流水号,创建新订单
    	 */
        params.put("outTradeNo", DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS)+"123456");
		TradeRecord tradeRecordNew = PabHandle.createEnterpriseSingleCapitalTradeVo(params);
		tradeRecordService.add(tradeRecordNew);
		logger.info("企业单笔资金划转订单记录已保存，记录id["+tradeRecordNew.getId()+"]");
		List<TradeRecordItem> items = tradeRecordNew.getItems();
		TradeRecordItem tradeRecordItem = items.get(0);
		tradeRecordItem.setRecordId(tradeRecordNew.getId());
		tradeRecordItemService.add(tradeRecordItem);
		logger.info("企业单笔资金划转交易记录已保存，记录id["+tradeRecordItem.getId()+"]");
		

		/**
    	 * 4,发送数据报文组装
    	 */
        String sendxml = PabHandle.getEnterpriseSingleCapitalTradeXml(params);
        params.put("xmllength", String.valueOf(sendxml.getBytes("gbk").length));
        params.put("trade_code", PabConstants.trade_code4004);
        String head = PabHandle.createHeadData(params);
        String  senddata = head+sendxml;
        logger.info("单笔自己转账交易网关查询发送报文：【"+senddata+"】");
        
        /**
    	 * 5,发送银行网关，并同步获取返回结果
    	 */
        String returnstr = PabHttprequest.send(senddata);
        logger.info("单笔自己转账交易网关返回报文：【"+returnstr+"】");
        if(returnstr==null || "".equals(returnstr)||returnstr.length()<80){
        	returnInfo.setErrormsg(Constants.GATEWAY_ERROR);
  			logger.info("企业单笔资金划转网关发送失败:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        String returnCode = returnstr.substring(87, 93);
        String returnMsg = returnstr.substring(94,193);
        if(!PabConstants.GATEWAY_SUCCESS_CODE.equals(returnCode)){
        	returnInfo.setErrormsg(returnMsg);
  			logger.info("企业单笔资金划转网关返回错误，错误信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        
        /**
    	 * 6,银行网关返回结果处理，更新数据库
    	 */
        String returnxml = returnstr.substring(216);
        EnterpriseSingleCapitalTradeResult refundResult = JaxbMapper.fromXml(returnxml, EnterpriseSingleCapitalTradeResult.class);
        if(refundResult.getFrontLogNo()!=null){
        	tradeRecordNew.setStatus(1);
        	tradeRecordService.updateTradeRecordByid(tradeRecordNew);
        }

        /**
    	 * 7, 同步返回产品线
    	 */
        String returnJsonData = PabHandle.createEnterpriseSingleCapitalTradeResultJson(refundResult,params);
        returnInfo.setSuccess(true);
	    returnInfo.setErrormsg("");
        returnInfo.setData(returnJsonData);
		logger.info("单笔自己转账交易同步返回报文："+JSON.toJSONString(returnInfo));
    	} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg("系统异常,请联系技术人员处理！");
		}
        return JSON.toJSONString(returnInfo);
    }
}
