package com.kingdee.ebank.bank.pab.util;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.util.DateStyle;
import com.kingdee.ebank.util.DateUtil;
import com.kingdee.ebank.vo.TradeRecord;
import com.kingdee.ebank.vo.TradeRecordItem;

public class PabHandle {
	
	/**
	 * 组装平安银企直连交易头信息
	 * @return
	 * @throws Exception 
	 */
	public static String createHeadData(Map<String,Object> map) throws Exception{
		StringBuffer sb = new StringBuffer();
		//1 报文版本
		sb.append(PabConstants.DATA_VERSION);
		//2 目标系统
		sb.append(PabConstants.TARGET_SYSTEM);
		//3 报文编码
		sb.append(PabConstants.encoding);
		//4 通讯协议
		sb.append(PabConstants.protocol);
		//5 外联客户代码
		sb.append(PabConstants.mer_code);
		
		//6 接收报文长度
		if(map.get("xmllength")==null){
			throw new ServerException("xmllength is null!");
		}
		String xmllengthstr = (String)map.get("xmllength");
		String addxmllengthstr = "";
		for(int i=0;i<(10-xmllengthstr.length());i++){
			addxmllengthstr=addxmllengthstr+"0";
		}
		sb.append(addxmllengthstr+xmllengthstr);
		
		//7 交易码
		if(map.get("trade_code")==null){
			throw new ServerException("trade_code is null!");
		}
		sb.append((String)map.get("trade_code"));
		//8 操做员代码
		sb.append(PabConstants.operator_code);
		//9 服务类型
		sb.append(PabConstants.trade_type);
		
		Date date = new Date();
		//10 交易日期
		sb.append(DateUtil.DateToString(date, DateStyle.YYYYMMDDEN));
		//11 交易时间
		sb.append(DateUtil.DateToString(date, DateStyle.HHMMSS));
		
		//12 请求方系统流水号
		if(map.get("outTradeNo")==null){
			throw new ServerException("outTradeNo is null!");
		}
		String out_trade_no = (String)map.get("outTradeNo");
		String out_trade_no_add="";
		for(int i=0;i<(20-out_trade_no.length());i++){
			out_trade_no_add=out_trade_no_add+" ";
		}
		sb.append(out_trade_no+out_trade_no_add);
		
		//13 返回码
		sb.append(PabConstants.return_code);
		
		String return_desc="test";
		if(map.get("return_desc")!=null){
			return_desc=(String)map.get("return_desc");
		}
		String return_desc_add="";
		for(int i=0;i<(100-return_desc.length());i++){
			return_desc_add=return_desc_add+" ";
		}

		//14 返回描述
		sb.append(return_desc+return_desc_add);
		
		//15 后续包标志
		sb.append(PabConstants.request_mark);
		
		//16 请求次数
		sb.append(PabConstants.request_time);
		
		//17 签名标识
		sb.append(PabConstants.sign_flag);
		//18 签名数据包格式
		sb.append(PabConstants.sign_format);
		
		//19 签名算法
		String sign_type=PabConstants.sign_type;
		String sign_type_add="";
		for(int i=0;i<(12-sign_type.length());i++){
			sign_type_add=sign_type_add+" ";
		}
		sb.append(PabConstants.sign_type+sign_type_add);
				
		//20 签名数据长度
		sb.append(PabConstants.sign_length);
		
		//21 附件数目
		sb.append(PabConstants.annex_number);
		return sb.toString();
	}
	
