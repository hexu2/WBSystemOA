package com.njwangbo.oa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.BaoXiaoService;
import com.njwangbo.oa.service.PropertiesService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.DateUtil;
import com.njwangbo.oa.util.PageModel;

/**
 * 报销相关请求的处理
 * 
 * @author Administrator
 * 
 */
public class BaoXiaoAction {
	private BaoXiaoService baoXiaoService = (BaoXiaoService) ApplicationContext
			.getBean("baoXiaoService");
	private PropertiesService propertiesService = (PropertiesService) ApplicationContext
			.getBean("propertiesService");

	private int pageSize = 5;
	/**
	 * 查询所有报销记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryBaoXiaos(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 管理员和人事可以察看所有用户请假
		if ("1".equals(user.getUserRole()) || "4".equals(user.getUserRole())) {
			user.setUserName(null);
		}
		String baoXiaoUser = user.getUserName();
		// 从前端获取当前页
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));

		PageModel<BaoXiao> pageModel = null;
		List<Properties> propertiesList = null;
		try {
			pageModel = baoXiaoService.queryByModel(pageNo, pageSize,
					baoXiaoUser);
			propertiesList = propertiesService.queryAllBaoXiaoProperties();
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("propertiesList", propertiesList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("baoXiaoUser", baoXiaoUser);
		request.setAttribute("flag", 2);
		return "success";
	}

	/**
	 * 模糊查询请假记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryBaoXiaoByBtn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String baoXiaoType = request.getParameter("baoXiaoType");
		String baoXiaoStatus = request.getParameter("baoXiaoStatus");
		String baoXiaoUser = request.getParameter("baoXiaoUser");
		// 从前端获取当前页
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));

		PageModel<BaoXiao> pageModel = null;
		List<Properties> propertiesList = null;
		try {
			pageModel = baoXiaoService.queryByPageModelForBtn(baoXiaoUser,
					baoXiaoType, baoXiaoStatus, pageNo, pageSize);
			propertiesList = propertiesService.queryAllBaoXiaoProperties();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("pageModel", pageModel);
		request.setAttribute("propertiesList", propertiesList);
		request.setAttribute("baoXiaoUser", baoXiaoUser);
		request.setAttribute("baoXiaoType", baoXiaoType);
		request.setAttribute("baoXiaoStatus", baoXiaoStatus);
		request.setAttribute("flag", 1);
		return "success";
	}

	/**
	 * 查询添加报销
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryBaoXiaoAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Properties> propertiesList = null;
		BaoXiao baoXiao = new BaoXiao();
		String baoXiaoNo = null;
		try {
			propertiesList = propertiesService.queryAllBaoXiaoProperties();
			baoXiao = baoXiaoService.queryBaoXiaoMaxId();
		} catch (OAException e) {
			e.printStackTrace();
		}
		if (null == baoXiao) {
			baoXiaoNo = "BX0001";
		} else {
			baoXiaoNo = baoXiao.getBaoXiaoNo();
			// 将baoXiaoNo 截串
			String[] strs = baoXiaoNo.split("BX");
			int numb = Integer.valueOf(strs[1]) +1;
			baoXiaoNo = "BX" + String.format("%04d", numb);

		}
		request.setAttribute("baoXiaoNo", baoXiaoNo);
		request.setAttribute("propertiesList", propertiesList);
		return "success";
	}

	/**
	 * 添加报销记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String baoXiaoAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String baoXiaoNo = request.getParameter("baoXiaoNo");
		String baoXiaoUser = request.getParameter("baoXiaoUser");
		String baoXiaoType = request.getParameter("baoXiaoType");
		String baoXiaoMoney = request.getParameter("baoXiaoMoney");
		String baoXiaoBz = request.getParameter("baoXiaoBz");
		String baoXiaoStatus = request.getParameter("baoXiaoStatus");
		BaoXiao baoXiao = new BaoXiao();
		baoXiao.setBaoXiaoNo(baoXiaoNo);
		baoXiao.setBaoXiaoUser(baoXiaoUser);
		baoXiao.setBaoXiaoType(baoXiaoType);
		baoXiao.setBaoXiaoMoney(Integer.valueOf(baoXiaoMoney));
		baoXiao.setBaoXiaoBz(baoXiaoBz);

		if ("草稿".equals(baoXiaoStatus)) {
			baoXiao.setBaoXiaoStatus("1");
		} else if ("提交".equals(baoXiaoStatus)) {
			baoXiao.setBaoXiaoStatus("2");
		}

		try {
			baoXiaoService.addBaoXiao(baoXiao);
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}
		request.setAttribute("operator", Constant.BAOXIAO_ADD);
		return "success";
	}

	/**
	 * 删除报销记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String baoXiaoDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String baoXiaoNo = request.getParameter("baoXiaoNo");
		try {
			baoXiaoService.delteBaoXiao(baoXiaoNo);
		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
			return "error";
		}
		request.setAttribute("operator", Constant.BAOXIAO_DEL);
		return "success";
	}

	/**
	 * 修改报销记录查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryBaoXiaoUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String baoXiaoNo = request.getParameter("baoXiaoNo");
		List<Properties> propertiesList = null;
		BaoXiao baoXiao = null;
		try {
			baoXiao = baoXiaoService.selectBaoXiao(baoXiaoNo);
			propertiesList = propertiesService.queryAllBaoXiaoProperties();
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("baoXiao", baoXiao);
		request.setAttribute("propertiesList", propertiesList);
		return "success";
	}

	/**
	 * 修改报销记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String baoXiaoUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String baoXiaoNo = request.getParameter("baoXiaoNo");
		String baoXiaoUser = request.getParameter("baoXiaoUser");
		String baoXiaoType = request.getParameter("baoXiaoType");
		String baoXiaoMoney = request.getParameter("baoXiaoMoney");
		String baoXiaoBz = request.getParameter("baoXiaoBz");
		String baoXiaoStatus = request.getParameter("baoXiaoStatus");
		String applyTime = request.getParameter("applyTime");
		
		
		BaoXiao baoXiao = new BaoXiao();
		baoXiao.setBaoXiaoNo(baoXiaoNo);
		baoXiao.setBaoXiaoUser(baoXiaoUser);
		baoXiao.setBaoXiaoType(baoXiaoType);
		baoXiao.setBaoXiaoMoney(Double.valueOf(baoXiaoMoney));
		baoXiao.setBaoXiaoBz(baoXiaoBz);
		baoXiao.setBaoXiaoStatus(baoXiaoStatus);
		baoXiao.setApplyTime(DateUtil.str2Date(applyTime, "yyyy-MM-dd"));

		if ("草稿".equals(baoXiaoStatus)) {
			baoXiao.setBaoXiaoStatus("1");
		} else if ("提交".equals(baoXiaoStatus)) {
			baoXiao.setBaoXiaoStatus("2");
		}

		BaoXiaoService baoXiaoService = (BaoXiaoService) ApplicationContext
				.getBean("baoXiaoService");

		try {
			baoXiaoService.updateBaoXiao(baoXiao);
		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
			return "error";
		}
		request.setAttribute("operator", Constant.BAOXIAO_EDIT);
		return "success";
	}

	/**
	 * 报销明细查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryBaoXiaoDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String baoXiaoNo = request.getParameter("baoXiaoNo");
		BaoXiao baoXiao = null;
		try {
			baoXiao = baoXiaoService.selectBaoXiao(baoXiaoNo);
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("baoXiao", baoXiao);
		return "success";
	}
}
