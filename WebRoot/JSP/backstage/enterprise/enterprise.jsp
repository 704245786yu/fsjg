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
    <title>工厂信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">
	<link href="plugin/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">
</head>
  
<body>
<div id="listPanel" class="panel panel-primary">
	<div class="panel-heading">工厂信息管理</div>
	<div class="panel-body">
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
	    
		<!-- 新增、批量导入按钮 -->
	    <div class="row" style="padding-bottom:10px;">
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
				<span class="btn btn-success fileinput-button">
					<i class="glyphicon glyphicon-upload"></i>
					<span>批量导入</span>
					<input id="fileupload" type="file" name="file" accept="application/vnd.ms-excel" data-url="enterprise/uploadExcel">
				</span>
			</div>
		</div>
		
		<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="enterprise/getAllByPage" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="getQueryParams"
				data-page-size="10"
				data-page-list="[10,20]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" class="col-xs-1" data-align="center">序号</th>
		            <th data-field="userName" data-align="center">用户名</th>
		            <th data-field="enterpriseNumber" data-align="center">工厂编号</th>
		            <th data-field="enterpriseName" data-align="center">工厂名称</th>
		            <th data-field="auditState" data-align="center">审核状态</th>
		            <th data-field="processType" data-align="center">加工类型</th>
		            <th data-field="createTime" data-align="center">注册时间</th>
		            <th data-formatter="operFormatterVUD" class="col-sm-2" data-align="center">操作</th>
		        </tr>
		    </thead>
		</table>
	</div><!-- panel-body -->
</div><!-- panel -->		

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>

<script src="plugin/bootstrap-daterangepicker/moment.min.js"></script>
<script src="plugin/bootstrap-daterangepicker/daterangepicker.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/backstage/enterprise/enterprise.js"></script>

<%@ include file="editEnterprise.jsp" %>
</body>
</html>
