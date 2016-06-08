package com.kingdee.ebank.services;

import com.kingdee.ebank.vo.CustomerCertificateVo;

public interface CustomerCertificateService {

	/**
	 * 插入请求对象
	 * @param vo
	 * @return
	 */
	public int add(CustomerCertificateVo vo);
	
	/**
	 * 查询请求对象
	 * @param customerNo
	 * @return
	 */
	public CustomerCertificateVo get(String customerNo);

}
