<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.njwangbo.oa.entity.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>密码重置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<!--<link rel="stylesheet" type="text/css" href="styles.css">-->

  	<script type="text/javascript">
  	$(function(){
  		$(":submit").click(function(){
  			var oldPwdFromInput = $.trim($("#oldPwd").val());
  			var inputOld = $.trim($("#inputOldPwd").val()); 
  			var newPwd = $.trim($("#newPwd").val());
  			var confirmPwd = $.trim($("#confirmPwd").val());
  			if(oldPwdFromInput == ""){
  				alert("旧密码不能为空");
  				return false;
  			}else if(inputOld != oldPwdFromInput){
  				alert("旧密码错误");
  				return false;
  			}else if(oldPwdFromInput.match(/^[a-zA-Z][a-zA-Z0-9_-]{3,15}$/) == null){
  				alert("旧密码格式错误：必须是由数字字母或下划线组成的第一个数字为字母的4-16位字符串！");
  				return false;
  			}else if(newPwd != confirmPwd){
  				alert("两次输入的密码不一致");
  				return false;
  			}else if(newPwd == oldPwdFromInput){
  				alert("新密码和旧密码相同");
  				return false;
  			}else if(newPwd == ""){
  				alert("新密码不能为空");
  				return false;
  			}else if(newPwd.match(/^[a-zA-Z][a-zA-Z0-9_-]{3,15}$/) == null){
  				alert("新密码格式错误：必须是由数字字母或下划线组成的第一个数字为字母的4-16位字符串！");
  				return false;
  			}else{
  				return true;
  			}
  		});
  	});
  	</script>
  	
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
  </head>
  
  <body>
   	<form action="resetPwd.do"  method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			旧密码:
   			</td>
   			<td>
   				<input type = "hidden" name="oldPwd" id="oldPwd"  value="${user.passWord}"/>
   				<input type = "password" name="inputOldPwd" id="inputOldPwd" />
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			新密码:
   			</td>
   			<td>
   				<input type = "password" name="newPwd" id="newPwd" />
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			确认密码:
   			</td>
   			<td>
   				<input type = "password" name="confirmPwd" id="confirmPwd"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>
   	</form>
  </body>
</html>
