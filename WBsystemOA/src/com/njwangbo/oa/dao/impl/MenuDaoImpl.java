package com.njwangbo.oa.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.MenuDao;
import com.njwangbo.oa.entity.Menu;
import com.njwangbo.oa.util.JdbcTemplate;

/**
 * 菜单接口实现类
 * @author Administrator
 *
 */
public class MenuDaoImpl implements MenuDao{

	//查询所有一级菜单
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> queryLevelOneMenu() throws SQLException {
		String sql = "select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu " +
				"where t_parent_id is null";
		List<Menu> menuList = JdbcTemplate.executeQuery(sql, new MenuMapper(), new Object[]{});
		return menuList;
	}

	//根据父亲结点查询子菜单
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> querySonMenuByParentId(int parentID) throws SQLException {
		
		String sql ="select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu " +
				"where t_parent_id = ?";
		List<Menu> menuList = null;
		menuList = JdbcTemplate.executeQuery(sql, new MenuMapper(), parentID);
		
		return menuList;
	}
	

	//根据角色查询一级菜单
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> queryLevelOneMenuByRole(String roleID) throws SQLException {
		String sql = "select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu " +
		"where t_parent_id is null and t_id in (select t_menu_id from t_permissions where t_role_id=?) ";
		List<Menu> menuList = JdbcTemplate.executeQuery(sql, new MenuMapper(), roleID);
		return menuList;
	}

	//根据父亲id和角色id查询二级菜单
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> querySonMenuByPidRole(int parentID, String roleID) throws SQLException {
		String sql = "select t_id,t_menu_name, t_href_url, t_parent_id, t_create_time " +
				"from t_menu where t_parent_id = ? " +
				"and t_id in (select t_menu_id from t_permissions where t_role_id=?)";
		List<Menu> menuList = JdbcTemplate.executeQuery(sql, new MenuMapper(),parentID,roleID);
		return menuList;
	}
	//查询所有菜单
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> queryAllMenu() throws SQLException {
		String sql = "select t_id,t_menu_name,t_href_url,t_parent_id,t_create_time from t_menu";
		List<Menu> menuList = null;
		menuList = JdbcTemplate.executeQuery(sql, new MenuMapper(), new Object[]{});
		return menuList;
	}

}
