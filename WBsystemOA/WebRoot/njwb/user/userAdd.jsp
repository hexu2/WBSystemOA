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
    <title>添加账户</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <script type="text/javascript" src="js/base.js"></script>
      <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 600px;
			margin: 50px auto;
		
			
		}
		
		#deptEditTable td{
			height: 40px;
			width: 200px;
		}
		
		.rColor{
			color: red;
		}
		.gColor{
			color: green;
		
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$(":submit").click(function(){
				//基础数据校验
				var userName = $.trim($("#userName").val());
				var passWord = $.trim($("#passWord").val());
				var confirmPwd = $.trim($("#confirmPwd").val());
				var employeeNo = $.trim($("#employeeNo").val());
				var userStatus = $.trim($("#userStatus").val());
				var userRole = $.trim($("#userRole").val());
				
				if( "" == userName){
					alert("账号不能为空！");
					return false;
				}else if(userName.match(/^[a-zA-Z0-9_-]{4,16}$/) == null){
				    alert("账户必须是由数字字母或下划线组成的4-16位字符串！");
				    return false;
				}else if("" == passWord){
					alert("密码不能为空！");
					return false;
				}else if(passWord.match(/^[a-zA-Z][a-zA-Z0-9_-]{3,15}$/) == null){
					alert("密码必须是由数字母或下划线组成的首字母大写的4-16位字符串！");
					return false;
				}else if("" == confirmPwd){
					alert("确认密码不能为空！");
					return false;
				}else if(passWord != confirmPwd){
					alert("两次输入的密码不一致！");
					return false;
				}else if("" == userStatus){
					alert("账户状态不能为空！");
					return false;
				}else if("" == userRole){
					alert("账户角色不能为空！");
					return false;
				}else if("" == employeeNo){
					alert("员工号不能为空！");
					return false;
				}else{
					return true;
				}
			
			});
			
			$("#userName").blur(function(){
				var userName = $.trim($("#userName").val());
				if("" != userName){
					$.ajax({
						type:"GET",
						url:"checkUserName.do?userName="+userName,
						dataType:"text",
						success:function(result){
							if(result == "0"){
								$("#userNameInfo").html("可用");
								$("#userNameInfo").removeClass("rColor");
								$("#userNameInfo").addClass("gColor");
							}else if(result == "1"){
							    $("#userNameInfo").html("该帐号已在");
							    $("#userNameInfo").removeClass("gColor");
								$("#userNameInfo").addClass("rColor");
							}
						}
						
					});
					
				}else{
					$("#userNameInfo").html("不能为空");
					$("#userNameInfo").removeClass("gColor");
					$("#userNameInfo").addClass("rColor");
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
   	<form action="userAdd.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			账号：
   			</td>
   			<td>
   				<input name="userName" id="userName"/>
   			</td>
   			<td><span id="userNameInfo" ></span></td>
   		</tr>

   		<tr>
   			<td>
   			密码：
   			</td>
   			<td>
   				<input type="password" name="passWord" id="passWord"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			确认密码：
   			</td>
   			<td>
   				<input type="password" name="confirmPwd" id="confirmPwd"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<select name="employeeNo" id="employeeNo">
   					<option value="">请选择</option>
   					<c:forEach items="${employeeList}" var="employee">
   						<option>${employee.empNo}</option>
   					</c:forEach>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			员工名:
   			</td>
   			<td>
   				 <input type="text" value="" readonly="readonly" id="empName" name="empName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			状态:
   			</td>
   			<td>
   				<select name="userStatus" id="userStatus">
   					<option value="">请选择</option>
   					<option value="1">正常</option>
   					<option value="2">已注销</option>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			角色:
   			</td>
   			<td>
   				<select name="userRole" id="userRole">
   					<option value="">请选择</option>
   					<c:forEach items="${roleList}" var="role">
   						<option value="${role.roleId}">${role.roleName}</option>
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
