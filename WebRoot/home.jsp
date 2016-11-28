<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>服饰加工网</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrap-login-form/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="plugin/superSlide/superSlide.css" rel="stylesheet">
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/home.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<input type="hidden" name="pageName" value="home">
<a id="topAd" target="_blank;"><img style="height:90px;widht:100%;"></a>
<br/>
<%@ include file="JSP/main/top.jsp" %>

<div class="main-body" style="width:1190px; margin:0 auto;">
<span style="display:none;" id="tradeAndCostumeMap">${applicationScope.tradeAndCostumeMap}</span>
<span style="display:none;" id="adPositions">${adPositions}</span>

<table id="table1">
	<tr>
		<!-- 左边栏 -->
		<td width="200px;">
			<div class="mallCategory" style="position:relative;">
				<ul class="category-search">
					<li>
						<h5><a href="#">针织</a></h5>
						<a href="#">针织内裤</a><span>|</span>
						<a href="#">针织大衣/风衣</a><span>|</span>
						<a href="#">针织便装</a>
						<a href="#">针织裤&nbsp;&nbsp;&nbsp;&nbsp;</a><span>|</span>
						<a href="#" style="padding:0px 13px">针织浴袍</a><span>|</span>
						<a href="#">针织衬衫</a>
					</li>
					<li>
						<h5><a href="#">梭织</a></h5>
						<a href="#">牛仔服装</a><span>|</span>
						<a href="#">真丝服装</a><span>|</span>
						<a href="#">旗袍</a><span>|</span>
						<a href="#">休闲服</a>
						<a href="#">手工定制</a><span>|</span>
						<a href="#">中山装&nbsp;&nbsp;&nbsp;&nbsp;</a><span>|</span>
						<a href="#">唐装</a><span>|</span>
						<a href="#">汉服</a>
					</li>
					<li class="fushi_li">
						<h5><a href="#">服饰</a></h5>
						<a href="#">帽类</a><span>|</span>
						<a href="#">领带</a><span>|</span>
						<a href="#">围巾类</a><span>|</span>
						<a href="#">手套</a>
						<a href="#">袜子</a><span>|</span>
						<a href="#">阳伞</a><span>|</span>
						<a href="#">工艺品</a><span>|</span>
						<a href="#">箱包</a>
					</li>
					<li class="fuliao_li">
						<h5><a href="#">服饰辅料</a></h5>
						<a href="#">纽扣</a><span>|</span>
						<a href="#">花边</a><span>|</span>
						<a href="#">拉链</a><span>|</span>
						<a href="#">织带/线/绳类</a>
						<a href="#">衣架</a><span>|</span>
						<a href="#">衬料</a><span>|</span>
						<a href="#">腰带</a><span>|</span>
						<a href="#">饰品/配饰</a>
					</li>
					<li class="jiafang_li">
						<h5><a href="#">家纺</a></h5>
						<a href="#">装饰布艺</a><span>|</span>
						<a href="#">浴巾</a><span>|</span>
						<a href="#">毛巾</a>
						<br/>
						<a href="#">沙发套</a><span>|</span>
						<a href="#">汽车垫</a><span>|</span>
						<a href="#">其他家纺</a>
					</li>
					<li class="picao_li">
						<h5><a href="#">皮革皮草</a></h5>
						<a href="#">仿皮服装</a><span>|</span>
						<a href="#">裘皮服装</a><span>|</span>
						<a href="#">仿裘皮服装</a>
					</li>
					<li class="xie_li">
						<h5><a href="#">鞋类</a></h5>
						<a href="#">皮鞋</a><span>|</span>
						<a href="#">童鞋</a><span>|</span>
						<a href="#">运动鞋</a><span>|</span>
						<a href="#">休闲鞋</a>
						<a href="#">拖鞋</a><span>|</span>
						<a href="#">布鞋</a><span>|</span>
						<a href="#">凉鞋&nbsp;&nbsp;&nbsp;&nbsp;</a><span>|</span>
						<a href="#">女靴</a>
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
										<tr> <td><a href="#">女士文胸</a></td> <td><a href="#">泳装</a></td> <td><a href="#">泳裤</a></td> </tr>
										<tr> <td><a href="#">打底衫</a></td> <td><a href="#">吊带衫</a></td> <td><a href="#">保暖内衣</a></td> </tr>
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
										<tr> <td><a href="#">夹克拉链衫</a></td> <td><a href="#">针织牛仔服装</a></td> <td><a href="#">套衫</a></td> </tr>
										<tr> <td><a href="#">蝙蝠衫</a></td> <td><a href="#">广告衫</a></td> <td><a href="#">文化衫</a></td> </tr>
										<tr> <td><a href="#">针织女装开衫</a></td> <td><a href="#">男士T恤</a></td> <td></td>	</tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织西装</a></h5>
									<table>
										<tr> <td><a href="#">小西服</a></td> <td><a href="#">针织立领西装</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">针织上衣</a></h5>
									<table>
										<tr> <td><a href="#">针织衫</a></td> <td><a href="#">卫衣</a></td> <td><a href="#">男女针织带帽衫</a></td> </tr>
										<tr> <td><a href="#">色织印条面料服装</a></td> <td><a href="#">针织填棉服装</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织裙</a></h5>
									<table>
										<tr> <td><a href="#">连衣裙</a></td> <td><a href="#">短裙</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">针织运动服</a></h5>
									<table>
										<tr> <td><a href="#">卫衣套头衫</a></td> <td><a href="#">针织运动服装</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">针织婴童装</a></h5>
									<table>
										<tr> <td><a href="#">童装</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">丝绸类</a></h5>
									<table>
										<tr> <td><a href="#">蚕丝</a></td> <td><a href="#">真丝</a></td> <td><a href="#">真丝绸</a></td> </tr>
										<tr> <td><a href="#">人丝绸</a></td> <td><a href="#">合纤绸</a></td> <td><a href="#">交织绸</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">其他</a></h5>
									<table>
										<tr> <td><a href="#">无缝针织服装</a></td> <td><a href="#">针织品羊毛衫</a></td> </tr>
										<tr> <td><a href="#">针织品羊毛衫</a></td> <td><a href="#">绒衫/摇粒绒衫</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- 梭织 -->
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">羽绒服装</a></h5>
									<table>
										<tr> <td><a href="#">羽绒大衣</a></td> <td><a href="#">羽绒棉</a></td> </tr>
										<tr> <td><a href="#">羽绒短上衣</a></td> <td><a href="#">其他羽绒服装</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">运动服</a></h5>
									<table>
										<tr> <td><a href="#">滑雪服</a></td> <td><a href="#">游泳服</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">西服</a></h5>
									<table>
										<tr> <td><a href="#">男西服</a></td> <td><a href="#">女西服</a></td> <td><a href="#">小西装</a></td> </tr>
										<tr> <td><a href="#">休闲西服</a></td> <td><a href="#">韩版西服</a></td> <td><a href="#">正装西服</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">西裤</a></h5>
									<table>
										<tr> <td><a href="#">男西裤</a></td> <td><a href="#">女西裤</a></td> <td><a href="#">中式西裤</a></td> </tr>
										<tr> <td><a href="#">韩版西裤</a></td> <td><a href="#">欧式西裤</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">衬衫</a></h5>
									<table>
										<tr> <td><a href="#">翻领衬衫</a></td> <td><a href="#">休闲衬衫</a></td> <td><a href="#">格子衬衫</a></td> </tr>
										<tr> <td><a href="#">条纹衬衫</a></td> <td><a href="#">印花衬衫</a></td> <td><a href="#">图案衬衫</a></td> </tr>
										<tr> <td><a href="#">方领衬衫</a></td> <td><a href="#">香槟领衬衫</a></td> <td><a href="#">尖领衬衫</a></td>	</tr>
									</table>
								</td>
								<td>
									<h5><a href="#">裤</a></h5>
									<table>
										<tr> <td><a href="#">工作裤</a></td> <td><a href="#">短裤</a></td> <td><a href="#">七分裤</a></td> <td><a href="#">九分裤</a></td> <td><a href="#">长裤</a></td> </tr>
										<tr> <td><a href="#">连体裤</a></td> <td><a href="#">热裤</a></td> <td><a href="#">哈伦裤</a></td> <td><a href="#">高腰裤</a></td> <td><a href="#">打底裤</a></td> </tr>
										<tr> <td><a href="#">雪纺裤</a></td> <td><a href="#">休闲裤</a></td> <td><a href="#">直筒裤</a></td> <td><a href="#">小脚裤</a></td> <td><a href="#">五分裤</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">裙</a></h5>
									<table>
										<tr> <td><a href="#">长裙</a></td> <td><a href="#">短裙</a></td> <td><a href="#">裙裤</a></td> <td><a href="#">衬裙</a></td> </tr>
										<tr> <td><a href="#">雪纺裙</a></td> <td><a href="#">高腰裙</a></td> <td><a href="#">百褶裙</a></td> </tr>
										<tr> <td><a href="#">连衣裙</a></td> <td><a href="#">蓬蓬裙</a></td> <td><a href="#">公主裙</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">内衣</a></h5>
									<table>
										<tr> <td><a href="#">胸罩</a></td> <td><a href="#">束腰带</a></td> <td><a href="#">腹带</a></td> </tr>
										<tr> <td><a href="#">紧身胸衣</a></td> <td><a href="#">吊带裤</a></td> <td><a href="#">吊带衫</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">职业装</a></h5>
									<table>
										<tr> <td><a href="#">酒店服</a></td> <td><a href="#">工作服</a></td> <td><a href="#">舞蹈服</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">呢制服</a></h5>
									<table>
										<tr> <td><a href="#">羊绒大衣</a></td> <td><a href="#">双面羊绒大衣</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">棉类服装</a></h5>
									<table>
										<tr> <td><a href="#">睡衣</a></td> <td><a href="#">睡裙</a></td> <td><a href="#">睡袍</a></td> </tr>
										
									</table>
								</td>
								<td>
									<h5><a href="#">婚纱礼服</a></h5>
									<table>
										<tr> <td><a href="#">水晶纱</a></td> <td><a href="#">雪纺</a></td> <td><a href="#">珍珠纱</a></td> </tr>
										<tr> <td><a href="#">塔夫绸</a></td> <td><a href="#">雪纱</a></td> <td><a href="#">冰纱</a></td> </tr>
										<tr> <td><a href="#">头巾纱</a></td> <td><a href="#">欧根纱</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- 服饰 -->
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">帽类</a></h5>
									<table>
										<tr> <td><a href="#">牛仔帽</a></td> <td><a href="#">鸭舌帽</a></td> <td><a href="#">旅游帽</a></td> </tr>
										<tr> <td><a href="#">广告帽</a></td> <td><a href="#">保安帽</a></td> <td><a href="#">圣诞帽</a></td> </tr>
										<tr> <td><a href="#">儿童帽</a></td> <td><a href="#">太阳帽</a></td> <td><a href="#">成人帽</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">围巾类</a></h5>
									<table>
										<tr> <td><a href="#">披肩</a></td> <td><a href="#">披毯</a></td> <td><a href="#">围脖</a></td> <td><a href="#">丝巾</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">手套</a></h5>
									<table>
										<tr> <td><a href="#">劳护手套</a></td> <td><a href="#">针织手套</a></td> <td><a href="#">滑雪手套</a></td> </tr>
										<tr> <td><a href="#">保暖手套</a></td> <td><a href="#">摇粒绒手套</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">袜子</a></h5>
									<table>
										<tr> <td><a href="#">呢绒袜</a></td> <td><a href="#">棉袜</a></td> <td><a href="#">纱袜</a></td> <td><a href="#">丝袜</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">工艺品</a></h5>
									<table>
										<tr> <td><a href="#">木头工艺品</a></td> <td><a href="#">铁件工艺品</a></td> <td><a href="#">塑料工艺品</a></td> </tr>
										<tr> <td><a href="#">布料工艺品</a></td> <td><a href="#">呢绒工艺品</a></td> <td><a href="#">喜庆工艺品</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">阳伞</a></h5>
									<table>
										<tr> <td><a href="#">遮阳伞</a></td> <td><a href="#">沙滩伞</a></td> <td><a href="#">雨伞</a></td> </tr>
										<tr> <td><a href="#">广告伞</a></td> <td><a href="#">儿童伞</a></td> <td><a href="#">工艺伞</a></td> </tr>
										<tr> <td><a href="#">钓鱼伞</a></td> <td><a href="#">自动开收伞</a></td> <td><a href="#">帐篷伞</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">箱包</a></h5>
									<table>
										<tr> <td><a href="#">皮夹</a></td> <td><a href="#">钱包</a></td> <td><a href="#">公文包</a></td> </tr>
										<tr> <td><a href="#">登山包</a></td> <td><a href="#">手提箱</a></td> <td><a href="#">拉杆箱</a></td> </tr>
										<tr> <td><a href="#">双肩包</a></td> <td><a href="#">箱包配件</a></td> <td><a href="#">其他</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">其他服饰</a></h5>
									<table>
										<tr> <td><a href="#">车罩</a></td> <td><a href="#">汽车坐垫套</a></td> <td><a href="#">帐篷</a></td> <td><a href="#">玩具</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- 服饰辅料 -->
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">钮扣</a></h5>
									<table>
										<tr> <td><a href="#">铜质扣</a></td> <td><a href="#">树脂扣</a></td> <td><a href="#">塑料扣</a></td> <td><a href="#">椰壳扣</a></td> </tr>
										<tr> <td><a href="#">五爪扣</a></td> <td><a href="#">果实扣</a></td> <td><a href="#">真皮扣</a></td> <td><a href="#">牛角扣</a></td> </tr>
										<tr> <td><a href="#">布包扣</a></td> <td><a href="#">竹木扣</a></td> <td><a href="#">贝壳扣</a></td> <td><a href="#">四合扣</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">花边</a></h5>
									<table>
										<tr> <td><a href="#">机织花边</a></td> <td><a href="#">针织花边</a></td> <td><a href="#">刺绣花边</a></td> <td><a href="#">编织花边</a></td> </tr>
										<tr> <td><a href="#">水溶花边</a></td> <td><a href="#">蕾丝花边</a></td> <td><a href="#">弹力花边</a></td> <td><a href="#">手工花边</a></td> </tr>
										<tr> <td><a href="#">亮片锈花边</a></td> <td><a href="#">窗帘花边</a></td> <td><a href="#">民族花边</a></td> <td><a href="#">其他花边</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">拉链</a></h5>
									<table>
										<tr> <td><a href="#">尼龙拉链</a></td> <td><a href="#">树脂拉链</a></td> <td><a href="#">金属拉链</a></td> <td><a href="#">压塑拉链</a></td> </tr>
										<tr> <td><a href="#">隐形拉链</a></td> <td><a href="#">防水拉链</a></td> <td><a href="#">拉链头</a></td> <td><a href="#">其他拉链</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">织带/线/绳类</a></h5>
									<table>
										<tr> <td><a href="#">纱线</a></td> <td><a href="#">缝纫线</a></td> <td><a href="#">珠片带</a></td> <td><a href="#">丝带</a></td> </tr>
										<tr> <td><a href="#">涤纶</a></td> <td><a href="#">线</a></td> <td><a href="#">鱼丝线</a></td> <td><a href="#">尼龙线</a></td> </tr>
										<tr> <td><a href="#">高强线</a></td> <td><a href="#">绳</a></td> <td><a href="#">绣花线</a></td> <td><a href="#">印花带</a></td> </tr>
										<tr> <td><a href="#">织带</a></td> <td><a href="#">松紧带</a></td> <td><a href="#">魔术贴</a></td> <td><a href="#">其他带绳线</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">衣架</a></h5>
									<table>
										<tr> <td><a href="#">木衣架</a></td> <td><a href="#">塑料衣架</a></td> <td><a href="#">金属衣架</a></td> <td><a href="#">裤架</a></td> </tr>
										<tr> <td><a href="#">模特架</a></td> <td><a href="#">展示架</a></td> <td><a href="#">其他衣架</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">衬料</a></h5>
									<table>
										<tr> <td><a href="#">里布</a></td> <td><a href="#">粘合衬</a></td> <td><a href="#">嵌条</a></td> <td><a href="#">衬布</a></td> </tr>
										<tr> <td><a href="#">网布</a></td> <td><a href="#">罗布</a></td> <td><a href="#">填充物</a></td> </tr>
										<tr> <td><a href="#">肩垫</a></td> <td><a href="#">罩杯/胸垫</a></td> <td><a href="#">其他衬料</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">商标</a></h5>
									<table>
										<tr> <td><a href="#">织唛/吊牌</a></td> <td><a href="#">印唛</a></td> <td><a href="#">洗水唛</a></td> <td><a href="#">尺码唛</a></td> </tr>
										<tr> <td><a href="#">印标</a></td> <td><a href="#">皮标</a></td> <td><a href="#">吊粒</a></td> <td><a href="#">肩章</a></td> </tr>
										<tr> <td><a href="#">徽章</a></td> <td><a href="#">其他商标</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">腰带</a></h5>
									<table>
										<tr> <td><a href="#">男士腰带</a></td> <td><a href="#">女士腰带</a></td> <td><a href="#">手编腰带</a></td> </tr>
										<tr> <td><a href="#">时尚腰带</a></td> <td><a href="#">皮带</a> <a href="#">皮条</a></td> <td><a href="#">其他腰带</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">饰品/配饰</a></h5>
									<table>
										<tr> <td><a href="#">烫钻</a></td> <td><a href="#">珠片亚克力</a></td> <td><a href="#">刺绣</a></td> <td><a href="#">挂件</a></td> </tr>
										<tr> <td><a href="#">贝壳</a></td> <td><a href="#">爪石</a></td> <td><a href="#">水钻</a></td> <td><a href="#">网钻</a></td> </tr>
										<tr> <td><a href="#">水晶类</a></td> <td><a href="#">帽钟</a></td> <td><a href="#">八角片</a></td> <td><a href="#">滴胶珠</a></td> </tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<h5><a href="#">皮毛/羽毛</a></h5>
									<table>
										<tr> <td><a href="#">人造皮毛</a></td> <td><a href="#">动物皮毛</a></td> <td><a href="#">其他皮毛</a></td> </tr>
										<tr> <td><a href="#">羽毛</a></td> <td><a href="#">羽毛制品</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">机械</a></h5>
									<table>
										<tr> <td><a href="#">拉链机械</a></td> <td><a href="#">纽/钮扣机械</a></td> <td><a href="#">织带机械</a></td> </tr>
										<tr> <td><a href="#">衬料机械</a></td> <td><a href="#">花边机械</a></td> <td><a href="#">饰品机械</a></td> </tr>
										<tr> <td><a href="#">商标机械</a></td> <td><a href="#">衣架机械</a></td> <td><a href="#">包装机械</a></td> </tr>
									</table>
								</td>
								<td>
									<h5><a href="#">其他服装辅料</a></h5>
									<table>
										<tr> <td><a href="#">胶袋</a></td> <td><a href="#">包装盒</a></td> <td><a href="#">包装袋</a></td> <td><a href="#">手提袋</a></td> </tr>
										<tr> <td><a href="#">包装箱</a></td> <td><a href="#">胶带</a></td> <td><a href="#">胶枪</a></td> <td><a href="#">胶针</a></td> </tr>
										<tr> <td><a href="#">热熔胶条</a></td> <td><a href="#">打针枪</a></td> <td><a href="#">热熔胶膜</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- 家纺 -->
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">床上用品</a></h5>
									<table>
										<tr> <td><a href="#">被套</a></td> <td><a href="#">床单</a></td> <td><a href="#">枕头套</a></td> </tr>
										<tr> <td><a href="#">儿童被套</a></td> <td></td> <td><a href="#">酒店床上用品</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<!-- 皮革皮草 -->
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">皮革服装</a></h5>
									<table>
										<tr> <td><a href="#">真皮皮衣</a></td> <td><a href="#">PU皮衣</a></td> <td><a href="#">皮裤</a></td> </tr>
										<tr> <td><a href="#">皮草</a></td> <td><a href="#">皮裙</a></td> <td><a href="#">男装皮革外套</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div>
						<table>
							<tr>
								<td>
									<h5><a href="#">运动鞋</a></h5>
									<table>
										<tr> <td><a href="#">篮球鞋</a></td> <td><a href="#">跑步鞋</a></td> <td><a href="#">女子健身鞋</a></td> </tr>
										<tr> <td><a href="#">足球鞋</a></td> <td><a href="#">网球鞋</a></td> <td><a href="#">综合训练鞋</a></td> </tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</td>
		<!-- 中间栏 -->
		<td width="760px" style="padding:0px 5px;">
			<div id="carousel-big-ad" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<!-- <li data-target="#carousel-big-ad" data-slide-to="0" class="active"></li> -->
				</ol>
				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<!-- <div class="item active">
						<a><img src="image/ad/big_ad1.jpg"></a>
						<div class="carousel-caption">
						</div>
					</div>
					<div class="item">
						<img src="image/ad/big_ad2.jpg">
						<div class="carousel-caption">
						</div>
					</div> -->
				</div>
				<div name="sample" style="display:none">
					<li data-target="#carousel-big-ad" data-slide-to="0"></li>
					
					<div class="item">
						<a target="_blank"><img src="image/ad/big_ad2.jpg"></a>
						<div class="carousel-caption">
						</div>
					</div>
				</div>
				<!-- Controls -->
				<!-- <a class="left carousel-control" href="#carousel-big-ad" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span>
				</a>
				<a class="right carousel-control" href="#carousel-big-ad" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right"></span>
				</a> -->
			</div>
			<div id="carousel-small-ad" class="picScroll">
				<div class="tempWrap" style="overflow:hidden; position:relative; width:670px">
					<ul style="width: 1206px; left: -9.62442px; position: relative; overflow: hidden; padding: 0px; margin: 0px;">
						<!-- <li style="float:left; width:122px;"><a target="_blank"><img src="images/pic1.jpg"></a></li> -->
					</ul>
				</div>
				<div name="sample" style="display:none;">
					<li style="float:left; width:118px;"><a target="_blank"><img src="images/pic1.jpg"></a></li>
				</div>
				<a class="prev prevStop"><span class="glyphicon glyphicon-chevron-left"></span></a> 
				<a class="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
			</div>
		</td>
		<!-- 右边栏 -->
		<td width="230px">
			<div class="panel panel-default" style="margin-bottom:5px;">
				<div class="panel-body" style="padding:20px 0 0 0;">
					<div class="media">
						<div class="media-left">
							<img class="media-object" src="image/touxiang.png">
						</div>
						<div class="media-body">
							<h4 class="media-heading">Hi,你好</h4>
							<p>欢迎来到服饰加工网</p>
						</div>
					</div>
					<table class="loginButtonGrp">
						<tr>
							<td>
								<a href="login.jsp"><i class="fa fa-key"></i>登录</a>
							</td>
							<td>
								<a href="JSP/signUp.jsp" ><i class="fa fa-pencil"></i>注册</a>
							</td>
						</tr>
					</table>
					<h5 style="padding:0 10px;">公告</h5>
					<ul class="affiche">
					    <!-- <li>[公告]B2B的春天正在到来</li>
					    <li>[公告]海外尖货频道上线</li>
					    <li>[公告]开启拳王诚心通时代</li>
					    <li>[公告]海外尖货频道上线</li> -->
					</ul>
					<div id="afficheDemo" style="display:none">
						<li style="width:183px;"><a>[公告]B2B的春天sdfsf正在到来</a></li>
					</div>
					<a name="right_top_ad" target="_blank"><img width="100%" height="80px"></a>
					<a name="right_top_ad" target="_blank"><img width="100%" height="80px"></a>
				</div>
			</div>
		</td>
	</tr>
