package com.kingdee.ebank.dao;

import com.kingdee.ebank.vo.CustomerVo;

public interface CustomerDao {

	/**
	 * 插入请求对象
	 * @param vo
	 * @return
	 */
	public int add(CustomerVo vo);
	
	/**
	 * 查询请求对象
	 * @param customerNo
	 * @return
	 */
	public CustomerVo get(String customerNo);

}
