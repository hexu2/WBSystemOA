package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作流实体
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class WorkFlow  implements Serializable{
	/**
	 * 工作流id
	 */
	private int id;
	/**
	 * 工作流名
	 */
	private String workName;
	/**
	 * 工作流表名
	 */
	private String tableName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	

	public WorkFlow() {
		super();
	}

	public WorkFlow(int id, String workName, String tableName, Date createTime) {
		super();
		this.id = id;
		this.workName = workName;
		this.tableName = tableName;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "WorkFlow [createTime=" + createTime + ", id=" + id
				+ ", tableName=" + tableName + ", workName=" + workName + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
