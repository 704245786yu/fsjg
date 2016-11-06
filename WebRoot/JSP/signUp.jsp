<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服饰加工网-注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="CSS/login.css" rel="stylesheet">
<style type="text/css">
a:hover{
text-decoration:none;
}
</style>
</head>
<body>
<!-- Top content -->
<div class="top-banner">
	<a href="">
		<img src="image/logo.png">
	</a>
</div>
<div class="background_div">
<div class="col-sm-3 col-sm-offset-8 form-box" style="margin-top:40px;">
	<!-- Nav tabs -->
	<!-- <ul class="nav nav-tabs nav-justified">
		<li class="active"><a href="#person" data-toggle="tab">个人用户注册</a></li>
		<li><a href="#enterprise" data-toggle="tab">企业用户注册</a></li>
	</ul> -->
	<a href="#person" data-toggle="tab">个人注册</a> <a href="#enterprise" data-toggle="tab" style="margin-left:20px;">工厂注册</a>
	<div style="float:right;">
		<a href="login.jsp">登录</a>
	</div>
	<!-- Tab panes -->
	<input type="hidden" name="userType" value="${param.userType}">
	<div class="tab-content" style="padding:20px">
		<!-- 个人用户注册 -->
		<div class="tab-pane active" id="person">
			<form id="personForm" class="form-horizontal" action="login/signUp" method="post" autocomplete="off">
				<h4 id="errorMsg" style="color:red">${param.errorMsg}</h4>
				<input type="hidden" name="roleId" value="1"/>
				<div class="form-group">
					<input type="text" class="form-control" name="userName" placeholder="用户名">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="password" placeholder="密码">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" name="rePassword" placeholder="确认密码">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="telephone" placeholder="手机号">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="smsNum" placeholder="短信验证码" style="display:inline;width:54%">
					<button type="button" class="btn btn-primary" style="display:inline;width:40%" onclick="getSmsNum(this)">获取短信验证码</button>
				</div>
				<div class="form-group">
					<div class="checkbox">
						<label>
							<input type="checkbox" name="agree"> 我已阅读并同意相关
						</label>
						<a href="">《服务协议》</a>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-sm-12">
						<button type="submit" class="btn btn-primary btn-lg">立即注册</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 企业用户注册 -->
		<div class="tab-pane fade" id="enterprise">
			<form id="enterpriseForm" class="form-horizontal" action="login/signUp" method="post" autocomplete="off">
				<h4 id="errorMsg" style="color:red">${param.errorMsg}</h4>
				<input type="hidden" name="roleId" value="2"/>
				<div class="form-group">
						<input type="text" class="form-control" name="userName" placeholder="用户名">
				</div>
				<div class="form-group">
						<input type="text" class="form-control" name="enterpriseName" placeholder="企业名称">
				</div>
				<div class="form-group">
						<input type="password" class="form-control" name="password" placeholder="密码">
				</div>
				<div class="form-group">
						<input type="password" class="form-control" name="rePassword" placeholder="确认密码">
				</div>
				<div class="form-group">
						<input type="text" class="form-control" name="telephone" placeholder="手机号">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="smsNum" placeholder="短信验证码" style="display:inline;width:54%">
					<button type="button" class="btn btn-primary" style="display:inline;width:40%" onclick="getSmsNum(this)">获取短信验证码</button>
				</div>
				<div class="form-group">
					<div class="checkbox">
						<label>
							<input type="checkbox" name="agree"> 我已阅读并同意相关
						</label>
						<a href="">《服务协议》</a>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-sm-12">
						<button type="submit" class="btn btn-primary btn-lg">立即注册</button>
					</div>
				</div>
			</form>
		</div>
  </div>
</div>
</div>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="JS/main/signUp.js"></script>
</body>
</html>

