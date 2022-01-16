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
    <title>员工添加</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
      <script type="text/javascript" src="js/base.js"></script>
      <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
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
			margin: 20px auto;
			
			
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
				var empNo = $.trim($("#empNo").val());
				var empName = $.trim($("#empName").val());
				var empSex = $.trim($("#empSex").val());
				var entryTime = $.trim($("#entryTime").val());
				var phone = $.trim($("#phone").val());
				if( "" == empNo){
					alert("员工编号不能为空！");
					return false;
				}else if(empNo.length != 5){
				    alert("员工编号长度必须为5位！");
				    return false;
				
				}else if("" == empName){
					alert("员工名不能为空！");
					return false;
				}else if("" == entryTime){
					alert("入职时间不能为空！");
					return false;
				}else if("" == phone){
					alert("员工联系方式不能为空！");
					return false;
				}else{
					return true;
				}
			
			});
			
			$("#empNo").blur(function(){
				var empNo = $.trim($("#empNo").val());
				if("" != empNo){
					$.ajax({
						type:"GET",
						url:"checkEmpNo.do?empNo="+empNo,
						dataType:"text",
						success:function(result){
							if(result == "0"){
								$("#empNoInfo").html("可用");
								$("#empNoInfo").removeClass("rColor");
								$("#empNoInfo").addClass("gColor");
							}else if(result == "1"){
							    $("#empNoInfo").html("该员工编号已存在");
							    $("#empNoInfo").removeClass("gColor");
								$("#empNoInfo").addClass("rColor");
							}
							if(result == "2"){
							    $("#empNoInfo").html("员工编号长度必须为5");
							    $("#empNoInfo").removeClass("gColor");
								$("#empNoInfo").addClass("rColor");
							}
						}
					});
					
				}else{
					$("#empNoInfo").html("员工编号不能为空");
					$("#empNoInfo").removeClass("gColor");
					$("#empNoInfo").addClass("rColor");
				}
			});
		
		});
	</script>
</head>
  <body>
   	<form action="addEmp.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input type = "text" name="empNo" id="empNo" />
   			</td>
   			<td><span id="empNoInfo" ></span></td>
   		</tr>
   		
   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td>
   				<input type = "text" name="empName" id="empName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
   				<select name="empSex" id="empSex">
   					<option value="0">女</option>
   					<option value="1" selected="selected">男</option>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			所属部门:
   			</td>
   			<td>
   				<select name="empDeptNo" id="empDeptNo">
   					<option value="0">请选择</option> 
   					<c:forEach items="${deptList}"  var="dept">
   						<option value="${dept.deptNo}">${dept.deptName}</option>
   					</c:forEach>

   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			学历:
   			</td>
   			<td>
   				<input type = "text" name="education" id="education"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			邮箱:
   			</td>
   			<td>
   				<input type = "text" name="email" id="email"/>
   			</td>
   		</tr>  	

   		<tr>
   			<td>
   			联系方式:
   			</td>
   			<td>
   				<input type = "text" name="phone" id="phone"/>
   			</td>
   		</tr> 

   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td>
   				<input type = "text" name="entryTime" id="entryTime" readonly="readonly"  class="Wdate" onfocus="new WdatePicker()"/>
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
