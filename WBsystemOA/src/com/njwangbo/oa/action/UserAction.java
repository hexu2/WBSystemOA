package com.njwangbo.oa.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.njwangbo.oa.entity.Employee;
import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.EmployeeService;
import com.njwangbo.oa.service.MenuService;
import com.njwangbo.oa.service.RoleService;
import com.njwangbo.oa.service.UserService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.MakeCertPic;
import com.njwangbo.oa.util.PageModel;

/**
 * 登陆处理的相关请求
 * 
 * @author soft01
 * 
 */
public class UserAction {

	private UserService userService = (UserService) ApplicationContext
			.getBean("userService");
	private RoleService roleService = (RoleService) ApplicationContext
			.getBean("roleService");
	private int pageSize = 5;
	private EmployeeService employeeService = (EmployeeService) ApplicationContext
			.getBean("employeeService");

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 1.设置编码
		// 2.获取用户名,密码,验证码
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("password");
		String code = request.getParameter("code");

		String result = "";// ajax最终的返回值
		// 比较客户端的验证码和服务器中验证码是否一致
		String serverCode = (String) request.getSession().getAttribute(
				"serverCode");
		// ********************************测试方便
		// !serverCode.equalsIgnoreCase(code) false
		if (!serverCode.equalsIgnoreCase(code)) {
			result = Constant.CODE_ERROR;
		} else {

			// 4.调用service
			User user = null;

			try {
				user = userService.userLogin(userName, passWord);
				if (null != user) {// 登录成功
					// 使用session保存用户对象
					// 移除session中存储的serCode
					request.getSession().removeAttribute("serverCode");
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("userName", userName);

					// 得到菜单的List并放入session中,用于在main.jsp动态创建菜单
					if (user.getUserStatus() == 1) {// 未注销
						MenuService menuService = (MenuService) ApplicationContext
								.getBean("menuService");
						List<Menu> menuList = new ArrayList<Menu>();
						menuList = menuService.queryMenuByRole(user
								.getUserRole());

						request.getSession().setAttribute("menuList", menuList);

						result = Constant.LOGIN_SUCCESS;
					} else {// 已经注销
						result = Constant.USER_STATUS_ERROR;
					}

				} else {// 登录失败
					result = Constant.USERNAME_PASSWORD_ERROR;
				}
			} catch (OAException e) {
				e.printStackTrace();
			}

		}
		// 将result使用流写出
		response.getWriter().write(result);
		// 统一返回
		return "login";
	}

	/**
	 * 获取验证码图片
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String getCertPic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 往客户端输出
		OutputStream os = response.getOutputStream();

		// 1.生成验证码
		String serverCode = MakeCertPic.getCertPic(60, 20, os);

		// 2.保存验证码对应的字符串
		request.getSession().setAttribute("serverCode", serverCode);
		return "success";
	}

	/**
	 * 查询所有用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 从前端获取当前页
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));
		PageModel<User> pageModel = null;
		List<Role> roleList = null;
		try {
			pageModel = userService.queryByPage(pageNo, pageSize);
			roleList = roleService.queryAllRoles();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("roleList", roleList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("user", user);
		request.setAttribute("flag", 2);
		return "success";
	}

	/**
	 * 模糊查询用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryUsersByBtn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String inputUserName = request.getParameter("inputUserName");
		String inputUserRole = request.getParameter("inputUserRole");
		String inputUserStatus = request.getParameter("inputUserStatus");
		// 从前端获取当前页
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));

		PageModel<User> pageModel = null;
		List<Role> roleList = null;
		try {
			pageModel = userService.queryByKeys(inputUserName, inputUserStatus,
					inputUserRole, pageNo, pageSize);
			roleList = roleService.queryAllRoles();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("roleList", roleList);
		request.setAttribute("pageModel", pageModel);

		request.setAttribute("inputUserName", inputUserName);
		request.setAttribute("inputUserRole", inputUserRole);
		request.setAttribute("inputUserStatus", inputUserStatus);

		request.setAttribute("flag", 1);
		return "success";
	}

	/**
	 * 添加用户查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryUserAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Role> roleList = null;
		List<Employee> employeeList = null;
		try {
			roleList = roleService.queryAllRoles();
			employeeList = employeeService.queryAll();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("roleList", roleList);
		request.setAttribute("employeeList", employeeList);
		return "success";
	}

	/**
	 * 添加账户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String userAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String employeeNo = request.getParameter("employeeNo");
		String userStatus = request.getParameter("userStatus");
		String userRole = request.getParameter("userRole");

		User user = new User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setEmployeeNo(employeeNo);
		user.setUserStatus(Integer.valueOf(userStatus));
		user.setUserRole(userRole);

		try {
			User userFromDB = userService.queryByUserName(userName);
			if (null == userFromDB) {
				userService.addUser(user);
			} else {
				request.setAttribute("errorMsg", "该账号已存在");
				return "error";
			}

		} catch (OAException e1) {
			e1.printStackTrace();
		}

		request.setAttribute("operator", Constant.USER_ADD);
		return "success";

	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String userDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		try {
			userService.deleteUser(Integer.valueOf(userId));
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.USER_DEL);
		return "success";
	}

	/**
	 * 查询修改用户
	 */
	public String queryUserUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String employeeNo = request.getParameter("employeeNo");
		System.out.println("employeeNo"+employeeNo);

		User user = null;
		List<Role> roleList = null;
		List<Employee> employeeList = null;
		Employee employee = null;
		try {
			user = userService.queryByUserId(userId);
			employee = employeeService.queryByEmpNo(employeeNo);
			roleList = roleService.queryAllRoles();
			employeeList = employeeService.queryAll();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("user", user);
		request.setAttribute("roleList", roleList);
		request.setAttribute("employee", employee);
		request.setAttribute("employeeList", employeeList);
		return "success";
	}

	/**
	 * 修改账户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String userUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String employeeNo = request.getParameter("employeeNo");
		String userStatus = request.getParameter("userStatus");
		String userRole = request.getParameter("userRole");

		User user = new User();
		user.setId(Integer.valueOf(userId));
		user.setUserName(userName);
		user.setEmployeeNo(employeeNo);
		user.setUserStatus(Integer.valueOf(userStatus));
		user.setUserRole(userRole);

		try {
			userService.updateUser(user);
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}

		request.setAttribute("operator", Constant.USER_EDIT);
		return "success";
	}

	/**
	 * 查询用户明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryUserDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		System.out.println(userId);
		User user = null;
		try {
			user = userService.queryByUserId(userId);
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		System.out.println(user.getUserRole());
		return "success";
	}

	/**
	 * 密码重置
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String resetPwd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String newPwd = request.getParameter("newPwd");// 新密码
		User user = (User) request.getSession().getAttribute("user");
		user.setPassWord(newPwd);
		try {
			userService.reSetPwd(user);
		} catch (OAException e) {
			e.printStackTrace();
			// 统一到error.jsp 记录错误信息 -----》重新回到添加页面
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		// 记录什么操作？---定义一个常量类
		request.setAttribute("operator", Constant.PWD_RESET);
		return "success";
	}

	/**
	 * 模糊查询根据用户名
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryByUserName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String inputUserName = request.getParameter("inputUserName");
		List<User> userList = userService.queryByInputName(inputUserName);
		for (User user : userList) {
			user.setCreateTime(null);
		}
		String users = JSONArray.fromObject(userList).toString();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(users);
		return "success";

	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String checkUserName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		String result = "";
		User userFromDB = userService.queryByUserName(userName);
		if (null == userFromDB) {
			result = "0";
		} else {
			result = "1";
		}

		response.getWriter().write(result);
		return "success";
	}

	/**
	 * 系统退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String quit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("user");
		return "success";
	}
}
