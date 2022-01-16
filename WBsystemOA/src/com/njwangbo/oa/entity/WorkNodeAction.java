package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作流动作实体
 * 
 * @author soft01
 * 
 */
@SuppressWarnings("serial")
public class WorkNodeAction  implements Serializable{
	/**
	 * 动作ID
	 */
	private int id;
	
	/**
	 * 节点ID
	 */
	private int nodeID;
	
	/**
	 * 节点名称
	 */
	private String nodeName;
	
	/**
	 * 
	 */
	private String tableID;
	
	/**
	 * 打开时间
	 */
	private Date openTime;
	
	/**
	 * 关闭时间
	 */
	private Date closeTime;
	
	/**
	 * 审批状态ID
	 */
	private int status;
	
	/**
	 * 审批状态名
	 */
	private String statusName;
	
	/**
	 * 待审核用户ID
	 */
	private String waitingUsers;
	
	/**
	 * 待审核用户名
	 */
	private String waitingUsersName;
	
	/**
	 * 处理人ID
	 */
	private String dealUser;
	
	/**
	 * 处理人姓名
	 */
	private String dealUserName;
	
	/**
	 * 处理意见
	 */
	private String dealAdvices;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	

	public WorkNodeAction() {
		super();
	}

	public WorkNodeAction(int id, int nodeID, String nodeName, String tableID,
			Date openTime, Date closeTime, int status, String statusName,
			String waitingUsers, String waitingUsersName, String dealUser,
			String dealUserName, String dealAdvices, Date createTime) {
		super();
		this.id = id;
		this.nodeID = nodeID;
		this.nodeName = nodeName;
		this.tableID = tableID;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.status = status;
		this.statusName = statusName;
		this.waitingUsers = waitingUsers;
		this.waitingUsersName = waitingUsersName;
		this.dealUser = dealUser;
		this.dealUserName = dealUserName;
		this.dealAdvices = dealAdvices;
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getTableID() {
		return tableID;
	}

	public void setTableID(String tableID) {
		this.tableID = tableID;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getWaitingUsers() {
		return waitingUsers;
	}

	public void setWaitingUsers(String waitingUsers) {
		this.waitingUsers = waitingUsers;
	}

	public String getWaitingUsersName() {
		return waitingUsersName;
	}

	public void setWaitingUsersName(String waitingUsersName) {
		this.waitingUsersName = waitingUsersName;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}

	public String getDealAdvices() {
		return dealAdvices;
	}

	public void setDealAdvices(String dealAdvices) {
		this.dealAdvices = dealAdvices;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "WorkNodeAction [closeTime=" + closeTime + ", createTime="
				+ createTime + ", dealAdvices=" + dealAdvices + ", dealUser="
				+ dealUser + ", dealUserName=" + dealUserName + ", id=" + id
				+ ", nodeID=" + nodeID + ", nodeName=" + nodeName
				+ ", openTime=" + openTime + ", status=" + status
				+ ", statusName=" + statusName + ", tableID=" + tableID
				+ ", waitingUsers=" + waitingUsers + ", waitingUsersName="
				+ waitingUsersName + "]";
	}
	
	
	
	
}
