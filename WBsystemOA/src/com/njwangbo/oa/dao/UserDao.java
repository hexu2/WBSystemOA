package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.util.PageModel;

/**
 * 用户数据操作
 * @author soft01
 *
 */
public interface UserDao {
	/**
	 * 查询所有用户
	 * @return List<User> 所有用户集合
	 * @throws SQLException
	 */
	List<User> queryAll()throws SQLException;


	/**
	 * 根据用户名密码查询用户
	 * @param username 用户名
	 * @param pwd 密码
	 * @return
	 * @throws SQLException
	 */
	User queryByNameAndPwd(String username,String pwd) throws SQLException;

	/**
	 * 添加用户
	 * @param user 一个新用户
	 * @throws SQLException
	 */
	void add(User user) throws SQLException;

	/**
	 * 根据用户id删除用户
	 * @param id 用户id
	 * @throws SQLException
	 */
	void deleteById(int id) throws SQLException;

	/**
	 * 根据用户id修改用户信息
	 * @param user 一条用户记录
	 * @throws SQLException
	 */
	void update(User user)throws SQLException;
	
	/**
	 * 查询用户总数量
	 * @return int 用户总数量
	 * @throws SQLException
	 */
	int queryCnt() throws SQLException;
	
	/**
	 * 按照分页模糊查询
	 * @return
	 * @throws SQLException
	 */
	public PageModel<User> queryByKeys(String userName,String userStatus,String userRole,PageModel<User> pageModel )throws SQLException;
	
	/**
	 * 模糊查询分页后的员工总数
	 * @return
	 * @throws SQLException
	 */
	int queryCntAfterPage(String userName,String userStatus,String userRole)throws SQLException;


	/**
	 * 按照分页模型查询用户
	 * @param pageModel
	 * @return
	 */
	PageModel<User> queryByPageModel(PageModel<User> pageModel) throws SQLException;
	
	/**
	 * 根据账号查询用户
	 * @param employeeNo
	 * @return
	 * @throws SQLException
	 */
	User queryByUserName(String userName)throws SQLException;


	/**
	 * 根据输入的帐号模糊查询用户
	 * @param inputUserName
	 * @return List<User>
	 */
	List<User> queryByInputName(String inputUserName) throws SQLException;
	
	/**
	 * 根据用户id查找用户
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	User queryByUserId(String userId) throws SQLException;


	/**
	 * 查询员号为empNO的员工数量
	 * @param empNo
	 * @return
	 */
	int queryByEmpNO(String empNo) throws SQLException;
	
	/**
	 * 重置用户密码
	 * @param user
	 * @throws SQLException
	 */
	public void reSetPwd(User user) throws SQLException;
}
