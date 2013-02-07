package com.checkins.service;

import java.util.List;

import com.checkins.bean.AdministratorBean;

/**
 * service interface for administrator 
 * @author mychao
 *
 */
public interface AdministratorService {
	/**
	 * add new administrator
	 * @param bean
	 * @return
	 */
	public int addNewAdministrator(AdministratorBean bean);
	/**
	 * edit administrator into by id
	 * @param bean
	 * @return
	 */
	public int edit(AdministratorBean bean);
	
	/**
	 * delete administrator by id
	 * @param id
	 * @return
	 */
	public int delete(int id);

	/**
	 * query administrator by user name
	 * @param userName
	 * @return
	 */
	public AdministratorBean queryByUserName(String userName);
	
	/**
	 * stat total records
	 * @return
	 */
	public int statTotal();
	
	/**
	 * query administrator list
	 * @param pageNow
	 * @param pageSize
	 * @return
	 */
	public List<AdministratorBean> query(int pageNow, int pageSize);
}
