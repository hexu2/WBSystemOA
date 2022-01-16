package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.Status;
import com.njwangbo.oa.exception.OAException;

/**
 * 提交状态业务逻辑接口层
 * @author Administrator
 *
 */
public interface StatusService {
	
	List<Status> queryAllStatus()throws OAException;

}
