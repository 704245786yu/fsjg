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

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bs_pagination/jquery.bs_pagination.min.css" rel="stylesheet">
<link href="CSS/costume-sample.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="costumeSample">
<%@ include file="/JSP/main/common/commonData.jsp" %>

<table style="width:1190px;margin:0 auto;bakcground-color:#FBF8F9;">
<tr>
	<td colspan="2">
		<div class="crumb">
			<span class="glyphicon glyphicon-home"></span>
			当前位置：<a href="contractor">服饰样品列表</a> > 样品详情
		</div>
	</td>
</tr>
<tr>
	<td style="width:280px;padding-right:5px;vertical-align:top;">
		<!-- 左边栏 -->
		<div id="baseInfoDiv" class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title" style="color:#008AE6;"><span class="glyphicon glyphicon-volume-up"></span> 工厂信息</h3>
			</div>
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<img style="width:90px;height:90px;" class="media-object" src="uploadFile/enterprise/${enterprise.logo}">
					</div>
					<div class="media-body">
						<h5 class="media-heading">${enterprise.enterpriseName}</h5>
					</div>
				</div>
				<p style="margin-top:10px;">联 系 人：${enterprise.linkman}</p>
				<c:choose>
					<c:when test="${loginBasicUser==null}">
						<p>联系电话：<button type="button" class="btn btn-sm" data-toggle="modal" data-target="#loginModal">申请查看</button></p>
						<p>QQ 号码：<button type="button" class="btn btn-sm" data-toggle="modal" data-target="#loginModal">申请查看</button></p>
					</c:when>
					<c:when test="${loginBasicUser!=null}">
						<p>联系电话：${enterprise.basicUser.telephone}</p>
						<p>QQ 号码：${enterprise.qq}</p>
					</c:when>
				</c:choose>
				<p>加工类型：<input type="hidden" name="processType" value="${enterprise.processType}"><span id="processTypeText"></span></p>
				<p>经营期限：${enterprise.enterpriseAge}年</p>
				<p>员工人数：${enterprise.staffNumber}人</p>
				<p>主营产品：<c:forEach var="name" items="${costumeNames}">${name} </c:forEach></p>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title"><span class="glyphicon glyphicon-volume-up"></span> 地理位置</h3>
			</div>
			<div class="panel-body">
				<p>邮政编码：${enterprise.postalCode}</p>
				<p>地址：<c:forEach items="${districts}" var="district">${district}</c:forEach>${enterprise.detailAddr}</p>
			</div>
		</div>
	</td>
	<td style="padding-left:5px;">
		<!-- 主体 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="col-md-6">
					<!--图片展示开始-->
					<div>
						<input type="hidden" name="enterpriseImg" value="${enterprise.enterpriseImg}">
						<div id="preview" class="spec-preview">
							<span class="jqzoom">
								<img style="width:410px;height:260px;" jqimg="uploadFile/enterprise/default_big.png" src="uploadFile/enterprise/default_big.png" />
							</span>
						</div>
						<!--缩图开始-->
						<div class="spec-scroll"> <a class="prev">&lt;</a> <a class="next">&gt;</a>
							<div class="items">
								<ul id="imgUl">
									<!-- <li><img alt="佳能" bimg="plugin/jquery-arc-pic/images/s1.jpg" src="plugin/jquery-arc-pic/images/s1.jpg" onmousemove="preview(this);"></li> -->
								</ul>
							</div>
						</div>
						<!--缩图结束-->
					</div>
					<!--图片展示结束-->
				</div>
				<div class="col-md-6 plist" style="padding-left:50px;">
					<p>
						<b>所在行业:</b>
						<br/><input type="hidden" name="trade" value="${enterprise.trade}"><span id="tradeText"></span>
					</p>
					<p>
						<b>生产工人:</b>
						<br/>工人${enterprise.staffNumber}人
					</p>
					<p>
						<b>生产设备:</b>
						<br/>${enterprise.equipment}
					</p>
					<p>
						<b>销售市场:</b>
						<br/>
						<c:choose>
							<c:when test="${enterprise.saleMarket==0}">内销</c:when>
							<c:when test="${enterprise.saleMarket==1}">外销</c:when>
						</c:choose>
					</p>
					<p>
						<b>合作客户:</b>
						<br/>${enterprise.cooperator}
					</p>
				</div>
			</div>
		</div>
		
		<!-- 样品详情 -->
		<div class="panel panel-default">
			<div class="panel-body">
				${enterprise.description}
			</div>
		</div>
	</td>
</tr>
</table>

<%@ include file="/JSP/main/bottom.jsp"%>

<jsp:include page="loginModal.jsp">
	<jsp:param name="showCloseBtn" value="true"/>
</jsp:include>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/sampleDetail.js"></script>
</body>
</html>

