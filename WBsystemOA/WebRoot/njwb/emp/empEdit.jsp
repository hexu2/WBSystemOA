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
    
    <title>员工编辑</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="js/base.js"></script>
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
			var empNo = $.trim($("#empNo").val());
			var empName = $.trim($("#empName").val());
			var empSex = $.trim($("#empSex").val());
			var empDeptNo = $.trim($("#empDeptNo").val());
			var entryTime = $.trim($("#entryTime").val());
			var phone = $.trim($("#phone").val());
			
			if( "" == empNo){
				alert("员工编号不能为空！");
				return false;
			}else if(empNo.length != 5){
			    alert("员工编号长度必须为5位！");
			    return false;
			
			}else if("" == empDeptNo){
				alert("员工所属部门不能为空!");
				return false;
			}else if("" == empName){
				alert("员工名不能为空！");
				return false;
			}else if("" == entryTime){
				alert("入职时间不能为空！");
				return false;
			}else if(entryTime.match(/^\d{4}(-)\d{2}(-)\d{2}$/) == null){
				alert("入职日期必须为xxxx-xx-xx格式！");
				return false;
			}else if("" == phone){
				alert("员工联系方式不能为空！");
				return false;
			}else{
				return true;
			}
		});
	
	});
	 </script>
  </head>
  <body>
	<form action="updateEmp.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input type = "text" name="empNo" id="empNo"  value="${employee.empNo}"  readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			员工姓名:
   			</td>
   			<td>
   				<input type = "text" name="empName" id="empName"  value="${employee.empName}" />
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
   				<select name="empSex" id="empSex">
   					<c:choose>
   						<c:when test="${employee.sex == '男'}">
   							<option value="1" selected="selected">男</option>
   							<option value="0">女</option>
   						</c:when>
   						<c:otherwise>
   							<option value="1">男</option>
   							<option value="0" selected="selected">女</option>
   						</c:otherwise>
   					</c:choose>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			所属部门:
   			</td>
   			<td>
   				<select name="empDeptNo" id="empDeptNo">
   					<c:forEach items="${deptList}"  var="dept">
   						<c:choose>
	   						<c:when test="${employee.empDeptNo == dept.deptName}">
	   							<option value="${dept.deptNo}" selected='selected'>${dept.deptName}</option>
	   						</c:when>
	   						<c:otherwise>
	   							<option value="${dept.deptNo}">${dept.deptName}</option>
	   						</c:otherwise>
   						</c:choose>
   					</c:forEach>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			学历:
   			</td>
   			<td>
   				<input type = "text" name="education" id="education"  value="${employee.education}" />
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			邮箱:
   			</td>
   			<td>
   				<input type = "text" name="email" id="email"  value="${employee.email}" />
   			</td>
   		</tr>  	

   		<tr>
   			<td>
   			联系方式:
   			</td>
   			<td>
   				<input type = "text" name="phone" id="phone"  value="${employee.phone}" />
   			</td>
   		</tr> 

   		<tr>
   			<td>
   			入职时间:
   			</td>
   			<td>
   				<input type = "text" name="entryTime" id="entryTime"  value="${employee.entryTime}" readonly="readonly" onfocus="new WdatePicker()" />
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="修改"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
	
   		
   	</table>
   	</form>
  </body>
</html>
