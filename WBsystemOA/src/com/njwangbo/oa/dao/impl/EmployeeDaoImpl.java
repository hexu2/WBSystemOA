package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.EmployeeDao;
import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;
/**
 * 
 * @author soft01
 *
 */
public class EmployeeDaoImpl implements EmployeeDao{

	
	/**
	 * 查询所有员工信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> queryAll() throws SQLException {
		List<Employee> employeeList = null;
		String sql = "select e2.t_id, e2.t_emp_no,e2.t_emp_name,e1.t_deptname as t_emp_dept ,e3.t_sex as t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3 where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id";
		employeeList = JdbcTemplate.executeQuery(sql, new EmployeeMapper(), new Object[]{});
		return employeeList;
	}

	
	/**
	 * 添加员工
	 */
	@Override
	public void add(Employee employee) throws SQLException {
		String sql = "insert into t_employee(t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time) values(?,?,?,?,?,?,?,?,now())";
		JdbcTemplate.executeUpdate(sql, employee.getEmpNo(),employee.getEmpName(),employee.getEmpDeptNo(),employee.getSex(),employee.getEducation(),employee.getEmail(),employee.getPhone(),employee.getEntryTime());
	}

	
	/**
	 * 根据员工编号删除员工
	 */
	@Override
	public void deleteById(String empNo) throws SQLException {
		String sql = "delete from t_employee where t_emp_no = ?";
		JdbcTemplate.executeUpdate(sql, empNo);
		
	}

	
	/**
	 * 根据员工编号查询员工
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Employee queryById(String empNo) throws SQLException {
		Employee employee = null;
		String sql = "select  e2.t_id, e2.t_emp_no,e2.t_emp_name,e1.t_deptname as t_emp_dept,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3 where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id and e2.t_emp_no = ?";
		List<Employee> employeeList = JdbcTemplate.executeQuery(sql, new EmployeeMapper(), empNo);
		if(employeeList.size() > 0){
			employee = employeeList.get(0);
		}
		return employee;
	}
	
	/**
	 * 查询员工总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt() throws SQLException {
		String sql = "select count(1) as cnt from t_employee";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
			return Integer.valueOf(rs.getInt("cnt"));
			}
		}, new Object[]{});
		return list.get(0);
	}

	/**
	 * 查询分页后的员工数量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCntAfterPage(String empName, String deptName)
			throws SQLException {
		String empName1 = "%" + empName + "%";
		String deptName1 = "%" + deptName + "%";
		
		String sql = "select count(1) as cnt " +
				"from  t_dept e1,t_employee e2,t_empSex e3  " +
				"where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id and e2.t_emp_name like ? and e1.t_deptname like ? ";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, empName1, deptName1);
		
		return list.get(0);
	}
	
	/**
	 * 修改员工信息
	 */
	@Override
	public void update(Employee employee) throws SQLException {
		String sql = "update t_employee set t_emp_name=?,t_emp_dept=?,t_sex=?,t_education=?,t_email=?,t_phone=?,t_entry_time=?,t_create_time=now() where t_emp_no = ?";
		JdbcTemplate.executeUpdate(sql, employee.getEmpName(),employee.getEmpDeptNo(),employee.getSex(),employee.getEducation(),employee.getEmail(),employee.getPhone(),employee.getEntryTime(),employee.getEmpNo());
		
		
	}

	/**
	 * 根据员工名和部门名模糊查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> queryByKeys(String empName, String deptName) throws SQLException {
		//拼接like后的内容
		String empName1 = "%" + empName + "%";
		String deptName1 = "%" + deptName + "%";
		String sql = "select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname as t_emp_dept,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3 where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id and e2.t_emp_name like ?  and e1.t_deptname like ? ";
		List<Employee> employeeList = null;
		employeeList = JdbcTemplate.executeQuery(sql,new EmployeeMapper(), empName1,deptName1);
		return employeeList;
	}


	/**
	 * 按照分页模型查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Employee> queryByPageModel(PageModel<Employee> pageModel)
			throws SQLException {
		List<Employee> employeeList = null;
		String sql = "select  e2.t_id,  e2.t_emp_no,e2.t_emp_name,e1.t_deptname as t_emp_dept,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3 where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id limit ?,?";
		employeeList = JdbcTemplate.executeQuery(sql, new EmployeeMapper(), (pageModel.getPageNo()-1)*pageModel.getPageSize(),pageModel.getPageSize());
		
		pageModel.setDataList(employeeList);
		
		return pageModel;
	}


	/**
	 * 按照分页模糊查询员工
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Employee> queryByPageModelForBtn(String empName,
			String deptName, PageModel<Employee> pageModel) throws SQLException {
		//拼接like后的内容
		String empName1 = "%" + empName + "%";
		String deptName1 = "%" + deptName + "%";
		
		List<Employee> employeeList = null;
		String sql = "select e2.t_id,e2.t_emp_no,e2.t_emp_name,e1.t_deptname as t_emp_dept,e3.t_sex,e2.t_education,e2.t_email,e2.t_phone,e2.t_entry_time,e2.t_create_time from t_dept e1,t_employee e2,t_empSex e3 where e1.t_deptno = e2.t_emp_dept and e2.t_sex = e3.t_id and e2.t_emp_name like ?  and e1.t_deptname like ?  limit ?,?;";
		employeeList = JdbcTemplate.executeQuery(sql, new EmployeeMapper(), empName1,deptName1,(pageModel.getPageNo()-1)*pageModel.getPageSize(),pageModel.getPageSize());
		
		pageModel.setDataList(employeeList);
		
		return pageModel;
	}

	/**
	 * 根据员工名模糊查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> queryByEmpName(String empName) throws SQLException {
		String sql = "select t_id,t_emp_no,t_emp_name,t_emp_dept,t_sex,t_education,t_email,t_phone,t_entry_time,t_create_time from t_employee " +
				"where t_emp_name like ? limit 0,10";
		
		List<Employee> employeeList = JdbcTemplate.executeQuery(sql, new EmployeeMapper(), "%" + empName + "%");
		
		return employeeList;
	}

	/**
	 * 查询部门号为deptNo的员工数量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryByDeptNO(String deptNo) throws SQLException {
		String sql = " select count(1) as cnt from  t_employee where t_emp_dept = ?";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, deptNo);

		return list.get(0);
	}
	


}
