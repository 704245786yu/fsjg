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
<title>中国服务加工网-加工订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bs_pagination/jquery.bs_pagination.min.css" rel="stylesheet">
<link href="CSS/indent-list.css" rel="stylesheet">

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<%@ include file="top.jsp" %>
<input type="hidden" name="pageName" value="indent">
<%@ include file="/JSP/main/common/commonData.jsp" %>
<span style="display:none;" id="tradeAndCostumeMap">${applicationScope.tradeAndCostumeMap}</span>

<table style="width:1190px;margin:0 auto;bakcground-color:#FBF8F9;">
<tr>
	<td style="width:906px;background-color:#FBF8F9;vertical-align:top;">
		<div class="panel panel-default search-panel">
			<div class="panel-body">
				<table>
					<tr>
						<td><b>产品类别：</b></td>
						<td style="width:806px;">
							<p id="costumeCategory" style="float:left;width:90%;height:75px;line-height:25px;overflow:hidden;overflow-x:hidden">
								<a class="label label-info" href="0" onclick="return aClick(this)">全部</a>
							</p>
							<a id="showMoreToggle" type="button" class="btn btn-default btn-xs" style="float:right;color:#337AB7;">更多<span class="glyphicon glyphicon-chevron-down"></span></a>
						</td>
					</tr>
					<tr>
						<td><b>所在地区：</b></td>
						<td>
							<button id="districtBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#districtModal">选择接单地区</button>
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
						<td><b>销售市场：</b></td>
						<td id="saleMarket">
							<a class="label label-info" href="0" onclick="return aClick(this)">全部</a>
							<a href="1" onclick="return aClick(this)">内销</a>
							<a href="2" onclick="return aClick(this)">外销</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 广告 -->
		<img src="image/ad/ad.png">
		
		<input id="totalRows" type="hidden" value="${result.total}">
		<table class="table" style="font-size:18px;">
			<tr>
				<th style="width:280px;">订单信息</th>
				<th style="width:120px;">订单类型</th>
				<th style="width:180px;">接单企业要求</th>
				<th style="width:120px;">发单企业</th>
				<th style="width:100px;">发布日期</th>
				<th style="width:100px;">有效日期</th>
			</tr>
		</table>
		<div id="indentDiv">
			<!-- 订单列表 -->
			<c:forEach var="indent" items="${result.rows}">
				<table class="table" style="background-color:white;">
					<tr>
						<td style="padding-right:0px;width:30px;vertical-align:top;">
							<c:if test="${indent.isUrgency == true}">
								<img src="image/indentRelease/urgency.png">
							</c:if>
						</td>
						<td style="width:250px;">
							<label style="font-size:16px"><a href="indent/detail/${indent.indentNum}" target="_blank" style="color:#4eb1e5;">${indent.indentName}</a></label>
							<div>预计订单数量：${indent.quantity}件</div>
							<div>预计交货日期：${indent.preDeliveryDate}</div>
							<div>销售市场：<span>${indent.saleMarket}</span></div>
						</td>
						<td style="width:120px;">
							<!-- 加工类型 -->
							<div>${indent.processType}</div>
							<div>${indent.indentType}</div>
						</td>
						<td style="width:180px">
							<div>${indent.condProvince},${indent.condCity}</div>
							<div>${indent.condDemand}</div>
						</td>
						<td style="width:120px">
							<input type="hidden" name="userType" value="${indent.userType}">
							<div>${indent.province},${indent.city}</div>
						</td>
						<td style="width:100px">
							<div>${indent.createTime}</div>
						</td>
						<td style="width:100px">
							<div>${indent.effectiveDate}</div>
						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<div style="display:none;">
			<table class="table template" style="background-color:white;">
				<tr>
					<td style="padding-right:0px;width:30px;vertical-align:top;">
						<img name="urgencyImg" src="image/indentRelease/urgency.png" style="display:none">
					</td>
					<td style="width:250px;">
						<label style="font-size:16px"><a name="title" href="indent/detail/" target="_blank" style="color:#4eb1e5;"></a></label>
						<div name="quantity">预计订单数量：件</div>
						<div>预计交货日期：</div>
						<div>销售市场：</div>
					</td>
					<td style="width:120px;">
						<!-- 加工类型 -->
						<div name="processType"></div>
						<!-- 订单类型 -->
						<div></div>
					</td>
					<td style="width:180px">
						<!-- 接单地区 -->
						<div name="condDistrict"></div>
						<!-- 接单要求 -->
						<div></div>
					</td>
					<td style="width:120px">
						<!-- 用户类型 -->
						<!-- 发单用户所在地 -->
						<div name="district"></div>
					</td>
					<td style="width:100px">
						<div name="createTime"></div>
					</td>
					<td style="width:100px">
						<!-- 发单日期 -->
						<div name="effectiveDate"></div>
					</td>
				</tr>
			</table>
		</div>
		<div id="bsPagination" style="text-align:center"></div>
	</td>
	
	<!-- 右边栏 -->
	<td style="width:274px;vertical-align:top;padding-left:10px;background-color:#FBF8F9">
	<!-- 行业分类 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				 <h3 class="panel-title cus-panel-title"><span class="glyphicon glyphicon-volume-up"></span> 行业分类</h3>
			</div>
			<div id="tradeDiv" class="panel-body" style="padding:0px;">
				<p>
					<a href="1"><img src="image/fuzhuang.png" align="left">服装</a>
				</p>
				<p>
					<a href="2"><img src="image/fushi.png" align="left">服饰</a>
				</p>
				<p>
					<a href="3"><img src="image/jiafang.png" align="left">家纺</a>
				</p>
				<p>
					<a href="4"><img src="image/mianliao.png" align="left">面料</a>
				</p>
			</div><!-- panel-body -->
		</div><!-- panel -->
		<ul class="list-group">
			<li class="list-group-item"><img width="100%" src="image/ad/guanggao.png"/></li>
			<li class="list-group-item"><img width="100%" src="image/ad/guanggao.png"/></li>
		</ul>
	</td>
</tr>
</table>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bs_pagination/jquery.bs_pagination.min.js"></script>
<script src="plugin/bs_pagination/localization/en.min.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/indentList.js"></script>
</body>
</html>

