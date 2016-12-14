<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=basePath%>">
	<title>广告位</title>
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
<div id="tablePanel" class="panel panel-primary">
	<div class="panel-heading">
		广告管理
	</div>
	<div class="panel-body">
	    
	<!-- 数据表格 -->
	<table id="dg" data-toggle="table" data-url="adPosition/getAll" data-cache="false" data-unique-id="id"
		data-search="true" data-search-on-enter-key="true" data-search-align="left"
		data-pagination="true"
		data-page-size="20"
		data-page-list="[10,20]">
	    <thead>
	        <tr>
				<th data-align="center" data-formatter="seqnumFormatter">序号</th>
	            <th data-field="name" data-align="center">广告名称</th>
	            <th data-field="code" data-align="center">广告编码</th>
	            <th data-field="size" data-align="center">尺寸</th>
	            <th data-field="sort" data-align="center">排序号</th>
	            <th data-field="title" data-align="center">广告标题</th>
	            <th data-field="img" data-align="center" data-formatter="imgFmt">广告图片</th>
	            <th data-field="linkType" data-align="center" data-formatter="linkTypeFmt">链接类型</th>
	            <th data-field="link" data-align="center">链接地址</th>
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
		<form id="ff" method="post" class="form-horizontal" enctype="multipart/form-data">
			<input type="hidden" name="id"/>
			<div class="form-group">
				<label class="col-sm-1 control-label">广告名称</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="name" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">广告编码</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="code" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">尺寸</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="size">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">排序号</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="sort">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">广告标题</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="title">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">广告图片</label>
				<div class="col-sm-3">
					<input type="hidden" name="img">
					<input type="file" name="imgFile">
					<img style="width:200px;height:50px;">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">链接类型</label>
				<div class="col-sm-3">
					<input type="radio" name="linkType" value="0"> 外部网址
					<input type="radio" name="linkType" value="1"> 工厂详情页
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-1 control-label">链接</label>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="link">
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

<script src="JS/util/bsFormTableExtend.js"></script>
<script src="JS/util/jqConfirmExtend.js"></script>
<script src="plugin/jquery.form.min.js"></script>
<script src="JS/backstage/ad/adPosition.js"></script>
</html>
