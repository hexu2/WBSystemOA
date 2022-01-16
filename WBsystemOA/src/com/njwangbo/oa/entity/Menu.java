package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 主菜单实体类
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class Menu implements Serializable{
	/**
	 * id
	 */
	private int id;
	
	/**
	 * 提交url
	 */
	private String hrefUrl;
	
	/**
	 * 主菜单名称
	 */
	private String menuName;//人事管理
	
	/**
	 * 父亲id
	 */
	private String parentID;
	
	/**
	 * 子菜单集合
	 */
	private List<Menu> sonMenuList;//部门、员工、请假
	
	/**
	 * 创建时间
	 */
	private Date  createTime;

	public Menu() {
		super();
	}

	public Menu(int id, String menuName) {
		super();
		this.id = id;
		this.menuName = menuName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	
	public List<Menu> getSonMenuList() {
		return sonMenuList;
	}

	public void setSonMenuList(List<Menu> sonMenuList) {
		this.sonMenuList = sonMenuList;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}
	
	

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}


	@Override
	public String toString() {
		return "Menu [createTime=" + createTime + ", hrefUrl=" + hrefUrl
				+ ", id=" + id + ", menuName=" + menuName + ", parentID="
				+ parentID + ", sonMenuList=" + sonMenuList + "]";
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
}
