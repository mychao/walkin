package com.checkins.dao;

import java.util.List;

import com.checkins.bean.AdministratorBean;

/**
 * administrator interface for database
 * 
 * @author mychao
 * 
 */
public interface AdministratorDao {

	/**
	 * add a new administrator
	 * 
	 * @param bean
	 * @return
	 */
	public int add(AdministratorBean bean);
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
	 * stat user count by user name
	 * 
	 * @param userName
	 * @return
	 */
	public int statByUserName(String userName);

	/**
	 * query by manager id
	 * 
	 * @param id
	 * @return AdministratorBean
	 */
	public AdministratorBean queryById(int id);
	
	/**
	 * query by manager id
	 * 
	 * @param id
	 * @return AdministratorBean
	 */
	public AdministratorBean queryById(String userName);
	/**
	 * stat total records
	 * @return
	 */
	public int statTotal();
	
	/**
	 * query administrator list
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public List<AdministratorBean> query(int pageStart, int pageSize);
}
