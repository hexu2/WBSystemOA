<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审批页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/main.css">
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
  <style type="text/css">
  #auditTable{
			border:2px solid gray;
			border-collapse: collapse;
			margin: 50px auto;			
			width: 400px;
			height: 300px;
		}
  </style>

  
  <script type="text/javascript">
  
  	//页面初始化
  	function formSubmit(type)
  	{
  		$("#dealType").val(type);
  		var workID=$("#workID").val();
  		var tableID=$("#tableID").val();
  		var dealAdvices=$("#dealAdvices").val();
  		location.href = "audit.do?dealType="+type+"&workID="+workID+"&dealAdvices="+dealAdvices+"&tableID="+tableID;
  	}
  </script>
  
  </head>
  
  <body>
  <h2 class="title" align="center" >审批</h2>
   	<form id="auditForm" action="audit.do" method="post">
   	<table id="auditTable">
   		<tr>
   			<td width="80px;">
   				审批流程:
   			</td>
   			<td width="200px;">
   				${workName}
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   				审核节点:
   			</td>
   			<td>
   				${nodeName}
   			</td>
   		</tr>  

   		<tr>
   			<td>
   				TableID:
   			</td>
   			<td>
   				<input type="text" id="tableID" name="tableID" value="${tableID}" readonly="readonly"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   				WorkID:
   			</td>
   			<td>
   				<input type="text" id="workID" name="workID" value="${workID}" readonly="readonly"/>
   			</td>
   		</tr>  
   		


   		<tr>
   			<td>
   			审核意见:
   			</td>
   			<td>
   			<textarea id="dealAdvices" rows="5" cols="30px" name="dealAdvices"></textarea>
   			</td>
   			
   		</tr>  

   		<tr>
   			<td colspan="2" align="center">
   				<input type = "button" value="通过" id="draft" onclick="formSubmit('pass')"/>&nbsp;&nbsp;
   				<c:if test="${nodeID != 104 and nodeID != 204}">
   					<input type = "button" value="拒绝" id="submit" onclick="formSubmit('refuse')"/>&nbsp;&nbsp;
   				</c:if>
   				<input type="button" value="返回" onclick="javascript:history.go(-1);" />
   				<input type="hidden" id="dealType"  name="dealType" >
   				<input type="hidden" id="waitingUser"  name="waitingUser" value="${waitingUser}" >
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
  </body>
</html>
