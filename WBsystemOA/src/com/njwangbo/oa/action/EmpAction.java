package com.njwangbo.oa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.DeptService;
import com.njwangbo.oa.service.EmployeeService;
import com.njwangbo.oa.service.UserService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.DateUtil;
import com.njwangbo.oa.util.PageModel;

/**
 * 处理员工相关操作的请求
 * 
 * @author soft01
 * 
 */
public class EmpAction {
	private EmployeeService employeeService = (EmployeeService) ApplicationContext
			.getBean("employeeService");
	private DeptService deptService = (DeptService) ApplicationContext
			.getBean("deptService");
	private UserService userService = (UserService) ApplicationContext
			.getBean("userService");
	private int pageSize = 5;

	/**
	 * 查询所有员工信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public String queryAllEmps(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));
		PageModel<Employee> pageModel = null;
		List<Dept> deptList = null;
		try {
			pageModel = employeeService.queryByPageModel(pageNo, pageSize);
			deptList = deptService.queryAll();
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("deptList", deptList);
		request.setAttribute("pageModel", pageModel);

		return "success";
	}

	/**
	 * 添加员工查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryEmpAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Dept> deptList = null;
		try {
			deptList = deptService.queryAll();
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("deptList", deptList);
		return "success";
	}

	/**
	 * 添加员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addEmp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("empNo");
		String empName = request.getParameter("empName");
		String empSex = request.getParameter("empSex");
		String empDeptNo = request.getParameter("empDeptNo");
		String entryTime = request.getParameter("entryTime");
		String education = request.getParameter("education");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		Employee employee = new Employee();
		employee.setEmpNo(empNo);
		employee.setEmpName(empName);
		employee.setEmpDeptNo(empDeptNo);
		employee.setSex(empSex);
		employee.setEducation(education);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setEntryTime(DateUtil.str2Date(entryTime, "yyyy-MM-dd"));

		try {
			employeeService.addEmp(employee);
		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.EMP_ADD);
		return "success";
	}

	/**
	 * 查询删除员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String deleteEmp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("empNo");
		int numb = 0;
		try {
			numb = userService.queryByEmpNO(empNo);
			if(0 == numb){//该员工号未绑定账户
				employeeService.delteEmp(empNo);
			}else{
				request.setAttribute("errorMsg", "该员工已经绑定账号不可删除");
				return "error";
			}
			
		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.EMP_DEL);
		return "success";
	}

	/**
	 * 查询修改员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryUpdateEmp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("empNo");

		List<Dept> deptList = null;
		Employee employee = null;
		try {
			deptList = deptService.queryAll();
			employee = employeeService.selectEmp(empNo);
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("deptList", deptList);
		request.setAttribute("employee", employee);
		return "success";
	}

	/**
	 * 修改员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String updateEmp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("empNo");
		String empName = request.getParameter("empName");
		String empSex = request.getParameter("empSex");
		String empDeptNo = request.getParameter("empDeptNo");
		String entryTime = request.getParameter("entryTime");
		String education = request.getParameter("education");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		Employee employee = new Employee();
		employee.setEmpNo(empNo);
		employee.setEmpName(empName);
		employee.setEmpDeptNo(empDeptNo);
		employee.setSex(empSex);
		employee.setEducation(education);
		employee.setEmail(email);
		employee.setPhone(phone);
		employee.setEntryTime(DateUtil.str2Date(entryTime, "yyyy-MM-dd"));

		try {
			employeeService.updateEmp(employee);

		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.EMP_EDIT);
		return "success";
	}

	/**
	 * 查询员工明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryEmpDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("empNo");
		Employee employee = null;
		try {
			employee = employeeService.selectEmp(empNo);
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("employee", employee);
		return "success";
	}

	/**
	 * 模糊查询所有员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryEmpsByBtn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empName = request.getParameter("empName");
		String deptName = request.getParameter("deptName");
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));

		PageModel<Employee> pageModel = null;

		List<Dept> deptList = null;
		try {
			pageModel = employeeService.queryByPageModelForBtn(pageNo,
					pageSize, empName, deptName);
			deptList = deptService.queryAll();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("pageModel", pageModel);
		request.setAttribute("deptList", deptList);

		request.setAttribute("empName", empName);
		request.setAttribute("deptName", deptName);
		request.setAttribute("flag", 1);

		return "success";
	}

	/**
	 * 根据姓名模糊查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryByEmpName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empName = request.getParameter("empName");
		List<Employee> employeeList = employeeService.QueryByEmpName(empName);
		for (Employee employee : employeeList) {
			employee.setEntryTime(null);
			employee.setCreateTime(null);
		}
		String employees = JSONArray.fromObject(employeeList).toString();
		// 写出数据
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(employees);
		return "success";
	}

	/**
	 * 检查员工编号是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String checkEmpNo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("employeeNo");
		Employee employee = null;
		employee = employeeService.queryByEmpNo(empNo);
		String result = "";
		if (null == employee) {
			result = "0";
		} else {
			result = "1";
		}

		if (empNo.length() != 5) {
			result = "2";
		}
		response.getWriter().write(result);
		return "success";
	}

	/**
	 * 根据员工号查询员工
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryByEmpNo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String empNo = request.getParameter("employeeNo");
		Employee employee = null;
		employee = employeeService.queryByEmpNo(empNo);
		employee.setEntryTime(null);
		employee.setCreateTime(null);
		String empStr = JSONObject.fromObject(employee).toString();

		System.out.println(empStr);
		// 写出数据
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(empStr);
		return "success";

	}
}
