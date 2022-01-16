package com.njwangbo.oa.entity;

import java.io.Serializable;


/**
 * 子菜单实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class MenuSon  implements Serializable{
	private int id;
	
	private String hrefUrl;
	
	private String menuName;//人事管理
	

	public MenuSon() {
		super();
	}

	
	public MenuSon(int id, String menuName) {
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


	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	@Override
	public String toString() {
		return "MenuSon [hrefUrl=" + hrefUrl + ", id=" + id + ", menuName="
				+ menuName + "]";
	}

	
}
