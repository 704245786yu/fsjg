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
			<table>
				<tr>
					<td></td>
					<td style="border-bottom:2px solid #FE6618;"><span class="circle" style="background-color:#FE6618;">&nbsp;1&nbsp;</span><b>填写手机号</b></td>
					<td><span class="circle">&nbsp;2&nbsp;</span><b>设置新密码</b></td>
					<td><span class="circle">&nbsp;3&nbsp;</span><b>成功</b></td>
					<td></td>
				</tr>
			</table>
			<form>
				<div class="form-group"><input type="text" class="form-control" name="telephone"></div>
			</form>
		</div>
	</div>
</div>

<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="JS/main/forgetPwd.js"></script>
</body>
</html>

