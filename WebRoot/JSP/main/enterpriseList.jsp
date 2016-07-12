<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-加工工厂</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/enterprise-list.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>

	<!-- <nav class="navbar navbar-default">
	<div class="navbar-header">
        <span class="navbar-brand glyphicon glyphicon-home"></span>
    </div>
	</nav> -->
	
<table style="width:1190px;margin:0 auto;">
<tr>
	<td style="width:906px;">
		<div class="panel panel-default search-panel">
			<div class="panel-body">
				<table>
					<tr>
						<td ><b>产品类别：</b></td>
						<td style="width:806px;">
							<p>全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装全部 梭织服装</p>
						</td>
					</tr>
					<tr>
						<td><b>所在地区：</b></td>
						<td>
							<div class="row">
								<div class="col-sm-3">
									<select class="form-control" id="province" name="province"></select>
								</div>
								<div class="col-sm-3">
									<select class="form-control" id="province" name="province"></select>
								</div>
								<div class="col-sm-3">
									<select class="form-control" id="province" name="province"></select>
								</div>
								<div class="col-sm-3">
									<select class="form-control" id="province" name="province"></select>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td><b>加工类型：</b></td>
						<td><span class="label label-info">Info</span> 梭织服装</td>
					</tr>
				</table>
			</div>
		</div>
	</td>
	<td style="width:274px;background:red">
	sdsf
	</td>
</tr>
</table>
	<!-- <div class="row">
		主体
		<div class="col-md-9">
		</div>主体
		
		右边栏
		<div class="col-md-3">
		</div>右边栏
		
	</div>row -->
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/enterpriseList.js"></script>
</body>
</html>

