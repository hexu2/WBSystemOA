package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.util.RowMapper;

public class MenuMapper implements RowMapper{

	@Override
	public Object mapperObject(ResultSet rs) throws SQLException {
		Menu menu = new Menu();
		menu.setId(rs.getInt("t_id"));
		menu.setMenuName(rs.getString("t_menu_name"));
		menu.setHrefUrl(rs.getString("t_href_url"));
		menu.setParentID(rs.getString("t_parent_id"));
		
		return menu;
	}

}
