<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-发布订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="indent">

<div style="width:1190px; margin:0 auto;">
	<div class="panel panel-default">
		<div class="panel-body">
			<table>
				<tr>
					<td style="padding-right:10px;">
						<span class="glyphicon glyphicon-ok-sign" style="color:#B1D764;font-size:26px;"></span>
					</td>
					<td style="width:450px;">
						<span style="font-size:20px;">订单已发布成功！</span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						订单发布期间，您可以进入个人中心-我收到的报价查看工厂申请，请保持电话畅通。
					</td>
				</tr>
			</div>
		</div>
	</div>
</div>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/indent.js"></script>
</body>
</html>

