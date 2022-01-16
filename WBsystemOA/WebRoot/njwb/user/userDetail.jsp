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
    
    <title>My JSP 'holidayDetail.jsp' starting page</title>
   
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
  	<h3 id="h">账户明细</h3>
   	<table id = "deptEditTable">
   	
   		<tr>
   			<td>
   			账号ID：
   			</td>
   			<td>
   				<input name="userName" id="userName" value="${user.id}" readonly="readonly"/>
   			</td>
   		</tr>

   		<tr>
   			<td>
   			账号：
   			</td>
   			<td>
   				<input name="userName" id="userName" value="${user.userName}" readonly="readonly"/>
   			</td>
   		</tr>

   		
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
				<input name="userName" id="userName" value="${user.employeeNo}" readonly="readonly"/>
   			</td>
   		</tr>  


   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td>
				<input name="userName" id="userName" value="${user.employeeName}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td>
				<c:if test="${user.userStatus == 1}"><input value="正常" readonly="readonly"/> </c:if>
				<c:if test="${user.userStatus == 2}"><input value="已注销" readonly="readonly"/> </c:if>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			角色:
   			</td>
   			<td>
				<input name="userRole" id="userRole" value="${user.userRole}" readonly="readonly"/>
   			</td>
   		</tr>  
   	
   		<tr>
   			<td>
   			创建时间:
   			</td>
   			<td>
				<input name="createTime" id="createTime" value="${user.createTime}" readonly="readonly"/>
   			</td>
   		</tr>   	
   		
   		<tr>
   			<td colspan="2">
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>


  </body>
</html>
