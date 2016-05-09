<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>服饰加工</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
  
<body>
	<a href="person/manage">个人信息管理</a>
	<a href="user">后台用户管理</a>
	
	<a href="test/jqueryFileUpload.jsp">Fileupload Test</a>
</body>
<script src="jquery/jquery.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
</html>
