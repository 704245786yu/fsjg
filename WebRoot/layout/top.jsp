<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>顶部边栏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<style type="text/css">
		body{background:#f0f0f0;}
	</style>
</head>
  
<body>
	<div style="margin:0">
		<h3>
  			中国服饰加工网后台管理
	   		<div style="float:right;margin-right:20px;font-weight:normal;font-size:16px;">
	   			<span class="label label-primary" style="font-size:14px;margin-right:20px;">${loginMngUser.realName}</span>
		   		<a href="login/mngLogout" style="text-decoration:none;">退出</a>
	   		</div>
		</h3>
	</div>
</body>
</html>
