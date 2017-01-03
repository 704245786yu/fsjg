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
<title>服饰加工网-行业咨询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

</head>

<body>
<%@ include file="top.jsp" %>

<div style="width:1190px; margin:0 auto;">
	<div class="panel panel-default">
		<input id="totalRows" type="hidden" value="${result.total}">
		<ul class="list-group">
			<li class="list-group-item"><a href="0">查看所有宝贝</a></li>
			<li class="list-group-item"><a href="0">查看所有宝贝</a></li>
			<c:forEach items="${result.rows}" var="tradeNews">
				<div class="col-md-3 col-xs-3">
					<a target="_blank" href="costumeSample/showDetail/${costumeSample.num}">
						<img src="uploadFile/costumeSample/${costumeSample.img}" style="width:100%;height:200px;">
						<p>${costumeSample.name}</p>
						<p><span>${costumeSample.enterpriseName}</span></p>
					</a>
				</div>
			</c:forEach>
		</ul>
	</div>
</div>
<%@ include file="/JSP/main/bottom.jsp"%>
</body>
</html>

