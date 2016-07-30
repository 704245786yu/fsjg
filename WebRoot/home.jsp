<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrap-login-form/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="CSS/home.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="home">
<%@ include file="JSP/main/top.jsp" %>

<div class="main-body" style="width:1190px; margin:0 auto;">
<table id="table1">
	<tr>
		<!-- 左边栏 -->
		<td width="200px;">
			<div class="mallCategory" style="position:relative;">
				<ul class="nav nav-pills nav-stacked category-search">
					<li><a href="enterprise/showList/101">梭织服装</a></li>
					<li><a href="enterprise/showList/102">针织服装</a></li>
					<li><a href="enterprise/showList/103">毛衫服装</a></li>
					<li><a href="enterprise/showList/104">皮革服装</a></li>
					<li><a href="enterprise/showList/105">裘皮服装</a></li>
					<li><a href="enterprise/showList/2">服饰</a></li>
					<li><a href="enterprise/showList/3">家纺</a></li>
					<li><a href="enterprise/showList/4">纺织消费品</a></li>
					<li><a href="enterprise/showList/5">面料/皮革/纱线</a></li>
					<li><a href="enterprise/showList/6">纺织辅料</a></li>
				</ul>
				<!-- 子菜单 -->
				<div class="cat-subcategory">
					<div id="category_101"></div>
					<div id="category_102"></div>
					<div id="category_103"></div>
					<div id="category_104"></div>
					<div id="category_105"></div>
					<div id="category_2"></div>
					<div id="category_3"></div>
					<div id="category_4"></div>
					<div id="category_5"></div>
					<div id="category_6"></div>
				</div>
			</div>
		</td>
		<!-- 中间栏 -->
		<td width="760px" style="padding:0px 5px;">
			<div id="carousel-big-ad" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-big-ad" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-big-ad" data-slide-to="1"></li>
				</ol>
				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="image/ad/big_ad1.jpg">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="item">
						<img src="image/ad/big_ad2.jpg">
						<div class="carousel-caption">
						</div>
					</div>
				</div>
				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-big-ad" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span>
				</a>
				<a class="right carousel-control" href="#carousel-big-ad" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>
		</td>
		<!-- 右边栏 -->
		<td width="230px">
			<div class="panel panel-default" style="margin-bottom:5px;">
				<div class="panel-body">
					<div class="media">
						<div class="media-left">
							<img class="media-object" src="image/touxiang.png">
						</div>
						<div class="media-body">
							<h4 class="media-heading">Hi,你好</h4>
							<p>欢迎来到服饰加工网</p>
						</div>
					</div>
					<div class="btn-group btn-group-justified">
						<a class="btn btn-default btn-lg" href="login.jsp" style="width:49%"><i class="fa fa-key"></i>登录</a>
						<a class="btn btn-default btn-lg" href="JSP/signUp.jsp" style="width:47%"><i class="fa fa-pencil"></i>注册</a>
					</div>
					<h5>公告</h5>
					<p>[公告]B2B的春天正在到来</p>
					<p>[公告]海外尖货频道上线</p>
					<p>[公告]海外尖货频道上线</p>
					<p>[公告]开启拳王诚心通时代</p>
					<img src="image/ad/home_small_ad.png" width="100%">
					<img src="image/ad/home_small_ad.png" width="100%">
				</div>
			</div>
		</td>
	</tr>
</table>

<table id="table2">
	<tr>
		<td style="width:595px;padding-right:3px">
			<!-- 实力工厂 -->
			<div class="panel panel-default">
				<div class="panel-heading"><span>实力工厂</span></div>
				<div class="panel-body">
					<div class="col-md-4 ad">
						<img src="image/ad/front_ad.png">
					</div>
					<div class="col-md-8">
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">sdfdfs</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">adsasa</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
					</div>
				</div>
			</div>
		</td>
		<td style="width:595px;padding-left:3px">
			<!-- 认证工厂 -->
			<div class="panel panel-default">
				<div class="panel-heading"><span>认证工厂</span></div>
				<div class="panel-body">
					<div class="col-md-4 ad">
						<img src="image/ad/front_ad2.png">
					</div>
					<div class="col-md-8">
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">sdfdfs</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">adsasa</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>

<!-- 中间广告 -->
<table>
	<tr>
		<td style="width:595px;padding-right:3px">
			<img width="100%" src="image/ad/home_mid_ad1.png"/>
		</td>
		<td style="width:595px;padding-left:3px">
			<img width="100%" src="image/ad/home_mid_ad2.png"/>
		</td>
	</tr>
</table>

<table>
	<tr>
		<td style="width:595px;padding-right:3px">
			<!-- 实力工厂 -->
			<div class="panel panel-default">
				<div class="panel-heading"><span>实力工厂</span></div>
				<div class="panel-body">
					<div class="col-md-4 ad">
						<img src="image/ad/front_ad.png">
					</div>
					<div class="col-md-8">
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">sdfdfs</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">adsasa</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
					</div>
				</div>
			</div>
		</td>
		<td style="width:595px;padding-left:3px">
			<!-- 认证工厂 -->
			<div class="panel panel-default">
				<div class="panel-heading"><span>认证工厂</span></div>
				<div class="panel-body">
					<div class="col-md-4 ad">
						<img src="image/ad/front_ad2.png">
					</div>
					<div class="col-md-8">
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">sdfdfs</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdas</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">adsasa</div>
						<div class="col-md-4"><img src="image/ad/enterprise-logo.png">asdad</div>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>
</div>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/home.js"></script>
</body>
</html>

