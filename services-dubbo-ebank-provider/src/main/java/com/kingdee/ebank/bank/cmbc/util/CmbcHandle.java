package com.kingdee.ebank.bank.cmbc.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.alibaba.fastjson.JSON;
import com.kingdee.ebank.common.PayCallBack;
import com.kingdee.ebank.util.Global;
import com.kingdee.ebank.util.Md5Encrypt;
import com.kingdee.ebank.vo.TradeRecord;

public class CmbcHandle {
	
	/**
	 * 创建代付交易发送xml格式数据
	 * @param params
	 * @return
	 */
	public static String generationPayXml(Map<String, Object> params){
		Document _document = DocumentHelper.createDocument();//创建document对象，  
		Element root = _document.addElement("TRAN_REQ");
		root.addElement("COMPANY_ID").addText(Global.getConfig("cmbc.gp.mer.id"));
		if(params.get("TRAN_DATE")!=null){
		root.addElement("TRAN_DATE").addText((String)params.get("TRAN_DATE"));
		}
		if(params.get("TRAN_TIME")!=null){
		root.addElement("TRAN_TIME").addText((String)params.get("TRAN_TIME"));
		}
		if(params.get("OUTTRADENO")!=null){
		root.addElement("TRAN_ID").addText((String)params.get("OUTTRADENO"));
		}
		if(params.get("CURRENCY")!=null){
		root.addElement("CURRENCY").addText((String)params.get("CURRENCY"));
		}
		if(params.get("ACC_NO")!=null){
		root.addElement("ACC_NO").addText((String)params.get("ACC_NO"));
		}
		if(params.get("ACC_NAME")!=null){
		root.addElement("ACC_NAME").addText((String)params.get("ACC_NAME"));
		}
		if(params.get("TRANS_AMT")!=null){
		root.addElement("TRANS_AMT").addText((String)params.get("TRANS_AMT"));
		}
		if(params.get("REMARK")!=null){
		root.addElement("REMARK").addText((String)params.get("REMARK"));
		}

		if(params.get("PAY_ACCT_NO")!=null){
		root.addElement("PAY_ACCT_NO").addText((String)params.get("PAY_ACCT_NO"));
		}
		if(params.get("PAY_ACC_NAME")!=null){
		root.addElement("PAY_ACC_NAME").addText((String)params.get("PAY_ACC_NAME"));
		}
		if(params.get("PAY_BANK_TYPE")!=null){
		root.addElement("PAY_BANK_TYPE").addText((String)params.get("PAY_BANK_TYPE"));
		}
		if(params.get("PAY_BANK_NAME")!=null){
		root.addElement("PAY_BANK_NAME").addText((String)params.get("PAY_BANK_NAME"));
		}
			
		return _document.asXML();
	}
	
	/**
	 * 创建代付交易查询发送xml格式数据
	 * @param params
	 * @return
	 */
	public static String generationQueryXml(Map<String, Object> params){
		Document _document = DocumentHelper.createDocument();//创建document对象，  
		Element root = _document.addElement("TRAN_REQ");
		root.addElement("COMPANY_ID").addText(Global.getConfig("cmbc.gp.mer.id"));
		root.addElement("TRAN_DATE").addText((String)params.get("TRAN_DATE"));
		root.addElement("TRAN_TIME").addText((String)params.get("TRAN_TIME"));
		root.addElement("TRAN_ID").addText((String)params.get("QUERYNO"));//查询订单号
		root.addElement("ORI_TRAN_DATE").addText((String)params.get("ORI_TRAN_DATE"));
		root.addElement("ORI_TRAN_ID").addText((String)params.get("OUTTRADENO"));
		return _document.asXML();
	}
	
	/**
	 * 创建代付交易同步返回json数据
	 * @param params
	 * @return
	 */
	public static String generationPayReturnStr(TradeRecord tradeRecord){
		return JSON.toJSONString(tradeRecord);
	}
	
	/**
	 * 创建本行代扣交易同步返回json数据
	 * @param params
	 * @return
	 */
	public static String cmbcBuckleTradeReturnStr(TradeRecord tradeRecord){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerNo", tradeRecord.getCustomerNo());
		map.put("outTradeNo", tradeRecord.getOutTradeNo());
		map.put("currency", "RMB");
		map.put("status", tradeRecord.getStatus());
		map.put("remark", tradeRecord.getRemark());
		map.put("customerOrderno", tradeRecord.getCustomerOrderno());
		
		return JSON.toJSONString(map);
	}
	
	/**
	 * 创建代付账务交易结果查询同步返回json数据
	 * @param params
	 * @return
	 */
	public static String generationQueryReturnStr(TradeRecord tradeRecord){
		return JSON.toJSONString(tradeRecord);
	}
	
	/**
	 * 创建代付交易系统交易订单
	 * @param params
	 * @return
	 */
	public static TradeRecord createGpTradeVo(Map<String, Object> params){
		
		TradeRecord tradeRecord = new TradeRecord();
		
		tradeRecord.setCreateDate(new Date());
		tradeRecord.setCreator("system");
		tradeRecord.setCustomerAsynurl((String)params.get("customerAsynurl"));
		tradeRecord.setCustomerCurrency((String)params.get("customerCurrency"));
		tradeRecord.setCustomerNo((String)params.get("customerNo"));
		tradeRecord.setCustomerOrderno((String)params.get("customerOrderno"));
		tradeRecord.setCustomerTotalamount(Integer.valueOf((String)params.get("customerTotalamount")));
		tradeRecord.setOutTradeNo(String.valueOf(System.currentTimeMillis()));//? 生成流水号
		tradeRecord.setRemark((String)params.get("remark"));
		tradeRecord.setStatus(2);//处理中
		
		return tradeRecord;
	}
	
