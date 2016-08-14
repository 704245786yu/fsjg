<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div class="top-banner">
	<a href="">
		<img src="image/logo.png">
	</a>
</div>
<div class="background_div">
	<div class="col-sm-3 col-sm-offset-8 form-box">
		<h4>用户登录</h4>
		<form class="form-group" action="login/login" method="post">
			<h4 id="signUpStatus" style="color:#008FDE">
				<c:if test="${param.signUpStatus == 200}">
					注册成功，请登录
				</c:if> 
			</h4>
			<h4 id="errorMsg" style="color:red">${errorMsg}</h4>
			<div class="form-group form-group-lg">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-user"></span>
					</span>
					<input type="text" name="userName" placeholder="用户名" class="form-control" id="userName">
				</div>
			</div>
			<br/>
			<div class="form-group form-group-lg">
				<div class="input-group">
					<span class="input-group-addon">
						<span class="glyphicon glyphicon-lock"></span>
					</span>
					<input type="password" name="password" placeholder="密码" class="form-control" id="password">
				</div>
			</div>
			<div class="checkbox">
				<label>
					<input type="checkbox"> 记住密码
				</label>
				<div style="float:right;"><a href="login/showSignUp">注册</a></div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<button type="submit" class="btn btn-primary btn-lg">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/login.js"></script>
</body>
</html>

