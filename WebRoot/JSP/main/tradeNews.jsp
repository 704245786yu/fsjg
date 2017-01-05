<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>服饰加工网-行业资讯</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bs_pagination/jquery.bs_pagination.min.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
<style>
/*面包屑*/
.crumb{
	border:1px solid #DDD;
	padding:5px;
	margin-bottom:10px;
}
.crumb span.glyphicon{
	font-size:16px;
	color:#4EB8ED;
	margin-right:10px;
}
.crumb a:hover{
	text-decoration:none;
}

#list > div{
	margin:10px 0px;
	font-size:16px;
}
</style>
</head>

<body>
<%@ include file="top.jsp" %>
<div style="width:1190px; margin:0 auto;">
	<div class="crumb">
		<span class="glyphicon glyphicon-home"></span>
		当前位置：<a href="#">首页</a> > 行业资讯 
	</div>
	<div class="panel panel-default" style="padding:20px;">
		<input id="totalRows" type="hidden" value="${result.total}">
		<div id="list">
			<c:forEach items="${result.rows}" var="tradeNews">
				<div>
					<a target="_blank" href="tradeNews/showDetail/${tradeNews.id}">${tradeNews.title}</a>
					<div style="float:right"><fmt:formatDate value="${tradeNews.updateTime}" pattern="yyyy-MM-dd HH:mm" /></div>
				</div>
			</c:forEach>
		</div>
		<div id="bsPagination" style="text-align:center"></div>
		<div id="template" style="display:none">
			<div>
				<a target="_blank" href="tradeNews/showDetail/"></a>
				<div style="float:right"></div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bs_pagination/jquery.bs_pagination.min.js"></script>
<script src="plugin/bs_pagination/localization/en.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="JS/main/tradeNews.js"></script>
</body>
</html>

