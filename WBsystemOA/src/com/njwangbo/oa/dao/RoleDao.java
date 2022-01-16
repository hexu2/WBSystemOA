package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.util.PageModel;

/**
 * 角色数据操作接口
 * @author Administrator
 *
 */
public interface RoleDao {
	/**
	 * 查询所有角色
	 * @return List<Role>所有角色的集合
	 * @throws SQLException
	 */
	List<Role> queryAllRoles() throws SQLException;
	
	/**
	 * 根据角色ID查询角色
	 * @return Role一条角色记录
	 * @throws SQLException
	 */
	Role queryById(int roleId) throws SQLException;
	
	/**
	 * 根据角色名查询角色
	 * @param roleName
	 * @return
	 * @throws SQLException
	 */
	Role queryByRoleName(String roleName) throws SQLException;
	
	/**
	 * 添加新角色
	 * @param role 一条角色记录
	 * @throws SQLException
	 */
	void addRole(Role role) throws SQLException;
	
	/**
	 * 根据id删除角色
	 * @param roleId 角色id
	 * @throws SQLException
	 */
	void deleteByRoleId(int roleId) throws SQLException;
	
	/**
	 * 修改角色根据角色id
	 * @param role 一条角色记录
	 * @throws SQLException
	 */
	void updateRole(Role role) throws SQLException;
	
	/**
	 * 查询角色总数
	 * @return int 角色总数
	 * @throws SQLException
	 */
	int queryCnt() throws SQLException;
	
	/**
	 * 按照分页模型查询用户
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	PageModel<Role> queryByPageModel(PageModel<Role> pageModel) throws SQLException;

	/**
	 * 根据id查询关联的账户数量
	 */
	int queryGuanLianUser(int roleId) throws SQLException;

	
}
