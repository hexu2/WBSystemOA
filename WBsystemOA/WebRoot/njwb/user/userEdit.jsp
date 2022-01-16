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
    
    <title>My JSP 'userEdit.jsp' starting page</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/base.js"></script>
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
			var userName = $.trim($("#userName").val());
			var oldPwd = $.trim($("#oldPwd").val());
			var inputOldPwd = $.trim($("#inputOldPwd").val());
			var passWord = $.trim($("#passWord").val());
			var confirmPwd = $.trim($("#confirmPwd").val());
			var employeeNo = $.trim($("#employeeNo").val());
			var userStatus = $.trim($("#userStatus").val());
			var userRole = $.trim($("#userRole").val());
			
			if( "" == userName){
				alert("账号不能为空！");
				return false;
			}else if("" == userStatus){
				alert("账户状态不能为空！");
				return false;
			}else if("" == userRole){
				alert("账户角色不能为空！");
				return false;
			}else{
				return true;
			}
		
		});

		$("#employeeNo").mouseout(function(){
			var employeeNo = $.trim($("#employeeNo").val());
			if(""!=employeeNo){
				$.ajax({
					type:"GET",
  					url:"queryByEmpNo.do?employeeNo=" + employeeNo,
  					dataType:"json",
  					success:function(empStr){
  						 $("#empName").attr("value",empStr.empName);
  					}
				});
			}
			

		});
	
	});
	 </script>
  </head>
  <body>
  	<form action="userUpdate.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			账号：
   			</td>
   			<td>
   				<input type="hidden" name="userId" id="userId" value="${user.id}" readonly="readonly" />
   				<input name="userName" id="userName" value="${user.userName}" readonly="readonly"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<select name="employeeNo" id="employeeNo">
   					<c:forEach items="${employeeList}" var="employee">
   						<c:choose>
   							<c:when test="${user.employeeNo == employee.empNo}">
   								<option selected='selected'>${employee.empNo}</option>
   							</c:when>
	   						<c:otherwise>
	   							<option >${employee.empNo}</option>
	   						</c:otherwise>
   						</c:choose>

   					</c:forEach>
   				</select>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			员工名:
   			</td>
   			<td>
   				 <input type="text" value="${employee.empName}" readonly="readonly" id="empName" name="empName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td>
   				<select name="userStatus" id="userStatus">
   					<c:choose>
   						<c:when test="${user.userStatus == 1}">
   							<option value="1" selected='selected'>正常</option>
   							<option value="2" >已注销</option>
   						</c:when>
   						<c:otherwise>
   							<option value="1">正常</option>
   							<option value="2" selected='selected'>已注销</option>
   						</c:otherwise>
   					</c:choose>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			角色:
   			</td>
   			<td>
   				<select name="userRole" id="userRole">
   					<c:forEach items="${roleList}" var="role">
   					   		<c:choose>
   								<c:when test="${user.userRole == role.roleName}">
   									<option value="${role.roleId}" selected="selected">${role.roleName}</option>
   								</c:when>
   								<c:otherwise>
   									<option value="${role.roleId}">${role.roleName}</option>
   								</c:otherwise>
   							</c:choose>
   						
   					</c:forEach>
   				</select>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="保存"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>

   	
   	</form>
  </body>
</html>
