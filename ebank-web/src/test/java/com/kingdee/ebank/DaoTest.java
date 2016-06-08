package com.kingdee.ebank;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.kingdee.ebank.common.SpringTransactionalContextTests;
import com.kingdee.ebank.services.CustomerCertificateService;
import com.kingdee.ebank.services.CustomerService;
import com.kingdee.ebank.vo.CustomerCertificateVo;
import com.kingdee.ebank.vo.CustomerVo;


/**
 * Unit test for simple App.
 */

public class DaoTest extends SpringTransactionalContextTests{
	private static Logger logger = Logger.getLogger(DaoTest.class);
	@Resource
	private CustomerService customerService;
	
	@Resource
	private CustomerCertificateService customerCertificateService;
	
	
	//@Test
	public void addCustomer(){
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCreateDate(new Date());
		customerVo.setCreator("eric");
		customerVo.setCustomerName("kis");
		customerVo.setCustomerNo("121121212");
		customerVo.setRemark("");
		customerVo.setStatus(true);
		customerService.add(customerVo);
	}
	
	//@Test
	public void getCustomer(){
		CustomerVo customerVo = customerService.get("121121212");
		logger.info("========================="+customerVo.getCustomerName());
	}
	
	//@Test
	public void addCustomerCertificate(){
		CustomerCertificateVo customerCertificateVo = new CustomerCertificateVo();
		
		customerCertificateVo.setCreateDate(new Date());
		customerCertificateVo.setCreator("eric1");
		customerCertificateVo.setCustomerNo("121121212");
		customerCertificateVo.setPublicKey("sdfgdfgdfgdfgfd");
		customerCertificateVo.setRemark("");
		customerCertificateVo.setStatus(true);
		
		
		customerCertificateService.add(customerCertificateVo);
	}
	
	@Test
	public void getCustomerCertificate(){
		CustomerCertificateVo customerCertificateVo = customerCertificateService.get("121121212");
		logger.info("========================="+customerCertificateVo.getPublicKey());
	}
}
