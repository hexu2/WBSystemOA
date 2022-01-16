package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 报销实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class BaoXiao  implements Serializable{
	/**
	 * 报销id
	 */
	private int id;
	
	/**
	 * 报销编号
	 */
	private String baoXiaoNo;
	
	/**
	 * 报销申请人
	 */
	private String baoXiaoUser;
	
	/**
	 * 报销类型
	 */
	private String baoXiaoType;
	
	/**
	 * 报销金额
	 */
	private double baoXiaoMoney;
	
	/**
	 * 报销事由
	 */
	private String baoXiaoBz;
	
	/**
	 * 申请状态
	 */
	private String baoXiaoStatus;
	
	/**
	 * 申请时间
	 */
	private Date applyTime;
	

	public BaoXiao() {
		super();
	}

	public BaoXiao(int id, String baoXiaoNo, String baoXiaoUser,
			String baoXiaoType, double baoXiaoMoney, String baoXiaoBz,
			String baoXiaoStatus, Date applyTime) {
		super();
		this.id = id;
		this.baoXiaoNo = baoXiaoNo;
		this.baoXiaoUser = baoXiaoUser;
		this.baoXiaoType = baoXiaoType;
		this.baoXiaoMoney = baoXiaoMoney;
		this.baoXiaoBz = baoXiaoBz;
		this.baoXiaoStatus = baoXiaoStatus;
		this.applyTime = applyTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBaoXiaoNo() {
		return baoXiaoNo;
	}

	public void setBaoXiaoNo(String baoXiaoNo) {
		this.baoXiaoNo = baoXiaoNo;
	}

	public String getBaoXiaoUser() {
		return baoXiaoUser;
	}

	public void setBaoXiaoUser(String baoXiaoUser) {
		this.baoXiaoUser = baoXiaoUser;
	}

	public String getBaoXiaoType() {
		return baoXiaoType;
	}

	public void setBaoXiaoType(String baoXiaoType) {
		this.baoXiaoType = baoXiaoType;
	}

	public double getBaoXiaoMoney() {
		return baoXiaoMoney;
	}

	public void setBaoXiaoMoney(double baoXiaoMoney) {
		this.baoXiaoMoney = baoXiaoMoney;
	}

	public String getBaoXiaoBz() {
		return baoXiaoBz;
	}

	public void setBaoXiaoBz(String baoXiaoBz) {
		this.baoXiaoBz = baoXiaoBz;
	}

	public String getBaoXiaoStatus() {
		return baoXiaoStatus;
	}

	public void setBaoXiaoStatus(String baoXiaoStatus) {
		this.baoXiaoStatus = baoXiaoStatus;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	

	
}
