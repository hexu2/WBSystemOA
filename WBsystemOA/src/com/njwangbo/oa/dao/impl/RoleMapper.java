package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.util.RowMapper;

public class RoleMapper implements RowMapper {

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {

		Role role = new Role();
		role.setRoleId(rs.getInt("t_id"));
		role.setRoleName(rs.getString("t_role_name"));
		role.setCreateTime(rs.getDate("t_create_time"));
		return role;
	}

}
