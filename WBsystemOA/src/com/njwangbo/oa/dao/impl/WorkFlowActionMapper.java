package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.WorkNodeAction;
import com.njwangbo.oa.util.RowMapper;

public class WorkFlowActionMapper implements RowMapper {

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		WorkNodeAction action = new WorkNodeAction();
		action.setId(rs.getInt("id"));
		action.setTableID(rs.getString("t_table_id"));
		action.setNodeID(rs.getInt("t_node_id"));
		action.setStatus(rs.getInt("t_status"));
		action.setOpenTime(rs.getTimestamp("t_open_time"));
		action.setCloseTime(rs.getTimestamp("t_close_time"));
		action.setWaitingUsers(rs.getString("t_waiting_user"));
		action.setDealUser(rs.getString("t_deal_user"));
		action.setDealAdvices(rs.getString("t_deal_advices"));
		action.setCloseTime(rs.getTimestamp("t_create_time"));
		return action;
	}

}
