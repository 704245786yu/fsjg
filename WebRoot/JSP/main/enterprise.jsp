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
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/enterprise-main.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="enterprise">
<%@ include file="top.jsp" %>

<div class="container-fluid">
	<div class="row">
		<!-- 主体 -->
		<div class="col-md-9">
			<div class="row">
				<!-- 工厂搜索 -->
				<div class="col-md-4">
					<div class="panel panel-default">
						<div class="panel-heading">工厂搜索</div>
						<div class="panel-body">
							<form class="form-horizontal">
								<div class="form-group">
									<label class="col-md-4 control-label">产品类别</label>
									<div class="col-md-8">
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#costumeCategoryModal" style="width:100%;background: url('image/select-btn.png') no-repeat 90%;">选择产品类别</button>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">发单地区</label>
									<div class="col-md-8">
										<select class="form-control">
											<option>请选择发单地区</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">关 键 字</label>
									<div class="col-md-8">
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
				<!-- 实力工厂 -->
				<div class="col-md-8">
					<div class="panel panel-default panel-ad1">
						<div class="panel-heading">实力工厂</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4"><img src="image/ad/enterprise-logo.png"></div>
								<div class="col-md-4"><img src="image/ad/enterprise-logo.png"></div>
								<div class="col-md-4"><img src="image/ad/enterprise-logo.png"></div>
							</div>
							<div class="row" style="margin-top:45px;">
								<div class="col-md-4"><img src="image/ad/enterprise-logo.png"></div>
								<div class="col-md-4"><img src="image/ad/enterprise-logo.png"></div>
								<div class="col-md-4"><img src="image/ad/enterprise-logo.png"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 广告位 -->
			<div class="row">
				<div class="col-md-12">
					<img style="width:100%;"src="image/ad/ad.png">
				</div>
			</div>
			<!-- 接单工厂 -->
			<div class="row" style="margin-top:20px;">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-globe"></span> 接单工厂</h3>
						</div>
						<div class="panel-body">
							<div id="enterpriseList" class="row">
								<div class="col-md-6 enterprise" style="display:none;">
									<div class="panel panel-default">
										<div class="panel-body">
									    	<div class="media">
												<div class="media-left">
													<img class="media-object" src="image/enterprise-icon.png">
												</div>
												<div class="media-body">
													<h4 class="media-heading">工厂名称</h4>
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
				</div>
			</div>
			<!-- 招商热线 -->
			<div class="row" >
				<div class="col-md-8">
					<img style="width:100%;height:110px;"src="image/ad/enterprise-logo.png">
				</div>
				<div class="col-md-4">
					<img style="width:100%;height:110px;"src="image/ad/enterprise-logo.png">
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
		<div class="col-md-3">
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
									<p class="list-group-item-text">主营产品：休闲服饰、女式连衣服</p>
									<h4><span class="label" style="background-color:#ff6717">已通过资质认证</span></h4>
								</li>
							</ul>
						</div><!-- panel-body -->
					</div><!-- panel -->
				</div><!-- col-md -->
			</div><!-- row -->
		</div><!-- 右边栏 -->
	</div><!-- row -->
</div><!-- container-fluid -->

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
				
				<table id="template" style="display:none;">
					<tr style="display:none;">
						<!-- 二级类目 -->
						<td style="width:130px;display:none;">
							<label style="display:none;cursor:pointer;margin-right:10px;">
								<input type="checkbox"> <span style="font-weight:normal;"></span>
							</label>
						</td>
						<!-- 三级类目 -->
						<td>
							<label style="display:none;cursor:pointer;margin-right:10px;">
								<input type="checkbox"> <span style="font-weight:normal;"></span>
							</label>
						</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/enterprise.js"></script>
</body>
</html>

