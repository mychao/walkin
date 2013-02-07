package com.checkins.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkins.bean.PlaceBean;
import com.checkins.dao.PlaceDao;
import com.checkins.service.PlaceService;

/**
 * implements for place service interface
 * @author mychao
 *
 */
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	
	@Override
	public int statTotal() {
		return placeDao.statTotal();
	}

	@Override
	public List<PlaceBean> queryForList(int pageNow, int pageSize) {
		int pageStart = pageNow * pageSize - pageSize;
		return placeDao.queryForList(pageStart, pageSize);
	}

	@Override
	public int add(PlaceBean checkinsBean) {
		if(checkinsBean == null){
			return -1;
		}
		return placeDao.add(checkinsBean);
	}

	@Override
	public int edit(PlaceBean bean) {
		if(bean == null){
			return -1;
		}
		return placeDao.modifyById(bean);
	}

	@Override
	public int delete(int id) {
		if(id <= 0){
			return -1;
		}
		return placeDao.deleteById(id);
	}

}
