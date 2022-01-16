package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限标实体类
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class Permission  implements Serializable{
	/**
	 * 权限id
	 */
	private int id;
	
	/**
	 * 角色id
	 */
	private int roleId;
	
	/**
	 * 角色名
	 */
	private String roleName;
	
	/**
	 * 菜单id
	 */
	private int menuId;
	
	/**
	 * 菜单名
	 */
	private String menuName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	
	
	public Permission() {
		super();
	}



	public Permission(int id, int roleId, String roleName, int menuId,
			String menuName, Date createTime) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.roleName = roleName;
		this.menuId = menuId;
		this.menuName = menuName;
		this.createTime = createTime;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public int getMenuId() {
		return menuId;
	}



	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}



	public String getMenuName() {
		return menuName;
	}



	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
	
	

}
