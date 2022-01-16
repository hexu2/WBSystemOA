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
    
    <title>角色管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/main.css">

	<script type="text/javascript">
		$(function(){
			//添加角色
			$("#add").click(function(){
				location.href="njwb/role/roleAdd.jsp";
			});
		})
		
		//obj  删除图片
		function del(roleId){
			if(confirm("确定删除吗?")){
				//删除
				location.href="roleDel.do?roleId="+roleId;
			}
		}
		
		//obj 修改图片
		function edit(roleId){
			location.href="queryRoleUpdate.do?roleId="+roleId;
		}
	</script>  
  </head>
  
  <body>
         	<h1 class="title">首页  &gt;&gt;角色管理 </h1>
         		
         	<div class="add">
         			<br/>
	         		<img alt="" src="img/add.png" width="18px" height="18px" id="add">添加角色
	         		<br/>
	        </div><br/>
	        
         	<table class="roleInfo">
         		<tr class="titleRow">
         			<td>角色ID</td>
         			<td>角色名称</td>
         			<td>创建时间</td>
         			<td>操作列表</td>
         		</tr>
         		
				<c:forEach items="${pageModel.dataList}" var="role">
		         		<tr >
		         			<td>${role.roleId}</td>
		         			<td>${role.roleName}</td>
		         			<td>${role.createTime}</td>
		         			
		         			<td width="80px">
		         				<img alt="" src="img/delete.png" class="operateImg" onclick="del('${role.roleId}')">
		         				<img alt="" src="img/edit.png" class="operateImg" onclick="edit('${role.roleId}')">
		         			</td>
		         		</tr>
				</c:forEach>

				<tr height="40px">
					<td colspan="9">
						<a href="queryRoles.do?pageNo=${pageModel.firstPage}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryRoles.do?pageNo=${pageModel.prePage}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryRoles.do?pageNo=${pageModel.nextPage}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryRoles.do?pageNo=${pageModel.lastPage}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</td>
				</tr>
         	</table>
  </body>
</html>
