package com.checkins.service;

import java.util.List;

import com.checkins.bean.CheckinsBean;

/**
 * service interface for checkins
 * @author mychao
 *
 */
public interface CheckinsService {
	
	/**
	 * stat the records total number
	 * @return 
	 */
	public int statTotal();
	/**
	 * query checkin list
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<CheckinsBean> queryForList(int pageNow, int pageSize);
	/**
	 * add a new record
	 * @param checkinsBean
	 * @return >0:success
	 */
	public int add(CheckinsBean checkinsBean);

	/**
	 * edit checkinfo
	 * @param bean
	 * @return >0:success
	 */
	public int edit(CheckinsBean bean);
	/**
	 * delete by id
	 * @param id
	 * @return >0:success
	 */
	public int delete (int id);
	
}
