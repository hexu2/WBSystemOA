package com.njwangbo.oa.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.njwangbo.oa.dao.BaoXiaoDao;
import com.njwangbo.oa.entity.BaoXiao;
import com.njwangbo.oa.util.JdbcTemplate;
import com.njwangbo.oa.util.PageModel;
import com.njwangbo.oa.util.RowMapper;

public class BaoXiaoDaoImpl implements BaoXiaoDao {

	/**
	 * 添加请假记录+++
	 */
	@Override
	public void add(BaoXiao baoXiao) throws SQLException {
		String sql = "insert into t_bx(t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time) "
				+ "values(?,?,?,?,?,?,now())";
		JdbcTemplate.executeUpdate(sql, baoXiao.getBaoXiaoNo(), baoXiao
				.getBaoXiaoUser(), baoXiao.getBaoXiaoType(), baoXiao
				.getBaoXiaoMoney(), baoXiao.getBaoXiaoBz(), baoXiao
				.getBaoXiaoStatus());

	}

	/**
	 * 根据请假编号删除请假记录++
	 */
	@Override
	public void deleteById(String baoXiaoNo) throws SQLException {
		String sql = "delete from t_bx where t_bx_no = ?";
		JdbcTemplate.executeUpdate(sql, baoXiaoNo);
	}

	/**
	 * 查询所有请假记录+++
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaoXiao> queryAll() throws SQLException {
		String sql = "select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type'";
		List<BaoXiao> baoXiaoList = null;
		baoXiaoList = JdbcTemplate.executeQuery(sql, new BaoXiaoMapper(),
				new Object[] {});
		return baoXiaoList;
	}

	/**
	 * 根据请假编号查询请假记录++
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaoXiao queryById(String baoXiaoNo) throws SQLException {
		String sql = "select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' and e1.t_bx_no = ?";
		BaoXiao baoXiao = null;
		List<BaoXiao> baoXiaoList = JdbcTemplate.executeQuery(sql,
				new BaoXiaoMapper(), baoXiaoNo);
		if (baoXiaoList.size() > 0) {
			baoXiao = baoXiaoList.get(0);
		}
		return baoXiao;
	}

	/**
	 * 请假分页查询+++
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaoXiao> queryByPage(int pageNo, int pageSize)
			throws SQLException {
		String sql = "select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' " + "limit ?,?";
		List<BaoXiao> baoXiaoList = null;
		baoXiaoList = JdbcTemplate.executeQuery(sql, new BaoXiaoMapper(),
				(pageNo - 1) * pageSize, pageSize);
		return baoXiaoList;
	}

	/**
	 * 根据用户名查询请假记录总数++
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCnt(String baoXiaoUser) throws SQLException {
		String sql = "select count(1) as t_cnt from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' " + "and e1.t_bX_user = ?";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, baoXiaoUser);
		return list.get(0);
	}

	/**
	 * 根据请假编号修改一个请假记录+++++
	 */
	@Override
	public void update(BaoXiao baoXiao) throws SQLException {
		String sql = "update t_bx "
				+ "set t_bx_user=?,t_bx_type=?,t_bx_money=?,t_bx_bz=?,t_bx_status=?,t_apply_time= now() "
				+ "where t_bx_no = ?";
		JdbcTemplate.executeUpdate(sql, baoXiao.getBaoXiaoUser(), baoXiao
				.getBaoXiaoType(), baoXiao.getBaoXiaoMoney(), baoXiao
				.getBaoXiaoBz(), baoXiao.getBaoXiaoStatus(), baoXiao
				.getBaoXiaoNo());
	}

