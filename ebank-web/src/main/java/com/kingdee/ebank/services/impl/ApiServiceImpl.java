package com.kingdee.ebank.services.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingdee.ebank.provider.Provider;
import com.kingdee.ebank.services.ApiService;
import com.kingdee.ebank.services.CommonService;
import com.kingdee.ebank.services.CustomerCertificateService;
import com.kingdee.ebank.services.CustomerService;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.utils.Constants;
import com.kingdee.ebank.utils.SpringContextUtil;
import com.kingdee.ebank.utils.sign.RSA;
import com.kingdee.ebank.utils.sign.SignCore;
import com.kingdee.ebank.vo.CustomerCertificateVo;
import com.kingdee.ebank.vo.CustomerVo;

@Service
public class ApiServiceImpl implements ApiService {

    private static Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerCertificateService customerCertificateService;

    @Autowired
    private CommonService commonService;

    /**
     * 数据处理
     *
     * @param request
     * @return
     */
    public String handle(HttpServletRequest request) throws Exception {
        logger.info("++++++++++++++++++++++++++++银企平台订单处理开始++++++++++++++++++++++++++");
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setSuccess(false);	
        Gson gson = new Gson();
        
        /**
         * 1 请求数据log记录
         */
        Map<String, Object> params = new HashMap<String, Object>();
        Enumeration<?> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            params.put(paraName, request.getParameter(paraName));
            logger.info(paraName + ": " + request.getParameter(paraName));
        }
        if(params.get("data")==null){
        	returnInfo.setErrormsg(Constants.ERROR_NO_DATA);
            return JSON.toJSONString(returnInfo);
        }
        
        if(params.get("sign")==null){
        	returnInfo.setErrormsg(Constants.ERROR_NO_DATA);
            return JSON.toJSONString(returnInfo);
        }
        String datajson = (String)params.get("data");
        String sign = (String)params.get("sign");
        Map<String, Object> extra = gson.fromJson(datajson,new TypeToken<Map<String, Object>>(){}.getType());
    	
        if(extra.get("customerNo")==null){
        	returnInfo.setErrormsg(Constants.ERROR_NO_CUSTOMER);
            return JSON.toJSONString(returnInfo);
        }
        /**
         * 2 用户验证
         */
        String customerNo = (String)extra.get("customerNo");
        CustomerVo customerVo = customerService.get(customerNo);
        if (customerVo == null) {
            returnInfo.setErrormsg(Constants.ERROR_NO_CUSTOMER);
            return JSON.toJSONString(returnInfo);
        } else {
            boolean status = customerVo.getStatus();
            if (!status) {
                returnInfo.setErrormsg(Constants.ERROR_ACTIVE_CUSTOMER);
                return JSON.toJSONString(returnInfo);
            }
        }

        /**
         * 3 数据验证,来源验证
         */
        CustomerCertificateVo customerCertificateVo = customerCertificateService.get(customerNo);
        if (customerCertificateVo == null) {
            returnInfo.setErrormsg(Constants.ERROR_CERTIFYCATE_CUSTOMER);
            return JSON.toJSONString(returnInfo);
        }
        boolean flag = RSA.verify(datajson, sign, customerCertificateVo.getPublicKey(), Constants.input_charset);
        if (!flag) {
            returnInfo.setErrormsg(Constants.ERROR_VERIFY_CUSTOMER);
            return JSON.toJSONString(returnInfo);
        }

        /**
         * 4 doubbo service获取
         */
        String channel = (String) extra.get("channel");
        returnInfo.setErrormsg(Constants.ERROR_SYS);
        String result = JSON.toJSONString(returnInfo);

        Provider bankApiProvider = SpringContextUtil.getBean(Provider.class);
        result = bankApiProvider.executeService(String.format("%s_%s", channel, extra.get("tradecode")), extra);

        logger.info("++++++++++++++++++++++++++++银企平台订单处理结束++++++++++++++++++++++++++");

        return result;
    }
}
