package com.kingdee.ebank;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingdee.ebank.util.HttpClientUtil;
import com.kingdee.ebank.util.sign.RSA;
import com.kingdee.ebank.util.sign.SignCore;


public class TestMain {
	private static String url = "http://localhost:8282/ebank-web/api/handle";
	private static String cername = "test_customer_private_pkcs8_key.pem";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
	
	    	Map<String, Object> tradeMap = new HashMap<String, Object>();
	    	
	    	tradeMap.put("customerNo", "121121212");
	    	tradeMap.put("channel", "pab");
	    	tradeMap.put("tradecode", "0002");//
	    	tradeMap.put("bankMerCode", "00203030000000037000");//银行客户号
	    	
	    	/**
	    	 * 一，平安接口
	    	 */
	    	if("pab".equals(tradeMap.get("channel"))){
		    	//1 企业账户余额查询 [4001]
		    	if("0000".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    	}
		    	//2 企业单笔资金划转[4004]
		    	else if("0001".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", ""+System.currentTimeMillis());
		    		tradeMap.put("amount", "10");
		    		tradeMap.put("currency", "RMB");
		    		tradeMap.put("payAccount", "11011781161701");
			    	tradeMap.put("payAccountName", "SHENFA011781161");
			    	tradeMap.put("receiveBankName", "中国工商银行");
			    	tradeMap.put("receiveAccount", "11011123456");
			    	tradeMap.put("receiveAccountName", "四十大盗");
			    	tradeMap.put("mobile", "18617126710");
			    	tradeMap.put("customerAsynurl", "异步通知地址");
			    	tradeMap.put("remark", "平安银行测试");
		    	}
		    	
		    	//3  企业大批量资金划转[4018]
		    	else if("0002".equals(tradeMap.get("tradecode"))){
		    		
		    		tradeMap.put("customerOrderno", "2015"+System.currentTimeMillis());
		    		tradeMap.put("totalcount", "2");
		    		tradeMap.put("totalamount", "10");
		    		tradeMap.put("currency", "RMB");
		    		tradeMap.put("payAccount", "11011781161701");
			    	tradeMap.put("payAccountName", "SHENFA011781161");
			    	
			    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
			    	
			    	Map<String, Object> ext0 = new HashMap<String, Object>();
			    	ext0.put("itemOrderno", ""+System.currentTimeMillis());
			    	ext0.put("itemAmount", "10");
			    	ext0.put("receiveBankName", "平安银行");
			    	ext0.put("receiveAccount", "6216260000000000548");
			    	ext0.put("receiveAccountName", "PA测试67336");
			    	ext0.put("useEx", "发工资");//资金用途
			    	list.add(ext0);
			    	
			    	Map<String, Object> ext1 = new HashMap<String, Object>();
			    	ext1.put("itemOrderno", ""+System.currentTimeMillis());
			    	ext1.put("itemAmount", "10");
			    	ext1.put("receiveBankCode", "平安银行");
			    	ext1.put("receiveAccount", "6216260000000000548");
			    	ext1.put("receiveAccountName", "PA测试67336");
			    	ext0.put("useEx", "发工资");//资金用途
			    	list.add(ext1);
			    	tradeMap.put("extra", list);
			    	tradeMap.put("remark", "平安银行测试");
		    	}
		    	
		    	//3 单笔转账指令查询[4005]
		    	else if("0003".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "1443075703449");
		    	}
		    	