	/**
	 * 创建本行代扣交易系统交易订单
	 * @param params
	 * @return
	 */
	public static TradeRecord createCBTradeVo(Map<String, Object> params){
		
		TradeRecord tradeRecord = new TradeRecord();
//		tradeVo.setOrderNo((String)params.get("ORDERNO"));
//		tradeVo.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
//		params.put("OUTTRADENO", tradeVo.getOutTradeNo());
//		tradeVo.setAmount(Integer.valueOf((String)params.get("TRANS_AMT")));
//		tradeVo.setCustomerNo((String)params.get("customerNo"));
//		tradeVo.setAsynurl((String)params.get("ASYNURL"));
//		tradeVo.setTradeType(CmbcConstants.TRADE_TYPE_GP);
//		tradeVo.setCreateDate(new Date());
//		tradeVo.setCreator("system");
//		if(params.get("BANK_TYPE")!=null){
//			tradeVo.setReceiveBankCode((String)params.get("BANK_TYPE"));
//		}
//		tradeVo.setReceiveAccount((String)params.get("ACC_NO"));
//		tradeVo.setReceiveAccountName((String)params.get("ACC_NAME"));
//		tradeVo.setRemark((String)params.get("REMARK"));
//		tradeVo.setStatus(Integer.valueOf(CmbcConstants.GP_SYS_STATUS_WAIT));

		return tradeRecord;
	}
	
	/**
	 * 组装代付交易发送报文
	 * @param params
	 * @return
	 */
	public static String createGpSendstr(Map<String, Object> params) throws UnsupportedEncodingException{
		//获取xml发送报文
		String tradeXml = CmbcHandle.generationPayXml(params);
		//获取xml发送报文字节长度
		String xmlBtyeLength = getXmlLengthStr(tradeXml);
		//获取xml签名（MD5）
		String sign = Md5Encrypt.md5(tradeXml+Global.getConfig("cmbc.gp.mer.key"));
		//获取服务码定长15位，左对齐，右补空
		String serviceCodeStr = getServiceCodeStr(CmbcConstants.GP_PAY_CODE);
		
		return xmlBtyeLength+serviceCodeStr+tradeXml+sign;
	}
	
	/**
	 * 组装代付交易查询发送报文
	 * @param params
	 * @return
	 */
	public static String createGquerySendstr(Map<String, Object> params) throws UnsupportedEncodingException{
		//获取xml发送报文
		String tradeXml = CmbcHandle.generationQueryXml(params);
		//获取xml发送报文字节长度
		String xmlBtyeLength = getXmlLengthStr(tradeXml);
		//获取xml签名（MD5）
		String sign = Md5Encrypt.md5(tradeXml+Global.getConfig("cmbc.gp.mer.key"));
		//获取服务码定长15位，左对齐，右补空
		String serviceCodeStr = getServiceCodeStr(CmbcConstants.GP_PAY_CODE);
		
		return xmlBtyeLength+serviceCodeStr+tradeXml+sign;
	}
	
	
	/**
	 * 组装本行代扣交易发送报文
	 * @param params
	 * @return
	 */
	public static String createCbSendstr(Map<String, Object> params) throws UnsupportedEncodingException{
		//获取xml发送报文
		String tradeXml = CmbcHandle.generationPayXml(params);
		//获取xml发送报文字节长度
		String xmlBtyeLength = getXmlLengthStr(tradeXml);
		//获取xml签名（MD5）
		String sign = Md5Encrypt.md5(tradeXml+Global.getConfig("cmbc.gp.mer.key"));
		//获取服务码定长15位，左对齐，右补空
		String serviceCodeStr = getServiceCodeStr(CmbcConstants.GP_PAY_CODE);
		
		return xmlBtyeLength+serviceCodeStr+tradeXml+sign;
	}

	/**
	 * 民生银行xml转换为map
	 * @param xml
	 * @return
	 */
	public static Map<String, Object>  xmlToMap(String xml) throws DocumentException{
		Map<String, Object> map = new HashMap<String, Object>();
		Document document = DocumentHelper.parseText(xml);
		Element root = document.getRootElement();
        //首先获取当前节点的所有属性节点  
        List<Element> list = root.elements();  
        //遍历属性节点  
        for(Element element : list){  
            map.put(element.getName(),element.getText());
        }  
		return map;
	}
	
	/**
	 * 定长6位，右对齐，左补零
	 */
	public static String getXmlLengthStr(String xml) throws UnsupportedEncodingException{
		//获取报文字节长度
		String lengthst = String.valueOf(xml.getBytes("utf-8").length);
		int j = 6-lengthst.length();
		String fill = "";
		for(int i=0;i<j;i++){
			fill = fill +"0";
		}
		lengthst = fill+lengthst;
		
		return lengthst;
	}
	
	/**
	 * 定长15位，左对齐，右补空
	 */
	public static String getServiceCodeStr(String code) throws UnsupportedEncodingException{
		//获取报文字节长度
		int j = 15-code.length();
		String fill = "";
		for(int i=0;i<j;i++){
			fill = fill +" ";
		}
		code = code+fill;
		return code;
	}
	/**
	 * 异步回调结果
	 */
	public static void callBack(String json,String asynurl){
		//异步回调
		PayCallBack payCallBack = new PayCallBack(Global.getConfig("ebank.syn.notify.url"),json,asynurl);
        Thread thread = new Thread(payCallBack);
        thread.start();
        
	}
}
