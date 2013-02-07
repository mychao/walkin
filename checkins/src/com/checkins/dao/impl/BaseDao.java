package com.checkins.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.checkins.jdbc.spring.BeanUtilRowMapper;

/**
 * dao 基本类,用来注入数据源，使用spring提供的jdbcTemplate进行数据库操作
 * @author mychao
 *
 */
public class BaseDao extends JdbcDaoSupport{
	
	/**
	 * 数据库与类对象转化，先去map再使用apache api进行转换，以后可优化
	 * @param sql
	 * @param params
	 * @param classType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryForBean(String sql, Object[] params, Class<?> classType){
		Map<String, Object> map = null;
		try {
			map = this.getJdbcTemplate().queryForMap(sql, params);
			T bean = (T) classType.newInstance();
			org.apache.commons.beanutils.BeanUtils.populate(bean, map);
			return bean;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch(DataAccessException e){
			System.err.println(e.getMessage());
		} finally{
			if(map != null){
				map.clear();
				map = null;
			}
		}
		return null;
	}

	/**
	 * 返回bean list
	 * @param sql
	 * @param params
	 * @param elementType
	 * @return
	 * @throws DataAccessException
	 */
	public <T> List<T> queryForBeanList(String sql, Object[] params, Class<T> elementType) throws DataAccessException {
		return this.getJdbcTemplate().query(sql, params, new BeanUtilRowMapper<T>(elementType));
	}
	
}
