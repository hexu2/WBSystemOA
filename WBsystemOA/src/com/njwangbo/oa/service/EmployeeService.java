package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 员工业务操作接口
 * @author soft01
 *
 */
public interface EmployeeService {

	/**
	 * 查询所有员工信息
	 * @return List<Employee> 所有员工记录的集合
	 * @throws OAException
	 */
	List<Employee> queryAll() throws OAException;
	

	/**
	 * 添加员工
	 * @param employee 一条员工记录
	 * @throws OAException
	 */
	void addEmp(Employee employee) throws OAException;

	/**
	 * 根据员工号删除一个员工记录
	 * @param empNo 员工号
	 * @throws OAException
	 */
	void delteEmp(String empNo) throws OAException;
	
	/**
	 * 修改一条员工记录
	 * @param employee 一条员工记录
	 * @throws OAException
	 */
	void updateEmp(Employee employee) throws OAException;

	/**
	 * 根据员工号查询一个员工记录
	 * @param empNo 员工号
	 * @return Employee 一条员工记录
	 * @throws OAException
	 */
	Employee selectEmp(String empNo) throws OAException;
	
	/**
	 * 查询员工总数
	 * @return int 员工总数
	 * @throws OAException
	 */
	int queryCnt() throws OAException;
	
	/**
	 * 按照模型分页查询数据
	 * @param pageNo 当前页
	 * @param pageSize 每页显示的记录条数
	 * @return  PageModel<Employee> 一个一页的分页模型集合
	 * @throws OAException
	 */
	PageModel<Employee> queryByPageModel(int pageNo,int pageSize)throws OAException;
	
	/**
	 * 模糊查询
	 * @param empName 员工名
	 * @param deptName 部门名
	 * @return 查询出来的员工集合
	 * @throws OAException
	 */
	List<Employee> QueryByEmpNameAndDeptName(String empName , String deptName) throws OAException;
	
	/**
	 * 模糊查询分页
	 * @param pageNo 当前页
	 * @param pageSize 没页显示的记录条数
	 * @param empName 员工名
	 * @param deptName 员工所属于的部门名
	 * @return
	 * @throws OAException
	 */
	PageModel<Employee> queryByPageModelForBtn(int pageNo,int pageSize,String empName , String deptName)throws OAException;
	
	/**
	 * 根据员工编号查询员工
	 * @param empNo
	 * @return Employee
	 * @throws OAException
	 */
	Employee queryByEmpNo(String empNo)throws OAException;

	/**
	 * 根据员工名模糊查询
	 * @param empName
	 * @return List<Employee> 根据员工名模糊查询后的员工集合
	 */
	List<Employee> QueryByEmpName(String empName)throws OAException;

	/**
	 * 查询部门号为deptNo的员工数量
	 * @param deptNo
	 * @return
	 * @throws OAException
	 */
	int queryByDeptNO(String deptNo)throws OAException;
}
