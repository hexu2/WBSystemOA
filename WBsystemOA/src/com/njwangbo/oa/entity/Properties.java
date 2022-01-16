package com.njwangbo.oa.entity;
/**
 * 配置表实体类
 * @author soft01
 *
 */
public class Properties {
	/**
	 * 类型标志
	 * 例如：1_holiday_type 代表请假类型
	 *      2_BX_type     代表报销类型
	 */
	private String type;
	
	/**
	 * 类型号
	 */
	private String keyId;
	
	/**
	 * 类型名
	 */
	private String pageValue;

	
	
	
	public Properties() {
		super();
	}

	
	
	public Properties(String type, String keyId, String pageValue) {
		super();
		this.type = type;
		this.keyId = keyId;
		this.pageValue = pageValue;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getPageValue() {
		return pageValue;
	}

	public void setPageValue(String pageValue) {
		this.pageValue = pageValue;
	}
	
	
}
