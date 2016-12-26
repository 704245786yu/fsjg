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
<title>服饰加工网-加工订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="CSS/common/default.css" rel="stylesheet">
<link href="CSS/indent-main.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="indent">
<%@ include file="/JSP/main/common/commonData.jsp" %>
<span style="display:none;" id="adPositions">${adPositions}</span>

<div style="width:1190px; margin:0 auto;">
	<!-- 左边栏(主体) -->
	<div style="width:900px;float:left;">
		<!-- 订单搜索 -->
		<div style="width:370px;float:left;">
			<div class="panel panel-default" style="height:260px">
				<div class="panel-heading">订单搜索</div>
				<div class="panel-body">
					<form class="form-horizontal" action="indent/search" method="post">
						<table>
							<tr>
								<td style="width:65px"><label>产品类别</label></td>
								<td>
									<jsp:include page="/JSP/main/common/costumeCategoryModal.jsp">
										<jsp:param name="limitChkNum" value="5"/>
									</jsp:include>
								</td>
							</tr>
							<tr>
								<td><label>接单地区</label></td>
								<td><button id="districtBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#districtModal">选择接单地区</button></td>
							</tr>
							<tr>
								<td><label>关 键 字</label></td>
								<td><input type="text" name="indentKeyword" class="form-control" placeholder="请输入关键字"></td>
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

		<div style="width:520px;float:right;">
			<div class="panel panel-default panel-tip1" style="height:260px">
				<div class="panel-heading">
					 <h3 class="panel-title cus-panel-title">如何得到高质量的订单?</h3>
				</div>
				<div class="panel-body">
					<h4>1、完善企业信息，展示实力</h4>
					<p>尽可能多地上传企业证书和产品图片及时更新生产加工档期，表明接单状态进行实名认证，获得靠前排名</p>
					<h4>2、找订单、申请报价</h4>
					<p>找和本厂生产能力匹配的订单申请提交申请前，确认符合发单方的要求并填写申请理由，表达接单诚意</p>
					<h4>3、耐心与客户沟通</h4>
					<p>客户主动找到你，并发送订单报价申请报价被客户接受后请及时回复</p>
				</div>
			</div>
		</div>
		
		<!-- 广告位 -->
		<a class="ad" target="_blank"><img style="width:100%;height:90px;"></a>
		
		<!-- 个人发布的订单 -->
		<div class="panel panel-default" style="margin-top:20px;">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-user"></span> 个人发布的订单
				 	<div class="pull-right">
				 		<a href="indent/search"><span class="label label-info" style="font-size:14px;">更多</span></a>
					</div>
				 </h3>
			</div>
			<div class="panel-body">
				<table id="personIndent" class="table" style="border-collapse:separate;border-spacing:0px 5px;">
					<tr>
						<th>产品类别</th> <th style="padding-left:80px;">订单名称</th> <th>订单类型</th> <th>订单数量</th> <th>发单地区</th> <th>发布日期</th>
					</tr>
					<c:forEach items="${personIndents}" var="indent">
						<tr style="border-bottom:5px;">
							<td style="background-color:#fdf4ea;padding:0px;">
								<div style="width:10px;height:34px;float:left;background-color:#f7b961;margin-right:10px;">&nbsp;</div>
								<div style="margin-top:8px;" name="costumeCodes">${indent.costumeCode}</div>
							</td>
							<td style="padding-left:50px;">
								<input type="hidden" name="isUrgency" value="${indent.isUrgency}">
								<span name="isUrgency" style="background-color:red;color:white;padding:3px;visibility:hidden;">急</span>
								<a target="_blank" href="indent/detail/${indent.indentNum}">${indent.indentName}</a>
							</td>
							<td name="processType">${indent.processType}</td>
							<td>${indent.quantity}件</td>
							<td name="district">${districtCodeNameMap[indent.province]},${districtCodeNameMap[indent.city]}</td>
							<td><fmt:formatDate value="${indent.createTime}"/></td>
						</tr>
					</c:forEach>
				</table>
			</div><!-- panel-body -->
		</div>
		<!-- 工厂外放的订单 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-globe"></span> 工厂外放的订单
				 	<div class="pull-right">
				 		<a href="indent/search"><span class="label label-info" style="font-size:14px;">更多</span></a>
					</div>
				 </h3>
			</div>
			<div class="panel-body">
				<table id="enterpriseIndent" class="table" style="border-collapse:separate;border-spacing:0px 5px;">
					<tr>
						<th>产品类别</th> <th style="padding-left:80px;">订单名称</th> <th>订单类型</th> <th>订单数量</th> <th>发单地区</th> <th>发布日期</th>
					</tr>
					<c:forEach items="${enterpriseIndents}" var="indent">
						<tr style="border-bottom:5px;">
							<td style="background-color:#f0f3e5;padding:0px;">
								<div style="width:10px;height:34px;float:left;background-color:#99ac4d;margin-right:10px;">&nbsp;</div>
								<div style="margin-top:8px;" name="costumeCodes">${indent.costumeCode}</div>
							</td>
							<td style="padding-left:50px;">
								<input type="hidden" name="isUrgency" value="${indent.isUrgency}">
								<span name="isUrgency" style="background-color:red;color:white;padding:3px;visibility:hidden;">急</span>
								<a target="_blank" href="indent/detail/${indent.indentNum}">${indent.indentName}</a>
							</td>
							<td name="processType">${indent.processType}</td>
							<td>${indent.quantity}件</td>
							<td name="district">${districtCodeNameMap[indent.province]},${districtCodeNameMap[indent.city]}</td>
							<td><fmt:formatDate value="${indent.createTime}"/></td>
						</tr>
					</c:forEach>
				</table>
			</div><!-- panel-body -->
		</div><!-- panel -->
		
		<!-- 按地区找订单 -->
		<div class="hotAreaDiv" class="row" style="margin-top:20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-map-marker" style="color:#0096E9;"></span> 按地区找订单</h3>
				</div>
				<div class="panel-body">
					<table>
						<tr>
							<td><a href="${districtNameCodeMap["浙江省"]}" title="province"><img src="image/enterprise/zhejiang.png"></a></td>
							<td>
								<a href="${districtNameCodeMap["宁波市"]}">宁波</a><span>|</span> <a href="${districtNameCodeMap["杭州市"]}">杭州</a><span>|</span> <a href="${districtNameCodeMap["金华市"]}">金华</a><span>|</span> <a href="${districtNameCodeMap["绍兴市"]}">绍兴</a><br/>
								<a href="${districtNameCodeMap["嘉兴市"]}">嘉兴</a><span>|</span> <a href="${districtNameCodeMap["温州市"]}">温州</a><span>|</span> <a href="${districtNameCodeMap["湖州市"]}">湖州</a><span>|</span> <a href="${districtNameCodeMap["台州市"]}">台州</a>
							</td>
							<td style="padding-left:100px;"><a href="${districtNameCodeMap["江苏省"]}" title="province"><img src="image/enterprise/jiangsu.png"></a></td>
							<td>
								<a href="${districtNameCodeMap["苏州市"]}">苏州</a><span>|</span> <a href="${districtNameCodeMap["南京市"]}">南京</a><span>|</span> <a href="${districtNameCodeMap["无锡市"]}">无锡</a><span>|</span> <a href="${districtNameCodeMap["泰州市"]}">泰州</a><br/>
								<a href="${districtNameCodeMap["南通市"]}">南通</a><span>|</span> <a href="${districtNameCodeMap["盐城市"]}">盐城</a><span>|</span> <a href="${districtNameCodeMap["镇江市"]}">镇江</a><span>|</span> <a href="${districtNameCodeMap["常州市"]}">常州</a>
							</td>
						</tr>
						<tr>
							<td><a href="${districtNameCodeMap["广东省"]}" title="province"><img src="image/enterprise/guangdong.png"></a></td>
							<td>
								<a href="${districtNameCodeMap["广州市"]}">广州</a><span>|</span> <a href="${districtNameCodeMap["深圳市"]}">深圳</a><span>|</span> <a href="${districtNameCodeMap["东莞市"]}">东莞</a><span>|</span> <a href="${districtNameCodeMap["佛山市"]}">佛山</a><br/>
								<a href="${districtNameCodeMap["中山市"]}">中山</a><span>|</span> <a href="${districtNameCodeMap["汕头市"]}">汕头</a><span>|</span> <a href="${districtNameCodeMap["惠州市"]}">惠州</a><span>|</span> <a href="${districtNameCodeMap["江门市"]}">江门</a>
							</td>
							<td style="padding-left:100px;"><a href="${districtNameCodeMap["福建省"]}" title="province"><img src="image/enterprise/fujian.png"></a></td>
							<td>
								<a href="${districtNameCodeMap["福州市"]}">福州</a><span>|</span> <a href="${districtNameCodeMap["厦门市"]}">厦门</a><span>|</span> <a href="${districtNameCodeMap["莆田市"]}">莆田</a><span>|</span> <a href="${districtNameCodeMap["三明市"]}">三明</a><br/>
								<a href="${districtNameCodeMap["泉州市"]}">泉州</a><span>|</span> <a href="${districtNameCodeMap["漳州市"]}">漳州</a><span>|</span> <a href="${districtNameCodeMap["南平市"]}">南平</a><span>|</span> <a href="${districtNameCodeMap["宁德市"]}">宁德</a>
							</td>
						</tr>
					</table>
					<table>
						<tr>
							<td><a href="${districtNameCodeMap["北京市"]}">北京</a></td> <td><a href="${districtNameCodeMap["上海市"]}">上海</a></td> <td><a href="${districtNameCodeMap["天津市"]}">天津</a></td> <td><a href="${districtNameCodeMap["山东省"]}">山东</a></td> <td><a href="${districtNameCodeMap["宁夏回族自治区"]}">宁夏</a></td>
							<td><a href="${districtNameCodeMap["辽宁省"]}">辽宁</a></td> <td><a href="${districtNameCodeMap["重庆市"]}">重庆</a></td> <td><a href="${districtNameCodeMap["云南省"]}">云南</a></td> <td><a href="${districtNameCodeMap["新疆维吾尔自治区"]}">新疆</a></td> <td><a href="${districtNameCodeMap["西藏自治区"]}">西藏</a></td>
						</tr>
						<tr>
							<td><a href="${districtNameCodeMap["四川省"]}">四川</a></td> <td><a href="${districtNameCodeMap["陕西省"]}">陕西</a></td> <td><a href="${districtNameCodeMap["山西省"]}">山西</a></td> <td><a href="${districtNameCodeMap["青海省"]}">青海</a></td> <td><a href="${districtNameCodeMap["内蒙古自治区"]}">内蒙古</a></td>
							<td><a href="${districtNameCodeMap["江西省"]}">江西</a></td> <td><a href="${districtNameCodeMap["吉林省"]}">吉林</a></td> <td><a href="${districtNameCodeMap["湖南省"]}">湖南</a></td> <td><a href="${districtNameCodeMap["湖北省"]}">湖北</a></td> <td><a href="${districtNameCodeMap["黑龙江省"]}">黑龙江</a></td>
						</tr>
						<tr>
							<td><a href="${districtNameCodeMap["河南省"]}">河南</a></td> <td><a href="${districtNameCodeMap["河北省"]}">河北</a></td> <td><a href="${districtNameCodeMap["海南省"]}">海南</a></td> <td><a href="${districtNameCodeMap["贵州省"]}">贵州</a></td> <td><a href="${districtNameCodeMap["广西省"]}">广西</a></td>
							<td><a href="${districtNameCodeMap["甘肃省"]}">甘肃</a></td> <td><a href="${districtNameCodeMap["安徽省"]}">安徽</a></td> <td><a href="${districtNameCodeMap["香港省"]}">香港</a></td> <td><a href="${districtNameCodeMap["澳门"]}">澳门</a></td> <td><a href="${districtNameCodeMap["台湾省"]}">台湾</a></td>
						</tr>
					</table>
				</div><!-- panel-body -->
			</div><!-- panel -->
		</div>
	</div><!-- 主体 -->
	
	<!-- 右边栏 -->
	<div style="width:280px;float:right;">
		<!-- 立即发布订单 -->
		<a href="indent/showRelease"><img style="margin-bottom:10px;" src="image/ad/orderForm/publish_order.png"/></a>
		<!-- 最新接到报价的订单 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 最新接到报价的订单</h3>
					</div>
					<div id="newstQuoteList" class="panel-body" style="padding:0px;">
						<c:forEach items="${newstQuotes}" var="newstQuote">
						<table style="width:100%;border-bottom:1px solid #DDDDDD;">
							<tr>
								<td style="text-align:center;width:100px;"><span style="color:red;font-size:26px;">${newstQuote.countNum}</span>人<br/>报价中</td>
								<td style="vertical-align:center;padding-top:10px;">
									<p><a href="indent/detail/${newstQuote.indentNum}">${newstQuote.indentName}</a></p>
									<p>
										<span name="processType">${newstQuote.processType}</span>
										<span style="margin-left:10px;">${newstQuote.quantity}件</span>
									</p>
								</td>
							</tr>
						</table>
						</c:forEach>
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
								<img class="media-object" src="image/enterprise-icon.png">
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
	</div><!-- 右边栏 -->
</div>
<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="JS/util/treeUtil.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/indent.js"></script>
</body>
</html>

