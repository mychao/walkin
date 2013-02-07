package com.checkins.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkins.bean.CheckinsBean;
import com.checkins.dao.CheckinsDao;
import com.checkins.service.CheckinsService;

/**
 * implements for checins service interface
 * @author mychao
 *
 */
public class CheckinsServiceImpl implements CheckinsService{

	@Autowired
	private CheckinsDao checkinsDao;
	@Override
	public int statTotal() {
		return checkinsDao.statTotal();
	}

	@Override
	public List<CheckinsBean> queryForList(int pageNow, int pageSize) {
		int pageStart = pageNow * pageSize - pageSize;
		return checkinsDao.queryForList(pageStart, pageSize);
	}

	@Override
	public int add(CheckinsBean bean) {
		if(bean == null){
			return -1;
		}
		return checkinsDao.add(bean);
	}

	@Override
	public int edit(CheckinsBean bean) {
		if(bean == null){
			return -1;
		}
		return checkinsDao.modifyById(bean);
	}

	@Override
	public int delete(int id) {
		if(id <= 0){
			return -1;
		}
		return checkinsDao.deleteById(id);
	}

}
