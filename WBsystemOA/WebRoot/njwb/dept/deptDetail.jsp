<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'deptDetail.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		#h{
			text-align: center;
		}
		#deptEditTable td{
			height: 40px;
		}
	</style>
  </head>
  
  <body>
  	<h1 id="h">部门明细</h1>
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			部门编号:
   			</td>
   			<td>
   				<input type = "text" name="deptNo" id="deptNo"  value="${dept.deptNo} " readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			部门名称:
   			</td>
   			<td>
   				<input type = "text" name="deptName" id="deptName"  value="${dept.deptName} " readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td>
   				<input type = "text" name="deptLoc" id="deptLoc" value="${dept.deptLoc }" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td>
   				<input type = "text" name="deptManager" id="deptManager"  value="${dept.deptManager }" readonly="readonly" />
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			创建时间:
   			</td>
   			<td>
   				<input type = "text" name="createtime" id="createtime"  value="${dept.createTime }" readonly="readonly"/>
   			</td>
   		</tr>  	
   		
   		<tr>
   			<td colspan="2">
   				<input type="button" value="返回" onclick="javascript:history.go(-1);" />
   			</td>

   		</tr>
   	</table>

  </body>
</html>
