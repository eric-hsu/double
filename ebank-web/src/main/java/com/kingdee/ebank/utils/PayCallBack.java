package com.kingdee.ebank.utils;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author ivan
 * 
 */
public class PayCallBack implements Runnable {
    // 日志
    /**
     * Automatically generated variable: TIME_MIN
     */
    private static final int TIME_MIN = 60000;
    private static final int READ_TIME_OUT = 60 * 1000;
	private static Logger logger = LoggerFactory.getLogger(PayCallBack.class);
    private static final String JSONSTR = "JSONSTR";

    /**
     * 最大轮询次数
     */
    private int cycleCount = 5;

    /**
     * 回调Url
     */
    private String redirectURL;
    /**
     * 回调xml格式字符串
     */
    private String postJson;


    public PayCallBack() {

    }

    public PayCallBack(String redirectURL, String postJson) {
        this.redirectURL = redirectURL;
        this.postJson = postJson;
    }

    public void run() {
        HttpClient httpclient = new HttpClient();
        PostMethod post = new PostMethod(redirectURL);
        logger.info("异步返回地址："+redirectURL);
		logger.info("返回信息，JSONSTR："+postJson);

        try {
            NameValuePair[] nameValuePair = new NameValuePair[1];
            String poststr = URLEncoder.encode(this.postJson, "UTF-8");
            nameValuePair[0] = new NameValuePair(JSONSTR, poststr);
            post.setRequestBody(nameValuePair);
            // 设置通信超时时间为20秒（正常通信时间为1-2秒）
            post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, READ_TIME_OUT);
            // 最多轮询cycleCount次
            for (int i = 0; i < cycleCount; i++) {
                // 循环次数间隔，第i*60秒
                Thread.sleep(i * TIME_MIN);
                int returnInt = httpclient.executeMethod(post);
                logger.info("post result = " + returnInt);
                String result = post.getResponseBodyAsString();
                logger.info("call back result=" + result);
                // 如果连接成功，循环结束
                if ("success".equals(result)) {
                	logger.info("回调成功，应答：success");
                    break;
                }else if(returnInt == 200 && !"fail".equals(result)){
                	logger.info("回调成功，应答：fail");
                    break;
                }
            }
        } catch (HttpException he) {
            he.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // 释放连接
            post.releaseConnection();
        }
    }
}
