package com.njwangbo.oa.service.impl;

import java.sql.SQLException;

import com.njwangbo.oa.dao.PermissionDao;
import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.PermissionService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;

/**
 * 权限业务逻辑接口实现类
 * @author soft01
 *
 */
public class PermissionServiceImpl implements PermissionService{
	
	PermissionDao permissionDao;
	Transaction tx;
	
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}


	//按照分页模型查询权限
	@Override
	public PageModel<Permission> queryByPageModel(int pageNo, int pageSize)
			throws OAException {
		if(StringUtil.isEmpty(String.valueOf(pageNo))){
			throw new OAException("当前页号不能为空");
		}else if(StringUtil.isEmpty(String.valueOf(pageSize))){
			throw new OAException("页面大小不能为空");
		}
		PageModel<Permission> pageModel = new PageModel<Permission>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = permissionDao.queryByPageModel(pageModel);
			pageModel.setCnt(permissionDao.queryCnt());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return pageModel;
	}

	//按照分页模型模糊查询
	@Override
	public PageModel<Permission> queryByKeys(String roleName, String nemuName,
			int pageNo, int pageSize) throws OAException {
		PageModel<Permission> pageModel = new PageModel<Permission>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = permissionDao.queryByKeys(roleName, nemuName, pageModel);
			pageModel.setCnt(permissionDao.queryCntAfterPage(roleName, nemuName));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return pageModel;
	}

	//添加权限
	@Override
	public void addPermission(int roleId, int menuId) throws OAException {
		if(StringUtil.isEmpty(String.valueOf(roleId))){
			throw new OAException("角色id不能为空");
		}else if(StringUtil.isEmpty(String.valueOf(menuId))){
			throw new OAException("菜单id不能为空");
		}
		try {
			tx.begin();
			permissionDao.addPermission(roleId, menuId);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
	}

	//根据id删除权限记录
	@Override
	public void deltePermission(int permissionId) throws OAException {
		if(StringUtil.isEmpty(String.valueOf(permissionId))){
			throw new OAException("权限记录id不能为空");
		}
		
		try {
			tx.begin();
			permissionDao.delteByid(permissionId);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
	}
	
	

}
