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
    <title>员工管理</title>
	
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
				location.href="queryEmpAdd.do";
			});
			
			//员工姓名engine
			$("#empName").keyup(function(){
				var empName = $.trim($("#empName").val());
				if("" != empName){
					$.ajax({
						type:"GET",
	  					url:"queryByEmpName.do?empName=" + empName,
	  					dataType:"json",
	  					success:function(employees){
	  					    var htmlStr = "";
	  					    //alert(employees[0].empName)
	  						for(var i = 0; i < employees.length; i++){
	  							//拼接html串
	  							htmlStr += " <div onmouseover='setBgColor(this)' onmouseout='resetBgColor(this)' onclick='selectName(this)'>"
	  							             + employees[i].empName
	  							             + "</div>";
	  						
	  						}
	  						if(employees.length != 0){
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
	    $("#empName").val($.trim($(obj).text()));
	    //让nameContainer隐藏
	    $("#nameContainer").css("display","none");
	    
	  }
		
		//obj  删除图片
		function del(empNo){
			//alert(empNo);
			if(confirm("确定删除吗?")){
				//删除
				location.href="deleteEmp.do?empNo="+empNo;
			}
		}
		
		//obj 修改图片
		function edit(empNo){
			
			location.href="queryUpdateEmp.do?empNo="+empNo;
		}
	    
	    //obj  明细图片
	    function showDetail(empNo){
		    //alert(empNo);
	    	location.href="queryEmpDetail.do?empNo="+empNo;
	    }
	    
	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;员工管理 </h1>
         		
         	<div class="add">
         		<form action="queryEmpsByBtn.do" method="post" >
		         	    姓名:<input type="text"  size="20px" name="empName" id="empName" autocomplete = "off" /> 
		         	    <div id="nameContainer"></div>
		         	    
		         	    部门:<select name="deptName" id="deptName">
		   					<option value="">请选择</option> 
		   					<c:forEach items="${deptList}"  var="dept">
		   						<option >${dept.deptName}</option>
		   					</c:forEach>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	         	
	        <img alt="" src="img/add.png" width="18px" height="18px" id="add">添加员工
	        
	        </div><br/>
         	
         	<table class="empInfo">
         		<tr class="titleRow">
         			<td>员工编号</td>
         			<td>员工姓名</td>
         			<td>性别</td>
         			<td>所属部门</td>
         			<td>入职时间</td>
         			<td>操作列表</td>
         		</tr>
         		
				<c:forEach items="${pageModel.dataList}" var="employee">
				
		         		<tr>
		         			<td>${employee.empNo}</td>
		         			<td>${employee.empName}</td>
		         			<td>${employee.sex}</td>
		         			<td>${employee.empDeptNo}</td>
		         			<td>${employee.entryTime}</td>
		         			<td>
		         				<img alt="" src="img/delete.png" class="operateImg" onclick="del('${employee.empNo}')">
		         				<img alt="" src="img/edit.png" class="operateImg" onclick="edit('${employee.empNo}')">
		         				<img alt="" src="img/detail.png" class="operateImg" onclick="showDetail('${employee.empNo}')">
		         			</td>
		         		</tr>
				</c:forEach>
				
				<tr>
					<td colspan="6">
						<c:if test="${flag == 1}">
							<a href="queryEmpsByBtn.do?pageNo=${pageModel.firstPage}&empName=${empName}&deptName=${deptName}">首页</a>&nbsp;&nbsp;
							<a href="queryEmpsByBtn.do?pageNo=${pageModel.prePage}&empName=${empName}&deptName=${deptName}">上一页</a>&nbsp;&nbsp;
							<a href="queryEmpsByBtn.do?pageNo=${pageModel.nextPage}&empName=${empName}&deptName=${deptName}">下一页</a>&nbsp;&nbsp;
							<a href="queryEmpsByBtn.do?pageNo=${pageModel.lastPage}&empName=${empName}&deptName=${deptName}">尾页</a>
							&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
						</c:if>
						<c:if test="${flag != 1}">
							<a href="queryAllEmps.do?pageNo=${pageModel.firstPage}">首页</a>&nbsp;&nbsp;
							<a href="queryAllEmps.do?pageNo=${pageModel.prePage}">上一页</a>&nbsp;&nbsp;
							<a href="queryAllEmps.do?pageNo=${pageModel.nextPage}">下一页</a>&nbsp;&nbsp;
							<a href="queryAllEmps.do?pageNo=${pageModel.lastPage}">尾页</a>
							&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
						</c:if>
					</td>
				</tr>
         	</table>
  </body>
</html>
