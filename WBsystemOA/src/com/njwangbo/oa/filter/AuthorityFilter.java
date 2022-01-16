package com.njwangbo.oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njwangbo.oa.entity.User;


/**
 * 登录验证过滤器
 * @author Administrator
 *
 */
public class AuthorityFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//.....
		//服务端所有的资源都需要登录验证吗？
		// 淘宝  京东----付款结算才验证
		// CSDN  ----下载的时候才验证
		
		//只需要jsp和 .do结尾的需要验证
		   // login.jsp  loginServlet.do   codeServlet.do ,其他的jsp和.do需要验证
		     //  .js  .css  图片 不需要验证
		     
		  
		//如果不需要验证的资源  直接放行
		//如果需要验证的资源
		    //检查是否已经登录了
		          //如果登录了，直接放行
		          //如果没有登录，提示信息，强制登录
		
		
		//1.获取请求的url
		//http://127.0.0.1:8888/njwb_oa_12/njwb/dept/deptAdd.jsp
		//String url = request.getRequestURL().toString();
		//   /njwb_oa_12/njwb/dept/deptAdd.jsp
	    String uri = request.getRequestURI();
	   // System.out.println(url);
	   // System.out.println(uri);
	    
	    String contentRootUrl = request.getContextPath();//  /njwb_oa_12
		
	   if(uri.contains("login.jsp") || uri.contains("login.do") 
			   || uri.contains("getCertPic.do")||uri.contains("control.jsp")
			   || !(uri.endsWith("jsp") || uri.endsWith(".do"))){//不需要验证  //  
		   //放行
		   chain.doFilter(request, response);
		   
	   }else{//需要验证
		   
		   
		   User user = (User)request.getSession().getAttribute("user");
		   
		   /***********    测试方便        *************/
		   if(null != user){//登录了，直接放行
			  chain.doFilter(request, response); 
		   }else{//没有登录，强迫必须登录
			   
			   //如果现在访问的是    /njwb/dept/deptAdd.jsp
			   // ../prompt/control.jsp
			   //如果   /njwb/main.jsp    prompt/control.jsp
			   //只能使用绝对路径
			   //先得到content-root url
			   response.sendRedirect(contentRootUrl + "/" + "njwb/prompt/control.jsp");
		   }
		   
	   }
	    
		
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
