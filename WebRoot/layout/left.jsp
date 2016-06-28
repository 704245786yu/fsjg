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

	<link href="plugin/jquery-accordion-menu/jquery-accordion-menu.css" rel="stylesheet"/>
	<link href="plugin/awesome-bootstrap-checkbox/Font-Awesome/css/font-awesome.min.css" rel="stylesheet"/>
	<link href="layout/left.css" rel="stylesheet"/>
</head>
  
<body>
	<input id="path" type="hidden" value="<%=path%>"/>
	<div class="content">
		<div id="jquery-accordion-menu" class="jquery-accordion-menu gray">
			<div class="jquery-accordion-menu-header" id="form"></div>
			<ul id="demo-list">   
			</ul>
		</div>
	</div>
</body>
<script src="plugin/jquery.min.js"></script>
<script src="plugin/jquery-accordion-menu/jquery-accordion-menu.js"></script>
<script src="layout/left.js"></script>

</html>
