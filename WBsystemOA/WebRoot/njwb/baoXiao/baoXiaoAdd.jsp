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
    <title>报销申请</title>
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
   	<form action="baoXiaoAdd.do" method="post">
   	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			报销编号：
   			</td>
   			<td>
   				<input name="baoXiaoNo" id="baoXiaoNo" value="${baoXiaoNo}" readonly="readonly"/>
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			申请人：
   			</td>
   			<td>
   				<input name="baoXiaoUser" id="baoXiaoUser" value="${userName}" readonly="readonly" />
   			</td>
   		</tr>
   		
   		<tr>
   			<td>
   			报销类型:
   			</td>
   			<td>
   				<select name="baoXiaoType" id="baoXiaoType">
   					<option value="">请选择</option>
   					<c:forEach items="${propertiesList}" var="properties">
   						<option value="${properties.keyId}">${properties.pageValue}</option>
   					</c:forEach>
   				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			摘要:
   			</td>
   			<td>
				<textarea rows="" cols="" name="baoXiaoBz" id="baoXiaoBz">
				</textarea>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			金额:
   			</td>
   			<td>
				<input type = "text" name="baoXiaoMoney" id="baoXiaoMoney"  value=""/>
   			</td>
   		</tr>  

   		<tr>
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
