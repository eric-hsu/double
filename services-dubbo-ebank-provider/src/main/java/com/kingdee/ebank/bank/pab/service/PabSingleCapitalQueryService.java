package com.kingdee.ebank.bank.pab.service;

import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.bank.pab.PabAbstractService;
import com.kingdee.ebank.bank.pab.util.CheckPabRequestParam;
import com.kingdee.ebank.bank.pab.util.EnterpriseSingleCapitalQueryResult;
import com.kingdee.ebank.bank.pab.util.PabConstants;
import com.kingdee.ebank.bank.pab.util.PabHandle;
import com.kingdee.ebank.bank.pab.util.PabHttprequest;
import com.kingdee.ebank.common.OperateResult;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.util.Constants;
import com.kingdee.ebank.util.JaxbMapper;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.vo.TradeRecordItem;

/**
 * 单笔转账指令查询[4005]
 * @author pc
 *
 */
@Service
public class PabSingleCapitalQueryService extends PabAbstractService {
	private static Logger logger = Logger.getLogger(PabAccountBalanceQueryService.class);

	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	/**
	 * 单笔转账指令查询[4005]
	 * @author pc
	 *
	 */
	public String perform(Map<String, Object> params) {
    	
		logger.info("**************************平安企业单笔资金划转[4004]***************************");
		logger.info("单笔自己转账交易查询请求报文："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		
    	try {
    	
        /**
    	 * 1,请求数据合法性验证
    	 */
  		OperateResult operateResult = CheckPabRequestParam.checkcEnterpriseSingleCapitalQueryParam(params);
  		logger.info("请求参数校验结果："+JSON.toJSONString(operateResult));
  		if(!operateResult.isSuccess()){
  			returnInfo.setErrormsg(operateResult.getMessage());
  			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
  		}
  		
  		/**
    	 * 2,系统订单查询，如交易状态为已交易成功，则直接返回（itemStatus==1）
    	 */
		TradeRecordItem tradeRecordItem = tradeRecordItemService.findByItemOrderno((String)params.get("customerOrderno"));
		logger.info("系统订单获取结果："+JSON.toJSONString(tradeRecordItem));
		if(tradeRecordItem==null){
			returnInfo.setErrormsg("CustomerOrderno is exist");
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}
		if(tradeRecordItem.getItemStatus()==1){
			String returnJsonData = PabHandle.createPabSingleCapitalQueryResultJson(tradeRecordItem);
		    returnInfo.setSuccess(true);
		    returnInfo.setErrormsg("");
		    returnInfo.setData(returnJsonData);
		    logger.info("平安单笔转账指令查询[4005],结果"+JSON.toJSONString(returnInfo)+"");
		    return JSON.toJSONString(returnInfo)+"";
		}
		/**
    	 * 3,发送数据报文组装
    	 */
        String sendxml = PabHandle.getEnterpriseSingleCapitalQueryXml(tradeRecordItem);
        params.put("xmllength", String.valueOf(sendxml.getBytes("gbk").length));
        params.put("trade_code", PabConstants.trade_code4005);
        params.put("outTradeNo", tradeRecordItem.getItemOutTradeNo());
        String head = PabHandle.createHeadData(params);
        String  senddata = head+sendxml;
        logger.info("单笔自己转账交易网关查询发送报文：【"+senddata+"】");
        
        /**
    	 * 4,发送银行网关，并同步获取返回结果
    	 */
        String returnstr = PabHttprequest.send(senddata);
        logger.info("单笔自己转账交易网关查询返回报文：【"+returnstr+"】");
        if(returnstr==null || "".equals(returnstr)){
        	returnInfo.setErrormsg(Constants.GATEWAY_ERROR);
  			logger.info("单笔转账指令查询[4005]网关返回数据为空:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        String returnCode = returnstr.substring(87, 93);
        String returnMsg = returnstr.substring(94,193);
        if(!PabConstants.GATEWAY_SUCCESS_CODE.equals(returnCode)){
        	returnInfo.setErrormsg(returnMsg);
  			logger.info("单笔转账指令查询[4005]网关返回错误，错误信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        
        /**
    	 * 5,银行网关返回结果处理，更新数据库
    	 */
        String returnxml = returnstr.substring(216);
        EnterpriseSingleCapitalQueryResult refundResult = JaxbMapper.fromXml(returnxml, EnterpriseSingleCapitalQueryResult.class);
        if(PabConstants.TRADE_SUCCESS.equals(refundResult.getStt())){
        	tradeRecordItem.setChannelStatus(refundResult.getStt());
        	tradeRecordItem.setItemStatus(1);
        	tradeRecordItem.setChannelAmount(refundResult.getTranAmount());
        	tradeRecordItem.setChannelCurrency(refundResult.getCcyCode());
        	tradeRecordItem.setChannelDescription(refundResult.getYhcljg());
        	tradeRecordItem.setChannelTradeDate(new Date());
        	tradeRecordItem.setChannelTradeNo(refundResult.getFrontLogNo());
        	tradeRecordItemService.updateTradeRecordItemByid(tradeRecordItem);
        }
        
        /**
    	 * 6,同步返回产品线
    	 */
        String returnJsonData = PabHandle.createPabSingleCapitalQueryResultJson(tradeRecordItem);
        returnInfo.setSuccess(true);
	    returnInfo.setErrormsg("");
        returnInfo.setData(returnJsonData);
		logger.info("单笔自己转账交易查询同步返回报文："+JSON.toJSONString(returnInfo));
    	} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg("系统异常,请联系技术人员处理！");
		}
        return JSON.toJSONString(returnInfo);
    }
}
