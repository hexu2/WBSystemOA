package com.njwangbo.oa.service;

import java.util.List;

import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 部门业务逻辑层接口
 * @author soft01
 *
 */
public interface DeptService {

	/**
	 * 添加新部
	 * @param dept 构造的新部门
	 * @throws OAException 自定义异常
	 */
	void addDept(Dept dept) throws OAException;
	
	/**
	 * 根据部门编号删除一个部门
	 * @param deptNo 部门编号
	 * @throws OAException 自定义异常
	 */
	void delteDept(String deptNo) throws OAException;
	
	/**
	 * 修改部门信息
	 * @param dept 构造的新部门
	 * @throws OAException 自定义异常
	 */
	void updateDept(Dept dept) throws OAException;
	
	/**
	 * 根据部门号查询部门
	 * @param deptNo 部门编号
	 * @return Dept 一条部门记录
	 * @throws OAException
	 */
	Dept selectDept(String deptNo) throws OAException;

	/**
	 * 查询部门总数
	 * @return
	 * @throws OAException
	 */
	int queryCnt() throws OAException;
	
	/**
	 * 按照模型分页查询数据
	 * @param pageNo 当前页
	 * @param pageSize 每页显示的记录条数
	 * @return  PageModel<Dept> 一个一页的分页模型集合
	 * @throws OAException
	 */
	PageModel<Dept> queryByPageModel(int pageNo,int pageSize) throws OAException;
	
	
	
	
	/**
	 * 查询所有部门信息
	 * @return List<Dept>部门集合
	 * @throws OAException
	 */
	List<Dept> queryAll() throws OAException;

	/**
	 * 根据部门编号查询部门
	 * @param deptNo
	 * @return
	 */
	Dept queryById(String deptNo) throws OAException;
	
	
	
}
