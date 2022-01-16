package com.njwangbo.oa.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.PropertiesDao;
import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.util.JdbcTemplate;

/**
 * 配置表数据操作实现类
 * @author soft01
 *
 */
public class PropertiesDaoImpl implements PropertiesDao{

	/**
	 * 查询所有配置
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Properties> queryAll() throws SQLException {
		
		String sql = "select t_type,t_keyId,t_pageValue from t_properties";
		
		List<Properties> propertieList = null;
		
		propertieList = JdbcTemplate.executeQuery(sql, new PropertiesMapper(), new Object[]{});
		
		return propertieList;
	}

	/**
	 * 查询所有的请假类型
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Properties> queryAllHoliday() throws SQLException {
		String sql = "select t_type,t_keyId,t_pageValue from t_properties where t_type = '1_holiday_type'";
		
		List<Properties> propertiesList = null;
		propertiesList = JdbcTemplate.executeQuery(sql, new PropertiesMapper(), new Object[]{});
		
		return propertiesList;
	}

	/**
	 * 查询所有的报销类型
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Properties> queryAllBaoXiao() throws SQLException {
		String sql = "select t_type,t_keyId,t_pageValue from t_properties where t_type = '2_BX_type'";
		
		List<Properties> propertiesList = null;
		propertiesList = JdbcTemplate.executeQuery(sql, new PropertiesMapper(), new Object[]{});
		
		return propertiesList;
	}

}
