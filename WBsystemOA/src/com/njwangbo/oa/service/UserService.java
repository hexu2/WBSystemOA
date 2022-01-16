package com.njwangbo.oa.service;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 用户业务逻辑层
 * @author soft01
 *
 */
public interface UserService {
	/**
	 * 查询所有用户
	 * @return List<User> 用户集合
	 * @throws OAException
	 */
	List<User> queryAll() throws OAException;
	/**
	 * 用户登录 
	 * @param userName 用户名
	 * @param passWord 密码
	 * @throws OAException
	 */
	User userLogin(String userName,String passWord) throws OAException;
	
	/**
	 * 增加新用户
	 * @param user
	 * @throws OAException
	 */
	void addUser(User user)  throws OAException;  
	/**
	 * 删除用户
	 * @param user 一个用户对象
	 * @throws OAException
	 */
	void deleteUser(int id)  throws OAException;  
	/**
	 * 修改用户信息
	 * @param user 一个用户对象
	 * @throws OAException
	 */
	void updateUser(User user)  throws OAException;  
	
	/**
	 * 查询用户总数
	 * @return int 用户总数
	 * @throws OAException
	 */
	int queryCnt() throws OAException;
	
	/**
	 * 分页查询用户
	 * @param pageNo 当前页号
	 * @param pageSize 页面大小
	 * @return
	 * @throws OAException
	 */
	PageModel<User> queryByPage(int pageNo,int pageSize) throws OAException;
	
	/**
	 * 按照分页模糊查询
	 * @param userName 用户名
	 * @param userStatus 当前状态id
	 * @param userRole 角色名
	 * @param pageModel
	 * @return
	 * @throws OAException
	 */
	public PageModel<User> queryByKeys(String userName,String userStatus,String userRole, int pageNo,int pageSize) throws OAException;
	
	/***
	 * 模糊查询后的用户总数量
	 * @param userName
	 * @param userStatus
	 * @param userRole
	 * @return
	 * @throws SQLException
	 */
	int queryCntAfterPage(String userName,String userStatus,String userRole) throws OAException;
	
	/**
	 * 根据账号查询用户
	 * @param employeeNo
	 * @return
	 * @throws OAException
	 */
	User queryByUserName(String userName) throws OAException;
	
	/**
	 * 根据输入的账户模糊查询用户
	 * @param inputUserName
	 * @return List<User>
	 */
	List<User> queryByInputName(String inputUserName) throws OAException;
	
	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 * @throws OAException
	 */
	User queryByUserId(String userId) throws OAException;
	
	/**
	 * 查询员工号为empNo的员工数量
	 * @param empNo
	 * @return int
	 * @throws OAException
	 */
	int queryByEmpNO(String empNo) throws OAException;
	
	/**
	 * 重置密码
	 * @param user
	 * @throws SQLException
	 */
	public void reSetPwd(User user) throws OAException;
	
}
