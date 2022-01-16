<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.njwangbo.oa.entity.User"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统主页</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
 	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
 	
   <script type="text/javascript">
   $(function(){
    		$("span").click(function(){
    		    //$(this) 代表当前的span,找下一个class="hide"兄弟节点
    			$(this).next(".hide").slideToggle(500);
    		});
    		
    		//系统退出
    		$("#menu_15").click(function(){
    			
		      	if(confirm("是否确定退出？")){
		      		parent.location.href="quit.do";
				}else{
					parent.location.href="njwb/main.jsp";
				}
    		});
    		
    	});

   
   </script>
  </head>
  <body>
  	<div id = "mainDiv">
	  	<div id = "header">
	    	<div id = "logoDiv" class="lft">
	    		南京网博教育集团
	    	</div>
	    	<div id = "userDiv" class="rft">
	    		${userName}
	    	</div>
	    </div>
	    <div id = "welcomeDiv">
	    	欢迎使用网博管理系统
	    </div>
	    
	    
	    <div id = "contentDiv">
	    	<div id = "content-left" class="lft">
	    		<%-- 动态生成菜单 --%>
	    		<ul>
	    			<c:forEach items="${menuList}" var="menu">
	    				<li class="menu">
		    				<span>${menu.menuName}</span>
		    				<ul class="hide">
			    				<c:forEach items="${menu.sonMenuList}" var="sonMenu">
				    				<li class="menu-sub"><a href="${sonMenu.hrefUrl}"  id="menu_${sonMenu.id}"   target="contentPage">${sonMenu.menuName}</a></li>
			    				</c:forEach>
		    				</ul>
	    				
	    				</li>
	    			</c:forEach>
	    			 
	    		
	    		 <%--
	    			<li class="menu">
	    				<span>人事管理</span>
	    				<ul class="hide">
	    					<li class="menu-sub" ><a href="queryAllDepts.do"  target="contentPage">部门管理</a></li>
	    					<li class="menu-sub"><a href="queryAllEmps.do"  target="contentPage">员工管理</a></li>
	    					<li class="menu-sub"><a href="queryAllHolidays.do"  target="contentPage">请假管理</a></li>
	    				</ul>
	    			
	    			</li>
	    			
	    			<li class="menu">
	    				<span>教务管理</span>
	    				<ul class="hide" >
	    					<li class="menu-sub">班级管理</li>
	    					<li class="menu-sub">学生管理</li>
	    				</ul>
	    			
	    			</li>	    
	    			
	    			<li class="menu">
	    				<span>财务管理</span>
	    				<ul class="hide" >
	    					<li class="menu-sub"><a href="queryBaoXiaos.do"  target="contentPage">报销管理</a></li>
	    				</ul>
	    			
	    			</li>
	    						
	    		    <li class="menu">
	    				<span>系统管理</span>
	    				<ul class="hide">
	    					<li class="menu-sub"><a href="queryUsers.do"  target="contentPage">账户管理</a></li>
	    					<li class="menu-sub"><a href="njwb/resetpwd/resetPwd.jsp"  id="deptManager"  target="contentPage" >密码重置</a></li>
	    					<li class="menu-sub"><a href="queryRoles.do"  id="deptManager"  target="contentPage" >角色管理</a></li>
	    					<li class="menu-sub"><a href="queryPermissions.do"  id="deptManager"  target="contentPage" >权限管理</a></li>
	    					<li class="menu-sub"><a href="njwb/quit/quit.jsp"  id="quit"  >系统退出</a></li>
	    				</ul>
	    			</li>
	    		--%>
	    		</ul>
	    	</div>
	    	
	    	<div id = "content-right" class="rft">
	    		<iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
	    		</iframe>
	    	</div>
	    </div>
	    
	    <div id = "footer">
	    	&copy;版权归属南京网博江北总部
	    </div>
  	</div>
  </body>
</html>
