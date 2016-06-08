package com.kingdee.ebank.web;

import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.services.ApiService;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * pay的Restful API的Controller.
 * 
 * @author eric
 */
@RestController
@RequestMapping(value = "/api/")
public class ApiController {

	private static Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private ApiService apiService;
	
	@RequestMapping(value = "/handle", method = {RequestMethod.POST, RequestMethod.GET})
	public String handle(HttpServletRequest request, HttpServletResponse response) {
		ReturnInfo returnInfo= new ReturnInfo();
		returnInfo.setSuccess(false);
		try {
			
			String jsonresult = apiService.handle(request);
			logger.info("同步返回："+jsonresult);
			return jsonresult;
			
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setErrormsg(Constants.ERROR_SYS);
			logger.info("系统异常，异常信息："+e.getMessage()+",同步返回："+JSON.toJSONString(returnInfo));
		}
		return JSON.toJSONString(returnInfo);
	}
}
