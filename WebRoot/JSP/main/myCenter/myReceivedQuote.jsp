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
<title>中国服务加工网-个人中心</title>
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
	<table id="dg" data-toggle="table" data-unique-id="id"
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

<!-- 添加/更新模态框 -->
<div class="modal fade" id="formModal">
	<div class="modal-dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title" id="formModalLabel">确认订单</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" name="indentId">
				<table id="Dg2" data-toggle="table" data-unique-id="id" data-url="">
				    <thead>
				        <tr>
				            <th data-field="indentNum" data-align="center">工厂名称</th>
				            <th data-field="indentName" data-align="center">联系人</th>
				            <th data-field="quantity" data-align="center">手机号码</th>
				            <th data-field="expectPrice" data-align="center">报价金额(元)</th>
				            <th data-field="countNum" data-align="center">员工人数</th>
				            <th data-field="latestTime" data-align="center">报价日期</th>
				            <th data-formatter="operFormatter" data-align="center">操作</th>
				        </tr>
				    </thead>
				</table>
				最终成交价格（元）：<input type="text" class="form-control" name="quote">
				<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
					<button type="submit" name="save" class="btn btn-primary">确认订单</button>
				</div>
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

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/main/myCenter/myReceivedQuote.js"></script>
</body>
</html>