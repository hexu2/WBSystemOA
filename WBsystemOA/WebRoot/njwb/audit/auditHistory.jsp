<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>审批历史记录</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
		.deptInfo{
		font-size:15px;
		width: 700px;
		margin: 10px auto;
		border-collapse: collapse;
		}
		
	</style>
  </head>
  <body>
         	<h1 class="title">历史审批记录 </h1>
         	<table class="deptInfo">
         		<tr class="titleRow">
         			<td>tableID</td>
         			<td>节点ID</td>
         			<td>节点状态</td>
         			<td>审核流程</td>
         			<td>打开时间</td>
         			<td>结束时间</td>
         			<td>待处理人</td>
         			<td>已处理人</td>
         			<td>处理意见</td>
         		</tr>
         		
         		<c:forEach items="${historyList}" var="workNodeAction">
         			<tr>
         				<td>${workNodeAction.tableID}</td>
         				<td>${workNodeAction.nodeID}</td>
         				<td>${workNodeAction.statusName}</td>
         				<td>${workNodeAction.nodeName}</td>
         				<td><fmt:formatDate value="${workNodeAction.openTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
         				<td><fmt:formatDate value="${workNodeAction.closeTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
         				<td>${workNodeAction.waitingUsersName}</td>
         				<td>${workNodeAction.dealUserName}</td>
         				<td>${workNodeAction.dealAdvices}</td>
         			</tr>
         		</c:forEach>
         		
	   			<tr>
	   				<td colspan="9">
	   					<input type="button" value="返回" onclick="javascript:history.go(-1);" />
	   				</td>
	   			</tr>
         	</table>
  </body>
</html>
