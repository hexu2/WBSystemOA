package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Menu;

/**
 * 菜单接口实现类
 * @author soft01
 *
 */
public interface MenuDao {
	
	List<Menu> queryAllMenu()throws SQLException;
	
	/**
	 * 查询所有一级菜单
	 * @return List<Menu> 所有一级菜单的集合
	 * @throws SQLException
	 */
	List<Menu> queryLevelOneMenu()throws SQLException;
	
	/**
	 * 根据父亲结点查询子菜单
	 * @param parentID 父亲结点
	 * @return 同一个parentID的所有子菜单的集合
	 * @throws SQLException
	 */
	List<Menu> querySonMenuByParentId(int parentID) throws SQLException;

	/**
	 * 根据角色id查询一级菜单
	 * @return List<Menu> 同一角色可以查看的所有一级菜单集合
	 * @throws SQLException
	 */
	List<Menu> queryLevelOneMenuByRole(String roleID) throws SQLException;

	/**
	 * 根据父亲结点 和 角色 id查询子菜单
	 * @param parentID
	 * @param roleID
	 * @return
	 */
	List<Menu> querySonMenuByPidRole(int parentID, String roleID) throws SQLException;
	
}
