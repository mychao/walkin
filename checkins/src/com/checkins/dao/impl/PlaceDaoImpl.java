package com.checkins.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.checkins.bean.PlaceBean;
import com.checkins.dao.PlaceDao;

/**
 * place implements for database interface
 * @author mychao
 *
 */
public class PlaceDaoImpl extends BaseDao implements PlaceDao{

	@Override
	public int add(PlaceBean bean) {
		String sql = "insert into place (geolocation, address) values(?, ?)";
		return this.getJdbcTemplate().update(sql, bean.getGeolocation(), bean.getAddress());
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from place where id = ?";
		return this.getJdbcTemplate().update(sql, id);
	}

	@Override
	public int modifyById(PlaceBean bean) {
		String sql = "update place set geolocation = ?, address = ? where id = ?";
		return this.getJdbcTemplate().update(sql, bean.getGeolocation(), bean.getAddress(), bean.getId());
	}

	@Override
	public List<PlaceBean> queryForList(int pageStart, int pageSize) {
		String sql = "select * from place order by id desc limit ?,?";
		try{
			return this.queryForBeanList(sql, new Object[]{pageStart, pageSize}, PlaceBean.class);
		}catch(DataAccessException e){
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int statTotal() {
		String sql = "select count(0) from place";
		return this.getJdbcTemplate().queryForInt(sql);
	}

}
