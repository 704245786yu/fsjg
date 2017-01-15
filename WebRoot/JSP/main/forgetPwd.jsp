<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>服饰加工网-忘记密码</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
<style>
.circle{
	background-color:#CCCCCC;
	color:white;
	border-radius:50%;
	padding:0px;
	width:20px;
	height:20px;
	text-align:center;
}
table{
	width:80%;
	text-align:center;
	margin:0 auto;
}
table td{
	width:20%;
	border-bottom:2px solid #CCCCCC;
	padding-bottom:10px;
}
table b{
	font-size:16px;
	margin-left:5px;
}
</style>

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<div style="width:1190px;margin:0 auto;bakcground-color:grey;">
	<div class="panel panel-default">
		<div class="panel-body">
			<h4 style="color:#00B8EF">找回密码</h4>
			<table id="stepTable">
				<tr>
					<td></td>
					<td style="border-bottom:2px solid #FE6618;"><span class="circle" style="background-color:#FE6618;">&nbsp;1&nbsp;</span><b>填写手机号</b></td>
					<td><span class="circle">&nbsp;2&nbsp;</span><b>设置新密码</b></td>
					<td><span class="glyphicon glyphicon-ok-sign"></span><b>成功</b></td>
					<td></td>
				</tr>
			</table><br>
			<form id="ff" class="form-horizontal" action="checkSmsCode" method="post" style="width:400px;margin:0 auto;" autocomplete="off">
				<div class="form-group">
					<label class="col-sm-3 control-label">手机号</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="telephone">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">短信验证码</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" name="smsNum" style="display:inline;width:54%">
						<button type="button" class="btn btn-primary" style="display:inline;width:42%" onclick="getSmsNum(this)">获取短信验证码</button>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-primary">下一步</button>
					</div>
				</div>
			</form>
			<form id="ff2" class="form-horizontal" action="basicUser/modifyPwdByTele" method="post" style="width:400px;margin:0 auto;display:none;" autocomplete="off">
				<input type="hidden" name="telephone">
				<div class="form-group">
					<label class="col-sm-3 control-label">新密码</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" name="password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">确认密码</label>
					<div class="col-sm-9">
						<input type="password" class="form-control" name="rePassword">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button type="submit" class="btn btn-primary">确定</button>
					</div>
				</div>
			</form>
			<div id="successDiv" style="width:400px;margin:30px auto;display:none;">
				<span style="font-size:16px;">恭喜您，密码设置成功，系统<span id="intervalSec" style="color:red;">5</span>秒后返回到登录页面</span>
			</div>
		</div>
	</div>
</div>

<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.form.min.js"></script>
<script src="JS/main/forgetPwd.js"></script>
</body>
</html>

