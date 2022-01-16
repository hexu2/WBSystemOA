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
  	<h3 id="h">报销明细</h3>
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			报销编号:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoNo" id="baoXiaoNo"  value="${baoXiao.baoXiaoNo}" readonly="readonly"/>
   				<input type = "hidden" name="id" id="id"  value="${baoXiao.id}" readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			申请人:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoUser" id="baoXiaoUser"  value="${baoXiao.baoXiaoUser}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			报销类型:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoType" id="baoXiaoType"  value="${baoXiao.baoXiaoType}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			报销金额:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoMoney" id="baoXiaoMoney"  value="${baoXiao.baoXiaoMoney}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			摘要:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoBz" id="baoXiaoBz" value="${baoXiao.baoXiaoBz}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			申请状态:
   			</td>
   			<td>
   				<c:if test="${baoXiao.baoXiaoStatus == 1}">
   					<input type = "text" name="baoXiaoStatus" id="baoXiaoStatus"  value="草稿" readonly="readonly" />
   				</c:if>
   				<c:if test="${baoXiao.baoXiaoStatus == 2}">
   					<input type = "text" name="baoXiaoStatus" id="baoXiaoStatus"  value="已提交" readonly="readonly" />
   				</c:if>
   			</td>
   		</tr>  	
  		
   		<tr>
   			<td>
   			创建时间:
   			</td>
   			<td>
   				<input type = "text" name="applyTime" id="applyTime"  value="${baoXiao.applyTime}" readonly="readonly"/>
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