</table>

<!-- 服饰分类栏下方广告 -->
<table class="mid_ad_table" style="margin-bottom:10px;">
	<tr>
		<td>
			<a class="mid_ad" target="_blank"><img></a>
		</td>
		<td>
			<a class="mid_ad" target="_blank"><img></a>
		</td>
	</tr>
</table>

<table id="table2">
	<tr>
		<td style="width:595px;padding-right:3px">
			<!-- 实力工厂 -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<span>实力工厂</span>
					<div class="pull-right">
				 		<a href="enterprise/search" style="text-decoration:none;"><span class="label label-info" style="font-size:14px;color:white;">更多</span></a>
					</div>
				</div>
				<div class="panel-body">
					<div class="col-md-4 ad">
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:240px;"></a>
					</div>
					<div class="col-md-8">
						<c:forEach var="enterprise" items="${strengths}">
							<div class="col-md-4">
								<img src="uploadFile/enterprise/${enterprise.logo}">
								<div>
									<a target="_blank" href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a>
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
				<div class="panel-heading">
					<span>认证工厂</span>
					<div class="pull-right">
				 		<a href="enterprise/search" style="text-decoration:none;"><span class="label label-info" style="font-size:14px;color:white;">更多</span></a>
					</div>
				</div>
				<div class="panel-body">
					<div class="col-md-4 ad">
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:240px;"></a>
					</div>
					<div class="col-md-8">
						<c:forEach var="enterprise" items="${auths}">
							<div class="col-md-4">
								<img src="uploadFile/enterprise/${enterprise.logo}">
								<div>
									<a target="_blank" href="enterprise/showDetail/${enterprise.id}">${enterprise.name}</a>
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
<table class="mid_ad_table" style="margin-bottom:20px;">
	<tr>
		<td>
			<a class="mid_ad" target="_blank"><img></a>
		</td>
		<td>
			<a class="mid_ad" target="_blank"><img></a>
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
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:291px;"></a>
					</td>
					<td colspan="2">
						<div style="float:left;">
							<h3><span>&nbsp;</span>针织</h3>
							<div><a target="_blank" class="text_ad"></a></div>
						</div>
						<a class="table_sm_ad" target="_blank"><img style="width:139px;float:right;"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td><a href="#">广告衫</a></td> <td><a href="#">针织衫</a></td> <td><a href="#">连衣裙</a></td>
							</tr>
							<tr>
								<td><a href="#">针织内衣</a></td> <td><a href="#">保暖内衣</a></td> <td><a href="#">针织睡衣</a></td>
							</tr>
							<tr>
								<td><a href="#">打底衫</a></td> <td><a href="#">针织运动服</a></td> <td><a href="#">打底裤</a></td>
							</tr>
							<tr>
								<td><a href="#">针织衬衫</a></td> <td><a href="#">针织T恤衫</a></td> <td><a href="#">男士T恤</a></td>
							</tr>
							<tr>
								<td><a href="#">卫衣</a></td> <td><a href="#">针织女装开衫</a></td> <td><a href="#">童装</a></td>
							</tr>
						</table>
					</td>
					<td>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
					</td>
				</tr>
			</table>
		</td>
		<td class="td2">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:291px;"></a>
					</td>
					<td colspan="2">
						<div style="float:left;">
							<h3><span>&nbsp;</span>梭织</h3>
							<div><a target="_blank" class="text_ad"></a></div>
						</div>
						<a class="table_sm_ad" target="_blank"><img style="width:139px;float:right;"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td><a href="#">风衣</a></td> <td><a href="#">西裤</a></td> <td><a href="#">衬衫</a></td>
							</tr>
							<tr>
								<td><a href="#">羽绒服装</a></td> <td><a href="#">牛仔服装</a></td> <td><a href="#">韩版西服</a></td>
							</tr>
							<tr>
								<td><a href="#">裤裙</a></td> <td><a href="#">童装</a></td> <td><a href="#">睡衣</a></td>
							</tr>
							<tr>
								<td><a href="#">职业装</a></td> <td><a href="#">休闲服</a></td> <td><a href="#">运动服</a></td>
							</tr>
							<tr>
								<td><a href="#">正装西服</a></td> <td><a href="#">吊带衫</a></td> <td><a href="#">酒店服</a></td>
							</tr>
						</table>
					</td>
					<td>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
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
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:291px;"></a>
					</td>
					<td colspan="2">
						<div style="float:left;">
							<h3><span>&nbsp;</span>服饰</h3>
							<div><a target="_blank" class="text_ad"></a></div>
						</div>
						<a class="table_sm_ad" target="_blank"><img style="width:139px;float:right;"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td><a href="#">帽类</a></td> <td><a href="#">领带</a></td> <td><a href="#">围巾类</a></td>
							</tr>
							<tr>
								<td><a href="#">手套</a></td> <td><a href="#">袜子</a></td> <td><a href="#">箱包</a></td>
							</tr>
							<tr>
								<td><a href="#">披肩</a></td> <td><a href="#">丝巾</a></td> <td><a href="#">广告帽</a></td>
							</tr>
							<tr>
								<td><a href="#">帐篷</a></td> <td><a href="#">广告伞</a></td> <td><a href="#">拉杆箱</a></td>
							</tr>
							<tr>
								<td><a href="#">汽车坐垫套</a></td> <td><a href="#">布料工艺品</a></td>
							</tr>
						</table>
					</td>
					<td>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
					</td>
				</tr>
			</table>
		</td>
		<td class="td2">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:291px;"></a>
					</td>
					<td colspan="2">
						<div style="float:left;">
							<h3><span>&nbsp;</span>家纺</h3>
							<div><a target="_blank" class="text_ad"></a></div>
						</div>
						<a class="table_sm_ad" target="_blank"><img style="width:139px;float:right;"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td><a href="#">浴巾</a></td> <td><a href="#">毛巾</a></td> <td><a href="#">被套</a></td>
							</tr>
							<tr>
								<td><a href="#">装饰布艺</a></td> <td><a href="#">床上用品</a></td> <td><a href="#">儿童被套</a></td>
							</tr>
							<tr>
								<td><a href="#">沙发套</a></td> <td><a href="#">枕头套</a></td> <td><a href="#">汽车垫</a></td>
							</tr>
							<tr>
								<td><a href="#">其他家纺</a></td> <td><a href="#">酒店床上用品</a></td> <td><a href="#">床单</a></td>
							</tr>
						</table>
					</td>
					<td>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
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
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:291px;"></a>
					</td>
					<td colspan="2">
						<div style="float:left;">
							<h3><span>&nbsp;</span>皮革皮草</h3>
							<div><a target="_blank" class="text_ad"></a></div>
						</div>
						<a class="table_sm_ad" target="_blank"><img style="width:139px;float:right;"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td><a href="#">皮革服装</a></td> <td><a href="#">仿皮服装</a></td> <td><a href="#">裘皮服装</a></td>
							</tr>
							<tr>
								<td><a href="#">皮裤</a></td> <td><a href="#">皮裙</a></td> <td><a href="#">皮草</a></td>
							</tr>
							<tr>
								<td><a href="#">真皮皮衣</a></td> <td></td> <td><a href="#">PU皮衣</a></td>
							</tr>
							<tr>
								<td><a href="#">男装皮革外套</a></td> <td></td> <td><a href="#">仿裘皮服装</a></td>
							</tr>
						</table>
					</td>
					<td>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
					</td>
				</tr>
			</table>
		</td>
		<td class="td2">
			<table class="table table-bordered">
				<tr>
					<td rowspan="2">
						<a class="left_big_ad" target="_blank"><img style="width:100%;height:291px;"></a>
					</td>
					<td colspan="2">
						<div style="float:left;">
							<h3><span>&nbsp;</span>鞋</h3>
							<div><a target="_blank" class="text_ad"></a></div>
						</div>
						<a class="table_sm_ad" target="_blank"><img style="width:139px;float:right;"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td><a href="#">皮鞋</a></td> <td><a href="#">布鞋</a></td> <td><a href="#">拖鞋</a></td>
							</tr>
							<tr>
								<td><a href="#">童鞋</a></td> <td><a href="#">女鞋</a></td> <td><a href="#">男鞋</a></td>
							</tr>
							<tr>
								<td><a href="#">旅游鞋</a></td> <td><a href="#">运动鞋</a></td> <td><a href="#">休闲鞋</a></td>
							</tr>
						</table>
					</td>
					<td>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
						<a class="table_sm_ad" target="_blank"><img></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table class="mid_ad_table" style="margin-bottom:20px;">
	<tr>
		<td style="width:980px;">
			<a class="mid_ad" target="_blank"><img></a>
		</td>
		<td style="width:210px;">
			<a class="mid_ad" target="_blank"><img></a>
		</td>
	</tr>
