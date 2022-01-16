package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.WorkFlowNode;
import com.njwangbo.oa.entity.WorkNodeAction;
import com.njwangbo.oa.exception.OAException;

/**
 * 工作流业务逻辑层
 * @author soft01
 *
 */
public interface WorkFlowService {
	
	/**
	 * 添加一条工作流记录*****************已经测试
	 * @param nodeId 节点id
	 * @param tableID 例如：QJ0001
	 * @throws OAException
	 */
	void addSatrtNode(int nodeId,String tableId) throws OAException;
	
	
	/**
	 * 关闭当前节点打开下一个节点
	 * @param tableId
	 * @param workId
	 * @param dealUser
	 * @param dealAdvices
	 * @param dealType
	 * @throws OAException
	 */
	void nextNode(String tableId,int workId,String dealUser,String dealAdvices,String dealType) throws OAException;
	
	/**
	 * 察看历史处理记录
	 * @param tableId
	 * @param i
	 * @return
	 * @throws OAException
	 */
	List<WorkNodeAction> queryHistoryByTableIdAndWorkId(String workID,
			String tableID) throws OAException;

	/**
	 * 察看当前节点****************已经测试
	 * @param tableId
	 * @param workId
	 * @return
	 * @throws OAException
	 */
	WorkFlowNode queryCurrentNode(String tableId,int workId) throws OAException;
	
	/**
	 * 根据workId查询当前工作流名称
	 * @param workId
	 * @return
	 * @throws OAException
	 */
	String queryByworkId(int workId) throws OAException;
}
