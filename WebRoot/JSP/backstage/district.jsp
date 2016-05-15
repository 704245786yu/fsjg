<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>省市街道信息管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="plugin/bootstrapValidator/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="plugin/jquery-confirm/jquery-confirm.min.css" rel="stylesheet">
	<link href="plugin/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
	<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">
</head>

<body>
	<div class="panel panel-primary">
		<div class="panel-heading">省市街道信息管理</div>
		<div class="panel-body">
			<span class="btn btn-success fileinput-button"> <i
				class="glyphicon glyphicon-plus"></i> <span>请选择省市信息EXCEL文件...</span>
				<!-- The file input field used as target for the file upload widget -->
				<input id="fileupload" type="file" accept="application/vnd.ms-excel"
				name="file" data-url="district/uploadExcel">
			</span> <br>
			<!-- 进度条 -->
			<div id="progress" class="progress">
				<div class="progress-bar progress-bar-success"></div>
			</div>
			<!-- 已上传图片文件列表 -->
			<div id="files" class="files"></div>
		</div>
	</div>
</body>
<script src="plugin/jquery.min.js"></script>
<script src="plugin/bootstgit ap/js/bootstrap.min.js"></script>
<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<!-- 添加Jquery框架对iframe元素上传的支持 -->
<script src="plugin/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<script src="JS/backstage/district.js"></script>
</html>