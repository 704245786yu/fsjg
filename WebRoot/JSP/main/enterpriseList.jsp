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
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/enterprise-main.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>

<div class="container-fluid">
	<nav class="navbar navbar-default">
	<div class="navbar-header">
        <span class="navbar-brand glyphicon glyphicon-home"></span>
    </div>
		
	</nav>
	<div class="row">
		<!-- 主体 -->
		<div class="col-md-9">
		</div><!-- 主体 -->
		
		<!-- 右边栏 -->
		<div class="col-md-3">
		</div><!-- 右边栏 -->
		
	</div><!-- row -->
</div><!-- container-fluid -->
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/enterprise.js"></script>
</body>
</html>