	/**
	 * 按照分页模型查询++
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<BaoXiao> queryByPageModel(String baoXiaoUser,
			PageModel<BaoXiao> pageModel) throws SQLException {
		List<BaoXiao> baoXiaoList = null;
		String sql = "select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' "
				+ "and e1.t_bx_user = ? "
				+ "limit ?,?";
		baoXiaoList = JdbcTemplate.executeQuery(sql, new BaoXiaoMapper(),
				baoXiaoUser, (pageModel.getPageNo() - 1)
						* pageModel.getPageSize(), pageModel.getPageSize());
		pageModel.setDataList(baoXiaoList);
		return pageModel;
	}

	/**
	 * 查询id最大的那条请假记录++
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BaoXiao queryBaoXiaoMaxId() throws SQLException {
		String sql = "select t_id,t_bx_no,t_bx_user,t_bx_type,t_bx_money,t_bx_bz,t_bx_status,t_apply_time from t_bx "
				+ "where t_id = (select max(t_id) from t_bx)";
		BaoXiao baoXiao = null;
		List<BaoXiao> baoXiaoList = JdbcTemplate.executeQuery(sql,
				new BaoXiaoMapper(), new Object[] {});
		if (baoXiaoList.size() > 0) {
			baoXiao = baoXiaoList.get(0);
		}
		return baoXiao;
	}

	/**
	 * 按照分页模型模糊查询+++
	 * 
	 * @param baoXiaoUser
	 *            申请人
	 * @param baoXiaoType
	 *            申请类型
	 * @param baoXiaoStatus
	 *            申请状态
	 * @param pageModel
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<BaoXiao> queryByKeys(String baoXiaoUser,
			String baoXiaoType, String baoXiaoStatus,
			PageModel<BaoXiao> pageModel) throws SQLException {
		if (null == baoXiaoUser) {
			baoXiaoUser = "";
		}
		if (null == baoXiaoType) {
			baoXiaoType = "";
		}
		if (null == baoXiaoStatus) {
			baoXiaoStatus = "";
		}

		// 拼接like后的内容
		String baoXiaoUser1 = "%" + baoXiaoUser + "%";
		String baoXiaoType1 = "%" + baoXiaoType + "%";
		String baoXiaoStatus1 = "%" + baoXiaoStatus + "%";

		List<BaoXiao> baoXiaoList = null;
		String sql = "select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' and e1.t_bx_user like ? and e2.t_pageValue like ? and e1.t_bx_status like ? "
				+ "limit ?,?";
		baoXiaoList = JdbcTemplate.executeQuery(sql, new BaoXiaoMapper(),
				baoXiaoUser1, baoXiaoType1, baoXiaoStatus1, (pageModel
						.getPageNo() - 1)
						* pageModel.getPageSize(), pageModel.getPageSize());

		pageModel.setDataList(baoXiaoList);

		return pageModel;
	}

	/**
	 * 模糊查询分页后的请假记录总数量
	 * 
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCntAfterPage(String baoXiaoUser, String baoXiaoType,
			String baoXiaoStatus) throws SQLException {
		if (null == baoXiaoUser) {
			baoXiaoUser = "";
		} else if (null == baoXiaoType) {
			baoXiaoType = "";
		} else if (null == baoXiaoStatus) {
			baoXiaoStatus = "";
		}
		String baoXiaoUser1 = "%" + baoXiaoUser + "%";
		String baoXiaoType1 = "%" + baoXiaoType + "%";
		String baoXiaoStatus1 = "%" + baoXiaoStatus + "%";

		String sql = "select count(1) as cnt "
				+ "from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' and e1.t_bx_user like ? and e2.t_pageValue like ? and e1.t_bx_status like ? ";

		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("cnt"));
			}
		}, baoXiaoUser1, baoXiaoType1, baoXiaoStatus1);

		return list.get(0);
	}

	/**
	 * 管理员登录时查询所有请假信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<BaoXiao> queryByPageModelForAdmin(
			PageModel<BaoXiao> pageModel) throws SQLException {
		List<BaoXiao> baoXiaoList = null;
		String sql = "select e1.t_id,e1.t_bx_no,e1.t_bx_user,e2.t_pageValue as t_bx_type,e1.t_bx_money,e1.t_bx_bz,e1.t_bx_status,e1.t_apply_time from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' " + "limit ?,?";
		baoXiaoList = JdbcTemplate.executeQuery(sql, new BaoXiaoMapper(),
				(pageModel.getPageNo() - 1) * pageModel.getPageSize(),
				pageModel.getPageSize());
		pageModel.setDataList(baoXiaoList);
		return pageModel;
	}

	/**
	 * 查询报销总数量（管理员登录时看到）
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int queryCntForAdmin() throws SQLException {
		String sql = "select count(1) as t_cnt from t_bx e1 "
				+ "inner join t_properties e2 "
				+ "on e1.t_bx_type = e2.t_keyId "
				+ "where e2.t_type = '2_BX_type' ";
		List<Integer> list = JdbcTemplate.executeQuery(sql, new RowMapper() {

			@Override
			public Object mapperObject(ResultSet rs) throws SQLException {
				return Integer.valueOf(rs.getInt("t_cnt"));
			}
		}, new Object[]{});
		return list.get(0);
	}

}
