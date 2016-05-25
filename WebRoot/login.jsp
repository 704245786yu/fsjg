<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrap-login-form/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="plugin/bootstrap-login-form/css/form-elements.css" rel="stylesheet">
<link href="plugin/bootstrap-login-form/css/style.css" rel="stylesheet">
</head>
<body>
	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>中国服饰加工平台管理平台</h1>
						<div class="description">
							<p>
								<b>Chinese apparel processing network platform management background</b>
							</p>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>登录</h3>
								<!-- <p>请输入你的用户名和密码进行登录:</p> -->
							</div>
							<div class="form-top-right">
								<i class="fa fa-key"></i>
							</div>
						</div>
						
						<div class="form-bottom">
							<form role="form" action="login/loginCheck" method="post" class="login-form">
								<h4 id="errorMsg" style="display:none;color:red">用户名或密码错误</h4>
								<div class="form-group">
									<label class="sr-only" for="form-username">用户名</label>
									<input
										type="text" name="userName" placeholder="用户名"
										class="form-username form-control" id="userName">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">密码</label>
									<input
										type="password" name="password" placeholder="密码"
										class="form-password form-control" id="password">
								</div>
								
								<!-- <h4><span class="label label-warning">用户名或密码错误</span></h4> -->
								<!-- <h4><span class="label label-danger">用户名或密码错误</span></h4> -->
								<button type="submit" class="btn">登 录</button>
							</form>
						</div><!-- form-bottom -->
						
					</div><!-- col-sm -->
				</div><!-- row -->
			</div><!-- container -->
		</div><!-- inner-bg -->
	</div><!-- top-content -->
<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrap-login-form/js/jquery.backstretch.min.js"></script>
<script src="plugin/bootstrap-login-form/js/login.js"></script>
</body>
</html>

