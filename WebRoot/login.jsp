<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<base href="<%=basePath%>">
	<title>信息发布系统-登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="stylesheet" href="plugin/login/css/reset.css">
	<link rel="stylesheet" href="plugin/login/css/supersized.css">
	<link rel="stylesheet" href="plugin/login/css/style.css">
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>
<body>
	<div class="page-container">
		<h1>登录</h1>
		<form action="" id="ff" method="post">
			<div>
				<input type="text" name="userName" class="username" placeholder="用户名" autocomplete="off"/>
			</div>
			<div>
				<input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
			</div>
			<div id="errorMsg" style="margin-top:5px;color:#a94442;font-size:85%;">
			</div>
			<button id="submit" type="button">登 录</button>
		</form>
	</div>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/login/js/supersized.3.2.7.min.js"></script>
<script src="plugin/login/js/supersized-init.js"></script>
<script src="JS/sys/login.js"></script>
</body>
</html>

