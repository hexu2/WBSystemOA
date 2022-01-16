package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.util.RowMapper;


public class EmployeeMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("t_id"));
		employee.setEmpNo(rs.getString("t_emp_no"));
		employee.setEmpName(rs.getString("t_emp_name"));
		employee.setEmpDeptNo(rs.getString("t_emp_dept"));
		employee.setSex(rs.getString("t_sex"));
		employee.setEducation(rs.getString("t_education"));
		employee.setEmail(rs.getString("t_email"));
		employee.setPhone(rs.getString("t_phone"));
		employee.setEntryTime(rs.getDate("t_entry_time"));
		employee.setCreateTime(rs.getDate("t_create_time"));

		return employee;
	}

}
