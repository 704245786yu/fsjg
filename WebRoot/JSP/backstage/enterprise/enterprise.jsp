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
<div id="listPanel" class="panel panel-primary">
	<div class="panel-heading">工厂信息管理</div>
	<div class="panel-body">
		<!-- 查询条件 -->
		<div class="form-inline" style="padding-bottom:10px;">
			<div class="form-group">
				<div class="form-group">
					<label>工厂名称</label>
					<input type="text" class="form-control" name="enterpriseName">
				</div>
				<div class="form-group">
					<label>认证状态</label>
					<select class="form-control" name="auditState">
						<option value="">全部</option>
						<option value="0">待审核</option>
						<option value="1">未通过</option>
						<option value="2">已通过</option>
					</select>
				</div>
				<div class="form-group">
					<label>注册类型</label>
					<select class="form-control" name="createType">
						<option value="">全部</option>
						<option value="0">前台注册</option>
						<option value="1">后台导入</option>
					</select>
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
	    
		<!-- 新增、批量导入按钮 -->
	    <div class="row" style="padding-bottom:10px;">
			<div class="col-sm-3">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
				<span class="btn btn-success fileinput-button" data-toggle="tooltip" data-placement="right" title="上传Excel文档">
					<i class="glyphicon glyphicon-upload"></i>
					<span>批量导入</span>
					<input id="fileupload" type="file" name="file" accept=".XLS,.xlsx" data-url="enterprise/uploadExcel">
				</span>
			</div>
		</div>
		
		<!-- 数据表格 -->
		<table id="dg" data-toggle="table" data-url="enterprise/findByPage" data-unique-id="id"
				data-pagination="true"
				data-side-pagination="server"
				data-query-params="queryParams"
				data-page-size="10"
				data-page-list="[10,20]">
		    <thead>
		        <tr>
		        	<th data-formatter="seqnumFormatter" data-align="center">序号</th>
		        	<th data-field="id" data-align="center">ID</th>
		            <th data-field="basicUser.userName" data-align="center">用户名</th>
		            <th data-field="number" data-align="center">工厂编号</th>
		            <th data-field="enterpriseName" data-align="center">工厂名称</th>
		            <th data-field="auditState" data-align="center" data-formatter="auditStateFormatter">审核状态</th>
		            <th data-field="processType" data-align="center" data-formatter="processTypeFormatter">加工类型</th>
		            <th data-field="basicUser.createBy" data-align="center" data-formatter="createByFormatter">注册类型</th>
		            <th data-field="basicUser.createTime" data-align="center">注册时间</th>
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
<script src="JS/backstage/enterprise/enterprise.js"></script>

<%@ include file="editEnterprise.jsp" %>
</body>
</html>
