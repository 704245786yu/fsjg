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
<link href="plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
		<td colspan="3">
			<table>
				<tr>
					<td>
						<div class="input-group date">
			                <input type="text" class="form-control" name="beginDate"/>
			                <span class="input-group-addon">
			                	<span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
					</td>
					<td>至</td>
					<td>
						<div class="input-group date">
			                <input type="text" class="form-control" name="endDate"/>
			                <span class="input-group-addon">
			                	<span class="glyphicon glyphicon-calendar"></span>
			                </span>
			            </div>
					</td>
				</tr>
			</table>
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
	            <th data-field="expectPrice" data-align="center">预期价(元)</th>
	            <th data-field="price" data-align="center">成交价(元)</th>
	            <th data-field="enterpriseName" data-align="center">工厂名称</th>
	            <th data-field="updateTime" data-align="center">接单日期</th>
	        </tr>
	    </thead>
	</table>
</div>

<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

<script src="plugin/jquery.mask.min.js"></script>

<script src="JS/main/myCenter/myConfirmed.js"></script>
</body>
</html>