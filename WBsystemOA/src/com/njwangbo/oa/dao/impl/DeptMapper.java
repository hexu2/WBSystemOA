package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.util.RowMapper;

public class DeptMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		Dept dept = new Dept();
		dept.setDeptNo(rs.getString("t_deptno"));
		dept.setDeptName(rs.getString("t_deptname"));
		dept.setDeptLoc(rs.getString("t_deptloc"));
		dept.setDeptManager(rs.getString("t_deptmanager"));
		dept.setCreateTime(rs.getDate("t_createtime"));
		return dept;
	}

}
