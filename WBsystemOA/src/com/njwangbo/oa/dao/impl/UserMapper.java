package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.util.RowMapper;

public class UserMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("t_id"));
		user.setUserName(rs.getString("t_username"));
		user.setPassWord(rs.getString("t_pwd"));
		user.setEmployeeNo(rs.getString("t_emp_no"));
		user.setEmployeeName(rs.getString("t_emp_name"));
		user.setUserStatus(rs.getInt("t_user_status"));
		user.setUserRole(rs.getString("t_role"));
		user.setCreateTime(rs.getDate("t_createtime"));
		return user;
	}

}
