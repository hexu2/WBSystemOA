package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.util.PageModel;

public interface EmployeeDao {
	/**
	 * 查询所有员工
	 * @return 员工集合
	 * @throws SQLException
	 */
	List<Employee> queryAll() throws SQLException;


	/**
	 * 根据员工号查询员工
	 * @param empNo 员工编号
	 * @return  Employee 一条员工记录
	 * @throws SQLException
	 */
	Employee queryById(String empNo) throws SQLException;

	/**
	 * 添加新的员工
	 * @param employee 一条员工记录
	 * @throws SQLException
	 */
	void add(Employee employee) throws SQLException;

	
	/**
	 * 根据员工编号删除员工
	 * @param empNo empNo
	 * @throws SQLException
	 */
	void deleteById(String empNo) throws SQLException;

	/**
	 * 修改员工信息 
	 * @param employee 一个员工信息
	 * @throws SQLException
	 */
	void update(Employee employee)throws SQLException;
	
	/**
	 * 查询员工数量
	 * @return
	 * @throws SQLException
	 */
	int queryCnt()throws SQLException;
	
	/**
	 * 模糊查询分页后的员工数量
	 * @return
	 * @throws SQLException
	 */
	int queryCntAfterPage(String empName , String deptName) throws SQLException;

	/**
	 * 按照分页模型查询
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	PageModel<Employee>  queryByPageModel(PageModel<Employee> pageModel)throws SQLException;
	
	
	/**
	 * 根据关键字模糊查询
	 * @param empName 员工名
	 * @param deptName 部门名 
	 * @return
	 * @throws SQLException
	 */
	List<Employee> queryByKeys(String empName , String deptName ) throws SQLException;
	
	/**
	 * 按照分页模糊查询员工
	 * @param empName 员工名
	 * @param deptName 员工所属于部门名
	 * @param pageModel 分页模型
	 * @return PageModel<Employee> 分页模糊查询后的结果
	 * @throws SQLException
	 */
	PageModel<Employee> queryByPageModelForBtn(String empName , String deptName ,PageModel<Employee> pageModel) throws SQLException;

	/**
	 * 根据员工名模糊查询
	 * @param empName 员工名
	 * @return List<Employee>
	 * @throws SQLException
	 */
	List<Employee> queryByEmpName(String empName) throws SQLException;

	/**
	 * 查询部门号为deptNo的员工数量
	 * @param deptNo
	 * @return
	 * @throws SQLException
	 */
	int queryByDeptNO(String deptNo)throws SQLException;
	
}