</table>

<!-- 品牌加盟 -->
<div id="bottom_carousel">
	<p style="border:1px solid #DDDDDD;color:#317EE7;font-size:16px;font-weight:bold;padding:3px 0 3px 5px;">品牌加盟</p>
	<div class="picScroll">
		<div class="tempWrap" style="overflow:hidden; position:relative; width:100%">
			<ul style="left: -9.62442px; position: relative; overflow: hidden; padding: 0px; margin: 0px;">
				<!-- <li style="float:left; width:124px;display:none;"><a target="_blank"><img src="images/pic2.jpg"></a></li> -->
			</ul>
		</div>
		<div name="sample" style="display:none;">
			<li style="float:left; width:124px;margin-right:10px"><a target="_blank"><img></a></li>
		</div>
		<a class="prev prevStop"><span class="glyphicon glyphicon-chevron-left"></span></a> 
		<a class="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
	</div>
</div>

<!-- 行业资讯 -->
<div id="tradeNews" style="border:1px solid #DDDDDD;">
	<p style="border-bottom:1px solid #DDDDDD;color:#317EE7;font-size:16px;font-weight:bold;padding:3px 0 3px 5px;margin-bottom:0px;">行业资讯</p>
	<table style="width:100%;">
		<tr>
			<td>
				<div class="media">
  					<a class="media-left" target="_blank" href="tradeNews/showDetail/${tradeNews[0].id}">
						<img src=""/>
					</a>
					<div class="media-body">
						<a target="_blank" href="tradeNews/showDetail/${tradeNews[0].id}"><h4 class="media-heading">${tradeNews[0].title}</h4></a>
						<span>${tradeNews[0].content}</span>
					</div>
				</div>
			</td>
			<td>
				<div class="media">
  					<a class="media-left" target="_blank" href="tradeNews/showDetail/${tradeNews[1].id}">
						<img src=""/>
					</a>
					<div class="media-body">
						<a target="_blank" href="tradeNews/showDetail/${tradeNews[1].id}"><h4 class="media-heading">${tradeNews[1].title}</h4></a>
						<span>${tradeNews[1].content}</span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="media">
  					<a class="media-left" target="_blank" href="tradeNews/showDetail/${tradeNews[2].id}">
						<img src=""/>
					</a>
					<div class="media-body">
						<a target="_blank" href="tradeNews/showDetail/${tradeNews[2].id}"><h4 class="media-heading">${tradeNews[2].title}</h4></a>
						<span>${tradeNews[2].content}</span>
					</div>
				</div>
			</td>
			<td>
				<div class="media">
  					<a class="media-left" target="_blank" href="tradeNews/showDetail/${tradeNews[3].id}">
						<img src=""/>
					</a>
					<div class="media-body">
						<a target="_blank" href="tradeNews/showDetail/${tradeNews[3].id}"><h4 class="media-heading">${tradeNews[3].title}</h4></a>
						<span>${tradeNews[3].content}</span>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<a target="_blank" href="tradeNews/showDetail/${tradeNews[4].id}">${tradeNews[4].title}</a>
				<p><fmt:formatDate value="${tradeNews[4].updateTime}"/></p>
			</td>
			<td>
				<a target="_blank" href="tradeNews/showDetail/${tradeNews[5].id}">${tradeNews[5].title}</a>
				<p><fmt:formatDate value="${tradeNews[5].updateTime}"/></p>
			</td>
		</tr>
		<tr>
			<td>
				<a target="_blank" href="tradeNews/showDetail/${tradeNews[6].id}">${tradeNews[6].title}</a>
				<p><fmt:formatDate value="${tradeNews[6].updateTime}"/></p>
			</td>
			<td>
				<a target="_blank" href="tradeNews/showDetail/${tradeNews[7].id}">${tradeNews[7].title}</a>
				<p><fmt:formatDate value="${tradeNews[7].updateTime}"/></p></td>
		</tr>
		<tr>
			<td>
				<a target="_blank" href="tradeNews/showDetail/${tradeNews[8].id}">${tradeNews[8].title}</a>
				<p><fmt:formatDate value="${tradeNews[8].updateTime}"/></p>
			</td>
			<td>
				<a target="_blank" href="tradeNews/showDetail/${tradeNews[9].id}">${tradeNews[9].title}</a>
				<p><fmt:formatDate value="${tradeNews[9].updateTime}"/></p>
			</td>
		</tr>
	</table>
