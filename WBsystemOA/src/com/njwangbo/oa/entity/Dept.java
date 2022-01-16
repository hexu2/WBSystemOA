package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门实体类
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class Dept  implements Serializable{
	/**
	 * 部门编号
	 */
	private String deptNo;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 部门位置
	 */
	private String deptLoc;
	/**
	 * 部门负责人
	 */
	private String deptManager;
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	public Dept() {
		super();
	}
	public Dept(String deptNo, String deptName, String deptLoc,
			String deptManager, Date createTime) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptLoc = deptLoc;
		this.deptManager = deptManager;
		this.createTime = createTime;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptLoc() {
		return deptLoc;
	}
	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}
	public String getDeptManager() {
		return deptManager;
	}
	public void setDeptManager(String deptManager) {
		this.deptManager = deptManager;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
