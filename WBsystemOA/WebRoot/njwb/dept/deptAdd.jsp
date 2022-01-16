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
   <title>部门添加</title>
	
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
				var deptNo = $.trim($("#deptNo").val());
				if(deptNo == ""){
					alert("部门编号不能为空");
					return false;
				}else if(deptNo.length != 5){
				    alert("部门编号长度必须为5位");
				    return false;
				
				}else{
					return true;
				}
				
			});
			
			$("#deptNo").blur(function(){
				var deptNo = $.trim($("#deptNo").val());
				if("" != deptNo){
					$.ajax({
						type:"GET",
						url:"checkDeptNo.do?deptNo="+deptNo,
						dataType:"text",
						success:function(result){
							if(result == "0"){
								$("#deptNoInfo").html("可用");
								$("#deptNoInfo").removeClass("rColor");
								$("#deptNoInfo").addClass("gColor");
							}else if(result == "1"){
							    $("#deptNoInfo").html("该部门号已存在");
							    $("#deptNoInfo").removeClass("gColor");
								$("#deptNoInfo").addClass("rColor");
							}
							if(result == "2"){
							    $("#deptNoInfo").html("部门号长度必须为5");
							    $("#deptNoInfo").removeClass("gColor");
								$("#deptNoInfo").addClass("rColor");
							}
						
						
						}
					});
					
				}else{
					$("#deptNoInfo").html("部门号不能为空");
					$("#deptNoInfo").removeClass("gColor");
					$("#deptNoInfo").addClass("rColor");
				}
				
			});
		});
	</script>
</head>

  <body>
   	 <form action="addDept.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			部门编号:
   			</td>
   			<td>
   				<input type = "text" name="deptNo" id="deptNo" />
   			</td>
   			<td><span id="deptNoInfo" ></span></td>
   		</tr>
   		<tr>
   			<td>
   			部门名称:
   			</td>
   			<td>
   				<input type = "text" name="deptName" id="deptName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td >
   				<input type = "text" name="deptLoc" id="deptLoc"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td>
   				<input type = "text" name="deptManager" id="deptManager"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="添加"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   	</table>
   	
   	</form>
   	
  </body>
</html>
