package com.njwangbo.oa.service;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.util.PageModel;

/**
 * 请假业务逻辑接口层
 * @author Administrator
 *
 */
public interface BaoXiaoService {
	
	/**
	 * 查询所有报销记录=====
	 * @return List<Holiday> 所有报销记录的集合
	 * @throws OAException
	 */
	List<BaoXiao> queryAll() throws OAException;

	/**
	 * 添加报销记录 =====
	 * @param baoXiao
	 * @throws OAException
	 */
	void addBaoXiao(BaoXiao baoXiao) throws OAException;
	
	/**
	 * 根据报销编号删除一个部门========
	 * @param baoXiaoNo 报销编号
	 * @throws OAException 自定义异常
	 */
	void delteBaoXiao(String baoXiaoNo) throws OAException;

	/**
	 * 修改报销信息===========
	 * @param baoXiao 构造的新的报销记录
	 * @throws OAException 自定义异常
	 */
	void updateBaoXiao(BaoXiao baoXiao) throws OAException;
	
	/**
	 * 根据报销编号查询报销记录==========
	 * @param baoXiaoNo 报销编号
	 * @return baoXiao 一条报销记录
	 * @throws OAException
	 */
	BaoXiao selectBaoXiao(String baoXiaoNo) throws OAException;
	
	/**
	 * 分页查询报销记录=====
	 * @param pageNo 页号
	 * @param pageSize 页面大小
	 * @return List<baoXiao> 一次分页的集合
	 * @throws OAException
	 */
	List<BaoXiao> queryByPage(int pageNo,int pageSize) throws OAException;
	
	/**
	 * 按分页根据申请人分页模型查询报销记录=========
	 * @param pageNo 页号
	 * @param pageSize 页面大小
	 * @return PageModel<baoXiao> 
	 * @throws OAException
	 */
	PageModel<BaoXiao> queryByModel(int pageNo,int pageSize,String baoXiaoUser) throws OAException;

	/**
	 * 查询id最大的报销记录
	 * @return baoXiao id 最大
	 * @throws OAException
	 */
	BaoXiao queryBaoXiaoMaxId() throws OAException;

	/**
	 * 按照分页模型模糊查询
	 * @param baoXiaoUser 申请人
	 * @param baoXiaoType 申请类型
	 * @param baoXiaoStatus 申请状态
	 * @param pageModel 
	 * @return
	 * @throws SQLException
	 */
	public PageModel<BaoXiao> queryByPageModelForBtn(String baoXiaoUser, String baoXiaoType, String baoXiaoStatus, int pageNo,int pageSize) throws OAException;

	
	/**
	 * 根据用户名查询报销数量
	 * @return int 报销记录总数
	 * @throws SQLException
	 */
	int queryCnt(String baoXiaoUser) throws OAException;
}
