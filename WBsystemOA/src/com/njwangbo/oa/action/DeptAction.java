package com.njwangbo.oa.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.DeptService;
import com.njwangbo.oa.service.EmployeeService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.PageModel;

/**
 * 处理部门相关操作的请求
 * 
 * @author Administrator
 * 
 */
public class DeptAction {
	private DeptService deptService = (DeptService) ApplicationContext
			.getBean("deptService");
	private EmployeeService employeeService = (EmployeeService) ApplicationContext.getBean("employeeService");
	private int pageSize = 5;
	/**
	 * 查询所有部门信息
	 * 
	 * @return
	 */
	public String queryAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));
		PageModel<Dept> pageModel = null;
		try {
			pageModel = deptService.queryByPageModel(pageNo, pageSize);
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageModel", pageModel);
		return "success";
	}

	/**
	 * 检查部门编号是否存在
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String checkDeptNo(HttpServletRequest request, HttpServletResponse response)throws Exception {
		String deptNo = request.getParameter("deptNo");
		Dept dept = deptService.queryById(deptNo);
		String result = "";
		if(null== dept){
			result = "0";
		}else{
			result = "1";
		}
		if(deptNo.length() != 5){
			result = "2";
		}
		response.getWriter().write(result);
		return "success";
	}
	
	/**
	 * 部门添加
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String addDept(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	  	String deptNo = request.getParameter("deptNo");
	  	String deptName = request.getParameter("deptName");
	  	String deptLoc = request.getParameter("deptLoc");
	  	String deptManager = request.getParameter("deptManager");
	  	
	  	DeptService deptService = (DeptService)ApplicationContext.getBean("deptService");
	  	
	  	Dept dept = new Dept();
	  	dept.setDeptNo(deptNo);
	  	dept.setDeptName(deptName);
	  	dept.setDeptLoc(deptLoc);
	  	dept.setDeptManager(deptManager);
	  	
		try{
		  	deptService.addDept(dept);
		}catch(OAException e){
	        request.setAttribute("errorMsg",e.getMessage());
	        return "error";
		}
	    request.setAttribute("operator",Constant.DEPT_ADD);
	    return "success";
	}
	
	/**
	 * 部门删除
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String delDept(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String deptNo = request.getParameter("deptNo");
		int numb = 0;
  		try{
			numb = employeeService.queryByDeptNO(deptNo);
			if(0 == numb){//该部门号未绑定员工
				deptService.delteDept(deptNo);
			}else{
				request.setAttribute("errorMsg", "该部门存在绑定员工不可删除");
				return "error";
			}
  		}catch(OAException e){
  			request.setAttribute("errorMsg",e.getMessage());
  			return "error";
  		}
		request.setAttribute("operator",Constant.DEPT_DEL);
		 return "success";
	}
	
	/**
	 * 部门修改查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryDeptUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	  	String deptNo = request.getParameter("deptNo");
     	Dept dept = null;
		try {
			dept = deptService.selectDept(deptNo);
		} catch (OAException e) {
			e.printStackTrace();
		}
     	request.setAttribute("dept",dept);
     	return "success";
	}
	/**
	 * 部门修改
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String deptUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	  	String deptNo = request.getParameter("deptNo");
	  	String deptName = request.getParameter("deptName");
	  	String deptLoc = request.getParameter("deptLoc");
	  	String deptManager = request.getParameter("deptManager");
	  	Dept dept = new Dept();
	  	dept.setDeptNo(deptNo);
	  	dept.setDeptName(deptName);
	  	dept.setDeptLoc(deptLoc);
	  	dept.setDeptManager(deptManager);
		try{
		  	deptService.updateDept(dept);
		}catch(OAException e){
	        request.setAttribute("errorMsg",e.getMessage());
	        return "error";
		}
	    request.setAttribute("operator",Constant.DEPT_EDIT);
	    return "success";
	}
	
	/**
	 * 查询部门明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryDeptDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
  		String deptNo = request.getParameter("deptNo");
  		Dept dept = null;
		try {
			dept = deptService.selectDept(deptNo);
		} catch (OAException e) {
			e.printStackTrace();
		}
  		request.setAttribute("dept",dept);
  		return "success";
	}
}
