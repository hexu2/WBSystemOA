package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.EmployeeDao;
import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.EmployeeService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.DateUtil;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;

public class EmployeeServiceImpl implements EmployeeService{
	

	private Transaction tx;
	private EmployeeDao employeeDao;
	
	
	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * 添加新员工
	 */
	@Override
	public void addEmp(Employee employee) throws OAException {
		//基础数据校验
		//1，员工号校验 非空长度为5
		System.out.println(employee.getEmpDeptNo().trim().length());
		if(StringUtil.isEmpty(employee.getEmpDeptNo())){
			throw new OAException("员工编号不能为空！");
		}else if(employee.getEmpDeptNo().trim().length() != 5){
			throw new OAException("员工编号长度必须为5!！");
		}
		//2，员工名校验非空
		if(StringUtil.isEmpty(employee.getEmpName())){
			throw new OAException("员工名不能为空！");
		}
		//3,员工所属部门号校验 不能为空 且长度为5
		if(StringUtil.isEmpty(employee.getEmpNo())){
			throw new OAException("员工所属部门号不能为空！");
		}else if (employee.getEmpNo().trim().length() !=5){
			throw new OAException("员工所属部门号长度必须为5！");
		}
		//4，性别校验 非空且只能是0或1
		if(StringUtil.isEmpty(String.valueOf(employee.getSex()))){
			throw new OAException("员工性别不能为空！");
		}else if(!String.valueOf(employee.getSex()).matches("^[01]$")){
			throw new OAException("员工性别只能是0或1 ，其中0：女，1：男！");
		}
		//5，手机号校验 不能为空
		if(StringUtil.isEmpty(employee.getPhone())){
			throw new OAException("员工手机号不能为空！");
		}
		//6，入职时间校验 非空 且必须是 xxxx-xx-xx 格式
		if(StringUtil.isEmpty(DateUtil.date2Str(employee.getEntryTime(), "yyyy-MM-dd"))){
			throw new OAException("员工入职时间不能为空！");
		}
		
		try {
			//从库中查询是否已经存在该员工号
			Employee employeeFromDB = employeeDao.queryById(employee.getEmpNo());
			if(null == employeeFromDB){//不存在可以添加
				tx.begin();
				employeeDao.add(employee);
				tx.commit();
			} else {
				throw new OAException("员工编号已经存在！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	
	
	}

	/**
	 * 根据员工号删除员工
	 */
	@Override
	public void delteEmp(String empNo) throws OAException {
		//基础数据校验
		//1，员工号校验 非空长度为5
		if(StringUtil.isEmpty(empNo)){
			throw new OAException("员工编号不能为空！");
		}else if(empNo.trim().length() != 5){
			throw new OAException("员工编号长度必须为5！");
		}
		
		try {
			tx.begin();
			employeeDao.deleteById(empNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
	}

	/**
	 * 查询所有员工
	 */
	@Override
	public List<Employee> queryAll() throws OAException {
		List<Employee> employeeList = null;
		try {
			tx.begin();
			employeeList = employeeDao.queryAll();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
			
		}
		return employeeList;
	}

	/**
	 * 查询员工总数
	 */
	@Override
	public int queryCnt() throws OAException {
		int cnt = 0;
		try {
			tx.begin();
			cnt = employeeDao.queryCnt();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		return cnt;
	}

	/**
	 * 根据部门编号查询一个员工
	 */
	@Override
	public Employee selectEmp(String empNo) throws OAException {
		//基础数据校验
		//1，员工号校验 非空长度为5
		if(StringUtil.isEmpty(empNo)){
			throw new OAException("员工编号不能为空！");
		}else if(empNo.trim().length() != 5){
			throw new OAException("员工编号长度必须为5！");
		}
		Employee employee = null;
		try {
			tx.begin();
			employee = employeeDao.queryById(empNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
		return employee;
	}

	/**
	 * 修改员工信息
	 */
	@Override
	public void updateEmp(Employee employee) throws OAException {
		//System.out.println(employee.getEmpDeptNo()+employee.getEmpNo());
		//基础数据校验
		//1，员工号校验 非空长度为5
		if(StringUtil.isEmpty(employee.getEmpDeptNo())){
			throw new OAException("员工部门编号不能为空！");
		}else if(employee.getEmpDeptNo().trim().length() != 5){
			throw new OAException("员工部门编号长度必须为5！");
		}
		//2，员工名校验非空
		if(StringUtil.isEmpty(employee.getEmpName())){
			throw new OAException("员工名不能为空！");
		}
		//3,员工所属部门号校验 不能为空 且长度为5
		if(StringUtil.isEmpty(employee.getEmpNo())){
			throw new OAException("员工所属部门号不能为空！");
		}else if (employee.getEmpNo().trim().length() !=5){
			throw new OAException("员工所属部门号长度必须为5！");
		}
		//4，性别校验 非空且只能是0或1
		//4，性别校验 非空且只能是0或1
		if(StringUtil.isEmpty(String.valueOf(employee.getSex()))){
			throw new OAException("员工性别不能为空！");
		}else if(!String.valueOf(employee.getSex()).matches("^[01]$")){
			throw new OAException("员工性别只能是0或1 ，其中0：女，1：男！");
		}
		//5，手机号校验 不能为空
		if(StringUtil.isEmpty(employee.getPhone())){
			throw new OAException("员工手机号不能为空！");
		}
		//6，入职时间校验 非空 且必须是 xxxx-xx-xx 格式
		if(StringUtil.isEmpty(DateUtil.date2Str(employee.getEntryTime(), "yyyy-MM-dd"))){
			throw new OAException("员工入职时间不能为空！");
		}
		
		try {
			tx.begin();
			employeeDao.update(employee);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}

	/**
	 * 按分页模型查询员工
	 */
	@Override
	public PageModel<Employee> queryByPageModel(int pageNo, int pageSize)
			throws OAException {
		PageModel<Employee> pageModel = new PageModel<Employee>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = employeeDao.queryByPageModel(pageModel);
			pageModel.setCnt(employeeDao.queryCnt());
			if(employeeDao.queryCnt() == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return pageModel;
	}

	/**
	 * 模糊查询
	 */
	@Override
	public List<Employee> QueryByEmpNameAndDeptName(String empName,
			String deptName) throws OAException {
		if(null == empName){
			empName = "";
		}
		if(null == deptName){
			deptName = "";
		}
		List<Employee> employeeList = null;
		try {
			employeeList = employeeDao.queryByKeys(empName, deptName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return employeeList;
	}


	/**
	 * 	模糊查询分页
	 */
	@Override
	public PageModel<Employee> queryByPageModelForBtn(int pageNo, int pageSize,
			String empName, String deptName) throws OAException {
		if(null == empName){
			empName = "";
		}
		if(null == deptName){
			deptName = "";
		}
		//构造一个pageModel
		PageModel<Employee> pageModel = new PageModel<Employee>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = employeeDao.queryByPageModelForBtn(empName, deptName, pageModel);
			pageModel.setCnt(employeeDao.queryCntAfterPage(empName, deptName));
			if(employeeDao.queryCntAfterPage(empName, deptName) == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		
		return pageModel;
	}

	/**
	 *  根据员工编号查询员工
	 */
	@Override
	public Employee queryByEmpNo(String empNo) throws OAException {
		if(StringUtil.isEmpty(empNo)){
			throw new OAException("员工编号不能为空！");
		}
		
		Employee employee = null;
		
		try {
			employee = employeeDao.queryById(empNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		
		return employee;
	}

	/**
	 * 根据员工名模糊查询
	 */
	@Override
	public List<Employee> QueryByEmpName(String empName) throws OAException {
		List<Employee> employeeList = null;
		
		try {
			employeeList = employeeDao.queryByEmpName(empName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return employeeList;
	}

	// 查询部门号为deptNo的员工数量
	@Override
	public int queryByDeptNO(String deptNo) throws OAException {
		if(StringUtil.isEmpty(deptNo)){
			throw new OAException("部门号不能为空");
		}
		
		int cnt = 0;
		try {
			cnt = employeeDao.queryByDeptNO(deptNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return cnt;
	}
	

}
