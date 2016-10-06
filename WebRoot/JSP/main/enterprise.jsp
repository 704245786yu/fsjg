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
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/enterprise-main.css" rel="stylesheet">
<style type="text/css">
.css1{
	color:#6699ff;border:1px #ff8000 dashed;
	margin-bottom:20px;
	width: 20em;
}
.css2 {
	overflow: hidden;
	text-overflow: ellipsis;/*文字隐藏后添加省略号*/
	white-space: nowrap;/*强制不换行*/
	width: 20em;/*不允许出现半汉字截断*/
	color:#6699ff;border:1px #ff8000 dashed;
}
</style>
<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="enterprise">
<%@ include file="top.jsp" %>

<div style="width:1190px; margin:0 auto;">
	<!-- 左边栏(主体) -->
	<div style="width:900px;float:left;">
		<!-- 工厂搜索 -->
		<div style="width:370px;float:left;">
			<div class="panel panel-default" style="height:260px">
				<div class="panel-heading">工厂搜索</div>
				<div class="panel-body">
					<form class="form-horizontal" action="enterprise/search" method="post">
						<table>
							<tr>
								<td style="width:65px"><label>产品类别</label></td>
								<td>
									<jsp:include page="/JSP/main/common/costumeCategoryModal.jsp">
										<jsp:param name="isLimitCheck" value="true"/>
									</jsp:include>
								</td>
							</tr>
							<tr>
								<td><label>发单地区</label></td>
								<td><button id="districtBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#districtModal">选择发单地区</button></td>
							</tr>
							<tr>
								<td><label>关 键 字</label></td>
								<td><input type="text" name="enterpriseKeyword" class="form-control" placeholder="请输入关键字"></td>
							</tr>
							<tr>
								<td colspan="2"><button type="submit" class="form-control btn btn-primary">搜索</button></td>
							</tr>
						</table>
						
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
					</form>
				</div>
			</div>
		</div>
		
		<!-- 实力工厂 -->
		<div style="width:520px;float:right;">
			<div class="panel panel-default strength-enterprise" style="height:260px">
				<div class="panel-heading">实力工厂</div>
				<div class="panel-body">
					<table>
						<tr>
							<c:forEach var="enterprise" items="${enterprises}" end="3">
								<td><img style="width:90px;height:90px;" src="uploadFile/enterprise/${enterprise.logo}"><div><a href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a></div></td>
							</c:forEach>
						</tr>
						<tr style="height:40px;">
							<!-- 省略号样式 -->
							<c:forEach var="enterprise" items="${enterprises}" begin="4" end="7">
								<td><div style="width:119.5px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"><a href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a></div></td>
							</c:forEach>
						</tr>
						<tr>
							<c:forEach var="enterprise" items="${enterprises}" begin="8" end="11">
								<td><div style="width:119.5px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"><a href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a></div></td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!-- 广告位 -->
		<img style="width:100%;"src="image/ad/ad.png">
		
		<!-- 接单工厂 -->
		<div class="panel panel-default" style="margin-top:20px;">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-globe"></span> 接单工厂</h3>
			</div>
			<div class="panel-body">
				<div id="enterpriseList" class="row">
					<div class="enterprise" style="width:449px;padding:0 15px;float:left;display:none;">
						<div class="panel panel-default">
							<div class="panel-body">
						    	<div class="media">
									<div class="media-left">
										<img class="media-object" style="width:90px;height:90px;" src="uploadFile/enterprise/default_logo.png">
									</div>
									<div class="media-body">
										<h4 class="media-heading"><a>工厂名称</a></h4>
										<p class="list-group-item-text">员工人数</p>
										<p class="list-group-item-text">加工类型</p>
										<p class="list-group-item-text">主营产品</p>
									</div>
								</div>
							</div><!-- panel-body -->
						</div><!-- panel -->
					</div><!-- col-md -->
				</div><!-- row -->
			</div><!-- panel-body -->
		</div>
		<!-- 招商热线 -->
		<div style="height:110px">
			<div style="width:595px;float:left;">
				<img style="width:100%;height:110px"src="image/ad/enterprise-logo.png">
			</div>
			<div style="width:295px;float:right;">
				<img style="width:100%;height:110px"src="image/ad/enterprise-logo.png">
			</div>
		</div>
		<!-- 热门区域加工厂 -->
		<div class="row" style="margin-top:20px;">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-map-marker"></span> 热门区域加工厂</h3>
					</div>
					<div class="panel-body">
					</div><!-- panel-body -->
				</div><!-- panel -->
			</div>
		</div>
	</div><!-- 主体 -->
	
	<!-- 右边栏 -->
	<div style="width:280px;float:right;">
		<!-- 如何找到合适的工厂 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title">如何找到合适的工厂?</h3>
					</div>
					<div class="panel-body">
						<p>1.从产品类别，发单地区进行初步筛选服装工厂</p>
						<p>2.利用高级搜索中的员工总数条件缩小范围</p>
						<p>3.优先查看资质认证，实名认证的企业</p>
						<p>4.价格比较敏感的订单可以考虑有当期愿意以优惠价格成交工厂</p>
						<p>5.通过QQ在线进行交流，获取价格</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 最新入住的工厂 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 最新入住的工厂</h3>
					</div>
					<div class="panel-body" style="padding:0px;">
						<ul id="newEnterpriseList" class="list-group">
							<li class="list-group-item enterprise" style="display:none;">
								<div class="media">
									<div class="media-left">
										<img class="media-object" src="image/new.png">
									</div>
									<div class="media-body">
										<h4 class="media-heading">工厂名称</h4>
										<p class="list-group-item-text">主营产品</p>
									</div>
								</div>
							</li>
						</ul>
					</div><!-- panel-body -->
				</div><!-- panel -->
			</div><!-- col-md -->
		</div><!-- row -->
		<!-- 成功案列 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 成功案列</h3>
					</div>
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<img class="media-object" src="uploadFile/enterprise/default_logo.png">
							</div>
							<div class="media-body">
								<h4 class="media-heading">张先生 成功发单</h4>
								<p>上海市海天贸易有限公司</p>
								<p>我们公司是一家服装贸易公司，有几家固定合作的服装加工厂，但是现在单子不稳定，工厂也不稳定，所以一有急单或者是工厂忙的时候，单子就很难发出去。这次就是</p>
							</div>
						</div>
					</div><!-- panel-body -->
				</div><!-- panel -->
			</div><!-- col-md -->
		</div><!-- row -->
		<!-- 最新认证加工厂 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 最新认证加工厂</h3>
					</div>
					<div class="panel-body" style="padding:0px;">
						<ul id="newAuthEnterpriseList" class="list-group">
							<li class="list-group-item enterprise" style="display:none;">
								<h4 class="media-heading">工厂名称</h4>
								<p class="list-group-item-text"></p>
								<h4><span class="label" style="background-color:#ff6717">已通过资质认证</span></h4>
							</li>
						</ul>
					</div><!-- panel-body -->
				</div><!-- panel -->
			</div><!-- col-md -->
		</div><!-- row -->
	</div><!-- 右边栏 -->
</div>

<%-- <input type="hidden" id="hiddenCostumeCategory" value={<c:forEach var="costumeCategory" items="${costumeCategoryMap}">"${costumeCategory.key}":"${costumeCategory.value}",</c:forEach>}> --%>
<!-- <input type="hidden" id="hiddenCostumeCategory" value=${costumeCategoryMap}> -->
<span style="display:none" id="hiddenCostumeCategory">${costumeCategoryMap}</span>
<input type="hidden" id="hiddenProcessType" value={<c:forEach var="processType" items="${processTypes}">"${processType.constantValue}":"${processType.constantName}",</c:forEach>}>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/enterprise.js"></script>
</body>
</html>