package com.njwangbo.oa.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.RoleService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.PageModel;

/**
 * 处理角色相关请求
 * 
 * @author Administrator
 * 
 */
public class RoleAction {
	private RoleService roleService = (RoleService) ApplicationContext
			.getBean("roleService");
	private int pageSize = 5;
	/**
	 * 查询所有角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));
		PageModel<Role> pageModel = null;
		try {
			pageModel = roleService.queryByPageModel(pageNo, pageSize);
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("pageModel", pageModel);
		return "success";
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String roleDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleId");
		try {
			roleService.deleteByRoleId(Integer.valueOf(roleId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.ROLE_DEL);
		return "success";
	}

	public String roleAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleName = request.getParameter("roleName");
		Role role = new Role();
		role.setRoleName(roleName);
		try {
			roleService.addRole(role);
		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}

		request.setAttribute("operator", Constant.ROLE_ADD);
		return "success";
	}

	/**
	 * 角色修改查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryRoleUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleId");
		Role role = null;
		try {
			role = roleService.queryById(Integer.valueOf(roleId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("role", role);
		return "success";
	}

	/**
	 * 角色修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String roleUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		Role role = new Role();
		role.setRoleId(Integer.valueOf(roleId));
		role.setRoleName(roleName);
		try {
			roleService.updateRole(role);

		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.ROLE_EDIT);
		return "success";
	}

}
