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
	<title>公告</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
</head>
<body>
<div id="tablePanel" class="panel panel-primary">
	<div class="panel-heading">
		公告
	</div>
	<div class="panel-body">
		<!-- 查询条件 -->
		<div class="form-inline" style="padding-bottom:10px;">
			<div class="form-group">
				<label for="workerAmount">公告标题</label>
				<input type="text" class="form-control" name="title">
			</div>
			<div class="form-group">
				<label for="startDate">发布日期</label>
				<div class="input-group date">
	                <input type="text" class="form-control" name="beginDate"/>
	                <span class="input-group-addon">
	                	<span class="glyphicon glyphicon-calendar"></span>
	                </span>
	            </div>
				至
				<div class="input-group date">
	                <input type="text" class="form-control" name="endDate"/>
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
				<button type="button" class="btn btn-primary" onclick="add()">
					<span class="glyphicon glyphicon-plus"></span> 添加
				</button>
			</div>
		</div>	    
	<!-- 数据表格 -->
	<table id="dg" data-toggle="table" data-url="affiche/findByParam" data-unique-id="id" data-query-params="queryParams">
	    <thead>
	        <tr>
	            <th data-field="title" data-align="center">公告标题</th>
	            <th data-field="realName" data-align="center">发布人</th>
	            <th data-field="updateTime" data-align="center">发布时间</th>
	            <th data-formatter="operFormatter" class="col-sm-1" data-align="center">操作</th>
	        </tr>
	    </thead>
	</table>
  </div><!-- panel-body -->
</div><!-- panel -->
	
<!-- 添加/更新面板 -->
<div id="editPanel" class="panel panel-primary" style="display:none;">
	<button type="button" class="btn btn-primary" onclick="cancelEdit()" style="width:100px;"><span class="glyphicon glyphicon-step-backward"></span>返回</button>
	<div class="panel-body">
		<form id="ff" method="post" class="form-horizontal" action="constantType/save">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label class="col-sm-1 control-label">公告标题</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" name="title">
				</div>
			</div>
			<div class="form-group">
				<label for="constantTypeCode" class="col-sm-1 control-label">公告详情 </label>
				<div class="col-sm-9">
					<script id="editor" name="content" type="text/plain" style="height:500px;"></script>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-4 col-sm-offset-8" style="text-align:right">
					<button type="submit" name="save" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-default" onclick="cancelEdit()">取消</button>
				</div>
			</div>
		</form>
	</div><!-- panel-body -->
</div><!-- panel -->
</body>

<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
<script src="plugin/bootstrapValidator/js/bootstrapValidator.min.js"></script>
<script src="plugin/jquery.formautofill.min.js"></script>
<script src="plugin/jquery-confirm/jquery-confirm.min.js"></script>
<script src="plugin/bootstrap-table/bootstrap-table.min.js"></script>
<script src="plugin/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="plugin/bootstrap-datetimepicker/js/moment-with-locales.js"></script>
<script src="plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>

<script src="plugin/UEditor/ueditor.config.js"></script>
<script src="plugin/UEditor/ueditor.all.js"></script>
<script src="plugin/UEditor/lang/zh-cn/zh-cn.js"></script>

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="plugin/jquery.form.min.js"></script>
<script src="JS/backstage/ad/affiche.js"></script>
</html>
