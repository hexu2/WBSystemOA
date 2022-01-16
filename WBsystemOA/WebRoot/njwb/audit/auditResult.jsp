<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>审批结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->

  </head>
  
  <body>
  	
     <input type="hidden" value="${operator}" id="operator"/>
     <input type="hidden" value="${workID}" id="workID"/>
     
     <script type="text/javascript">
        var operator = document.getElementById("operator").value;
        var workID = document.getElementById("workID").value;
        //审批状态
        switch(operator){
        	case "1000":
        	           alert("审批成功！");
        	           break;
        	case "9999":
        	           alert("审批失败");
        	           break;
        }
        
        //跳转页面
        switch(workID){
        	case "1":
        	           location.href = "queryAllHolidays.do";
        	           break;
        	case "2":
        	           location.href = "queryBaoXiaos.do";
        	           break;
        }
     </script>
  </body>
</html>