	//1 企业账户余额查询 [4001]
	public static String getEnterpriseAccountBalanceQueryXml(Map<String, Object> params)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		if(params.get("account")==null){
			throw new ServerException("account is null!");
		}
		String account = (String)params.get("account");
		sb.append("<Account>"+account+"</Account>");//平安对公账户
		sb.append("<CcyCode>RMB</CcyCode>");
		sb.append("</Result>");
		return sb.toString();
	}
	
	//2 企业单笔资金划转[4004]
	public static String getEnterpriseSingleCapitalTradeXml(Map<String, Object> params)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		if(params.get("outTradeNo")==null){
			throw new ServerException("outTradeNo is null!");
		}
		String out_trade_no = (String)params.get("outTradeNo");
		sb.append("<ThirdVoucher>"+out_trade_no+"</ThirdVoucher>");//转账凭证号
		sb.append("<CcyCode>RMB</CcyCode>");//货币类型 RMB-人民币
		
		if(params.get("payAccount")==null){
			throw new ServerException("payAccount is null!");
		}
		String payAccount = (String)params.get("payAccount");
		sb.append("<OutAcctNo>"+payAccount+"</OutAcctNo>");//付款人账户
		
		if(params.get("payAccountName")==null){
			throw new ServerException("payAccountName is null!");
		}
		String payAccountName = (String)params.get("payAccountName");
		sb.append("<OutAcctName>"+payAccountName+"</OutAcctName>");//付款人名称
		
		if(params.get("receiveAccount")==null){
			throw new ServerException("receiveAccount is null!");
		}
		String receiveAccount = (String)params.get("receiveAccount");
		sb.append("<InAcctNo>"+receiveAccount+"</InAcctNo>");//收款人账号，
		
		if(params.get("receiveAccountName")==null){
			throw new ServerException("receiveAccountName is null!");
		}
		String receiveAccountName = (String)params.get("receiveAccountName");
		sb.append("<InAcctName>"+receiveAccountName+"</InAcctName>");//收款人账户户名
		
		if(params.get("receiveBankName")==null){
			throw new ServerException("receiveBankName is null!");
		}
		String receiveBankName = (String)params.get("receiveBankName");
		sb.append("<InAcctBankName>"+receiveBankName+"</InAcctBankName>");//收款人开户行名称
		
		if(params.get("amount")==null){
			throw new ServerException("amount is null!");
		}
		String amount = (String)params.get("amount");
		sb.append("<TranAmount>"+amount+"</TranAmount>");//转出金额
		sb.append("<UnionFlag>0</UnionFlag>");//行内跨行标志
		sb.append("<SysFlag>S</SysFlag>");//转账加急标志 ‘1’—大额 ，等同Y  ‘2’—小额”等同NY：加急 N：普通S：特急 默认为N
		sb.append("<AddrFlag>1</AddrFlag>");//同城/异地标志 “1”—同城   “2”—异地
		
		if(params.get("mobile")!=null){
			String mobile = (String)params.get("mobile");
			sb.append("<Mobile>"+mobile+"</Mobile>");//转账短信通知手机号码
		}

		sb.append("</Result>");
		return sb.toString();
	}
	
	
	//2 企业大批量资金划转[4004]
	public static String getEnterpriseBatchCapitalTradeXml(TradeRecord tradeRecordNew)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		sb.append("<ThirdVoucher>"+tradeRecordNew.getOutTradeNo()+"</ThirdVoucher>");//转账凭证号
		sb.append("<totalCts>"+tradeRecordNew.getItems().size()+"</totalCts>");//货币类型 RMB-人民币
		sb.append("<totalAmt>"+tradeRecordNew.getCustomerTotalamount()+"</totalAmt>");//转账凭证号
		sb.append("<BSysFlag>Y</BSysFlag>");//转账凭证号
		sb.append("<CcyCode>RMB</CcyCode>");//转账凭证号
		sb.append("<OutAcctNo>"+tradeRecordNew.getItems().get(0).getPayAccount()+"</OutAcctNo>");//转账凭证号
		sb.append("<OutAcctName>"+tradeRecordNew.getItems().get(0).getPayAccountName()+"</OutAcctName>");//转账凭证号
		
		for(int i=0;i<tradeRecordNew.getItems().size();i++){
			TradeRecordItem tradeRecordItem = tradeRecordNew.getItems().get(i);
			sb.append("<HOResultSet4018R>");
			sb.append("<SThirdVoucher>"+tradeRecordItem.getItemOutTradeNo()+"</SThirdVoucher>");//单笔转账凭证号(批次中的流水号)/序号
			sb.append("<InAcctNo>"+tradeRecordItem.getReceiveAccount()+"</InAcctNo>");//收款人账户
			sb.append("<InAcctName>"+tradeRecordItem.getReceiveAccountName()+"</InAcctName>");//收款人账户户名
			sb.append("<InAcctBankName>"+tradeRecordItem.getReceiveBankCode()+"</InAcctBankName>");//收款人开户行名称
			sb.append("<TranAmount>"+tradeRecordItem.getItemAmount()+"</TranAmount>");//转出金额
			sb.append("<UseEx>"+tradeRecordItem.getRemark()+"</UseEx>");//资金用途
			sb.append("<UnionFlag>1</UnionFlag>");//1：行内转账，0：跨行转账
			sb.append("<AddrFlag>1</AddrFlag>");//“1”—同城  “2”—异地
			sb.append("</HOResultSet4018R>");
		}
		sb.append("</Result>");
		return sb.toString();
	}
	
	//2 代发代扣申请接口[4047]
	public static String getEnterpriseBuckleCapitalTradeXml(TradeRecord tradeRecordNew,Map<String, Object> params)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		sb.append("<ThirdVoucher>"+tradeRecordNew.getOutTradeNo()+"</ThirdVoucher>");//转账凭证号
		sb.append("<BusiType>"+params.get("busiType")+"</BusiType>");//货币类型 RMB-人民币
		sb.append("<TotalNum>"+tradeRecordNew.getItems().size()+"</TotalNum>");//货币类型 RMB-人民币
		sb.append("<TotalAmount>"+tradeRecordNew.getCustomerTotalamount()+"</TotalAmount>");//转账凭证号
		sb.append("<SettleType>0</SettleType>");//转账凭证号
		sb.append("<Currency>RMB</Currency>");//转账凭证号
		sb.append("<SrcAccNo>"+params.get("srcAccNo")+"</SrcAccNo>");//转账凭证号
		sb.append("<PayType>"+params.get("payType")+"</PayType>");//转账凭证号
		
		for(int i=0;i<tradeRecordNew.getItems().size();i++){
			TradeRecordItem tradeRecordItem = tradeRecordNew.getItems().get(i);
			sb.append("<HOResultSet4047R>");
			sb.append("<SThirdVoucher>"+tradeRecordItem.getItemOutTradeNo()+"</SThirdVoucher>");//单笔转账凭证号(批次中的流水号)/序号
			sb.append("<OppAccNo>"+params.get("oppAccNo")+"</OppAccNo>");//收款人账户
			sb.append("<OppAccName>"+params.get("oppAccName")+"</OppAccName>");//收款人账户户名
			sb.append("<Amount>"+tradeRecordItem.getItemAmount()+"</Amount>");//收款人开户行名称
			sb.append("</HOResultSet4047R>");
		}
		sb.append("</Result>");
		return sb.toString();
	}
		
	
	//2 单笔转账指令查询[4005]
	public static String getEnterpriseSingleCapitalQueryXml(TradeRecordItem tradeRecordItem)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		sb.append("<OrigThirdVoucher>"+tradeRecordItem.getItemOutTradeNo()+"</OrigThirdVoucher>");//转账凭证号
		sb.append("</Result>");
		return sb.toString();
	}
	
	//2 批量转账指令查询[4015]
	public static String getEnterpriseBatchCapitalQueryXml(TradeRecord tradeRecord)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		sb.append("<OrigThirdVoucher>"+tradeRecord.getOutTradeNo()+"</OrigThirdVoucher>");//转账凭证号
		sb.append("</Result>");
		return sb.toString();
	}

	//2 代发代扣结果查询接口[4048]
	public static String getEnterpriseBuckleCapitalQueryXml(TradeRecordItem tradeRecordItem)throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
		sb.append("<Result>");
		sb.append("<OrigThirdVoucher>"+tradeRecordItem.getItemOutTradeNo()+"</OrigThirdVoucher>");//转账凭证号
		sb.append("</Result>");
		return sb.toString();
	}
	
	/**
	 * 企业账户余额查询 [4001]
	 * @param params
	 * @return
	 */
	public static String createEnterpriseAccountBalanceQueryResultJson(EnterpriseAccountBalanceQueryResult refundResult,Map<String, Object> params){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", refundResult.getAccount());
		map.put("accountName", refundResult.getAccountName());
		map.put("balance", refundResult.getBalance());
		map.put("totalAmount", refundResult.getTotalAmount());
		map.put("tradeNo", params.get("outTradeNo"));
		
		return JSON.toJSONString(map);
	}
	
	/**
	 * 企业账户余额查询 [4001]
	 * @param params
	 * @return
	 */
	public static String createEnterpriseSingleCapitalTradeResultJson(EnterpriseSingleCapitalTradeResult refundResult,Map<String, Object> params){
		params.put("bankTradeNo", refundResult.getFrontLogNo());//银行流水号
		return JSON.toJSONString(params);
	}
	
	/**
	 * 企业大批量资金划转[4018]
	 * @param params
	 * @return
	 */
	public static String createEnterpriseBatchCapitalTradeResultJson(TradeRecord tradeRecordNew){
		Map<String, Object> params = new HashMap<String, Object>();
	
		params.put("customerOrderno", tradeRecordNew.getCustomerOrderno());
		params.put("totalcount", tradeRecordNew.getItems().size());
		params.put("totalamount", tradeRecordNew.getCustomerTotalamount());
		params.put("currency", tradeRecordNew.getCustomerCurrency());
		params.put("payAccount", tradeRecordNew.getItems().get(0).getPayAccount());
		params.put("payAccountName", tradeRecordNew.getItems().get(0).getPayAccountName());
		params.put("outTradeNo", tradeRecordNew.getOutTradeNo());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
		for(int i=0;i<tradeRecordNew.getItems().size();i++){
			TradeRecordItem tradeRecordItem = tradeRecordNew.getItems().get(i);
	    	Map<String, Object> ext0 = new HashMap<String, Object>();
	    	ext0.put("itemOrderno", tradeRecordItem.getItemOrderno());
	    	ext0.put("itemAmount", tradeRecordItem.getItemAmount());
	    	ext0.put("receiveBankName", tradeRecordItem.getReceiveBankCode());
	    	ext0.put("receiveAccount", tradeRecordItem.getReceiveAccount());
	    	ext0.put("receiveAccountName", tradeRecordItem.getReceiveAccountName());
	    	ext0.put("useEx", tradeRecordItem.getRemark());//资金用途
	    	ext0.put("status", tradeRecordItem.getItemStatus());
	    	ext0.put("itemOutTradeNo", tradeRecordItem.getItemOutTradeNo());
	    	list.add(ext0);
		}
    	
		params.put("extra", list);
		params.put("remark", tradeRecordNew.getRemark());
		
		return JSON.toJSONString(params);
	}
	
	/**
	 * 代发代扣申请接口[4047]
	 * @param params
	 * @return
	 */
	public static String createEnterpriseBuckleCapitalTradeResultJson(TradeRecord tradeRecordNew,Map<String, Object> params0){
		Map<String, Object> params = new HashMap<String, Object>();
	
		params.put("customerOrderno", tradeRecordNew.getCustomerOrderno());
		params.put("busiType", params0.get("busiType"));
		params.put("payType", params0.get("payType"));
		params.put("srcAccNo", params0.get("srcAccNo"));
		params.put("totalcount", tradeRecordNew.getItems().size());
		params.put("totalamount", tradeRecordNew.getCustomerTotalamount());
		params.put("settleType", params0.get("settleType"));
		params.put("outTradeNo", tradeRecordNew.getOutTradeNo());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
		for(int i=0;i<tradeRecordNew.getItems().size();i++){
			TradeRecordItem tradeRecordItem = tradeRecordNew.getItems().get(i);
			List<Map<String, Object>> list0 = (List<Map<String, Object>>) params0.get("extra");

			for(int j=0;j<list0.size();j++){
				Map<String, Object> params1 = list0.get(j);
				
				if(tradeRecordItem.getItemOrderno().equals((String)params1.get("itemOrderno"))){
			    	params1.put("status", tradeRecordItem.getItemStatus());
			    	params1.put("itemOutTradeNo", tradeRecordItem.getItemOutTradeNo());
			    	list.add(params1);
				}
			}
			
		}
    	
		params.put("extra", list);
		
		return JSON.toJSONString(params);
	}
	
	/**
	 * 代发代扣结果查询接口[4048]
	 * @param params
	 * @return
	 */
	public static String createPabBuckleCapitalQueryResultJson(TradeRecordItem tradeRecordItem){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bankTradeNo", tradeRecordItem.getChannelTradeNo());//银行流水号
		params.put("customerOrderno", ""+tradeRecordItem.getItemOrderno());
		params.put("amount", tradeRecordItem.getItemAmount());
		params.put("currency", tradeRecordItem.getItemCurrency());
		params.put("payAccount", tradeRecordItem.getPayAccount());
		params.put("payAccountName", tradeRecordItem.getPayAccountName());
		params.put("receiveBankName", tradeRecordItem.getPayBankCode());
		params.put("receiveAccount", tradeRecordItem.getReceiveAccount());
		params.put("receiveAccountName", tradeRecordItem.getReceiveAccountName());
		params.put("status", tradeRecordItem.getItemStatus());
		params.put("outTradeNo", tradeRecordItem.getItemOutTradeNo());
		return JSON.toJSONString(params);
	}
	
	/**
	 * 单笔转账指令查询[4005]
	 * @param params
	 * @return
	 */
	public static String createPabSingleCapitalQueryResultJson(TradeRecordItem tradeRecordItem){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bankTradeNo", tradeRecordItem.getChannelTradeNo());//银行流水号
		params.put("customerOrderno", ""+tradeRecordItem.getItemOrderno());
		params.put("amount", tradeRecordItem.getItemAmount());
		params.put("currency", tradeRecordItem.getItemCurrency());
		params.put("payAccount", tradeRecordItem.getPayAccount());
		params.put("payAccountName", tradeRecordItem.getPayAccountName());
		params.put("receiveBankName", tradeRecordItem.getPayBankCode());
		params.put("receiveAccount", tradeRecordItem.getReceiveAccount());
		params.put("receiveAccountName", tradeRecordItem.getReceiveAccountName());
		params.put("status", tradeRecordItem.getItemStatus());
		params.put("outTradeNo", tradeRecordItem.getItemOutTradeNo());
		return JSON.toJSONString(params);
	}
	
	/**
	 * 企业单笔资金划转[4004]
	 * @param params
	 * @return
	 */
	public static TradeRecord createEnterpriseSingleCapitalTradeVo(Map<String, Object> params){
		
		TradeRecord tradeRecord = new TradeRecord();
		
		tradeRecord.setCreateDate(new Date());
		tradeRecord.setCreator("system");
		tradeRecord.setCustomerCurrency((String)params.get("currency"));
		tradeRecord.setCustomerNo((String)params.get("customerNo"));
		tradeRecord.setCustomerOrderno((String)params.get("customerOrderno"));
		tradeRecord.setCustomerTotalamount(Integer.valueOf((String)params.get("amount")));
		tradeRecord.setOutTradeNo((String)params.get("outTradeNo"));//? 生成流水号
		tradeRecord.setRemark((String)params.get("remark"));
		tradeRecord.setStatus(2);//处理中
		tradeRecord.setChannel("pab");
		
		TradeRecordItem tradeRecordItem = new TradeRecordItem();
		tradeRecordItem.setItemAmount(Integer.valueOf((String)params.get("amount")));
		tradeRecordItem.setItemCurrency(tradeRecord.getCustomerCurrency());
		tradeRecordItem.setItemOrderno(tradeRecord.getCustomerOrderno());
		tradeRecordItem.setItemOutTradeNo(tradeRecord.getOutTradeNo());
		tradeRecordItem.setItemStatus(2);
		tradeRecordItem.setItemType("1");
		tradeRecordItem.setCreateDate(new Date());
		tradeRecordItem.setCreator("system");
		tradeRecordItem.setRemark((String)params.get("remark"));
		
		tradeRecordItem.setPayAccount((String)params.get("payAccount"));
		tradeRecordItem.setPayAccountName((String)params.get("payAccountName"));
		tradeRecordItem.setPayBankCode("平安银行");
		tradeRecordItem.setReceiveAccount((String)params.get("receiveAccount"));
		tradeRecordItem.setReceiveAccountName((String)params.get("receiveAccountName"));
		tradeRecordItem.setReceiveBankCode((String)params.get("receiveBankName"));
		
		tradeRecord.addItems(tradeRecordItem);
		
		return tradeRecord;
	}
	
	/**
	 * 企业大批量资金划转[4018]
	 * @param params
	 * @return
	 */
	public static TradeRecord createEnterpriseBatchCapitalTradeVo(Map<String, Object> params){
		
		TradeRecord tradeRecord = new TradeRecord();
		
		tradeRecord.setCreateDate(new Date());
		tradeRecord.setCreator("system");
		tradeRecord.setCustomerCurrency((String)params.get("currency"));
		tradeRecord.setCustomerNo((String)params.get("customerNo"));
		tradeRecord.setCustomerOrderno((String)params.get("customerOrderno"));
		tradeRecord.setCustomerTotalamount(Integer.valueOf((String)params.get("totalamount")));
		tradeRecord.setOutTradeNo((String)params.get("outTradeNo"));//? 生成流水号
		tradeRecord.setRemark((String)params.get("remark"));
		tradeRecord.setStatus(2);//处理中
		tradeRecord.setChannel("pab");
		
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) params.get("extra");
		for(int i=0;i<list.size();i++){
			Map<String, Object> mapextra = list.get(i);
			
			TradeRecordItem tradeRecordItem = new TradeRecordItem();
			tradeRecordItem.setItemAmount(Integer.valueOf((String)mapextra.get("itemAmount")));
			tradeRecordItem.setItemCurrency(tradeRecord.getCustomerCurrency());
			tradeRecordItem.setItemOrderno((String)mapextra.get("itemOrderno"));
			tradeRecordItem.setItemOutTradeNo(DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS)+"");
			tradeRecordItem.setItemStatus(2);
			tradeRecordItem.setItemType("1");
			tradeRecordItem.setCreateDate(new Date());
			tradeRecordItem.setCreator("system");
			tradeRecordItem.setRemark((String)params.get("useEx"));
			tradeRecordItem.setPayAccount((String)params.get("payAccount"));
			tradeRecordItem.setPayAccountName((String)params.get("payAccountName"));

			tradeRecordItem.setReceiveAccount((String)mapextra.get("receiveAccount"));
			tradeRecordItem.setReceiveAccountName((String)mapextra.get("receiveAccountName"));
			tradeRecordItem.setReceiveBankCode((String)mapextra.get("receiveBankName"));
			
			tradeRecord.addItems(tradeRecordItem);
		
		}
		return tradeRecord;
	}
	
	
	/**
	 * 代发代扣申请接口[4047]
	 * @param params
	 * @return
	 */
	public static TradeRecord createEnterpriseBuckleCapitalTradeVo(Map<String, Object> params){
		
		TradeRecord tradeRecord = new TradeRecord();
		
		tradeRecord.setCreateDate(new Date());
		tradeRecord.setCreator("system");
		tradeRecord.setCustomerCurrency((String)params.get("currency"));
		tradeRecord.setCustomerNo((String)params.get("customerNo"));
		tradeRecord.setCustomerOrderno((String)params.get("customerOrderno"));
		tradeRecord.setCustomerTotalamount(Integer.valueOf((String)params.get("totalamount")));
		tradeRecord.setOutTradeNo((String)params.get("outTradeNo"));//? 生成流水号
		tradeRecord.setRemark((String)params.get("remark"));
		tradeRecord.setStatus(2);//处理中
		tradeRecord.setChannel("pab");
		
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) params.get("extra");
		for(int i=0;i<list.size();i++){
			Map<String, Object> mapextra = list.get(i);
			
			TradeRecordItem tradeRecordItem = new TradeRecordItem();
			tradeRecordItem.setItemAmount(Integer.valueOf((String)mapextra.get("itemAmount")));
			tradeRecordItem.setItemCurrency(tradeRecord.getCustomerCurrency());
			tradeRecordItem.setItemOrderno((String)mapextra.get("itemOrderno"));
			tradeRecordItem.setItemOutTradeNo(DateUtil.DateToString(new Date(), DateStyle.YYYYMMDDHHMMSS)+"");
			tradeRecordItem.setItemStatus(2);
			tradeRecordItem.setItemType("2");
			tradeRecordItem.setCreateDate(new Date());
			tradeRecordItem.setCreator("system");
			tradeRecordItem.setRemark((String)params.get("remark"));
			String paytype = (String)params.get("payType");
			if("0".equals(paytype)){
				tradeRecordItem.setPayAccount((String)params.get("oppAccNo"));
				tradeRecordItem.setPayAccountName((String)params.get("oppAccName"));
				tradeRecordItem.setReceiveAccount((String)mapextra.get("srcAccNo"));
			}else{
				tradeRecordItem.setPayAccount((String)params.get("srcAccNo"));
				tradeRecordItem.setReceiveAccount((String)mapextra.get("oppAccNo"));
				tradeRecordItem.setReceiveAccountName((String)mapextra.get("oppAccName"));				
			}
			tradeRecord.addItems(tradeRecordItem);
		
		}
		return tradeRecord;
	}

}
