<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-加工工厂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/enterprise-detail.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="enterprise">
<%@ include file="top.jsp" %>
<div class="page-header">
	<h2>
		威凯顿服饰加工厂<small><span class="label" style="margin-left:10px;background-color:#FF842F">资质认证</span></small>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" style="">QQ在线交流</button>
		</div>
	</h2>
</div>
<div class="page-body">
	<nav class="navbar navbar-default">
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">工厂详情</a></li>
			<li><a href="#">样品展示</a></li>
		</ul>
	</nav>
	<div class="row">
		<!-- 左边栏 -->
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					 <h3 class="panel-title"><span class="glyphicon glyphicon-volume-up"></span> 工厂信息</h3>
				</div>
				<div class="panel-body">
					<div class="media">
						<div class="media-left">
							<img class="media-object" src="image/enterprise-icon.png">
						</div>
						<div class="media-body">
							<h4 class="media-heading">工厂名称</h4>
						</div>
					</div>
					<p style="margin-top:10px;">联 系 人:章先生</p>
					<p>联系电话：<a class="btn btn-xs" href="login.jsp">登录后查看</a></p>
					<p>QQ 号码：<a class="btn btn-xs" href="login.jsp">登录后查看</a></p>
					<p>加工类型：清加工</p>
					<p>经营期限：10年</p>
					<p>员工人数：100人</p>
					<p>主营产品：女裙、西裤</p>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-heading">
					 <h3 class="panel-title"><span class="glyphicon glyphicon-volume-up"></span> 地理位置</h3>
				</div>
				<div class="panel-body">
					<p>地址：<a class="btn btn-xs" href="login.jsp">登录后查看</a></p>
				</div>
			</div>
		</div><!-- 左边栏 -->
		<!-- 主体 -->
		<div class="col-md-9">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-6"></div>
					<div class="col-md-6">
						<b>生产工人:</b>
						<p>工人61人，生产流水线2条</p>
						<b>生产设备:</b>
						<p>工人61人，生产流水线2条</p>
						<b>接单类型:</b>
						<p>工人61人，生产流水线2条</p>
						<b>销售市场:</b>
						<p>工人61人，生产流水线2条</p>
						<b>合作客户:</b>
						<p>工人61人，生产流水线2条</p>
					</div>
				</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-body">
					工厂介绍
				</div>
			</div>
		</div><!-- 主体 -->
		
		
	</div><!-- row -->
</div>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/enterprise.js"></script>
</body>
</html>

