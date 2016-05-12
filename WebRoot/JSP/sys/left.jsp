<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">   
    <title>主页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

	<link href="plugin/jquery-accordion-menu/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
	<!-- <link href="plugin/font-awesome.css" rel="stylesheet" type="text/css" /> -->
	<link href="CSS/leftframe.css" rel="stylesheet" type="text/css" />
</head>
  
<body>
	<div class="content">
	<div id="jquery-accordion-menu" class="jquery-accordion-menu gray">
		<div class="jquery-accordion-menu-header" id="form"></div>
		<ul id="demo-list">   
		</ul>
	</div>
	</div>
</body>
<script src="plugin/jquery.min.js" type="text/javascript"></script>
<script src="plugin/jquery-accordion-menu/jquery-accordion-menu.js" type="text/javascript"></script>
<script src="JS/sys/left.js" type="text/javascript"></script>

</html>
