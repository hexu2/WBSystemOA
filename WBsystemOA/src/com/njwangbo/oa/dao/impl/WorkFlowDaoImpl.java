package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.WorkFlowDao;
import com.njwangbo.oa.entity.WorkFlow;
import com.njwangbo.oa.entity.WorkFlowNode;
import com.njwangbo.oa.entity.WorkNodeAction;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.RowMapper;

/**
 * 工作流数据操作接口层实现类
 * 
 * @author soft01
 * 
 */
public class WorkFlowDaoImpl implements WorkFlowDao {

	/**
	 * 添加工作流记录
	 */
	@Override
	public void addAction(WorkNodeAction actionPojo) throws SQLException {
		// 添加新的工作流记录：table_id,node_id,open_time,waiting_user
		String sql = "insert into t_work_node_action"
				+ "(t_node_id,t_table_id,t_open_time,t_status,t_waiting_user,t_create_time,t_close_time,t_deal_user,t_deal_advices) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		JdbcTemplate.executeUpdate(sql, actionPojo.getNodeID(), actionPojo
				.getTableID(), actionPojo.getOpenTime(),
				actionPojo.getStatus(), actionPojo.getWaitingUsers(),
				actionPojo.getCreateTime(), actionPojo.getCloseTime(),
				actionPojo.getDealUser(), actionPojo.getDealAdvices());
	}

	/**
	 * 查询当前
	 */
	@SuppressWarnings("unchecked")
	@Override
	public WorkNodeAction queryCurrentAction(String tableID, int workID)
			throws SQLException {
		// 查询当前的工作流节点
		/*
		 * 根据ID降序排序，取第一条数据 select * from t_work_node_action where
		 * t_table_id='QJ1003' and t_node_id in( select t_node_id from
		 * t_work_node_config where t_work_id=1) order by id desc limit 0,1;
		 */
		WorkNodeAction action = null;
		String sql = "select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time "
				+ "from t_work_node_action "
				+ "where t_table_id=? and t_node_id in "
				+ "(select t_node_id from t_work_node_config where t_work_id=?) "
				+ "order by id desc limit 0,1";
		List<WorkNodeAction> list = JdbcTemplate.executeQuery(sql,
				new WorkFlowActionMapper(), tableID, workID);
		if(list.size()>0){
			action = list.get(0);
		}
		return action;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryEmpNameByIDs(String ids) throws SQLException {
		String sql = "select e.t_emp_name from t_user u ,t_employee e where u.t_emp_no=e.t_emp_no and u.t_id in ("
				+ ids + ")";
		List<String> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return rs.getString("t_emp_name");
			}
		}, new Object[] {});
		return list;
	}

	/**
	 * 查询历史审批记录 根据t_tabele_id 例如：QJ1003 t_work_id 例如：1，2，3。。。。
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WorkNodeAction> queryHistoryByTableIDWorkID(String workId,
			String tableId) throws SQLException {
		String sql = "select id,t_node_id,t_table_id,t_open_time,t_close_time,t_status,t_waiting_user,t_deal_user,t_deal_advices,t_create_time "
				+ "from t_work_node_action where t_table_id=? and t_node_id in (select t_node_id from t_work_node_config where t_work_id=?)";
		List<WorkNodeAction> list = JdbcTemplate.executeQuery(sql,
				new WorkFlowActionMapper(), tableId, workId);
		return list;
	}

	/**
	 * 根据nodeID查询工作节点
	 */
	@SuppressWarnings("unchecked")
	@Override
	public WorkFlowNode queryNodeByNodeID(int nodeID) throws SQLException {
		WorkFlowNode workFlowNode = null;
		String sql = "select id,t_work_id,t_node_id,t_node_name,t_next_node_oper,t_waiting_user,t_create_time "
				+ "from t_work_node_config where t_node_id=?";
		List<WorkFlowNode> list = JdbcTemplate.executeQuery(sql,
				new WorkFlowNodeMapper(), nodeID);
		if(list.size()>0){
			workFlowNode = list.get(0);
		}
		return workFlowNode;
	}

	/**
	 * 修改当前工作流action
	 */
	@Override
	public void updateCurrentAction(WorkNodeAction workNodeAction)
			throws SQLException {
		// 关闭当前节点：修改当前节点数据：close_time,deal_user,deal_advice
		String sql = "update t_work_node_action  "
				+ "set t_close_time=?,t_deal_user=?,t_deal_advices=?,t_status=?, t_create_time=? where id=?";
		JdbcTemplate.executeUpdate(sql, workNodeAction.getCloseTime(),
				workNodeAction.getDealUser(), workNodeAction.getDealAdvices(),
				workNodeAction.getStatus(), workNodeAction.getCloseTime(),
				workNodeAction.getId());

	}

	/**
	 * 根据workId查询工作流名
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String queryByworkId(int workId) throws SQLException {
		WorkFlow workFlow = null;
		String sql = "select id,t_work_name,t_table_name,t_create_time from t_work_flow where id =?";
		List<WorkFlow> list = JdbcTemplate.executeQuery(sql,new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				WorkFlow workFlow = new WorkFlow();
				workFlow.setId(rs.getInt("id"));
				workFlow.setWorkName(rs.getString("t_work_name"));
				workFlow.setTableName(rs.getString("t_table_name"));
				workFlow.setCreateTime(rs.getDate("t_create_time"));
				return workFlow;
			}
		},workId);
		if(list.size()>0){
			
			workFlow = list.get(0);
		}
		return workFlow.getWorkName();
	}

}
