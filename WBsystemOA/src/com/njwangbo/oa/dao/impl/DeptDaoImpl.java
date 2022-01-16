package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.DeptDao;
import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;

public class DeptDaoImpl implements DeptDao{

	/**
	 * 添加一个新部门----------已经测试通过
	 */
	@Override
	public void add(Dept dept) throws SQLException {
		String sql ="insert into t_dept (t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime) values(?,?,?,?,now())";
		JdbcTemplate.executeUpdate(sql, dept.getDeptNo(),dept.getDeptName(),dept.getDeptLoc(),dept.getDeptManager());
	}

	/**
	 * 根据部门号删除一个部门----------已经测试通过
	 */
	@Override
	public void deleteById(String deptNo) throws SQLException {
		String sql = "delete from t_dept where t_deptno = ?";
		JdbcTemplate.executeUpdate(sql, deptNo);
	}

	/**
	 * 查询所有部门----------已经测试通过
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> queryAll() throws SQLException {
		String sql = "select t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime from t_dept";
		List<Dept> deptList = null;
		deptList = JdbcTemplate.executeQuery(sql, new DeptMapper(), new Object[]{});
		return deptList;
	}

	/**
	 * 根据部门编号查询一个部门----------已经测试通过
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dept queryById(String deptNo) throws SQLException {
		Dept dept = null;
		String sql = "select t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime from t_dept where t_deptno = ?";
		List<Dept> deptList = JdbcTemplate.executeQuery(sql, new DeptMapper(), deptNo);
		if(deptList.size() > 0){
			dept = deptList.get(0);
		}
		return dept;
	}

	/**
	 * 根据部门编号修改一个部门----------已经测试通过 
	 */
	@Override
	public void update(Dept dept) throws SQLException {
		String sql = " update t_dept set t_deptname=?,t_deptloc=?,t_deptmanager=?,t_createtime=now() where t_deptno= ? ";
		JdbcTemplate.executeUpdate(sql, dept.getDeptName(),dept.getDeptLoc(),dept.getDeptManager(),dept.getDeptNo());
		
	}

	/**
	 * 查询部门数量
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt() throws SQLException {
		String sql ="select count(0) as cnt from t_dept";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {
			
			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, new Object[]{});
		return list.get(0);
	}

	/**
	 * 按照分页模型查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<Dept> queryByPageModel(PageModel<Dept> pageModel)
			throws SQLException {
		List<Dept> deptList = null;
		String sql = "select t_deptno,t_deptname,t_deptloc,t_deptmanager,t_createtime from t_dept limit ?,?";
		deptList = JdbcTemplate.executeQuery(sql, new DeptMapper(), (pageModel.getPageNo()-1)*pageModel.getPageSize(),pageModel.getPageSize());

		//调用pageModel模型，将deptList存储到模型中
		pageModel.setDataList(deptList);
		return pageModel;
	}
}
