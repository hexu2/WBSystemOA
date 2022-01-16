package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.StatusDao;
import com.njwangbo.oa.entity.Status;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.StatusService;

public class StatusServiceImpl implements StatusService{

	private StatusDao statusDao;
	
	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}
	@Override
	public List<Status> queryAllStatus() throws OAException {
		List<Status> statusList = null;
		try {
			statusList = statusDao.queryAllStatus();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return statusList;
	}

}
