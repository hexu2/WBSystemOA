package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.WorkFlowNode;
import com.njwangbo.oa.entity.WorkNodeAction;


/**
 * 工作流数据操作接口层
 * @author soft01
 *
 */
public interface WorkFlowDao {
	/**
	 * 添加工作流记录
	 * @param actionPojo
	 * @throws SQLException
	 */
	void addAction(WorkNodeAction actionPojo) throws SQLException;
	
	/**
	 * 查询当前
	 * @param tableID
	 * @param workID
	 * @return
	 * @throws SQLException
	 */
	WorkNodeAction queryCurrentAction(String tableID,int workID) throws SQLException;
	
	/**
	 * 修改当前工作流action
	 * @param workNodeAction
	 * @throws SQLException
	 */
	void updateCurrentAction(WorkNodeAction workNodeAction) throws SQLException;
	
	/**
	 * 根据id查询工作流记录
	 * @param nodeID
	 * @return
	 * @throws SQLException
	 */
	WorkFlowNode queryNodeByNodeID(int nodeID) throws SQLException;
	
	/**
	 * 查询历史审批记录根据t_tabele_id 例如：QJ1003  t_work_id 例如：1，2，3。。。。
	 * @param workId
	 * @param tableId
	 * @return
	 * @throws SQLException
	 */
	List<WorkNodeAction> queryHistoryByTableIDWorkID(String workId,
			String tableId) throws SQLException;
	
	/**
	 * 查询处理人名字
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	List<String> queryEmpNameByIDs(String ids) throws SQLException;

	/**
	 * 查询工作流名
	 * @param workId
	 * @throws SQLException
	 */
	String queryByworkId(int workId)throws SQLException;

}
