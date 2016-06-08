package com.kingdee.ebank.cmbc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.kingdee.ebank.bank.cmbc.GpasynReceiveService;
import com.kingdee.ebank.util.SpringContextUtil;
import com.kingdee.ebank.util.SpringTransactionalContextTests;

public class ServiceTest  extends SpringTransactionalContextTests{
	
	@Test
	public void gptest(){
		try {
			
		
//			Map<String, Object> tradeMap = new HashMap<String, Object>();
//	    	
//			tradeMap.put("TRAN_DATE", new SimpleDateFormat("yyyyMMdd").format( new Date()));
//	    	tradeMap.put("TRAN_TIME", new SimpleDateFormat("HHmmss").format( new Date()));
//	    	tradeMap.put("ORDERNO", String.valueOf(System.currentTimeMillis()));
//	    	tradeMap.put("CURRENCY", "RMB");
//	    	tradeMap.put("ACC_NO", "62221310507254251");
//	    	tradeMap.put("ACC_NAME", "胡顺强");
//	    	tradeMap.put("TRANS_AMT", "100");
//	    	tradeMap.put("REMARK", "代付");
//	    	
//			cmbcService.generationPay(tradeMap);
//			
			GpasynReceiveService hs = (GpasynReceiveService) SpringContextUtil.getBean("gPasynReceiveService");
			System.out.println(hs);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
