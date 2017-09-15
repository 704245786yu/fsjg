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
<title>服饰加工网-活动推广</title>
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

#districtBtn{
	width:320px;
	overflow:hidden;
	background: url('image/select-btn.png') no-repeat 90%;
}
</style>
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="activity">
<span style="display:none;" id="adPositions">${adPositions}</span>
<table style="width:1190px;margin:0 auto;">
<tr>
	<td style="width:906px;vertical-align:top;">
		<div class="crumb">
			<span class="glyphicon glyphicon-home"></span>
			当前位置：<a href="#">首页</a> > 活动推广 
		</div>
		<div class="panel panel-default search-panel">
			<div class="panel-body">
				<table>
					<tr>
						<td><b>活动类型：</b></td>
						<td style="padding-bottom:10px;">
							<select class="form-control" name="type">
								<option value="">全部</option>
								<option value="1">开业</option>
								<option value="2">促销</option>
								<option value="3">库存</option>
								<option value="4">活动</option>
								<option value="5">求购</option>
								<option value="6">二手旧货</option>
							</select>
						</td>
					</tr>
					<tr>
						<td><b>所在地区：</b></td>
						<td>
							<button id="districtBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#districtModal">选择地区</button>
							<!-- 选择地区模态框 -->
							<div class="modal fade" id="districtModal" tabindex="-1">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
											<h5 class="modal-title" id="myModalLabel">选择地区</h5>
										</div>
										<div id="districtContainer" class="modal-body">
											<div class="row">
												<div class="col-sm-3">
													<select class="form-control" id="province" name="province"></select>
												</div>
												<div class="col-sm-3">
													<select class="form-control" id="city" name="city"></select>
												</div>
												<div class="col-sm-3">
													<select class="form-control" id="county" name="county"></select>
												</div>
												<div class="col-sm-3">
													<select class="form-control" id="town" name="town"></select>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="checkDistrict()">确定</button>
											<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="panel panel-default" style="padding:20px;">
			<input id="totalRows" type="hidden" value="${result.total}">
			<table class="table table-hover" id="list">
				<thead style="font-size:16px;">
					<th>发布地址</th>
					<th style="text-align:center;">活动类型</th>
					<th style="text-align:center;">活动内容</th>
					<th style="text-align:center;">活动日期</th>
				</thead>
				<c:forEach items="${result.rows}" var="activity">
					<tr onclick="window.open('activity/showDetail/${activity.id}')">
						<td>${activity.detailAddr}</td>
						<td style="text-align:center;">
							<c:choose>
							   <c:when test="${activity.type==1}">开业</c:when>
							   <c:when test="${activity.type==2}">促销</c:when>
							   <c:when test="${activity.type==3}">库存</c:when>
							   <c:when test="${activity.type==4}">活动</c:when>
							   <c:when test="${activity.type==5}">求购</c:when>
							   <c:when test="${activity.type==6}">二手旧货</c:when>
							</c:choose>
						</td>
						<td style="text-align:center;">${activity.title}</td>
						<td style="text-align:center;">${activity.duration}</td>
					</tr>
				</c:forEach>
			</table>
			<div id="bsPagination" style="text-align:center"></div>
		</div>
	</td>
	<td style="width:274px;vertical-align:top;padding-left:10px;">
		<div style="margin-bottom:20px;">
			<a class="ad" target="_blank"><img style="height:210px;width:280px;"/></a>
		</div>
		<div style="margin-bottom:20px;">
			<a class="ad" target="_blank"><img style="height:210px;width:280px;"/></a>
		</div>
		<div style="margin-bottom:20px;">
			<a class="ad" target="_blank"><img style="height:210px;width:280px;"/></a>
		</div>
		<div style="margin-bottom:20px;">
			<a class="ad" target="_blank"><img style="height:210px;width:280px;"/></a>
		</div>
	</td>
</tr>
</table>
<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bs_pagination/jquery.bs_pagination.min.js"></script>
<script src="plugin/bs_pagination/localization/en.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/activity.js"></script>
</body>
</html>

