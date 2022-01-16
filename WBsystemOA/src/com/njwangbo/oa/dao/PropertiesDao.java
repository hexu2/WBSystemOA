package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Properties;

/**
 * 配置标数据操作层
 * @author soft01
 *
 */
public interface PropertiesDao {
	
	/**
	 * 查询所有配置
	 * @return List<Properties> 所有配置的集合
	 * @throws SQLException
	 */
	List<Properties> queryAll() throws SQLException;
	
	/**
	 * 查询所有请假类型记录
	 * @return List<Properties> 所有配置的集合
	 * @throws SQLException
	 */
	List<Properties> queryAllHoliday() throws SQLException;

	/**
	 * 查询所有的报销类型记录
	 * @return
	 * @throws SQLException
	 */
	List<Properties> queryAllBaoXiao() throws SQLException;

}
