<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'demo1.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <!-- 加载编辑器的容器 -->
    <script id="editor" name="content" type="text/plain"></script>
    
    <button onclick="getTxt()">获取文字</button>
    <script type="text/javascript" src="plugin/jquery.min.js"></script>
    <script type="text/javascript" src="plugin/UEditor/ueditor.config.js"></script>
	<script type="text/javascript" src="plugin/UEditor/ueditor.all.js"></script>
	<script type="text/javascript" src="plugin/UEditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">
    var ue = UE.getEditor('editor', {
	    autoFloatEnabled: true,
	    toolbars: [
		    [
		        'undo', //撤销
		        'redo', //重做
		        'bold', //加粗
		        'italic', //斜体
		        'underline', //下划线
		        'subscript', //下标
		        'superscript', //上标
		        'formatmatch', //格式刷
		        'pasteplain', //纯文本粘贴模式
		        'preview', //预览
		        'horizontal', //分隔线
		        'removeformat', //清除格式
		        'unlink', //取消链接
		        'cleardoc', //清空文档
		        ],
		        [
		        'fontfamily', //字体
		        'fontsize', //字号
		        'paragraph', //段落格式
		        'link', //超链接
		        'searchreplace', //查询替换
		        'justifyleft', //居左对齐
		        'justifyright', //居右对齐
		        'justifycenter', //居中对齐
		        'justifyjustify', //两端对齐
		        'forecolor', //字体颜色
		        'backcolor', //背景色
		        'insertorderedlist', //有序列表
		        'insertunorderedlist', //无序列表
		        'rowspacingtop', //段前距
		        'rowspacingbottom', //段后距
		        'lineheight', //行间距
		        'edittip ' //编辑提示
		    ]
		]
	});
    
    function getTxt(){
    	var a= UE.getEditor('editor').getContent();
    	alert(a);
    }
    </script>
  </body>
</html>
