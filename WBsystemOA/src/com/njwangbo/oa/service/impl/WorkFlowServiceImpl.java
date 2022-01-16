package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.njwangbo.oa.dao.WorkFlowDao;
import com.njwangbo.oa.entity.WorkFlowNode;
import com.njwangbo.oa.entity.WorkNodeAction;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.WorkFlowService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.StringUtil;

/**
 * 工作流业务逻辑接口实现类
 * 
 * @author soft01
 * 
 */
public class WorkFlowServiceImpl implements WorkFlowService {

	private WorkFlowDao workFlowDao;
	private Transaction tx;

	public void setWorkFlowDao(WorkFlowDao workFlowDao) {
		this.workFlowDao = workFlowDao;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	/**
	 * 添加一条工作流记录
	 * 
	 * @param nodeId
	 *            节点id
	 * @param tableID
	 *            例如：QJ0001
	 * @throws OAException
	 */
	@Override
	public void addSatrtNode(int nodeId, String tableId) throws OAException {
		// 基础数据校验证
		if (StringUtil.isEmpty(String.valueOf(nodeId))) {
			throw new OAException("nodeId不能为空");
		} else if (StringUtil.isEmpty(tableId)) {
			throw new OAException("tableId不能为空");
		}
		WorkNodeAction workNodeAction = new WorkNodeAction();
		workNodeAction.setNodeID(nodeId);
		workNodeAction.setTableID(tableId);
		workNodeAction.setOpenTime(new Date());
		workNodeAction.setCreateTime(new Date());
		workNodeAction.setStatus(1);
		try {
			tx.begin();
			workFlowDao.addAction(workNodeAction);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库错误");
		}

	}

	/**
	 * workId 查看当前节点
	 */
	@Override
	public WorkFlowNode queryCurrentNode(String tableId, int workId)
			throws OAException {
		WorkNodeAction currentAction = null;
		WorkFlowNode currentNode = null;
		try {
			currentAction = workFlowDao.queryCurrentAction(tableId, workId);
			if (null != currentAction) {
				currentNode = workFlowDao.queryNodeByNodeID(currentAction
						.getNodeID());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentNode;
	}

	/**
	 * 查看历史处理记录
	 */
	@Override
	public List<WorkNodeAction> queryHistoryByTableIdAndWorkId(String tableId,
			String workId) throws OAException {
		try {
			System.out.println("tableId" + tableId);
			System.out.println("workId" + workId);
			List<WorkNodeAction> list = workFlowDao
					.queryHistoryByTableIDWorkID(workId, tableId);
			System.out.println("list.size():" + list.size());
			for (WorkNodeAction action : list) {
				action.setStatusName(action.getStatus() == 1 ? "打开" : "关闭");
				WorkFlowNode node = workFlowDao.queryNodeByNodeID(action
						.getNodeID());
				action.setNodeName(node.getNodeName());
				// 待处理人转换
				String waitingUser = action.getWaitingUsers();
				action.setWaitingUsersName(getNamesString(waitingUser));

				// 已处理人转换
				action.setDealUserName(getNamesString(action.getDealUser()));

			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<WorkNodeAction>();
	}

	/**
	 * 根据用户的ID查询出用户的员工姓名，用于审批历史记录的待处理人，已处理人页面显示
	 * 
	 * @param userIDs
	 *            格式：1,3,34
	 * @return
	 * @throws SQLException
	 */
	private String getNamesString(String userIDs) throws SQLException {
		String names = "";
		if (!StringUtil.isEmpty(userIDs)) {
			try {
				List<String> nameList = workFlowDao.queryEmpNameByIDs(userIDs);
				for (String string : nameList) {
					names += string + ",";
				}
				names = names.substring(0, names.length() - 1);
			} catch (Exception e) {
				System.out.println("查询用户的员工姓名出错：userID:" + userIDs);
				e.printStackTrace();
				names = "";
			}
		}
		return names;
	}

	/**
	 * 关闭当前节点打开下一个节点
	 */
	@Override
	public void nextNode(String tableId, int workId, String dealUser,
			String dealAdvices, String dealType) throws OAException {
		// 审核操作：1、关闭当前环节。2、打开下一个环节
		// 关闭当前数据的当前节点
		// 1.查询出当前工作流节点对象
		try {
			WorkNodeAction currentAction = workFlowDao.queryCurrentAction(
					tableId, workId);
			System.out.println("currentAction:" + currentAction);
			// 关闭：update原有数据（close_time,deal_user,deal_advice）
			currentAction.setCloseTime(new Date());
			currentAction.setCreateTime(currentAction.getCloseTime());
			currentAction.setDealUser(dealUser);
			currentAction.setDealAdvices(dealAdvices);
			currentAction.setStatus(2);
			// 打开下一个节点，insert新数据（table_id,node_id,open_time,waiting_user，Status）
			WorkNodeAction nextAction = new WorkNodeAction();
			nextAction.setTableID(tableId);
			// 1.查询出当前节点的节点配置对象node，根据处理类型获取下一个节点ID
			WorkFlowNode currentNode = workFlowDao
					.queryNodeByNodeID(currentAction.getNodeID());
			System.out.println("currentNode:" + currentNode);

			int nextNodeId = currentNode.getNextNodeID(dealType);
			nextAction.setNodeID(nextNodeId);
			nextAction.setOpenTime(new Date());
			currentAction.setCreateTime(nextAction.getOpenTime());
			// 查出下一个节点配置对象，获取待处理人
			WorkFlowNode nextNode = workFlowDao.queryNodeByNodeID(nextNodeId);
			nextAction.setWaitingUsers(nextNode.getWaitingUser());
			nextAction.setStatus(1);
			// 如果下一个节点==结束节点，那么这个节点需要关闭
			if (-1 == nextNode.getNextNodeID("pass")) {
				nextAction.setCloseTime(new Date());
				nextAction.setDealUser(dealUser);
				nextAction.setDealAdvices("工作流结束");
				nextAction.setCreateTime(nextAction.getCloseTime());
				nextAction.setStatus(2);
			}

			tx.begin();
			// 关闭当前节点
			workFlowDao.updateCurrentAction(currentAction);
			// 添加下一个节点数据
			workFlowDao.addAction(nextAction);
			tx.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库错误");
		}

	}

	/**
	 * 根据workId查询工作流名称
	 */
	@Override
	public String queryByworkId(int workId) throws OAException {
		// 基础数据校验证
		if (StringUtil.isEmpty(String.valueOf(workId))) {
			throw new OAException("workId不能为空");
		}
		String workFlowName = null;
		try {
			workFlowName = workFlowDao.queryByworkId(workId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库错误");
		}

		return workFlowName;
	}

}
