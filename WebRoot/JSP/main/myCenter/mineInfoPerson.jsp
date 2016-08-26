<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-个人中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/mine-info.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="../top.jsp"%>
<table class="table table-bordered" style="width:1190px; margin:0 auto;">
	<tr>
		<td colspan="2">
			<!-- 用户基本信息 -->
			<table id="basic-info" class="table">
				<tr>
					<td rowspan="3" style="width:200px;text-align:center;">
						<img src="image/enterpriseDetail/headphoto.png">
					</td>
					<td>
						<label>帐号名称：</label>${userInfo.basicUser.userName}
					</td>
					<td>
						<label>姓名：</label>${userInfo.realName}
					</td>
					<td>
						<label>电子邮箱：</label>${userInfo.email}
					</td>
				</tr>
				<tr>
					<td><label>手机号码：</label>${userInfo.basicUser.telephone}</td>
					<td><label>身份证号：</label>${userInfo.idCard}</td>
				</tr>
				<tr>
					<td colspan="3" class="auth">
						<span><img src="image/enterpriseDetail/email_tg.png">邮箱认证</span>
						<span><img src="image/enterpriseDetail/telephone_tg.png">手机认证</span>
						<span><img src="image/enterpriseDetail/realname_tg.png">实名认证</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<!-- 左边菜单 -->
		<td style="padding:0px;width:200px;background-color:#FCF9FA;">
			<ul class="menu_li">
				<li class="level1">帐号管理</li>
				<li>详细信息</li>
				<li>修改密码</li>
				<li class="level1">个人管理</li>
				<li>快产信息</li>
				<li class="level1">发单管理</li>
				<li>我发布的订单</li>
				<li>我收到的报价</li>
				<li>我确认单的订单</li>
			</ul>
		</td>
		<!-- 主体内容 -->
		<td>
			<%@ include file="personInfo.jsp"%>
		</td>
	</tr>
</table>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

