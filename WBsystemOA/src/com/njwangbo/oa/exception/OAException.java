package com.njwangbo.oa.exception;

@SuppressWarnings("serial")
public class OAException extends Exception{
//	/**
//	 * 错误码
//	 */
//	private String code;
//	/**
//	 * 错误信息
//	 */
//	private String msg;
//	
//	public OAException() {
//		super();
//	}
//
//	public OAException(String code, String msg) {
//		super(msg);
//		this.code = code;
//		this.msg = msg;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getMsg() {
//		return msg;
//	}
//
//	public void setMsg(String msg) {
//		this.msg = msg;
//	}
	
	
	public OAException(String msg) {
		super(msg);
		
	}
	
	
	
	

}
