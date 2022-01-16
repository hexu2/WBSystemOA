package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.util.RowMapper;

public class PermissionMapper implements RowMapper {
	

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		Permission permission = new Permission();
		permission.setId(rs.getInt("t_id"));
		permission.setRoleId(rs.getInt("t_role_id"));
		permission.setRoleName(rs.getString("t_role_name"));
		permission.setMenuId(rs.getInt("t_menu_id"));
		permission.setMenuName(rs.getString("t_menu_name"));
		permission.setCreateTime(rs.getDate("t_create_time"));
		return permission;
	}

}
