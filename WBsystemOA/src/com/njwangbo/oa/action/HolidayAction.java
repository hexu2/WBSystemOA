package com.njwangbo.oa.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.HolidayService;
import com.njwangbo.oa.service.PropertiesService;
import com.njwangbo.oa.util.Constant;
import com.njwangbo.oa.util.PageModel;

/**
 * 请假相关请求操作的处理
 * 
 * @author soft01
 * 
 */
public class HolidayAction {
	private HolidayService holidayService = (HolidayService) ApplicationContext
			.getBean("holidayService");
	private PropertiesService propertiesService = (PropertiesService) ApplicationContext
			.getBean("propertiesService");

	private int pageSize = 5;
	/**
	 * 查询所有请假记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryAllHolidays(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		// 管理员和人事可以察看所有用户请假
		if ("1".equals(user.getUserRole()) || "3".equals(user.getUserRole())) {
			user.setUserName(null);
		}
		String holidayUser = user.getUserName();
		// 从前端获取当前页
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));

		PageModel<Holiday> pageModel = null;
		List<Properties> propertiesList = null;
		try {
			pageModel = holidayService.queryByModel(pageNo, pageSize,
					holidayUser);
			propertiesList = propertiesService.queryAllHolidayProperties();// 配置表
		} catch (OAException e) {
			e.printStackTrace();
		}

		request.setAttribute("propertiesList", propertiesList);
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("holidayUser", holidayUser);
		request.setAttribute("flag", 2);
		return "success";
	}

	/**
	 * 模糊查询所有请假记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryHolidayByBtn(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String holidayType = request.getParameter("holidayType");
		String holidayStatus = request.getParameter("holidayStatus");
		String holidayUser = request.getParameter("holidayUser");

		// 从前端获取当前页
		int pageNo = PageModel.getPageNoFromFront(request
				.getParameter("pageNo"));

		PageModel<Holiday> pageModel = null;
		List<Properties> propertiesList = null;
		try {
			pageModel = holidayService.queryByPageModelForBtn(holidayUser,
					holidayType, holidayStatus, pageNo, pageSize);
			propertiesList = propertiesService.queryAllHolidayProperties();
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("pageModel", pageModel);
		request.setAttribute("propertiesList", propertiesList);
		request.setAttribute("holidayUser", holidayUser);
		request.setAttribute("holidayType", holidayType);
		request.setAttribute("holidayStatus", holidayStatus);
		request.setAttribute("flag", 1);
		return "success";
	}

	/**
	 * 添加请假记录查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryHolidayAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Properties> propertiesList = null;
		Holiday holiday = new Holiday();
		String holidayNo = null;
		try {
			propertiesList = propertiesService.queryAllHolidayProperties();
			holiday = holidayService.queryHolidayMaxId();
		} catch (OAException e) {
			e.printStackTrace();
		}
		if (null == holiday) {
			holidayNo = "QJ0001";
		} else {
			holidayNo = holiday.getHolidayNo();
			// 将holidayNo 截串
			String[] strs = holidayNo.split("QJ");
			int  numb = Integer.valueOf(strs[1]) + 1;
			holidayNo = "QJ" + String.format("%04d", numb);

		}
		
		request.setAttribute("holidayNo", holidayNo);
		request.setAttribute("propertiesList", propertiesList);

		return "success";
	}

	/**
	 * 添加请假记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String holidayAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String holidayNo = request.getParameter("holidayNo");
		String holidayType = request.getParameter("holidayType");
		String holidayBz = request.getParameter("holidayBz");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String holidayStatus = request.getParameter("holidayStatus");
		String holidayUser = request.getParameter("holidayUser");

		Holiday holiday = new Holiday();
		holiday.setHolidayNo(holidayNo);
		holiday.setHolidayUser(holidayUser);
		holiday.setHolidayType(holidayType);
		holiday.setHolidayBz(holidayBz);
		holiday.setStartTime(startTime);
		holiday.setEndTime(endTime);

		if ("草稿".equals(holidayStatus)) {
			holiday.setHolidayStatus("1");
		} else if ("提交".equals(holidayStatus)) {
			holiday.setHolidayStatus("2");
		}
		
		try {
			holidayService.addHoliday(holiday);
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}

		request.setAttribute("operator", Constant.HOLIDAY_ADD);
		return "success";
	}

	/**
	 * 删除请假记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String holidayDel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String holidayNo = request.getParameter("holidayNo");
		try {
			holidayService.delteHoliday(holidayNo);
		} catch (OAException e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
			return "error";
		}
		request.setAttribute("operator", Constant.HOLIDAY_DEL);
		return "success";

	}

	/**
	 * 查询修改请假记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryHolidayUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String holidayNo = request.getParameter("holidayNo");
		List<Properties> propertiesList = null;
		Holiday holiday = null;
		try {
			holiday = holidayService.selectHoliday(holidayNo);
			propertiesList = propertiesService.queryAllHolidayProperties();
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("holiday", holiday);
		request.setAttribute("propertiesList", propertiesList);
		return "success";
	}

	/**
	 * 请假修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String holidayUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String holidayNo = request.getParameter("holidayNo");
		String holidayUser = request.getParameter("holidayUser");
		String holidayType = request.getParameter("holidayType");
		String holidayBz = request.getParameter("holidayBz");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String holidayStatus = request.getParameter("holidayStatus");

		Holiday holiday = new Holiday();
		holiday.setHolidayNo(holidayNo);
		holiday.setHolidayUser(holidayUser);
		holiday.setHolidayType(holidayType);
		holiday.setHolidayBz(holidayBz);
		holiday.setStartTime(startTime);
		holiday.setEndTime(endTime);
		holiday.setHolidayStatus(holidayStatus);

		if ("草稿".equals(holidayStatus)) {
			holiday.setHolidayStatus("1");
		} else if ("提交".equals(holidayStatus)) {
			holiday.setHolidayStatus("2");
		}

		try {
			holidayService.updateHoliday(holiday);
		} catch (OAException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			return "error";
		}

		request.setAttribute("operator", Constant.HOLIDAY_EDIT);
		return "success";
	}

	/**
	 * 请假明细查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String queryHolidayDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String holidayNo = request.getParameter("holidayNo");

		Holiday holiday = null;
		try {
			holiday = holidayService.selectHoliday(holidayNo);
		} catch (OAException e) {
			e.printStackTrace();
		}
		request.setAttribute("holiday", holiday);
		return "success";
	}

}
