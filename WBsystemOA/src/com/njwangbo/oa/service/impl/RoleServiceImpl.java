package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.RoleDao;
import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.RoleService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;

/**
 * 角色业务逻辑接口实现类
 * @author Administrator
 *
 */
public class RoleServiceImpl implements RoleService{
	private RoleDao roleDao;
	private Transaction tx;
	
	

	public RoleDao getRoleDao() {
		return roleDao;
	}


	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	public Transaction getTx() {
		return tx;
	}


	public void setTx(Transaction tx) {
		this.tx = tx;
	}


	//查询所有角色
	@Override
	public List<Role> queryAllRoles() throws OAException {
		List<Role> roleList = null;
		
		try {
			roleList = roleDao.queryAllRoles();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return roleList;
	}


	//添加角色 
	@Override
	public void addRole(Role role) throws OAException {
		if(StringUtil.isEmpty(String.valueOf(role.getRoleId()))){
			throw new OAException("角色Id不能为空");
		}else if(StringUtil.isEmpty(role.getRoleName())){
			throw new OAException("角色名不能为空");
		}
		Role roleFromDB = null;
		try {
			roleFromDB = roleDao.queryByRoleName(role.getRoleName());
			if(null == roleFromDB){
				tx.begin();
				roleDao.addRole(role);
				tx.commit();
			}else{
				throw new OAException("该角色已经存在");
			}
		
		
		} catch (SQLException e1) {
			e1.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
		
	}

	//根据id删除角色
	@Override
	public void deleteByRoleId(int roleId) throws OAException {
		if(StringUtil.isEmpty(String.valueOf(roleId))){
			throw new OAException("角色Id不能为空");
		}
		try {
			if(roleDao.queryGuanLianUser(roleId) == 0){
				tx.begin();
				roleDao.deleteByRoleId(roleId);
				tx.commit();
			}else {
				throw new OAException("该角色已经关联用户，不可删除");
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}

	//根据id查询角色
	@Override
	public Role queryById(int roleId) throws OAException {
		if(StringUtil.isEmpty(String.valueOf(roleId))){
			throw new OAException("角色Id不能为空");
		}
		Role role = null;
		try {
			role = roleDao.queryById(roleId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return role;
	}

	//根据分页模型查询角色
	@Override
	public PageModel<Role> queryByPageModel(int pageNo, int pageSize)
			throws OAException {
		if(StringUtil.isEmpty(String.valueOf(pageNo))){
			throw new OAException("当前页号不能为空");
		}else if(StringUtil.isEmpty(String.valueOf(pageSize))){
			throw new OAException("页面大小不能为空");
		}
		PageModel<Role> pageModel = new PageModel<Role>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = roleDao.queryByPageModel(pageModel);
			pageModel.setCnt(roleDao.queryCnt());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return pageModel;
	}

	//查询角色总数
	@Override
	public int queryCnt() throws OAException {
		int cnt = 0;
		try {
			cnt = roleDao.queryCnt();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return cnt;
	}

	//修改角色（根据id）
	@Override
	public void updateRole(Role role) throws OAException {
		Role roleFromDB = new Role();
		try {
			roleFromDB = roleDao.queryByRoleName(role.getRoleName());
			if(null == roleFromDB ){
				tx.begin();
				roleDao.updateRole(role);
				tx.commit();
			}else{
				throw new OAException("该角色已经存在");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		
	}

}
