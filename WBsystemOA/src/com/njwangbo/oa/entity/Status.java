package com.njwangbo.oa.entity;

import java.io.Serializable;

/**
 * 提交状态实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Status  implements Serializable{
	/**
	 * 状态标号1：草稿 2：提交
	 */
	private int id;
	
	/**
	 * id对应的值
	 */
	private String value;
	
	

	public Status() {
		super();
	}

	public Status(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
