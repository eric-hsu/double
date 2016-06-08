package com.kingdee.ebank.bank.pab.service;

import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.bank.pab.PabAbstractService;
import com.kingdee.ebank.bank.pab.util.CheckPabRequestParam;
import com.kingdee.ebank.bank.pab.util.EnterpriseAccountBalanceQueryResult;
import com.kingdee.ebank.bank.pab.util.PabConstants;
import com.kingdee.ebank.bank.pab.util.PabHandle;
import com.kingdee.ebank.bank.pab.util.PabHttprequest;
import com.kingdee.ebank.common.OperateResult;
import com.kingdee.ebank.util.Constants;
import com.kingdee.ebank.util.DateStyle;
import com.kingdee.ebank.util.DateUtil;
import com.kingdee.ebank.util.JaxbMapper;
import com.kingdee.ebank.util.ReturnInfo;

/**
 * 企业账户余额查询 [4001]
 * @author pc
 *
 */
@Service
public class PabAccountBalanceQueryService extends PabAbstractService {
	private static Logger logger = Logger.getLogger(PabAccountBalanceQueryService.class);

	/**
	 * 企业账户余额查询 [4001]
	 * @author pc
	 *
	 */
	public String perform(Map<String, Object> params) {

		logger.info("**************************平安企业账户余额查询 [4001]***************************");
		logger.info("接收参数："+JSON.toJSONString(params));
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo.setSuccess(false);
		returnInfo.setErrormsg("接口未开通");
		
    	try {

        /**
    	 * 1,请求数据合法性验证
    	 */
  		OperateResult operateResult = CheckPabRequestParam.checkcEnterpriseAccountBalanceQueryParam(params);
  		if(!operateResult.isSuccess()){
  			returnInfo.setErrormsg(operateResult.getMessage());
  			logger.info("校验失败，异常信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
  		}
      		
		/**
    	 * 2,产生交易流水号，发送数据报文组装
    	 */
  		params.put("outTradeNo", ""+DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS)+"123456");
        String sendxml = PabHandle.getEnterpriseAccountBalanceQueryXml(params);
        params.put("xmllength", String.valueOf(sendxml.getBytes("gbk").length));
        params.put("trade_code", PabConstants.trade_code4001);
        String head = PabHandle.createHeadData(params);
        String  senddata = head+sendxml;
        logger.info("企业账户余额查询网关发送报文：【"+senddata+"】");

        /**
    	 * 4,发送银行网关，并同步获取返回结果
    	 */
        String returnstr = PabHttprequest.send(senddata);
        logger.info("企业账户余额查询网关返回报文：【"+returnstr+"】");
        if(returnstr==null || "".equals(returnstr)){
        	returnInfo.setErrormsg(Constants.GATEWAY_ERROR);
  			logger.info("企业余额查询网关返回数据为空:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        String returnCode = returnstr.substring(87, 93);
        String returnMsg = returnstr.substring(94,193);
        if(!PabConstants.GATEWAY_SUCCESS_CODE.equals(returnCode)){
        	returnInfo.setErrormsg(returnMsg);
  			logger.info("企业余额查询网关返回错误，错误信息:"+JSON.toJSONString(returnInfo));
  			return  JSON.toJSONString(returnInfo);
        }
        
        /**
    	 * 5,银行网关返回结果处理
    	 */
        String returnxml = returnstr.substring(216);
        EnterpriseAccountBalanceQueryResult refundResult = JaxbMapper.fromXml(returnxml, EnterpriseAccountBalanceQueryResult.class);

        /**
    	 * 6,同步返回产品线
    	 */
        String returnJsonData = PabHandle.createEnterpriseAccountBalanceQueryResultJson(refundResult,params);
        returnInfo.setSuccess(true);
	    returnInfo.setErrormsg("");
        returnInfo.setData(returnJsonData);
		logger.info("企业账户余额查询同步返回报文："+JSON.toJSONString(returnInfo));

    	} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg("系统异常,请联系技术人员处理！");
		}
        return JSON.toJSONString(returnInfo);
    }
}
