<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>我发布的订单</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
</head>
  
<body>
	<!-- 查询条件 -->
	<div class="form-inline" style="padding-bottom:10px;">
		<div class="form-group">
			<label for="startDate">发布日期</label>
			<input type="text" class="form-control" style="width:270px;" id="daterange" name="daterange">
		</div>
		<div class="form-group">
			<label for="workerAmount">员工数量</label>
			<input type="text" class="form-control" name="workerAmount">
		</div>
		<div class="form-group">
			<label for="processYear">加工年限</label>
			<input type="text" class="form-control" name="processYear">
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-primary">查询</button>
			<button type="button" class="btn btn-primary">清空</button>
		</div>
    </div><!-- form-inline -->
	    
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

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-daterangepicker/moment.min.js"></script>
<script src="plugin/bootstrap-daterangepicker/daterangepicker.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/main/myCenter/myRealseIndent.js"></script>
</body>
</html>
