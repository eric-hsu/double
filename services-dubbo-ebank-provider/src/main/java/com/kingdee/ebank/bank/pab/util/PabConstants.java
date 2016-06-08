package com.kingdee.ebank.bank.pab.util;

import java.util.HashMap;
import java.util.Map;

public class PabConstants {
	
	//前置机服务地址
	public static final String url="http://127.0.0.1:7072";
	
	//企业账户余额查询 [4001]
	public static final String trade_code4001="4001  ";
	
	//企业账户余额查询 [4004]
	public static final String trade_code4004="4004  ";
	
	//企业大批量资金划转[4018]
	public static final String trade_code4018="4018  ";
	
	//单笔转账指令查询[4005]
	public static final String trade_code4005="4005  ";

	//批量转账指令查询[4015]
	public static final String trade_code4015="4015  ";
	
	//历史余额查询[4012]
	public static final String trade_code4012="4012  ";
	
	//查询账户当日历史交易明细[4013]
	public static final String trade_code4013="4013  ";
	
	//银行联行号查询[4017]
	public static final String trade_code4017="4017  ";
	
	//代发代扣申请接口[4047]
	public static final String trade_code4047="4047  ";
	
	//代发代扣结果查询接口[4048]
	public static final String trade_code4048="4048  ";
	
	//混合批量转账接口[4027]
	public static final String trade_code4027="4027  ";
	
	//借记卡客户信息验证接口[400101]
	public static final String trade_code400101="400101";
	
	//A001定长222报文头+报文体，客户端需要按此报文头上送。
	public static final String DATA_VERSION="A001";
	//01:银企直连TCP 02:供应链金融03:交易资金04:电票	05:政府前置-昆明国土局06:供应链1号店07:POSP信用卡系统08:资产托管网银09:银企直连HTTP10:交易资金-P2P系统11:实物黄金系统12:政府前置-深圳交警13:交易资金-见证系统14:企业网上银行系统15: 贷贷平安网银
	public static final String TARGET_SYSTEM="01";
	
	//01：GBK缺省02：UTF803：unicode04：iso-8859-1建议使用GBK编码
	public static final String encoding="01";
	
	//01:tcpip 缺省02：http03：webservice
	public static final String protocol="01";
	
	//交易状态-20：成功30：失败其他为银行受理成功处理中
	public static final String TRADE_SUCCESS="20";
	
	//交易状态-20：成功30：失败其他为银行受理成功处理中
	public static final String TRADE_FAIL="30";
	
	//企业-原测试（00203030000000037000）（企业上线的时候由银行分配）
	public static final String mer_code="00203030000000037000";
	
	//企业-原测试账号（11011781161701）
	public static final String account="11011781161701";
	
	//企业-账户名称(SHENFA011781161)
	public static final String accountname="SHENFA011781161";
	
	//企业-代发代扣费项代码
	public static final String busiType="M8YQD";
	
	//企业-代扣
	public static final String withholding="0";
	
	//企业-代付
	public static final String pay="1";
	
	//私人-账号
	public static final String privateaccount="6216260000000000548";
	
	//私人-账户名称
	public static final String privateaccountname="PA测试67336";
	
	//私人-银行名称
	public static final String privateaccountbankname="平安银行";
	
	//网关正常返回
	public static final String GATEWAY_SUCCESS_CODE="000000";
	
	//供应链报文统一交易码'4001  ',默认填写000000
	public static final String trade_code="000000";
	
	//操做员代码,5个字符，可为空，字符用空格代替
	public static final String operator_code="     ";
	
	//01-	请求02-	应答
	public static final String trade_type="01";
	
	//请求非必输，请求时必须填写000000
	public static final String return_code="      ";
	
	//0-结束包，1-还有后续包同请求方流水号一起运作请求方系统流水号要和第一次保持一致
	public static final String request_mark="0";
	
	//如果有后续包请求第一次	000第二次	001第三次	002依此增加请求方系统流水号要和第一次保持一致。
    public static final String request_time="000";
    
    //0-	不签名1-	签名 （填0，企业不管，由银行客户端完成）
    public static final String sign_flag="0";
    
    //0-	裸签（填1，企业不管，由银行客户端完成）1-	PKCS7
    public static final String sign_format=" ";
    
    //RSA-SHA1
    public static final String sign_type="";
    
    //签名报文数据长度,填写0
    public static final String sign_length="0000000000";
    
    //0-	没有,默认为0；有的话填具体个数，最多9个目前只支持1
    public static final String annex_number="0";
    
    public static Map<String,String> map = new HashMap<String,String>();
    
    public static String getbankgroupCode(String bankname){
    	map.put("中国工商银行", "102");
    	map.put("中国农业银行", "103");
    	map.put("中国银行", "104");
    	map.put("中国建设银行", "105");
    	map.put("国家开发银行", "201");
    	map.put("中国进出口银行", "202");
    	map.put("中国农业发展银行", "203");
    	map.put("交通银行", "301");
    	map.put("中信银行", "302");
    	map.put("中国光大银行", "303");
    	map.put("华夏银行", "304");
    	map.put("中国民生银行", "305");
    	map.put("广东发展银行", "306");
    	map.put("平安银行", "307");
    	map.put("招商银行", "308");
    	map.put("兴业银行", "309");
    	map.put("上海浦东发展银行", "310");
    	String code = map.get(bankname);
    	if(code==null){
    		code = "313";
    	}
    	return code;
    }
}
