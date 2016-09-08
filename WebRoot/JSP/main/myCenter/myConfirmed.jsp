<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<base href="<%=basePath%>">
<title>中国服务加工网-个人中心-我确认的订单</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="plugin/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
<style type="text/css">
	.query-cond td{
		padding-right:10px;
	}
</style>

<script src="plugin/jquery.min.js"></script>
</head>

<body>
<!-- 查询条件 -->
<table class="query-cond" style="margin:10px;">
	<tr>
		<td>
			<label>订单编号：</label>
		</td>
		<td>
			<input type="text" class="form-control" name="indentNum">
		</td>
		<td>
			<label>订单名称：</label>
		</td>
		<td>
			<input type="text" class="form-control" name="indentName" style="width:280px;">
		</td>
	</tr>
	<tr style="line-height: 60px;">
		<td>
			<label>发布日期：</label>
		</td>
		<td colspan="2">
			<input type="text" class="form-control" style="width:270px;" name="daterange">
		</td>
		<td>
			<button type="button" class="btn btn-primary" onclick="search()">查询</button>
		</td>
	</tr>
</table>

<!-- 数据表格 -->
<div style="padding:0px 10px;">
	<table id="dg" data-toggle="table" data-unique-id="indentNum"
			data-pagination="true"
			data-side-pagination="server"
			data-query-params="queryParams"
			data-page-size="30"
			data-page-list="[30,50]">
	    <thead>
	        <tr>
	            <th data-field="indentNum" data-align="center">订单编号</th>
	            <th data-field="indentName" data-align="center">订单名称</th>
	            <th data-field="quantity" data-align="center">订单数量</th>
	            <th data-field="expectPrice" data-align="center">订单金额(元)</th>
	            <th data-field="countNum" data-align="center">报价人数</th>
	            <th data-field="latestTime" data-align="center">报价日期</th>
	            <th data-formatter="operFormatter" data-align="center">操作</th>
	        </tr>
	    </thead>
	</table>
</div>

<div class="modal fade" id="formModal" data-backdrop="false">
	<div class="modal-dialog" style="width:800px">
		<div class="modal-content">
			<div class="modal-header" style="background-color:#208EEA;color:white;">
				<button type="button" class="close" data-dismiss="modal"><span >&times;</span></button>
				<h4 class="modal-title">确认订单</h4>
			</div>
			<div class="modal-body">
				<table id="dg2" data-toggle="table" data-unique-id="id" class="table-no-bordered">
				    <thead>
				        <tr>
				            <th data-formatter="radioFormatter" data-align="right" style="width:30px;"></th>
				            <th data-field="enterpriseName" data-align="center">工厂名称</th>
				            <th data-field="linkman" data-align="center">联系人</th>
				            <th data-field="telephone" data-align="center">手机号码</th>
				            <th data-field="quote" data-align="center">报价金额(元)</th>
				            <th data-field="quoteDate" data-align="center">报价日期</th>
				            <th data-field="staffNumber" data-align="center">员工人数</th>
				        </tr>
				    </thead>
				</table>
				<form id="confirmFrom" style="margin-top:10px;" action="indent/confirm">
					<label>最终成交价格（元）：</label>
					<input type="text" class="form-control" name="price" style="width:200px;display:inline;">
					<input type="hidden" name="indentNum">
					<input type="hidden" name="enterpriseId">
					<input type="hidden" name="quote">
					<span id="errorMsg" style="color:red"></span>
					<div class="alert alert-info" style="margin-top:5px;margin-bottom:5px;display:none;">
						确认订单成功，可以到<strong>我确认的订单</strong>页面查看。弹窗将在3秒后关闭。
					</div>
					<div class="row">
						<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
							<button type="submit" name="save" class="btn btn-primary">确认订单</button>
						</div>
					</div>
				</form>
			</div><!-- modal-body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal -->

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-daterangepicker/moment.min.js"></script>
<script src="plugin/bootstrap-daterangepicker/daterangepicker.js"></script>

<script src="plugin/jquery.mask.min.js"></script>

<script src="JS/main/myCenter/myConfirmed.js"></script>
</body>
</html>