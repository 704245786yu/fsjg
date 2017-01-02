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
<link href="plugin/jquery-arc-pic/css/base.css" rel="stylesheet">
<link href="CSS/sample-detail.css" rel="stylesheet">

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
						<a target="_blank" href="enterprise/showDetail/${enterprise.id}">
							<img style="width:90px;height:90px;" class="media-object" src="uploadFile/enterprise/${enterprise.logo}">
						</a>
					</div>
					<div class="media-body">
						<a target="_blank" href="enterprise/showDetail/${enterprise.id}">
							<h5 class="media-heading">${enterprise.enterpriseName}</h5>
						</a>
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
						<input type="hidden" name="imgs" value="${costumeSample.smImg}">
						<div id="preview" class="spec-preview">
							<span class="jqzoom">
								<img style="width:410px;height:410px;" jqimg="" src="" />
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
				<div class="col-md-6" style="padding-left:20px;">
					<table style="width:100%;margin-top:20px;text-align:center;">
						<tr>
							<td>
								<img src="image/costumeSample/gai.png">
								<div>支持改款</div>
							</td>
							<td style="border-left:1px solid #dfddd9;border-right:1px solid #dfddd9;">
								<img src="image/costumeSample/tie.png">
								<div>贴牌生产</div>
							</td>
							<td>
								<img src="image/costumeSample/kan.png">
								<div>支持看样</div>
							</td>
						</tr>
					</table>
					<div style="border-top:1px solid #dfddd9;background-color:#fff6ec;margin-top:20px;padding:20px;">
						<div style="margin-bottom:10px;">
							下单价格:<span style="margin-left:20px;">>=${costumeSample.orderAmount}件，${costumeSample.price}元/件</span>
						</div>
						<div>销售市场:<span id="saleMarket" style="margin-left:20px;">${costumeSample.saleMarket}</span></div>
					</div>
					<p style="margin-top:10px;">
						<h4>加工说明:</h4>
						<p style="padding-left:20px;">${costumeSample.processDesc}</p>
					</p>
				</div>
			</div>
		</div>
		
		<!-- 样品详情 -->
		<div class="panel panel-default">
			<div class="panel-body" style="padding:0px;">
				<div style="padding:10px;font-size:16px;border-top:2px solid #ad0000;border-right:1px solid #dfddd9;width:90px;text-align:center;">样品详情</div>
				<div id="detailImgDiv" style="border-top:2px solid #dfddd9;text-align:center;padding:40px 0px;">
					<input type="hidden" name="detailImg" value="${costumeSample.detailImg}">
				</div>
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
<script src="JS/main/sampleDetail.js"></script>
</body>
</html>

