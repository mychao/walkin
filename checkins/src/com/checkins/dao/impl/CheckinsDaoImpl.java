package com.checkins.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.checkins.bean.CheckinsBean;
import com.checkins.dao.CheckinsDao;

/**
 * checkin implements for database interface
 * 
 * @author mychao
 * 
 */
public class CheckinsDaoImpl extends BaseDao implements CheckinsDao {

	@Override
	public int add(CheckinsBean bean) {
		String sql = "insert into checkins (uid, geolocation,checkinTime) values (?, ?, ?)";
		return this.getJdbcTemplate().update(sql, bean.getUid(),
				bean.getGeolocation(), bean.getCheckinTime());
	}

	@Override
	public List<CheckinsBean> queryForList(int pageStart, int pageSize) {
		String sql = "select * from checkins order by id desc limit ?, ?";
		try {
			return this.queryForBeanList(sql, new Object[] { pageStart,
					pageSize }, CheckinsBean.class);
		} catch (DataAccessException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from checkins where id = ?";

		return this.getJdbcTemplate().update(sql, id);
	}

	@Override
	public int modifyById(CheckinsBean bean) {
		String sql = "update checkins set uid = ?, checkinTime = ?, geolocation = ? where id = ?";

		return this.getJdbcTemplate().update(sql, bean.getUid(),
				bean.getCheckinTime(), bean.getGeolocation(), bean.getId());
	}

	@Override
	public int statTotal() {
		String sql = "select count(0) from checkins";
		return this.getJdbcTemplate().queryForInt(sql);
	}

}
