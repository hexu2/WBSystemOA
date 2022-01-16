package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工实体类
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class Employee  implements Serializable{
	
	/**
	 * 员工id
	 */
	private int id;
	/**
	 * 员工号
	 */
	private String empNo;
	
	/**
	 * 员工名
	 */
	private String empName;
	
	/**
	 * 员工所属部门编号
	 */
	private String empDeptNo;
	
	/**
	 * 员工性别 0：女，1：男
	 */
	private String sex;
	
	/**
	 * 员工学历
	 */
	private String education;
	
	/**
	 * 员工邮箱
	 */
	private String email;
	
	/**
	 * 员工联系方式
	 */
	private String phone;
	
	/**
	 * 员工入职时间
	 */
	private Date entryTime;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	

	public Employee() {
		super();
	}




	


	

	public Employee(int id, String empNo, String empName, String empDeptNo,
			String sex, String education, String email, String phone,
			Date entryTime, Date createTime) {
		super();
		this.id = id;
		this.empNo = empNo;
		this.empName = empName;
		this.empDeptNo = empDeptNo;
		this.sex = sex;
		this.education = education;
		this.email = email;
		this.phone = phone;
		this.entryTime = entryTime;
		this.createTime = createTime;
	}









	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmpNo() {
		return empNo;
	}



	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public String getEmpDeptNo() {
		return empDeptNo;
	}



	public void setEmpDeptNo(String empDeptNo) {
		this.empDeptNo = empDeptNo;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getEducation() {
		return education;
	}



	public void setEducation(String education) {
		this.education = education;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Date getEntryTime() {
		return entryTime;
	}



	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}



	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	
	
}
