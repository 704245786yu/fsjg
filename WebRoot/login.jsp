<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/login.css" rel="stylesheet">
</head>
<body>
<!-- Top content -->
<!-- <div class="top-banner">
	<img src="image/logo.png">
</div> -->
<div class="container form-box">
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2 form-box-content">
			<h2>用户登录</h2>
			<form class="form-group" action="login/loginCheck" method="post">
				<h4 id="errorMsg" style="display:none;color:red">用户名或密码错误</h4>
				<div class="form-group form-group-lg">
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-user"></span>
						</span>
						<input type="text" name="userName" placeholder="用户名" class="form-control" id="userName">
					</div>
				</div>
				<div class="form-group form-group-lg">
					<div class="input-group">
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-lock"></span>
						</span>
						<input type="password" name="password" placeholder="密码" class="form-control" id="password">
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<button type="submit" class="btn btn-primary btn-lg">登 录</button>
					</div>
					<div class="col-sm-6">
						<button type="button" class="btn btn-warning btn-lg">注册</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/login.js"></script>
</body>
</html>

