package com.kingdee.ebank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingdee.ebank.dao.CustomerCertificateDao;
import com.kingdee.ebank.services.CustomerCertificateService;
import com.kingdee.ebank.vo.CustomerCertificateVo;

@Service
public class CustomerCertificateServiceImpl implements
		CustomerCertificateService {

	@Autowired
	private CustomerCertificateDao customerCertificateDao;
	
	public int add(CustomerCertificateVo vo) {
		return customerCertificateDao.add(vo);
	}

	public CustomerCertificateVo get(String customerNo) {
		return customerCertificateDao.get(customerNo);
	}
}
