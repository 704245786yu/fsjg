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
	<title>友情链接</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
</head>
<body>
<div class="panel panel-primary">
	<div class="panel-heading">
		友情链接
	</div>
	<div class="panel-body">
	<!-- 搜索框、新增按钮 -->
	<div id="toolbar">
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#formModal" onclick="add()">
			<span class="glyphicon glyphicon-plus"></span> 添加
		</button>
	</div>
	    
	<!-- 数据表格 -->
	<table id="dg" data-toggle="table" data-url="blogroll/getAll" data-unique-id="id" data-toolbar="#toolbar"
		data-search="true" data-search-on-enter-key="true" data-search-align="left"
		data-toolbar-align="right"
		data-pagination="true"
		data-page-size="20"
		data-page-list="[10,20]">
	    <thead>
	        <tr>
	            <th data-field="name" data-align="center">链接名称</th>
	            <th data-field="url" data-align="center">链接地址</th>
	            <th data-formatter="operFormatter" class="col-sm-1" data-align="center">操作</th>
	        </tr>
	    </thead>
	</table>
  </div><!-- panel-body -->
</div><!-- panel -->
	
<!-- 添加/更新模态框 -->
<div class="modal fade" id="formModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="dialog" style="width:500px">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
				<h4 class="modal-title" id="formModalLabel">编辑友情链接</h4>
			</div>
			<div class="modal-body">
				<form id="ff" method="post" class="form-horizontal" action="constantType/save">
					<input type="hidden" name="id"/>
					<div class="form-group">
						<label class="col-sm-3 control-label">链接名称</label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="name">
						</div>
					</div>
					<div class="form-group">
						<label for="constantTypeCode" class="col-sm-3 control-label">链接地址 </label>
						<div class="col-sm-9">
							<input type="text" class="form-control" name="url">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
							<button type="submit" name="save" class="btn btn-primary">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</div>
				</form>
			</div><!-- modal-body -->
		</div><!-- modal-content -->
	</div><!-- modal-dialog -->
</div><!-- modal -->
</body>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="JS/backstage/ad/blogroll.js"></script>
</html>
