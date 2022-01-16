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
    
    <title>员工信息详情页</title>
   
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
  	<h3 id="h">部门明细</h3>
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工ID:
   			</td>
   			<td>
   				<input type = "text" name="id" id="id"  value="${employee.id} " readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input type = "text" name="empNo" id="empNo"  value="${employee.empNo} " readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			员工名:
   			</td>
   			<td>
   				<input type = "text" name="empName" id="empName"  value="${employee.empName} " readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
   				<input type = "text" name="sex" id="sex"  value="${employee.sex} " readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			学历:
   			</td>
   			<td>
   				<input type = "text" name="education" id="education" value="${employee.education}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			邮箱:
   			</td>
   			<td>
   				<input type = "text" name="email" id="email"  value="${employee.email}" readonly="readonly" />
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			联系方式:
   			</td>
   			<td>
   				<input type = "text" name="phone" id="phone"  value="${employee.phone}" readonly="readonly" />
   			</td>
   		</tr>  		

   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td>
   				<input type = "text" name="entryTime" id="entryTime"  value="${employee.entryTime}" readonly="readonly" />
   			</td>
   		</tr>  	
  		
   		<tr>
   			<td>
   			创建时间:
   			</td>
   			<td>
   				<input type = "text" name="createTime" id="createTime"  value="${employee.createTime}" readonly="readonly"/>
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
