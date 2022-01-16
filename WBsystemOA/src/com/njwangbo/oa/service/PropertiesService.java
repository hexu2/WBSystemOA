package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.exception.OAException;

/**
 * 配置表业务逻辑接口
 * @author soft01
 *
 */
public interface PropertiesService {
	
	/**
	 * 查询所有配置表记录
	 * @return
	 * @throws OAException
	 */
	List<Properties> queryAllProperties() throws OAException;
	
	/**
	 * 查询所有请假配置表记录
	 * @return
	 * @throws OAException
	 */
	List<Properties> queryAllHolidayProperties() throws OAException;

	/**
	 * 查询所有报销配置表记录
	 * @return
	 */
	List<Properties> queryAllBaoXiaoProperties() throws OAException;

}
