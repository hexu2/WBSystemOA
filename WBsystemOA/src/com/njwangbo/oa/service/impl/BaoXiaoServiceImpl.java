package com.njwangbo.oa.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.BaoXiaoDao;
import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.entity.User;
import com.njwangbo.oa.exception.OAException;
import com.njwangbo.oa.factory.ApplicationContext;
import com.njwangbo.oa.service.BaoXiaoService;
import com.njwangbo.oa.service.UserService;
import com.njwangbo.oa.service.WorkFlowService;
import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.StringUtil;

public class BaoXiaoServiceImpl implements BaoXiaoService{
	private BaoXiaoDao baoXiaoDao;
	private Transaction tx;
	
	
	public void setBaoXiaoDao(BaoXiaoDao baoXiaoDao) {
		this.baoXiaoDao = baoXiaoDao;
	}

	public void setTx(Transaction tx) {
		this.tx = tx;
	}

	/**
	 * 添加请假记录
	 */
	@Override
	public void addBaoXiao(BaoXiao baoXiao) throws OAException {
		//基础数据校验
		//1，请假编号是否为空且是6位String
		if(StringUtil.isEmpty(baoXiao.getBaoXiaoUser())){//2，请假申请人不能为空
			throw new OAException("报销申请人不能为空");
		}else if(StringUtil.isEmpty(baoXiao.getBaoXiaoType())){//3.报销类型不能为空
			throw new OAException("报销类型不能为空");
		}else if(StringUtil.isEmpty(baoXiao.getBaoXiaoBz())){//4.报销事由不能为空
			throw new OAException("报销事由不能为空");
		}else if(StringUtil.isEmpty(String.valueOf(baoXiao.getBaoXiaoMoney()))){
			throw new OAException("报销金额不能为空");
		}

		try {
			//1.2 业务流程数据校验 报销编号是否重复  需要查数据库
			//根据报销编号查询部门信息
			BaoXiao baoXiaoFromDB = baoXiaoDao.queryById(baoXiao.getBaoXiaoNo());
			if(null == baoXiaoFromDB){
				tx.begin();
				baoXiaoDao.add(baoXiao);
				tx.commit();
				if("2".equals(baoXiao.getBaoXiaoStatus())){//提交申请
					//添加工作流
					WorkFlowService workFlowService = (WorkFlowService) ApplicationContext.getBean("workFlowService");
					workFlowService.addSatrtNode(201, baoXiao.getBaoXiaoNo());
					/**
					 * 根据用户名查找用户id
					 */
					UserService userService = (UserService) ApplicationContext.getBean("userService");
					User user = userService.queryByUserName(baoXiao.getBaoXiaoUser());
					workFlowService.nextNode(baoXiao.getBaoXiaoNo(), 2, String.valueOf(user.getId()),
							"工作流提交", "pass");
				}
				
			}else {
				throw new OAException("该报销编号已经存在");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}

	/**
	 * 根据报销编号删除一个部门
	 */
	@Override
	public void delteBaoXiao(String baoXiaoNo) throws OAException {
		
		try {
			tx.begin();
			baoXiaoDao.deleteById(baoXiaoNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
	}

	/**
	 * 查询所有报销记录
	 */
	@Override
	public List<BaoXiao> queryAll() throws OAException {
		List<BaoXiao> baoXiaoList = null;
		try {
			tx.begin();
			baoXiaoList = baoXiaoDao.queryAll();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return baoXiaoList;
	}

	/**
	 * 分页查询报销记录
	 */
	@Override
	public List<BaoXiao> queryByPage(int pageNo, int pageSize) throws OAException {
		List<BaoXiao> baoXiaoList = null;
		try {
			baoXiaoList = baoXiaoDao.queryByPage(pageNo, pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return baoXiaoList;
	}


	/**
	 * 根据报销编号查询部门
	 */
	@Override
	public BaoXiao selectBaoXiao(String baoXiaoNo) throws OAException {
		//1，报销编号是否为空且是6位String
		if(StringUtil.isEmpty(baoXiaoNo)){
			throw new OAException("报销编号不能为空");
		}
		BaoXiao baoXiao = null;
		try {
			tx.begin();
			baoXiao = baoXiaoDao.queryById(baoXiaoNo);
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return baoXiao;
	}

	/**
	 * 修改报销信息
	 */
	@Override
	public void updateBaoXiao(BaoXiao baoXiao) throws OAException {

		//基础数据校验
		//1，报销编号是否为空且是6位String
		if(StringUtil.isEmpty(baoXiao.getBaoXiaoType())){//3.报销类型不能为空
			throw new OAException("报销类型不能为空");
		}else if(StringUtil.isEmpty(baoXiao.getBaoXiaoBz())){//4.报销事由不能为空
			throw new OAException("报销事由不能为空");
		}else if(StringUtil.isEmpty(baoXiao.getBaoXiaoStatus())){
			throw new OAException("申请状态不能为空");
		}
		
		try {
			tx.begin();
			baoXiaoDao.update(baoXiao);
			tx.commit();
			if("2".equals(baoXiao.getBaoXiaoStatus())){//提交申请
				//添加工作流
				WorkFlowService workFlowService = (WorkFlowService) ApplicationContext.getBean("workFlowService");
				workFlowService.addSatrtNode(201, baoXiao.getBaoXiaoNo());
				/**
				 * 根据用户名查找用户id
				 */
				UserService userService = (UserService) ApplicationContext.getBean("userService");
				User user = userService.queryByUserName(baoXiao.getBaoXiaoUser());
				workFlowService.nextNode(baoXiao.getBaoXiaoNo(), 2, String.valueOf(user.getId()),
						"工作流提交", "pass");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			tx.rollback();
			throw new OAException("数据库异常");
		}
	}

	/**
	 * 按分页模型查询报销记录
	 */
	@Override
	public PageModel<BaoXiao> queryByModel(int pageNo, int pageSize,String baoXiaoUser)
			throws OAException {
		PageModel<BaoXiao> pageModel = new PageModel<BaoXiao>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			if(null != baoXiaoUser){
				pageModel = baoXiaoDao.queryByPageModel(baoXiaoUser,pageModel);
				pageModel.setCnt(queryCnt(baoXiaoUser));
			}else{
				pageModel = baoXiaoDao.queryByPageModelForAdmin(pageModel);
				pageModel.setCnt(queryCnt(baoXiaoUser));
			}
			if(queryCnt(baoXiaoUser) == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return pageModel;
	}

	/**
	 * 查询id最大的报销记录
	 */
	@Override
	public BaoXiao queryBaoXiaoMaxId() throws OAException {
		BaoXiao baoXiao = null;
		try {
			baoXiao = baoXiaoDao.queryBaoXiaoMaxId();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		return baoXiao;
	}


	/**
	 * 按照分页模型模糊查询
	 * @param baoXiaoUser 申请人
	 * @param baoXiaoType 申请类型
	 * @param baoXiaoStatus 申请状态
	 * @param pageModel 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public PageModel<BaoXiao> queryByPageModelForBtn(String baoXiaoUser,
			String baoXiaoType, String baoXiaoStatus,int pageNo,int pageSize) throws OAException {
		if(null == baoXiaoUser){
			baoXiaoUser = "";
		}else if(null == baoXiaoType){
			baoXiaoType = "";
		}else if(null == baoXiaoStatus){
			baoXiaoStatus = "";
		}
		PageModel<BaoXiao> pageModel = new PageModel<BaoXiao>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		
		try {
			pageModel = baoXiaoDao.queryByKeys(baoXiaoUser, baoXiaoType, baoXiaoStatus, pageModel);
			pageModel.setCnt(baoXiaoDao.queryCntAfterPage(baoXiaoUser, baoXiaoType, baoXiaoStatus));
			if(baoXiaoDao.queryCntAfterPage(baoXiaoUser, baoXiaoType, baoXiaoStatus) == 0){
				pageModel.setPageNo2(0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return pageModel;
	}

	/**
	 * 查询报销数量
	 * @return int 报销记录总数
	 * @throws SQLException
	 */
	@Override
	public int queryCnt(String baoXiaoUser) throws OAException {
		int cnt = 0;
		try {
			if(null != baoXiaoUser){
				cnt = baoXiaoDao.queryCnt(baoXiaoUser);
			}else{
				cnt = baoXiaoDao.queryCntForAdmin();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new OAException("数据库异常");
		}
		
		return cnt;
	}

}
