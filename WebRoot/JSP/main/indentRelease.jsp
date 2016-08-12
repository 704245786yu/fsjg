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
<title>中国服务加工网-加工订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/common/default.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<div style="width:1190px; margin:0 auto;">
	<div class="panel panel-default panel-tip1" style="height:260px">
		<div class="panel-body">
			<h4>1、完善企业信息，展示实力</h4>
			<p>尽可能多地上传企业张数和产品图片及时更新生产加工当期，表明接单状态进行实名认证，获得靠前的排名</p>
			<h4>2、找订单、申请报价</h4>
			<p>找和本厂生产能力匹配的订单申请提交申请前，确认符合发单方的要求并填写申请理由，表达接单诚意</p>
			<h4>3、耐心与客户沟通</h4>
			<p>客户主动找到你，并发送订单报价申请报价被客户接受后请及时回复</p>
		</div>
	</div>
</div>



<c:if test="${loginBasicUser==null}">
	<!-- 添加/更新模态框 -->
	<div class="modal " id="loginModal" data-backdrop="static" data-keyboard="false" data-show="false">
		<div class="modal-dialog" style="width:500px">
			<div class="modal-content">
				<div class="modal-body">
					
				</div><!-- modal-body -->
			</div><!-- modal-content -->
		</div><!-- modal-dialog -->
	</div><!-- modal -->
</c:if>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/indent.js"></script>
</body>
</html>

