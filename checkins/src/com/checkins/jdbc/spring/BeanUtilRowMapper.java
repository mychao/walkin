package com.checkins.jdbc.spring;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 * 基于apache commons-beanutils.jar,bean转换
 * @author mychao
 *
 * @param <T>
 */
public class BeanUtilRowMapper<T> implements RowMapper<T>{
	
	public BeanUtilRowMapper(Class<?> classType){
		this.classType = classType;
	}
	
	private Class<?> classType;

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		try {
			T bean = (T) classType.newInstance();
			ColumnMapRowMapper columnMapRow = new ColumnMapRowMapper();
			Map<String, Object> map = columnMapRow.mapRow(rs, rowNum);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
