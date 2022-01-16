package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 角色实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Role  implements Serializable{
	/**
	 * 角色id
	 */
	private int roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	
	
	public Role() {
		super();
	}



	public Role(int roleId, String roleName, Date createTime) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.createTime = createTime;
	}



	public int getRoleId() {
		return roleId;
	}



	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}



	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
