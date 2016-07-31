<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link href="CSS/orderForm-main.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="orderForm">

<div style="width:1190px; margin:0 auto;">
	<!-- 左边栏(主体) -->
	<div style="width:900px;float:left;">
		<!-- 订单搜索 -->
		<div style="width:370px;float:left;">
			<div class="panel panel-default" style="height:260px">
				<div class="panel-heading">订单搜索</div>
				<div class="panel-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-md-3 control-label">产品类别</label>
							<div class="col-md-9">
								<button id="costumeBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#costumeCategoryModal" style="width:100%;overflow:hidden;background: url('image/select-btn.png') no-repeat 90%;">选择产品类别</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">发单地区</label>
							<div class="col-md-9">
								<select class="form-control">
									<option>请选择发单地区</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">关 键 字</label>
							<div class="col-md-9">
								<input type="text" class="form-control" placeholder="请输入关键字">
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<button type="submit" class="form-control btn btn-primary">搜索</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<div style="width:520px;float:right;">
			<div class="panel panel-default panel-tip1" style="height:260px">
				<div class="panel-heading">
					 <h3 class="panel-title cus-panel-title">如何得到高质量的订单?</h3>
				</div>
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
		
		<!-- 广告位 -->
		<img style="width:100%;"src="image/ad/ad.png">
		
		<!-- 个人发布的订单 -->
		<div class="panel panel-default" style="margin-top:20px;">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-user"></span> 个人发布的订单</h3>
			</div>
			<div class="panel-body">
					
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
		<img style="margin-bottom:10px;" src="image/ad/orderForm/publish_order.png"/>
		<!-- 最新接到报价的订单 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 最新接到报价的订单</h3>
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
								<img class="media-object" src="image/enterprise-icon.png">
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
	</div><!-- 右边栏 -->
</div>

<!-- 选择服饰类别模态框 -->
<div class="modal fade" id="costumeCategoryModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h5 class="modal-title" id="myModalLabel">选择服饰类别</h5>
			</div>
			<div class="modal-body">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#costume_1" data-toggle="tab">服装</a></li>
					<li><a href="#costume_2" data-toggle="tab">服饰</a></li>
					<li><a href="#costume_3" data-toggle="tab">家纺</a></li>
					<li><a href="#costume_4" data-toggle="tab">纺织消费品</a></li>
					<li><a href="#costume_5" data-toggle="tab">面料/皮革/纱线</a></li>
					<li><a href="#costume_6" data-toggle="tab">纺织辅料</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="costume_1">
						
					</div>
					<div class="tab-pane" id="costume_2"></div>
					<div class="tab-pane" id="costume_3"></div>
					<div class="tab-pane" id="costume_4"></div>
					<div class="tab-pane" id="costume_5"></div>
					<div class="tab-pane" id="costume_6"></div>
				</div>
				
				<table id="template" class="table" style="display:none;">
					<tr style="display:none;">
						<!-- 二级类目 -->
						<td style="width:100px;vertical-align:top;display:none;">
							<label style="cursor:pointer;">
								<input type="checkbox" onchange="checkAllSubBox(this)" readonly="readonly"> <span style="font-weight:normal;"></span>
							</label>
						</td>
						<!-- 三级类目 -->
						<td>
							<label style="display:none;width:140px;cursor:pointer;margin-right:10px;float:left;">
								<input type="checkbox" onchange="threeLevelCheck(this)"> <span style="font-weight:normal;"></span>
							</label>
						</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="checkCostume()">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/orderForm.js"></script>
</body>
</html>
