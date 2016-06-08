package com.kingdee.ebank.web.cmbc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.services.ApiService;
import com.kingdee.ebank.util.ReturnInfo;
import com.kingdee.ebank.utils.Constants;
import com.kingdee.ebank.utils.PayCallBack;

/**
 * pay的Restful API的Controller.
 * 
 * @author eric
 */
@RestController
@RequestMapping(value = "/api/cmbc")
public class CmbcController {

	private static Logger logger = LoggerFactory.getLogger(CmbcController.class);
	

	@RequestMapping(value = "/asyn", method = {RequestMethod.POST, RequestMethod.GET})
	public String handle(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("*************************收到doubbo服务端反馈*****************************");
			//异步返回
			String jsonstr  = request.getParameter("JSONSTR");
			String asynurl  = request.getParameter("ASYNURL");
			logger.info("jsonstr："+jsonstr);
			logger.info("asynurl："+asynurl);

			PayCallBack payCallBack = new PayCallBack(asynurl,jsonstr);
	        Thread thread = new Thread(payCallBack);
	        thread.start();
	        
	        return "success";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("系统异常，异常信息："+e.getMessage()+",异步返回：error");
		}
		return "error";
	}
}
