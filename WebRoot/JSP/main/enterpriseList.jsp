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
						<td><b>产品类别：</b></td>
						<td style="width:806px;">
							<p id="costumeCategory" style="float:left;width:90%;height:50px;line-height:25px;overflow:hidden;overflow-x:hidden">
								<a class="label label-info" href="0">全部</a>
							</p>
							<a id="showMoreToggle" type="button" class="btn btn-default btn-xs" style="float:right;color:#337AB7;">更多<span class="glyphicon glyphicon-chevron-down"></span></a>
						</td>
					</tr>
					<tr>
						<td><b>所在地区：</b></td>
						<td>
							<div id="districtContainer" class="row">
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
						</td>
					</tr>
					<tr>
						<td><b>加工类型：</b></td>
						<td>
							<a class="label label-info" href="0">全部</a>
							<a href="1">清加工</a>
							<a href="2">经销</a>
							<a href="3">来料加工</a>
							<a href="4">自营出口</a>
							<a href="5">其他</a>
						</td>
					</tr>
					<tr>
						<td><b>员工数量：</b></td>
						<td>
							<a class="label label-info" href="0">全部</a>
							<a href="1">50人以下</a>
							<a href="2">50-100人</a>
							<a href="3">100-200人</a>
							<a href="4">200-500人</a>
							<a href="5">500-1000人</a>
							<a href="6">1000人以上</a>
						</td>
					</tr>
				</table>
			</div>
		</div><!-- panel -->
		
		<!-- 广告 -->
		<img src="image/ad/ad.png">
		
		<!-- 工厂列表 -->
		<table class="table table-bordered" style="margin-top:20px;">
			<tr>
				<td rowspan="3">
					QQ在线交流
				</td>
				<td>工厂信息：</td>
				<td>台州市威凯顿服饰加工厂</td>
			</tr>
			<tr>
				<td>工厂介绍：</td>
				<td>台州市凯顿服饰有限公司是媳妇、衬衫、职业装、</td>
			</tr>
			<tr>
				<td>所在地区：</td>
				<td>浙江省 台州市 温岭区 乐桥镇</td>
			</tr>
		</table>
	</td>
	
	<!-- 右边栏 -->
	<td style="width:274px;vertical-align:top;padding-left:10px;">
		<ul class="list-group">
			<li class="list-group-item"><img width="100%" src="image/ad/guanggao.png"/></li>
			<li class="list-group-item"><img width="100%" src="image/ad/guanggao.png"/></li>
		</ul>
	</td>
</tr>
</table>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/districtCascade.js"></script>
<script src="JS/main/enterpriseList.js"></script>
</body>
</html>

