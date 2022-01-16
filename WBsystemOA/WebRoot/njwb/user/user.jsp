<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>账户管理</title>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
  	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>

	<style type="text/css">
	   #nameContainer{
	   	  width: 240px;
	   	  /**
	   	  height: 100px;
	   	  */
	   	  border: 1px solid #9FBAD6;
	      position: absolute;
	      left:32px;
	      display: none;
	      background-color: white;
	      
	   }
	   
	   .selectedColor{
	   	  background-color: #DBEAF9;
	   
	   }
	   .resetColor{
	   	  background-color: white;
	   }

	
	</style>
	
	<script type="text/javascript">
		$(function(){
			$("#add").click(function(){
				location.href="queryUserAdd.do?";
			});
			
			
			$("#inputUserName").keyup(function(){
				var inputUserName = $.trim($("#inputUserName").val());
				if("" != inputUserName){
					//alert(inputUserName);
					$.ajax({
						type:"GET",
						url:"queryByUserName.do?inputUserName="+inputUserName,
						dataType:"json",
						success:function(users){
							var htmlStr = "";
							for(var i = 0; i < users.length; i++){
	  							htmlStr += " <div onmouseover='setBgColor(this)' onmouseout='resetBgColor(this)' onclick='selectName(this)'>"
	  							             + users[i].userName
	  							             + "</div>"
	  						
							}
							if(users.length != 0){
	  							$("#nameContainer").html(htmlStr);
	  							$("#nameContainer").css("display","block");
							}
							
						}
						
					});
					
				}else{
	  			    $("#nameContainer").html("");
	  			    $("#nameContainer").css("display","none");
  				}
				
			});

			
		});
		
					

		/**
		     obj是js对象  ---》转换成jquery对象  ${obj}
		  */
		  function setBgColor(obj){
		    $(obj).removeClass("resetColor");
		  	$(obj).addClass("selectedColor");	
		  
		  }
		  
		  function resetBgColor(obj){
		    $(obj).removeClass("selectedColor");
		    $(obj).addClass("resetColor");	
		  
		  }
		  
		  function selectName(obj){
		    //$.trim($(obj).text());
		    $("#inputUserName").val($.trim($(obj).text()));
		    //让nameContainer隐藏
		    $("#nameContainer").css("display","none");
		    
		  } 
			
		
		
		
		//obj  删除图片
		function del(userId){
			if(confirm("确定删除吗?")){
				//删除
				//alert("删除成功");
				location.href="userDel.do?userId="+userId;
			}
		}
		
		//obj 修改图片
		function edit(userId,employeeNo){
			location.href="queryUserUpdate.do?userId="+userId+"&employeeNo="+employeeNo;
			//alert(userName);
		}
	    
	    //obj  明细图片
	    function showDetail(userId){
	    	location.href="queryUserDetail.do?userId="+userId;
	    }

	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;账户管理 </h1>
         		<!--   -->
         	<div class="add" >
         		<form action="queryUsersByBtn.do" method="post" >
		         	    账号:<input type="text"  size="20px" name="inputUserName" id="inputUserName" autocomplete = "off" /> 
		         	   		<div id="nameContainer"></div>
		         	    角色：<select  name="inputUserRole" id="inputUserRole">
		         	    	<option value="">请选择</option>
		   					<c:forEach items="${roleList}" var="role">
		   						<option>${role.roleName}</option>
		   					</c:forEach>
		         	    </select>
		         	    账户状态:<select name="inputUserStatus" id="inputUserStatus">
		   					<option value="">请选择</option> 
		   					<option value="1">正常</option>
		   					<option value="2">已注销</option>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         		
	         	</form>
	         		<img alt="" src="img/add.png" width="18px" height="18px" id="add">添加账户
	        </div>
         	<br/>
         	<table class="userInfo">
         		<tr class="titleRow">
         			<td>账号</td>
         			<td>员工姓名</td>
         			<td>状态</td>
         			<td>角色</td>
         			<td>操作列表</td>
         		</tr>
				<c:forEach items="${pageModel.dataList}" var="user">
				
		         		<tr >
		         			<td>${user.userName}</td>
		         			<td>${user.employeeName}</td> 
		         			<c:if test="${user.userStatus  == '1'}">
		         			<td>正常</td>
		         			</c:if>
		         			<c:if test="${user.userStatus  == '2'}">
		         			<td>已注销</td>
		         			</c:if>
		         			
		         			<td>
							${user.userRole }
		         			</td>
		         			
		         			<td width="80px">
		         				<img alt="" src="img/delete.png" class="operateImg" onclick="del('${user.id}')">
		         				<img alt="" src="img/edit.png" class="operateImg" onclick="edit('${user.id}','${user.employeeNo}')">
		         				<img alt="" src="img/detail.png" class="operateImg" onclick="showDetail('${user.id}')">
		         			</td>
		         		</tr>
				
				
				</c:forEach>
				

				<tr height="40px">
					<td colspan="9">
					<c:if test="${flag == 1}">
						<a href="queryUsersByBtn.do?pageNo=${pageModel.firstPage}&inputUserName=${inputUserName}&inputUserRole=${inputUserRole}&inputUserStatus=${inputUserStatus}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryUsersByBtn.do?pageNo=${pageModel.prePage}&inputUserName=${inputUserName}&inputUserRole=${inputUserRole}&inputUserStatus=${inputUserStatus}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryUsersByBtn.do?pageNo=${pageModel.nextPage}&inputUserName=${inputUserName}&inputUserRole=${inputUserRole}&inputUserStatus=${inputUserStatus}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryUsersByBtn.do?pageNo=${pageModel.lastPage}&inputUserName=${inputUserName}&inputUserRole=${inputUserRole}&inputUserStatus=${inputUserStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</c:if>
					
					<c:if test="${flag == 2}">
						<a href="queryUsers.do?pageNo=${pageModel.firstPage}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryUsers.do?pageNo=${pageModel.prePage}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryUsers.do?pageNo=${pageModel.nextPage}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryUsers.do?pageNo=${pageModel.lastPage}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</c:if>

					</td>
				</tr>
         	</table>
  </body>
</html>
