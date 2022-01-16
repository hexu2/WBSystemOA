package com.njwangbo.oa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.MenuService;
import com.njwangbo.oa.service.PermissionService;
import com.njwangbo.oa.service.RoleService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.PageModel;

/**
 * 权限相关请求处理
 * 
 * @author Administrator
 * 
 */
public class PermissionAction {
	private PermissionService permissionService = (PermissionService) ApplicationContext
			.getBean("permissionService");
	private MenuService menuService = (MenuService) ApplicationContext
			.getBean("menuService");
	private RoleService roleService = (RoleService) ApplicationContext
			.getBean("roleService");
	private int pageSize = 5;
	/**
	 * 查询所有权限记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryPermissions(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));
		

		PageModel<Permission> pageModel = null;
		List<Menu> menuList = null;
		List<Role> roleList = null;
		try {
			pageModel = permissionService.queryByPageModel(pageNo, pageSize);
			menuList = menuService.queryAllMenu();
			roleList = roleService.queryAllRoles();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("pageModel", pageModel);
		request.setAttribute("roleList", roleList);
		request.setAttribute("menuList", menuList);
		request.setAttribute("flag", 2);
		return "success";
	}

	/**
	 * 模糊查询所有权限
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryPermissionsByBtn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleNameFromInput = request.getParameter("roleNameFromInput");// 角色id
		String menuNameFromInput = request.getParameter("menuNameFromInput");// 菜单id
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));
		PageModel<Permission> pageModel = null;
		List<Menu> menuList = null;
		List<Role> roleList = null;
		try {
			pageModel = permissionService.queryByKeys(roleNameFromInput,
					menuNameFromInput, pageNo, pageSize);
			menuList = menuService.queryAllMenu();
			roleList = roleService.queryAllRoles();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("pageModel", pageModel);
		request.setAttribute("roleList", roleList);
		request.setAttribute("menuList", menuList);
		request.setAttribute("roleNameFromInput", roleNameFromInput);
		request.setAttribute("menuNameFromInput", menuNameFromInput);

		request.setAttribute("flag", 1);

		return "success";
	}

	/**
	 * 添加权限查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryPermissionAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Role> roleList = null;
		List<Menu> menuList = null;
		try {
			roleList = roleService.queryAllRoles();
			menuList = menuService.queryAllMenu();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("roleList", roleList);
		request.setAttribute("menuList", menuList);

		return "success";

	}

	/**
	 * 权限添加
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String permissionAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleId = request.getParameter("roleId");
		String menuId = request.getParameter("menuId");
		try {
			permissionService.addPermission(Integer.valueOf(roleId), Integer
					.valueOf(menuId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}

		request.setAttribute("operator", Constant.PERMISSION_ADD);
		return "success";
	}

	/**
	 * 权限删除
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String permissionDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String permissionId = request.getParameter("permissionId");

		try {
			permissionService.deltePermission(Integer.valueOf(permissionId));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}

		request.setAttribute("operator", Constant.PERMISSION_DEL);
		return "success";
	}
}
