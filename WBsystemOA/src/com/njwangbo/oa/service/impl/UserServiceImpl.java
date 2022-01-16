package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.UserDao;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.UserService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	private Transaction tx;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setTx(Transaction tx) {
		this.tx = tx;
	}
	//根据用户名和密码查询用户
	@Override
	public User userLogin(String userName, String passWord) throws OAException {
		//数据校验
		User user = null;
		try {
		user = userDao.queryByNameAndPwd(userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//修改用户信息
	@Override
	public void updateUser(User user) throws OAException {
		if(StringUtil.isEmpty(user.getUserName())){
			throw new OAException("用户名不能为空");
		}else if(StringUtil.isEmpty(user.getEmployeeNo())){
			throw new OAException("用户部门号不能为空");
		}else if(StringUtil.isEmpty(String.valueOf(user.getUserStatus()))){
			throw new OAException("用户类型不能为空");
		}
		try {
			tx.begin();
			userDao.update(user);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}
	
	//添加用户
	@Override
	public void addUser(User user) throws OAException {

		if(StringUtil.isEmpty(user.getUserName())){
			throw new OAException("用户名不能为空");
		}else if(StringUtil.isEmpty(user.getPassWord())){
			throw new OAException("用户密码不能为空");
		}else if(StringUtil.isEmpty(user.getEmployeeNo())){
			throw new OAException("用户部门号不能为空");
		}else if(StringUtil.isEmpty(String.valueOf(user.getUserStatus()))){
			throw new OAException("用户类型不能为空");
		}
		try {
			tx.begin();
			userDao.add(user);
			tx.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}
	
	/**
	 * 重置密码
	 * @param user
	 * @throws SQLException
	 */
	public void reSetPwd(User user) throws OAException {
		
		if(StringUtil.isEmpty(user.getUserName())){
			throw new OAException("用户名不能为空");
		}else if(StringUtil.isEmpty(user.getPassWord())){
			throw new OAException("用户密码不能为空");
		}
		
		try {
			tx.begin();
			userDao.reSetPwd(user);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
		
		
	}
	
	//删除用户
	@Override
	public void deleteUser(int id) throws OAException {
		
		try {
			tx.begin();
			userDao.deleteById(id);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}
	
	//查询所有用户
	@Override
	public List<User> queryAll() throws OAException {
		List<User> userList = null;
		try {
			userList = userDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return userList;
	}
	
	//模糊分页查询
	@Override
	public PageModel<User> queryByKeys(String userName, String userStatus,
			String userRole, int pageNo,int pageSize) throws OAException {
		if(StringUtil.isEmpty(userName)){
			userName = "";
		}
		if(StringUtil.isEmpty(userStatus)){
			userStatus = "";
		}
		if(StringUtil.isEmpty(userRole)){
			userRole = "";
		}
		String userName1 = "%" + userName + "%";
		String userStatus1 = "%" + userStatus + "%";
		String userRole1 = "%" + userRole + "%";
		
		
		PageModel<User> pageModel = new PageModel<User>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel =userDao.queryByKeys(userName1, userStatus1, userRole1, pageModel);
			pageModel.setCnt(userDao.queryCntAfterPage(userName1, userStatus1, userRole1));
			if(userDao.queryCntAfterPage(userName1, userStatus1, userRole1) == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return pageModel;
	}
	
	//分页查询
	@Override
	public
	PageModel<User> queryByPage(int pageNo, int pageSize) throws OAException {
		//构造一个PageModel模型
		PageModel<User> pageModel = new PageModel<User>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = userDao.queryByPageModel(pageModel);
			pageModel.setCnt(userDao.queryCnt());
			if(userDao.queryCnt() == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return pageModel;
	}
	
	//用户总数量
	@Override
	public int queryCnt() throws OAException {
		int cnt = 0;
		try {
			cnt = userDao.queryCnt();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return cnt;
	}
	
	//模糊分页后的用户总数量
	@Override
	public int queryCntAfterPage(String userName, String userStatus,
			String userRole) throws OAException {
		if(StringUtil.isEmpty(userName)){
			userName = "";
		}
		if(StringUtil.isEmpty(userStatus)){
			userStatus = "";
		}
		if(StringUtil.isEmpty(userRole)){
			userRole = "";
		}
		String userName1 = "%" + userName + "%";
		String userStatus1 = "%" + userStatus + "%";
		String userRole1 = "%" + userRole + "%";
		int cnt = 0;
		try {
			cnt = userDao.queryCntAfterPage(userName1, userStatus1, userRole1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//根据账号查询用户
	@Override
	public User queryByUserName(String userName) throws OAException {
		if(StringUtil.isEmpty(userName)){
			throw new OAException("账号不能为空");
		}
		
		User user = null;
		try {
			user = userDao.queryByUserName(userName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return user;
	}
	
	//根据输入的账户名模糊查询用户
	@Override
	public List<User> queryByInputName(String inputUserName) throws OAException {
		
		List<User> userList = null;
		try {
			userList = userDao.queryByInputName(inputUserName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return userList;
	}
	
	//根据用户id查询用户
	@Override
	public User queryByUserId(String userId) throws OAException {
		if(StringUtil.isEmpty(userId)){
			throw new OAException("账号ID不能为空");
		}
		User user = null;
		try {
			user = userDao.queryByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return user;
	}
	
	//查询员工号为empNo的员工数量
	@Override
	public int queryByEmpNO(String empNo) throws OAException {
		if(StringUtil.isEmpty(empNo)){
			throw new OAException("员工号不能为空");
		}
		
		int cnt = 0;
		try {
			cnt = userDao.queryByEmpNO(empNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return cnt;
	}




}
