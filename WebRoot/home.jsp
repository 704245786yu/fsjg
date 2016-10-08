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
				<ul class="category-search">
					<li>
						<h5><a>针织</a></h5>
						<a href="enterprise/showList/101">针织内裤</a><span>|</span>
						<a href="enterprise/showList/101">针织大衣/风衣</a><span>|</span>
						<a href="enterprise/showList/101">针织便装</a>
						<a href="enterprise/showList/101">针织裤&nbsp;&nbsp;&nbsp;&nbsp;</a><span>|</span>
						<a href="enterprise/showList/101" style="padding:0px 13px">针织浴袍</a><span>|</span>
						<a href="enterprise/showList/101">针织衬衫</a>
					</li>
					<li>
						<h5><a>梭织</a></h5>
						<a href="enterprise/showList/101">牛仔服装</a><span>|</span>
						<a href="enterprise/showList/101">真丝服装</a><span>|</span>
						<a href="enterprise/showList/101">旗袍</a><span>|</span>
						<a href="enterprise/showList/101">休闲服</a>
						<a href="enterprise/showList/101">手工定制</a><span>|</span>
						<a href="enterprise/showList/101">中山装</a><span>|</span>
						<a href="enterprise/showList/101">唐装</a><span>|</span>
						<a href="enterprise/showList/101">汉服</a><span>|</span>
						<a href="enterprise/showList/101">其他</a>
					</li>
					<li class="fushi_li">
						<h5><a>服饰</a></h5>
						<a href="enterprise/showList/101">帽类</a><span>|</span>
						<a href="enterprise/showList/101">领带</a><span>|</span>
						<a href="enterprise/showList/101">围巾类</a><span>|</span>
						<a href="enterprise/showList/101">手套</a>
						<a href="enterprise/showList/101">袜子</a><span>|</span>
						<a href="enterprise/showList/101">阳伞</a><span>|</span>
						<a href="enterprise/showList/101">工艺品</a><span>|</span>
						<a href="enterprise/showList/101">箱包</a>
					</li>
					<li class="fuliao_li">
						<h5><a>服饰辅料</a></h5>
						<a href="enterprise/showList/101">纽扣</a><span>|</span>
						<a href="enterprise/showList/101">花边</a><span>|</span>
						<a href="enterprise/showList/101">拉链</a><span>|</span>
						<a href="enterprise/showList/101">织带/线/绳类</a>
						<a href="enterprise/showList/101">衣架</a><span>|</span>
						<a href="enterprise/showList/101">衬料</a><span>|</span>
						<a href="enterprise/showList/101">腰带</a><span>|</span>
						<a href="enterprise/showList/101">饰品/配饰</a>
					</li>
					<li class="jiafang_li">
						<h5><a>家纺</a></h5>
						<a href="enterprise/showList/101">装饰布艺</a><span>|</span>
						<a href="enterprise/showList/101">浴巾</a><span>|</span>
						<a href="enterprise/showList/101">毛巾</a>
						<a href="enterprise/showList/101">沙发套</a><span>|</span>
						<a href="enterprise/showList/101">汽车垫</a><span>|</span>
						<a href="enterprise/showList/101">其他家纺</a>
					</li>
					<li class="picao_li">
						<h5><a>皮革皮草</a></h5>
						<a href="enterprise/showList/101">仿皮服装</a><span>|</span>
						<a href="enterprise/showList/101">裘皮服装</a><span>|</span>
						<a href="enterprise/showList/101">仿裘皮服装</a>
					</li>
					<li class="xie_li">
						<h5><a>鞋类</a></h5>
						<a href="enterprise/showList/101">皮鞋</a><span>|</span>
						<a href="enterprise/showList/101">童鞋</a><span>|</span>
						<a href="enterprise/showList/101">运动鞋</a><span>|</span>
						<a href="enterprise/showList/101">休闲鞋</a>
						<a href="enterprise/showList/101">拖鞋</a><span>|</span>
						<a href="enterprise/showList/101">布鞋</a><span>|</span>
						<a href="enterprise/showList/101">凉鞋&nbsp;&nbsp;&nbsp;&nbsp;</a><span>|</span>
						<a href="enterprise/showList/101">女靴</a>
					</li>
				</ul>
				<!-- 子菜单 -->
				<div class="cat-subcategory">
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">针织内衣</a></h5>
									<table>
										<tr> <td><a href="aaa">女士文胸</a></td> <td><a href="#">泳装</a></td> <td><a href="#">泳裤</a></td> </tr>
										<tr> <td><a href="#">打底衫</a></td> <td><a href="#">吊带衫</a></td>	<td><a href="#">保暖内衣</a></td> </tr>
										<tr> <td><a href="#">保暖内裤</a></td> <td><a href="#">塑身衣</a></td> <td><a href="#">打底裤</a></td>	</tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织背心</a></h5>
									<table>
										<tr> <td><a href="#">男士背心</a></td> <td><a href="#">女士背心</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织睡衣</a></h5>
									<table>
										<tr> <td><a href="#">睡衣家居服</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">针织T恤衫</a></h5>
									<table>
										<tr> <td><a href="#">基本款T恤</a></td> <td><a href="#">开衫</a></td> <td><a href="#">娃娃衫</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织休闲衫</a></h5>
									<table>
										<tr> <td><a href="aaa">夹克拉链衫</a></td> <td><a href="#"></a></td> <td><a href="#">泳裤</a></td> </tr>
										<tr> <td><a href="#">打底衫</a></td> <td><a href="#">吊带衫</a></td>	<td><a href="#">保暖内衣</a></td> </tr>
										<tr> <td><a href="#">保暖内裤</a></td> <td><a href="#">塑身衣</a></td> <td><a href="#">打底裤</a></td>	</tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织睡衣</a></h5>
									<table>
										<tr> <td><a href="#">睡衣家居服</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div></div>
					<div></div>
					<div></div>
					<div></div>
					<div></div>
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
						<c:forEach var="enterprise" items="${strengths}">
							<div class="col-md-4">
								<img src="uploadFile/enterprise/${enterprise.logo}">
								<div>
									<a href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a>
								</div>
							</div>
						</c:forEach>
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
						<c:forEach var="enterprise" items="${auths}">
							<div class="col-md-4">
								<img src="uploadFile/enterprise/${enterprise.logo}">
								<div>
									<a href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>

