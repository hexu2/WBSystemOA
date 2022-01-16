package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.PropertiesDao;
import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.PropertiesService;
import com.njwangbo.oa.transaction.Transaction;

/**
 * 配置表业务逻辑实现类
 * @author soft01
 *
 */
public class PropertiesServiceImpl implements PropertiesService{
	
	private PropertiesDao propertiesDao;
	private Transaction tx;
	
	public void setPropertiesDao(PropertiesDao propertiesDao) {
		this.propertiesDao = propertiesDao;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	/**
	 * 查询所有配置表记录
	 * @return
	 * @throws OAException
	 */
	@Override
	public List<Properties> queryAllProperties() throws OAException {
		List<Properties> propertiesList = null;
		
		try {
			propertiesList = propertiesDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return propertiesList;
	}

	/**
	 * 查询所有配置表记录
	 * @return
	 * @throws OAException
	 */
	@Override
	public List<Properties> queryAllHolidayProperties() throws OAException {
		List<Properties> propertiesList = null;
		try {
			propertiesList = propertiesDao.queryAllHoliday();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return propertiesList;
	}

	/**
	 * 查询所有报销配置表记录
	 * @return
	 * @throws OAException 
	 */
	@Override
	public List<Properties> queryAllBaoXiaoProperties() throws OAException {
		List<Properties> propertiesList = null;
		try {
			propertiesList = propertiesDao.queryAllBaoXiao();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return propertiesList;
	}

}
