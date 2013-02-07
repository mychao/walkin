package com.checkins.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.checkins.bean.AdministratorBean;
import com.checkins.dao.AdministratorDao;
import com.checkins.service.AdministratorService;
import com.checkins.util.TypeUtils;
/**
 * implements for administrator service
 * @author mychao
 *
 */
public class AdministratorServiceImpl implements AdministratorService{

	@Autowired
	private AdministratorDao administratorDao;
	
	@Override
	public AdministratorBean queryByUserName(String userName) {
		if(TypeUtils.isEmptyString(userName)){
			return null;
		}
		return administratorDao.queryById(userName);
	}

	@Override
	public int statTotal() {
		return administratorDao.statTotal();
	}

	@Override
	public List<AdministratorBean> query(int pageNow, int pageSize) {
		int pageStart = pageNow * pageSize - pageSize;
		return administratorDao.query(pageStart, pageSize);
	}

	@Override
	public int addNewAdministrator(AdministratorBean bean) {
		return administratorDao.add(bean);
	}

	@Override
	public int edit(AdministratorBean bean) {
		if(bean == null){
			return -1;
		}
		return administratorDao.edit(bean);
	}

	@Override
	public int delete(int id) {
		if(id <= 0){
			return -1;
		}
		return administratorDao.delete(id);
	}

}
