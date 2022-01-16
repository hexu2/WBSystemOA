package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.HolidayDao;
import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.HolidayService;
import com.njwangbo.oa.service.UserService;
import com.njwangbo.oa.service.WorkFlowService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;

public class HolidayServiceImpl implements HolidayService{
	
	//private HolidayDao holidayDao = (HolidayDao)ObjectFactory.getBean("holidayDao");
	//private Transaction tx = (Transaction)ObjectFactory.getBean("tx");
	
	private HolidayDao holidayDao;
	private Transaction tx;
	

	
	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}


	/**
	 * 添加请假记录
	 */
	@Override
	public void addHoliday(Holiday holiday) throws OAException {
		//基础数据校验
		//1，请假编号是否为空且是6位String
		if(StringUtil.isEmpty(holiday.getHolidayUser())){//2，请假申请人不能为空
			throw new OAException("请假申请人不能为空");
		}else if(StringUtil.isEmpty(holiday.getHolidayType())){//3.请假类型不能为空
			throw new OAException("请假类型不能为空");
		}else if(StringUtil.isEmpty(holiday.getHolidayBz())){//4.请假事由不能为空
			throw new OAException("请假事由不能为空");
		}else if(StringUtil.isEmpty(holiday.getStartTime())){
			throw new OAException("开始时间不能为空");
		}else if(StringUtil.isEmpty(holiday.getEndTime())){
			throw new OAException("结束时间不能为空");
		}

		try {
			//1.2 业务流程数据校验 请假编号是否重复  需要查数据库
			//根据请假编号查询部门信息
			Holiday holidayFromDB = holidayDao.queryById(holiday.getHolidayNo());
			if(null == holidayFromDB){
				tx.begin();
				holidayDao.add(holiday);
				tx.commit();
				//modify by longhuan 2016-10-25 start-----------
				//如果是提交状态，则需要添加工作流
				if("2".equals(holiday.getHolidayStatus())){//提交申请
					//添加工作流
					WorkFlowService workFlowService = (WorkFlowService) ApplicationContext.getBean("workFlowService");
					workFlowService.addSatrtNode(101, holiday.getHolidayNo());
					/**
					 * 根据用户名查找用户id
					 */
					UserService userService = (UserService) ApplicationContext.getBean("userService");
					User user = userService.queryByUserName(holiday.getHolidayUser());
					workFlowService.nextNode(holiday.getHolidayNo(), 1, String.valueOf(user.getId()),
							"工作流提交", "pass");
				}
				//modify by longhuan 2016-10-25 end-----------
			}else {
				throw new OAException("该请假编号已经存在");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}

	/**
	 * 根据请假编号删除一个部门
	 */
	@Override
	public void delteHoliday(String holidayNo) throws OAException {
		
		try {
			tx.begin();
			holidayDao.deleteById(holidayNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
	}

	/**
	 * 查询所有请假记录
	 */
	@Override
	public List<Holiday> queryAll() throws OAException {
		List<Holiday> holidayList = null;
		try {
			holidayList = holidayDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return holidayList;
	}

	/**
	 * 分页查询请假记录
	 */
	@Override
	public List<Holiday> queryByPage(int pageNo, int pageSize) throws OAException {
		List<Holiday> holidayList = null;
		try {
			holidayList = holidayDao.queryByPage(pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return holidayList;
	}


	/**
	 * 根据请假编号查询部门
	 */
	@Override
	public Holiday selectHoliday(String holidayNo) throws OAException {
		//1，请假编号是否为空且是6位String
		if(StringUtil.isEmpty(holidayNo)){
			throw new OAException("请假编号不能为空");
		}else if(holidayNo.trim().length() != 6){
			throw new OAException("请假编号长度必须为6");
		}
		Holiday holiday = null;
		try {
			tx.begin();
			holiday = holidayDao.queryById(holidayNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return holiday;
	}

	/**
	 * 修改请假信息
	 */
	@Override
	public void updateHoliday(Holiday holiday) throws OAException {

		//基础数据校验
		//1，请假编号是否为空且是6位String
		if(StringUtil.isEmpty(holiday.getHolidayType())){//3.请假类型不能为空
			throw new OAException("请假类型不能为空");
		}else if(StringUtil.isEmpty(holiday.getHolidayBz())){//4.请假事由不能为空
			throw new OAException("请假事由不能为空");
		}else if(StringUtil.isEmpty(holiday.getStartTime())){
			throw new OAException("开始时间不能为空");
		}else if(StringUtil.isEmpty(holiday.getEndTime())){
			throw new OAException("结束时间不能为空");
		}else if(StringUtil.isEmpty(holiday.getHolidayStatus())){
			throw new OAException("申请状态不能为空");
		}
		
		try {
			tx.begin();
			holidayDao.update(holiday);
			//modify by longhuan 2016-10-25 start-----------
			//如果是提交状态，则需要添加工作流
			if("2".equals(holiday.getHolidayStatus())){//提交申请
				//添加工作流
				WorkFlowService workFlowService = (WorkFlowService) ApplicationContext.getBean("workFlowService");
				workFlowService.addSatrtNode(101, holiday.getHolidayNo());
				/**
				 * 根据用户名查找用户id
				 */
				UserService userService = (UserService) ApplicationContext.getBean("userService");
				User user = userService.queryByUserName(holiday.getHolidayUser());
				workFlowService.nextNode(holiday.getHolidayNo(), 1, String.valueOf(user.getId()),
						"工作流提交", "pass");
			}
			//modify by longhuan 2016-10-25 end-----------
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}

	/**
	 * 按分页模型查询请假记录
	 */
	@Override
	public PageModel<Holiday> queryByModel(int pageNo, int pageSize,String holidayUser)
			throws OAException {
		PageModel<Holiday> pageModel = new PageModel<Holiday>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			if(null != holidayUser){//非管理员登录时只能个人查看个人
				pageModel = holidayDao.queryByPageModel(holidayUser,pageModel);
				pageModel.setCnt(queryCnt(holidayUser));
			}else{
				pageModel = holidayDao.queryByPageModelForAdmin(pageModel);
				pageModel.setCnt(queryCnt(holidayUser));
			}
			if(queryCnt(holidayUser) == 0){
				pageModel.setPageNo2(0);
			}			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return pageModel;
	}

	/**
	 * 查询id最大的请假记录
	 */
	@Override
	public Holiday queryHolidayMaxId() throws OAException {
		Holiday holiday = null;
		try {
			holiday = holidayDao.queryHolidayMaxId();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return holiday;
	}


	/**
	 * 按照分页模型模糊查询
	 * @param holidayUser 申请人
	 * @param holidayType 申请类型
	 * @param holidayStatus 申请状态
	 * @param pageModel 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PageModel<Holiday> queryByPageModelForBtn(String holidayUser,
			String holidayType, String holidayStatus,int pageNo,int pageSize) throws OAException {
		if(null == holidayUser){
			holidayUser = "";
		}else if(null == holidayType){
			holidayType = "";
		}else if(null == holidayStatus){
			holidayStatus = "";
		}
		PageModel<Holiday> pageModel = new PageModel<Holiday>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = holidayDao.queryByKeys(holidayUser, holidayType, holidayStatus, pageModel);
			pageModel.setCnt(holidayDao.queryCntAfterPage(holidayUser, holidayType, holidayStatus));
			if(holidayDao.queryCntAfterPage(holidayUser, holidayType, holidayStatus) == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return pageModel;
	}

	/**
	 * 查询请假数量
	 * @return int 请假记录总数
	 * @throws SQLException
	 */
	@Override
	public int queryCnt(String holidayUser) throws OAException {
		int cnt = 0;
		try {
			if(null != holidayUser){
				cnt = holidayDao.queryCnt(holidayUser);
			}else{
				cnt = holidayDao.queryCntForAdmin();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return cnt;
	}

}
