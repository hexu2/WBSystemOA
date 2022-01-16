package com.njwangbo.oa.dao;

import java.sql.SQLException;

import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;


/**
 * 权限实体接口
 * @author soft01
 *
 */
public interface PermissionDao {
	
	/**
	 * 按照分页模型查询权限
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	PageModel<Permission> queryByPageModel(PageModel<Permission> pageModel) throws SQLException;

	/**
	 * 查询角色总数
	 * @return
	 * @throws SQLException
	 */
	int queryCnt() throws SQLException;
	
	/**
	 * 按照分页模型模糊查询
	 * @param roleId 角色id
	 * @param menuId 菜单id
	 * @param pageModel
	 * @return
	 * @throws OAException
	 */
	PageModel<Permission> queryByKeys(String roleName , String menuName ,PageModel<Permission> pageModel) throws SQLException;

	/**
	 * 模糊查询后的总角色数
	 * @return
	 * @throws SQLException
	 */
	int queryCntAfterPage(String roleName , String menuName ) throws SQLException;
	
	/**
	 * 添加
	 * @param roleId
	 * @param menuId
	 * @throws SQLException
	 */
	void addPermission(int roleId,int menuId) throws SQLException;

	/**
	 * 根据id删除权限记录
	 * @param permissionId
	 * @throws SQLException
	 */
	void delteByid(int permissionId)  throws SQLException;
}
