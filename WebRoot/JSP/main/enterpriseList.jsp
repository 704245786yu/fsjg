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
	
<table style="width:1190px;margin:0 auto;bakcground-color:#FBF8F9;">
<tr>
	<td style="width:906px;background-color:#FBF8F9;">
		<div class="panel panel-default search-panel">
			<div class="panel-body">
				<table>
					<tr>
						<td><b>产品类别：</b></td>
						<td style="width:806px;">
							<p id="costumeCategory" style="float:left;width:90%;height:50px;line-height:25px;overflow:hidden;overflow-x:hidden">
								<a class="label label-info" href="0" onclick="return query(this)">全部</a>
							</p>
							<a id="showMoreToggle" type="button" class="btn btn-default btn-xs" style="float:right;color:#337AB7;">更多<span class="glyphicon glyphicon-chevron-down"></span></a>
						</td>
					</tr>
					<tr>
						<td><b>所在地区：</b></td>
						<td>
							<button id="districtBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#districtModal">选择发单地区</button>
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
					<tr>
						<td><b>加工类型：</b></td>
						<td id="processType">
							<a class="label label-info" href="0" onclick="return aClick(this)">全部</a>
							<c:forEach var="processType" items="${processTypes}">
								<a href="${processType.constantValue}" onclick="return aClick(this)">${processType.constantName}</a>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td><b>员工数量：</b></td>
						<td id="staffNumber">
							<a class="label label-info" href="0" onclick="return aClick(this)">全部</a>
							<a href="1" onclick="return aClick(this)">50人以下</a>
							<a href="2" onclick="return aClick(this)">50-100人</a>
							<a href="3" onclick="return aClick(this)">100-200人</a>
							<a href="4" onclick="return aClick(this)">200-500人</a>
							<a href="5" onclick="return aClick(this)">500-1000人</a>
							<a href="6" onclick="return aClick(this)">1000人以上</a>
						</td>
					</tr>
				</table>
			</div>
		</div><!-- panel -->
		
		<!-- 广告 -->
		<img src="image/ad/ad.png">
		<span style="display:none;" id="districts">
			{<c:forEach var="district" items="${districts}">"${district.districtCode}":"${district.districtName}",</c:forEach>}
		</span>
		<span style="display:none;" id="costumeCategoryMap">${costumeCategoryMap}</span>
		
		<div id="enterpriseListDiv">
			<c:forEach var="enterprise" items="${result.rows}">
				<!-- 工厂列表 -->
				<table class="table table-bordered" style="margin-top:20px;">
					<tr height="45px">
						<td rowspan="3" style="width:180px;background-color:#F8F8F8;text-align:center;">
							<img width="120px" height="120px" style="margin-bottom:10px" src="image/enterprise-icon.png">
							<button type="button" class="btn btn-info">QQ在线交流</button>
						</td>
						<td style="width:90px;text-align:center;background-color:#E5E5E5">工厂信息：</td>
						<td style="background-color:white;">
							<div style="font-size:16px;float:left;">
								<a href="enterprise/showDetail/${enterprise.id}" style="color:#59BBE7;">${enterprise.enterpriseName}</a>
							</div>
							<div style="float:right;">员工人数：${enterprise.staffNumber}人</div>
							<div style="float:right;margin-right:40px;">
								加工类型：
								<span name="processType">${enterprise.processType}</span>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align:center;background-color:#E5E5E5">工厂介绍：</td>
						<td style="background-color:white;">
							<span>${enterprise.description}</span>
							<a href="#" style="color:#59BBE7">更多详情</a>
						</td>
					</tr>
					<tr height="40px">
						<td style="text-align:center;background-color:white;">所在地区：</td>
						<td style="background-color:white;">
							<span name="disctrict">[${enterprise.province},${enterprise.city}]</span>
							<span style="margin-left:180px;color:red;">
								主营产品:
								<span name="costumeName">${enterprise.costumeCode}</span>
							</span>
						</td>
					</tr>
					<tr>
						<td colspan="3" style="background-color:#D9D9D9;padding:1px;">
						</td>
					</tr>
				</table>
			</c:forEach>
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
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/districtCascade.js"></script>
<script src="JS/main/enterpriseList.js"></script>
</body>
</html>

