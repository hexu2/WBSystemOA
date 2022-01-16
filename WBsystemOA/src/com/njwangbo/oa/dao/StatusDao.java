package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Status;

/**
 * 提交状态数据操作接口
 * @author Administrator
 *
 */
public interface StatusDao {
	
	/**
	 * 查询所有提交状态
	 * @return
	 * @throws SQLException
	 */
	List<Status> queryAllStatus()throws SQLException;

}
