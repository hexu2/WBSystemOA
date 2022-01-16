package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.util.PageModel;

/**
 * 请假表数据操作接口
 * 
 * @author Administrator
 * 
 */
public interface HolidayDao {

	/**
	 * 查询所有请假记录-------------》已测试通过
	 * 
	 * @return List<Holiday> 所有请假记录集合
	 * @throws SQLException
	 */
	List<Holiday> queryAll() throws SQLException;

	/**
	 * 根据请假编号查询请假记录-------------》已测试通过
	 * 
	 * @param HolidayNo
	 * @return
	 * @throws SQLException
	 */
	Holiday queryById(String holidayNo) throws SQLException;

	/**
	 * 添加请假记录
	 * 
	 * @param Holiday
	 * @throws SQLException
	 */
	void add(Holiday holiday) throws SQLException;

	/**
	 * 根据请假编号删除一个部门
	 * 
	 * @param holidayNo
	 * @throws SQLException
	 */
	void deleteById(String holidayNo) throws SQLException;

	/**
	 * 根据请假编号修改部门信息
	 * 
	 * @param Holiday
	 * @throws SQLException
	 */
	void update(Holiday holiday) throws SQLException;

	/**
	 * 根据用户名查询请假条数
	 * 
	 * @param holidayUser
	 *            用户名
	 * @return
	 * @throws SQLException
	 */
	int queryCnt(String holidayUser) throws SQLException;
	
	/**
	 * 所有请假记录条数（管理员登录时使用）
	 * @return
	 * @throws SQLException
	 */
	public int queryCntForAdmin() throws SQLException;

	/**
	 * 查询id最大的那条请假记录
	 * 
	 * @return Holiday id最大
	 * @throws SQLException
	 */
	Holiday queryHolidayMaxId() throws SQLException;

	/**
	 * 分页查询-------------》已测试通过
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页面显示的大小
	 * @return List<Holiday> 返回当前页中的数据集合
	 * @throws SQLException
	 */
	List<Holiday> queryByPage(int pageNo, int pageSize) throws SQLException;

	/**
	 * 根据用户名分页查询请假记录
	 * 
	 * @param holidayUser
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	PageModel<Holiday> queryByPageModel(String holidayUser,
			PageModel<Holiday> pageModel) throws SQLException;

	/**
	 * 按照分页模型模糊查询
	 * 
	 * @param holidayUser
	 *            申请人
	 * @param holidayType
	 *            申请类型
	 * @param holidayStatus
	 *            申请状态
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	public PageModel<Holiday> queryByKeys(String holidayUser,
			String holidayType, String holidayStatus,
			PageModel<Holiday> pageModel) throws SQLException;

	/**
	 * 模糊查询分页后的请假记录总数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	int queryCntAfterPage(String holidayUser, String holidayType,
			String holidayStatus) throws SQLException;

	/**
	 * 管理员登录时查询所有请假信息
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	public PageModel<Holiday> queryByPageModelForAdmin(
			PageModel<Holiday> pageModel) throws SQLException;
}
