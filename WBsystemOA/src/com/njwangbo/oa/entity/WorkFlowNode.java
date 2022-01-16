package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作流实体
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class WorkFlowNode  implements Serializable{
	/**
	 * 工作流节点配置id
	 */
	private int id;
	
	/**
	 * 工作流流类型id
	 */
	private int workID;
	
	/**
	 * 节点ID
	 */
	private int nodeID;
	
	/**
	 * 节点名称
	 */
	private String nodeName;
	
	/**
	 * 下一个节点
	 */
	private String nextNodeOper;
	
	/**
	 * 本节点待处理人
	 */
	private String waitingUser;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	

	public WorkFlowNode() {
		super();
	}
	
	


	
	public WorkFlowNode(int id, int workID, int nodeID, String nodeName,
			String nextNodeOper, String waitingUser, Date cerateTime) {
		super();
		this.id = id;
		this.workID = workID;
		this.nodeID = nodeID;
		this.nodeName = nodeName;
		this.nextNodeOper = nextNodeOper;
		this.waitingUser = waitingUser;
		this.createTime = cerateTime;
	}

	

	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public int getWorkID() {
		return workID;
	}





	public void setWorkID(int workID) {
		this.workID = workID;
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





	public String getNextNodeOper() {
		return nextNodeOper;
	}





	public void setNextNodeOper(String nextNodeOper) {
		this.nextNodeOper = nextNodeOper;
	}





	public String getWaitingUser() {
		return waitingUser;
	}





	public void setWaitingUser(String waitingUser) {
		this.waitingUser = waitingUser;
	}





	public Date getCreateTime() {
		return createTime;
	}





	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	/**
	 * 根据处理类型返回下一个节点ID,如果返回-1，则当前节点为结束节点
	 * 
	 * @param dealType
	 *            : pass/refuse
	 * @return
	 */
	public int getNextNodeID(String dealType) {
		// nextNodeOper=pass:103;refuse:105
		String[] operStrs = this.nextNodeOper.split(";");
		for (String string : operStrs) {
			// string= pass:103
			String[] strs = string.split(":");
			// pass:-1 Integer.valueOf('end');
			if (dealType.equals(strs[0])) {
				if ("end".equals(strs[1])) {
					return -1;
				} else {
					return Integer.valueOf(strs[1]);
				}
			}

		}
		return -1;
	}

	@Override
	public String toString() {
		return "WorkFolwNode [id=" + id + ", nextNodeOper=" + nextNodeOper
				+ ", nodeID=" + nodeID + ", nodeName=" + nodeName
				+ ", waitingUser=" + waitingUser + ", workID=" + workID + "]";
	}
	
	
}
