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
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/jquery-arc-pic/css/base.css" rel="stylesheet">
<link href="CSS/enterprise-detail.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top2.jsp"%>
<%@ include file="/JSP/main/common/commonData.jsp" %>
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
			<li name="li-home" style="background-image:url('image/nav-orange.png');"><a>店铺首页</a></li>
			<!-- <li name="li"><a>样品展示</a></li> -->
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
		
		<!-- 工厂描述 -->
		<div class="panel panel-default">
			<div class="panel-body">
				${enterprise.description}
			</div>
		</div>
		
		<!-- 档期 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<img src="image/enterpriseDetail/clock.png"><span style="margin-left:10px;color:#00B4E8;font-size:20px;">${enterprise.schedule}</span>
			</div>
		</div>
		
		<!-- 感兴趣的工厂 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title" style="color:#008AE6;"><span class="glyphicon glyphicon-volume-up"></span> 您可能感兴趣的工厂</h3>
			</div>
			<div class="panel-body">
				<div id="enterpriseList" class="row">
					<c:forEach items="${enterpriseList}" var="enterprise" varStatus="status">
						<div class="enterprise" style="width:300px;padding:0 3px;float:left;">
							<div class="panel panel-default">
								<div class="panel-body">
							    	<div class="media">
										<div class="media-left">
											<img class="media-object" style="width:90px;height:90px;" src="uploadFile/enterprise/default_logo.png">
										</div>
										<div class="media-body">
											<a target="_blank" href="enterprise/showDetail/${enterprise.id}"><h5 class="media-heading" style="font-weight:bold;">${enterprise.enterpriseName}</h5></a>
											<p class="list-group-item-text" style="font-size:12px;">员工人数：${enterprise.staffNumber}人</p>
											<p class="list-group-item-text" style="font-size:12px;">加工类型：<input type="hidden" name="processType" value="${enterprise.processType}"></p>
											<p class="list-group-item-text" style="font-size:12px;">所在地区：<c:forEach items="${disctricsList[status.count-1]}" var="disctric">${disctric} </c:forEach></p>
										</div>
										<p style="margin-top:10px;text-align:center;font-size:12px;">主营产品：<c:forEach items="${costumeNamesList[status.count-1]}" var="costume">${costume} </c:forEach></p>
									</div>
								</div><!-- panel-body -->
							</div><!-- panel -->
						</div><!-- col-md -->
					</c:forEach>
				</div><!-- row -->
			</div>
		</div>
		
		<!-- 免责声明 -->
		<div class="panel panel-default">
			<div class="panel-body">
				<p>免责声明：</p>
				<p>
					以上展示的信息由企业自行提供，内容的真实性、准确性和合法性由发布企业负责，服饰加工网对此不承担任何责任。如果发现虚假信息，欢迎到商圈举报，或直接致电客服热线：400-168-1978向我们反馈。
				</p>
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
<script src="plugin/jquery-arc-pic/js/base.js"></script>
<script src="plugin/jquery-arc-pic/js/jquery.jqzoom.js"></script>
<script src="JS/main/enterpriseDetail.js"></script>
</body>
</html>

