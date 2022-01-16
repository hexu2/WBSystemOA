package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.UserDao;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;

public class UserDaoImpl implements UserDao {
	// 查询所有用户
	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryAll() throws SQLException {
		List<User> userList = null;
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no";
		userList = JdbcTemplate.executeQuery(sql, new UserMapper(),
				new Object[] {});
		return userList;
	}

	// 根据用户名和密码查询用户
	@SuppressWarnings("unchecked")
	@Override
	public User queryByNameAndPwd(String username, String pwd)
			throws SQLException {
		User user = null;
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e1.t_role,e1.t_createtime " +
				"from t_user e1 ,t_employee e3 " +
				"where  e1.t_emp_no= e3.t_emp_no " +
				"and e1.t_userName = ? and e1.t_pwd =?";
		List<User> userList = JdbcTemplate.executeQuery(sql, new UserMapper(),
				username, pwd);
		if (userList.size() > 0) {
			user = userList.get(0);
		}
		return user;
	}

	// 添加用户===========
	@Override
	public void add(User user) throws SQLException {
		String sql = "insert into t_user(t_userName,t_pwd,t_emp_no,t_user_status,t_role,t_createtime) "
				+ "values(?,?,?,?,?,now())";
		JdbcTemplate.executeUpdate(sql, user.getUserName(), user.getPassWord(),
				user.getEmployeeNo(), user.getUserStatus(), user.getUserRole());
	}

	// 根据用户id修改用户
	@Override
	public void reSetPwd(User user) throws SQLException {
		String sql = "update t_user set t_pwd=?,t_createtime = now() where t_id =?";
		JdbcTemplate.executeUpdate(sql,user.getPassWord(),user.getId());
	}

	
	// 根据用户id修改用户
	@Override
	public void update(User user) throws SQLException {
		String sql = "update t_user set t_userName=?,t_emp_no=?,t_user_status=?,t_role=?,t_createtime = now() where t_id =?";
		JdbcTemplate.executeUpdate(sql, user.getUserName(), user
				.getEmployeeNo(), user.getUserStatus(), user.getUserRole(),
				user.getId());
	}
	
	// 根据id删除用户
	@Override
	public void deleteById(int id) throws SQLException {
		String sql = "delete from t_user where t_id =?";
		JdbcTemplate.executeUpdate(sql, id);

	}

	// 按照分页模糊查询
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<User> queryByKeys(String userName, String userStatus,
			String userRole, PageModel<User> pageModel) throws SQLException {
		List<User> userList = null;
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime  "
				+ "from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no "
				+ "and e1.t_userName like ? and e1.t_user_status like ? and e2.t_role_name like ?"
				+ "limit ?,?";
		userList = JdbcTemplate.executeQuery(sql, new UserMapper(), userName,
				userStatus, userRole, (pageModel.getPageNo() - 1)
						* pageModel.getPageSize(), pageModel.getPageSize());
		pageModel.setDataList(userList);
		return pageModel;
	}

	// 按照分页模型查询用户
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<User> queryByPageModel(PageModel<User> pageModel)
			throws SQLException {
		List<User> userList = null;
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no "
				+ "limit ?,?";
		userList = JdbcTemplate.executeQuery(sql, new UserMapper(), (pageModel
				.getPageNo() - 1)
				* pageModel.getPageSize(), pageModel.getPageSize());
		pageModel.setDataList(userList);
		return pageModel;
	}

	// 查询用户总数量
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt() throws SQLException {
		String sql = "select count(1)as t_cnt from t_user";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, new Object[] {});
		return list.get(0);
	}

	// 模糊查询分页后的员工总数
	@SuppressWarnings("unchecked")
	@Override
	public int queryCntAfterPage(String userName, String userStatus,
			String userRole) throws SQLException {
		String sql = "select count(1) as cnt "
				+ "from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no "
				+ "and e1.t_userName like ? and e1.t_user_status like ? and e2.t_role_name like ?";

		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, userName, userStatus, userRole);

		return list.get(0);
	}

	// 根据用户名查询用户
	@SuppressWarnings("unchecked")
	@Override
	public User queryByUserName(String userName) throws SQLException {
		User user = null;
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime "
				+ "from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no  and e1.t_userName= ? ";
		List<User> userList = null;
		userList = JdbcTemplate.executeQuery(sql, new UserMapper(), userName);
		if (userList.size() > 0) {
			user = userList.get(0);
		}
		return user;
	}

	// 根据输入的帐号模糊查询用户
	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryByInputName(String inputUserName)
			throws SQLException {
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime  "
				+ "from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no and t_username like ? "
				+ "limit 0,10";

		List<User> userList = JdbcTemplate.executeQuery(sql, new UserMapper(),
				"%" + inputUserName + "%");

		return userList;
	}

	// 根据用户id查找用户
	@SuppressWarnings("unchecked")
	@Override
	public User queryByUserId(String userId) throws SQLException {
		User user = null;
		String sql = "select e1.t_id,e1.t_userName,e1.t_pwd,e1.t_emp_no,e3.t_emp_name,e1.t_user_status,e2.t_role_name as t_role,e1.t_createtime "
				+ "from t_user e1 , t_role e2,t_employee e3 "
				+ "where e1.t_role = e2.t_id and e1.t_emp_no= e3.t_emp_no and e1.t_id = ? ";
		List<User> userList = null;
		userList = JdbcTemplate.executeQuery(sql, new UserMapper(), userId);
		if (userList.size() > 0) {
			user = userList.get(0);
		}
		return user;
	}

	//查询员号为empNO的员工数量
	@SuppressWarnings("unchecked")
	@Override
	public int queryByEmpNO(String empNo) throws SQLException {
		String sql = " select count(1) as cnt from t_user where t_emp_no = ?";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, empNo);

		return list.get(0);
		
	}

}
