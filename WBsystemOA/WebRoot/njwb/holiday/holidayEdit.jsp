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
    
    <title>My JSP 'holidayEdit.jsp' starting page</title>
	
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
				var holidayType = $.trim($("#holidayType").val());
				var holidayBz = $.trim($("#holidayBz").val());
				var empSex = $.trim($("#empSex").val());
				var startTime = $.trim($("#startTime").val());
				var endTime = $.trim($("#endTime").val());
				if( "" == holidayType){
					alert("请假类型不能为空！");
					return false;
				}else if("" == holidayBz){
					alert("请假事由不能为空！");
					return false;
				}else if("" == startTime){
					alert("开始时间不能为空！");
					return false;
				}else if("" == endTime){
					alert("结束时间不能为空！");
					return false;
				}else{
					return true;
				}
		
		});
	
	});
	 </script>
  </head>
  <body>
  	<form action="holidayUpdate.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			请假ID:
   			</td>
   			<td>
   				<input type = "text" name="id" id="id"  value="${holiday.id}" readonly="readonly"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			请假编号:
   			</td>
   			<td>
   				<input type = "text" name="holidayNo" id="holidayNo"  value="${holiday.holidayNo}" readonly="readonly"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			申请人:
   			</td>
   			<td>
   				<input type = "text" name="holidayUser" id="holidayUser"  value="${holiday.holidayUser}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			请假类型:
   			</td>
   			<td>
   				<select name="holidayType" id="holidayType">
   					<c:forEach items="${propertiesList}" var="properties">
	   					<c:choose>
	   						<c:when test="${properties.pageValue == holiday.holidayType}">
	   							<option value="${properties.keyId}" selected='selected'>${properties.pageValue}</option>
	   						</c:when>
	   						<c:otherwise>
	   							<option value="${properties.keyId}">${properties.pageValue}</option>
	   						</c:otherwise>
	   					</c:choose>
   					</c:forEach>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			请假事由:
   			</td>
   			<td>
   				<input type = "text" name="holidayBz" id="holidayBz" value="${holiday.holidayBz}"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			开始时间:
   			</td>
   			<td>
   				<input type = "text" name="startTime" id="startTime"  value="${holiday.startTime}" readonly="readonly" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td>
   			结束时间:
   			</td>
   			<td>
   				<input type = "text" name="endTime" id="endTime"  value="${holiday.endTime}" readonly="readonly" onfocus="new WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
   			</td>
   		</tr><%--  		

   		<tr>
   			<td>
   			申请状态:
   			</td>
   			<td>
			   	<select name="holidayStatus" id="holidayStatus">
   					<option value="">请选择</option>
   					<option value="1">草稿</option>
   					<option value="2">提交</option>
   				</select>
   			</td>
   		</tr>  	
  		 	
   		
   		--%><tr>
   			<td colspan="2">
   				<input type = "submit" value="草稿" name="holidayStatus"/>
   				<input type = "submit" value="提交" name="holidayStatus"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   		
   	</table>
   	
   	</form>
  </body>
</html>
