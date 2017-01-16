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
	<link href="plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
  
<body>
<div id="listPanel" class="panel">
	<div class="panel-body">
		<!-- 查询条件 -->
		<div class="form-inline" style="padding-bottom:10px;">
			<div class="form-group">
				<div class="form-group">
					<label>样品编号</label>
					<input type="text" class="form-control" name="num">
					<input type="hidden" id="enterpriseNum" value="${enterpriseNum}">
				</div>
				<div class="form-group">
					<label>样品名</label>
					<input type="text" class="form-control" name="name">
				</div>
				<label for="startDate">创建日期</label>
				<div class="input-group date">
	                <input type="text" class="form-control" name="beginDate" style="width:120px;"/>
	                <span class="input-group-addon">
	                	<span class="glyphicon glyphicon-calendar"></span>
	                </span>
	            </div>
				至
				<div class="input-group date">
	                <input type="text" class="form-control" name="endDate" style="width:120px;"/>
	                <span class="input-group-addon">
	                	<span class="glyphicon glyphicon-calendar"></span>
	                </span>
	            </div>
			</div>
			<div class="form-group">
				<button type="button" class="btn btn-primary" onclick="search()">查询</button>
			</div>
	    </div><!-- form-inline -->
	    
		<!-- 新增按钮 -->
	    <div class="row" style="padding-bottom:10px;">
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
			</div>
		</div>
		
		<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="costumeSample/findMySample" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="queryParams"
				data-page-size="10"
				data-page-list="[10,20]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" data-align="center">序号</th>
		            <th data-field="num" data-align="center">样品编号</th>
		            <th data-field="name" data-align="center">样品名</th>
		            <th data-field="costumeCate" data-align="center">服饰类型</th>
		            <th data-field="updateTime" data-align="center">添加时间</th>
		            <th data-formatter="operFormatter" class="col-sm-1" data-align="center">操作</th>
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

<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/main/myCenter/mySample.js"></script>

<%@ include file="editMySample.jsp" %>
</body>
</html>
