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
			var baoXiaoType = $.trim($("#baoXiaoType").val());
			var baoXiaoBz = $.trim($("#baoXiaoBz").val());
			var baoXiaoMoney = $.trim($("#baoXiaoMoney").val());
			if( "" == baoXiaoType){
				alert("报销类型不能为空！");
				return false;
			}else if("" == baoXiaoBz){
				alert("摘要不能为空！");
				return false;
			}else if("" == baoXiaoMoney){
				alert("报销金额不能为空！");
				return false;
			}else if(isNaN(baoXiaoMoney)){
				alert("报销金额必须为数字！");
				return false;
			}else{
				return true;
			}
		
		});
	
	});
	 </script>
  </head>
  <body>
  	<form action="baoXiaoUpdate.do" method="post">
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			报销编号:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoNo" id="baoXiaoNo"  value="${baoXiao.baoXiaoNo}" readonly="readonly"/>
   				<input type = "hidden" name="id" id="id"  value="${baoXiao.id}" readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			申请人:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoUser" id="baoXiaoUser"  value="${baoXiao.baoXiaoUser}" readonly="readonly"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			报销类型:
   			</td>
   			<td>
   				<select name="baoXiaoType" id="baoXiaoType">
   				<c:forEach items="${propertiesList}" var="properties">
   					<c:choose>
   						<c:when test="${properties.pageValue == baoXiao.baoXiaoType}" >
   							<option value="${properties.keyId}" selected="selected">${properties.pageValue}</option>
   						</c:when>
   						<c:otherwise>
   							<option value="${properties.keyId}" >${properties.pageValue}</option>
   						</c:otherwise>
   					</c:choose>
   				</c:forEach>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			报销金额:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoMoney" id="baoXiaoMoney"  value="${baoXiao.baoXiaoMoney}"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			报销事由:
   			</td>
   			<td>
   				<input type = "text" name="baoXiaoBz" id="baoXiaoBz" value="${baoXiao.baoXiaoBz}"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			报销时间:
   			</td>
   			<td>
   				<input type = "text" name="applyTime" id="applyTime"  value="${baoXiao.applyTime}"   readonly="readonly"/>
   			</td>
   		</tr>  
   		
	<%--  		

   		<tr>
   			<td>
   			申请状态:
   			</td>
   			<td>
			   	<select name="baoXiaoStatus" id="baoXiaoStatus">
   					<option value="">请选择</option>
   					<option value="1">草稿</option>
   					<option value="2">提交</option>
   				</select>
   			</td>
   		</tr>  	
  		 	
   		
   		--%><tr>
   			<td colspan="2">
   				<input type = "submit" value="草稿" name="baoXiaoStatus"/>
   				<input type = "submit" value="提交" name="baoXiaoStatus"/>
   				<input type = "reset" value="重置"/>
   				<input type = "button" value="返回" onclick="javascript:history.go(-1);"/>
   			</td>
   		</tr>  	
   		
   	</table>
   	
   	</form>
  </body>
</html>
