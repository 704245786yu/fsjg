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
<title>服饰加工网-快产专家</title>
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
<input type="hidden" name="pageName" value="contractor">
<%@ include file="/JSP/main/common/commonData.jsp" %>
<span style="display:none;" id="tradeAndCostumeMap">${applicationScope.tradeAndCostumeMap}</span>
<span style="display:none;" id="adPositions">${adPositions}</span>

<table style="width:1190px;margin:0 auto;bakcground-color:#FBF8F9;">
<tr>
	<td style="width:906px;background-color:#FBF8F9;vertical-align:top;">
		<!-- 广告 -->
		<a class="ad" target="_blank"><img style="width:100%;height:260px;margin-bottom:10px;"></a>
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
						<td><b>加工年限：</b></td>
						<td id="processYear">
							<a class="label label-info" href="0" onclick="return aClick(this)">全部</a>
							<a href="1" onclick="return aClick(this)">1-5年</a>
							<a href="2" onclick="return aClick(this)">5-10年</a>
							<a href="3" onclick="return aClick(this)">11-15年</a>
							<a href="4" onclick="return aClick(this)">16-20年</a>
							<a href="5" onclick="return aClick(this)">20年以上</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<!-- 结果排序 -->
		<!-- <input name="sortMark" type="hidden">
		<table id="tableSort">
			<tr>
				<td><a href="0">最新发布<span class="glyphicon glyphicon-arrow-down"></span></a></td>
				<td><a href="1">有效日期<span class="glyphicon glyphicon-arrow-down"></span></a></td>
				<td><a href="2">订单数量<span class="glyphicon glyphicon-arrow-down"></span></a></td>
				<td><a href="3">预计交货日期<span class="glyphicon glyphicon-arrow-down"></span></a></td>
				<td><input type="checkbox" onclick="query()"> 只看急单</td>
			</tr>
		</table> -->
		<table id="contractorTable" class="table" style="font-size:18px;margin-bottom:0px;background-color:white;">
			<tr>
				<th>姓名</th>
				<th>年龄</th>
				<th>加工年限</th>
				<th>员工人数</th>
				<th>地址</th>
				<th>主营产品</th>
				<th>专业技能</th>
				<th>发布日期</th>
			</tr>
		</table>
		<div id="bsPagination" style="text-align:center"></div>
	</td>
	
	<!-- 右边栏 -->
	<td style="width:274px;vertical-align:top;padding-left:10px;background-color:#FBF8F9">
		<!-- 广告 -->
		<a class="ad" target="_blank"><img width="100%" style="margin-bottom:10px;"></a>
		<a class="ad" target="_blank"><img width="100%" style="margin-bottom:10px;"></a>
		
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
		
		<div name="sample" style="display:none;">
			<li class="list-group-item">
				<a target="_blank"><img width="100%"></a>
			</li>
		</div>
	</td>
</tr>
</table>

<%@ include file="/JSP/main/bottom.jsp"%>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bs_pagination/jquery.bs_pagination.min.js"></script>
<script src="plugin/bs_pagination/localization/en.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="JS/main/common/districtCascade.js"></script>
<script src="JS/main/contractorList.js"></script>
</body>
</html>

