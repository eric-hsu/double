package com.kingdee.ebank.cmbc.services;

import java.io.IOException;
import java.util.Map;

public interface CmbcService {
	
	/**
     * 代付-交易
     * 
     * @param params
	 * @return
	 */
    public String generationPay(Map<String, Object> params) throws IOException;
    
    /**
     * 代付-账务交易结果查询请求
     * 
     * @param params
	 * @return
	 */
    public String generationQuery(Map<String, Object> params) throws IOException;
    
    /**
     * 本行代扣-交易
     * 
     * @param params
	 * @return
	 */
    public String cmbcBuckleTrade(Map<String, Object> params) throws IOException;
    
    /**
     * 本行代扣-账务交易结果查询请求
     * 
     * @param params
	 * @return
	 */
    public String cmbcBuckleQuery(Map<String, Object> params) throws IOException;
    
    /**
     * 跨行代扣-交易
     * 
     * @param params
	 * @return
	 */
    public String crossBuckleTrade(Map<String, Object> params) throws IOException;
    
    /**
     * 跨行代扣-实时跨行代扣结果查询
     * 
     * @param params
	 * @return
	 */
    public String crossBuckleQuery(Map<String, Object> params) throws IOException;
    
    /**
     * 白名单添加授权
     * 
     * @param params
	 * @return
	 */
    public String authorizedEnter(Map<String, Object> params) throws IOException;
    
    /**
     * 白名单授权查询
     * 
     * @param params
	 * @return
	 */
    public String authorizedQuery(Map<String, Object> params) throws IOException;
}
