<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>

  </head>
  
  <script type="text/javascript">
  $(function(){
  	  $("#codeImg").click(function(){
  	       //alert($(this).attr("src"));
  	       //不生效，因为重新设置的src和原来的一致，则不会提交给服务器
  	       //$(this).attr("src","codeServlet");
  	       //想让src和原来的不一致，保证有codeServlet，使用时间戳
  	       var date  = new Date();
  	       $(this).attr("src","getCertPic.do?date=" + date);
  	  });

  	  $("#loginBtn").click(function(){
			var param = "userName=" + $("#userName").val()+
						"&password=" + $("#password").val() +
						"&code=" + $("#code").val();
			$.ajax({
				type:"POST",
				url:"login.do",
				data:param,
				dataType:"text",
				success:function(result){
					//alert(result);
					//登录成功 0
					//登录失败 1.验证码不正确 2.用户名或者密码错误
					if(result == "0"){
						//重定向到main.jsp
						location.href = "njwb/main.jsp";
						return;
					}else if(result == "1"){
						//验证码不正确
						$("#errorMsg").html("验证码不正确");
					}else if(result == "2"){
						//用户名或者密码错误
						
						$("#errorMsg").html("用户名或者密码错误");
					}else if(result == "3"){
						//帐号已经注销
						$("#errorMsg").html("该帐号已经注销");
					}

					$("#errorMsg").css("color","red");
  	  	   			$("#codeImg").attr("src","getCertPic.do?date=" + new Date);
  	  	   			$("#code").val("");
				}
			});
  	  });
  });
  </script>
  <body>
     <div id = "login">
     	  <div id = "title">
     	  		NJWB管理系统
     	  </div>
     	  
     	  <table id="loginTable">
     	  		<tr>
     	  			<td>用户名:&nbsp;</td>
     	  			<td>
     	  				<input type= "text" name = "userName" id = "userName" value=""/>
     	  			</td>
     	  			<td>&nbsp;</td>
     	  		</tr>
     	  		
     	  		<tr>
     	  			<td>密&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
     	  			<td>
     	  				<input type= "password" name = "password" id = "password" value=""/>
     	  			</td>
     	  			<td>&nbsp;</td>
     	  		</tr>
     	  		
      	  		<tr>
     	  			<td>验证码:&nbsp;</td>
     	  			<td>
     	  				<input type= "text" name = "code" id = "code"/><br/>
     	  			</td>
     	  			<td>
     	  				&nbsp;
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr> 
     	  			<td colspan="2">
     	  				<img alt="" src="getCertPic.do" id="codeImg">点击换一张
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
     	  			<td colspan="2" align="center">
     	  				<input type= "button" value="登&nbsp;录" class="btn" id="loginBtn"/>
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
					<td colspan="2"><span id="errorMsg"></span></td>
				</tr>
     	  </table>
     </div>
  </body>
</html>
