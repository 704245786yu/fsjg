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
<%@ include file="/JSP/main/common/commonData.jsp" %>
<span style="display:none;" id="adPositions">${adPositions}</span>

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
								<td><label>所在地区</label></td>
								<td><button id="districtBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#districtModal">选择所在地区</button></td>
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
							<c:forEach var="enterprise" items="${strengths}" end="3">
								<td><img style="width:90px;height:90px;" src="uploadFile/enterprise/${enterprise.logo}"><div><a target="_blank" href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a></div></td>
							</c:forEach>
						</tr>
						<tr style="height:40px;">
							<!-- 省略号样式 -->
							<c:forEach var="enterprise" items="${strengths}" begin="4" end="7">
								<td><div style="width:119.5px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"><a target="_blank" href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a></div></td>
							</c:forEach>
						</tr>
						<tr>
							<c:forEach var="enterprise" items="${strengths}" begin="8" end="11">
								<td><div style="width:119.5px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"><a target="_blank" href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a></div></td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!-- 广告位 -->
		<a class="ad" target="_blank"><img style="width:100%;height:90px;"></a>
		
		<!-- 接单工厂 -->
		<div class="panel panel-default" style="margin-top:20px;">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-globe"></span> 接单工厂
					<div class="pull-right">
				 		<a href="enterprise/search"><span class="label label-info" style="font-size:14px;">更多</span></a>
					</div>
				 </h3>
					 
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
										<h4 class="media-heading"><a target="_blank">工厂名称</a></h4>
										<p class="list-group-item-text">员工人数</p>
										<p class="list-group-item-text">加工类型</p>
										<p class="list-group-item-text" style="width:290px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;">主营产品</p>
									</div>
								</div>
							</div><!-- panel-body -->
							<div name="schedule" style="background-color:#779BCA;color:white;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;"><img src="image/enterprise/clock.png" style="margin-left:10px;margin-right:10px;"></div>
						</div><!-- panel -->
					</div><!-- col-md -->
				</div><!-- row -->
			</div><!-- panel-body -->
		</div>
		<!-- 招商热线 -->
		<div style="height:110px">
			<div style="width:595px;float:left;">
				<a class="ad" target="_blank"><img style="width:100%;height:110px"></a>
			</div>
			<div style="width:295px;float:right;">
				<a class="ad" target="_blank"><img style="width:100%;height:110px"></a>
			</div>
		</div>
		<!-- 热门区域加工厂 -->
		<div class="hotAreaDiv" class="row" style="margin-top:20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-map-marker" style="color:#0096E9;"></span> 热门区域加工厂</h3>
				</div>
				<div class="panel-body">
					<table>
						<tr>
							<td><a href="province" title="浙江"><img src="image/enterprise/zhejiang.png"></a></td>
							<td>
								<a href="#">宁波</a><span>|</span> <a href="#">杭州</a><span>|</span> <a href="#">金华</a><span>|</span> <a href="#">绍兴</a><br/>
								<a href="#">嘉兴</a><span>|</span> <a href="#">温州</a><span>|</span> <a href="#">湖州</a><span>|</span> <a href="#">台州</a>
							</td>
							<td style="padding-left:100px;"><a href="province" title="江苏"><img src="image/enterprise/jiangsu.png"></a></td>
							<td>
								<a href="#">苏州</a><span>|</span> <a href="#">南京</a><span>|</span> <a href="#">无锡</a><span>|</span> <a href="#">泰州</a><br/>
								<a href="#">南通</a><span>|</span> <a href="#">盐城</a><span>|</span> <a href="#">镇江</a><span>|</span> <a href="#">常州</a>
							</td>
						</tr>
						<tr>
							<td><a href="province" title="广东"><img src="image/enterprise/guangdong.png"></a></td>
							<td>
								<a href="#">广州</a><span>|</span> <a href="#">深圳</a><span>|</span> <a href="#">东莞</a><span>|</span> <a href="#">佛山</a><br/>
								<a href="#">中山</a><span>|</span> <a href="#">汕头</a><span>|</span> <a href="#">惠州</a><span>|</span> <a href="#">江门</a>
							</td>
							<td style="padding-left:100px;"><a href="province" title="福建"><img src="image/enterprise/fujian.png"></a></td>
							<td>
								<a href="#">福州</a><span>|</span> <a href="#">厦门</a><span>|</span> <a href="#">莆田</a><span>|</span> <a href="#">三明</a><br/>
								<a href="#">泉州</a><span>|</span> <a href="#">漳州</a><span>|</span> <a href="#">南平</a><span>|</span> <a href="#">宁德</a>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td><a href="#">北京</a></td> <td><a href="#">上海</a></td> <td><a href="#">天津</a></td> <td><a href="#">山东</a></td> <td><a href="#">宁夏</a></td>
							<td><a href="#">辽宁</a></td> <td><a href="#">重庆</a></td> <td><a href="#">云南</a></td> <td><a href="#">新疆</a></td> <td><a href="#">西藏</a></td>
						</tr>
						<tr>
							<td><a href="#">四川</a></td> <td><a href="#">陕西</a></td> <td><a href="#">山西</a></td> <td><a href="#">青海</a></td> <td><a href="#">内蒙古</a></td>
							<td><a href="#">江西</a></td> <td><a href="#">吉林</a></td> <td><a href="#">湖南</a></td> <td><a href="#">湖北</a></td> <td><a href="#">黑龙江</a></td>
						</tr>
						<tr>
							<td><a href="#">河南</a></td> <td><a href="#">河北</a></td> <td><a href="#">海南</a></td> <td><a href="#">贵州</a></td> <td><a href="#">广西</a></td>
							<td><a href="#">甘肃</a></td> <td><a href="#">安徽</a></td> <td><a href="#">香港</a></td> <td><a href="#">澳门</a></td> <td><a href="#">台湾</a></td>
						</tr>
					</table>
				</div><!-- panel-body -->
			</div><!-- panel -->
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
					<div class="panel-body" style="padding-bottom:10px;">
						<p>1.从产品类别，所在地区进行初步筛选服装工厂</p>
						<p>2.利用搜索关键字缩小找工厂范围</p>
						<p>3.优先查看资质认证，实名认证的企业</p>
						<p>4.价格比较敏感的订单可以考虑有当期愿意以优惠价格成交工厂</p>
						<p>5.通过QQ在线进行交流，获取价格</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 最新入驻的工厂 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 最新入驻的工厂</h3>
					</div>
					<div class="panel-body" style="padding:0px;">
						<ul id="newEnterpriseList" class="list-group" style="margin-bottom:0px;">
							<li class="list-group-item enterprise" style="display:none;border-bottom:0px;border-left:0px;border-right:0px;">
								<div class="media">
									<div class="media-left">
										<img class="media-object" src="image/new.png">
									</div>
									<div class="media-body">
										<h4 class="media-heading"><a target="_blank">工厂名称</a></h4>
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
		<!-- <div class="row">
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
					</div>
				</div>
			</div>
		</div> -->
		<!-- 最新认证加工厂 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 最新认证加工厂</h3>
					</div>
					<div class="panel-body" style="padding:0px;">
						<ul id="newAuthEnterpriseList" class="list-group" style="margin-bottom:0px;">
							<li class="list-group-item enterprise" style="display:none;border-bottom:0px;border-left:0px;border-right:0px;">
								<h4 class="media-heading"><a target="_blank">工厂名称</a></h4>
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
<%@ include file="/JSP/main/bottom.jsp"%>

<span style="display:none" id="hiddenCostumeCategory">${costumeCategoryMap}</span>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/enterprise.js"></script>
</body>
</html>