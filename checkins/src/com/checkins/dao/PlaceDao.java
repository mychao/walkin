package com.checkins.dao;

import java.util.List;

import com.checkins.bean.PlaceBean;

/**
 * place interface for database
 * @author mychao
 *
 */
public interface PlaceDao {

	/**
	 * add a new place record
	 * @param bean
	 * @return >0: success
	 */
	public int add(PlaceBean bean);
	/**
	 * delete an exists place
	 * @param bean
	 * @return >0:success
	 */
	public int deleteById(int id);
	/**
	 * modify an exists place info
	 * @param bean
	 * @return >0:success
	 */
	public int modifyById(PlaceBean bean);
	/**
	 * query place's infomation list
	 * @param pageStart
	 * @param pageSize
	 * @return List<PlaceBean>
	 */
	public List<PlaceBean> queryForList(int pageStart, int pageSize);
	/**
	 * stat the records total number
	 * @return 
	 */
	public int statTotal();
}