</div>

<div style="font-size:16px;margin-top:20px;">友情链接</div>
<p class="blogroll" style="border-top:2px solid grey;padding:5px 10px;line-height:30px;">
	<c:forEach items="${blogrolls}" var="blogroll">
		<a style="margin-right:20px;" href="${blogroll.url}" target="_blank">${blogroll.name}</a>
	</c:forEach>
</p>

<table class="helpTable">
	<tr>
		<td><img src="image/home/icon_sm1.png"></td>
		<td><h4>新手指南</h4></td>
		<td><img src="image/home/icon_sm2.png"></td>
		<td><h4>认证优势</h4></td>
		<td><img src="image/home/icon_sm3.png"></td>
		<td><h4>服务中心</h4></td>
		<td><img src="image/home/icon_sm4.png"></td>
		<td><h4>关注我们</h4></td>
	</tr>
	<tr>
		<td><div></div></td>
		<td><p>发单指南</p><p>接单宝典</p></td>
		<td><div></div></td>
		<td><p>个人实名</p><p>企业资质认证</p></td>
		<td><div></div></td>
		<td><p>广告服务</p><p>认证服务</p></td>
		<td><div></div></td>
		<td><p><img style="width:48px;" src="image/home/wechat.png"><img style="width:48px;" src="image/home/qq.png"><img style="width:90px;" src="image/home/qrCode.png"></p></td>
	</tr>
</table>
<%@ include file="/JSP/main/bottom.jsp"%>
</div>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/advanced-news-ticker/jquery.newsTicker.min.js"></script>
<script src="plugin/superSlide/jquery.SuperSlide.js"></script>
<script src="JS/main/home.js"></script>
</body>
</html>