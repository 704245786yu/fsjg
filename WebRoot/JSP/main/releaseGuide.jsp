<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>服饰加工网</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

</head>

<body>
<%@ include file="top.jsp" %>

<div style="width:1190px; margin:0 auto;">
	<div class="panel panel-default">
		<div class="panel-body" style="text-align:center;">
			
		</div>
	</div>
</div>
<%@ include file="/JSP/main/bottom.jsp"%>
</body>
</html>

