package com.njwangbo.oa.service;

import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 权限业务逻辑接口
 * @author soft01
 *
 */
public interface PermissionService {

	/**
	 *	按照分页模型查询
	 * @param pageNo 当前页号
	 * @param pageSize 当前页面大小
	 * @return PageModel<Permission>
	 * @throws OAException
	 */
	PageModel<Permission> queryByPageModel(int pageNo,int pageSize) throws OAException;
	
	/**
	 * 按照分页模糊查询
	 * @param roleId
	 * @param menuId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws OAException
	 */
	PageModel<Permission> queryByKeys(String roleId , String menuId ,int pageNo,int pageSize) throws OAException;

	/**
	 * 添加权限记录
	 * @param roleId 角色id
	 * @param menuId 菜单id
	 * @throws OAException
	 */
	void addPermission(int roleId, int menuId) throws OAException;

	/**
	 * 根据id删除权限记录
	 */
	void deltePermission(int permissionId)  throws OAException;
}
