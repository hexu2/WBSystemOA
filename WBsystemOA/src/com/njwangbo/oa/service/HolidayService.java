package com.njwangbo.oa.service;

import java.sql.SQLException;
import java.util.List;
import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 请假业务逻辑接口层
 * @author Administrator
 *
 */
public interface HolidayService {
	
	/**
	 * 查询所有请假记录=====
	 * @return List<Holiday> 所有请假记录的集合
	 * @throws OAException
	 */
	List<Holiday> queryAll() throws OAException;

	/**
	 * 添加请假记录 =====
	 * @param Holiday
	 * @throws OAException
	 */
	void addHoliday(Holiday holiday) throws OAException;
	
	/**
	 * 根据请假编号删除一个部门========
	 * @param HolidayNo 请假编号
	 * @throws OAException 自定义异常
	 */
	void delteHoliday(String holidayNo) throws OAException;

	/**
	 * 修改请假信息===========
	 * @param Holiday 构造的新的请假记录
	 * @throws OAException 自定义异常
	 */
	void updateHoliday(Holiday holiday) throws OAException;
	
	/**
	 * 根据请假编号查询请假记录==========
	 * @param HolidayNo 请假编号
	 * @return Holiday 一条请假记录
	 * @throws OAException
	 */
	Holiday selectHoliday(String holidayNo) throws OAException;
	
	/**
	 * 分页查询请假记录=====
	 * @param pageNo 页号
	 * @param pageSize 页面大小
	 * @return List<Holiday> 一次分页的集合
	 * @throws OAException
	 */
	List<Holiday> queryByPage(int pageNo,int pageSize) throws OAException;
	
	/**
	 * 按分页模型查询请假记录=========
	 * @param pageNo 页号
	 * @param pageSize 页面大小
	 * @return PageModel<Holiday> 
	 * @throws OAException
	 */
	PageModel<Holiday> queryByModel(int pageNo,int pageSize,String holidayUser) throws OAException;

	/**
	 * 查询id最大的请假记录
	 * @return Holiday id 最大
	 * @throws OAException
	 */
	Holiday queryHolidayMaxId() throws OAException;

	/**
	 * 按照分页模型模糊查询
	 * @param holidayUser 申请人
	 * @param holidayType 申请类型
	 * @param holidayStatus 申请状态
	 * @param pageModel 
	 * @return
	 * @throws SQLException
	 */
	public PageModel<Holiday> queryByPageModelForBtn(String holidayUser, String holidayType, String holidayStatus, int pageNo,int pageSize) throws OAException;

	
	/**
	 * 查询请假数量
	 * @return int 请假记录总数
	 * @throws SQLException
	 */
	int queryCnt(String holidayUser) throws OAException;
}
