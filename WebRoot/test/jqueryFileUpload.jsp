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
	<link href="plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<!-- 样式表文件引用 -->
	<link href="plugin/jQuery-File-Upload/css/style.css" rel="stylesheet">
	<!-- 文件上传控件样式文件引用 -->
	<link href="plugin/jQuery-File-Upload/css/jquery.fileupload.css" rel="stylesheet">
  </head>
  
<body>
	<!-- The fileinput-button span is used to style the file input field as button -->
	<!--<span class="btn btn-success fileinput-button">
		<i class="glyphicon glyphicon-plus"></i>
		<span>请选择图片文件...</span>
		<!-- The file input field used as target for the file upload widget -->
		<!--<input id="fileupload" type="file" name="file" data-url="person/uploadExcel">
	</span>
	<br> -->
	<span class="btn btn-success fileinput-button">
		<i class="glyphicon glyphicon-plus"></i>
		<span>请选择省市信息EXCEL文件...</span>
		<!-- The file input field used as target for the file upload widget -->
		<input id="fileupload" type="file" accept="application/vnd.ms-excel" name="file" data-url="district/uploadExcel">
	</span>
	<br>
	<!-- 进度条 -->
	<div id="progress" class="progress">
		<div class="progress-bar progress-bar-success"></div>
	</div>
	<!-- 已上传图片文件列表 -->
	<div id="files" class="files"></div>
	
	<div class="well">
  <div id="datetimepicker1" class="input-append date">
    <input data-format="dd/MM/yyyy hh:mm:ss" type="text"></input>
    <span class="add-on">
      <i data-time-icon="icon-time" data-date-icon="icon-calendar">
      </i>
    </span>
  </div>
</div>

</body>
	<script src="plugin/jquery.min.js"></script>
	<script src="plugin/bootstrap/js/bootstrap.min.js"></script>
	<script src="plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
	<!-- 添加Jquery框架对iframe元素上传的支持 -->
	<script src="plugin/jQuery-File-Upload/js/jquery.iframe-transport.js"></script>
	<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>
	<script src="test/jqueryFileUpload.js"></script>
	
	<script type="text/javascript">
  $(function() {
    $('#datetimepicker1').datetimepicker({
      language: 'pt-BR'
    });
  });
</script>
</html>
