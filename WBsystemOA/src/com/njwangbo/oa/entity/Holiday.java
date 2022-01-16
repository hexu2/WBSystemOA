package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 请假实体类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Holiday  implements Serializable{
	/**
	 * 请假id
	 */
	private int id;
	
	/**
	 * 请假编号
	 */
	private String holidayNo;
	
	/**
	 * 请假申请人
	 */
	private String holidayUser;
	
	/**
	 * 请假类型
	 */
	private String holidayType;
	
	/**
	 * 请假事由
	 */
	private String holidayBz;
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 申请状态
	 */
	private String holidayStatus;
	
	/**
	 * 提交时间
	 */
	private Date createTime;
	
	

	public Holiday() {
		super();
	}



	public Holiday(int id, String holidayNo, String holidayUser,
			String holidayType, String holidayBz, String startTime,
			String endTime, String holidayStatus, Date createTime) {
		super();
		this.id = id;
		this.holidayNo = holidayNo;
		this.holidayUser = holidayUser;
		this.holidayType = holidayType;
		this.holidayBz = holidayBz;
		this.startTime = startTime;
		this.endTime = endTime;
		this.holidayStatus = holidayStatus;
		this.createTime = createTime;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getHolidayNo() {
		return holidayNo;
	}



	public void setHolidayNo(String holidayNo) {
		this.holidayNo = holidayNo;
	}



	public String getHolidayUser() {
		return holidayUser;
	}



	public void setHolidayUser(String holidayUser) {
		this.holidayUser = holidayUser;
	}



	public String getHolidayType() {
		return holidayType;
	}



	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}



	public String getHolidayBz() {
		return holidayBz;
	}



	public void setHolidayBz(String holidayBz) {
		this.holidayBz = holidayBz;
	}



	public String getStartTime() {
		return startTime;
	}



	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	public String getEndTime() {
		return endTime;
	}



	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	public String getHolidayStatus() {
		return holidayStatus;
	}



	public void setHolidayStatus(String holidayStatus) {
		this.holidayStatus = holidayStatus;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	
	
	

}
