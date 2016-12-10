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
<title>服饰加工网-服饰样品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bs_pagination/jquery.bs_pagination.min.css" rel="stylesheet">
<link href="CSS/indent-list.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="contractor">
<%@ include file="/JSP/main/common/commonData.jsp" %>
<span style="display:none;" id="tradeAndCostumeMap">${applicationScope.tradeAndCostumeMap}</span>
<span style="display:none;" id="adPositions">${adPositions}</span>

<table style="width:1190px;margin:0 auto;bakcground-color:#FBF8F9;">
<tr>
	<td colspan="2">
		<div class="crumb">
			<span class="glyphicon glyphicon-home"></span>
			当前位置：<a href="contractor">服饰样品</a>
		</div>
	</td>
</tr>
<tr>
	<td style="width:906px;background-color:#FBF8F9;vertical-align:top;">
		<h4 style="text-align:center;">功能正在开发测试中，敬请期待</h4>
		<div style="height:500px;"></div>
	</td>
</tr>
</table>

<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bs_pagination/jquery.bs_pagination.min.js"></script>
<script src="plugin/bs_pagination/localization/en.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
</body>
</html>

