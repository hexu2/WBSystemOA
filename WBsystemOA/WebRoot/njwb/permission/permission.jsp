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
    
    <title>权限管理</title>
  	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>

	<style type="text/css">
	.empInfo{
		width: 700px;
	}
	
	</style>
	
	<script type="text/javascript">
		$(function(){
			$("#add").click(function(){
				location.href="queryPermissionAdd.do?";
			});
		})
		
		//obj  删除图片
		function del(permissionId){
			if(confirm("确定删除吗?")){
				location.href="permissionDel.do?permissionId="+permissionId;
			}
		}
	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;权限管理 </h1>
         		
         	<div class="add">
         		<form action="queryPermissionsByBtn.do" method="post" >
		         	    角色:<select name="roleNameFromInput" id="roleNameFromInput">
		   					<option value="">请选择</option> 
		   					<c:forEach items="${roleList}"  var="role">
		   						<option>${role.roleName}</option>
		   					</c:forEach>
		         		</select>
		         	   菜单:<select name="menuNameFromInput" id="menuNameFromInput">
		   					<option value="">请选择</option> 
		   					<c:forEach items="${menuList}"  var="menu">
		   						<option>${menu.menuName}</option>
		   					</c:forEach>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	        <img alt="" src="img/add.png" width="18px" height="18px" id="add">添加权限
	        </div><br/>
	        
         	<table class="permissionInfo">
         		<tr class="titleRow">
         			<td>角色ID</td>
         			<td>角色名称</td>
         			<td>菜单ID</td>
         			<td>菜单名称</td>
         			<td>创建时间</td>
         			<td>操作列表</td>
         		</tr>
         		
				<c:forEach items="${pageModel.dataList}" var="permission">
		         		<tr >
		         			<td>${permission.roleId}</td>
		         			<td>${permission.roleName}</td>
		         			<td>${permission.menuId}</td>
		         			<td>${permission.menuName}</td>
		         			<td>${permission.createTime}</td>
		         			<td width="80px">
		         				<img alt="" src="img/delete.png" class="operateImg" onclick="del('${permission.id}')">
		         			</td>
		         		</tr>
				</c:forEach>
				
				<tr height="40px">
					<td colspan="6">
						<c:if test="${flag == 1}">
							<a href="queryPermissionsByBtn.do?pageNo=${pageModel.firstPage}&roleNameFromInput=${roleNameFromInput}&menuNameFromInput=${menuNameFromInput}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryPermissionsByBtn.do?pageNo=${pageModel.prePage}&roleNameFromInput=${roleNameFromInput}&menuNameFromInput=${menuNameFromInput}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryPermissionsByBtn.do?pageNo=${pageModel.nextPage}&roleNameFromInput=${roleNameFromInput}&menuNameFromInput=${menuNameFromInput}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryPermissionsByBtn.do?pageNo=${pageModel.lastPage}&roleNameFromInput=${roleNameFromInput}&menuNameFromInput=${menuNameFromInput}">尾页</a>&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
						</c:if>
						<c:if test="${flag == 2}">
							<a href="queryPermissions.do?pageNo=${pageModel.firstPage}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryPermissions.do?pageNo=${pageModel.prePage}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryPermissions.do?pageNo=${pageModel.nextPage}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryPermissions.do?pageNo=${pageModel.lastPage}">尾页</a>&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
						</c:if>
					</td>
				</tr>
         	</table>
  </body>
</html>
