package com.njwangbo.oa.dao;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.util.PageModel;


/**
 * 请假表数据操作接口
 * @author Administrator
 *
 */
public interface BaoXiaoDao {
	
	/**
	 * 查询所有请假记录-------------》已测试通过
	 * @return List<Holiday> 所有请假记录集合
	 * @throws SQLException
	 */
	List<BaoXiao> queryAll()throws SQLException;
	
	/**
	 * 根据请假编号查询请假记录-------------》已测试通过
	 * @param BaoXiaoNo
	 * @return
	 * @throws SQLException
	 */
	BaoXiao queryById(String baoXiaoNo) throws SQLException;

	/**
	 * 添加请假记录
	 * @param BaoXiao
	 * @throws SQLException
	 */
	void add(BaoXiao baoXiao) throws SQLException;
	
	/**
	 * 根据请假编号删除一个请假
	 * @param BaoXiaoNo
	 * @throws SQLException
	 */
	void deleteById(String baoXiaoNo) throws SQLException;
	/**
	 * 根据请假编号修改请假信息
	 * @param BaoXiao
	 * @throws SQLException
	 */
	void update(BaoXiao baoXiao)throws SQLException;
	

	/**
	 * 根据用户名查询请假条数
	 * @param BaoXiaoUser 用户名
	 * @return
	 * @throws SQLException
	 */
	int queryCnt(String baoXiaoUser) throws SQLException;
	
	/**
	 * 查询id最大的那条请假记录
	 * @return BaoXiao id最大
	 * @throws SQLException
	 */
	BaoXiao queryBaoXiaoMaxId() throws SQLException;
	
	
	/**
	 * 分页查询-------------》已测试通过
	 * @param pageNo 当前页码
	 * @param pageSize 每页面显示的大小
	 * @return List<BaoXiao> 返回当前页中的数据集合
	 * @throws SQLException
	 */
	List<BaoXiao> queryByPage(int pageNo,int pageSize) throws SQLException;
	

	/**
	 * 根据用户名分页查询请假记录
	 * @param BaoXiaoUser
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	PageModel<BaoXiao> queryByPageModel(String baoXiaoUser,PageModel<BaoXiao> pageModel) throws SQLException;
	
	/**
	 * 按照分页模型模糊查询
	 * @param baoXiaoUser 申请人
	 * @param baoXiaoType 申请类型
	 * @param baoXiaoStatus 申请状态
	 * @param pageModel 
	 * @return
	 * @throws SQLException
	 */
	public PageModel<BaoXiao> queryByKeys(String baoXiaoUser, String baoXiaoType, String baoXiaoStatus, PageModel<BaoXiao> pageModel) throws SQLException;

	/**
	 * 模糊查询分页后的请假记录总数量
	 * @return
	 * @throws SQLException
	 */
	int queryCntAfterPage(String baoXiaoUser, String baoXiaoType, String baoXiaoStatus) throws SQLException;

	/**
	 * 管理员登录时查询所有请假信息
	 * @param pageModel
	 * @return
	 */
	PageModel<BaoXiao> queryByPageModelForAdmin(PageModel<BaoXiao> pageModel) throws SQLException;

	/**
	 * 查询总报销数量（管理员登录时候）
	 * @return
	 */
	int queryCntForAdmin() throws SQLException;
}
