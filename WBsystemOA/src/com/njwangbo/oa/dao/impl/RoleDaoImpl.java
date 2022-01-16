package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.RoleDao;
import com.njwangbo.oa.entity.Role;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;

/**
 * 角色数据操作接口实现类
 * @author Administrator
 *
 */
public class RoleDaoImpl implements RoleDao{

	//查询所有角色
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> queryAllRoles() throws SQLException {
		List<Role> roleList = null;
		String sql = "select t_id,t_role_name,t_create_time from t_role";
		roleList = JdbcTemplate.executeQuery(sql, new RoleMapper(), new Object[]{});
		
		return roleList;
	}

	//添加新角色
	@Override
	public void addRole(Role role) throws SQLException {
		String sql = "insert into t_role(t_role_name,t_create_time) values(?,now())";
		JdbcTemplate.executeUpdate(sql, role.getRoleName());
		
	}

	//根据id删除角色记录
	@Override
	public void deleteByRoleId(int roleId) throws SQLException {
		String sql ="delete from t_role where t_id = ?";
		JdbcTemplate.executeUpdate(sql,roleId);
		
	}

	//根据id查询角色
	@SuppressWarnings("unchecked")
	@Override
	public Role queryById(int roleId) throws SQLException {
		Role role = null;
		String sql = "select t_id,t_role_name,t_create_time from t_role where t_id = ?";
		List<Role> roleList= JdbcTemplate.executeQuery(sql, new RoleMapper(), roleId);
		if(roleList.size()>0){
			role = roleList.get(0);
		}
		
		return role;
	}

	//分页查询角色
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Role> queryByPageModel(PageModel<Role> pageModel)
			throws SQLException {
		List<Role> roleList = null;
		String sql ="select t_id,t_role_name,t_create_time from t_role limit ?,?";
		roleList = JdbcTemplate.executeQuery(sql, new RoleMapper(), (pageModel.getPageNo()-1)*pageModel.getPageSize(),pageModel.getPageSize());
		pageModel.setDataList(roleList);
		return pageModel;
	}

	//查询所有角色数
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt() throws SQLException {
		String sql = "select count(1) as t_cnt from t_role";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, new Object[]{});
		return list.get(0);
	}

	//根据id修改角色
	@Override
	public void updateRole(Role role) throws SQLException {
		String sql="update t_role set t_role_name=?,t_create_time=now()  where t_id = ?";
		JdbcTemplate.executeUpdate(sql, role.getRoleName(),role.getRoleId());
		
	}

	//根据角色名查询角色
	@SuppressWarnings("unchecked")
	@Override
	public Role queryByRoleName(String roleName) throws SQLException {
		List<Role> roleList = null;
		Role role = null;
		String sql = "select t_id,t_role_name,t_create_time from t_role where t_role_name = ?";
		roleList = JdbcTemplate.executeQuery(sql, new RoleMapper(), roleName);
		if(roleList.size()>0){
			role = roleList.get(0);
		}
		return role;	
	}

	//根据id查询关联的账户数量
	@SuppressWarnings("unchecked")
	@Override
	public int queryGuanLianUser(int roleId) throws SQLException {
		String sql ="select count(1) as t_cnt from t_user where t_role = ?";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, roleId);
		return list.get(0);
	}

}
