package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.HolidayDao;
import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;

public class HolidayDaoImpl implements HolidayDao {

	/**
	 * 添加请假记录
	 */
	@Override
	public void add(Holiday holiday) throws SQLException {
		String sql = "insert into t_holiday(t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time) "
				+ "values(?,?,?,?,?,?,?,now());";
		JdbcTemplate.executeUpdate(sql, holiday.getHolidayNo(), holiday
				.getHolidayUser(), holiday.getHolidayType(), holiday
				.getHolidayBz(), holiday.getStartTime(), holiday.getEndTime(),
				holiday.getHolidayStatus());

	}

	/**
	 * 根据请假编号删除请假记录
	 */
	@Override
	public void deleteById(String holidayNo) throws SQLException {
		String sql = "delete from t_holiday where t_holiday_no = ?";
		JdbcTemplate.executeUpdate(sql, holidayNo);
	}

	/**
	 * 查询所有请假记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Holiday> queryAll() throws SQLException {
		String sql = "select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type'";
		List<Holiday> holidayList = null;
		holidayList = JdbcTemplate.executeQuery(sql, new HolidayMapper(),
				new Object[] {});
		return holidayList;
	}

	/**
	 * 根据请假编号查询请假记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Holiday queryById(String holidayNo) throws SQLException {
		String sql = "select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type' and e1.t_holiday_no = ?";
		Holiday holiday = null;
		List<Holiday> holidayList = JdbcTemplate.executeQuery(sql,
				new HolidayMapper(), holidayNo);
		if (holidayList.size() > 0) {
			holiday = holidayList.get(0);
		}
		return holiday;
	}

	/**
	 * 请假分页查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Holiday> queryByPage(int pageNo, int pageSize)
			throws SQLException {
		String sql = "select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type' " + "limit ?,?";
		List<Holiday> holidayList = null;
		holidayList = JdbcTemplate.executeQuery(sql, new HolidayMapper(),
				(pageNo - 1) * pageSize, pageSize);
		return holidayList;
	}

	/**
	 * 根据用户名查询请假记录总数(普通用户登录)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt(String holidayUser) throws SQLException {
		String sql = "select count(1) as t_cnt "
				+ "from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type' and e1.t_holiday_user = ?";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, holidayUser);
		return list.get(0);
	}

	/**
	 * 查询所有请假记录数量
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public int queryCntForAdmin() throws SQLException {
		String sql = "select count(1) as t_cnt "
				+ "from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type'";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, new Object[]{});
		return list.get(0);
	}
	
	/**
	 * 根据请假编号修改一个请假记录
	 */
	@Override
	public void update(Holiday holiday) throws SQLException {
		String sql = "update t_holiday "
				+ "set t_holiday_user = ?,t_holiday_type = ?,t_holiday_bz = ?,t_start_time = ?,t_end_time = ?,t_holiday_status = ?,t_create_time  = now()"
				+ "where t_holiday_no = ?";
		JdbcTemplate.executeUpdate(sql, holiday.getHolidayUser(), holiday
				.getHolidayType(), holiday.getHolidayBz(), holiday
				.getStartTime(), holiday.getEndTime(), holiday
				.getHolidayStatus(), holiday.getHolidayNo());
	}

	/**
	 * 按照分页模型查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Holiday> queryByPageModel(String holidayUser,
			PageModel<Holiday> pageModel) throws SQLException {
		List<Holiday> holidayList = null;
		String sql = "select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId  and e1.t_holiday_user = ? "
				+ "where e2.t_type = '1_holiday_type' " + "limit ?,?";
		holidayList = JdbcTemplate.executeQuery(sql, new HolidayMapper(),
				holidayUser, (pageModel.getPageNo() - 1)
						* pageModel.getPageSize(), pageModel.getPageSize());
		pageModel.setDataList(holidayList);
		return pageModel;
	}

	/**
	 * 管理员查询请假记录
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public PageModel<Holiday> queryByPageModelForAdmin(
			PageModel<Holiday> pageModel) throws SQLException {
		List<Holiday> holidayList = null;
		String sql = "select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId  "
				+ "where e2.t_type = '1_holiday_type' " + "limit ?,?";
		holidayList = JdbcTemplate.executeQuery(sql, new HolidayMapper(),(pageModel.getPageNo() - 1)
						* pageModel.getPageSize(), pageModel.getPageSize());
		pageModel.setDataList(holidayList);
		return pageModel;
	}

	/**
	 * 查询id最大的那条请假记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Holiday queryHolidayMaxId() throws SQLException {
		String sql = "select  t_id,t_holiday_no,t_holiday_user,t_holiday_type,t_holiday_bz,t_start_time,t_end_time,t_holiday_status,t_create_time from t_holiday "
				+ "where t_id = (select max(t_id) from t_holiday)";
		Holiday holiday = null;
		List<Holiday> holidayList = JdbcTemplate.executeQuery(sql,
				new HolidayMapper(), new Object[] {});
		if (holidayList.size() > 0) {
			holiday = holidayList.get(0);
		}
		return holiday;
	}

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
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Holiday> queryByKeys(String holidayUser,
			String holidayType, String holidayStatus,
			PageModel<Holiday> pageModel) throws SQLException {
		if (null == holidayUser) {
			holidayUser = "";
		}
		if (null == holidayType) {
			holidayType = "";
		}
		if (null == holidayStatus) {
			holidayStatus = "";
		}

		// 拼接like后的内容
		String holidayUser1 = "%" + holidayUser + "%";
		String holidayType1 = "%" + holidayType + "%";
		String holidayStatus1 = "%" + holidayStatus + "%";

		List<Holiday> holidayList = null;
		String sql = "select e1.t_id,e1.t_holiday_no,e1.t_holiday_user,e2.t_pageValue as t_holiday_type ,e1.t_holiday_bz,e1.t_start_time,e1.t_end_time,e1.t_holiday_status,e1.t_create_time from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type' and e1.t_holiday_user like ? and e2.t_pageValue like ? and e1.t_holiday_status like ? "
				+ "limit ?,?";
		holidayList = JdbcTemplate.executeQuery(sql, new HolidayMapper(),
				holidayUser1, holidayType1, holidayStatus1, (pageModel
						.getPageNo() - 1)
						* pageModel.getPageSize(), pageModel.getPageSize());

		pageModel.setDataList(holidayList);

		return pageModel;
	}

	/**
	 * 模糊查询分页后的请假记录总数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCntAfterPage(String holidayUser, String holidayType,
			String holidayStatus) throws SQLException {
		if (null == holidayUser) {
			holidayUser = "";
		} else if (null == holidayType) {
			holidayType = "";
		} else if (null == holidayStatus) {
			holidayStatus = "";
		}
		String holidayUser1 = "%" + holidayUser + "%";
		String holidayType1 = "%" + holidayType + "%";
		String holidayStatus1 = "%" + holidayStatus + "%";

		String sql = "select count(1) as cnt "
				+ "from t_holiday e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_holiday_type = e2.t_keyId "
				+ "where e2.t_type = '1_holiday_type' and e1.t_holiday_user like ? and e2.t_pageValue like ? and e1.t_holiday_status like ?";

		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, holidayUser1, holidayType1, holidayStatus1);

		return list.get(0);
	}

}