<!-- 中间广告 -->
<table style="margin-bottom:20px;">
	<tr>
		<td style="width:595px;padding-right:3px">
			<img width="100%" src="image/ad/home_mid_ad1.png"/>
		</td>
		<td style="width:595px;padding-left:3px">
			<img width="100%" src="image/ad/home_mid_ad2.png"/>
		</td>
	</tr>
</table>

<!-- 服饰列表 -->
<table class="costumeList">
	<tr>
		<td class="td1">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<img src="image/ad/home_costume_ad1.png"/>
					</td>
					<td colspan="2"><h3>服装</h3></td>
				</tr>
				<tr>
					<td>
						<p>
							<a>梭织服装</a> <a>硬领衬衫</a> <a>标准西服西裤</a>
						</p>
						<p>
							<a>婚礼服饰</a> <a>工作服/制服/校服</a>
						</p>
						<p>
							<a>休闲裤</a> <a>夹克风衣</a> <a>休闲衬衫</a>
						</p>
						<p>
							<a>针织服装</a> <a>内衣内裤</a> <a>泳装</a>
						</p>
						<p>
							<a>针织婴童装</a> <a>针织运动/户外服</a>
						</p>
					</td>
					<td>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
					</td>
				</tr>
			</table>
		</td>
		<td class="td2">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<img src="image/ad/home_costume_ad2.png"/>
					</td>
					<td colspan="2"><h3>服饰</h3></td>
				</tr>
				<tr>
					<td>
						<p>
							<a>帽子</a> <a>领带</a> <a>围巾类</a>
						</p>
						<p>
							<a>手套</a> <a>袜子</a> <a>鞋类</a>
						</p>
						<p>
							<a>皮带腰带</a> <a>箱包/皮具</a> <a>其它服饰</a>
						</p>
						<p>
							<a>帽子</a> <a>领带</a> <a>围巾类</a>
						</p>
						<p>
							<a>手套</a> <a>袜子</a> <a>鞋类</a>
						</p>
					</td>
					<td>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table class="costumeList">
	<tr>
		<td class="td1">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<img src="image/ad/home_costume_ad3.png"/>
					</td>
					<td colspan="2"><h3>家纺</h3></td>
				</tr>
				<tr>
					<td>
						<p>
							<a>装饰布艺</a> <a>床上用品</a> <a>毛巾</a>
						</p>
						<p>
							<a>地毯/垫</a> <a>宠物纺织用品</a> <a>其它家纺</a>
						</p>
						<p>
							<a>装饰布艺</a> <a>床上用品</a> <a>毛巾</a>
						</p>
						<p>
							<a>地毯/垫</a> <a>宠物纺织用品</a> <a>其它家纺</a>
						</p>
						<p>
							<a>装饰布艺</a> <a>床上用品</a> <a>毛巾</a>
						</p>
					</td>
					<td>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
					</td>
				</tr>
			</table>
		</td>
		<td class="td2">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<img src="image/ad/home_costume_ad4.png"/>
					</td>
					<td colspan="2"><h3>纺织消费品</h3></td>
				</tr>
				<tr>
					<td>
						<p>
							<a>纺织类玩具</a> <a>户外用品</a>
						</p>
						<p>
							<a>其它纺织消费品</a> <a>梭织面料</a>
						</p>
						<p>
							<a>纺织类玩具</a> <a>户外用品</a>
						</p>
						<p>
							<a>其它纺织消费品</a> <a>梭织面料</a>
						</p>
						<p>
							<a>纺织类玩具</a> <a>户外用品</a>
						</p>
					</td>
					<td>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table class="costumeList">
	<tr>
		<td class="td1">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<img src="image/ad/home_costume_ad5.png"/>
					</td>
					<td colspan="2"><h3>面料/皮革/纱线</h3></td>
				</tr>
				<tr>
					<td>
						<p>
							<a>针织面料</a> <a>无纺布</a> <a>皮革皮具</a>
						</p>
						<p>
							<a>纺织纱线</a> <a>针织面料</a> <a>无纺布</a>
						</p>
						<p>
							<a>皮革皮草</a> <a>无纺布</a> <a>皮革皮草</a>
						</p>
						<p>
							<a>纺织纱线</a> <a>针织面料</a> <a>无纺布</a>
						</p>
						<p>
							<a>皮革皮草</a> <a>无纺布</a> <a>皮革皮草</a>
						</p>
					</td>
					<td>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
					</td>
				</tr>
			</table>
		</td>
		<td class="td2">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<img src="image/ad/home_costume_ad5.png"/>
					</td>
					<td colspan="2"><h3>纺织辅料</h3></td>
				</tr>
				<tr>
					<td>
						<p>
							<a>拉链类</a> <a>钮扣类</a> <a>线类</a>
						</p>
						<p>
							<a>花边类</a> <a>绳带类</a> <a>衬料/衬垫</a>
						</p>
						<p>
							<a>包装材料类</a> <a>衣架模特</a> <a>饰件类</a>
						</p>
						<p>
							<a>内衣配件</a> <a>其他辅料</a> <a>线类</a>
						</p>
						<p>
							<a>拉链类</a> <a>钮扣类</a> <a>线类</a>
						</p>
					</td>
					<td>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
						<img src="image/ad/home_ad_small.png"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<div style="font-size:16px;">友情链接</div>
<p style="border-top:2px solid grey;padding:5px 10px;line-height:30px;">
	<c:forEach items="${blogrolls}" var="blogroll">
		<a style="color:black;margin-right:20px;" href="${blogroll.url}" target="_blank">${blogroll.name}</a>
	</c:forEach>
</p>

</div>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/main/home.js"></script>
</body>
</html>

