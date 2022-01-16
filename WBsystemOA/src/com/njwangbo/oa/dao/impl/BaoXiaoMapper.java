package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.util.RowMapper;

public class BaoXiaoMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		BaoXiao baoXiao = new BaoXiao();
		
		baoXiao.setId(rs.getInt("t_id"));
		baoXiao.setBaoXiaoNo(rs.getString("t_bx_no"));
		baoXiao.setBaoXiaoUser(rs.getString("t_bx_user"));
		baoXiao.setBaoXiaoType(rs.getString("t_bx_type"));
		baoXiao.setBaoXiaoMoney(rs.getDouble("t_bx_money"));
		baoXiao.setBaoXiaoBz(rs.getString("t_bx_bz"));
		baoXiao.setBaoXiaoStatus(rs.getString("t_bx_status"));
		baoXiao.setApplyTime(rs.getDate("t_apply_time"));
		
		return baoXiao;
	}

}
