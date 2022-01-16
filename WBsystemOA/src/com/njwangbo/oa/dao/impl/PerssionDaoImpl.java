package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.PermissionDao;
import com.njwangbo.oa.entity.Permission;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;

/**
 * 权限数据操作接口实现类
 * @author soft01
 *
 */
public class PerssionDaoImpl implements PermissionDao{

	//按照分页模型查询权限
	@SuppressWarnings("unchecked")
	@Override 
	public PageModel<Permission> queryByPageModel(PageModel<Permission> pageModel)
			throws SQLException {
		List<Permission> roleList = null;
		String sql ="select e1.t_id,e1.t_role_id,e2.t_role_name,e1.t_menu_id,e3.t_menu_name,e1.t_create_time " +
				"from t_permissions e1, t_role e2, t_menu e3 " +
				"where e1.t_role_id=e2.t_id and e1.t_menu_id=e3.t_id limit ?,?";
		roleList = JdbcTemplate.executeQuery(sql, new PermissionMapper(), (pageModel.getPageNo()-1)*pageModel.getPageSize(),pageModel.getPageSize());
		pageModel.setDataList(roleList);
		return pageModel;
	}

	//查询角色总数
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt() throws SQLException {
		String sql = "select count(1) as t_cnt from  t_permissions";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, new Object[]{});
		return list.get(0);
	}

	//按照分页模型模糊查询
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Permission> queryByKeys(String roleName , String menuName  ,
			PageModel<Permission> pageModel) throws SQLException {
		if(null == roleName){
			roleName = "";
		}
		if(null == menuName){
			menuName = "";
		}
		String roleName1 = "%" + roleName + "%";
		String menuName1 = "%" + menuName + "%";
		
		List<Permission> permissionList = null;
		String sql = "select e1.t_id,e1.t_role_id,e2.t_role_name,e1.t_menu_id,e3.t_menu_name,e1.t_create_time " +
				"from t_permissions e1, t_role e2, t_menu e3 " +
				"where e1.t_role_id=e2.t_id and e1.t_menu_id=e3.t_id and e2.t_role_name like ? and e3.t_menu_name like ? " +
				"limit ?,?";
		
		permissionList = JdbcTemplate.executeQuery(sql, new PermissionMapper(),roleName1,menuName1,(pageModel.getPageNo()-1)*pageModel.getPageSize(),pageModel.getPageSize());
		
		pageModel.setDataList(permissionList);
		
		return pageModel;
	}

	//模糊查询后的角色总数
	@SuppressWarnings("unchecked")
	@Override
	public int queryCntAfterPage(String roleName , String menuName ) throws SQLException {
		if(null == roleName){
			roleName = "";
		}
		if(null == menuName){
			menuName = "";
		}
		String roleName1 = "%" + roleName + "%";
		String menuName1 = "%" + menuName + "%";
		
		
		String sql = "select count(1) as cnt " +
				"from t_permissions e1, t_role e2, t_menu e3 " +
				"where e1.t_role_id=e2.t_id and e1.t_menu_id=e3.t_id and e2.t_role_name like ? and e3.t_menu_name like ? ";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, roleName1, menuName1);
		
		return list.get(0);
	}

	//添加权限
	@Override
	public void addPermission(int roleId, int menuId) throws SQLException {
		String sql = "insert into t_permissions(t_role_id,t_menu_id,t_create_time) " +
				"values(?,?,now())";
		JdbcTemplate.executeUpdate(sql, roleId,menuId);
	}
	
	//根据id删除权限记录
	@Override
	public void delteByid(int permissionId) throws SQLException {
		String sql ="delete from t_permissions where t_id = ?";
		JdbcTemplate.executeUpdate(sql, permissionId);
		
	}

}
