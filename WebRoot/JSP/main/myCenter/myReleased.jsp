<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="plugin/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

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
		<td>
			<label>订单状态：</label>
		</td>
		<td>
			<select class="form-control" name="state">
				<option></option>
				<option value="0">未收到报价</option>
				<option value="1">已收到报价</option>
				<option value="2">已接单</option>
				<option value="3">已失效</option>
			</select>
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
			data-query-params="getQueryParams"
			data-page-size="30"
			data-page-list="[30,50]">
	    <thead>
	        <tr>
	            <th data-field="indentNum" data-align="center">订单编号</th>
	            <th data-field="indentName" data-align="center">订单名称</th>
	            <th data-field="quantity" data-align="center">订单数量</th>
	            <th data-field="expectPrice" data-align="center">订单金额(元)</th>
	            <th data-field="state" data-align="center" data-formatter="stateFormatter">订单状态</th>
	            <th data-field="createTime" data-align="center">发布日期</th>
	        </tr>
	    </thead>
	</table>
</div>

<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-daterangepicker/moment.min.js"></script>
<script src="plugin/bootstrap-daterangepicker/daterangepicker.js"></script>

<script src="plugin/jquery.mask.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/main/myCenter/myReleased.js"></script>
