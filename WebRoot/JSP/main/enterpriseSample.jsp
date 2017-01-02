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
<title>服饰加工网-加工工厂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/awesome-bootstrap-checkbox/Font-Awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="plugin/bs_pagination/jquery.bs_pagination.min.css" rel="stylesheet">
<link href="CSS/enterprise-sample.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top2.jsp"%>
<input id="enterpriseNum" type="hidden" value="${enterprise.number}">
<div class="panel panel-default" style="margin-bottom:0px;">
	<div id="detail-head" class="panel-body" style="background-color:#FCFCFC">
		<h2>
			${enterprise.enterpriseName}
			<c:if test="${enterprise.auditState==2}">
				<small><span class="label" style="margin-left:10px;background-color:#FF842F">资质认证</span></small>
			</c:if>
		</h2>
		<div class="pull-right">
			<button type="button" class="btn btn-primary" style="background-color:#4fb3e8">QQ在线交流</button>
		</div>
	</div>
</div>

<nav class="topNav">
	<div style="width:1190px;margin:0 auto;">
		<ul class="nav navbar-nav" >
			<li name="li-home"><a target="_blank" href="enterprise/showDetail/${enterprise.id}">店铺首页</a></li>
			<li name="li-sample" style="background-image:url('image/nav-orange.png');"><a>样品展示</a></li>
		</ul>
	</div>
</nav>

<table style="width:1190px;margin:0px auto;background-color:#FBF8F9">
<tr>
	<td colspan="2" style="background-color:white;">
		<div class="crumb">
			<span class="glyphicon glyphicon-home"></span>
			当前位置：<a href="enterprise">加工工厂</a> > <a href="enterprise/search">工厂列表页</a> > 工厂详情页
		</div>
	</td>
</tr>
<tr>
	<td style="width:280px;padding-right:5px;vertical-align:top;">
		<!-- 左边栏 -->
		<span id="costumeCateMap" style="display:none;">${costumeCateMap}</span>
		<ul class="list-group">
			<li class="list-group-item"><h3 class="panel-title" style="color:#008AE6;"><span class="glyphicon glyphicon-volume-up"></span> 产品类别</h3></li>
			<li class="list-group-item"><a href="0" onclick="return querySample(this)">查看所有宝贝</a></li>
		</ul>
		<!-- 类目Demo -->
		<div id="costumeCateLi" style="display:none;">
			<li class="list-group-item">
				<!-- 二级类目 -->
				<a class="faSquare" href="#">
					<i class="fa fa-plus-square-o"></i>
				</a> <a name="secCate" onclick="return querySample(this)"></a>
				<!-- 三级类目 -->
				<div style="padding-left:25px;display:none">
					<!-- <div><a href="#">内裤</a></div> -->
				</div>
			</li>
		</div>
		<!-- <div class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title" style="color:#008AE6;"><span class="glyphicon glyphicon-volume-up"></span> 最新上架</h3>
			</div>
			<div class="panel-body">
			</div>
		</div> -->
	</td>
	<td style="padding-left:5px;vertical-align:top;">
		<!-- 主体 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div id="list" class="row">
					<c:forEach items="${result.rows}" var="costumeSample">
						<div class="col-md-3 col-xs-3">
							<a target="_blank" href="costumeSample/showDetail/${costumeSample.num}">
								<img src="uploadFile/costumeSample/${costumeSample.img}" style="width:100%;height:200px;">
								<p>${costumeSample.name}</p>
							</a>
						</div>
					</c:forEach>
				</div>
				<div id="template" style="display:none">
					<div class="col-md-3 col-xs-3">
						<a target="_blank" href="javascript:void(0);">
							<img src="" style="width:100%;height:200px;">
							<p></p>
						</a>
					</div>
				</div>
				<input id="totalRows" type="hidden" value="${result.total}">
				<div id="bsPagination" style="text-align:center"></div>
			</div>
		</div>
	</td>
</tr>
</table>
<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bs_pagination/jquery.bs_pagination.min.js"></script>
<script src="plugin/bs_pagination/localization/en.js"></script>
<script src="JS/main/enterpriseSample.js"></script>
</body>
</html>

