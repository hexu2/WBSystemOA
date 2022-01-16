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
    
    <title>请假管理</title>
  	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
	   #nameContainer{
	   	  width: 240px;
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
				location.href="queryHolidayAdd.do?";
			});
			
			//holidayUser engine
			$("#holidayUser").keyup(function(){
				var inputUserName = $.trim($("#holidayUser").val());
				if("" != inputUserName){
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
		
		//obj  删除图片
		function del(holidayNo,holidayStatus,holidayUser){
			if(2 == holidayStatus){
				alert("该请假记录已经提交不可以删除！");
			}else if(holidayUser != '${userName}'){
				alert("申请人该记录尚未提交不可以操作！");
			}else if(confirm("确定删除吗?")){
				location.href="holidayDel.do?holidayNo="+holidayNo;
			}
		}
		
		//obj 修改图片
		function edit(holidayNo,holidayStatus,holidayUser){
			if(2 == holidayStatus){
				alert("该请假记录已经提交不可修改！");
			}else if(holidayUser != '${userName}'){
				alert("申请人该记录尚未提交不可以操作！");
			}else{
				location.href="queryHolidayUpdate.do?holidayNo="+holidayNo;
			}
		}
	    
	    //obj  明细图片
	    function showDetail(holidayNo){
	    	location.href="queryHolidayDetail.do?holidayNo="+holidayNo;
	    }
	    
	    //审批
	    function auditBefor(workID,tableID){
	    	location.href="auditBefor.do?workID="+workID+"&tableID="+tableID;
	    }


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
		    $("#holidayUser").val($.trim($(obj).text()));
		    //让nameContainer隐藏
		    $("#nameContainer").css("display","none");
		    
		  }  
	    
	</script>

  </head>
  <body>
         	<h1 class="title">首页  &gt;&gt;请假管理 </h1>
         		
         	<div class="add">
         		<form action="queryHolidayByBtn.do" method="post" >
         		<c:if test="${user.userRole != '2'}">
         		 申请人:<input type="text"  size="20px" value=""  name="holidayUser" id="holidayUser" autocomplete = "off" /> 
         		 <div id="nameContainer"></div>
         		</c:if>
         		<c:if test="${user.userRole == '2'}">
         		 申请人:<input type="text"  size="20px" value="${user.userName}" readonly="readonly" name="holidayUser" id="holidayUser"/> 
         		</c:if>
		         	   
		         	    请假类型:<select name="holidayType" id="holidayType">
		   					<option value="">请选择</option> 
		   					<c:forEach items="${propertiesList}"  var="properties">
		   						<option >${properties.pageValue}</option>
		   					</c:forEach>
		         		</select>
		         	    申请状态:<select name="holidayStatus" id="holidayStatus">
		   					<option value="">请选择</option> 
		   					<option value="1">草稿</option>
		   					<option value="2">已提交</option>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	        <img alt="" src="img/add.png" width="18px" height="18px" id="add">申请假期
	        </div><br/>
	        
         	<table class="holidayInfo">
         		<tr class="titleRow">
         			<td>请假编号</td>
         			<td>申请人</td>
         			<td>请假类型</td>
         			<td>请假事由</td>
         			<td>开始时间</td>
         			<td>结束时间</td>
         			<td>申请状态</td>
         			<td>提交时间</td>
         			<td>审批记录</td>
         			<td>操作列表</td>
         		</tr>
         		
				<c:forEach items="${pageModel.dataList}" var="holiday">
		         		<tr >
		         			<td>${holiday.holidayNo}</td>
		         			<td>${holiday.holidayUser}</td>
		         			<td>${holiday.holidayType}</td>
		         			<td>${holiday.holidayBz}</td>
		         			<td>${holiday.startTime}</td>
		         			<td>${holiday.endTime}</td>
		         			<c:if test="${holiday.holidayStatus  == '1'}">
		         				<td>草稿</td>
		         			</c:if>
		         			<c:if test="${holiday.holidayStatus  == '2'}">
		         				<td>已提交</td>
		         			</c:if>
		         			<td>${holiday.createTime}</td>
		         			<td><a href="queryHistoryAudit.do?workID=1&tableID=${holiday.holidayNo}">查看</a></td>
		         			<td width="100px">
		         				<img alt="" src="img/delete.png" class="operateImg" onclick="del('${holiday.holidayNo}','${holiday.holidayStatus }','${holiday.holidayUser}')">
		         				<img alt="" src="img/edit.png" class="operateImg" onclick="edit('${holiday.holidayNo}','${holiday.holidayStatus }','${holiday.holidayUser}')">
		         				<img alt="" src="img/detail.png" class="operateImg" onclick="showDetail('${holiday.holidayNo}')">
		         				<c:if test="${user.userRole != 2}">
         							<img alt="" src="img/calendar_edit.png" class="operateImg" onclick="auditBefor('${1}','${holiday.holidayNo}')">
         						</c:if>
		         			</td>
		         		</tr>
				</c:forEach>
				
				<tr height="40px">
					<td colspan="10">
						<c:if test="${flag == 1}">
							<a href="queryHolidayByBtn.do?pageNo=${pageModel.firstPage}&holidayUser=${holidayUser}&holidayType=${holidayType}&holidayStatus=${holidayStatus}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryHolidayByBtn.do?pageNo=${pageModel.prePage}&holidayUser=${holidayUser}&holidayType=${holidayType}&holidayStatus=${holidayStatus}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryHolidayByBtn.do?pageNo=${pageModel.nextPage}&holidayUser=${holidayUser}&holidayType=${holidayType}&holidayStatus=${holidayStatus}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryHolidayByBtn.do?pageNo=${pageModel.lastPage}&holidayUser=${holidayUser}&holidayType=${holidayType}&holidayStatus=${holidayStatus}">尾页</a>&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
						</c:if>
						<c:if test="${flag == 2}">
							<a href="queryAllHolidays.do?pageNo=${pageModel.firstPage}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryAllHolidays.do?pageNo=${pageModel.prePage}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryAllHolidays.do?pageNo=${pageModel.nextPage}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="queryAllHolidays.do?pageNo=${pageModel.lastPage}">尾页</a>&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
						</c:if>
					</td>
				</tr>
         	</table>
  </body>
</html>
