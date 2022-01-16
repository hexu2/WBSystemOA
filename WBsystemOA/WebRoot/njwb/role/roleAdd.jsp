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
    
   <title>角色添加</title>
	
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
			$(":submit").click(function(){
				//基础数据校验
				var roleName = $.trim($("#roleName").val());
				if("" == roleName){
					alert("角色名称不能为空");
					return false;
				}else{
					return true;
				
				}
				
			
			});
		
		});
	</script>
</head>

  <body>
   	<form action="roleAdd.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			角色名称:
   			</td>
   			<td>
   				<input type = "text" name="roleName" id="roleName" />
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
