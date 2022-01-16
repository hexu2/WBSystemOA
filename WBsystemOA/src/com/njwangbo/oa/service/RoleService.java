package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 角色业务逻辑接口
 * @author Administrator
 *
 */
public interface RoleService {
	/**
	 * 查询所有角色记录
	 * @return List<Role>所有角色记录的集合
	 * @throws OAException
	 */
	List<Role> queryAllRoles() throws OAException;
	
	/**
	 * 根据角色ID查询角色
	 * @return Role一条角色记录
	 * @throws OAException
	 */
	Role queryById(int roleId) throws OAException;
	
	/**
	 * 添加新角色
	 * @param role 一条角色记录
	 * @throws OAException
	 */
	void addRole(Role role) throws OAException;
	
	/**
	 * 删除角色根据id
	 * @param roleId 角色id
	 * @throws OAException
	 */
	void deleteByRoleId(int roleId) throws OAException;
	
	/**
	 * 修改角色根据角色id
	 * @param role
	 * @throws OAException
	 */
	void updateRole(Role role) throws OAException;
	
	/**
	 * 查询角色总数
	 * @return int 角色总数
	 * @throws OAException
	 */
	int queryCnt() throws OAException;
	
	/**
	 * 按照分页模型查询角色
	 * @param pageNo 当前页号
	 * @param pageSize 页面大小
	 * @return PageModel<Role> 
	 * @throws OAException
	 */
	PageModel<Role> queryByPageModel(int pageNo,int pageSize) throws OAException;
	
}
