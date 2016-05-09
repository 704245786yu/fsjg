<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>jQuery File Upload Example</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
	<link href="plugin/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- 样式表文件引用 -->
	<link href="plugin/jQuery-File-Upload/css/style.css" rel="stylesheet">
	<!-- 文件上传控件样式文件引用 -->
	<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">
  </head>
  
<body>
	<!-- The fileinput-button span is used to style the file input field as button -->
	<span class="btn btn-success fileinput-button">
		<i class="glyphicon glyphicon-plus"></i>
		<span>请选择图片文件...</span>
		<!-- The file input field used as target for the file upload widget -->
		<input id="fileupload" type="file" name="files[]" data-url="server/php/" multiple>
	</span>
	<br>
	<!-- 进度条 -->
	<div id="progress" class="progress">
		<div class="progress-bar progress-bar-success"></div>
	</div>
	<!-- 已上传图片文件列表 -->
	<div id="files" class="files"></div>
</body>
	<script src="plugin/jquery.min.js"></script>
	<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
	<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
	<!-- 添加Jquery框架对iframe元素上传的支持 -->
	<script src="plugin/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
	<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>
	<script src="test/jqueryFileUpload.js"></script>
</html>
