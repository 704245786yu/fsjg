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
			<input type="text" class="form-control" style="width:270px;" id="daterange" name="daterange">
		</td>
		<td>
			<button type="button" class="btn btn-primary">查询</button>
		</td>
	</tr>
</table>

<!-- 数据表格 -->
<table id="dg" data-toggle="table" data-url="" data-unique-id="id"
		data-pagination="true"
		data-side-pagination="server"
		data-query-params="getQueryParams"
		data-page-size="10"
		data-page-list="[30,50]">
    <thead>
        <tr>
        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">序号</th>
            <th data-field="userName" data-align="center">用户名</th>
            <th data-field="enterpriseNumber" data-align="center">工厂编号</th>
            <th data-field="enterpriseName" data-align="center">工厂名称</th>
            <th data-field="auditState" data-align="center" data-formatter="auditStateFormatter">审核状态</th>
            <th data-field="processType" data-align="center" data-formatter="processTypeFormatter">加工类型</th>
            <th data-field="basicUser.createTime" data-align="center">注册时间</th>
            <th data-formatter="operFormatterVUD" class="col-sm-2" data-align="center">操作</th>
        </tr>
    </thead>
</table>

<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-daterangepicker/moment.min.js"></script>
<script src="plugin/bootstrap-daterangepicker/daterangepicker.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/main/myCenter/myReleased.js"></script>
