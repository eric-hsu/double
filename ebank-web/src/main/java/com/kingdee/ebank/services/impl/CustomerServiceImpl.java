package com.kingdee.ebank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kingdee.ebank.dao.CustomerDao;
import com.kingdee.ebank.services.CustomerService;
import com.kingdee.ebank.vo.CustomerVo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	public int add(CustomerVo vo) {
		return customerDao.add(vo);
	}

	public CustomerVo get(String customerNo) {
		return customerDao.get(customerNo);
	}

}
