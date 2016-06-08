package com.kingdee.ebank.services;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;


public interface ApiService {

	/**
     * 处理
     * 
     * @param request
     * @return
     */
    public String handle(HttpServletRequest request) throws Exception;
}
