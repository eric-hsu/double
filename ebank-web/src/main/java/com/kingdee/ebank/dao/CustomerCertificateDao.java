package com.kingdee.ebank.dao;

import com.kingdee.ebank.vo.CustomerCertificateVo;

public interface CustomerCertificateDao {

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
