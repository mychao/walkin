package com.checkins.dao;

import java.util.List;

import com.checkins.bean.CheckinsBean;

/**
 * checkin interface for database
 * @author mychao
 *
 */
public interface CheckinsDao {

	/**
	 * insert a checkin info data
	 * @param bean
	 * @return >0:success
	 */
	public int add(CheckinsBean bean);
	
	/**
	 * query checkins list
	 * @param pageStart 
	 * @param pageSize
	 * @return List<CheckinsBean> 
	 */
	public List<CheckinsBean> queryForList(int pageStart, int pageSize);
	
	/**
	 * delete user checkin record by auto generated id
	 * @param id
	 * @return >0:success
	 */
	public int deleteById(int id);
	/**
	 * modify checkin record by auto generated id
	 * @param bean
	 * @return >0:success
	 */
	public int modifyById(CheckinsBean bean);
	
	/**
	 * stat total record number
	 * @return
	 */
	public int statTotal();
}
