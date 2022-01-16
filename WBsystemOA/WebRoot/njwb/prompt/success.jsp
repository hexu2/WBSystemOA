<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>操作成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <body>
    <%--
                提示操作成功
    --%>
   
	<input type="hidden" value="${operator}" id="operator"/>
	<script type="text/javascript">
		var operator = document.getElementById("operator").value;
        switch(operator){
        
        <%-- ***********************部门***********************--%>
        	case "001":
        	           alert("部门添加成功");
        	           location.href = "queryAllDepts.do";
        	           break;
        	case "002":
        	           alert("部门删除成功");
        	           location.href = "queryAllDepts.do";
        	           break;
        	case "003":
        	           alert("部门修改成功");
        	           location.href = "queryAllDepts.do";
        	           break;
        	           
        	           
        <%-- **********************员工************************--%>
        	case "101":
        	           alert("员工添加成功");
        	           location.href = "queryAllEmps.do";
        	           break;
        	case "102":
        	           alert("员工删除成功");
        	           location.href = "queryAllEmps.do";
        	           break;
        	case "103":
        	           alert("员工修改成功");
        	           location.href = "queryAllEmps.do";
        	           break;
        	           
        	           
        <%-- **********************请假************************--%>
        	case "201":
        	           alert("请假记录添加成功");
        	           location.href = "queryAllHolidays.do";
        	           break;         
        	case "202":
        	           alert("请假记录删除成功");
        	           location.href = "queryAllHolidays.do";
        	           break;
        	case "203":
        	           alert("请假记录修改成功");
        	           location.href = "queryAllHolidays.do";
        	           break;

        <%-- **********************报销************************--%>
        	case "301":
 	           alert("报销记录添加成功");
 	           location.href = "queryBaoXiaos.do";
 	           break;         
 			case "302":
 	           alert("报销记录删除成功");
 	           location.href = "queryBaoXiaos.do";
 	           break;
 			case "303":
 	           alert("报销记录修改成功");
 	           location.href = "queryBaoXiaos.do";
 	           break;

 	    <%-- **********************账户************************--%>
        	case "401":
 	           alert("账户添加成功");
 	           location.href = "queryUsers.do";
 	           break;         
 			case "402":
 	           alert("账户删除成功");
 	           location.href = "queryUsers.do";
 	           break;
 			case "403":
 	           alert("账户修改成功");
 	           location.href = "queryUsers.do";
 	           break;
 			case "404":
 	           alert("账户密码重置成功,请重新登陆");
 	           parent.location.href = "njwb/login.jsp";
 	           break;
 	           
 	    <%-- **********************角色************************--%>
        	case "501":
 	           alert("角色添加成功");
 	           location.href = "queryRoles.do";
 	           break;         
 			case "502":
 	           alert("角色删除成功");
 	           location.href = "queryRoles.do";
 	           break;
 			case "503":
 	           alert("角色修改成功");
 	           location.href = "queryRoles.do";
 	           break;

 	    <%-- **********************权限************************--%>
        	case "601":
 	           alert("权限添加成功");
 	           location.href = "queryPermissions.do";
 	           break;         
 			case "602":
 	           alert("权限删除成功");
 	           location.href = "queryPermissions.do";
 	           break;
        	           
        }

        
        	           
     
          
	</script>
</body>
</html>
