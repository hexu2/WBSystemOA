package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.util.PageModel;

/**
 * 部门数据操作接口
 * @author soft01
 *
 */
public interface DeptDao {
	/**
	 * 查询所有部门信息
	 * @return List<Dept> 所有部门集合
	 * @throws SQLException
	 */
	List<Dept> queryAll()throws SQLException;
	/**
	 * 根据id查询部门
	 * @param deptno
	 * @return Dept
	 * @throws SQLException
	 */
	Dept queryById(String deptNo) throws SQLException;
	/**
	 * 添加新的部门
	 * @param dept
	 * @throws SQLException
	 */
	void add(Dept dept) throws SQLException;
	/**
	 * 根据部门编号删除一个部门
	 * @param deptno
	 * @throws SQLException
	 */
	void deleteById(String deptNo) throws SQLException;
	/**
	 * 根据部门号码修改部门信息
	 * @param dept
	 * @throws SQLException
	 */
	void update(Dept dept)throws SQLException;
	
	
	/**
	 * 查询部门数量
	 * @return
	 * @throws SQLException
	 */
	int queryCnt()throws SQLException;
	
	/**
	 * 按照分页模型 查询
	 * @param pageModel service传递到dao时pageModel中肯定要有pageNo和pageSize
	 *  但是dataList没有值
	 * @return PageModel<Dept> pageModel经过dao的查询，将dept的集合数据存储到pageModel中的dataList
	 * @throws SQLException
	 */
	PageModel<Dept> queryByPageModel(PageModel<Dept> pageModel)throws SQLException;
}
