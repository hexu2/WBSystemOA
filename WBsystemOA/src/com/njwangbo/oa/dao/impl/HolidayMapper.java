package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.Holiday;
import com.njwangbo.oa.util.RowMapper;

public class HolidayMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		Holiday holiday = new Holiday();
		
		holiday.setId(rs.getInt("t_id"));
		holiday.setHolidayNo(rs.getString("t_holiday_no"));
		holiday.setHolidayUser(rs.getString("t_holiday_user"));
		holiday.setHolidayType(rs.getString("t_holiday_type"));
		holiday.setHolidayBz(rs.getString("t_holiday_bz"));
		holiday.setStartTime(rs.getString("t_start_time"));
		holiday.setEndTime(rs.getString("t_end_time"));
		holiday.setHolidayStatus(rs.getString("t_holiday_status"));
		holiday.setCreateTime(rs.getDate("t_create_time"));
		
		return holiday;
	}

}
