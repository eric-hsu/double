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
import com.kingdee.ebank.bank.pab.util.EnterpriseBatchCapitalQueryItemResult;
import com.kingdee.ebank.bank.pab.util.EnterpriseBatchCapitalQueryResult;
import com.kingdee.ebank.bank.pab.util.PabConstants;
import com.kingdee.ebank.bank.pab.util.PabHandle;
import com.kingdee.ebank.bank.pab.util.PabHttprequest;
import com.kingdee.ebank.common.OperateResult;
import com.kingdee.ebank.service.TradeRecordItemService;
import com.kingdee.ebank.service.TradeRecordService;
import com.kingdee.ebank.util.Constants;
import com.kingdee.ebank.util.JaxbMapper;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.vo.TradeRecord;
import com.kingdee.ebank.vo.TradeRecordItem;

/**
 * 批量转账指令查询[4015]
 * @author pc
 *
 */
@Service
public class PabBatchCapitalQueryService extends PabAbstractService {
	private static Logger logger = Logger.getLogger(PabAccountBalanceQueryService.class);
	@Autowired
	private TradeRecordService tradeRecordService;
	
	@Autowired
	private TradeRecordItemService tradeRecordItemService;

	/**
	 * 批量转账指令查询[4015]
	 * @author pc
	 *
	 */
	public String perform(Map<String, Object> params) {

		logger.info("**************************平安批量转账指令查询[4015]***************************");
		logger.info("批量转账指令查询交易查询请求报文："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		
    	try {
    	
        /**
    	 * 1,请求数据合法性验证
    	 */
  		OperateResult operateResult = CheckPabRequestParam.checkcEnterpriseBatchCapitalTradeParam(params);
  		logger.info("请求参数校验结果："+JSON.toJSONString(operateResult));
  		if(!operateResult.isSuccess()){
  			returnInfo.setErrormsg(operateResult.getMessage());
  			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
  		}
  		
  		/**
    	 * 2,系统订单查询，如交易状态为已交易成功，则直接返回（itemStatus==1）
    	 */
		TradeRecord tradeRecord = tradeRecordService.findByCustomerOrderno((String)params.get("customerOrderno"));
		logger.info("系统订单获取结果："+JSON.toJSONString(tradeRecord));
		if(tradeRecord==null){
			returnInfo.setErrormsg("CustomerOrderno is exist");
			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
			return  JSON.toJSONString(returnInfo);
		}
		
		/**
    	 * 3,发送数据报文组装
    	 */
        String sendxml = PabHandle.getEnterpriseBatchCapitalQueryXml(tradeRecord);
        params.put("xmllength", String.valueOf(sendxml.getBytes("gbk").length));
        params.put("trade_code", PabConstants.trade_code4005);
        params.put("outTradeNo", tradeRecord.getOutTradeNo());
        String head = PabHandle.createHeadData(params);
        String  senddata = head+sendxml;
        logger.info("批量转账指令查询交易网关查询发送报文：【"+senddata+"】");
        
        /**
    	 * 4,发送银行网关，并同步获取返回结果
    	 */
        String returnstr = PabHttprequest.send(senddata);
        logger.info("批量转账指令查询交易网关查询返回报文：【"+returnstr+"】");
        if(returnstr==null || "".equals(returnstr)){
        	returnInfo.setErrormsg(Constants.GATEWAY_ERROR);
  			logger.info("批量转账指令查询[4005]网关返回数据为空:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        String returnCode = returnstr.substring(87, 93);
        String returnMsg = returnstr.substring(94,193);
        if(!PabConstants.GATEWAY_SUCCESS_CODE.equals(returnCode)){
        	returnInfo.setErrormsg(returnMsg);
  			logger.info("批量转账指令查询[4005]网关返回错误，错误信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        
        /**
    	 * 5,银行网关返回结果处理，更新数据库
    	 */
        String returnxml = returnstr.substring(216);
        EnterpriseBatchCapitalQueryResult queryResult = JaxbMapper.fromXml(returnxml, EnterpriseBatchCapitalQueryResult.class);

        List<EnterpriseBatchCapitalQueryItemResult> queryItemResultList = queryResult.getList();
        for(EnterpriseBatchCapitalQueryItemResult item:queryItemResultList){
        	for(TradeRecordItem tradeRecordItem:tradeRecord.getItems()){
        		if(item.getSThirdVoucher().equals(tradeRecordItem.getItemOutTradeNo())){
        			tradeRecordItem.setChannelStatus(item.getStt());
        			if(PabConstants.TRADE_SUCCESS.equals(item.getStt())){
        				tradeRecordItem.setItemStatus(1);
                	}else if(PabConstants.TRADE_FAIL.equals(item.getStt())){
                		tradeRecordItem.setItemStatus(0);
                	}
                	tradeRecordItem.setChannelAmount(item.getTranAmount());
                	tradeRecordItem.setChannelCurrency(item.getCcyCode());
                	tradeRecordItem.setChannelDescription(item.getYhcljg());
                	tradeRecordItem.setChannelTradeDate(new Date());
                	tradeRecordItem.setChannelTradeNo(item.getFrontLogNo());
                	tradeRecordItemService.updateTradeRecordItemByid(tradeRecordItem);
        		}
        	}
        }
        
        /**
    	 * 6,同步返回产品线
    	 */
        String returnJsonData = PabHandle.createEnterpriseBatchCapitalTradeResultJson(tradeRecord);
        returnInfo.setSuccess(true);
	    returnInfo.setErrormsg("");
        returnInfo.setData(returnJsonData);
		logger.info("批量转账指令查询同步返回报文："+JSON.toJSONString(returnInfo));
    	} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg("系统异常,请联系技术人员处理！");
		}
        return JSON.toJSONString(returnInfo);
    }
}
