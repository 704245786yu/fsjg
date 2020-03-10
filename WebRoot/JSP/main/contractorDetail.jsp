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
<title>服饰加工网-快产专家详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/indent-detail.css" rel="stylesheet">
<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="contractor">
<%@ include file="top.jsp" %>
<table style="width:1190px;margin:0 auto;">
<tr>
	<td colspan="2">
		<div class="crumb">
			<span class="glyphicon glyphicon-home"></span>
			当前位置：<a href="contractor">快产专家</a> > 快产专家详情页
		</div>
	</td>
</tr>
<tr>
	<td style="width:906px;">
		<table id="indentInfo" class="table table-bordered" >
			<tr>
				<td colspan="5">
					<label style="font-size:20px;color:#4bb1ec">专业技能：${contractorDto.contractor.skill}</label>
				</td>
				<td rowspan="5" style="width:270px;padding-top:30px;text-align:center;">
					<img src="uploadFile/person/${contractorDto.person.headPic}" class="img-circle" style="width:140px;height:140px;">
					<c:if test="${contractorDto.person.auditState==2}">
						<div style="margin-top:5px;"><span class="label" style="background-color:#ff5719;font-size:14px;padding:5px 25px;">已实名</span></div>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style="width:60px;">姓名：</td><td>${contractorDto.person.realName}</td>
				<td style="width:90px;">主营产品：</td><td>${contractorDto.contractor.costumeCode}</td>
			</tr>
			<tr>
				<td>性别：</td><td>${contractorDto.person.gender}</td>
				<td>加工类型：</td><td id="processType"></td>
			</tr>
			<tr>
				<td>年龄：</td><td>${contractorDto.person.age}</td>
				<td>工人数量：</td><td>${contractorDto.contractor.workerAmount}</td>
			</tr>
			<tr>
				<td>地址：</td>
				<td>${district}
					<c:choose>
						<c:when test="${loginBasicUser == null}">
							<button class="btn btn-warning" data-toggle="modal" data-target="#loginModal" style="padding-top:0px;padding-bottom:0px;background-color:#ff5719;">申请查看</button>
						</c:when>
						<c:when test="${loginBasicUser != null}">
							${contractorDto.person.detailAddr}
						</c:when>
					</c:choose>
				</td>
				<td>加工年限：</td><td>${contractorDto.contractor.processYear}年</td>
			</tr>
			<tr>
				<td>报价：</td><td>${contractorDto.contractor.quote}</td>
				<td>生产设备：</td><td>${contractorDto.contractor.equipment}</td>
			</tr>
		</table>
		
		<div class="panel panel-default">
			<div class="panel-body">
				<h4>加工说明：</h4>
				<p>
					${contractorDto.contractor.processDesc}
				</p>
				<p>
					<c:choose>
						<c:when test="${loginBasicUser == null}">
							<button class="btn btn-warning" data-toggle="modal" data-target="#loginModal" style="width:180px;background-color:#ff5719;">查看联系方式</button>
						</c:when>
						<c:when test="${loginBasicUser != null}">
							联系方式：${contractorDto.person.basicUser.telephone}
						</c:when>
					</c:choose>
				</p>
			</div>
		</div>
	</td>
	
	<!-- 右边栏 -->
	<td style="width:274px;vertical-align:top;padding-left:10px;background-color:#FBF8F9">
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
<script>
var g_processType = {'1':'清加工','2':'经销','3':'来料加工','4':'自营出口','5':'其他'};
$(function(){
	$('#loginModal').modal('hide');
	//加工类型
	var processType = [${contractorDto.contractor.processType}];
	var $processType = $('#processType');
	var str = g_processType[processType[0]];
	for(var i=1;i<processType.length;i++){
		str += ','+g_processType[processType[i]];
	}
	$processType.html(str);
});
</script>
</body>
</html>

