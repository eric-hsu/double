package com.kingdee.ebank.bank.cmbc.util;

import java.util.Map;

import org.dom4j.Element;

import com.kingdee.ebank.common.OperateResult;


public class CheckCmbcRequestParam {
	
	/**
     * 代付-交易请求验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcPp(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object TRAN_DATE_OBJ = params.get("TRAN_DATE");
		if(TRAN_DATE_OBJ==null){
			operateResult.setMessage("TRAN_DATE ERROR");
			return operateResult;
		}
		
		Object ASYNURL_OBJ = params.get("ASYNURL");
		if(ASYNURL_OBJ==null){
			operateResult.setMessage("ASYNURL ERROR");
			return operateResult;
		}
		
		Object TRAN_TIME_OBJ = params.get("TRAN_TIME");
		if(TRAN_TIME_OBJ==null){
			operateResult.setMessage("TRAN_TIME ERROR");
			return operateResult;
		}

		Object ORDERNO_OBJ = params.get("ORDERNO");
		if(ORDERNO_OBJ==null){
			operateResult.setMessage("ORDERNO ERROR");
			return operateResult;
		}
		
		Object CURRENCY_OBJ = params.get("CURRENCY");
		if(CURRENCY_OBJ==null){
			operateResult.setMessage("CURRENCY ERROR");
			return operateResult;
		}
		
		Object ACC_NO_OBJ = params.get("ACC_NO");
		if(ACC_NO_OBJ==null){
			operateResult.setMessage("ACC_NO ERROR");
			return operateResult;
		}
		
		Object ACC_NAME_OBJ = params.get("ACC_NAME");
		if(ACC_NAME_OBJ==null){
			operateResult.setMessage("ACC_NAME ERROR");
			return operateResult;
		}
		
		Object TRANS_AMT_OBJ = params.get("TRANS_AMT");
		if(TRANS_AMT_OBJ==null){
			operateResult.setMessage("TRANS_AMT ERROR");
			return operateResult;
		}
		
		Object REMARK_OBJ = params.get("REMARK");
		if(REMARK_OBJ==null){
			operateResult.setMessage("REMARK ERROR");
			return operateResult;
		}
		
		Object customerNo_OBJ = params.get("customerNo");
		if(customerNo_OBJ==null){
			operateResult.setMessage("customerNo ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	/**
     * 代付-查询请求参数验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcPquery(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object TRAN_DATE_OBJ = params.get("TRAN_DATE");
		if(TRAN_DATE_OBJ==null){
			operateResult.setMessage("TRAN_DATE ERROR");
			return operateResult;
		}
	
		Object TRAN_TIME_OBJ = params.get("TRAN_TIME");
		if(TRAN_TIME_OBJ==null){
			operateResult.setMessage("TRAN_TIME ERROR");
			return operateResult;
		}

		Object QUERYNO_OBJ = params.get("QUERYNO");
		if(QUERYNO_OBJ==null){
			operateResult.setMessage("QUERYNO ERROR");
			return operateResult;
		}
		
		Object OUTTRADENO_OBJ = params.get("OUTTRADENO");
		if(OUTTRADENO_OBJ==null){
			operateResult.setMessage("OUTTRADENO ERROR");
			return operateResult;
		}
		
		Object ACC_NO_OBJ = params.get("ACC_NO");
		if(ACC_NO_OBJ==null){
			operateResult.setMessage("ACC_NO ERROR");
			return operateResult;
		}
		
		Object ACC_NAME_OBJ = params.get("ACC_NAME");
		if(ACC_NAME_OBJ==null){
			operateResult.setMessage("ACC_NAME ERROR");
			return operateResult;
		}
		
		Object TRANS_AMT_OBJ = params.get("TRANS_AMT");
		if(TRANS_AMT_OBJ==null){
			operateResult.setMessage("TRANS_AMT ERROR");
			return operateResult;
		}
		
		Object REMARK_OBJ = params.get("REMARK");
		if(REMARK_OBJ==null){
			operateResult.setMessage("REMARK ERROR");
			return operateResult;
		}
		
		Object customerNo_OBJ = params.get("customerNo");
		if(customerNo_OBJ==null){
			operateResult.setMessage("customerNo ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
	/**
     * 本行代扣-交易请求验证
     * 
     * @param params
     * @return String
     */
	public static OperateResult checkcCb(Map<String, Object> params){

		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		
		Object TRAN_DATE_OBJ = params.get("TRAN_DATE");
		if(TRAN_DATE_OBJ==null){
			operateResult.setMessage("TRAN_DATE ERROR");
			return operateResult;
		}
		
		Object ASYNURL_OBJ = params.get("ASYNURL");
		if(ASYNURL_OBJ==null){
			operateResult.setMessage("ASYNURL ERROR");
			return operateResult;
		}
		
		Object TRAN_TIME_OBJ = params.get("TRAN_TIME");
		if(TRAN_TIME_OBJ==null){
			operateResult.setMessage("TRAN_TIME ERROR");
			return operateResult;
		}

		Object ORDERNO_OBJ = params.get("ORDERNO");
		if(ORDERNO_OBJ==null){
			operateResult.setMessage("ORDERNO ERROR");
			return operateResult;
		}
		
		Object CURRENCY_OBJ = params.get("CURRENCY");
		if(CURRENCY_OBJ==null){
			operateResult.setMessage("CURRENCY ERROR");
			return operateResult;
		}
		
		Object ACC_NO_OBJ = params.get("ACC_NO");
		if(ACC_NO_OBJ==null){
			operateResult.setMessage("ACC_NO ERROR");
			return operateResult;
		}
		
		Object ACC_NAME_OBJ = params.get("ACC_NAME");
		if(ACC_NAME_OBJ==null){
			operateResult.setMessage("ACC_NAME ERROR");
			return operateResult;
		}
		
		Object TRANS_AMT_OBJ = params.get("TRANS_AMT");
		if(TRANS_AMT_OBJ==null){
			operateResult.setMessage("TRANS_AMT ERROR");
			return operateResult;
		}
		
		Object REMARK_OBJ = params.get("REMARK");
		if(REMARK_OBJ==null){
			operateResult.setMessage("REMARK ERROR");
			return operateResult;
		}
		
		Object customerNo_OBJ = params.get("customerNo");
		if(customerNo_OBJ==null){
			operateResult.setMessage("customerNo ERROR");
			return operateResult;
		}
		
		operateResult.setSuccess(true);
		return operateResult;
	}
	
}
