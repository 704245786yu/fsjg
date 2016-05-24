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
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link rel="stylesheet" href="plugin/bootstrap-login-form/css/form-elements.css">
	<link rel="stylesheet" href="plugin/bootstrap-login-form/css/style.css">
	<!-- <link rel="stylesheet" href="plugin/login/css/supersized.css"> -->
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
                            	<p><b>Chinese apparel processing network platform management background</b></p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登录</h3>
                            		<p>请输入你的用户名和密码进行登录:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-lock"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" name="userName" placeholder="用户名" class="form-username form-control" id="userName">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="password" placeholder="密码" class="form-password form-control" id="password">
			                        </div>
			                        <button type="submit" class="btn">登 录</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>

<!-- 	<div style="padding:100px 0 170px 0;">
		<div class="container">
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
	</div> -->

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/bootstrap-login-form/js/jquery.backstretch.min.js"></script>
<script src="plugin/bootstrap-login-form/js/scripts.js"></script>

<!-- <script src="JS/sys/login.js"></script> -->
</body>
</html>

