package com.njwangbo.oa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * @author soft01
 *
 */
@SuppressWarnings("serial")
public class User  implements Serializable{
	/**
	 * 用户id
	 */
	private int id;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 密码
	 */
	private String passWord;
	
	/**
	 * 确认密码
	 */
	private String confirmPwd;
	
	/**
	 * 员工编号
	 */
	private String employeeNo;
	
	/**
	 * 员工姓名
	 */
	private String employeeName;
	
	/**
	 * 用户状态
	 */
	private int userStatus;
	
	/**
	 * 用户角色
	 */
	private String userRole;
	
	
	/**
	 * 创建时间
	 */
	private Date createTime;


	

	public User() {
		super();
	}



	




	public User(int id, String userName, String passWord, String confirmPwd,
			String employeeNo, String employeeName, int userStatus,
			String userRole, Date createTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.confirmPwd = confirmPwd;
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.userStatus = userStatus;
		this.userRole = userRole;
		this.createTime = createTime;
	}








	public String getEmployeeName() {
		return employeeName;
	}








	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}








	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getEmployeeNo() {
		return employeeNo;
	}




	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}





	public String getPassWord() {
		return passWord;
	}




	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}




	public String getConfirmPwd() {
		return confirmPwd;
	}




	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}




	public String getUserRole() {
		return userRole;
	}




	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}




	public int getUserStatus() {
		return userStatus;
	}




	public void setUserStatus(int string) {
		this.userStatus = string;
	}




	public Date getCreateTime() {
		return createTime;
	}




	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	
	

}
