package com.checkins.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.checkins.bean.AdministratorBean;
import com.checkins.dao.AdministratorDao;

/**
 * administrator implements for database
 * @author mychao
 *
 */
public class AdministratorDaoImpl extends BaseDao implements AdministratorDao{

	@Override
	public int add(AdministratorBean bean) {
		String sql = "insert into administrator(name,password) values (?,?)";
		return this.getJdbcTemplate().update(sql, bean.getName(),bean.getPassword());
	}

	@Override
	public int statByUserName(String userName) {
		return 0;
	}

	@Override
	public AdministratorBean queryById(int id) {
		return null;
	}

	@Override
	public AdministratorBean queryById(String userName) {
		String sql = "select * from administrator where name = ?";
		Object[] obj = new Object[]{userName};
		try{
			return this.queryForBean(sql, obj, AdministratorBean.class);
		}catch(DataAccessException e){
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int statTotal() {
		String sql = "select count(0) from administrator";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	@Override
	public List<AdministratorBean> query(int pageStart, int pageSize) {
		String sql = "select * from administrator order by id desc limit ?,?";
		try{
			return this.queryForBeanList(sql, new Object[]{pageStart, pageSize}, AdministratorBean.class);
		}catch(DataAccessException e){
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int edit(AdministratorBean bean) {
		String sql = "update administrator set name = ? , password = ? where id = ?";
		
		return this.getJdbcTemplate().update(sql, bean.getName(), bean.getPassword(), bean.getId());
	}

	@Override
	public int delete(int id) {
		String sql = "delete from administrator where id = ?";
		
		return this.getJdbcTemplate().update(sql, id);
	}

}
