<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<link href="CSS/login.css" rel="stylesheet">
</head>
<body>
<!-- Top content -->
<div class="top-banner">
	<img src="image/logo.png">
</div>
<div class="container sign_up_container">
	<!-- Nav tabs -->
	<ul class="nav nav-tabs nav-justified">
		<li class="active"><a href="#person" data-toggle="tab">个人用户注册</a></li>
		<li><a href="#enterprise" data-toggle="tab">企业用户注册</a></li>
	</ul>
	<!-- Tab panes -->
	<div class="tab-content" style="padding:20px 80px">
		<!-- 个人用户注册 -->
		<div class="tab-pane active" id="person">
			<form id="personForm" class="form-horizontal" action="login/signUp" method="post" accept-charset="utf-8">
				<h4 id="errorMsg" style="color:red">${errorMsg}</h4>
				<input type="hidden" name="roleId" value="1"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="userName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="rePassword">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="telephone">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">短信验证码</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="telephone">
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-primary">获取短信验证码</button>
					</div>
				</div>
				<div class="checkbox col-sm-offset-2">
					<label>
						<input type="checkbox"> 我已阅读并同意相关
					</label>
					<a href="">《服务协议》</a>
				</div>
				<br/>
				<div class="row">
					<div class="col-sm-7 col-sm-offset-3">
						<button type="submit" class="btn btn-primary btn-lg">立即注册</button>
					</div>
				</div>
			</form>
		</div>
		<!-- 企业用户注册 -->
		<div class="tab-pane fade" id="enterprise">
			<form id="enterpriseForm" class="form-horizontal" action="login/signUp" method="post" accept-charset="utf-8">
				<h4 id="errorMsg" style="color:red">${errorMsg}</h4>
				<input type="hidden" name="roleId" value="2"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="userName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">企业名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="enterpriseName">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="rePassword">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">手机号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="telephone">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">短信验证码</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="telephone">
					</div>
					<div class="col-sm-3">
						<button type="button" class="btn btn-primary">获取短信验证码</button>
					</div>
				</div>
				<div class="checkbox col-sm-offset-2">
					<label>
						<input type="checkbox"> 我已阅读并同意相关
					</label>
					<a href="">《服务协议》</a>
				</div>
				<br/>
				<div class="row">
					<div class="col-sm-7 col-sm-offset-3">
						<button type="submit" class="btn btn-primary btn-lg">立即注册</button>
					</div>
				</div>
			</form>
		</div>
  </div>
</div>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="JS/main/signUp.js"></script>
</body>
</html>

