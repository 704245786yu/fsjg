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
<title>服饰加工网-订单详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/indent-detail.css" rel="stylesheet">
<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="indent">
<%@ include file="top.jsp" %>
<table style="width:1190px;margin:0 auto;">
<tr>
	<td colspan="2">
		<div class="crumb">
			<span class="glyphicon glyphicon-home"></span>
			当前位置：<a href="indent">加工订单</a> > <a href="indent/search">订单列表页</a> > 订单详情页
		</div>
	</td>
</tr>
<tr>
	<td style="width:906px;">
		<table id="indentInfo" class="table table-bordered" >
			<tr>
				<td colspan="5">
					<c:if test="${indent.isUrgency == true}">
						<img src="image/indentRelease/urgency.png">
					</c:if>
					<label style="font-size:20px;color:#4bb1ec">${indent.indentName}</label>
				</td>
				<td rowspan="5" style="width:270px;padding-top:30px;">
					<label style="font-size:20px;">详细资料：</label>
					<c:choose>
						<c:when test="${loginBasicUser == null}">
							<p style="text-align:center;">
								<span class="glyphicon glyphicon-picture" style="font-size:50px;color:#50B2E9;margin-right:30px;"></span>
								<span class="glyphicon glyphicon-file" style="font-size:45px;color:#50B2E9;"></span>
							</p>
							<div style="text-align:center;margin-bottom:10px;">登录后可查看详细信息</div>
							<div style="text-align:center;"><button class="btn btn-warning" data-toggle="modal" data-target="#loginModal" style="width:170px;background-color:#ff5719;">申请下载</button></div>
						</c:when>
						<c:when test="${loginBasicUser != null}">
							<p style="text-align:center;">
								<span class="glyphicon glyphicon-file" style="font-size:45px;color:#50B2E9;"></span>
							</p>
							<c:if test="${indent.document == ''}">
								<div style="text-align:center;margin-bottom:10px;">无订单附件。</div>
								<div style="text-align:center;"><button class="btn" style="width:170px;">立即下载</button></div>
							</c:if>
							<c:if test="${indent.document != ''}">
								<div style="text-align:center;margin-bottom:10px;">订单有附件可以下载，更详细！</div>
								<div style="text-align:center;"><a class="btn btn-warning" href="indent/downloadRes?document=${indent.document}" style="width:170px;background-color:#ff5719;">立即下载</a></div>
							</c:if>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td style="width:90px;">发布日期：</td><td id="createTime">${indent.createTime}</td>
				<td style="width:100px;">预交货日期：</td><td id="preDeliveryDate">${indent.preDeliveryDate}</td>
				<td rowspan="4" style="width:1px;">
					<div style="width:1px;height:160px;background-color:black;"></div>
				</td>
			</tr>
			<tr>
				<td>产品类别：</td>
				<td>
					<c:forEach var="costume" items="${costumes}">${costume} </c:forEach>
				</td>
				<td>订单类型：</td><td id="indentType">${indent.indentType}</td>
			</tr>
			<tr>
				<td>预计数量：</td>
				<td>${indent.quantity}件</td>
				<td>目标价：</td>
				<td id="expectPrice">
					<c:choose>
						<c:when test="${indent.expectPrice==-1}">
							面谈
						</c:when>
						<c:when test="${indent.expectPrice!=-1}">
							<fmt:formatNumber type="currency" value="${indent.expectPrice}"></fmt:formatNumber>
						</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>有效日期：</td><td id="effectiveDate">${indent.effectiveDate}</td>
				<td>加工类型：</td><td><input type="hidden" name="processType" value="${indent.processType}"></td>
			</tr>
			<c:if test="${loginBasicUser != null}">
				<tr>
					<td colspan="5">
						<img class="photo" src="uploadFile/indent/" width="115px" height="120px" style="display:none;margin-left:10px;border:1px solid #DDDDDD"/>
						<input type="hidden" name="photo" value="${indent.photo}"/>
					</td>
				</tr>
			</c:if>
		</table>

		<table class="table table-bordered">
			<tr>
				<td style="width:550px;border:0px;">
					<p><label style="font-size:16px">接单要求：</label>请确认您符合接单要求再报价，以提升接单成功几率</p>
					<p style="padding:20px 0 20px 20px;">
						<span>
							<c:forEach var="district" items="${districts}">${district}</c:forEach>
						</span>${indent.condDemand}
					</p>
					<p>
						<c:choose>
							<c:when test="${loginBasicUser == null}">
								<button class="btn btn-warning" data-toggle="modal" data-target="#loginModal" style="width:170px;background-color:#ff5719;margin-left:20px;">申请接单</button>
							</c:when>
							<c:when test="${loginBasicUser != null}">
								<div id="quoteP">
									<div style="width:160px;float:left;padding-left:20px;">
										<input id="quote" type="text" class="form-control" placeholder="输入您的报价金额"/>
										<input id="quoteUrl" type="hidden" value="indentQuote/quote/${indent.indentNum}/"/>
									</div>
									<div style="float:left;">
										元，<button class="btn btn-warning" style="background-color:#ff5719;" onclick="quote()">报价</button> &nbsp;<span></span>
									</div>
								</div>
								<div id="quoteSuc" style="font-size:20px;padding-left:20px;display:none;">恭喜您报价成功，耐心等待对方的回复，<br/>前往个人中心查看您申请过的报价订单。</div>
							</c:when>
						</c:choose>
					</p>
					<p></p>
				</td>
				<td style="border:0px;">
					<a class="btn btn-default disabled" style="color:black;width:220px;">找到订单，申请接单</a>
					<div style="width:220px;text-align:center;margin-top:5px;margin-bottom:5px;color:#50B2E9;font-size:16px;">
						<span class="glyphicon glyphicon-chevron-down"></span>
					</div>
					<a class="btn btn-default disabled" style="color:black;width:220px;">填写报价或者QQ在线交流</a>
					<div style="width:220px;text-align:center;margin-top:5px;margin-bottom:5px;color:#50B2E9;font-size:16px;">
						<span class="glyphicon glyphicon-chevron-down"></span>
					</div>
					<a class="btn btn-default disabled" style="color:black;width:220px;">双方洽谈达成意向</a>
				</td>
			</tr>
		</table>
		
		<div class="panel panel-default">
			<div class="panel-body">
				服饰加工网提醒您：订单数量过大，要求预付定金、跟单费、中介费或其他费用等，都可能是欺诈信息，请务必谨慎，以防上当受骗!
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-body">
				<h4>免责声明：</h4>
				<p>
					以上展示的信息由企业自行提供，内容的真实性、准确性和合法性由发布企业负责，服饰加工网对此不承担任何责任。如果发现虚假信息，欢迎到商圈举报，或直接致电客服热线：400-168-1978向我们反馈。
				</p>
			</div>
		</div>
	</td>
	
	<!-- 右边栏 -->
	<td style="width:274px;vertical-align:top;padding-left:10px;background-color:#FBF8F9">
		<div class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 发单方信息</h3>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${loginBasicUser == null}">
						<h5>
							<c:forEach var="districtName" items="${districtNames}" end="1">${districtName}</c:forEach>某
							<c:choose>
								<c:when test="${indent.createUserType == 1}">个体户</c:when>
								<c:when test="${indent.createUserType == 2}">加工厂</c:when>
							</c:choose>
						</h5>
						<p>联 系 人 ：<button class="btn btn-warning" data-toggle="modal" data-target="#loginModal" style="width:160px;background-color:#ff5719;">申请查看</button></p>
						<p>联系电话：<button class="btn btn-warning" data-toggle="modal" data-target="#loginModal" style="width:160px;background-color:#ff5719;">申请查看</button></p>
						<p>所在地区：<c:forEach var="districtName" items="${districtNames}" end="1">${districtName}</c:forEach></p>
					</c:when>
					<c:when test="${loginBasicUser != null}">
						<h4>
							<c:choose>
								<c:when test="${indent.createUserType == 1}">${user.realName}</c:when>
								<c:when test="${indent.createUserType == 2}">${user.enterpriseName}</c:when>
							</c:choose>
						</h4>
						<p>联系人：${indent.linkman}</p>
						<p>联系电话：${indent.telephone}</p>
						<p>所在地区：<c:forEach var="districtName" items="${districtNames}">${districtName}</c:forEach>${user.detailAddr}</p>
					</c:when>
				</c:choose>
			</div><!-- panel-body -->
		</div><!-- panel -->
		
		<ul class="list-group">
			<li class="list-group-item"><img width="100%" src="image/ad/guanggao.png"/></li>
			<li class="list-group-item"><img width="100%" src="image/ad/guanggao.png"/></li>
		</ul>
	</td>
</tr>
</table>

<%@ include file="/JSP/main/bottom.jsp"%>

<jsp:include page="loginModal.jsp">
	<jsp:param name="showCloseBtn" value="true"/>
</jsp:include>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/main/indentDetail.js"></script>
</body>
</html>

