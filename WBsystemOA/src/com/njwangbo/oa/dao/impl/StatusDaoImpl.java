package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.StatusDao;
import com.njwangbo.oa.entity.Status;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.RowMapper;

public class StatusDaoImpl implements StatusDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> queryAllStatus() throws SQLException {
		List<Status> statusList = null;
		String sql = "select t_id ,t_value from t_status";
		statusList = JdbcTemplate.executeQuery(sql, new RowMapper() {
			
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				Status status = new Status();
				status.setId(rs.getInt("t_id"));
				status.setValue(rs.getString("t_value"));
				return status;
			}
		},  new Object[]{});
		return statusList;
	}

}
