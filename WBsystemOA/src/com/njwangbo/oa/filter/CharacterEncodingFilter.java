package com.njwangbo.oa.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;



/**
 * 字符编码过滤器
 * 
 * @author soft01
 * 
 */
public class CharacterEncodingFilter implements Filter {
	private String encoding;
	@Override
	public void destroy() {
		
	}

	/**
	 * 拦截所有以.do结尾的请求url
	 * 在未到达真正的.do请求之前，做编码
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//处理编码
		//分为get和post提交
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		//获取请求方式
		String method = request.getMethod();
		if(method.equalsIgnoreCase("post")){
			//
			request.setCharacterEncoding(this.encoding);
		}else{//get提交
			//原来的做法  每个值都需要先取出来---》getBytes()----》再重新构造新的字符串
			//如  String deptName = new String(request.getParameter("deptName").getBytes("iso-8859-1"),"utf-8")
			//  request.getParameterValues
			
			//既然字符集编码要通用，---》并不知道客户端传什么参数过来----》传统的做法不好使
			//  request .getParameter  getParameterValues
			//     这些都是HttpServletRequest中的方法
			//     如果我们自己定义一个HttpServletRequest的一个子类，子类重写父类的getParameter  getParameterValues
			       //在子类中自己处理乱码问题，那以后servlet中再调用request .getParameter，实际上调用的是子类的方法----》多态
			request = new NBRequest(request);
			
		}
		
		//请求放行
		chain.doFilter(request, response);
		
		
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("encoding");
	}

	/**
	 * HttpServletRequest的子类    使用内部类
	 * @author Administrator
	 *
	 */
	private class NBRequest  extends HttpServletRequestWrapper{
		private HttpServletRequest request;//拥有请求中的一切数据
		public NBRequest(HttpServletRequest request){//
			super(request);
			this.request = request;
		}
		
		@Override
		public String getParameter(String name) {
			String value = this.request.getParameter(name);
			if(null == value){
				return null;
			}else{
				//get方式的编码
				try {
					return new String(value.getBytes("iso-8859-1"),CharacterEncodingFilter.this.encoding);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
		@Override
		public String[] getParameterValues(String name) {
			String [] values = this.request.getParameterValues(name);
			if(null == values || values.length == 0){
				return null;
			}else{
				try {
					for(int i = 0; i < values.length; i++){
						String value = new String(values[i].getBytes("iso-8859-1"),CharacterEncodingFilter.this.encoding);
						values[i] = value;
					}
					return values;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}
	
}
