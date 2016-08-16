<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<!-- Force latest IE rendering engine or ChromeFrame if installed -->
<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>jQuery File Upload Demo - Basic Plus version</title>
<meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support, progress bar, validation and preview images, audio and video for jQuery. Supports cross-domain, chunked and resumable file uploads. Works with any server-side platform (Google App Engine, PHP, Python, Ruby on Rails, Java, etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap styles -->
<link rel="stylesheet" href="plugin/bootstrap/css/bootstrap.min.css">
<!-- Generic page styles -->
<link rel="stylesheet" href="css/style.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="plugin/jQuery-File-Upload/css/jquery.fileupload.css">
</head>
<body>
<div class="container">
    <h2 class="lead">Basic Plus version</h2>
    <br>
    <!-- The fileinput-button span is used to style the file input field as button -->
    <form method="post" action="indent/release" enctype="multipart/form-data">
		<span class="btn btn-success fileinput-button">
			<i class="glyphicon glyphicon-plus"></i>
			<span>Add files...</span>
	    	<input id="fileupload" type="file" name="files"/>
	    </span>
    <button type="submit">提交</button>
    </form>
    <div id="files" class="files"></div>
</div>
<script src="plugin/jquery.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="//blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<script src="plugin/jQuery-File-Upload/js/vendor/jquery.ui.widget.js"></script>
<!-- The basic File Upload plugin -->
<script src="plugin/jQuery-File-Upload/js/jquery.fileupload.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="js/jquery.fileupload-image.js"></script>
<!-- The File Upload validation plugin -->
<script src="js/jquery.fileupload-validate.js"></script>
<script>
$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',
        autoUpload: false,
        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
        maxFileSize: 999000,
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        previewCrop: true
    }).on('fileuploadadd', function (e, data) {
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            var node = $('<p/>')
                    .append($('<span/>').text(file.name));
            node.appendTo(data.context);
        });
    });
});
</script>
</body>
</html>
