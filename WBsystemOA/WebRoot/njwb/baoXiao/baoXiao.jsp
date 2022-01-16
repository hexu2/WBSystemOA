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
    
    <title>报销管理</title>
  	
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
				location.href="queryBaoXiaoAdd.do?";
			});
		});
		
		//obj  删除图片
		function del(baoXiaoNo,baoXiaoStatus,baoXiaoUser){
			if(2 == baoXiaoStatus){
				alert("该请假记录已经提交不可以删除！");

			}else if(baoXiaoUser != '${userName}'){
				alert("申请人该记录尚未提交不可操作！");
			}else if(confirm("确定删除吗?")){
				//删除
				//alert("删除成功");
				location.href="baoXiaoDel.do?baoXiaoNo="+baoXiaoNo;
			}
		}
		
		//obj 修改图片
		function edit(baoXiaoNo,baoXiaoStatus,baoXiaoUser){
			
			if(2 == baoXiaoStatus){
				alert("该请假记录已经提交不可修改！");
			}else if(baoXiaoUser != '${userName}'){
				alert("申请人该记录尚未提交不可操作！");
			}else{
				location.href="queryBaoXiaoUpdate.do?baoXiaoNo="+baoXiaoNo;
			}
			
		}
	    
	    //obj  明细图片
	    function showDetail(baoXiaoNo){
		    //alert(empNo);
	    	location.href="queryBaoXiaoDetail.do?baoXiaoNo="+baoXiaoNo;
	    }

	    //审批
	    function auditBefor(workID,tableID){
	    	location.href="auditBefor.do?workID="+workID+"&tableID="+tableID;
	    }
	</script>
  </head>
  
  <body>
         	<h1 class="title">首页  &gt;&gt;报销管理 </h1>
         	<div class="add">
         		<form action="queryBaoXiaoByBtn.do" method="post" >
	         		<c:if test="${user.userRole != '2'}">
	         		 申请人:<input type="text"  size="20px" value=""  name="baoXiaoUser" id="baoXiaoUser"/> 
	         		</c:if>
	         		<c:if test="${user.userRole == '2'}">
	         		 申请人:<input type="text"  size="20px" value="${user.userName}" readonly="readonly" name="baoXiaoUser" id="baoXiaoUser"/> 
	         		</c:if>  
	         		
		         	    报销类型:<select name="baoXiaoType" id="baoXiaoType">
		   					<option value="">请选择</option> 
		   					<c:forEach items="${propertiesList}"  var="properties">
		   						<option >${properties.pageValue}</option>
		   					</c:forEach>
		         		</select>
		         		
		         	    申请状态:<select name="baoXiaoStatus" id="baoXiaoStatus">
		   					<option value="">请选择</option> 
		   					<option value="1">草稿</option>
		   					<option value="2">已提交</option>
		         		</select>
		         		<input type="submit" value="查询"/>
	         		<br/>
	         	</form>
	         	
	        <img alt="" src="img/add.png" width="18px" height="18px" id="add">申请报销
	        </div><br/>
	        
         	<table class="baoXiaoInfo">
         		<tr class="titleRow">
         			<td>报销编号</td>
         			<td>申请人</td>
         			<td>报销类型</td>
         			<td>报销金额</td>
         			<td>提交时间</td>
         			<td>申请状态</td>
         			<td>审批记录</td>
         			<td>操作列表</td>
         		</tr>
         		
				<c:forEach items="${pageModel.dataList}" var="baoXiao">
		         		<tr >
		         			<td>${baoXiao.baoXiaoNo}</td>
		         			<td>${baoXiao.baoXiaoUser}</td>
		         			<td>${baoXiao.baoXiaoType}</td>
		         			<td>${baoXiao.baoXiaoMoney}</td>
		         			<td>${baoXiao.applyTime}</td>
		         			<c:if test="${baoXiao.baoXiaoStatus  == '1'}">
		         			<td>草稿</td>
		         			</c:if>
		         			<c:if test="${baoXiao.baoXiaoStatus  == '2'}">
		         			<td>已提交</td>
		         			</c:if>
		         			<td><a href="queryHistoryAudit.do?workID=2&tableID=${baoXiao.baoXiaoNo}">查看</a></td>
		         			<td width="100px">
		         				<img alt="" src="img/delete.png" class="operateImg" onclick="del('${baoXiao.baoXiaoNo}','${baoXiao.baoXiaoStatus }','${baoXiao.baoXiaoUser}')">
		         				<img alt="" src="img/edit.png" class="operateImg" onclick="edit('${baoXiao.baoXiaoNo}','${baoXiao.baoXiaoStatus }','${baoXiao.baoXiaoUser}')">
		         				<img alt="" src="img/detail.png" class="operateImg" onclick="showDetail('${baoXiao.baoXiaoNo}')">
		         				<c:if test="${user.userRole != 2}">
         							<img alt="" src="img/calendar_edit.png" class="operateImg" onclick="auditBefor('${2}','${baoXiao.baoXiaoNo}')">
         						</c:if>
		         			</td>
		         		</tr>
				</c:forEach>
				
				<tr height="40px">
					<td colspan="9">
					<c:if test="${flag == 1}">
						<a href="queryBaoXiaoByBtn.do?pageNo=${pageModel.firstPage}&baoXiaoUser=${baoXiaoUser}&baoXiaoType=${baoXiaoType}&baoXiaoStatus=${baoXiaoStatus}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryBaoXiaoByBtn.do?pageNo=${pageModel.prePage}&baoXiaoUser=${baoXiaoUser}&baoXiaoType=${baoXiaoType}&baoXiaoStatus=${baoXiaoStatus}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryBaoXiaoByBtn.do?pageNo=${pageModel.nextPage}&baoXiaoUser=${baoXiaoUser}&baoXiaoType=${baoXiaoType}&baoXiaoStatus=${baoXiaoStatus}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryBaoXiaoByBtn.do?pageNo=${pageModel.lastPage}&baoXiaoUser=${baoXiaoUser}&baoXiaoType=${baoXiaoType}&baoXiaoStatus=${baoXiaoStatus}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</c:if>
					
					<c:if test="${flag == 2}">
						<a href="queryBaoXiaos.do?pageNo=${pageModel.firstPage}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryBaoXiaos.do?pageNo=${pageModel.prePage}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryBaoXiaos.do?pageNo=${pageModel.nextPage}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="queryBaoXiaos.do?pageNo=${pageModel.lastPage}">尾页</a>&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;<span>第${pageModel.pageNo}/${pageModel.totalPage }页</span>
					</c:if>

					</td>
				</tr>
         	</table>
  </body>
</html>
