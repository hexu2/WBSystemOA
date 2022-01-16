<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   <title>添加权限</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <script type="text/javascript" src="js/base.js"></script>
      <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 350px;
			margin: 20px auto;
			
			
		}
		
		#deptEditTable td{
			height: 40px;
		}
	</style>
	
	<script type="text/javascript">
		$(function(){
			//添加权限提交
			$(":submit").click(function(){
				var roleId = $.trim($("#roleId").val());
				var menuId = $.trim($("#menuId").val());
				if("" == roleName){
					alert("请选择角色");
					return false;
				}else if("" == menuId){
					alert("请选择菜单");
					return false;
				}else{
					return true;
				}
			});
		});
	</script>
</head>

  <body>
   	<form action="permissionAdd.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			角色:
   			</td>
   			<td>
   				<select name="roleId" id="roleId">
   				 <option value="">请选择</option>
   					<c:forEach items="${roleList}" var="role">
	   					<option value="${role.roleId}">${role.roleName}</option>
   					</c:forEach>
   				</select>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			菜单:
   			</td>
   			<td>
   				<select name="menuId" id="menuId">
   				 <option value="">请选择</option>
   					<c:forEach items="${menuList}" var="menu">
	   					<option value="${menu.id}">${menu.menuName}</option>
   					</c:forEach>
   				</select>
   			</td>
   		</tr>
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="添加"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
