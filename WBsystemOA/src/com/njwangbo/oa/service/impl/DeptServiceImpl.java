package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.DeptDao;
import com.njwangbo.oa.entity.Dept;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.service.DeptService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;
/**
 * 部门业务逻辑接口实现类
 * @author soft01
 *
 */
public class DeptServiceImpl implements DeptService{
	private DeptDao deptDao;
	private Transaction tx;
	
	
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	/**
	 * 添加新部
	 */
	@Override
	public void addDept(Dept dept) throws OAException {
		//基础数据校验
		//1，部门号是否为空且是5位String
		if(StringUtil.isEmpty(dept.getDeptNo())){
			throw new OAException("部门号不能为空");
		}else if(dept.getDeptNo().trim().length() != 5){
			
			throw new OAException("部门号长度必须为5");
			
		}
		//2，部门名是否为空
		if(StringUtil.isEmpty(dept.getDeptName())){
			
			throw new OAException("部门名称不能为空");
			
		}		
		//3，部门位置是否为空
		if(StringUtil.isEmpty(dept.getDeptLoc())){
			
			throw new OAException("部门位置不能为空");
			
		}	
		//4，部门负责人是否为空
		if(StringUtil.isEmpty(dept.getDeptManager())){
			
			throw new OAException("部门负责人不能为空");
			
		}	
		try {
			//1.2 业务流程数据校验 部门编号是否重复  需要查数据库
			//根据部门编号查询部门信息
			Dept deptFromDB = deptDao.queryById(dept.getDeptNo());
			if(null == deptFromDB){
				tx.begin();
				deptDao.add(dept);
				tx.commit();
			}else {
				throw new OAException("部门编号已经存在");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
	}

	/**
	 * 根据部门编号删除一个部门
	 */
	@Override
	public void delteDept(String deptNo) throws OAException {
		try {
			tx.begin();
			deptDao.deleteById(deptNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	/**
	 * 查询所有部门信息
	 */
	@Override
	public List<Dept> queryAll() throws OAException {
		List<Dept> deptList = null;
		try {
			tx.begin();
			deptList = deptDao.queryAll();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return deptList;
	}

	/**
	 * 根据部门号查询部门
	 */
	@Override
	public Dept selectDept(String deptNo) throws OAException {
		//基础数据校验
		//1，部门号是否为空且是5位String
		if(StringUtil.isEmpty(deptNo) ){
			throw new OAException("部门号是否为空且是5位String");
		}
		Dept dept = null;
		try {
			tx.begin();
			dept = deptDao.queryById(deptNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return dept;
	}

	/**
	 * 修改部门信息
	 */
	@Override
	public void updateDept(Dept dept) throws OAException {
		//基础数据校验
		//1，部门号是否为空且是5位String
		if(StringUtil.isEmpty(dept.getDeptNo())){
			throw new OAException("部门号不能为空");
		}else if(dept.getDeptNo().trim().length() != 5){
			
			throw new OAException("部门号长度必须为5");
		}
		//2，部门名是否为空
		if(StringUtil.isEmpty(dept.getDeptName())){
			throw new OAException("部门名称不能为空");
		}		
		//3，部门位置是否为空
		if(StringUtil.isEmpty(dept.getDeptLoc())){
			throw new OAException("部门位置不能为空");
		}	
		//4，部门负责人是否为空
		if(StringUtil.isEmpty(dept.getDeptManager())){
			throw new OAException("部门负责人不能为空");
		}	
		try {
			tx.begin();
			deptDao.update(dept);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
		
	}

	//查询部门总数
	@Override
	public int queryCnt() throws OAException {
		int cnt = 0;
		try {
			cnt = deptDao.queryCnt();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return cnt;
	}

	/**
	 * 按照分页模型查询数据
	 */
	@Override
	public PageModel<Dept> queryByPageModel(int pageNo, int pageSize)
			throws OAException {
		//构造一个PageModel模型
		PageModel<Dept> pageModel = new PageModel<Dept>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = deptDao.queryByPageModel(pageModel);
			pageModel.setCnt(deptDao.queryCnt());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return pageModel;
	}

	//根据部门编号查询部门
	@Override
	public Dept queryById(String deptNo) throws OAException {
		Dept dept = null;
		
		try {
			dept = deptDao.queryById(deptNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return dept;
	}
 
}
