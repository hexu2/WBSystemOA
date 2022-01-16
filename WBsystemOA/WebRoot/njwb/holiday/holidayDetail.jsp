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
    
    <title>请假明细</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

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
  	<h3 id="h">请假明细</h3>
  	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			请假ID:
   			</td>
   			<td>
   				<input type = "text" name="id" id="id"  value="${holiday.id}" readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			请假编号:
   			</td>
   			<td>
   				<input type = "text" name="empNo" id="empNo"  value="${holiday.holidayNo}" readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			申请人:
   			</td>
   			<td>
   				<input type = "text" name="empName" id="empName"  value="${holiday.holidayUser}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			请假类型:
   			</td>
   			<td>
   				<input type = "text" name="sex" id="sex"  value="${holiday.holidayType}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			请假事由:
   			</td>
   			<td>
   				<input type = "text" name="education" id="education" value="${holiday.holidayBz}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			开始时间:
   			</td>
   			<td>
   				<input type = "text" name="email" id="email"  value="${holiday.startTime}" readonly="readonly" />
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			结束时间:
   			</td>
   			<td>
   				<input type = "text" name="phone" id="phone"  value="${holiday.endTime}" readonly="readonly" />
   			</td>
   		</tr>  		

   		<tr>
   			<td>
   			申请状态:
   			</td>
   			<td>
   				<input type = "text" name="entryTime" id="entryTime"  value="${holiday.holidayStatus}" readonly="readonly" />
   			</td>
   		</tr>
  		
   		<tr>
   			<td>
   			创建时间:
   			</td>
   			<td>
   				<input type = "text" name="createTime" id="createTime"  value="${holiday.createTime}" readonly="readonly"/>
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
