package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.Properties;
import com.njwangbo.oa.util.RowMapper;

public class PropertiesMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		Properties properties = new Properties();
		properties.setType(rs.getString("t_type"));
		properties.setKeyId(rs.getString("t_keyId"));
		properties.setPageValue(rs.getString("t_pageValue"));
		
		return properties;
	}

}
