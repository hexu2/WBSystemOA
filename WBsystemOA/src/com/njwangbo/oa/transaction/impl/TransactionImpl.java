package com.njwangbo.oa.transaction.impl;

import java.sql.Connection;
import java.sql.SQLException;

import com.njwangbo.oa.transaction.Transaction;
import com.njwangbo.oa.util.JdbcUtil;



/**
 * 事物实现类
 * @author soft01
 *
 */
public class TransactionImpl implements Transaction{
	

	@Override
	public void begin() {
		//拿到Connection
		Connection conn =JdbcUtil.getConnection();
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit() {
		Connection conn = JdbcUtil.getConnection();
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close();//关闭Connection
		}
	}

	@Override
	public void rollback() {
		Connection conn = JdbcUtil.getConnection();
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close();//关闭Connection
		}
	}

}