		    	//4 批量转账指令查询[4015]
		    	else if("0004".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "45416161616158181");
		    	}
		    	
		    	//5  历史余额查询[4012]
		    	else if("0005".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    		tradeMap.put("rptDate", "20150910");//限制查询当前日期的前100天内的
		    	}
		    	
		    	//6  查询账户当日历史交易明细[4013]
		    	else if("0006".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    		tradeMap.put("currency", "RMB");//限制查询当前日期的前100天内的
		    		tradeMap.put("beginDate", "20150922");//开始日期
		    		tradeMap.put("endDate", "20150922");//结束日期
		    		tradeMap.put("pageNo", "1");//查询页码1：第一页，依次递增
		    	}
		    	
		    	//7   代发代扣申请接口[4047]
		    	else if("0007".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "2015"+System.currentTimeMillis());
		    		tradeMap.put("busiType", "2");// 客户在银行签约代发代扣后银行通过的费项代码
		    		tradeMap.put("payType", "0");//0：扣  1：付
		    		tradeMap.put("srcAccNo", "11011781161701");//代发代扣协议签约账户,本方账户
		    		tradeMap.put("totalcount", "2");
		    		tradeMap.put("totalamount", "20");
		    		tradeMap.put("settleType", "0");//该字段只对数量为1笔的代扣有效0：实时，如果客户有单笔代扣协议，建议选择0，交易实时处理并实时返回代扣结果；1：非实时，如果客户只有批量代扣协议，没有单笔代扣协议，则只能选择该方式，该方式批量处理，处理结果需要发送4048交易查询。
		    		tradeMap.put("currency", "RMB");
			    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
			    	
			    	Map<String, Object> ext0 = new HashMap<String, Object>();
			    	ext0.put("itemOrderno", System.currentTimeMillis());
			    	ext0.put("itemAmount", "10");
			    	ext0.put("oppAccNo", "6216260000000000548");
			    	ext0.put("oppAccName", "PA测试67336");
			    	list.add(ext0);
			    	
			    	Map<String, Object> ext1 = new HashMap<String, Object>();
			    	ext1.put("itemOrderno", System.currentTimeMillis());
			    	ext1.put("itemAmount", "10");
			    	ext1.put("oppAccNo", "6216260000000000548");
			    	ext1.put("oppAccName", "PA测试67336");
			    	list.add(ext1);
			    	tradeMap.put("extra", list);
			    	tradeMap.put("remark", "平安银行测试");
		    	}
		    	
		    	//8   代发代扣结果查询接口[4048]
		    	else if("0008".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "45416161616158181");
		    	}
		    	
		    	//9   混合批量转账接口[4027]
		    	else if("0009".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("customerOrderno", "2015"+System.currentTimeMillis());
		    		tradeMap.put("totalcount", "2");
		    		tradeMap.put("totalamount", "10");
		    		tradeMap.put("currency", "RMB");
		    		tradeMap.put("payAccount", "11011781161701");
			    	tradeMap.put("payAccountName", "SHENFA011781161");
			    	
			    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
			    	
			    	Map<String, Object> ext0 = new HashMap<String, Object>();
			    	ext0.put("itemOrderno", System.currentTimeMillis());
			    	ext0.put("itemAmount", "10");
			    	ext0.put("receiveBankName", "平安银行");
			    	ext0.put("receiveAccount", "6216260000000000548");
			    	ext0.put("receiveAccountName", "PA测试67336");
			    	ext0.put("useEx", "发工资");//资金用途
			    	list.add(ext0);
			    	
			    	Map<String, Object> ext1 = new HashMap<String, Object>();
			    	ext1.put("itemOrderno", System.currentTimeMillis());
			    	ext1.put("itemAmount", "10");
			    	ext1.put("receiveBankCode", "平安银行");
			    	ext1.put("receiveAccount", "6216260000000000548");
			    	ext1.put("receiveAccountName", "PA测试67336");
			    	ext0.put("useEx", "发工资");//资金用途
			    	list.add(ext1);
			    	tradeMap.put("extra", list);
			    	tradeMap.put("remark", "平安银行测试");
		    	}
		    	
		    	//10   借记卡客户信息验证接口[400101]
		    	else if("0010".equals(tradeMap.get("tradecode"))){
		    		tradeMap.put("account", "11011781161701");
		    		tradeMap.put("accountName", "小张");
		    		tradeMap.put("certType", "1");//证件类型代码
		    		tradeMap.put("certNo", "11011781161701");//证件号码
			    	tradeMap.put("mobile", "18617126710");//银行预留手机
		    	}
		    	
		    	
	    	}
	    	
	    	//2  民生银行
	    	else if("cmbc".equals(tradeMap.get("channel"))){
	    		//1 单笔联机代付
		    	if("0000".equals(tradeMap.get("tradecode"))){
		    		
		    		
		    	}
	    	}
	    	
	    	Gson gson = new Gson();

	    	String jsonstr= gson.toJson(tradeMap);
	    	
	    	System.out.println(jsonstr);
	    	System.out.println("发送订单号："+tradeMap.get("customerOrderno"));
	    	
	    	
//	    	其他银行相关参数
//	    	tradeMap.put("account", "42562112247882215");
//	    	tradeMap.put("TRAN_DATE", new SimpleDateFormat("yyyyMMdd").format( new Date()));
//	    	tradeMap.put("TRAN_TIME", new SimpleDateFormat("HHmmss").format( new Date()));
//	    	tradeMap.put("ORDERNO", String.valueOf(System.currentTimeMillis()));
//	    	tradeMap.put("CURRENCY", "RMB");
//	    	tradeMap.put("ACC_NO", "62221310507254251");
//	    	tradeMap.put("ACC_NAME", "ddd");
//	    	tradeMap.put("TRANS_AMT", "100");
//	    	tradeMap.put("REMARK", "代付");
//	    	tradeMap.put("ASYNURL", "http://");
//	    	
//	    	String signstr = SignCore.createLinkString(tradeMap);
	    	String privatekey = getPrivateKey(cername);
	    	String sign = RSA.sign(jsonstr, privatekey, "utf-8");
	    	
	    	
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put("sign", sign);
	    	params.put("data", jsonstr);
	    	
//	    	Map<String, Object> extra = gson.fromJson(jsonstr,new TypeToken<Map<String, Object>>(){}.getType());
//	
//			String bodyStr = HttpClientUtil.sendPostRequest(url,params,"UTF-8" , "UTF-8");

			System.out.println(params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String getPrivateKey(String path) throws IOException{

		InputStream ips =RSA.class.getResourceAsStream(path);
		
		int i=0; 
		byte[] b = new byte[1024];
		StringBuffer buffer = new StringBuffer();
		while((i=ips.read(b))!=-1){
			String str = new String(b);
			buffer.append(str);
		}
		String privatekey = buffer.toString();
		privatekey = privatekey.replaceAll("-----BEGIN PRIVATE KEY-----", "");
		privatekey = privatekey.replaceAll("-----END PRIVATE KEY-----", "");
	
		System.out.println(privatekey.trim());
		
		return privatekey.trim();

	}
	

}
