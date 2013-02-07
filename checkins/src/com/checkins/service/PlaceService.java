package com.checkins.service;

import java.util.List;

import com.checkins.bean.PlaceBean;

/**
 * service interface for place
 * @author mychao
 *
 */
public interface PlaceService {
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
	public List<PlaceBean> queryForList(int pageNow, int pageSize);
	/**
	 * add a new record
	 * @param checkinsBean
	 * @return >0:success
	 */
	public int add(PlaceBean checkinsBean);

	/**
	 * edit checkinfo
	 * @param bean
	 * @return >0:success
	 */
	public int edit(PlaceBean bean);
	/**
	 * delete by id
	 * @param id
	 * @return >0:success
	 */
	public int delete (int id);
}
